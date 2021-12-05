package com.xxd.security.dao.mapper;

import com.xxd.security.dao.entity.User;
import java.util.List;

public interface UserMapper extends UserMapperExt{
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}