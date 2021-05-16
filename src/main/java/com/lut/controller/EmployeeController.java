package com.lut.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lut.entity.EmployeeEntity;
import com.lut.entity.HumanResource;
import com.lut.service.impl.EmployeeServiceImpl;
import com.lut.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;
    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/api/employee/search")
    public PageInfo listSearchEmployee(@RequestParam("condition") String condition, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request) {
        HashMap conditionMap = JSON.parseObject(condition, HashMap.class); //new TypeReference<HashMap<String,Object>>(){}  or  HashMap.class

        String getToken = request.getParameter("token");
        String username = JWTUtil.getClaimValueByToken(getToken, "username");
        String roleType = JWTUtil.getClaimValueByToken(getToken, "roleType");
        if (getToken != null) {
            if (roleType.equals("employee")) {
                conditionMap.put("username", username);
            }
        } else {
            return null;
        }
        return employeeService.searchEmployees(conditionMap, pageNum, pageSize);
    }

    @GetMapping("/api/emps")
    public PageInfo listEmp(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return employeeService.queryAllEmp(pageNum, pageSize);
    }

    @PostMapping("/api/emp")
    public int insertEmp(@RequestBody EmployeeEntity employee, HttpServletResponse response) {
        int count = 0;
        try {
            count = employeeService.insertEmp(employee);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(500);
        }
        return count;
    }

    @PostMapping("/api/emp/update")
    int updateEmp(@RequestBody EmployeeEntity employee) {
        return employeeService.updateEmp(employee);
    }


    @PostMapping("/api/emp/delete")
    int deleteEmp(@RequestBody Map<String, String> map, HttpServletResponse response) {
        try {
            map.values().forEach(value -> {
                employeeService.deleteEmp(Integer.parseInt(value));
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(500);
        }
        return map.values().size();
    }
}

