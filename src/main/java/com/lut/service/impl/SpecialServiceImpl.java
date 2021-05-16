package com.lut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lut.entity.SpecialEntity;
import com.lut.mapper.SpecialMapper;
import com.lut.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpecialServiceImpl implements SpecialService {

    @Autowired
    private SpecialMapper specialMapper;

    @Override
    public void truncateTable() {
        specialMapper.truncateTable();
    }

    @Override
    public int insertSpecial(SpecialEntity specialEntity) {
        return specialMapper.insertSpecial(specialEntity);
    }

    @Override
    public PageInfo queryAllSpecials(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> specialList = specialMapper.getAllSpecials();
        PageInfo pageInfo = new PageInfo(specialList);
        return pageInfo;
    }

    @Override
    public PageInfo searchSpecials(HashMap<String, Object> condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> searchSpecials = specialMapper.searchSpecials(condition);
        PageInfo pageInfo = new PageInfo(searchSpecials);
        return pageInfo;
    }

    @Override
    public int insertManySpecials(List<SpecialEntity> list) {
        return specialMapper.insertManySpecials(list);
    }
}
