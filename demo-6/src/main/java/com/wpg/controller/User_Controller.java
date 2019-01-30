package com.wpg.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpg.pojo.Users;
import com.wpg.pojo.Water_Division;
import com.wpg.service.UsersService;
import com.wpg.service.Water_DivisionService;

@Controller
public class User_Controller {

	@Autowired
	private UsersService usersService;

	@Autowired
	private Water_DivisionService waterService;
	
	public final static Logger logger = LoggerFactory.getLogger(User_Controller.class);
	@RequestMapping("login")
	@ResponseBody
	public String login(Users users,HttpSession session) {
		
		
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
					session.setAttribute("admin", user);
					return "admin";
				
				} else {
					//进入用户页面
					session.setAttribute("user", user);
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
	
	@ResponseBody
	@RequestMapping("selUsers.do")
	public List<Water_Division> selAllUsers(String region){
		List<Water_Division> list=waterService.getWater_DivisionByRegion(region);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("delWater_divison.do")
	public String delWater_divison(String id,String region,HttpSession session) {
		int id1=Integer.parseInt(id);
		waterService.delWater_DivisionById(id1);
		waterService.delPicById(id1);
		Users user=(Users) session.getAttribute("admin");
		logger.info(user.getUserName()+"删除了水司，水司id为"+id+"大区是"+region);
		return region;
	}
	
	@ResponseBody
	@RequestMapping("addwater_division.do")
	public String addWater_division(String rname,String region,HttpSession session) {
		Water_Division w=new Water_Division();
		w.setwName(rname);
		w.setRegion(region);
		waterService.addWater_Division(w);
		int wid = waterService.selectWidByname(rname);
		waterService.addPicByWid(wid);

		Users user=(Users) session.getAttribute("admin");
		logger.info(user.getUserName()+"添加了水司，大区是"+region);

		return "华东大区";
	}
	
	@ResponseBody
	@RequestMapping("selAllUsers.do")
	public List<Users> selAllUsersByRname(String rname){
		List<Users> list=usersService.selAllUsersByRname(rname);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("delManager.do")
	public String delManager(String id,String rname,HttpSession session){
		int id1=Integer.parseInt(id);
		usersService.delManagerById(id1);
		Users user=(Users) session.getAttribute("admin");
		logger.info(user.getUserName()+"删除了管理员，大区是"+rname+"管理员id是"+id);
		return rname;
	}
	
	@ResponseBody
	@RequestMapping("addmanager.do")
	public String addManager(String username,String password,String rname,HttpSession session) {
		Users u=new Users();
		u.setUserName(username);
		u.setPassword(password);
		u.setrName(rname);
		u.setRole(0);
		usersService.addManager(u);
		Users user=(Users) session.getAttribute("admin");
		logger.info(user.getUserName()+"添加了管理员，用户名是"+username+"密码是"+password);
		System.out.println(111);
		return "华东大区";
	}
}
