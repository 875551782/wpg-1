package com.wpg.dao;

import java.util.List;

import com.wpg.pojo.Users;

public interface UsersDao {

	// 获取用户表中所有用户名
	public List<String> getAllUsers();

	// 根据用户名查询用户信息
	Users selUser(String username);

	public List<Users> selAllUsersByRname(String rname);
	
	//删除大区管理
	public void delManagerById(int id);
	
	//添加大区管理
	public void addManager(Users u);
 }
