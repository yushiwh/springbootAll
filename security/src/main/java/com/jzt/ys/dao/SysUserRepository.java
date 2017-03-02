package com.jzt.ys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jzt.ys.domain.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);//根据用户名查询用户的方法

}
