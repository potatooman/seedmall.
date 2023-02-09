package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.mapper.CartMapper;
import com.product.mapper.ProductMapper;
import com.product.model.CartVO;
import com.product.model.ProductVO;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	ProductMapper prodMapper;

	@Override
	public int insertCart(CartVO cart) {
		int n = cartMapper.existCart(cart);
		if(n>0) {
			return cartMapper.plusCart(cart);
		} else {
			return cartMapper.insertCart(cart);
		}
	}
	
	//카트 번호를 이용해 카트정보 가져오기
	public List<CartVO> getCartListByCidx(int[] cidxs){
		
		List<CartVO> cartArr = cartMapper.getCartListByCidx(cidxs);
		
		for(CartVO cart : cartArr) {
			ProductVO prod = prodMapper.selectByPidx(cart.getPidx());
			cart.setProduct(prod);
		}
		
		return cartArr;
	}
	
	@Override
	public int updateCart(CartVO cart) {
		return cartMapper.updateCart(cart);
	}

	@Override
	public List<CartVO> selectMyCartList(int midx) {
		return cartMapper.selectMyCartList(midx);
	}

	@Override
	public int deleteCart(int cart_idx) {
		return cartMapper.deleteCart(cart_idx);
	}

	@Override
	public int[] getCidxs(int midx) {
		return cartMapper.getCidxs(midx);
	}

}
