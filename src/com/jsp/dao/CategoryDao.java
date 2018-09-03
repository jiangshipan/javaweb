package com.jsp.dao;

import java.util.List;

import com.jsp.bean.Category;

public interface CategoryDao {

	//查询所有的分类
	List<Category> findAll();

	//增加分类
	void addCategory(Category category);

	//根据id查询分类
	Category findByCid(String cid);

	//根据id修改分类
	void updateCategory(Category category);

	//根据id删除分类
	void deleteCategory(String cid);

}
