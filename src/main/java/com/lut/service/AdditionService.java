package com.lut.service;

import com.lut.entity.SalaryEntity;

import java.util.HashMap;

public interface AdditionService {

    void truncateAddition();

    int setZero(SalaryEntity salaryEntity);

    int submitApply(HashMap<String, Object> argMap);

}
