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
	
	public List<Hardware> selHardwareByModule(String module){
		return hardwareDao.selHardwareByModule(module);
	}
	
	public void addHardware_Group(Hardware_Group group) {
		hardwareDao.addHardware_Group(group);
	}
	
	public void addHardWare(Hardware hardware) {
		hardwareDao.addHardWare(hardware);
	}
	
	public void updateHardWare(Hardware hardware) {
		hardwareDao.updateHardWare(hardware);
	}
}
