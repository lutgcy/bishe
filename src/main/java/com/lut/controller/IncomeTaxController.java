package com.lut.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lut.service.impl.IncomeTaxServiceImpl;
import com.lut.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class IncomeTaxController {

    @Autowired
    private IncomeTaxServiceImpl incomeTaxService;

    @PostMapping("/api/incometax/generate")
    public String generateIncomeTax() {

        // 生成数据
        incomeTaxService.generateAllIncomeTax();

        return "success";
    }

    @GetMapping("/api/tax/search")
    public PageInfo searchIncomeTax(@RequestParam("condition") String condition, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request) {
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
        return incomeTaxService.searchIncomeTax(conditionMap, pageNum, pageSize);
    }
}
