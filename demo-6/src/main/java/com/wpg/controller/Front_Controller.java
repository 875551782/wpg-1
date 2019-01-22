package com.wpg.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpg.pojo.Hardware;
import com.wpg.pojo.Hardware_Group;
import com.wpg.pojo.Order_Hardware;
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
	public List<Order_Hardware> showDetailOrders(int oId){
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
	
	@RequestMapping("user_orderUpload.do")
	@ResponseBody
	public void upload(int oId,HttpServletResponse response) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		List<Order_Hardware> list = ordersService.getOrder_HardwaresByOrdersId(oId);
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("功能");
		row.createCell(1).setCellValue("物料名称");
		row.createCell(2).setCellValue("规格型号");
		row.createCell(3).setCellValue("单位");
		row.createCell(4).setCellValue("数量");
		row.createCell(5).setCellValue("品牌");
		row.createCell(6).setCellValue("说明");
		row.createCell(7).setCellValue("价格");
		
		
		double sum = 0;
		for (int i = 1; i <= list.size(); i++) {
			HSSFRow rows = sheet.createRow(i);
			Hardware_Group hardware_Group = list.get(i-1).getHardware_Group();
			Hardware hardware = hardware_Group.getHardwareList().get(0);
			rows.createCell(0).setCellValue(hardware.getModule());
			rows.createCell(1).setCellValue(hardware.getName());
			rows.createCell(2).setCellValue(hardware.getType());
			rows.createCell(3).setCellValue(hardware.getUnit());
			rows.createCell(4).setCellValue(hardware.getNum());
			rows.createCell(5).setCellValue(hardware.getBrand());
			rows.createCell(6).setCellValue(hardware_Group.getDesct());
			rows.createCell(7).setCellValue(hardware.getPrice());
			sum+=hardware.getPrice();
		}
		HSSFRow rows = sheet.createRow(list.size());
		rows.createCell(6).setCellValue("总价");
		rows.createCell(7).setCellValue(sum);
		//File file = new File("E:/test.xls");
		//FileOutputStream xlsStream = new FileOutputStream(file);
		

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=plan.xls");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
		
	}
	
}
