package com.lut.controller;

import com.lut.entity.RoleEntity;
import com.lut.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleEntityController {

    @Autowired
    private RoleServiceImpl roleService;


    @ResponseBody
    @GetMapping("/api/roles")
    public List<RoleEntity> list() {
        return roleService.queryAllRoles();
    }

    @ResponseBody
    @PostMapping("/api/role")
    public int createRole(@RequestBody(required = false) RoleEntity roleEntity) {
//        System.out.println(roleEntity);
        return roleService.insertRole(roleEntity);
    }


}
