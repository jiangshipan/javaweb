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

	//�洢������Ϣ�����ﳵ
	public void saveOrder(Order order) {
		OrderDao dao = new OrderDaoImpl();
		//��Ӷ�����������
		dao.saveOrder(order);
		//��Ӷ�����������
		//�õ�������ļ���
		List<OrderItem> list = order.getOrderItems();
		for (OrderItem orderItem : list) {
			dao.saveOrderItem(orderItem);
		}
	}

	//�����û���id��ѯ���еĶ���
	public List<Order> findOrdersByUid(String uid) {
		OrderDao dao = new OrderDaoImpl();
		List<Order> list = dao.findOrderByUid(uid);
		return list;
	}

	//���ݶ�����id����ѯ������
	public List<Map<String, Object>> findOrderItemByOid(String oid) {
		OrderDao dao = new OrderDaoImpl();
		List<Map<String, Object>> list = dao.findOrderItemByOid(oid);
		return list;
	}

	//����id�޸Ķ�����״̬
	public void updateOrderById(String r6_Order) {
		OrderDao dao = new OrderDaoImpl();
		dao.updateOrderById(r6_Order);		
	}

	//����id��ѯ��������װ�����Ķ����
	public List<Order> findOrdersByUidTest(String uid) throws Exception {
		
		OrderDao dao = new OrderDaoImpl();
		List<Order> list = dao.findOrderByUid(uid);
		//��ѯ�����������ÿ�����������кܶ�Ķ�����
		for (Order order : list) {//ÿ������
			//��ÿ������������Ӷ�����
			//���ݶ�����id��ѯ������������еĶ�����
			//�����ÿ���������װ��map���棬�Ѷ��map�ŵ�list��������
			//[{bname=Java���˼�루��4�棩, author=qdmmy6, ......}]
			List<Map<String, Object>> maplist = dao.findOrderItemByOid(order.getOid());
			//��������map��list���ϣ���ÿ���������װ
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = new OrderItem();
				//ʹ��beanutils��װ����
				BeanUtils.populate(orderItem, map);
				//��װGoods����
				Goods goods = new Goods();
				BeanUtils.populate(goods, map);
				orderItem.setGoods(goods);
				orderItem.setOrder(order);
				//�ѷ�װ֮��Ķ�����ŵ������ļ�������
				order.getOrderItems().add(orderItem);			
			}		
		}
		return list;
	}

	//��ѯ���еĶ���
	public List<Order> findAllOrder() throws Exception {
		OrderDao dao = new OrderDaoImpl();
		//��ѯ���еĶ����ķ���
		List<Order> orderlist = dao.findAllOrder();
		//��װorder
		for (Order order : orderlist) {
			//��ѯ������������еĶ�����
			List<Map<String, Object>> maplist = dao.findOrderItemByOid(order.getOid());
			//����map��list����
			for (Map<String, Object> map : maplist) {
				//�ֱ��װorderItem��Goods
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				Goods goods = new Goods();
				BeanUtils.populate(goods, map);
				orderItem.setGoods(goods);
				orderItem.setOrder(order);
				//�Ѷ�����ŵ�order��list����
				order.getOrderItems().add(orderItem);
			}
		}
		return orderlist;
	}

	//���ݶ�����״̬��ѯ����
	public List<Order> findOrderByState(int state) throws Exception {
		OrderDao dao = new OrderDaoImpl();
		//��ѯ���еĶ����ķ���
		List<Order> orderlist = dao.findOrderByState(state);
		//��װorder
		for (Order order : orderlist) {
			//��ѯ������������еĶ�����
			List<Map<String, Object>> maplist = dao.findOrderItemByOid(order.getOid());
			//����map��list����
			for (Map<String, Object> map : maplist) {
				//�ֱ��װorderItem��Goods
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				Goods goods = new Goods();
				BeanUtils.populate(goods, map);
				orderItem.setGoods(goods);
				orderItem.setOrder(order);
				//�Ѷ�����ŵ�order��list����
				order.getOrderItems().add(orderItem);
			}
		}
		return orderlist;
	}


	public void setGid(String gid) {
		OrderDao dao = new OrderDaoImpl();
		dao.setGid(gid);
	
	}
	//��ɸ���
	public void setState(String oid) {
		OrderDao dao = new OrderDaoImpl();
		dao.setState(oid);
	}
	//ɾ������
	public void deleteOrder(String oid) {
		OrderDao dao = new OrderDaoImpl();
		dao.deleteOrder(oid);
		
	}
	//����orderitem ���oidΪ��
	public void setOid(String oid) {
		OrderDao dao = new OrderDaoImpl();
		dao.setOid(oid);
		
	}

}
