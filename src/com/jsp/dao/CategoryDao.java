package com.jsp.dao;

import java.util.List;

import com.jsp.bean.Category;

public interface CategoryDao {

	//��ѯ���еķ���
	List<Category> findAll();

	//���ӷ���
	void addCategory(Category category);

	//����id��ѯ����
	Category findByCid(String cid);

	//����id�޸ķ���
	void updateCategory(Category category);

	//����idɾ������
	void deleteCategory(String cid);

}
