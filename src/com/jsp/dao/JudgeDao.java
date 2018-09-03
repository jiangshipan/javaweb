package com.jsp.dao;

import java.util.List;

import com.jsp.bean.Judge;

public interface JudgeDao {
	//添加评价到数据库
	void addJudge(Judge jd);
	//查询评价
	List<Judge> findJudge(String gid);

}
