package com.xxd.security.dao.mapper;

import com.xxd.security.dao.entity.RolePermission;
import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    List<RolePermission> selectAll();

    int updateByPrimaryKey(RolePermission record);
}