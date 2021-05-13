package com.lut.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.math.BigDecimal;

@Configuration
@ConfigurationProperties(prefix = "constant")
@PropertySource("classpath:constant.properties")
@Data
public class Constant {

    /**
     * 住房公积金---个人
     */
    private BigDecimal perHouseRate;
    /**
     * 住房公积金---公司
     */
    private BigDecimal compHouseRate;
    /**
     * 失业保险-个人
     */
    private BigDecimal perFireRate;
    /**
     * 失业保险-公司
     */
    private BigDecimal compFireRate;
    /**
     * 生育保险-公司
     */
    private BigDecimal compBirthRate;
    /**
     * 医疗保险-个人
     */
    private BigDecimal perMedicalRate;
    /**
     * 医疗保险-公司
     */
    private BigDecimal compMedicalRate;
    /**
     * 工伤保险-公司
     */
    private BigDecimal compHurtRate;
    /**
     * 养老保险-个人
     */
    private BigDecimal perOldRate;
    /**
     * 养老保险-公司
     */
    private BigDecimal compOldRate;

}
