package com.wpg.bean;

import java.util.HashMap;
import java.util.List;

import com.wpg.pojo.Order_Hardware;
import com.wpg.pojo.Pic;
import com.wpg.pojo.Water_Division;

public class Order_WaterInfo {

	//订单的详细信息
	//private HashMap<Integer,Order_Hardware> order_HardwareMap;
	private List<OrderInfo> orderInfo;
	

	//水司的详细信息
	//private Water_Division water_Division;
	private Integer wId;
	private String wName;
	private String region;

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
	public List<OrderInfo> getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(List<OrderInfo> orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Pic getPic() {
		return pic;
	}
	

	public void setPic(Pic pic) {
		this.pic = pic;
	}
	
	
}
