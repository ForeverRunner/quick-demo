package com.xxd.security.service;

import com.xxd.security.dao.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getByUserName(String username);
}
