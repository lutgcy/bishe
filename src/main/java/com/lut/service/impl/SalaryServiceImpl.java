package com.lut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lut.entity.AddApplyEntity;
import com.lut.entity.IncomeTaxEntity;
import com.lut.entity.SalaryEntity;
import com.lut.entity.SpecialEntity;
import com.lut.mapper.*;
import com.lut.service.SalaryService;
import com.lut.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;
    @Autowired
    private Constant CONSTANT;
    @Autowired
    private SpecialMapper specialMapper;
    @Autowired
    private AdditionMapper additionMapper;
    @Autowired
    private AddApplyMapper addApplyMapper;
    @Autowired
    private IncomeTaxMapper incomeTaxMapper;

    @Override
    public List<SalaryEntity> getWholeSalary() {
        return salaryMapper.getWholeSalary();
    }

    @Override
    public PageInfo queryAllSalaryInfo(HashMap<String, Object> condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> salaryInfo = salaryMapper.queryAllSalaryInfo(condition);
        PageInfo pageInfo = new PageInfo(salaryInfo);
        return pageInfo;
    }

    @Override
    public int initNextMonthSalary() {
        // 查询到最大 年份 月份+1>12 则year+1
        Map<String, Integer> nextMonthDate = salaryMapper.getNextMonthDate();
        Integer salary_year = nextMonthDate.get("salary_year");
        Integer salary_month = nextMonthDate.get("salary_month");
        if (salary_month == 12) {
            salary_year += 1;  // 下月 年
            salary_month = 1; // 下月 月
        } else {
            salary_month += 1;
        }
        // 查询到所有员工信息、模拟工资
        List<Map<String, Object>> salaryList = salaryMapper.getSalaryForNextMonth();

        // 插入 下月 工资 数据
        ArrayList<SalaryEntity> salaryNextList = new ArrayList<>();

        // 生成 并 插入 专项扣除 数据
        ArrayList<SpecialEntity> specialList = new ArrayList<>();
        for (Map<String, Object> map : salaryList) {
            Integer empId = (Integer) map.get("emp_id");
            BigDecimal postSalary = new BigDecimal(String.valueOf(map.get("salary")));

            SalaryEntity salaryEntity = new SalaryEntity();
            salaryEntity.setEmpId(empId);
            salaryEntity.setSalaryYear(salary_year);
            salaryEntity.setSalaryMonth(salary_month);
            salaryEntity.setPostSalary(postSalary);
            salaryEntity.setBaseSalary(CONSTANT.getBaseSalary());
            salaryNextList.add(salaryEntity);

            BigDecimal salary = postSalary.add(CONSTANT.getBaseSalary());
            SpecialEntity specialEntity = new SpecialEntity();
            specialEntity.setEmpId(empId);
            specialEntity.setSpacialYear(salary_year);
            specialEntity.setSpecialMonth(salary_month);
            specialEntity.setCompBirth(salary.multiply(CONSTANT.getCompBirthRate()));
            specialEntity.setCompFire(salary.multiply(CONSTANT.getCompFireRate()));
            specialEntity.setCompHurt(salary.multiply(CONSTANT.getCompHurtRate()));
            specialEntity.setCompMedical(salary.multiply(CONSTANT.getCompMedicalRate()));
            specialEntity.setCompOld(salary.multiply(CONSTANT.getCompOldRate()));
            specialEntity.setCompHouse(salary.multiply(CONSTANT.getCompHouseRate()));
            specialEntity.setPerFire(salary.multiply(CONSTANT.getPerFireRate()));
            specialEntity.setPerMedical(salary.multiply(CONSTANT.getPerMedicalRate()));
            specialEntity.setPerOld(salary.multiply(CONSTANT.getPerOldRate()));
            specialEntity.setPerHouse(salary.multiply(CONSTANT.getPerHouseRate()));
            specialList.add(specialEntity);
        }
        specialMapper.insertManySpecials(specialList);
        // specialaddition表 用0初始化专项附加扣除
        additionMapper.insertNewAdditionWithZero(specialList);

        salaryMapper.insertInitSalary(salaryNextList);
        return 1;
    }

    @Override
    public int generateNextMonthSalary() {
        // 生成专项附加扣除 ---> 执行 apply表 中申请成功数据
        List<AddApplyEntity> addApplyEntities = addApplyMapper.selectAddApplyByState();
        for (AddApplyEntity addApplyEntity : addApplyEntities) {
            String applyType = addApplyEntity.getApplyType();
            Integer empId = addApplyEntity.getEmpId();
            Date applyDate = addApplyEntity.getApplyDate();
            BigDecimal applyMoney = addApplyEntity.getApplyMoney();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(applyDate);
            HashMap<String, Object> argsMap = new HashMap<>();
            argsMap.put("applyType", applyType);
            argsMap.put("applyMoney", applyMoney);
            argsMap.put("empId", empId);
            argsMap.put("additionYear", calendar.get(Calendar.YEAR));
            for (int i = calendar.get(Calendar.MONTH) + 1; i <= 12; i++) {
                argsMap.put("additionMonth", i);
                additionMapper.submitApply(argsMap);
            }
        }
        // 生成纳税明细  =======》计算 实发 工资
        // 查询到最大 年份 月份
        Map<String, Integer> nextMonthDate = salaryMapper.getNextMonthDate();
        Integer new_year = nextMonthDate.get("salary_year");
        Integer new_month = nextMonthDate.get("salary_month");
        List<SalaryEntity> salaryByYear = salaryMapper.getSalaryByYearAndMonth(new_year, new_month);

        salaryByYear.sort((o1, o2) -> {
            return Integer.compare(o1.getEmpId(), o2.getEmpId());
        });

        LinkedHashMap<Integer, BigDecimal> monthSalary = new LinkedHashMap<>();
        LinkedHashMap<Integer, BigDecimal> monthReduceSalary = new LinkedHashMap<>();
        LinkedHashMap<Integer, BigDecimal> monthSpecial = new LinkedHashMap<>();
        LinkedHashMap<Integer, BigDecimal> monthReduceSpecial = new LinkedHashMap<>();
        LinkedHashMap<Integer, BigDecimal> monthReduceAddition = new LinkedHashMap<>();
        Integer minMonth = 0;

        for (SalaryEntity salaryItem : salaryByYear) {
            Integer id = salaryItem.getEmpId();
            Integer year = salaryItem.getSalaryYear();

            monthSalary.clear();
            monthReduceSalary.clear();
            monthSpecial.clear();
            monthReduceSpecial.clear();
            monthReduceAddition.clear();

            List<Map<String, Object>> salaryMapList = salaryMapper.querySalaryByEmpIdAndYear(id, year);
            List<Map<String, Object>> specialMapList = specialMapper.querySpecialByEmpIdAndYear(id, year);
            List<Map<String, Object>> additionMapList = additionMapper.queryAdditionByEmpIdAndYear(id, year);

            salaryMapList.sort((o1, o2) -> {
                Integer salary_1 = (Integer) o1.get("salary_month");
                Integer salary_2 = (Integer) o2.get("salary_month");
                return Integer.compare(salary_1, salary_2);
            });

            specialMapList.sort((o1, o2) -> {
                Integer special_1 = (Integer) o1.get("special_month");
                Integer special_2 = (Integer) o2.get("special_month");
                return Integer.compare(special_1, special_2);
            });

            additionMapList.sort((o1, o2) -> {
                Integer addition_1 = (Integer) o1.get("addition_month");
                Integer addition_2 = (Integer) o2.get("addition_month");
                return Integer.compare(addition_1, addition_2);
            });

            minMonth = (Integer) salaryMapList.get(0).get("salary_month"); // 开始月份

            BigDecimal salarySum = new BigDecimal(0.00);
            for (Map<String, Object> map : salaryMapList) {
                Integer salary_month = (Integer) map.get("salary_month");
                BigDecimal salaryOfMonth = (BigDecimal) map.get("salary");
                monthSalary.put(salary_month, salaryOfMonth);
                salarySum = salarySum.add(salaryOfMonth);
                monthReduceSalary.put(salary_month, salarySum);
            }

            BigDecimal specialSum = new BigDecimal(0.00);
            for (Map<String, Object> map : specialMapList) {
                Integer special_month = (Integer) map.get("special_month");
                BigDecimal specialOfMonth = (BigDecimal) map.get("perSpecialSum");
                monthSpecial.put(special_month, specialOfMonth);
                specialSum = specialSum.add(specialOfMonth);
                monthReduceSpecial.put(special_month, specialSum);
            }

            BigDecimal additionSum = new BigDecimal(0.00);
            for (Map<String, Object> map : additionMapList) {
                Integer addition_month = (Integer) map.get("addition_month");
                BigDecimal additionOfMonth = (BigDecimal) map.get("additionSum");
                additionSum = additionSum.add(additionOfMonth);
                monthReduceAddition.put(addition_month, additionSum);
            }

            Integer month = salaryItem.getSalaryMonth(); // 当前月份
            // 应纳税所得额=累计工资-5000*累计月份数（累计减除费用）-累计专项扣除-累计专项附加扣除
            BigDecimal accumulationSalary = monthReduceSalary.get(month); // 当前月 年累计工资
            BigDecimal accumulationSpecial = monthReduceSpecial.get(month); // 当月 年累计 专项扣除（五险一金）
            BigDecimal accumulationAddition = monthReduceAddition.get(month); // 当月 年累计 专项附加扣除

            BigDecimal threshold = CONSTANT.getThreshold();  // 个税起征点
            BigDecimal accumulationSubtract = threshold.multiply(new BigDecimal(month - minMonth + 1)); // 累计减除费用=个税起征点*月份数
            // 应纳税所得额
            BigDecimal taxableIncome = accumulationSalary.subtract(accumulationSubtract).subtract(accumulationSpecial).subtract(accumulationAddition);
            int compare = taxableIncome.compareTo(new BigDecimal(0.00));
            if (compare == -1) { // 小于0的 置0
                taxableIncome = new BigDecimal(0.00);
            }
            // 个人所得税 = 应纳税所得额*所在级别税率 – 速算扣除数-已预缴预扣税额
            BigDecimal currentMonthTaxMoney = new BigDecimal(0.00);
            BigDecimal paidTaxMoney = incomeTaxMapper.getPaidTaxMoney(id, year, month);
            if (paidTaxMoney == null) {
                paidTaxMoney = new BigDecimal(0.00);
            }

            if (taxableIncome.compareTo(CONSTANT.getLevel1TaxableIncome()) != 1) { // <= 36000
                currentMonthTaxMoney = taxableIncome.multiply(CONSTANT.getLevel1Rate()).subtract(CONSTANT.getLevel1QuickNum()).subtract(paidTaxMoney);
            } else if (taxableIncome.compareTo(CONSTANT.getLevel1TaxableIncome()) == 1 && taxableIncome.compareTo(CONSTANT.getLevel2TaxableIncome()) != 1) {
                currentMonthTaxMoney = taxableIncome.multiply(CONSTANT.getLevel2Rate()).subtract(CONSTANT.getLevel2QuickNum()).subtract(paidTaxMoney);
            } else if (taxableIncome.compareTo(CONSTANT.getLevel2TaxableIncome()) == 1 && taxableIncome.compareTo(CONSTANT.getLevel3TaxableIncome()) != 1) {
                currentMonthTaxMoney = taxableIncome.multiply(CONSTANT.getLevel3Rate()).subtract(CONSTANT.getLevel3QuickNum()).subtract(paidTaxMoney);
            } else if (taxableIncome.compareTo(CONSTANT.getLevel3TaxableIncome()) == 1 && taxableIncome.compareTo(CONSTANT.getLevel4TaxableIncome()) != 1) {
                currentMonthTaxMoney = taxableIncome.multiply(CONSTANT.getLevel4Rate()).subtract(CONSTANT.getLevel4QuickNum()).subtract(paidTaxMoney);
            } else if (taxableIncome.compareTo(CONSTANT.getLevel4TaxableIncome()) == 1 && taxableIncome.compareTo(CONSTANT.getLevel5TaxableIncome()) != 1) {
                currentMonthTaxMoney = taxableIncome.multiply(CONSTANT.getLevel5Rate()).subtract(CONSTANT.getLevel5QuickNum()).subtract(paidTaxMoney);
            } else if (taxableIncome.compareTo(CONSTANT.getLevel5TaxableIncome()) == 1 && taxableIncome.compareTo(CONSTANT.getLevel6TaxableIncome()) != 1) {
                currentMonthTaxMoney = taxableIncome.multiply(CONSTANT.getLevel6Rate()).subtract(CONSTANT.getLevel6QuickNum()).subtract(paidTaxMoney);
            } else if (taxableIncome.compareTo(CONSTANT.getLevel6TaxableIncome()) == 1) {
                currentMonthTaxMoney = taxableIncome.multiply(CONSTANT.getLevel7Rate()).subtract(CONSTANT.getLevel7QuickNum()).subtract(paidTaxMoney);
            }
            if (currentMonthTaxMoney.compareTo(new BigDecimal(0.00)) == -1) {
                currentMonthTaxMoney = new BigDecimal(0.00);
            }
            IncomeTaxEntity incomeTaxEntity = new IncomeTaxEntity();
            incomeTaxEntity.setEmpId(id);
            incomeTaxEntity.setTaxYear(year);
            incomeTaxEntity.setTaxMonth(month);
            incomeTaxEntity.setTaxableIncome(taxableIncome);
            incomeTaxEntity.setTaxMoney(currentMonthTaxMoney);
            incomeTaxMapper.insertIncomeTax(incomeTaxEntity);

            BigDecimal salary = monthSalary.get(month); // 应发工资
            BigDecimal special = monthSpecial.get(month);
            BigDecimal realSalary = salary.subtract(special).subtract(currentMonthTaxMoney);
            salaryMapper.setRealSalary(realSalary, id, year, month);
        }

        // 初始化下月 数据
        initNextMonthSalary();
        return 1;
    }

    @Override
    public int deleteLastMonth() {
        // // 查询到最大 年份 月份
        Map<String, Integer> nextMonthDate = salaryMapper.getNextMonthDate();
        Integer max_year = nextMonthDate.get("salary_year");
        Integer max_month = nextMonthDate.get("salary_month");

        Integer last_year;
        Integer last_month;

        if (max_month == 1) {
            last_year = max_year - 1;
            last_month = 12;
        } else {
            last_year = max_year;
            last_month = max_month - 1;
        }

        salaryMapper.deleteSalaryNewMonth(max_year, max_month); //  删 工资表
        salaryMapper.setLastMonthRealSalaryZero(last_year, last_month);
        specialMapper.deleteSpecialNewMonth(max_year, max_month);
        additionMapper.deleteAdditionNewMonth(max_year, max_month);

        incomeTaxMapper.deleteIncomeTaxNewMonth(last_year, last_month);
        return 1;
    }
}
