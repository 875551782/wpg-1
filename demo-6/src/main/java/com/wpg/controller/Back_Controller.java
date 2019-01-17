package com.wpg.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wpg.pojo.Hardware;
import com.wpg.pojo.Hardware_Group;
import com.wpg.service.HardwareService;

@Controller
public class Back_Controller {
	
	private HardwareService hardwareService;
	
	
	//通过功能查询物料信息
	@RequestMapping("admin_viewHardware.do")
	public String selHardware(ModelMap map, String module) {
		List<Hardware> hardwares = hardwareService.selHardwareByModule(module);
		map.addAttribute("hardwareList", hardwares);
		return "";
	}
	
	//增加物料数据
	@RequestMapping("admin_insertHardware.do")
	public String addHardware(ModelMap map, Hardware hardware) {
		hardwareService.addHardWare(hardware);
		return "";
	}
	
	//增加一个新类
	public String addHardware_Group(Hardware_Group group) {
		hardwareService.addHardware_Group(group);
		return "";
	}
	
	//修改物料信息
	public String updateHardWare(Hardware hardware) {
		hardwareService.updateHardWare(hardware);
		
		return "";
	}
	
}
