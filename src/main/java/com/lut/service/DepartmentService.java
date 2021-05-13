package com.lut.service;

import com.github.pagehelper.PageInfo;
import com.lut.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    PageInfo queryAllDept(int pageNum, int pageSize);

    List<Map<String, Object>> queryDeptIdAndName();

    int insertDept(String deptName, Integer leaderId);

    int updateDept(Department department);

    int deleteDept(Integer deptId);
}
