package com.wpg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpg.bean.Order_WaterInfo;
import com.wpg.dao.Water_DivisionDao;
import com.wpg.pojo.Order_Hardware;
import com.wpg.pojo.Order_Water;
import com.wpg.pojo.Pic;
import com.wpg.pojo.Water_Division;
@Service
public class Water_DivisionService {
	
	@Autowired
	private Water_DivisionDao water_DivisionDao;

	
	public List<Water_Division> getWater_DivisionByRegion(String rName){
		return water_DivisionDao.getWater_DivisionByRegion(rName);
	}
	
	public List<Order_Water> getOrder_WaterByWId(int id){
		return water_DivisionDao.getOrder_WaterByWId(id);
	}
	
	public Pic getPicByWid(int id) {
		return water_DivisionDao.getPicByWid(id);
	}
	
	public List<Order_WaterInfo> getOrder_WaterInfos(String rName){
		return water_DivisionDao.getOrder_WaterInfos(rName);
	}
	
	public int updateOrder_Water(int oId,int wId,int num) {
		return water_DivisionDao.updateOrder_Water(oId, wId, num);
	}
}
