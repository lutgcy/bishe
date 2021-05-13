package com.lut.service;

import com.github.pagehelper.PageInfo;
import com.lut.entity.SpecialEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SpecialService {

    void truncateTable();

    PageInfo queryAllSpecials(int pageNum, int pageSize);

    PageInfo searchSpecials(HashMap<String, Object> condition, int pageNum, int pageSize);

    int insertSpecial(SpecialEntity specialEntity);

}
