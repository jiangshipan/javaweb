package com.jsp.dao;

import java.util.List;

import com.jsp.bean.User;

public interface UserDao {
		//�������Ʋ�ѯ�û�
		User findByUserName(String username);
		//����û������ݿ�
		boolean addUser(User user1);
		//�����û����������ѯ�û��������û�״̬ status=1��
		User findUser(String username, String password);
		//��ѯ���е��û�
		List<User> findAllUser();
		//����id��ѯ�û�
		User findByUid(String uid);
		//�����û�id�޸��û�״̬
		void updateUserById(User user, String status);
}
