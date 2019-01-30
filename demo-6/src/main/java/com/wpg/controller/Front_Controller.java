package com.wpg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wpg.bean.OrderInfo;
import com.wpg.bean.Order_WaterInfo;
import com.wpg.pojo.Hardware;
import com.wpg.pojo.Hardware_Group;
import com.wpg.pojo.Order_Hardware;
import com.wpg.pojo.Order_Water;
import com.wpg.pojo.Orders;
import com.wpg.pojo.Users;
import com.wpg.pojo.Water_Division;
import com.wpg.service.HardwareService;
import com.wpg.service.OrdersService;
import com.wpg.service.Water_DivisionService;

@Controller
public class Front_Controller {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private HardwareService hardwareService;
	@Autowired
	private Water_DivisionService water_DivisionService;
	
	private static final Logger log = LoggerFactory.getLogger(Front_Controller.class);

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
	public ModelAndView showPage(String name) {
		
		return new ModelAndView("user/"+name);
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
	public int deleteOrder(int oId,HttpSession session) {
		Users user = (Users)session.getAttribute("user");
		int i = ordersService.deleteOrder_Hardware(oId);
		if(i>0) {
			String str = formatLog(user);
			String message = str+":delete"+"表:order_hardware";
			log.info(message);
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
	public int submitOrder(HttpSession session,@RequestParam(value="id_Multiple[]")String[] id_Multiple,int wId) {
		Users user = (Users)session.getAttribute("user");
		List<Order_Hardware> order_Hardwares = parseString(id_Multiple);
		int num = 1;
		int i = ordersService.insertOrders(wId, num, order_Hardwares);
		
		if(i>0) {
			String str = formatLog(user);
			String message = str+":insert"+"表:order_hardware和order_water";
			log.info(message);
			return 1;
		}
		else{
			return 0;
		}
		
	}
	@RequestMapping("user_changeOrder.do")
	@ResponseBody
	public int updateOrder_Hardware(HttpSession session,@RequestParam(value="id_Multiple[]")String[] id_Multiple,int oId) {
		Users user = (Users)session.getAttribute("user");
		List<Order_Hardware> order_Hardwares = parseString(id_Multiple);
		int i = ordersService.updateOrder_Hardware(oId,order_Hardwares);
		
		if(i>0) {
			String str = formatLog(user);
			String message = str+":update"+"表:order_hardware";
			log.info(message);
			return 1;
		}
		else{
			return 0;
		}
	}
	
	
	
	@ResponseBody
	@RequestMapping("user_showWater_Division.do")
	public List<Order_WaterInfo> showWater_Division(HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		String rName = user.getrName();
		List<Order_WaterInfo> order_WaterInfos = water_DivisionService.getOrder_WaterInfos(rName);
		return order_WaterInfos;
	}
	
	@RequestMapping("user_updateOrderNum.do")
	@ResponseBody
	public int updateOrder_Water(HttpSession session,int oId,int wId,int num) {
		Users user = (Users)session.getAttribute("user");
		int i = water_DivisionService.updateOrder_Water(oId, wId, num);
		if(i>0) {
			String str = formatLog(user);
			String message = str+":update"+"表:order_water";
			log.info(message);
			return 1;
		}else {
			return 0;
		}
	}
	
	/*public int changeOrder_HardwareNum(@RequestParam(value="id_Multiple[]")String[] id_Multiple,int oId) {
		List<Order_Hardware> order_Hardwares = parseString(id_Multiple);
		for(int i=0;i<order_Hardwares.size();i++) {
			order_Hardwares.get(i).setOrder_Id(oId);
		}
		int i = water_DivisionService.updateOrder_HardwareMultiple(order_Hardwares);
		if(i>0) {
			return 1;
		}
		else{
			return 0;
		}
	}*/
	@RequestMapping("user_updatePic.do")
	@ResponseBody
	public int updatePic(HttpSession session,int wId,String name,String tel) {
		Users user = (Users)session.getAttribute("user");
		int i = water_DivisionService.updatePic(wId,name,tel);
		if(i>0) {
			String str = formatLog(user);
			String message = str+":update"+"表:pic";
			log.info(message);
			return 1;
		}else {
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
	@RequestMapping("user_orderUploadAll.do")
	@ResponseBody
	public void uploadAll(int wId,HttpServletResponse response) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		//HSSFSheet sheet = workbook.createSheet();
		/*List<Order_Hardware> list = ordersService.getOrder_HardwaresByOrdersId(oId);*/
		List<OrderInfo> orderInfos = water_DivisionService.getOrderInf(wId);
		for(int j = 0;j<orderInfos.size();j++) {
			HSSFSheet sheet = workbook.createSheet();
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
			List<Order_Hardware> list = orderInfos.get(j).getOrder_Hardwares();
			for (int i = 1; i <= list.size(); i++) {
				HSSFRow rows = sheet.createRow(i);
				Hardware_Group hardware_Group = list.get(i-1).getHardware_Group();
				Hardware hardware = hardware_Group.getHardwareList().get(0);
				rows.createCell(0).setCellValue(hardware.getModule());
				rows.createCell(1).setCellValue(hardware.getName());
				rows.createCell(2).setCellValue(hardware.getType());
				rows.createCell(3).setCellValue(hardware.getUnit());
				int num = hardware.getNum()*list.get(j).getMultiple();
				rows.createCell(4).setCellValue(num);
				rows.createCell(5).setCellValue(hardware.getBrand());
				rows.createCell(6).setCellValue(hardware_Group.getDesct());
				rows.createCell(7).setCellValue(hardware.getPrice());
				sum+=hardware.getPrice()*num;
			}
			HSSFRow rows = sheet.createRow(list.size());
			rows.createCell(6).setCellValue("总价");
			rows.createCell(7).setCellValue(sum);
			HSSFRow rows1 = sheet.createRow(list.size()+1);
			rows1.createCell(6).setCellValue("泵房数量");
			rows1.createCell(7).setCellValue(orderInfos.get(j).getNum());
		}
		
		
		
		
		//File file = new File("E:/test.xls");
		//FileOutputStream xlsStream = new FileOutputStream(file);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=plan.xls");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
		
	}
	/**
	 * 	解析 id_Multiple
	 * @return
	 */
	private List<Order_Hardware> parseString(String[] id_Multiple){
		int length = id_Multiple.length;
		List<Order_Hardware> order_Hardwares = new ArrayList<>(length);
		for(int i=0;i<length;i++) {
			Order_Hardware order_Hardware = new Order_Hardware();
			int id = Integer.parseInt(id_Multiple[i].split("\\*")[0]);
			int multiple = Integer.parseInt(id_Multiple[i].split("\\*")[1]);
			order_Hardware.setHardware_id(id);
			order_Hardware.setMultiple(multiple);
			order_Hardwares.add(order_Hardware);
		}
		return order_Hardwares;
		
	}
	
	private String formatLog(Users user) {
		String userName = user.getUserName();
		String rName = user.getrName();
		return rName+" "+userName;
	}
}
