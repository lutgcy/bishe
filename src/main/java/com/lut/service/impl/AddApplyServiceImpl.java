package com.lut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lut.entity.AddApplyEntity;
import com.lut.mapper.AddApplyMapper;
import com.lut.service.AddApplyService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddApplyServiceImpl implements AddApplyService {

    @Autowired
    private AddApplyMapper addApplyMapper;

    @Override
    public int insertAddApply(AddApplyEntity applyEntity) {
        return addApplyMapper.insertAddApply(applyEntity);
    }

    @Override
    public int setApplyFile(String applyFile, Integer id) {
        return addApplyMapper.setApplyFile(applyFile, id);
    }

    @Override
    public PageInfo searchAddApply(HashMap<String, Object> condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> searchSpecials = addApplyMapper.searchAddApply(condition);
        PageInfo pageInfo = new PageInfo(searchSpecials);
        return pageInfo;
    }

    @Override
    public int agreeApply(Integer id) {
        return addApplyMapper.agreeApply(id);
    }

    @Override
    public int rejectApply(Integer id) {
        return addApplyMapper.rejectApply(id);
    }

    @Override
    public AddApplyEntity selectAddApply(Integer id) {
        return addApplyMapper.selectAddApply(id);
    }

    @Override
    public int alterApply(AddApplyEntity applyEntity) {
        return addApplyMapper.alterApply(applyEntity);
    }
}
