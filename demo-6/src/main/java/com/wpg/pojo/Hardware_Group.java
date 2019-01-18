package com.wpg.pojo;

import java.util.List;

public class Hardware_Group {

	private Integer mark;
	private String desct;
	private Integer states;
	private List<Hardware> hardwareList;
	
	
	public List<Hardware> getHardwareList() {
		return hardwareList;
	}
	
	public void setHardwareList(List<Hardware> hardwareList) {
		this.hardwareList = hardwareList;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public String getDesct() {
		return desct;
	}
	public void setDesct(String desct) {
		this.desct = desct;
	}
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	
	
	
}
