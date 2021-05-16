package com.lut.service;

import com.github.pagehelper.PageInfo;
import com.lut.entity.EmployeeEntity;

import java.util.HashMap;

public interface EmployeeService {

    PageInfo queryAllEmp(int pageNum, int pageSize);

    int insertEmp(EmployeeEntity employee);

    int updateEmp(EmployeeEntity employee);

    int deleteEmp(Integer empId);

    Integer getEmpIdFormUsername(String username);

    PageInfo searchEmployees(HashMap<String, Object> condition, int pageNum, int pageSize);
}
