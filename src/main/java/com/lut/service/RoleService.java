package com.lut.service;

import com.lut.entity.RoleEntity;

import java.util.List;

public interface RoleService {
    public List<RoleEntity> queryAllRoles();

    public int insertRole(RoleEntity roleEntity);

}
