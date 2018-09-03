package com.jsp.service;

import java.util.List;

import com.jsp.bean.User;
import com.jsp.dao.UserDao;
import com.jsp.dao.UserDaoImpl;

public class UserService {
	
	//根据名称查询用户
		public User findByName(String username) {
			UserDao dao = new UserDaoImpl();
			User user = dao.findByUserName(username);
			return user;
		}
		
		//添加用户
		public boolean addUser(User user1) {
			UserDao dao = new UserDaoImpl();
			boolean flag = dao.addUser(user1);
			return flag;
		}

		//根据用户名和密码查询用户（根据用户状态 status=1）
		public User checkUser(String username, String password) {
			UserDao dao = new UserDaoImpl();
			User user = dao.findUser(username,password);
			return user;
		}

		//查询所有的用户
		public List<User> findAllUser() {
			UserDao dao = new UserDaoImpl();
			List<User> list = dao.findAllUser();
			return list;
		}

		//根据id查询用户
		public User findByUid(String uid) {
			UserDao dao = new UserDaoImpl();
			User user = dao.findByUid(uid);
			return user;
		}

		//根据id修改用户状态
		public void updateUserById(User user) {
			UserDao dao = new UserDaoImpl();
			//判断如果状态 0，传递 1
			if("1".equals(user.getStatus())) {
				dao.updateUserById(user,"0");
			} else {
				dao.updateUserById(user,"1");
			}
		}

}
