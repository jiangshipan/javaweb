package com.jsp.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jsp.bean.Category;
import com.jsp.utils.MyJDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	//查询所有的分类
	public List<Category> findAll() {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			List<Category> list = runner.query("select * from category", new BeanListHandler<Category>(Category.class));
			return list;
		}catch(Exception e) {
			
		}
		return null;
	}

	//增加分类
	public void addCategory(Category category) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("insert into category values(?,?)", category.getCid(),category.getCname());
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}

	//根据id查询分类
	public Category findByCid(String cid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			Category cate = runner.query("select * from category where cid=?", 
					new BeanHandler<Category>(Category.class),cid);
			return cate;
		}catch(Exception e) {
			
		}
		return null;
	}

	//根据id修改分类
	public void updateCategory(Category category) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("update category set cname=? where cid=?", category.getCname(),category.getCid());
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	//根据id删除分类
	public void deleteCategory(String cid) {
		try {
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			runner.update("delete from category where cid=?", cid);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

}
