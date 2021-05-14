package com.lut;

import com.lut.utils.Constant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@EnableConfigurationProperties(Constant.class) // 注入失败
//@ConfigurationPropertiesScan("com.lut.utils") // 注入失败
@EnableSwagger2
@SpringBootApplication
@MapperScan("com/lut/mapper")
public class BiShe10Application {

    public static void main(String[] args) {
        SpringApplication.run(BiShe10Application.class, args);
    }

}
