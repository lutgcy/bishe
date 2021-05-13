package com.lut.mapper;

import com.lut.entity.RoleEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {

    @Select("SELECT * FROM sys_role;")
    public List<RoleEntity> getAllRoles();

    @Insert("INSERT INTO sys_role(role_name, role_key, role_sort, `status`, data_scope, create_by, create_time, remark) VALUES(#{roleName}, #{roleKey}, #{roleSort}, 0, #{dataScope}, 'admin', SYSDATE(), #{remark});")
    public int insertRole(RoleEntity roleEntity);


}
