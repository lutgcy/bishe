package com.lut.service;

import com.github.pagehelper.PageInfo;
import com.lut.entity.AddApplyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AddApplyService {

    int insertAddApply(AddApplyEntity applyEntity);

    int setApplyFile(String applyFile, Integer id);

    PageInfo searchAddApply(HashMap<String, Object> condition, int pageNum, int pageSize);

    int agreeApply(Integer id);

    int rejectApply(Integer id);

    AddApplyEntity selectAddApply(Integer id);

    int alterApply(AddApplyEntity applyEntity);
}
