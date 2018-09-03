package com.jsp.bean;
/**
 * 购物项的实体
 * @author asus
 *
 */
public class CartItem {

	private Goods goods;
	private int count;
//	private double subtotal; //小计：图书的价格*数量
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		String s = String.format("%.2f",goods.getPrice()*count);
		return Double.parseDouble(s);
		/*return goods.getPrice()*count;*/
	}
}
