package com.wpg.dao;

import java.util.List;

import com.wpg.pojo.Users;

public interface UsersDao {

	public int get();

	
	List<Users> selUser(String username);

}
