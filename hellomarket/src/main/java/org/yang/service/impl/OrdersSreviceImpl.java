package org.yang.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.OperationLogsMapper;
import org.yang.mapper.OrdersMapper;
import org.yang.mapper.ProductMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.OperationLogs;
import org.yang.pojo.Orders;
import org.yang.pojo.OrdersInfo;
import org.yang.pojo.ProductInfo;
import org.yang.service.OrdersService;
import org.yang.utils.Utils;
@Service
public class OrdersSreviceImpl implements OrdersService {
	@Resource
	private OrdersMapper ordersMapper;
	@Resource
	private OperationLogsMapper operationLogsMapper;
	@Resource
	private ProductMapper productMapper;
	private int limit =20;
	@Override
	public List<OrdersInfo> showOrdersInfoByPage(String condition, String startPage,String phone,HttpServletRequest request) {
		if(Utils.judgEmpty(startPage)) {
			startPage="1";
		}
		if(Utils.judgEmpty(condition)) {
			request.getSession().removeAttribute("condition");
			List<OrdersInfo> ordersInfoList=ordersMapper.selectOrdersInfoByPage((Integer.parseInt(startPage)-1)*limit, limit,phone);
			for(int i=0;i<ordersInfoList.size();i++) {
				if(ordersInfoList.get(i).getSpecification_id().length()>3) {
					List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
					String specificationIdArray=ordersInfoList.get(i).getSpecification_id().substring(0, ordersInfoList.get(i).getSpecification_id().length()-1);
					List<String> specificationIdList=Arrays.asList(specificationIdArray.split(","));
					for(int j=0;j<specificationIdList.size();j++) {
						ProductInfo productInfo = productMapper.selectProductInfoByProductSpecificationId(Integer.parseInt(specificationIdList.get(j)));
						productInfoList.add(productInfo);
					}
					ordersInfoList.get(i).setProductInfoList(productInfoList);
				}else {
					List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
					ProductInfo productInfo = productMapper.selectProductInfoByProductSpecificationId(Integer.parseInt(ordersInfoList.get(i).getSpecification_id().substring(0, ordersInfoList.get(i).getSpecification_id().length()-1)));
					productInfoList.add(productInfo);
					ordersInfoList.get(i).setProductInfoList(productInfoList);
				}
				
			}
			return ordersInfoList;
		}else {
			condition = Utils.judgeChiese(condition);
			request.getSession().setAttribute("condition", condition);
			List<OrdersInfo> ordersInfoList=new ArrayList<OrdersInfo>();
			/*if(condition.equals("received")) {
				ordersInfoList=ordersMapper.selectOrdersInfoByPageAndCondition("已发货", (Integer.parseInt(startPage)-1)*limit, limit);
			}else if(condition.equals("commented")) {
				ordersInfoList=ordersMapper.selectOrdersInfoByPageAndCondition("已收货", (Integer.parseInt(startPage)-1)*limit, limit);
			}else {*/
			ordersInfoList=ordersMapper.selectOrdersInfoByPageAndCondition(condition, (Integer.parseInt(startPage)-1)*limit, limit);
			for(int i=0;i<ordersInfoList.size();i++) {
				if(ordersInfoList.get(i).getSpecification_id().length()>3) {
					List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
					String specificationIdArray=ordersInfoList.get(i).getSpecification_id().substring(0, ordersInfoList.get(i).getSpecification_id().length()-1);
					List<String> specificationIdList=Arrays.asList(specificationIdArray.split(","));
					for(int j=0;j<specificationIdList.size();j++) {
						ProductInfo productInfo = productMapper.selectProductInfoByProductSpecificationId(Integer.parseInt(specificationIdList.get(j)));
						productInfoList.add(productInfo);//多个
					}
					ordersInfoList.get(i).setProductInfoList(productInfoList);
				}else {
					List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
					ProductInfo productInfo=productMapper.selectProductInfoByProductSpecificationId(Integer.parseInt(ordersInfoList.get(i).getSpecification_id().substring(0, ordersInfoList.get(i).getSpecification_id().length()-1)));
					productInfoList.add(productInfo);
					ordersInfoList.get(i).setProductInfoList(productInfoList);
				}
			}
			return ordersInfoList;
		}
	}
	@Override
	public List<OrdersInfo> showMyOrdersInfoByPage(String createTime,String status, String startPage,String phone,HttpServletRequest request) {
		if(Utils.judgEmpty(startPage)) {
			startPage="1";
		}
		if(Utils.judgEmpty(createTime)&&Utils.judgEmpty(status)) {
			request.getSession().removeAttribute("createTime");
			request.getSession().removeAttribute("status");
			List<OrdersInfo> ordersInfoList=ordersMapper.selectMyOrdersInfoByPageAndCondition("","","",(Integer.parseInt(startPage)-1)*limit, limit,phone);
			for(int i=0;i<ordersInfoList.size();i++) {
				if(ordersInfoList.get(i).getSpecification_id().length()>3) {
					List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
					String specificationIdArray=ordersInfoList.get(i).getSpecification_id().substring(0, ordersInfoList.get(i).getSpecification_id().length()-1);
					List<String> specificationIdList=Arrays.asList(specificationIdArray.split(","));
					for(int j=0;j<specificationIdList.size();j++) {
						ProductInfo productInfo = productMapper.selectProductInfoByProductSpecificationId(Integer.parseInt(specificationIdList.get(j)));
						productInfoList.add(productInfo);
					}
					ordersInfoList.get(i).setProductInfoList(productInfoList);
				}else {
					List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
					ProductInfo productInfo = productMapper.selectProductInfoByProductSpecificationId(Integer.parseInt(ordersInfoList.get(i).getSpecification_id().substring(0, ordersInfoList.get(i).getSpecification_id().length()-1)));
					productInfoList.add(productInfo);
					ordersInfoList.get(i).setProductInfoList(productInfoList);
				}
				
			}
			return ordersInfoList;
		}else {
			status = Utils.judgeChiese(status);
			request.getSession().setAttribute("status", status);
			List<OrdersInfo> ordersInfoList=new ArrayList<OrdersInfo>();
			String endTime=Utils.getCurrentDate();
			String beginTime="";
			Date date=new Date();
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			if(!(Utils.judgEmpty(createTime))) {
				createTime = Utils.judgeChiese(createTime);
				request.getSession().setAttribute("createTime",createTime);
				if(createTime.equals("过去一周")) {
					calendar.add(Calendar.WEEK_OF_MONTH,-1);
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
					int day = calendar.get(Calendar.DAY_OF_MONTH);
					beginTime=year+"-"+month+"-"+day;
				}
				if(createTime.equals("过去一月")) {
					calendar.add(Calendar.MONTH,-1);
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
					int day = calendar.get(Calendar.DAY_OF_MONTH);
					beginTime=year+"-"+month+"-"+day;
				}
				if(createTime.equals("过去一年")) {
					calendar.add(Calendar.YEAR,-1);
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
					int day = calendar.get(Calendar.DAY_OF_MONTH);
					beginTime=year+"-"+month+"-"+day;
				}
				if(status.equals("received")) {
					ordersInfoList=ordersMapper.selectMyOrdersInfoByPageAndCondition(beginTime,endTime,"已发货", (Integer.parseInt(startPage)-1)*limit, limit,phone);
				}else if(status.equals("commented")) {
					ordersInfoList=ordersMapper.selectMyOrdersInfoByPageAndCondition(beginTime,endTime,"已收货", (Integer.parseInt(startPage)-1)*limit, limit,phone);
				}else {
					ordersInfoList=ordersMapper.selectMyOrdersInfoByPageAndCondition(beginTime,endTime,"",(Integer.parseInt(startPage)-1)*limit, limit,phone);
				}
			}else {
				if(status.equals("received")) {
					ordersInfoList=ordersMapper.selectMyOrdersInfoByPageAndCondition("","","已发货", (Integer.parseInt(startPage)-1)*limit, limit,phone);
				}else if(status.equals("commented")) {
					ordersInfoList=ordersMapper.selectMyOrdersInfoByPageAndCondition("","","已收货", (Integer.parseInt(startPage)-1)*limit, limit,phone);
				}else {
					ordersInfoList=ordersMapper.selectMyOrdersInfoByPageAndCondition("","","",(Integer.parseInt(startPage)-1)*limit, limit,phone);
				}
			}
			for(int i=0;i<ordersInfoList.size();i++) {
				if(ordersInfoList.get(i).getSpecification_id().length()>3) {
					List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
					String specificationIdArray=ordersInfoList.get(i).getSpecification_id().substring(0, ordersInfoList.get(i).getSpecification_id().length()-1);
					List<String> specificationIdList=Arrays.asList(specificationIdArray.split(","));
					for(int j=0;j<specificationIdList.size();j++) {
						ProductInfo productInfo = productMapper.selectProductInfoByProductSpecificationId(Integer.parseInt(specificationIdList.get(j)));
						productInfoList.add(productInfo);//多个
					}
					ordersInfoList.get(i).setProductInfoList(productInfoList);
				}else {
					List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
					ProductInfo productInfo=productMapper.selectProductInfoByProductSpecificationId(Integer.parseInt(ordersInfoList.get(i).getSpecification_id().substring(0, ordersInfoList.get(i).getSpecification_id().length()-1)));
					productInfoList.add(productInfo);
					ordersInfoList.get(i).setProductInfoList(productInfoList);
				}
			}
			return ordersInfoList;
		}
	}
	
	@Override
	public String updateOrder(String order_status, String order_id, HttpServletRequest request) {
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="更新订单号为" + order_id + "的状态为" + order_status;
		int updateStatus = ordersMapper.updateOrder(order_status, order_id);
		if(updateStatus==1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "更新成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "更新成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "更新失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "更新失败";
		}
	}
	@Override
	public int getTotalPage(String condition) {
		int count=0;
		if(Utils.judgEmpty(condition)) {
			count=ordersMapper.selectCount();
		}else {
			condition=Utils.judgeChiese(condition);
			count=ordersMapper.selectCountByCondition(condition);
		}
		int totalPage=(int)Math.ceil(count/(limit*1.0));
		return totalPage;
	}
	@Override
	public int getMyOrdersTotalPage(String createTime,String status,String phone) {
		int count=0;
		if(Utils.judgEmpty(createTime)&&Utils.judgEmpty(status)) {
			count=ordersMapper.selectMyOrdersCount(phone);
		}else {
			if(!(Utils.judgEmpty(createTime))) {
				createTime=Utils.judgeChiese(createTime);
				String endTime=Utils.getCurrentDate();
				String beginTime="";
				Date date=new Date();
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(date);
				if(createTime.equals("过去一周")) {
					calendar.add(Calendar.WEEK_OF_MONTH,-1);
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
					int day = calendar.get(Calendar.DAY_OF_MONTH);
					beginTime=year+"-"+month+"-"+day;
				}
				if(createTime.equals("过去一月")) {
					calendar.add(Calendar.MONTH,-1);
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
					int day = calendar.get(Calendar.DAY_OF_MONTH);
					beginTime=year+"-"+month+"-"+day;
				}
				if(createTime.equals("过去一年")) {
					calendar.add(Calendar.YEAR,-1);
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
					int day = calendar.get(Calendar.DAY_OF_MONTH);
					beginTime=year+"-"+month+"-"+day;
				}
				status=Utils.judgeChiese(status);
				if(status.equals("received")) {
					count=ordersMapper.selectMyOrdersCountByCondition(beginTime,endTime,"已发货",phone);
				}else
				if(status.equals("commented")) {
					count=ordersMapper.selectMyOrdersCountByCondition(beginTime,endTime,"已收货",phone);
				}else {
					count=ordersMapper.selectMyOrdersCountByCondition(beginTime,endTime,"",phone);
				}
		}else {
			status=Utils.judgeChiese(status);
			if(status.equals("received")) {
				count=ordersMapper.selectMyOrdersCountByCondition("","","已发货",phone);
			}else
			if(status.equals("commented")) {
				count=ordersMapper.selectMyOrdersCountByCondition("","","已收货",phone);
			}else {
				count=ordersMapper.selectMyOrdersCountByCondition("","","",phone);
			}
		}
		}
		int totalPage=(int)Math.ceil(count/(limit*1.0));
		return totalPage;
	}
	@Override
	public void addOrders(Orders order) {
		ordersMapper.insertOrders(order);
	}
	@Override
	public List<String> selectSpecificationIdByPhone(String phone) {
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH)+1;
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		String beginTime=year+"-"+month+"-"+day;
		calendar.add(Calendar.DAY_OF_MONTH,7);
		int endday=calendar.get(Calendar.DAY_OF_MONTH);
		String endTime=year+"-"+month+"-"+endday;
		List<String> specificationIdList = ordersMapper.selectSpecificationIdByPhone(phone,beginTime,endTime);
		return specificationIdList;
	}
	@Override
	public int updateOrderStatus(String specificationId) {
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH)+1;
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		String beginTime=year+"-"+month+"-"+day;
		calendar.add(Calendar.DAY_OF_MONTH,7);
		int endday=calendar.get(Calendar.DAY_OF_MONTH);
		String endTime=year+"-"+month+"-"+endday;
		int updateStatus=ordersMapper.updateOrderStatus(specificationId, beginTime, endTime);
		return updateStatus;
	}
	@Override
	public String updateOrderStatusByOrderId(String orderId,String order_status) {
		int updateStatus = ordersMapper.updateOrder(order_status, orderId);
		if(updateStatus==1) {
			return "更新成功";
		}else {
			return "更新失败";
		}
	}
	@Override
	public String getProductIdByOrderId(String orderId) {
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH)+1;
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		String beginTime=year+"-"+month+"-"+day;
		calendar.add(Calendar.DAY_OF_MONTH,7);
		int endday=calendar.get(Calendar.DAY_OF_MONTH);
		String endTime=year+"-"+month+"-"+endday;
		List<String> specificationIdList = ordersMapper.selectSpecificationIdByOrderId(orderId, beginTime, endTime);
		String specificationId=specificationIdList.get(0).substring(0, specificationIdList.get(0).indexOf(","));
		String productId=String.valueOf(productMapper.selectProductInfoBySpecificationId(Integer.parseInt(specificationId)).getProduct_id());
		return productId;
	}

}
