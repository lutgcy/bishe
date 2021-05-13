package com.lut.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 负责人id
     */
    private Integer leaderId;
}
