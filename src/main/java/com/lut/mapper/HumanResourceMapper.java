package com.lut.mapper;

import com.lut.entity.HumanResource;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface HumanResourceMapper {

    @Select("SELECT * FROM hr WHERE username = #{username}")
    HumanResource getHrInfoByUsername(@Param("username") String username);

    @Select("SELECT username, pwd_salt, pwd_hash FROM hr WHERE username = #{username}")
    HumanResource getHumanResourceByUserName(@Param("username") String username);

    @Select("SELECT * FROM hr")
    List<HumanResource> getAllHR();

    @Insert("INSERT INTO hr (username, pwd_salt, pwd_hash, hr_name, hr_sex, hr_avatar, hr_telephone, hr_email, hr_address, hr_introduction) VALUES (#{username}, #{pwdSalt}, #{pwdHash}, #{hrName}, #{hrSex}, #{hrAvatar}, #{hrTelephone}, #{hrEmail}, #{hrAddress}, #{hrIntroduction})")
    int insertHr(HumanResource hr);

    @Delete("DELETE FROM hr WHERE username = #{username}")
    int deleteHr(String username);

    @Update("UPDATE hr SET hr_name=#{hrName}, hr_sex=#{hrSex}, hr_telephone=#{hrTelephone}, hr_email=#{hrEmail}, hr_address=#{hrAddress}, hr_introduction=#{hrIntroduction} WHERE username=#{username}")
    int updateHr(HumanResource hr);

    @Update("UPDATE hr SET pwd_hash = #{newPasswordSHA256} WHERE username=#{username}")
    int updatePassword(@Param("newPasswordSHA256")String newPasswordSHA256, @Param("username") String username);

    @Update("UPDATE hr SET pwd_hash = #{defaultPassword} WHERE username=#{username}")
    int resetPwd(@Param("defaultPassword") String defaultPassword, @Param("username") String username);
}
