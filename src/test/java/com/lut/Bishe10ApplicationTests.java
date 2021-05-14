package com.lut;


import com.lut.entity.SpecialEntity;
import com.lut.mapper.IncomeTaxMapper;
import com.lut.mapper.SalaryMapper;
import com.lut.mapper.SpecialMapper;
import com.lut.service.impl.IncomeTaxServiceImpl;
import com.lut.utils.Constant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootTest
class Bishe10ApplicationTests {


    @Autowired
    private Constant constant;
    @Autowired
    private SalaryMapper salaryMapper;
    @Autowired
    private IncomeTaxServiceImpl incomeTaxService;
    @Autowired
    private IncomeTaxMapper incomeTaxMapper;


    @Test
    public void test05() {
        BigDecimal paidTaxMoney = incomeTaxMapper.getPaidTaxMoney(1, 2018, 6);
        System.out.println(paidTaxMoney);

    }

    @Test
    public void test04() {
        incomeTaxService.generateAllIncomeTax();
    }



    @Test
    void test03() {
        List<Map<String, Object>> maps = salaryMapper.querySalaryByEmpIdAndYear(1, 2018);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
            /**
             * {salary_month=10, salary=15000.00}
             * {salary_month=11, salary=16000.00}
             * {salary_month=12, salary=17000.00}
             */
        }
    }

    @Test
    void contextLoads() {
        System.out.println(constant);
    }


    @Autowired
    private SpecialMapper specialMapper;

    @Test
    void test01() throws IOException {
        System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
        File directory = new File("");//设定为当前文件夹
        System.out.println(directory.getCanonicalPath());//获取标准的路径
        System.out.println(directory.getAbsolutePath());//获取绝对路径

    }

    @Test
    void test02() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        List<Integer> list = numbers.stream().map(s -> s * s).collect(Collectors.toList());
        list.forEach(System.out::println);

        for (int i = 1; i < numbers.size(); i++) {
            numbers.set(i, numbers.get(i - 1) + numbers.get(i));
        }
        System.out.println(numbers.toString());
    }

}
