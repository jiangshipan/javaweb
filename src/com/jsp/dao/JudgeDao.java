package com.jsp.dao;

import java.util.List;

import com.jsp.bean.Judge;

public interface JudgeDao {
	//������۵����ݿ�
	void addJudge(Judge jd);
	//��ѯ����
	List<Judge> findJudge(String gid);

}
