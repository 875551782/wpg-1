package com.wpg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpg.dao.HardwareDao;
import com.wpg.pojo.Hardware;
import com.wpg.pojo.Hardware_Group;

@Service
public class HardwareService {

	@Autowired
	private HardwareDao hardwareDao;
	
	//查询所有物料
	public List<Hardware> selAllHardware(){
		return hardwareDao.selAllHardware();
	}
	
	//按功能查询物料
	public List<Hardware> selHardwareByModule(String module){
		return hardwareDao.selHardwareByModule(module);
	}
	
	//增加物料类
	public void addHardware_Group(Hardware_Group group) {
		hardwareDao.addHardware_Group(group);
	}
	
	//增加新物料
	public void addHardWare(Hardware hardware) {
		hardwareDao.addHardWare(hardware);
	}
	
	//更新物料信息
	public void updateHardWare(Hardware hardware) {
		hardwareDao.updateHardWare(hardware);
	}

}
