package com.xxd.security.service.impl;

import com.xxd.security.dao.entity.Permission;
import com.xxd.security.dao.entity.User;
import com.xxd.security.dao.mapper.PermissionMapper;
import com.xxd.security.dao.mapper.UserMapper;
import com.xxd.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    public User getByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUserName(username);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (user != null) {
            List<Permission> permissions = permissionMapper.selectByUserId(user.getId());
            permissions.forEach(permission -> {
                if (permission != null) {

                }
            });
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("用户名不存在");
        }
    }
}
