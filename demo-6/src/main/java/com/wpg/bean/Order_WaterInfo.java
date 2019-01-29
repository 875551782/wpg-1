package com.wpg.bean;

import java.util.HashMap;
import java.util.List;

import com.wpg.pojo.Order_Hardware;
import com.wpg.pojo.Pic;
import com.wpg.pojo.Water_Division;

public class Order_WaterInfo {

	//订单的详细信息
	//private HashMap<Integer,Order_Hardware> order_HardwareMap;
	private List<Order_Hardware> order_Hardwares;
	

	//水司的详细信息
	//private Water_Division water_Division;
	private Integer wId;
	private String wName;
	private String region;
	private Integer num;
	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}


	//负责人
	private Pic pic;
	public Integer getwId() {
		return wId;
	}

	public void setwId(Integer wId) {
		this.wId = wId;
	}

	public String getwName() {
		return wName;
	}

	public void setwName(String wName) {
		this.wName = wName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	public List<Order_Hardware> getOrder_Hardwares() {
		return order_Hardwares;
	}

	public void setOrder_Hardwares(List<Order_Hardware> order_Hardwares) {
		this.order_Hardwares = order_Hardwares;
	}
	public Pic getPic() {
		return pic;
	}
	

	public void setPic(Pic pic) {
		this.pic = pic;
	}
	
	
}
