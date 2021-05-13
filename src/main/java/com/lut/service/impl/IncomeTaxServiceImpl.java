package com.lut.service.impl;

import com.lut.entity.SalaryEntity;
import com.lut.mapper.IncomeTaxMapper;
import com.lut.mapper.SalaryMapper;
import com.lut.service.IncomeTaxService;
import com.lut.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class IncomeTaxServiceImpl implements IncomeTaxService {

    @Autowired
    Constant constant;
    @Autowired
    private IncomeTaxMapper incomeTaxMapper;
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public void truncateTable() {
        incomeTaxMapper.truncateTable();
    }

    @Override
    public int generateAllIncomeTax() {
        // 先清空表
        incomeTaxMapper.truncateTable();

        List<SalaryEntity> wholeSalary = salaryMapper.getWholeSalary();

        Integer tempId = Integer.MIN_VALUE;
        Integer tempYear = Integer.MIN_VALUE;
//        List<BigDecimal> salaryOfYear;
        LinkedHashMap<Integer, BigDecimal> monthSalary = new LinkedHashMap<>();
        LinkedHashMap<Integer, BigDecimal> monthReduceSalary = new LinkedHashMap<>();
        Integer minMonth = 0;

        for (SalaryEntity salaryItem : wholeSalary) {
            BigDecimal baseSalary = salaryItem.getBaseSalary();
            BigDecimal postSalary = salaryItem.getPostSalary();
            BigDecimal salary = baseSalary.add(postSalary);

            Integer id = salaryItem.getEmpId();
            Integer year = salaryItem.getSalaryYear();
            if (!id.equals(tempId) || !year.equals(tempYear)) {
                tempId = id;
                tempYear = year;
                monthSalary.clear();
                monthReduceSalary.clear();
                List<Map<String, Object>> mapList = salaryMapper.querySalaryByEmpIdAndYear(id, year);

                mapList.sort((o1, o2) -> {
                    Integer salary_1 = (Integer) o1.get("salary_month");
                    Integer salary_2 = (Integer) o2.get("salary_month");
                    return Integer.compare(salary_1, salary_2);
                });

                minMonth = (Integer) mapList.get(0).get("salary_month");

                BigDecimal sum = new BigDecimal(0.00);
                for (Map<String, Object> map : mapList) {
                    Integer salary_month = (Integer) map.get("salary_month");
                    BigDecimal salaryOfMonth = (BigDecimal) map.get("salary");
                    sum = sum.add(salaryOfMonth);
                    monthSalary.put(salary_month, salaryOfMonth);
                    monthReduceSalary.put(salary_month, sum);
                }
                System.out.println(monthSalary);
                System.out.println(monthReduceSalary);
                /**
                 * {10=15000.00, 11=17000.00, 12=16000.00}
                 * {10=15000.00, 11=32000.00, 12=48000.00}
                 */
            }
            Integer month = salaryItem.getSalaryMonth(); // 当前月份

            BigDecimal accumulationSalary = monthReduceSalary.get(month); // 当年累计工资

//            constant.

            BigDecimal accumulationSubtract = new BigDecimal(5000).multiply(new BigDecimal(month - minMonth + 1)); // 累计减除费用





        }
        // 应纳税所得额=累计工资-5000*累计月份数-累计专项扣除-累计专项附加扣除
        // 个人所得税 = 应纳税所得额*所在级别税率 – 速算扣除数-已预缴预扣税额


        return 0;
    }

}
