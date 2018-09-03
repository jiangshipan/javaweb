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
	
	//�����û�id�޸��û�״̬
	public String updateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//�õ�Ҫ�޸��û���id
		String uid = req.getParameter("uid");
		//����uid��ѯ�û�
		UserService service = new UserService();
		User user = service.findByUid(uid);
		if(user == null) {
			req.setAttribute("msg", "�û�������");
			return "/adminjsps/msg.jsp";
		}
		//�õ���ǰ��״̬ 0�����ã� 1������
		//�ж�״̬�����0 ���޸�Ϊ1�����1���޸�Ϊ 0
		service.updateUserById(user);
		List<User> list = service.findAllUser();
		req.setAttribute("list", list);
		return "/adminjsps/admin/user/list.jsp";
	}
	
	//��̨�û�ģ��ʵ�ֲ�ѯ���е��û�
	public String findAllUser(HttpServletRequest req, HttpServletResponse resp) {
		//���÷���ʵ�ֲ�ѯ���е��û�
		UserService service = new UserService();
		List<User> list = service.findAllUser();
		req.setAttribute("list", list);
		return "/adminjsps/admin/user/list.jsp";
		
	}
	
	//����Ա��½
	public String adminLogin(HttpServletRequest req, HttpServletResponse resp) {
		String adminname = req.getParameter("adminname");
		String password = req.getParameter("password");
		//�ж��û����������Ƿ��� admin �� 123456
		if("admin".equals(adminname) && "123456".equals(password)) {
			req.getSession().setAttribute("admin", adminname+"#jsp#"+password);
			try {
				resp.sendRedirect(req.getContextPath()+"/adminjsps/admin/index.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			req.setAttribute("msg", "�û��������������");
			return "/adminjsps/login.jsp";
		}
		return null;
		}

	//ע��ķ���
	public String regist(HttpServletRequest req, HttpServletResponse resp) {
		/*
		 * 1����ȡ�ύ������
		 * 	* �ж����ݿ������Ƿ������ͬ���û��������������
		 * 2�����÷���ʵ�ֲ���
		 * */
		//����������������
		try {
			req.setCharacterEncoding("utf-8");
			//�ж��û����Ƿ����
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String address = req.getParameter("addrname");
			UserService service = new UserService();
			User user = service.findByName(username);
			//���user��Ϊ��
			if(user != null) {
				req.setAttribute("msg", "�û����Ѿ�����");
				return "/mainface/regist.jsp";		
			} else if(username!=null&&password!=null&&address!=null){
				//���û�������
				//�����ݷ�װ��user��������
				User user1 = new User();
				//�ֶ���װ
				String uid = UUID.randomUUID().toString();
				user1.setUid(uid);
				user1.setUsername(username);
				user1.setPassword(password);
				user1.setAddress(address);	
				user1.setStatus("1");
				
				//���÷���ʵ�����
				boolean flag = service.addUser(user1);
				if(flag) {
					req.setAttribute("msg","ע��ɹ���");
					return "/mainface/login.jsp";
					
				} else {
					req.setAttribute("msg","ע��ʧ�ܣ�");
					return "/mainface/regist.jsp";					
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//ʵ���û���¼�Ĳ���
	public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		/*
		 * ʵ����֤����ж�
		 * */
		resp.setContentType("text/html;charset=utf-8");
		//�õ��������֤
		String codeInput = req.getParameter("codename");
		//�õ�session�������֤��
		String codeSession = (String) req.getSession().getAttribute("code");
		//�Ƚ�
		System.out.println(codeInput+codeSession);
		if(!codeInput.equals(codeSession)) {//����ͬ
			req.setAttribute("msg", "��֤����������");
			return "/mainface/login.jsp";
		}	
		/*
		 * 1����ȡ������û���������
		 * 2�����÷����ж��û����������Ƿ���ȷ
		 * */
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//�ж��û��Ƿ����
		UserService service = new UserService();
		User user = service.findByName(username);
		if(user!=null){
			//���÷����ж��û���������
			User user1 = service.checkUser(username,password);
			if(user1 == null) {//��֤ʧ��
				req.setAttribute("msg", "�û��������������");
				return "/mainface/login.jsp";
			} else {
				//��user����ŵ�session����
				req.getSession().setAttribute("user", user);
				resp.sendRedirect("/petstore/index.jsp");
			}
		}
		return null;	
	}
	
	//ʵ���˳��Ĺ���
	public String exit(HttpServletRequest req, HttpServletResponse resp) {
		//����session
		HttpSession session = req.getSession();
		session.invalidate();
		return "/index.jsp";
	}
}

