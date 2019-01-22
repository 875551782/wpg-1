package com.wpg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpg.pojo.Hardware;
import com.wpg.pojo.Hardware_Group;
import com.wpg.pojo.Orders;
import com.wpg.pojo.Users;
import com.wpg.service.HardwareService;
import com.wpg.service.OrdersService;

@Controller
public class Front_Controller {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private HardwareService hardwareService;
	
	@RequestMapping("user_showOrders.do")
	@ResponseBody
	public List<Orders> show(){
		return ordersService.getAllOrders();
	}
	
	@RequestMapping("user_hardwares.do")
	@ResponseBody
	public List<Hardware> showHardwares(){	
		return hardwareService.selAllHardware();
	}
	
	@RequestMapping("user_showModule.do")
	@ResponseBody
	public Map<Integer, String> showModule(){
		HashMap<Integer, String> moduleMap = new HashMap<>();
		List<String> moduleList = hardwareService.selModuleName();
		for(int i = 1;i<=moduleList.size();i++) {
			moduleMap.put(i, moduleList.get(i-1));
		}
		return moduleMap;
	}
	@RequestMapping("user_forward.do")
	public String showPage(String name) {
		return "user/"+name;
	}
	@RequestMapping("user_group.do")
	@ResponseBody
	public HashMap<String, List<Hardware_Group>> showGroup(){
		HashMap<String, List<Hardware_Group>> map = new HashMap<>();
		List<String> moduleList = hardwareService.selModuleName();
		for(int i=0;i<moduleList.size();i++) {
			
			map.put(moduleList.get(i), hardwareService.selAllModuleByModule(moduleList.get(i)));
		}
		return map;
	}
	
	@RequestMapping("user_deleteOrder.do")
	@ResponseBody
	public int deleteOrder(int oId) {
		
		int i = ordersService.deleteOrder_Hardware(oId);
		if(i>0) {
			return 1;
		}
		return 0;
		
	}
	
	@RequestMapping("user_showOrder.do")
	@ResponseBody
	public List<Orders> showOrders(HttpSession session) {
		Users user = new Users();
		user.setId(1);
		
		if(session.getAttribute("user") != null) {
			user = (Users) session.getAttribute("user");
		}
		
		return ordersService.getAllOrdersByUserId(user.getId());
	}
	
	@RequestMapping("user_showDetailOrder.do")
	@ResponseBody
	public List<Hardware_Group> showDetailOrders(int oId){
		return ordersService.getOrder_HardwaresByOrdersId(oId);
		
	}
	/**
	 * @param ids
	 * @param session
	 * @return
	 */
	@RequestMapping("user_submitOrder.do")
	@ResponseBody
	public int submitOrder(@RequestParam(value="ids[]")int[] ids,HttpSession session) {
		Users user = new Users();
		user.setId(1);
		if(session.getAttribute("user") != null) {
			user = (Users) session.getAttribute("user");
		}
		
		int i = ordersService.insertOrders(ids,user.getId());
		if(i>0) {
			return 1;
		}
		else{
			return 0;
		}
		
	}
	@RequestMapping("user_changeOrder.do")
	@ResponseBody
	public int updateOrder_Hardware(HttpSession session,int oId,@RequestParam(value="ids[]")int[] ids) {
		Users user = new Users();
		user.setId(1);
		if(session.getAttribute("user") != null) {
			user = (Users) session.getAttribute("user");
		}
		int i = ordersService.updateOrder_Hardware(oId,ids);
		
		if(i>0) {
			return 1;
		}
		else{
			return 0;
		}
	}
	
}
