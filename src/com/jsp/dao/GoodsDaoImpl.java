package com.jsp.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jsp.bean.Goods;
import com.jsp.utils.MyJDBCUtils;

public class GoodsDaoImpl implements GoodsDao {

	//查询所有的商品
	public List<Goods> findAll() {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			List<Goods> list = runner.query("select * from Goods", new BeanListHandler<Goods>(Goods.class));
			return list;
		}catch(Exception e) {
			
		}
		return null;
	}

	//根据分类的id查询商品
	public List<Goods> findGoodsId(String cid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			List<Goods> list = runner.query("select * from Goods where cid=?", 
					new BeanListHandler<Goods>(Goods.class),cid);
			return list;
		}catch(Exception e) {
			
		}
		return null;
	}

	//根据id查询商品
	public Goods findById(String gid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			Goods Goods = runner.query("select * from Goods where gid=?", 
					new BeanHandler<Goods>(Goods.class),gid);
			return Goods;
		}catch(Exception e) {
			
		}
		return null;
	}

	//根据商品的id把商品的id设置为null
	public void updateGoodsCid(Goods Goods) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			//update Goods set cid=? where cid=?
			runner.update("update Goods set cid=? where cid=?",null,Goods.getCid());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	//增加商品
	public void addGoodsAdmin(Goods Goods) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			Object[] os = {Goods.getGid(),Goods.getGname(),Goods.getPrice(),Goods.getStorename(),Goods.getImage(),
					Goods.getCid()};
			runner.update("insert into Goods values(?,?,?,?,?,?)", os);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//后台修改商品的功能
	public void updateGoods(Goods Goods) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			Object[] os = {Goods.getGname(),Goods.getPrice(),Goods.getStorename(),Goods.getCid(),
					Goods.getGid()};
			runner.update("update goods set gname=?,price=?,storename=?,cid=? where gid=?", os);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	//根据id删除商品
	public boolean delGoods(String gid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("delete from goods where gid=?", gid);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//模糊查询
	public List<Goods> findBySimpleName(String name) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			List<Goods> list = runner.query("select * from goods where gname like ?",new BeanListHandler<Goods>(Goods.class),"%"+name+"%");
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Goods> findByRand() {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			List<Goods> list = runner.query("select * from goods order by rand() limit 8",new BeanListHandler<Goods>(Goods.class));
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
