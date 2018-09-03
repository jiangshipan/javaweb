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

	//��Ӷ�����
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

	//��Ӷ������
//	  iid VARCHAR(100) PRIMARY KEY,/*����*/
//	  `count` INT,/*����*/
//	  subtotal DECIMAL(10,0),/*С��*/
//	  oid  VARCHAR(100),/*��������*/
//	  bid  VARCHAR(100),/*��������ָ����Ʒ*/
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

	//�����û���id��ѯ����
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

	//���ݶ�����Ų�ѯ������
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

	//����id�޸Ķ���
	public void updateOrderById(String r6_Order) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("update orders set state=? where oid=?", 2,r6_Order);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	//��ѯ���еĶ���
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

	//���ݶ�����״̬��ѯ����
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
	//����orderitem���gidΪ0
	public void setGid(String gid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("update orderitem set gid=? where gid=?",null,gid);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
	}
	//����oid����state=2
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
	//����oidΪ��
	public void setOid(String oid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("update orderitem set oid=? where oid=?",null,oid);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

}
