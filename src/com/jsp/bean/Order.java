package com.jsp.bean;

import java.util.ArrayList;
import java.util.List;

//oid VARCHAR(100) PRIMARY KEY,/*主键*/
//ordertime DATETIME,/*订单生成时间*/
//total DECIMAL(10,0),/*订单合计*/
//state SMALLINT(1),/*订单状态：未付款、已付款/
//uid VARCHAR(100),/*订单的主人*/
//address VARCHAR(200),/*订单的收货地址*/
public class Order {

	private String oid;
	private String ordertime;
	private double total;
	private int state; // 1,未付款 2，已付款
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
