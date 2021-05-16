package com.lut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lut.entity.IncomeTaxEntity;
import com.lut.entity.SalaryEntity;
import com.lut.mapper.AdditionMapper;
import com.lut.mapper.IncomeTaxMapper;
import com.lut.mapper.SalaryMapper;
import com.lut.mapper.SpecialMapper;
import com.lut.service.IncomeTaxService;
import com.lut.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class IncomeTaxServiceImpl implements IncomeTaxService {

    @Autowired
    Constant CONSTANT;
    @Autowired
    private IncomeTaxMapper incomeTaxMapper;
    @Autowired
    private SalaryMapper salaryMapper;
    @Autowired
    private SpecialMapper specialMapper;
    @Autowired
    private AdditionMapper additionMapper;

    @Override
    public void truncateTable() {
        incomeTaxMapper.truncateTable();
    }

    @Override
    public PageInfo searchIncomeTax(HashMap<String, Object> condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> taxList = incomeTaxMapper.searchIncomeTax(condition);
        PageInfo pageInfo = new PageInfo(taxList);
        return pageInfo;
    }

    @Override
    public int generateAllIncomeTax() {
        // 先清空表
        incomeTaxMapper.truncateTable();

        List<SalaryEntity> wholeSalary = salaryMapper.getWholeSalary();
        wholeSalary.sort(new Comparator<SalaryEntity>() { // 按照 empId  year  month 排序
            @Override
            public int compare(SalaryEntity o1, SalaryEntity o2) {
                if (o1.getEmpId() > o2.getEmpId()) {
                    return 1;
                } else if (o1.getEmpId() < o2.getEmpId()) {
                    return -1;
                } else {
                    if (o1.getSalaryYear() > o2.getSalaryYear()) {
                        return 1;
                    } else if (o1.getSalaryYear() < o2.getSalaryYear()) {
                        return -1;
                    } else {
                        return Integer.compare(o1.getSalaryMonth(), o2.getSalaryMonth());
                    }
                }
            }
        });
        Integer tempId = Integer.MIN_VALUE;
        Integer tempYear = Integer.MIN_VALUE;
        LinkedHashMap<Integer, BigDecimal> monthSalary = new LinkedHashMap<>();
        LinkedHashMap<Integer, BigDecimal> monthReduceSalary = new LinkedHashMap<>();
        LinkedHashMap<Integer, BigDecimal> monthSpecial = new LinkedHashMap<>();
        LinkedHashMap<Integer, BigDecimal> monthReduceSpecial = new LinkedHashMap<>();
        LinkedHashMap<Integer, BigDecimal> monthReduceAddition = new LinkedHashMap<>();
        Integer minMonth = 0;

        for (SalaryEntity salaryItem : wholeSalary) {
            Integer id = salaryItem.getEmpId();
            Integer year = salaryItem.getSalaryYear();
            if (!id.equals(tempId) || !year.equals(tempYear)) {
                tempId = id;
                tempYear = year;
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
        return 0;
    }

}
