package com.lut.controller;

import com.lut.entity.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author guochengye
 * @create 2021-08-29 12:08
 * @description hello
 */

@Slf4j
@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello(@RequestBody EmployeeEntity emp, BindingResult bindingResult) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//        }
        log.error(bindingResult.toString());
        return "Hello " + emp;
    }
}
