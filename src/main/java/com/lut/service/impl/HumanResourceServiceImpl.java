package com.lut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lut.entity.HumanResource;
import com.lut.mapper.HumanResourceMapper;
import com.lut.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumanResourceServiceImpl implements HumanResourceService {

    @Autowired
    private HumanResourceMapper humanResourceMapper;


    @Override
    public PageInfo queryAllHR(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<HumanResource> allHR = humanResourceMapper.getAllHR();
        PageInfo pageInfo = new PageInfo(allHR);

        return pageInfo;
    }

    @Override
    public int insertHr(HumanResource humanResource) {
        return humanResourceMapper.insertHr(humanResource);
    }

    @Override
    public int deleteHr(String username) {
        return humanResourceMapper.deleteHr(username);
    }

    @Override
    public int updateHr(HumanResource hr) {
        return humanResourceMapper.updateHr(hr);
    }
}
