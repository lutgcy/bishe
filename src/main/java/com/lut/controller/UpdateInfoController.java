package com.lut.controller;

import com.lut.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateInfoController {

    @Autowired
    private AdminMapper adminMapper;

    @PostMapping("/api/update/info/admin")
    public String updateAdminInfo(@RequestBody Map<String, String> map) {
        adminMapper.updateUsername(map.get("newUsername"), map.get("oldUsername"));
        return "OK";
    }

}
