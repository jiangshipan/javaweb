package com.jsp.dao;

import java.util.List;
import java.util.Map;

import com.jsp.bean.Order;
import com.jsp.bean.OrderItem;

public interface OrderDao {

	//��ӵ�������
	void saveOrder(Order order);

	//��ӵ��������
	void saveOrderItem(OrderItem orderItem);

	//�����û�id��ѯ����
	List<Order> findOrderByUid(String uid);

	//���ݶ�����id��ѯ������������еĶ�����
	List<Map<String, Object>> findOrderItemByOid(String oid);

	//����id�����޸�
	void updateOrderById(String r6_Order);

	//��ѯ���еĶ���
	List<Order> findAllOrder();

	//���ݶ�����״̬��ѯ
	List<Order> findOrderByState(int state);
	//����order���gidΪnull
	void setGid(String gid);
	//��ɸ���
	void setState(String oid);
	//ɾ������
	void deleteOrder(String oid);
	//����oidΪ��
	void setOid(String oid);

}
