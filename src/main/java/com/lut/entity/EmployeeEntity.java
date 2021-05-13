package com.lut.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gcy
 * @email gcy@gmail.com
 * @date 2021-04-26 10:31:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    /**
     * 员工编号
     */
    private Integer empId;
    /**
     * 员工用户名 登录使用
     */
    private String username;
    /**
     * salt
     */
    private String pwdSalt;
    /**
     * pwd_hash
     */
    private String pwdHash;
    /**
     * 部门ID
     */
    private Integer deptId;
    /**
     * 岗位编号
     */
    private Integer postId;
    /**
     * 员工姓名
     */
    private String empName;
    /**
     * 性别
     */
    private String empSex;
    /**
     * 邮箱
     */
    private String empEmail;
    /**
     * 头像地址
     */
    private String empAvatar;
    /**
     * 手机号码
     */
    private String empTelephone;
    /**
     * 住址
     */
    private String empAddress;
    /**
     * 个人简介
     */
    private String empIntroduction;
    /**
     * 身份证号
     */
    private String empIdnumber;
    /**
     * 银行卡号
     */
    private String empBank;
    /**
     * 入职时间
     */
    private String empHiredate;

}
