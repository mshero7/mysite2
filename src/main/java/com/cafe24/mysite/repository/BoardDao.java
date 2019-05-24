package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVo> getList(){
		return sqlSession.selectList("board.getList");
	}

	public BoardVo getView(BoardVo vo) {
		return sqlSession.selectOne("board.getView",vo);
	}

	public Boolean insertView(BoardVo vo) {
		return 1==sqlSession.insert("board.insertView",vo);
	}

	public boolean deleteView(BoardVo vo) {
		return 1==sqlSession.delete("board.deleteView",vo);
	}

	public boolean modifyView(BoardVo vo) {
		return 1==sqlSession.update("board.modifyView",vo);
	}

	public List<BoardVo> getList(String keyword) {
		return sqlSession.selectList("board.getListByKeyowrd",keyword);
	}
}
