package com.lut.mapper;

import com.lut.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {

    @Select("SELECT username, pwd_salt, pwd_hash FROM admin WHERE username = #{username}")
    public AdminEntity getAdminByUserName(@Param("username") String username);

}
