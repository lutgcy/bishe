package com.lut.service.impl;

import com.lut.entity.RoleEntity;
import com.lut.mapper.RoleMapper;
import com.lut.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleEntity> queryAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public int insertRole(RoleEntity roleEntity) {
        return roleMapper.insertRole(roleEntity);
    }
}
