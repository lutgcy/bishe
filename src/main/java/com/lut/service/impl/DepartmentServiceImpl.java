package com.lut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lut.entity.Department;
import com.lut.mapper.DepartmentMapper;
import com.lut.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public PageInfo queryAllDept(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> deptList = departmentMapper.getAllDept();
        PageInfo pageInfo = new PageInfo(deptList);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> queryDeptIdAndName() {
        return departmentMapper.getDeptIdAndName();
    }

    @Override
    public int insertDept(String deptName, Integer leaderId) {
        return departmentMapper.insertDept(deptName, leaderId);
    }

    @Override
    public int updateDept(Department department) {
        return departmentMapper.updateDept(department);
    }

    @Override
    public int deleteDept(Integer deptId) {
        return departmentMapper.deleteDept(deptId);
    }
}
