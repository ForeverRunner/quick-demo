package com.xxd.security.dao.mapper;

import com.xxd.security.dao.entity.Permission;
import java.util.List;

public interface PermissionMapper extends PermissionMapperExt{
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);
}