package com.wpg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpg.dao.UsersDao;
import com.wpg.pojo.Users;

@Service
public class UsersService {

	@Autowired
	private UsersDao usersDao;
	
	public List<String> getAllUsers(){
		return usersDao.getAllUsers();
	}
	
	public Users selUser(String username){
		return usersDao.selUser(username);
	}
}
