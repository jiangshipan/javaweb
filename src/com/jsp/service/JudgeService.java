package com.jsp.service;

import java.util.List;

import com.jsp.bean.Judge;
import com.jsp.dao.JudgeDao;
import com.jsp.dao.JudgeDaoImpl;

/*
 * 实现评价
 */
public class JudgeService {
	//添加评价到数据库
	public void addJudge(Judge jd) {
		// TODO Auto-generated method stub
		JudgeDao dao = new JudgeDaoImpl();
		dao.addJudge(jd);
	}
	//查询评价
	public List<Judge> findJudge(String gid) {
		JudgeDao dao = new JudgeDaoImpl();
		List<Judge> list = dao.findJudge(gid);
		return list;
	}
	

}
