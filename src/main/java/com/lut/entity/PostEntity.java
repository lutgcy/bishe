package com.lut.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 岗位信息表
 * 
 * @author gcy
 * @email gcy@lut.com
 * @date 2021-04-29 20:25:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 岗位ID
	 */
	private Long postId;
	/**
	 * 岗位编码
	 */
	private String deptId;
	/**
	 * 岗位名称
	 */
	private String postName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 岗位工资
	 */
	private Integer postSalary;

}
