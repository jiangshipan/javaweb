package com.jsp.service;

import java.util.List;

import com.jsp.bean.Judge;
import com.jsp.dao.JudgeDao;
import com.jsp.dao.JudgeDaoImpl;

/*
 * ʵ������
 */
public class JudgeService {
	//������۵����ݿ�
	public void addJudge(Judge jd) {
		// TODO Auto-generated method stub
		JudgeDao dao = new JudgeDaoImpl();
		dao.addJudge(jd);
	}
	//��ѯ����
	public List<Judge> findJudge(String gid) {
		JudgeDao dao = new JudgeDaoImpl();
		List<Judge> list = dao.findJudge(gid);
		return list;
	}
	

}
