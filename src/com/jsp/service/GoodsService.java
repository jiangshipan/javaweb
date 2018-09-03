package com.jsp.service;

import java.util.List;

import com.jsp.bean.Goods;
import com.jsp.dao.GoodsDao;
import com.jsp.dao.GoodsDaoImpl;

public class GoodsService {

	//查询所有的商品
	public List<Goods> findAllGoods() {
		//调用dao接口里面的方法实现
		GoodsDao dao = new GoodsDaoImpl();
		List<Goods> list = dao.findAll();
		return list;
	}

	//根据id进行查询商品
	public List<Goods> findGoodsByCid(String cid) {
		//调用dao接口里面的方法实现
		GoodsDao dao = new GoodsDaoImpl();
		List<Goods> list = dao.findGoodsId(cid);
		return list;
	}

	//根据商品的id查询某个商品
	public Goods findById(String gid) {
		//调用dao接口里面的方法实现
		GoodsDao dao = new GoodsDaoImpl();
		Goods goods = dao.findById(gid);
		return goods;
	}

	//增加商品
	public void addGoodsAdmin(Goods goods) {
		//调用dao接口里面的方法实现
		GoodsDao dao = new GoodsDaoImpl();
		dao.addGoodsAdmin(goods);
	}

	//后台修改商品的功能（根据gid进行修改）
	public void updateGoods(Goods goods) {
		//调用dao接口里面的方法实现
		GoodsDao dao = new GoodsDaoImpl();
		dao.updateGoods(goods);
	}

	//根据id删除商品
	public boolean delGoods(String gid) {
		//调用dao接口里面的方法实现
		GoodsDao dao = new GoodsDaoImpl();
		boolean flag = dao.delGoods(gid);
		return flag;
	}
	//实现搜索功能
	public List<Goods> findBySimpleName(String name) {
		GoodsDao dao = new GoodsDaoImpl();
		List<Goods> list = dao.findBySimpleName(name);
		return list;
	}
	//实现热点推荐
	public List<Goods> findByRand() {
		GoodsDao dao = new GoodsDaoImpl();
		List<Goods> list = dao.findByRand();
		return list;
	}

}
