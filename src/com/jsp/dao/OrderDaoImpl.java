package com.jsp.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.jsp.bean.Order;
import com.jsp.bean.OrderItem;
import com.jsp.utils.MyJDBCUtils;

public class OrderDaoImpl implements OrderDao {

	//添加订单表
	public void saveOrder(Order order) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			Object[] os = {order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),
					order.getUser().getUid(),order.getAddress()};
			runner.update("insert into orders values(?,?,?,?,?,?)", os);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	//添加订单项表
//	  iid VARCHAR(100) PRIMARY KEY,/*主键*/
//	  `count` INT,/*数量*/
//	  subtotal DECIMAL(10,0),/*小计*/
//	  oid  VARCHAR(100),/*所属订单*/
//	  bid  VARCHAR(100),/*订单项所指的商品*/
	public void saveOrderItem(OrderItem orderItem) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			Object[] os = {orderItem.getIid(),orderItem.getCount(),orderItem.getSubtotal(),orderItem.getOrder().getOid(),
					orderItem.getGoods().getGid()};
			runner.update("insert into orderitem values(?,?,?,?,?)", os);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	//根据用户的id查询订单
	public List<Order> findOrderByUid(String uid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			List<Order> list = runner.query("select * from orders where uid=?", 
					new BeanListHandler<Order>(Order.class), uid);
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	//根据订单编号查询订单项
	public List<Map<String, Object>> findOrderItemByOid(String oid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			List<Map<String, Object>> list = runner.query("SELECT * FROM orderitem o,goods g WHERE o.gid=g.gid AND oid=?", 
					new MapListHandler(), oid);
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	//根据id修改订单
	public void updateOrderById(String r6_Order) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("update orders set state=? where oid=?", 2,r6_Order);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	//查询所有的订单
	public List<Order> findAllOrder() {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			List<Order> list = runner.query("select * from orders", 
					new BeanListHandler<Order>(Order.class));
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	//根据订单的状态查询订单
	public List<Order> findOrderByState(int state) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			List<Order> list = runner.query("select * from orders where state=?", 
					new BeanListHandler<Order>(Order.class),state);
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	//设置orderitem里的gid为0
	public void setGid(String gid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("update orderitem set gid=? where gid=?",null,gid);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
	}
	//根据oid设置state=2
	public void setState(String oid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("update orders set state=? where oid=?",2,oid);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	public void deleteOrder(String oid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("delete from orders where oid=?",oid);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
	}
	//设置oid为空
	public void setOid(String oid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("update orderitem set oid=? where oid=?",null,oid);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

}
