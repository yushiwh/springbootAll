package com.jzt.ys.security;

import com.jzt.ys.dao.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jzt.ys.domain.SysUser;

public class CustomUserService implements UserDetailsService { //1自定义需实现UserDetailsService接口
    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) { //2重写loadUserByUsername方法获得用户

        SysUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return user; //3当前用户实现了UserDetails接口，可直接返回给Spring Security使用
    }

}
