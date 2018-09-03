package com.jsp.service;

import java.util.List;

import com.jsp.bean.User;
import com.jsp.dao.UserDao;
import com.jsp.dao.UserDaoImpl;

public class UserService {
	
	//�������Ʋ�ѯ�û�
		public User findByName(String username) {
			UserDao dao = new UserDaoImpl();
			User user = dao.findByUserName(username);
			return user;
		}
		
		//����û�
		public boolean addUser(User user1) {
			UserDao dao = new UserDaoImpl();
			boolean flag = dao.addUser(user1);
			return flag;
		}

		//�����û����������ѯ�û��������û�״̬ status=1��
		public User checkUser(String username, String password) {
			UserDao dao = new UserDaoImpl();
			User user = dao.findUser(username,password);
			return user;
		}

		//��ѯ���е��û�
		public List<User> findAllUser() {
			UserDao dao = new UserDaoImpl();
			List<User> list = dao.findAllUser();
			return list;
		}

		//����id��ѯ�û�
		public User findByUid(String uid) {
			UserDao dao = new UserDaoImpl();
			User user = dao.findByUid(uid);
			return user;
		}

		//����id�޸��û�״̬
		public void updateUserById(User user) {
			UserDao dao = new UserDaoImpl();
			//�ж����״̬ 0������ 1
			if("1".equals(user.getStatus())) {
				dao.updateUserById(user,"0");
			} else {
				dao.updateUserById(user,"1");
			}
		}

}
