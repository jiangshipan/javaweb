package com.jsp.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车的实体
 * @author asus
 *
 */
public class Cart {
	
	private double total;
	//map的key是商品的id，value是每一个购物项
	private Map<String,CartItem> map = new HashMap<String,CartItem>(); 
	
	public double getTotal() {
		return total;
	}

//	public Map<String, CartItem> getMap() {
//		return map;
//	}
	//直接向页面返回一个集合，方便在页面中遍历
	public Collection<CartItem> getCartItems() {
		//返回map里面的values的值
		return map.values();
	}

	//封装三个方法，这三个方法就是对map集合的操作
	//增加到购物车
	public void addCart(CartItem cartItem) {
		/*
		 * 1、首先根据id判断是否有相同的商品
		 * 2、如果有相同的商品，获取商品的数量，之后+1
		 * 3、如果没有相同的商品，直接把商品id，购物项添加到map里面
		 * 4、总计+添加商品的小计
		 * */
		String Gid = cartItem.getGoods().getGid();
		if(map.containsKey(Gid)) {//有相同的key （Gid）
			CartItem _item = map.get(Gid);
			_item.setCount(_item.getCount()+cartItem.getCount());
		} else {
			map.put(Gid, cartItem);
		}
		//添加总计
		total += cartItem.getSubtotal();
	}
	
	//删除某一个购物项
	public void removeCart(String id) {
		//根据id把购物向移除
		CartItem cartItem = map.remove(id);
		//把总计减去移除的购物项里面的小计
		total -= cartItem.getSubtotal();
	}
	
	//清空购物车
	public void clearCart() {	
		map.clear();
		total = 0;
	}
	
}
