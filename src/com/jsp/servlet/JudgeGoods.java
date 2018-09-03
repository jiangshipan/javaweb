package com.jsp.servlet;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.bean.Judge;
import com.jsp.service.JudgeService;

public class JudgeGoods extends BaseServlet {
	//������Ʒ����
	public String addJudge(HttpServletRequest req, HttpServletResponse resp) {
		//��ȡ���ݹ�����gid��judge
		String gid = req.getParameter("gid");
		String textarea = req.getParameter("textarea");
		//��װ����
		if(gid!=null&textarea!=null){
			Judge jd = new Judge();
			jd.setGid(gid);
			jd.setJudge(textarea);
			//���÷���ʵ���������
			JudgeService service = new JudgeService();
			service.addJudge(jd);
		}
		return "/order?method=findAllOrder";
		
	}
	//��ѯ��Ʒ����
	public String findJudge(HttpServletRequest req, HttpServletResponse resp) {
		//�õ����ݹ�����gid
		String gid = req.getParameter("gid");
		//���÷�����ѯ����
		if(gid!=null){
			JudgeService service = new JudgeService();
			List<Judge> list = service.findJudge(gid);
			req.setAttribute("list", list);
			return "/other/showjudge.jsp";
		}
		return null;
	}
}
