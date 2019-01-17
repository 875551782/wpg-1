package com.wpg.dao;

import java.util.List;

import com.wpg.pojo.Users;

public interface UsersDao {

	List<Users> selUser(String username);	

}
