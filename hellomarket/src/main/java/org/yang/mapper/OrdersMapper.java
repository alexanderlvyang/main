package org.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yang.pojo.Orders;
import org.yang.pojo.OrdersInfo;

public interface OrdersMapper {
	List<OrdersInfo> selectOrdersInfoByPage(@Param("startPage") int startPage,@Param("limit") int limit,@Param("phone")String phone);
	List<OrdersInfo> selectOrdersInfoByPageAndCondition(@Param("condition") String condition,@Param("startPage") int startPage,@Param("limit") int limit);
	List<OrdersInfo> selectMyOrdersInfoByPageAndCondition(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("status") String status,@Param("startPage") int startPage,@Param("limit") int limit,@Param("phone")String phone);
	int updateOrder(@Param("order_status")String order_status, @Param("order_id") String order_id);
	int selectCountByCondition(String condition);
	int selectCount();
	int selectMyOrdersCountByCondition(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("status")String status,@Param("phone")String phone);
	int selectMyOrdersCount(@Param("phone")String phone);
	int insertOrders(Orders order);
	List<String> selectSpecificationIdByPhone(@Param("phone")String phone,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	List<String> selectSpecificationIdByOrderId(@Param("orderId")String orderId,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	int updateOrderStatus(@Param("specificationId")String specificationId,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
}
