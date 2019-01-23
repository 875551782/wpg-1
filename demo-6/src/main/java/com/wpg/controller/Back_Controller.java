package com.wpg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpg.pojo.Hardware;
import com.wpg.pojo.Hardware_Group;
import com.wpg.service.HardwareService;

@Controller
public class Back_Controller {
	
	@Autowired
	private HardwareService hardwareService;
	
	@RequestMapping("init.do")
	public String init() {
		return "manager/index";
	}

	//通过功能查询物料信息
	@ResponseBody
	@RequestMapping("admin_viewHardware.do")
	public List<Hardware> selHardware(ModelMap map, String module) {
		List<Hardware> hardwares = hardwareService.selHardwareByModule(module);
		map.addAttribute("hardwareList", hardwares);
		return hardwares;
	}
	
	//通过功能查询物料全部信息
	@ResponseBody
	@RequestMapping("admin_viewHardwareAll.do")
	public List<Hardware_Group> selAllModuleByModule(String module){
		List<Hardware_Group> mGroups = hardwareService.selAllModuleByModule(module);
		return mGroups;
	}
	
	//增加物料数据
	@RequestMapping("admin_insertHardware.do")
	public String addHardware(Hardware hardware) {
		hardwareService.addHardWare(hardware);
		return "";
	}
	
	//增加一个新类
	@RequestMapping("admin_addHardware_Group.do")
	public String addHardware_Group(Hardware group,HttpServletRequest req,String flag,String mark) {
		if(flag.equals("1")) {
			int i=hardwareService.selMarkByModule(group.getModule());
			group.setMark(i+1);
			group.setdFlag(1);
			hardwareService.addHardWare(group);
			String states=req.getParameter("states");
			int s;
			if(states.equals("是")) {
				s=1;
			}else {
				s=0;
			}
			String desct=req.getParameter("desct");
			Hardware_Group g=new Hardware_Group();
			g.setMark(i+1);
			g.setDesct(desct);
			g.setStates(s);
			hardwareService.addHardware_Group(g);
		}else {
			int mark1=Integer.parseInt(mark);
			group.setMark(mark1);
			group.setdFlag(1);
			hardwareService.addHardWare(group);
		}
		return "manager/index";
	}
	
	//修改物料信息
	@ResponseBody
	@RequestMapping("admin_updateHardware.do")
	public String updateHardWare(String id,String name,String type,String unit,String num,String brand,String price,String desct,String states,String mark,String module) {
		Hardware h=new Hardware();
		h.setName(name);
		h.setType(type);
		h.setUnit(unit);
		h.setBrand(brand);
		h.setdFlag(1);
		int m=Integer.parseInt(mark);
		h.setMark(m);
		h.setModule(module);
		double p;
		if(price==null||price.equals("null")) {
			p=0;
		}else {
			p=Double.parseDouble(price);
		}
		
		h.setPrice(p);
		int n=Integer.parseInt(num);
		h.setNum(n);
		int id1=Integer.parseInt(id);
		h.setId(id1);
		hardwareService.updateHardWare(h);
		Hardware_Group hg=new Hardware_Group();
		int s;
		hg.setDesct(desct);
		if(states.equals("是")) {
			s=1;
		}else {
			s=0;
		}
		hg.setStates(s);
		hg.setMark(m);
		hardwareService.updateHardware_Group(hg);
		return module;
	}
	
	//修改物料类信息
	@RequestMapping("admin_updateHardware_Group.do")
	public String updateHardware_Group(Hardware_Group group) {
		hardwareService.updateHardware_Group(group);
		return "";
	}
	
	//假删除物料信息
	@ResponseBody
	@RequestMapping("admin_delHardware.do")
	public String fdelHardware(String module,int id) {
		hardwareService.fdelHardware(id);
		return module;
	}
}
