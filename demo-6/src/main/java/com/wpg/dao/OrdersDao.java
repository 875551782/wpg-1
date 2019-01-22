package com.wpg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wpg.pojo.Hardware_Group;
import com.wpg.pojo.Order_Hardware;
import com.wpg.pojo.Orders;

public interface OrdersDao {

	/**
	 * 获取所有的orders
	 * @return
	 */
	public List<Orders> getAllOrders();
	/**
	 * 	通过用户Id获取订单
	 * 
	 * @return
	 */
	public List<Orders> getOrdersByUserId(int userId);
	/**
	  *  通过订单获取硬件id
	 * @param ordersId
	 * @return
	 */
	public List<Hardware_Group> getOrder_HardwaresByOrdersId(int ordersId);
	
	public int deleteOrder_Hardware(int oId);
	
	public int deleteOrders(int oId);
	
	public List<Orders> getAllOrdersByUserId(int userId);
	
	
	public int insertOrders(@Param("order")Orders order);
	
	public int insertOrder_Hardware(@Param("ids")int[] ids,@Param("order")Orders order);
	
	public int deleteOrder_HardwareById(int ordersId);
}
