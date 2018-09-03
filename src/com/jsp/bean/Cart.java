package com.jsp.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ���ﳵ��ʵ��
 * @author asus
 *
 */
public class Cart {
	
	private double total;
	//map��key����Ʒ��id��value��ÿһ��������
	private Map<String,CartItem> map = new HashMap<String,CartItem>(); 
	
	public double getTotal() {
		return total;
	}

//	public Map<String, CartItem> getMap() {
//		return map;
//	}
	//ֱ����ҳ�淵��һ�����ϣ�������ҳ���б���
	public Collection<CartItem> getCartItems() {
		//����map�����values��ֵ
		return map.values();
	}

	//��װ�����������������������Ƕ�map���ϵĲ���
	//���ӵ����ﳵ
	public void addCart(CartItem cartItem) {
		/*
		 * 1�����ȸ���id�ж��Ƿ�����ͬ����Ʒ
		 * 2���������ͬ����Ʒ����ȡ��Ʒ��������֮��+1
		 * 3�����û����ͬ����Ʒ��ֱ�Ӱ���Ʒid����������ӵ�map����
		 * 4���ܼ�+�����Ʒ��С��
		 * */
		String Gid = cartItem.getGoods().getGid();
		if(map.containsKey(Gid)) {//����ͬ��key ��Gid��
			CartItem _item = map.get(Gid);
			_item.setCount(_item.getCount()+cartItem.getCount());
		} else {
			map.put(Gid, cartItem);
		}
		//����ܼ�
		total += cartItem.getSubtotal();
	}
	
	//ɾ��ĳһ��������
	public void removeCart(String id) {
		//����id�ѹ������Ƴ�
		CartItem cartItem = map.remove(id);
		//���ܼƼ�ȥ�Ƴ��Ĺ����������С��
		total -= cartItem.getSubtotal();
	}
	
	//��չ��ﳵ
	public void clearCart() {	
		map.clear();
		total = 0;
	}
	
}
