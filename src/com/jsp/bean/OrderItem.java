package com.jsp.bean;

//iid VARCHAR(100) PRIMARY KEY,/*����*/
//`count` INT,/*����*/
//subtotal DECIMAL(10,0),/*С��*/
//oid  VARCHAR(100),/*��������*/
//bid  VARCHAR(100),/*��������ָ����Ʒ*/
public class OrderItem {

	private String iid;
	private int count;
	private double subtotal;
	private Order order;
	private Goods goods;
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
}
