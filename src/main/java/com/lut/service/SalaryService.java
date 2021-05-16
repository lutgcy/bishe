package com.lut.service;

import com.github.pagehelper.PageInfo;
import com.lut.entity.SalaryEntity;

import java.util.HashMap;
import java.util.List;

public interface SalaryService {

    List<SalaryEntity> getWholeSalary();

    PageInfo queryAllSalaryInfo(HashMap<String, Object> condition, int pageNum, int pageSize);

    int generateNextMonthSalary();

    int initNextMonthSalary();

    int deleteLastMonth();

}
