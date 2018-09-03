package com.jsp.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.jsp.bean.User;
import com.jsp.service.UserService;
import com.jsp.utils.TranJson;

public class UserServlet extends BaseServlet {
	
	//根据用户id修改用户状态
	public String updateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//得到要修改用户的id
		String uid = req.getParameter("uid");
		//根据uid查询用户
		UserService service = new UserService();
		User user = service.findByUid(uid);
		if(user == null) {
			req.setAttribute("msg", "用户不存在");
			return "/adminjsps/msg.jsp";
		}
		//得到当前的状态 0，禁用； 1，启用
		//判断状态，如果0 ，修改为1，如果1，修改为 0
		service.updateUserById(user);
		List<User> list = service.findAllUser();
		req.setAttribute("list", list);
		return "/adminjsps/admin/user/list.jsp";
	}
	
	//后台用户模块实现查询所有的用户
	public String findAllUser(HttpServletRequest req, HttpServletResponse resp) {
		//调用方法实现查询所有的用户
		UserService service = new UserService();
		List<User> list = service.findAllUser();
		req.setAttribute("list", list);
		return "/adminjsps/admin/user/list.jsp";
		
	}
	
	//管理员登陆
	public String adminLogin(HttpServletRequest req, HttpServletResponse resp) {
		String adminname = req.getParameter("adminname");
		String password = req.getParameter("password");
		//判断用户名和密码是否是 admin 和 123456
		if("admin".equals(adminname) && "123456".equals(password)) {
			req.getSession().setAttribute("admin", adminname+"#jsp#"+password);
			try {
				resp.sendRedirect(req.getContextPath()+"/adminjsps/admin/index.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			req.setAttribute("msg", "用户名或者密码错误");
			return "/adminjsps/login.jsp";
		}
		return null;
		}

	//注册的方法
	public String regist(HttpServletRequest req, HttpServletResponse resp) {
		/*
		 * 1、获取提交的数据
		 * 	* 判断数据库里面是否存在相同的用户名，如果不存在
		 * 2、调用方法实现操作
		 * */
		//设置中文乱码问题
		try {
			req.setCharacterEncoding("utf-8");
			//判断用户名是否存在
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String address = req.getParameter("addrname");
			UserService service = new UserService();
			User user = service.findByName(username);
			//如果user不为空
			if(user != null) {
				req.setAttribute("msg", "用户名已经存在");
				return "/mainface/regist.jsp";		
			} else if(username!=null&&password!=null&&address!=null){
				//把用户完成添加
				//把数据封装到user对象里面
				User user1 = new User();
				//手动封装
				String uid = UUID.randomUUID().toString();
				user1.setUid(uid);
				user1.setUsername(username);
				user1.setPassword(password);
				user1.setAddress(address);	
				user1.setStatus("1");
				
				//调用方法实现添加
				boolean flag = service.addUser(user1);
				if(flag) {
					req.setAttribute("msg","注册成功！");
					return "/mainface/login.jsp";
					
				} else {
					req.setAttribute("msg","注册失败！");
					return "/mainface/regist.jsp";					
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//实现用户登录的操作
	public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		/*
		 * 实现验证码的判断
		 * */
		resp.setContentType("text/html;charset=utf-8");
		//得到输入的验证
		String codeInput = req.getParameter("codename");
		//得到session里面的验证码
		String codeSession = (String) req.getSession().getAttribute("code");
		//比较
		System.out.println(codeInput+codeSession);
		if(!codeInput.equals(codeSession)) {//不相同
			req.setAttribute("msg", "验证码输入有误");
			return "/mainface/login.jsp";
		}	
		/*
		 * 1、获取输入的用户名和密码
		 * 2、调用方法判断用户名和密码是否正确
		 * */
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//判断用户是否存在
		UserService service = new UserService();
		User user = service.findByName(username);
		if(user!=null){
			//调用方法判断用户名和密码
			User user1 = service.checkUser(username,password);
			if(user1 == null) {//验证失败
				req.setAttribute("msg", "用户名或者密码错误");
				return "/mainface/login.jsp";
			} else {
				//把user对象放到session里面
				req.getSession().setAttribute("user", user);
				resp.sendRedirect("/petstore/index.jsp");
			}
		}
		return null;	
	}
	
	//实现退出的功能
	public String exit(HttpServletRequest req, HttpServletResponse resp) {
		//销毁session
		HttpSession session = req.getSession();
		session.invalidate();
		return "/index.jsp";
	}
}

