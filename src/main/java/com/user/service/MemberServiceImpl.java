package com.user.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.mapper.MemberMapper;
import com.user.model.GradeVO;
import com.user.model.MemberVO;
import com.user.model.NotUserException;
import com.user.model.PagingVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberMapper MemberMapper;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@Override
	public int createMember(MemberVO Member) {

		return MemberMapper.createMember(Member);
	}

	@Override
	public int getMemberCount(PagingVO pvo) {
		return MemberMapper.getMemberCount(pvo);
	}

	@Override
	public List<MemberVO> listUser(PagingVO pvo) {
		return MemberMapper.listUser(pvo);
	}

	

	@Override
	public int deleteMember(Integer midx) {
		return MemberMapper.deleteUser(midx);
	}

	@Override
	public int updateMember(MemberVO member) {
		return MemberMapper.updateUser(member);
	}
	
	@Override
	public int updateMemberInfo(MemberVO member) {
		return MemberMapper.updateMemberInfo(member);
	}

	@Override
	public MemberVO getMember(Integer midx) {
		return MemberMapper.getMember(midx);
	}

	@Override
	public MemberVO findUser(MemberVO findUser) throws NotUserException {
		MemberVO user = MemberMapper.findUser(findUser);
		if (user == null) {
			throw new NotUserException("존재하지 않는 이메일입니다!");
		}
		return user;
	}

	@Override
	public MemberVO loginCheck(String email, String pwd) throws NotUserException {
		MemberVO tmpVo = new MemberVO();
		tmpVo.setEmail(email);

		MemberVO member = this.findUser(tmpVo);
		if (member == null) {
			throw new NotUserException("존재하지 않는 이메일입니다.");
		}
		if (!bcryptPasswordEncoder.matches(pwd,member.getPwd())) {
			throw new NotUserException("비밀번호가 일치하지 않습니다.");
		}
		if(member.getEnabled()==0) {
			throw new NotUserException("정지 또는 탈퇴한 회원입니다.");
		}

		return member;

	}

	@Override
	public void logout(HttpSession session) throws Exception {
		session.invalidate();

	}
	// 게시목록 가져오기
	@Override
	public List<MemberVO> selectMemberAll(Map<String,Integer> map){
		return this.MemberMapper.selectMemberAll(map);
	}
	@Override
	public List<MemberVO> selectMemberAllPaging(PagingVO pvo){
		return this.MemberMapper.selectMemberAllPaging(pvo);
	}
	   
	// 검색목록 가져오기
	@Override
	public List<MemberVO> findMember(PagingVO pvo){
		return this.MemberMapper.findMember(pvo);
	}

	@Override
	public boolean emailCheck(String email) {
		Integer n= MemberMapper.emailCheck(email);
		if(n==null) {
			return true;
		}
		return false;
	}

	@Override
	public MemberVO findemail(MemberVO vo) {
		
		return MemberMapper.findemail(vo);
	}

	@Override
	public MemberVO findPassword(MemberVO vo) {
		return MemberMapper.findPassword(vo);
	}

	@Override
	public int updatePassword(MemberVO vo) {
		
		return MemberMapper.updatePassword(vo);
	}
	
	@Override
	public int RewardMileage(int midx, int point) {
		return this.MemberMapper.RewardMileage(midx, point);
	}

	@Override
	public int updateGrade(MemberVO mvo) {
		int mileage= MemberMapper.getMileage(mvo.getMidx());
		String grade= MemberMapper.getGrade(mileage);
		mvo.setGrade(grade);
		log.info("mvo="+mvo);
		return this.MemberMapper.updateGrade(mvo);
	}
	
	@Override
	public GradeVO getDrate(MemberVO mvo) {
		return MemberMapper.getDrate(mvo);
	}
	
	@Override
	public GradeVO getNextGrade(MemberVO mvo) {
		return MemberMapper.getNextGrade(mvo);
	}


}
