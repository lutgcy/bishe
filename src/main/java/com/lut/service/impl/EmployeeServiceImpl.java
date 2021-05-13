package com.lut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lut.entity.EmployeeEntity;
import com.lut.mapper.EmployeeMapper;
import com.lut.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Override
    public PageInfo queryAllEmp(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> empList = employeeMapper.getAllEmp();
        PageInfo pageInfo = new PageInfo(empList);
        return pageInfo;
    }

    @Override
    public int insertEmp(EmployeeEntity employee) {
        return employeeMapper.insertEmp(employee);
    }

    @Override
    public int updateEmp(EmployeeEntity employee) {
        return employeeMapper.updateEmp(employee);
    }

    @Override
    public int deleteEmp(Integer empId) {
        return employeeMapper.deleteEmp(empId);
    }

    @Override
    public Integer getEmpIdFormUsername(String username) {
        return employeeMapper.getEmpIdFormUsername(username);
    }
}
