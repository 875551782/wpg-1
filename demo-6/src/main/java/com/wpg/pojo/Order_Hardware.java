package com.wpg.pojo;

public class Order_Hardware {
	
	private Integer order_Id;
	private Integer hardware_id;
	private Hardware_Group hardware_Group;
	public Hardware_Group getHardware_Group() {
		return hardware_Group;
	}
	public void setHardware_Group(Hardware_Group hardware_Group) {
		this.hardware_Group = hardware_Group;
	}
	public Integer getOrder_Id() {
		return order_Id;
	}
	public void setOrder_Id(Integer order_Id) {
		this.order_Id = order_Id;
	}
	public Integer getHardware_id() {
		return hardware_id;
	}
	public void setHardware_id(Integer hardware_id) {
		this.hardware_id = hardware_id;
	}

	
}
