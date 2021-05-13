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
public class SpecialEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 附加扣除ID
	 */
	private Integer specialId;
	/**
	 * 员工编号
	 */
	private Integer empId;
	/**
	 * 年份
	 */
	private Integer spacialYear;
	/**
	 * 月份
	 */
	private Integer specialMonth;
	/**
	 * 住房公积金---个人
	 */
	private BigDecimal perHouse;
	/**
	 * 住房公积金---公司
	 */
	private BigDecimal compHouse;
	/**
	 * 失业保险-个人
	 */
	private BigDecimal perFire;
	/**
	 * 失业保险-公司
	 */
	private BigDecimal compFire;
	/**
	 * 生育保险-公司
	 */
	private BigDecimal compBirth;
	/**
	 * 医疗保险-个人
	 */
	private BigDecimal perMedical;
	/**
	 * 医疗保险-公司
	 */
	private BigDecimal compMedical;
	/**
	 * 工伤保险-公司
	 */
	private BigDecimal compHurt;
	/**
	 * 养老保险-个人
	 */
	private BigDecimal perOld;
	/**
	 * 养老保险-公司
	 */
	private BigDecimal compOld;

}
