package com.lut.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Configuration  Component 都行
@Component
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

    /**
     * 个税起征点
     */
    private BigDecimal threshold;
    /**
     * 个税缴纳等级 应纳税所得额 预扣率 速算扣除数
     */
    private BigDecimal level1TaxableIncome;
    private BigDecimal level2TaxableIncome;
    private BigDecimal level3TaxableIncome;
    private BigDecimal level4TaxableIncome;
    private BigDecimal level5TaxableIncome;
    private BigDecimal level6TaxableIncome;
//    private BigDecimal LEVEL7;

    /**
     * 预扣率（%）
     */
    private BigDecimal level1Rate;
    private BigDecimal level2Rate;
    private BigDecimal level3Rate;
    private BigDecimal level4Rate;
    private BigDecimal level5Rate;
    private BigDecimal level6Rate;
    private BigDecimal level7Rate;

    /**
     * 速算扣除数
     */
    private BigDecimal level1QuickNum;
    private BigDecimal level2QuickNum;
    private BigDecimal level3QuickNum;
    private BigDecimal level4QuickNum;
    private BigDecimal level5QuickNum;
    private BigDecimal level6QuickNum;
    private BigDecimal level7QuickNum;

    /**
     * 基本工资
     */
    private BigDecimal baseSalary;

}
