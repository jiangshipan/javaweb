package com.jsp.service;

import java.util.List;

import com.jsp.bean.Goods;
import com.jsp.bean.Category;
import com.jsp.dao.GoodsDao;
import com.jsp.dao.GoodsDaoImpl;
import com.jsp.dao.CategoryDao;
import com.jsp.dao.CategoryDaoImpl;

public class CategoryService {

	//��ѯ���еķ���
	public List<Category> findAllCategory() {
		CategoryDao dao = new CategoryDaoImpl();
		List<Category> list = dao.findAll();
		return list;
	}

	//���ӷ���
	public void addCategory(Category category) {
		CategoryDao dao = new CategoryDaoImpl();
		dao.addCategory(category);
	}

	//����id��ѯ����
	public Category findByCid(String cid) {
		CategoryDao dao = new CategoryDaoImpl();
		Category cate = dao.findByCid(cid);
		return cate;
	}

	//����id�޸ķ���
	public void updateCategory(Category category) {
		CategoryDao dao = new CategoryDaoImpl();
		dao.updateCategory(category);
	}

	//������Ʒ��id����Ʒ��id����Ϊnull
	public void updateGoodsCid(List<Goods> goodlist) {
		for (Goods goods : goodlist) {
			GoodsDao dao = new GoodsDaoImpl();
			dao.updateGoodsCid(goods);
		}
		
	}

	//���ݷ���id����ɾ��
	public void deleteCategoryByCid(String cid) {
		CategoryDao dao = new CategoryDaoImpl();
		dao.deleteCategory(cid);
		
	}

}
