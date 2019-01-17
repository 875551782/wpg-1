package com.wpg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpg.dao.OrdersDao;
import com.wpg.pojo.Orders;

@Service
public class OrdersService {

	@Autowired
	private OrdersDao ordersDao;
	
	public List<Orders> getAllOrders() {
		return ordersDao.getAllOrders();		
	}
}
