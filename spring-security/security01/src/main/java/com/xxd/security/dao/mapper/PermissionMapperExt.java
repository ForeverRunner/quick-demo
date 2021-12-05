package com.xxd.security.dao.mapper;

import com.xxd.security.dao.entity.Permission;

import java.util.List;

public interface PermissionMapperExt {
    List<Permission> selectByUserId(Long userId);
}