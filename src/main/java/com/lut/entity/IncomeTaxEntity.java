package com.lut.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author gcy
 * @email gcy@gmail.com
 * @date 2021-05-12 20:11:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeTaxEntity {
    /**
     * 个人所得税ID
     */
    private Integer taxId;
    /**
     * 员工编号
     */
    private Integer empId;
    /**
     * 年份
     */
    private Integer taxYear;
    /**
     * 月份
     */
    private Integer taxMonth;
    /**
     * 应纳税所得额
     */
    private BigDecimal taxableIncome;
    /**
     * 当月个税
     */
    private BigDecimal taxMoney;
}
