package com.lut.entity;


import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * 
 * @author gcy
 * @email gcy@lut.com
 * @date 2021-05-04 22:36:28
 */
@Data
public class SalaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 工资表主键
	 */
	private Integer salaryId;
	/**
	 * 员工id
	 */
	private Integer empId;
	/**
	 * 年份
	 */
	private Integer salaryYear;
	/**
	 * 月份
	 */
	private Integer salaryMonth;
	/**
	 * 岗位工资
	 */
	private BigDecimal postSalary;
	/**
	 * 基本工资
	 */
	private BigDecimal baseSalary;
	/**
	 * 实发工资
	 */
	private BigDecimal realSalary;
}
