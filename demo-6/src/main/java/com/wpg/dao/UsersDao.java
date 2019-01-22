package com.wpg.dao;

import java.util.List;

import com.wpg.pojo.Users;

public interface UsersDao {

	// 获取用户表中所有用户名
	public List<String> getAllUsers();

	// 根据用户名查询用户信息
	Users selUser(String username);

}
