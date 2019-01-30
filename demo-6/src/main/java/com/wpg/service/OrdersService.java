package com.wpg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpg.dao.OrdersDao;
import com.wpg.pojo.Hardware_Group;
import com.wpg.pojo.Order_Hardware;
import com.wpg.pojo.Order_Water;
import com.wpg.pojo.Orders;
import com.wpg.pojo.Users;

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
		return ordersDao.deleteOrder_HardwareById(oId);
	}
	
	public List<Orders> getAllOrdersByUserId(int userId){
		return ordersDao.getAllOrdersByUserId(userId);
	}
	
	
	public List<Order_Hardware> getOrder_HardwaresByOrdersId(int ordersId){
		return ordersDao.getOrder_HardwaresByOrdersId(ordersId);
	}
	
	@Transactional
	public int insertOrders(int wId,int num,List<Order_Hardware> order_Hardwares) {
		Order_Water order_Water = new Order_Water();
		order_Water.setwId(wId);
		order_Water.setNum(num);
		ordersDao.insertOrders(order_Water);
		//System.out.println(order.getOrder_Id());
		return ordersDao.insertOrder_Hardware(order_Hardwares, order_Water);
	}
	
	public int updateOrder_Hardware(int oId,List<Order_Hardware> order_Hardwares) {
		ordersDao.deleteOrder_HardwareById(oId);
		Order_Water order_Water = new Order_Water();
		order_Water.setoId(oId);
		return ordersDao.insertOrder_Hardware(order_Hardwares, order_Water);
		
	}
	
}
