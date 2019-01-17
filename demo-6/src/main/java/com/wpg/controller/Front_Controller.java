package com.wpg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpg.pojo.Orders;
import com.wpg.service.OrdersService;

@Controller
public class Front_Controller {
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("user_showOrders.do")
	@ResponseBody
	public List<Orders> show(){
		return ordersService.getAllOrders();
	}
	
	
}
