package com.jsp.dao;

import java.util.List;

import com.jsp.bean.Goods;

public interface GoodsDao {

	//查询所有的商品
	List<Goods> findAll();

	//根据cid查询商品
	List<Goods> findGoodsId(String cid);

	//根据id查询商品
	Goods findById(String bid);

	//根据商品的id把商品的id设置为null
	void updateGoodsCid(Goods goods);

	//增加商品
	void addGoodsAdmin(Goods goods);

	//后台修改商品的功能
	void updateGoods(Goods goods);

	//根据id删除商品
	boolean delGoods(String gid);
	//实现搜索功能
	List<Goods> findBySimpleName(String name);
	//实现热点推荐功能
	List<Goods> findByRand();

}
