package com.wpg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpg.dao.OrdersDao;
import com.wpg.pojo.Orders;

@Service
public class OrdersService {

	@Autowired
	private OrdersDao ordersDao;
	
	public List<Orders> getAllOrders() {
		return ordersDao.getAllOrders();		
	}
	@Transactional
	public int deleteOrder_Hardware(int oId) {
		ordersDao.deleteOrders(oId);
		return ordersDao.deleteOrder_Hardware(oId);
	}
}
