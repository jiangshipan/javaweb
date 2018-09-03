package com.jsp.dao;

import java.util.List;

import com.jsp.bean.User;

public interface UserDao {
		//根据名称查询用户
		User findByUserName(String username);
		//添加用户到数据库
		boolean addUser(User user1);
		//根据用户名和密码查询用户（根据用户状态 status=1）
		User findUser(String username, String password);
		//查询所有的用户
		List<User> findAllUser();
		//根据id查询用户
		User findByUid(String uid);
		//根据用户id修改用户状态
		void updateUserById(User user, String status);
}
