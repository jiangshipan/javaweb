package com.jsp.dao;

import java.util.List;

import com.jsp.bean.Goods;

public interface GoodsDao {

	//��ѯ���е���Ʒ
	List<Goods> findAll();

	//����cid��ѯ��Ʒ
	List<Goods> findGoodsId(String cid);

	//����id��ѯ��Ʒ
	Goods findById(String bid);

	//������Ʒ��id����Ʒ��id����Ϊnull
	void updateGoodsCid(Goods goods);

	//������Ʒ
	void addGoodsAdmin(Goods goods);

	//��̨�޸���Ʒ�Ĺ���
	void updateGoods(Goods goods);

	//����idɾ����Ʒ
	boolean delGoods(String gid);
	//ʵ����������
	List<Goods> findBySimpleName(String name);
	//ʵ���ȵ��Ƽ�����
	List<Goods> findByRand();

}
