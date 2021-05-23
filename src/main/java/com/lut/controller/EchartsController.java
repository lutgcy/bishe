package com.lut.controller;

import com.lut.mapper.EmployeeMapper;
import com.lut.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EchartsController {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private SalaryMapper salaryMapper;

    @GetMapping("/api/echarts/pie")
    List<Map<String, Object>> pie() {
        return employeeMapper.countPostRate();
    }

    @GetMapping("/api/echarts/histogram")
    List<Map<String, Object>> histogramChart() {
        return employeeMapper.salaryInterval();
    }

    @GetMapping("/api/echarts/line")
    List<Map<String, Object>> lineChart() {
        List<Map<String, Object>> avgSalary = salaryMapper.getAVGSalary();
        avgSalary.addAll(salaryMapper.getAVGTax());
        avgSalary.addAll(salaryMapper.getAVGIncome());
        return avgSalary;
    }
}
