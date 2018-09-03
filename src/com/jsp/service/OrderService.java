package com.jsp.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.jsp.bean.Goods;
import com.jsp.bean.Order;
import com.jsp.bean.OrderItem;
import com.jsp.dao.OrderDao;
import com.jsp.dao.OrderDaoImpl;

public class OrderService {

	//存储订单信息到购物车
	public void saveOrder(Order order) {
		OrderDao dao = new OrderDaoImpl();
		//添加订单到订单表
		dao.saveOrder(order);
		//添加订单项到订单项表
		//得到订单项的集合
		List<OrderItem> list = order.getOrderItems();
		for (OrderItem orderItem : list) {
			dao.saveOrderItem(orderItem);
		}
	}

	//根据用户的id查询所有的订单
	public List<Order> findOrdersByUid(String uid) {
		OrderDao dao = new OrderDaoImpl();
		List<Order> list = dao.findOrderByUid(uid);
		return list;
	}

	//根据订单的id，查询订单项
	public List<Map<String, Object>> findOrderItemByOid(String oid) {
		OrderDao dao = new OrderDaoImpl();
		List<Map<String, Object>> list = dao.findOrderItemByOid(oid);
		return list;
	}

	//根据id修改订单额状态
	public void updateOrderById(String r6_Order) {
		OrderDao dao = new OrderDaoImpl();
		dao.updateOrderById(r6_Order);		
	}

	//根据id查询订单（封装订单的订单项）
	public List<Order> findOrdersByUidTest(String uid) throws Exception {
		
		OrderDao dao = new OrderDaoImpl();
		List<Order> list = dao.findOrderByUid(uid);
		//查询多个订单，在每个订单里面有很多的订单项
		for (Order order : list) {//每个订单
			//向每个订单里面添加订单项
			//根据订单的id查询订单里面的所有的订单项
			//结果：每个订单项封装到map里面，把多个map放到list集合里面
			//[{bname=Java编程思想（第4版）, author=qdmmy6, ......}]
			List<Map<String, Object>> maplist = dao.findOrderItemByOid(order.getOid());
			//继续遍历map的list集合，把每个订单项封装
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = new OrderItem();
				//使用beanutils封装对象
				BeanUtils.populate(orderItem, map);
				//封装Goods对象
				Goods goods = new Goods();
				BeanUtils.populate(goods, map);
				orderItem.setGoods(goods);
				orderItem.setOrder(order);
				//把封装之后的订单项放到订单的集合里面
				order.getOrderItems().add(orderItem);			
			}		
		}
		return list;
	}

	//查询所有的订单
	public List<Order> findAllOrder() throws Exception {
		OrderDao dao = new OrderDaoImpl();
		//查询所有的订单的方法
		List<Order> orderlist = dao.findAllOrder();
		//封装order
		for (Order order : orderlist) {
			//查询订单下面的所有的订单项
			List<Map<String, Object>> maplist = dao.findOrderItemByOid(order.getOid());
			//遍历map的list集合
			for (Map<String, Object> map : maplist) {
				//分别封装orderItem和Goods
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				Goods goods = new Goods();
				BeanUtils.populate(goods, map);
				orderItem.setGoods(goods);
				orderItem.setOrder(order);
				//把订单项放到order的list里面
				order.getOrderItems().add(orderItem);
			}
		}
		return orderlist;
	}

	//根据订单的状态查询订单
	public List<Order> findOrderByState(int state) throws Exception {
		OrderDao dao = new OrderDaoImpl();
		//查询所有的订单的方法
		List<Order> orderlist = dao.findOrderByState(state);
		//封装order
		for (Order order : orderlist) {
			//查询订单下面的所有的订单项
			List<Map<String, Object>> maplist = dao.findOrderItemByOid(order.getOid());
			//遍历map的list集合
			for (Map<String, Object> map : maplist) {
				//分别封装orderItem和Goods
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				Goods goods = new Goods();
				BeanUtils.populate(goods, map);
				orderItem.setGoods(goods);
				orderItem.setOrder(order);
				//把订单项放到order的list里面
				order.getOrderItems().add(orderItem);
			}
		}
		return orderlist;
	}


	public void setGid(String gid) {
		OrderDao dao = new OrderDaoImpl();
		dao.setGid(gid);
	
	}
	//完成付款
	public void setState(String oid) {
		OrderDao dao = new OrderDaoImpl();
		dao.setState(oid);
	}
	//删除订单
	public void deleteOrder(String oid) {
		OrderDao dao = new OrderDaoImpl();
		dao.deleteOrder(oid);
		
	}
	//设置orderitem 里的oid为空
	public void setOid(String oid) {
		OrderDao dao = new OrderDaoImpl();
		dao.setOid(oid);
		
	}

}
