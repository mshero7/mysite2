package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;
	
	public List<BoardVo> getList() {
		return boardDao.getList();
	}

	public BoardVo getView(BoardVo vo) {
		return boardDao.getView(vo);
	}
	
	public Boolean insertAnswerView(BoardVo vo) {
		
		BoardVo parentBoardVo = getView(vo);
		
		BoardVo childBoardVo = new BoardVo();
		System.out.println("parentBoardVo : "+ parentBoardVo);
		System.out.println("childBoardVo : "+ childBoardVo);
		
		childBoardVo.setGroup_no(parentBoardVo.getGroup_no());
		childBoardVo.setOrder_no(parentBoardVo.getOrder_no());
		childBoardVo.setDepth(parentBoardVo.getDepth());
		
		return boardDao.insertView(childBoardVo);
	}
	
	public Boolean insertView(BoardVo vo) {
		vo.setGroup_no(1);
		vo.setOrder_no(1);
		vo.setDepth(0);
		return boardDao.insertView(vo);
	}

	public boolean deleteView(BoardVo vo) {
		return boardDao.deleteView(vo);
	}

	public boolean modifyView(BoardVo vo) {
		return boardDao.modifyView(vo);
	}

	public List<BoardVo> getList(String keyword) {
		return boardDao.getList(keyword);
	}

}
