package com.board.model;

import java.sql.Date;
import java.util.List;

import com.product.model.ProductVO;

import lombok.Data;

@Data
public class QNADTO {
	private int qidx; //글번호
	private int pidx; //상품번호
	private int midx; //회원번호
	private String qcontent; //내용
	private String secret="N"; //비밀글 여부
	private String isCom="N"; // 답변 여부
	private Date qdate; //작성일
	
	private ProductVO product; // 문의한 상품 객체
	
	private String email; //작성자 이메일
	private List<QNA_ReDTO> qna_ReList; //QNA 댓글 리스트
}
