package com.lut.controller;

import com.lut.entity.AdminEntity;
import com.lut.entity.EmployeeEntity;
import com.lut.entity.HumanResource;
import com.lut.mapper.AdminMapper;
import com.lut.mapper.EmployeeMapper;
import com.lut.mapper.HumanResourceMapper;
import com.lut.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginHandler {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private HumanResourceMapper humanResourceMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    @ResponseBody
    @RequestMapping(path = "/api/admin/login", method = RequestMethod.POST)
    public Map<String, String> login(@RequestBody Map<String, String> map, HttpServletResponse response, HttpServletRequest request) {

        String username = map.get("username");
        String pwdSHA256 = map.get("pwdSHA256");
        AdminEntity adminByUserName = adminMapper.getAdminByUserName(username);

        Map<String, String> hashMap = new HashMap<>();
        if (adminByUserName !=null && adminByUserName.getPwdHash().equals(pwdSHA256)) {
            String secret = pwdSHA256;
            Map<String, String> claimMap = new HashMap<>();
            claimMap.put("username", adminByUserName.getUsername());
            claimMap.put("roleType", "admin");
            Long expireSecond = 12 * 60 * 60 * 1000L;
            String token = JWTUtil.sign(claimMap, secret, expireSecond);
            hashMap.put("token", token);
            hashMap.put("roleType", "admin");
            return hashMap;
        }
        return hashMap;
    }

    @ResponseBody
    @RequestMapping(path = "/api/hr/login", method = RequestMethod.POST)
    public Map<String, String> hrLogin(@RequestBody Map<String, String> map, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {

        String username = map.get("username");
        String pwdSHA256 = map.get("pwdSHA256");

        HumanResource humanResourceByUserName = humanResourceMapper.getHumanResourceByUserName(username);
        System.out.println(humanResourceByUserName);

        Map<String, String> hashMap = new HashMap<>();
        if (humanResourceByUserName !=null && humanResourceByUserName.getPwdHash().equals(pwdSHA256)) {
//            String secret = "i am private key!!!";
            String secret = pwdSHA256;
            Map<String, String> claimMap = new HashMap<>();
            claimMap.put("username", humanResourceByUserName.getUsername());
            claimMap.put("roleType", "hr");
            Long expireSecond = 12 * 60 * 60 * 1000L;
            String token = JWTUtil.sign(claimMap, secret, expireSecond);
            hashMap.put("token", token);
            hashMap.put("roleType", "hr");
            return hashMap;
        }
        return hashMap;
    }

    @ResponseBody
    @RequestMapping(path = "/api/employee/login", method = RequestMethod.POST)
    public Map<String, String> employeeLogin(@RequestBody Map<String, String> map) {

        String username = map.get("username");
        String pwdSHA256 = map.get("pwdSHA256");

        EmployeeEntity loginInfoFromEmployee = employeeMapper.getLoginInfoFromEmployee(username);

        Map<String, String> hashMap = new HashMap<>();
        if (loginInfoFromEmployee !=null && loginInfoFromEmployee.getPwdHash().equals(pwdSHA256)) {
//            String secret = "i am private key!!!";
            String secret = pwdSHA256;
            Map<String, String> claimMap = new HashMap<>();
            claimMap.put("username", loginInfoFromEmployee.getUsername());
            claimMap.put("roleType", "employee");
            Long expireSecond = 12 * 60 * 60 * 1000L;
            String token = JWTUtil.sign(claimMap, secret, expireSecond);
            hashMap.put("token", token);
            hashMap.put("roleType", "employee");
            return hashMap;
        }
        return hashMap;
    }


    @ResponseBody
    @GetMapping("/api/admin//info")
    public Map<String, Object> getInfo() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("roles", new String[]{"admin"});
        hashMap.put("name", "超级管理员");
        //        hashMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        hashMap.put("avatar", "http://localhost:8080/header-image/head.jpg"); // http://localhost:8080/header-image/admin.jpg
        hashMap.put("introduction", "I am a super administrator");
        return hashMap;
    }

    @ResponseBody
    @GetMapping("/api/hr/info")
    public Map<String, Object> hrGetInfo(@RequestParam(value = "token", required = false) String token) {
        String username = JWTUtil.getClaimValueByToken(token, "username");
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("roles", new String[]{"hr"});
        hashMap.put("name", username);
//        hashMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        hashMap.put("avatar", "http://localhost:8080/header-image/head.jpg"); // http://localhost:8080/header-image/admin.jpg
        hashMap.put("introduction", "I am a Human Resource");
        return hashMap;
    }

    @ResponseBody
    @GetMapping("/api/employee/info")
    public Map<String, Object> employeeGetInfo(@RequestParam(value = "token", required = false) String token) {
        String username = JWTUtil.getClaimValueByToken(token, "username");
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("roles", new String[]{"employee"});
        hashMap.put("name", username);
//        hashMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        hashMap.put("avatar", "http://localhost:8080/header-image/head.jpg"); // http://localhost:8080/header-image/admin.jpg
        hashMap.put("introduction", "I am a Employee");
        return hashMap;
    }

    @ResponseBody
    @PostMapping("/api/logout")
    public String logout() {
        logger.warn("退出登录成功！！！");
        return "退出登录成功！！！";
    }

}
