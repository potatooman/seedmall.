package com.my.seedmall;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.model.BoardComVO;
import com.board.model.BoardReviewVO;
import com.board.model.QNADTO;
import com.board.service.BoardComService;
import com.board.service.BoardReviewService;
import com.board.service.QNAService;
import com.myplant.model.MyPlantVO;
import com.myplant.service.MyPlantService;
import com.order.model.OrderProductVO;
import com.order.model.OrderVO;
import com.order.service.OrderService;
import com.seedmall.security.CustomUserDetailsService;
import com.user.mapper.MemberMapper;
import com.user.model.GradeVO;
import com.user.model.MemberVO;
import com.user.model.NotUserException;
import com.user.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/user/mypage")
public class MypageController {
   @Autowired
   MemberService memberService;
   
   @Autowired
   OrderService orderService;
   
   @Autowired
   MyPlantService mpService;
   
   @Autowired
   QNAService qnaService;
   
   @Autowired
   BoardReviewService reviewService;
   
   @Autowired
   BoardComService boardComService;
   
   @Autowired
   MemberMapper memberMapper;
   
   @Autowired
   BCryptPasswordEncoder bCryptPasswordEncoder;
   
   @GetMapping()
   public String mypage(Model m, Principal pr) {
      
	  MemberVO loginUser = memberMapper.read(pr.getName());
      
      GradeVO nextGrade = memberService.getNextGrade(loginUser);
      
      m.addAttribute("nextGrade", nextGrade);
      
      return "member/mypage";
   }
   
   // ?????? ?????? ?????? ???
   @GetMapping("/infoForm")
   public String myinfoForm() {
      return "member/mypageEditForm";
   }
   
   @PostMapping("/updateInfo")
   public String updateMemberInfo(@ModelAttribute MemberVO member) {
      int result =memberService.updateMemberInfo(member);
      
      if(result>0) {
    	  
    	  return "member/mypage";
      }
      return "member/mypageEditForm";
      
      
   }
   
   //???????????? ?????? ???
   
   @GetMapping("/editPassForm")
   public String editPasswordForm() {
      return "member/passEditForm";
   }
   
   @PostMapping("/updatePass")
   public String updatePassword(Model m,@ModelAttribute MemberVO updateMember, @RequestParam("password2") String newPwd, Principal pr) 
		   throws NotUserException {
      
      log.info("updateMember ="+updateMember);
      log.info("newPwd ="+newPwd);
      
      //service ????????? ?????? ??? ??????
      
      MemberVO findMember = memberService.findUser(updateMember);
      if(!bCryptPasswordEncoder.matches(updateMember.getPwd(), findMember.getPwd())) {
         m.addAttribute("message", "??????????????? ???????????? ????????????.");
         m.addAttribute("loc", "editPassForm");
         return "msg";
      }
      // ??? ??????????????? set
      updateMember.setPwd(bCryptPasswordEncoder.encode(newPwd));
      
      // ???????????? ??????
      memberService.updatePassword(updateMember);
      
      return "redirect:/user/mypage"; 
   }
   
   
   
   //?????? ??????
   @GetMapping("/orderList")
   public String myOrderList(Model m, Principal pr) {
	  MemberVO loginUser = memberMapper.read(pr.getName());
      List<OrderVO> orderArr = orderService.getOrderList(loginUser.getMidx());
      for(OrderVO order : orderArr) {
         List<OrderProductVO> orderProduct = orderService.getOrderProductList(order.getDesc_oidx());
         order.setProdList(orderProduct);
      }
      
      m.addAttribute("orderArr", orderArr);
      
      return "member/orderList";
   }
   
   @PostMapping("/order")
   public String getOrderDetail(Model m, HttpSession ses, @RequestParam("oidx") int oidx ) {
      
      log.info("oidx = "+oidx);
      OrderVO order = orderService.getOrderDesc(oidx);
      OrderVO orderMember = orderService.getOrderMember(oidx);
      
      //?????? ????????? ??????????????? ???????????? ??????
      List<OrderProductVO> orderProduct = orderService.getOrderProductList(oidx);
      
      m.addAttribute("order", order);
      m.addAttribute("orderMember", orderMember);
      m.addAttribute("orderProduct", orderProduct);
      
      return "member/orderDetail";
   }
   
   //?????? ??????
   @PostMapping(value="/orderEnd", produces = "application/json")
   @ResponseBody
   public int orderConfirmed(int oidx, HttpSession ses) {
      log.info("oidx = "+oidx);
      int result = orderService.orderConfirmed(oidx);
      
		/*
		 * if(result>0) { MemberVO loginUser = (MemberVO)ses.getAttribute("loginUser");
		 * MemberVO findUser = memberService.getMember(loginUser.getMidx());
		 * ses.setAttribute("loginUser", findUser); }
		 */
      
      return result;
   }
   
   
   //?????? ??????
   @GetMapping("/plantList")
   public String myPlantList(Model m, Principal pr) {
      
	  MemberVO loginUser = memberMapper.read(pr.getName());
      
      List<MyPlantVO> plantArr = mpService.getMyPlantList(loginUser.getMidx());
      
      m.addAttribute("plantArr", plantArr);
      
      return "member/plantList";
   }
   
   //?????? ????????????
   @PostMapping("/plant")
   public String getPlantDetail(Model m, HttpSession ses, @RequestParam("pidx") int pidx) {
      
      log.info("pidx = "+pidx);
      MyPlantVO plant = mpService.getMyPlantDetail(pidx);
      
      m.addAttribute("plant", plant);
      
      return "member/plantDetail";
   }
   
   //?????? ?????? ??????
   @PostMapping("/updateNick")
   @ResponseBody
   public int updateNickname(MyPlantVO plant) {
      
      log.info("plant="+plant);
      int result = mpService.updateMyPlantNickname(plant);
      
      return result;
   }
   
   //QNA ?????????
   @GetMapping("/QNA")
   public String getQnAList(Model m, Principal pr) {
	  MemberVO loginUser = memberMapper.read(pr.getName());
      
      List<QNADTO> qArr = qnaService.getQNAListByMidx(loginUser.getMidx());
      
      m.addAttribute("qArr", qArr);
      
      return "member/QNAList";
      
   }
   
   // ?????? ?????? ?????? ???
   @PostMapping("/orderCancel")
   public String orderCancel(@ModelAttribute OrderVO ovo) {
      int n = orderService.updatedeliverystate(ovo);
      
      return "redirect:/user/mypage"; 
   }
   
   //?????? ????????? ?????? ?????????
   @GetMapping("/reviewList")
   public String getReviewList(Model m, Principal pr) {
	   MemberVO loginUser = memberMapper.read(pr.getName());
      List<BoardReviewVO> reviewArr = reviewService.selectReviewByMidx(loginUser.getMidx());
      
      m.addAttribute("reviewArr", reviewArr);
      
      return "member/reviewList";
      
   }
   
   //?????? ????????? ??? ?????????
   @GetMapping("/communityList")
   public String getComList(Model m, Principal pr) {
	   MemberVO loginUser = memberMapper.read(pr.getName());
      List<BoardComVO> boardComArr = boardComService.selectComByMidx(loginUser.getMidx());
      m.addAttribute("boardComArr", boardComArr);
      
      return "member/communityList";
   }
      
}
