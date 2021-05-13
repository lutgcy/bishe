package com.lut.controller;

import com.lut.entity.SalaryEntity;
import com.lut.service.impl.AdditionServiceImpl;
import com.lut.service.impl.SalaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdditionController {

    @Autowired
    private SalaryServiceImpl salaryService;
    @Autowired
    private AdditionServiceImpl additionService;

    @PostMapping("/api/addition/setzero")
    public String setZeroAddition() {
        additionService.truncateAddition();
        List<SalaryEntity> wholeSalary = salaryService.getWholeSalary();
        for (SalaryEntity salaryEntity : wholeSalary) {
            additionService.setZero(salaryEntity);
        }
        return "ok";
    }
}
