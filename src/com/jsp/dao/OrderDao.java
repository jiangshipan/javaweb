package com.jsp.dao;

import java.util.List;
import java.util.Map;

import com.jsp.bean.Order;
import com.jsp.bean.OrderItem;

public interface OrderDao {

	//添加到订单表
	void saveOrder(Order order);

	//添加到订单项表
	void saveOrderItem(OrderItem orderItem);

	//根据用户id查询订单
	List<Order> findOrderByUid(String uid);

	//根据订单的id查询订单里面的所有的订单项
	List<Map<String, Object>> findOrderItemByOid(String oid);

	//根据id进行修改
	void updateOrderById(String r6_Order);

	//查询所有的订单
	List<Order> findAllOrder();

	//根据订单的状态查询
	List<Order> findOrderByState(int state);
	//设置order里的gid为null
	void setGid(String gid);
	//完成付款
	void setState(String oid);
	//删除订单
	void deleteOrder(String oid);
	//设置oid为空
	void setOid(String oid);

}
