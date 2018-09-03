package com.jsp.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jsp.bean.User;
import com.jsp.utils.MyJDBCUtils;

public class UserDaoImpl implements UserDao {
	//根据名称查询用户
		public User findByUserName(String username) {
			try {
				QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
				User user = runner.query("select * from pt_user where username=?", 
						new BeanHandler<User>(User.class), username);
				return user;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		//添加用户到数据库
		public boolean addUser(User user1) {
			try {
				QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
				Object[] os = {user1.getUid(),user1.getUsername(),user1.getPassword(),user1.getAddress(),user1.getStatus()};
				runner.update("insert into pt_user values(?,?,?,?,?)", os);
				return true;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		//根据用户名和密码查询用户（根据用户状态 status=1）
		public User findUser(String username, String password) {
			try {
				QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
				User user = runner.query("select * from pt_user where username=? and password=? and status=?", 
						new BeanHandler<User>(User.class), username,password,"1");
				return user;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		//查询所有的用户
		public List<User> findAllUser() {
			try {
				QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
				List<User> list = runner.query("select * from pt_user", 
						new BeanListHandler<User>(User.class));
				return list;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		//根据id查询用户
		public User findByUid(String uid) {
			try {
				QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
				User user = runner.query("select * from pt_user where uid=?", 
						new BeanHandler<User>(User.class), uid);
				return user;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		//根据id修改用户状态
		public void updateUserById(User user, String status) {
			try {
				QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
				runner.update("update pt_user set status=? where uid=?", 
						status,user.getUid());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
}
