package com.wpg.dao;

import java.util.List;

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
	
	List<Order_Hardware> getOrder_HardwaresByWId(int wId);
}
