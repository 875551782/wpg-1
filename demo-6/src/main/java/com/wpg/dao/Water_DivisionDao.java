package com.wpg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wpg.bean.OrderInfo;
import com.wpg.bean.Order_WaterInfo;
import com.wpg.pojo.Order_Hardware;
import com.wpg.pojo.Order_Water;
import com.wpg.pojo.Pic;
import com.wpg.pojo.Water_Division;

public interface Water_DivisionDao {
	
	List<Water_Division> getWater_DivisionByRegion(String rName);
	  
	List<Order_Water> getOrder_WaterByWId(int id);
	
	Pic getPicByWid(int id);
	
	List<Order_WaterInfo> getOrder_WaterInfos(String rName);
	
	List<OrderInfo> getOrder_HardwaresByWId(int wId);
	public void delWater_DivisionById(int id);
	
	public void addWater_Division(Water_Division w);
	
	int updateOrder_Water(int oId,int wId,int num);
	
	int updateOrder_HardwareMultiple(@Param("order_Hardwares")List<Order_Hardware> order_Hardwares);
	
	void delPicById(int id);
	
	int selectWidByname(String rname);
	
	void addPicByWid(int wid);

	int updatePic(int wId, String name, String tel);
}
