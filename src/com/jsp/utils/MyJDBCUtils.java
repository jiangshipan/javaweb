package com.jsp.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyJDBCUtils {
	public static ComboPooledDataSource ds = new ComboPooledDataSource();
	//�õ����ݿ�����ӷ���
	public static Connection getConnection1() throws Exception {
		//�õ�����
		Connection conn = ds.getConnection();
		return conn;
	}
	//����datasource
	public static DataSource getDataSource() {
		return ds;
	}
}
