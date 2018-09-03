package com.jsp.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jsp.bean.User;
import com.jsp.utils.MyJDBCUtils;

public class UserDaoImpl implements UserDao {
	//�������Ʋ�ѯ�û�
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

		//����û������ݿ�
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

		//�����û����������ѯ�û��������û�״̬ status=1��
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

		//��ѯ���е��û�
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

		//����id��ѯ�û�
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

		//����id�޸��û�״̬
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
