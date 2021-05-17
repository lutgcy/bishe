package com.lut.mapper;

import com.lut.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {

    @Select("SELECT username, pwd_salt, pwd_hash FROM admin WHERE username = #{username}")
    public AdminEntity getAdminByUserName(@Param("username") String username);

    @Update("UPDATE admin SET pwd_hash = #{newPasswordSHA256} WHERE username=#{username}")
    int updatePassword(@Param("newPasswordSHA256")String newPasswordSHA256, @Param("username") String username);
}
