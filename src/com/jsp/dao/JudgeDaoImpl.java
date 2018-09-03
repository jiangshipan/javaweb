package com.jsp.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jsp.bean.Judge;
import com.jsp.utils.MyJDBCUtils;

public class JudgeDaoImpl implements JudgeDao {
	//添加评价到数据库
	public void addJudge(Judge jd) {
		try{
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			Object[] os = {jd.getGid(),jd.getJudge()};
			runner.update("insert into judge values(?,?)",os);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public List<Judge> findJudge(String gid) {
		try{
			QueryRunner runner = new QueryRunner(MyJDBCUtils.getDataSource());
			List<Judge> list = runner.query("select * from judge where gid=?",new BeanListHandler<Judge>(Judge.class),gid);
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
