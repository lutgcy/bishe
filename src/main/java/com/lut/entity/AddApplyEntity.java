package com.lut.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author gcy
 * @email gcy@lut.com
 * @date 2021-05-07 21:00:45
 */
@Data
public class AddApplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private Integer empId;
	/**
	 * 申请类型 6种
	 */
	private String applyType;
	/**
	 * 金额
	 */
	private BigDecimal applyMoney;
	/**
	 * 申请日期
	 */
	private Date applyDate;
	/**
	 * 0 申请中 1 成功 -1失败
	 */
	private Integer applyState;
	/**
	 * 0 申请中 1 成功 -1失败
	 */
	private String applyFile;
}
