package com.lut.service.impl;

import com.lut.entity.SalaryEntity;
import com.lut.mapper.AdditionMapper;
import com.lut.service.AdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdditionServiceImpl implements AdditionService {

    @Autowired
    private AdditionMapper additionMapper;

    @Override
    public void truncateAddition() {
        additionMapper.truncateAddition();
    }

    @Override
    public int setZero(SalaryEntity salaryEntity) {
        return additionMapper.setZero(salaryEntity);
    }

    @Override
    public int submitApply(HashMap<String, Object> argMap) {
        return additionMapper.submitApply(argMap);
    }
}
