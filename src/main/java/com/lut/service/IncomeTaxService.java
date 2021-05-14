package com.lut.service;


import com.github.pagehelper.PageInfo;

import java.util.HashMap;

public interface IncomeTaxService {

    void truncateTable();

    int generateAllIncomeTax();

    PageInfo searchIncomeTax(HashMap<String, Object> condition, int pageNum, int pageSize);

}
