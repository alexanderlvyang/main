package org.yang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.pojo.Users;
import org.yang.service.OrdersService;

@Controller
public class OrdersController {
	@Resource
	private OrdersService ordersServiceImpl;
	
	@RequestMapping("orderManage.do")
	public String showOrdersInfo(Model model,String condition,String phone,String startPage,HttpServletRequest request) {
		model.addAttribute("totalPage", ordersServiceImpl.getTotalPage(condition));
		model.addAttribute("ordersInfoList", ordersServiceImpl.showOrdersInfoByPage(condition, startPage,phone,request));
		return "WEB-INF/pages/ordersinfo.jsp";
	}
	@RequestMapping("updateOrder.do")
	@ResponseBody
	public String updateOrder(String order_status,String order_id,HttpServletRequest request) {
		return ordersServiceImpl.updateOrder(order_status, order_id, request);
	}
	@RequestMapping("myOrder")
	public String myOrder(HttpServletRequest request,String startPage,String status,String createTime,Model model) {
		Users user=(Users)request.getSession().getAttribute("user");
		model.addAttribute("totalPage", ordersServiceImpl.getMyOrdersTotalPage(createTime,status,user.getPhone()));
		model.addAttribute("ordersInfoList", ordersServiceImpl.showMyOrdersInfoByPage(createTime,status, startPage, user.getPhone(), request));
		return "WEB-INF/frontpages/myorders.jsp";
	}
}	
