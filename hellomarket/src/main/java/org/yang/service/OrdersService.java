package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.Orders;
import org.yang.pojo.OrdersInfo;

public interface OrdersService {
	List<OrdersInfo> showOrdersInfoByPage(String condition,String startPage,String phone,HttpServletRequest request);
	List<OrdersInfo> showMyOrdersInfoByPage(String createTime,String status, String startPage,String phone,HttpServletRequest request) ;
	String updateOrder(String order_status,String order_id,HttpServletRequest request);
	int getTotalPage(String condition);
	void addOrders(Orders order);
	int getMyOrdersTotalPage(String createTime,String status,String phone);
	List<String> selectSpecificationIdByPhone(String phone);
	int updateOrderStatus(String specificationId);
	String updateOrderStatusByOrderId(String orderId,String order_status);
	String getProductIdByOrderId(String orderId);
}
