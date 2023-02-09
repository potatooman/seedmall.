package com.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.board.model.BoardReviewVO;
import com.board.model.ReviewImageVO;
import com.user.model.PagingVO;

public interface BoardReviewService {
public List<BoardReviewVO> getReview(int pidx);
	
	public int insertReview(BoardReviewVO review, HttpServletRequest req);
	
	BoardReviewVO getReviewVO(BoardReviewVO review);
	
	int getReviewCount(PagingVO paging);
	
	public int updateReview(BoardReviewVO review);
	
	public int deleteReview(int ridx);
	
	int updateReadnum(Integer ridx);
	
	BoardReviewVO selectBoardByIdx(Integer ridx);
	
	//내가 작성한 리뷰 가져오기
	List<BoardReviewVO> selectReviewByMidx(Integer midx);
	
	//사진리뷰 top 가져오기
	List<BoardReviewVO> getReviewListTop(int top);
	
	int like_check(BoardReviewVO review);
	
	int like_check_cancel(BoardReviewVO review);
	
	List<ReviewImageVO> getReviewImages(Integer ridx);
	
	public int insertImage(ReviewImageVO riVo);
	
	public ReviewImageVO uploadImage(MultipartFile mf, HttpServletRequest req);
}
