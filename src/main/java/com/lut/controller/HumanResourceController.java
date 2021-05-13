package com.lut.controller;

import com.github.pagehelper.PageInfo;
import com.lut.entity.HumanResource;
import com.lut.service.impl.HumanResourceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Controller
public class HumanResourceController {

    @Autowired
    private HumanResourceServiceImpl humanResourceService;
    private Logger logger = LoggerFactory.getLogger(HumanResource.class);


    @GetMapping("/api/hrs")
    public PageInfo listHr(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return humanResourceService.queryAllHR(pageNum, pageSize);
    }

    @PostMapping("/api/hr")
    public int insertHr(@RequestBody HumanResource hr, HttpServletResponse response) {
        hr.setPwdHash("bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a");
        int count = 0;
        try {
            count = humanResourceService.insertHr(hr);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(500);
        }
        return count;
    }

    @PostMapping("/api/hr/delete")
    public int deleteHr(@RequestBody Map<String, String> map, HttpServletResponse response) {
        try {
            map.values().forEach(value -> {
                humanResourceService.deleteHr(value);
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(500);
        }
        return map.values().size();
    }

    @PostMapping("/api/hr/update")
    public int updateHr(@RequestBody HumanResource hr, HttpServletResponse response) {
        return humanResourceService.updateHr(hr);
    }

}
