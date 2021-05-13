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
public class AdditionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 专项附加扣除ID
	 */
	private Integer additionId;
	/**
	 * 员工ID
	 */
	private Integer empId;
	/**
	 * 年份
	 */
	private Integer additionYear;
	/**
	 * 月份
	 */
	private Integer additionMonth;
	/**
	 * 子女教育
	 */
	private BigDecimal childEdu;
	/**
	 * 大病医疗
	 */
	private BigDecimal bigSick;
	/**
	 * 继续教育
	 */
	private BigDecimal continueEdu;
	/**
	 * 赡养老人
	 */
	private BigDecimal oldMan;
	/**
	 * 住房贷款利息
	 */
	private BigDecimal credit;
	/**
	 * 租房租金
	 */
	private BigDecimal rent;

}
