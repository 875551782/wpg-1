package com.wpg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wpg.pojo.Hardware;
import com.wpg.pojo.Hardware_Group;
import com.wpg.service.HardwareService;

@Controller
public class Back_Controller {
	
	@Autowired
	private HardwareService hardwareService;
	
	@RequestMapping("init")
	public String init() {
		return "index";
	}

	//通过功能查询物料信息
	@RequestMapping("admin_viewHardware.do")
	public String selHardware(ModelMap map, String module) {
		List<Hardware> hardwares = hardwareService.selHardwareByModule(module);
		map.addAttribute("hardwareList", hardwares);
		return "";
	}
	//增加物料数据
	@RequestMapping("admin_insertHardware.do")
	public String addHardware(Hardware hardware) {
		hardwareService.addHardWare(hardware);
		return "";
	}
	
	//增加一个新类
	@RequestMapping("admin_addHardware_Group.do")
	public String addHardware_Group(Hardware_Group group) {
		hardwareService.addHardware_Group(group);
		return "";
	}
	
	//修改物料信息
	@RequestMapping("admin_updateHardware.do")
	public String updateHardWare(Hardware hardware) {
		hardwareService.updateHardWare(hardware);
		return "";
	}
	
	//修改物料类信息
	@RequestMapping("admin_updateHardware_Group.do")
	public String updateHardware_Group(Hardware_Group group) {
		hardwareService.updateHardware_Group(group);
		return "";
	}
	
	//假删除物料信息
	@RequestMapping("admin_delHardware.do")
	public String fdelHardware(int id) {
		hardwareService.fdelHardware(id);
		return "";
	}
}
