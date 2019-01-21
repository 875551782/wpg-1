package com.wpg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpg.dao.OrdersDao;
import com.wpg.pojo.Hardware_Group;
import com.wpg.pojo.Order_Hardware;
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
	
	public List<Orders> getAllOrdersByUserId(int userId){
		return ordersDao.getAllOrdersByUserId(userId);
	}
	
	
	public List<Hardware_Group> getOrder_HardwaresByOrdersId(int ordersId){
		return ordersDao.getOrder_HardwaresByOrdersId(ordersId);
	}
	@Transactional
	public int insertOrders(int[] ids,int userId) {
		int order_Id = ordersDao.insertOrders(userId);
		return ordersDao.insertOrder_Hardware(ids, order_Id);
	}
}
