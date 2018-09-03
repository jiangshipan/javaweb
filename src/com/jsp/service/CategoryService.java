package com.jsp.service;

import java.util.List;

import com.jsp.bean.Goods;
import com.jsp.bean.Category;
import com.jsp.dao.GoodsDao;
import com.jsp.dao.GoodsDaoImpl;
import com.jsp.dao.CategoryDao;
import com.jsp.dao.CategoryDaoImpl;

public class CategoryService {

	//查询所有的分类
	public List<Category> findAllCategory() {
		CategoryDao dao = new CategoryDaoImpl();
		List<Category> list = dao.findAll();
		return list;
	}

	//增加分类
	public void addCategory(Category category) {
		CategoryDao dao = new CategoryDaoImpl();
		dao.addCategory(category);
	}

	//根据id查询分类
	public Category findByCid(String cid) {
		CategoryDao dao = new CategoryDaoImpl();
		Category cate = dao.findByCid(cid);
		return cate;
	}

	//根据id修改分类
	public void updateCategory(Category category) {
		CategoryDao dao = new CategoryDaoImpl();
		dao.updateCategory(category);
	}

	//根据商品的id把商品的id设置为null
	public void updateGoodsCid(List<Goods> goodlist) {
		for (Goods goods : goodlist) {
			GoodsDao dao = new GoodsDaoImpl();
			dao.updateGoodsCid(goods);
		}
		
	}

	//根据分类id进行删除
	public void deleteCategoryByCid(String cid) {
		CategoryDao dao = new CategoryDaoImpl();
		dao.deleteCategory(cid);
		
	}

}
