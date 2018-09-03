package com.jsp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.utils.TranJson;
/**
 * ͨ�õ�servlet��д
 * @author ��ʱ��
 *
 */
public class BaseServlet extends HttpServlet {
	/**
	 * �ύ���� /petstore/user?method=regist
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			String method = req.getParameter("method");
			//�ж����methodΪ��
			if(method == null) {
				method = "execute";
			}
			//�õ���ǰ�����servlet����ǰ���е���,�õ�UserServlet���Class
			Class clazz = this.getClass();
			//���ݵõ����ݹ��������ƣ������ƶ�Ӧ�ķ���ȥִ��
			Method m1 = clazz.getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			//�÷���ִ��
			String s = (String) m1.invoke(clazz.newInstance(), req,resp);
			if(s != null) {
				//ת������
				req.getRequestDispatcher(s).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

		
}

