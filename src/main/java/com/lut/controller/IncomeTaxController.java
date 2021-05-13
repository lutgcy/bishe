package com.lut.controller;

import com.lut.service.impl.IncomeTaxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncomeTaxController {

    @Autowired
    private IncomeTaxServiceImpl incomeTaxService;

    @PostMapping("/api/incometax/generate")
    public String generateIncomeTax() {


        // 生成数据



        return "success";
    }
}
