package com.jsp.bean;

import java.util.ArrayList;
import java.util.List;

//oid VARCHAR(100) PRIMARY KEY,/*����*/
//ordertime DATETIME,/*��������ʱ��*/
//total DECIMAL(10,0),/*�����ϼ�*/
//state SMALLINT(1),/*����״̬��δ����Ѹ���/
//uid VARCHAR(100),/*����������*/
//address VARCHAR(200),/*�������ջ���ַ*/
public class Order {

	private String oid;
	private String ordertime;
	private double total;
	private int state; // 1,δ���� 2���Ѹ���
	private String address;
	private User user;
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
