package com.lut.controller;

import com.github.pagehelper.PageInfo;
import com.lut.entity.Department;
import com.lut.entity.HumanResource;
import com.lut.service.impl.DepartmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;
    private Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @GetMapping("/api/depts")
    public PageInfo listDept(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return departmentService.queryAllDept(pageNum, pageSize);
    }


    @GetMapping("/api/dept/options")  // select表单选项
    public List<Map<String, Object>> getAllDeptIdAndName() {
        return departmentService.queryDeptIdAndName();
    }

    @PostMapping("/api/dept")
    public int insertDept(@RequestBody Map<String, String> map, HttpServletResponse response) {
        int count = 0;
        try {
            count = departmentService.insertDept(map.get("deptName"), Integer.valueOf(map.get("leaderId")));
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(500);
        }
        return count;
    }

    @PostMapping("/api/dept/update")
    public int updateDept(@RequestBody Department department) {
        return departmentService.updateDept(department);
    }

    @PostMapping("/api/dept/delete")
    public int deleteHr(@RequestBody Map<String, String> map, HttpServletResponse response) {
        try {
            map.values().forEach(value -> {
                departmentService.deleteDept(Integer.parseInt(value));
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(500);
        }
        return map.values().size();
    }
}