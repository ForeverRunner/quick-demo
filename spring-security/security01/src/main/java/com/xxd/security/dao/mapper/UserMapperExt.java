package com.xxd.security.dao.mapper;

import com.xxd.security.dao.entity.User;

public interface UserMapperExt {
    User selectByUserName(String username);
}
