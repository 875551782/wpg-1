package com.wpg.dao;

import java.util.List;

import com.wpg.pojo.Hardware;
import com.wpg.pojo.Hardware_Group;

public interface HardwareDao {

	//查询所有物料
	public List<Hardware> selAllHardware();
	
	//按功能查询物料
	public List<Hardware> selHardwareByModule(String module);
	
	//增加类
	public void addHardware_Group(Hardware_Group group);
	
	//增加物料
	public void addHardWare(Hardware hardware);
	
	//修改物料信息
	public void updateHardWare(Hardware hardware);
}
