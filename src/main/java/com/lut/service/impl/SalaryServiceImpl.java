package com.lut.service.impl;

import com.lut.entity.SalaryEntity;
import com.lut.mapper.SalaryMapper;
import com.lut.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public List<SalaryEntity> getWholeSalary() {
        return salaryMapper.getWholeSalary();
    }
}
