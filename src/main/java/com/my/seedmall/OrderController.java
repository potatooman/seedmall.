package com.my.seedmall;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myplant.model.MyPlantVO;
import com.myplant.service.MyPlantService;
import com.order.mapper.OrderMapper;
import com.order.model.OrderProductVO;
import com.order.model.OrderVO;
import com.order.service.OrderService;
import com.product.model.ProductVO;
import com.product.service.ProductService;
import com.user.mapper.MemberMapper;
import com.user.model.GradeVO;
import com.user.model.MemberVO;
import com.user.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private ProductService productService;

	@Autowired
	private MyPlantService myPlantService;
	
	@Autowired
	private MemberService memberService;

	@Autowired
	MemberMapper memberMapper;

	// 결제 전, 결제정보 출력
	@PostMapping("/order")
	public String order(Model m, @RequestParam("pidx") int pidxs[], @RequestParam("oqty") int oqty,
			@RequestParam(value = "growCheck", defaultValue = "N") String growCheck, Principal pr,
			@ModelAttribute OrderProductVO opvo) {
		MemberVO loginUser = memberMapper.read(pr.getName());
		
		GradeVO grade = memberService.getDrate(loginUser);
		
		int total = 0, totalPayment = 0;
		List<ProductVO> orderArr = new ArrayList<>();
		if (pidxs != null) {
			for (int i = 0; i < pidxs.length; i++) {
				// 받아온 상품번호를 차례대로 임시변수에 저장해서 List에 추가할 것
				int pidx = pidxs[i];

				// 상품번호로 해당 상품 정보 가져오기
				ProductVO pvo = productService.selectByIdx(pidx);

				// ProductVO에서 재고수량으로 사용되던 pqty를 사용자가 선택한 수량(oqty)으로 덮어쓴다 -> 상품 정보를 세션으로 넘겨야하기
				// 때문
				pvo.setPqty(oqty);
				orderArr.add(pvo); // List에 추가

				// 주문VO 셋팅
				opvo.setPidx(pidx);
				opvo.setOqty(oqty);
				opvo.setOsalePrice(pvo.getPsaleprice());

				// 주문상품 총액(등급할인 전)
				total += (pvo.getPsaleprice() * opvo.getOqty());

				
			}
		}
		// 총 주문금액(등급할인 후)
		totalPayment = (int) (total * (1-(grade.getDrate()*0.01)));
		
		log.info("totalPayment = "+totalPayment);
		
		totalPayment += 4000; // 배송비

		m.addAttribute("oqty", opvo.getOqty());
		m.addAttribute("total", total);
		m.addAttribute("totalPayment", totalPayment);
		m.addAttribute("orderArr", orderArr);
		m.addAttribute("opvo", opvo);
		m.addAttribute("growCheck", growCheck);
		m.addAttribute("grade", grade);
		

		return "order/orderDetail";
	}

	// 결제완료 페이지 - 주문 명세서 생성
	@PostMapping("/orderAdd")
	public String orderAdd(Model m, @ModelAttribute("ovo") OrderVO ovo,
			@RequestParam(value = "growCheck", defaultValue = "N") String growCheck, Principal pr) {
		MemberVO loginUser = memberMapper.read(pr.getName());
		int midx_fk = loginUser.getMidx();

		log.info("ovo = " + ovo);

		// 세션에 로그인한 사람 정보로 회원번호 셋팅
		ovo.setMidx(midx_fk);
		
		// 키워주세요 체크 시
		if(growCheck.equals("Y")) {
			ovo.setDeliveryState("5");
		} else {
			ovo.setDeliveryState("0");
		}
		
		ProductVO pvo = productService.selectByIdx(ovo.getPidx());
		pvo.setPqty(ovo.getOqty());
		int totalPoint = pvo.getTotalPoint();
		ovo.setGradePoint(totalPoint);
		
		// 주문 명세서 + 수령자 DB에 생성
		int n = orderService.createOrderList(ovo);
		int n2 = orderMapper.createOrderMember(ovo); // Mapper에서는 명세서와 수령자를 따로 insert해줬다

		
		// 주문개요 DB에 생성
		// 주문 상품정보에 해당 주문개요번호를 넣는다
		OrderProductVO opvo = orderService.getOrderProduct(ovo.getPidx());

		// 그리고 OrderProductVO에 값을 넣어준다
		opvo.setDesc_oidx(ovo.getDesc_oidx());
		opvo.setOqty(pvo.getPqty()); // 선택한 상품 수량
		opvo.setOsalePrice(pvo.getPsaleprice());
		opvo.setOpoint(pvo.getPpoint());

		int n3 = orderMapper.createOrderProductList(opvo);

		// 그 수량만큼 for문을 돌려서 키워주세요를 체크한 상품을 찾아 한 건씩 new해준다
		if (growCheck.equals("Y")) {
			for (int i = 0; i < opvo.getOqty(); i++) {
				MyPlantVO mpvo = new MyPlantVO();
				mpvo.setMidx(ovo.getMidx());
				mpvo.setOidx(opvo.getOidx());
				mpvo.setNickname("이름을 정해주세요");
				mpvo.setPcomment("관리자 코멘트");
				mpvo.setPercent(0);
				mpvo.setPlantImage("noimage.png");

				int n4 = myPlantService.insertMyPlant(mpvo);
			}
		}

		// 총 주문정보 가져오기(명세서, 수령자 + 주문개요)
		OrderVO orderDesc = orderService.getOrderDesc(ovo.getDesc_oidx());
		List<OrderProductVO> orderProductArr = orderService.getOrderProductList(ovo.getDesc_oidx());
		
		m.addAttribute("desc_oidx", ovo.getDesc_oidx());
		m.addAttribute("orderDesc", orderDesc);
		m.addAttribute("orderProductArr", orderProductArr);
		log.info("m="+m);

		return "redirect:orderResult"; // 새로고침 시 또 주문이 들어가기때문에 redirect처리 해줘야 함
	}

	@GetMapping("/orderResult")
	public String orderAddResult(Model m, OrderVO ovo) {

		int desc_oidx=ovo.getDesc_oidx();
		log.info("desc_oidx="+desc_oidx);
		 
		 // 총 주문정보 가져오기(명세서, 수령자, 주문개요) OrderVO orderDesc =
		OrderVO orderDesc = orderService.getOrderDesc(desc_oidx); 
		OrderVO orderMember = orderService.getOrderMember(desc_oidx); 
		List<OrderProductVO> orderProductArr = orderService.getOrderProductList(desc_oidx);
		log.info("orderDesc="+orderDesc);
		log.info("orderProductArr="+orderProductArr);
		log.info("orderMember="+orderMember);
		m.addAttribute("orderDesc", orderDesc); 
		m.addAttribute("orderProductArr", orderProductArr); 
		m.addAttribute("orderMember", orderMember); 
		 
		log.info("resultm=" + m);

		return "order/orderEnd";
	}

}
