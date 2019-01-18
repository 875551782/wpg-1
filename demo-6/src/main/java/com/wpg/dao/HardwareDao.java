package com.wpg.dao;

import java.util.List;

import com.wpg.pojo.Hardware;
import com.wpg.pojo.Hardware_Group;

public interface HardwareDao {

	//查询所有物料
	public List<Hardware> selAllHardware();
	
	//查询所有功能名
	public List<String> selModuleName();
	
	//按功能查询物料
	public List<Hardware> selHardwareByModule(String module);
	
	//增加类
	public void addHardware_Group(Hardware_Group group);
	
	//增加物料
	public void addHardWare(Hardware hardware);
	
	//修改物料信息
	public void updateHardWare(Hardware hardware);
	
	//修改物料类信息
	public void updateHardware_Group(Hardware_Group group);
	
	//假删除物料信息
	public void fdelHardware(int id);
}
