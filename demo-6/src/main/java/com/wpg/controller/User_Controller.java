package com.wpg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpg.pojo.Users;
import com.wpg.service.UsersService;

@Controller
public class User_Controller {

	@Autowired
	private UsersService usersService;

	@RequestMapping("login")
	@ResponseBody
	public String login(Users users) {
		String username = users.getUserName();
		String password = users.getPassword();
		System.out.println("username:" + username + ",password:" + password);
		List<String> usernames = usersService.getAllUsers();
		Users user = usersService.selUser(username);
		// 对用户进行判断
		if (usernames.contains(username)) {
			if (user.getPassword().equals(password)) {

				if (1 == user.getRole()) {
					//进入后台
					return "admin";
				
				} else {
					//进入用户页面
					return "user";
				}
			} else {
				// 密码错误
//				System.out.println("密码不正确");

				return "false";
			}
		} else {
			// 用户名不存在
//			System.out.println("用户名不存在");

			return "false";
		}
	}
}
