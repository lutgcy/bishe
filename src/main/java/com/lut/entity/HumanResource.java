package com.lut.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author gcy
 * @email gcy@gmail.com
 * @date 2021-04-27 14:52:48
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HumanResource {

    /**
     *
     */
    private String username;
    /**
     *
     */
    private String pwdSalt;
    /**
     *
     */
    private String pwdHash;
    /**
     * 姓名
     */
    @NotBlank(message = "hrName不能为空")
    private String hrName;
    /**
     * 性别
     */
    private Integer hrSex;
    /**
     * 头像地址
     */
    private String hrAvatar;
    /**
     * 电话
     */
    private String hrTelephone;
    /**
     * 邮箱
     */
    private String hrEmail;
    /**
     * 住址
     */
    private String hrAddress;
    /**
     * 个人简介
     */
    private String hrIntroduction;

}
