package com.wpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpg.dao.UsersDao;

@Service
public class UsersService {

	@Autowired
	private UsersDao usersDao;
}