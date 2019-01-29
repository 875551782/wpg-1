package com.wpg.bean;

import java.util.List;

import com.wpg.pojo.Order_Hardware;

public class OrderInfo {

	private Integer oId;
	private Integer num;
	private List<Order_Hardware> order_Hardwares;
	public Integer getoId() {
		return oId;
	}
	public void setoId(Integer oId) {
		this.oId = oId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public List<Order_Hardware> getOrder_Hardwares() {
		return order_Hardwares;
	}
	public void setOrder_Hardwares(List<Order_Hardware> order_Hardwares) {
		this.order_Hardwares = order_Hardwares;
	}
	
	
}
