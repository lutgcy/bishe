package com.lut.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lut.entity.SalaryEntity;
import com.lut.entity.SpecialEntity;
import com.lut.service.impl.SalaryServiceImpl;
import com.lut.service.impl.SpecialServiceImpl;
import com.lut.utils.Constant;
import com.lut.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class SpecialController {

    @Autowired
    private SpecialServiceImpl specialService;
    @Autowired
    private SalaryServiceImpl salaryService;
    @Autowired
    private Constant constant;

    @PostMapping("/api/generatespecial")
    public String generateSpecial() {

        specialService.truncateTable();
        List<SalaryEntity> wholeSalary = salaryService.getWholeSalary();
        ArrayList<SpecialEntity> specialList = new ArrayList<>();

        for (SalaryEntity salaryEntity : wholeSalary) {
            BigDecimal baseSalary = salaryEntity.getBaseSalary();
            BigDecimal postSalary = salaryEntity.getPostSalary();
            BigDecimal salary = baseSalary.add(postSalary);

            SpecialEntity specialEntity = new SpecialEntity();

            specialEntity.setEmpId(salaryEntity.getEmpId());
            specialEntity.setSpacialYear(salaryEntity.getSalaryYear());
            specialEntity.setSpecialMonth(salaryEntity.getSalaryMonth());

            specialEntity.setCompBirth(salary.multiply(constant.getCompBirthRate()));
            specialEntity.setCompFire(salary.multiply(constant.getCompFireRate()));
            specialEntity.setCompHurt(salary.multiply(constant.getCompHurtRate()));
            specialEntity.setCompMedical(salary.multiply(constant.getCompMedicalRate()));
            specialEntity.setCompOld(salary.multiply(constant.getCompOldRate()));
            specialEntity.setCompHouse(salary.multiply(constant.getCompHouseRate()));
            specialEntity.setPerFire(salary.multiply(constant.getPerFireRate()));
            specialEntity.setPerMedical(salary.multiply(constant.getPerMedicalRate()));
            specialEntity.setPerOld(salary.multiply(constant.getPerOldRate()));
            specialEntity.setPerHouse(salary.multiply(constant.getPerHouseRate()));
            specialList.add(specialEntity);
//            specialService.insertSpecial(specialEntity);
        }
        specialService.insertManySpecials(specialList);
        return "success";
    }

    @GetMapping("/api/specials")
    public PageInfo listSpecial(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return specialService.queryAllSpecials(pageNum, pageSize);
    }


    @GetMapping("/api/special/search")
    public PageInfo listSearchSpecial(@RequestParam("condition") String condition, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request) {
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
        return specialService.searchSpecials(conditionMap, pageNum, pageSize);
    }
}
