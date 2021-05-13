package com.lut.service;

import com.github.pagehelper.PageInfo;
import com.lut.entity.HumanResource;

public interface HumanResourceService {

    PageInfo queryAllHR(int pageNum, int pageSize);

    int insertHr(HumanResource humanResource);

    int deleteHr(String username);

    int updateHr(HumanResource hr);

}
