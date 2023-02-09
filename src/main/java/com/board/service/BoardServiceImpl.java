package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.mapper.BoardComMapper;
import com.board.model.BoardComVO;
import com.user.model.PagingVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardComMapper boardComMapper;
	
	@Override
	public List<BoardComVO> getBoardList(PagingVO paging) {
		return boardComMapper.getBoardList(paging);
	}

	@Override
	public int getBoardCount(PagingVO paging) {
		return boardComMapper.getBoardCount(paging);
	}

}
