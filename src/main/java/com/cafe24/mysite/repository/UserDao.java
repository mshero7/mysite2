package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public UserDao() {
		System.out.println("UserDao Constructor!!");
	}
	
	public UserVo get(Long no) {
		StopWatch sw = new StopWatch();
		
		sw.start();
		UserVo vo = sqlSession.selectOne("user.getByNo",no);
		sw.stop();
		
		Long totalTime = sw.getTotalTimeMillis();
		System.out.println(totalTime);
		
		return vo;
	}

	public UserVo get(String email) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("email",email);
		UserVo userVo = sqlSession.selectOne("user.getByEmail", map);
		
		return userVo;
	}
	
	public UserVo get(String email, String password) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("email",email);
		map.put("password",password);
		
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		
		return userVo;
	}

	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);

		return 1 == count;
	}
}
