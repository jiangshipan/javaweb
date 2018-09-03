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
	//增加商品评价
	public String addJudge(HttpServletRequest req, HttpServletResponse resp) {
		//获取传递过来的gid和judge
		String gid = req.getParameter("gid");
		String textarea = req.getParameter("textarea");
		//封装评价
		if(gid!=null&textarea!=null){
			Judge jd = new Judge();
			jd.setGid(gid);
			jd.setJudge(textarea);
			//调用方法实现添加评价
			JudgeService service = new JudgeService();
			service.addJudge(jd);
		}
		return "/order?method=findAllOrder";
		
	}
	//查询商品评价
	public String findJudge(HttpServletRequest req, HttpServletResponse resp) {
		//得到传递过来的gid
		String gid = req.getParameter("gid");
		//调用方法查询评价
		if(gid!=null){
			JudgeService service = new JudgeService();
			List<Judge> list = service.findJudge(gid);
			req.setAttribute("list", list);
			return "/other/showjudge.jsp";
		}
		return null;
	}
}
