package com.jsp.service;

import java.util.List;

import com.jsp.bean.Goods;
import com.jsp.dao.GoodsDao;
import com.jsp.dao.GoodsDaoImpl;

public class GoodsService {

	//��ѯ���е���Ʒ
	public List<Goods> findAllGoods() {
		//����dao�ӿ�����ķ���ʵ��
		GoodsDao dao = new GoodsDaoImpl();
		List<Goods> list = dao.findAll();
		return list;
	}

	//����id���в�ѯ��Ʒ
	public List<Goods> findGoodsByCid(String cid) {
		//����dao�ӿ�����ķ���ʵ��
		GoodsDao dao = new GoodsDaoImpl();
		List<Goods> list = dao.findGoodsId(cid);
		return list;
	}

	//������Ʒ��id��ѯĳ����Ʒ
	public Goods findById(String gid) {
		//����dao�ӿ�����ķ���ʵ��
		GoodsDao dao = new GoodsDaoImpl();
		Goods goods = dao.findById(gid);
		return goods;
	}

	//������Ʒ
	public void addGoodsAdmin(Goods goods) {
		//����dao�ӿ�����ķ���ʵ��
		GoodsDao dao = new GoodsDaoImpl();
		dao.addGoodsAdmin(goods);
	}

	//��̨�޸���Ʒ�Ĺ��ܣ�����gid�����޸ģ�
	public void updateGoods(Goods goods) {
		//����dao�ӿ�����ķ���ʵ��
		GoodsDao dao = new GoodsDaoImpl();
		dao.updateGoods(goods);
	}

	//����idɾ����Ʒ
	public boolean delGoods(String gid) {
		//����dao�ӿ�����ķ���ʵ��
		GoodsDao dao = new GoodsDaoImpl();
		boolean flag = dao.delGoods(gid);
		return flag;
	}
	//ʵ����������
	public List<Goods> findBySimpleName(String name) {
		GoodsDao dao = new GoodsDaoImpl();
		List<Goods> list = dao.findBySimpleName(name);
		return list;
	}
	//ʵ���ȵ��Ƽ�
	public List<Goods> findByRand() {
		GoodsDao dao = new GoodsDaoImpl();
		List<Goods> list = dao.findByRand();
		return list;
	}

}
