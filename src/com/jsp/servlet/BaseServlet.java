package com.jsp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.utils.TranJson;
/**
 * 通用的servlet编写
 * @author 江时盼
 *
 */
public class BaseServlet extends HttpServlet {
	/**
	 * 提交请求 /petstore/user?method=regist
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			String method = req.getParameter("method");
			//判断如果method为空
			if(method == null) {
				method = "execute";
			}
			//得到当前请求的servlet，当前运行的类,得到UserServlet类的Class
			Class clazz = this.getClass();
			//根据得到传递过来的名称，让名称对应的方法去执行
			Method m1 = clazz.getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			//让方法执行
			String s = (String) m1.invoke(clazz.newInstance(), req,resp);
			if(s != null) {
				//转发操作
				req.getRequestDispatcher(s).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

		
}

