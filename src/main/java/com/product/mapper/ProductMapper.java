package com.product.mapper;

import java.util.List;

import com.product.model.CategoryVO;
import com.product.model.PagingVO;
import com.product.model.ProductForm;
import com.product.model.ProductImageVO;
import com.product.model.ProductVO;
import com.product.model.TopProdDTO;

public interface ProductMapper {

	int productInsert(ProductForm vo);
	
	int getProdCount(PagingVO page);

	List<ProductVO> getProducts(PagingVO page);
	
	List<ProductVO> getProductsPaging(PagingVO page);

	List<ProductImageVO> getProdImages(int pidx);

	List<ProductVO> selectByPspec(String pspec);

	List<ProductVO> selectByCategory(CategoryVO cvo);

	ProductVO selectByPidx(int pidx);
	
	List<TopProdDTO> getProdListTop(int n);

	int insertPimage(ProductImageVO piVo);

	int deleteProduct(int pidx);

	int deleteImage(int pidx);

	int updateProduct(ProductForm prod);

	int insertUpcg(CategoryVO cvo);

	int insertDowncg(CategoryVO cvo);

	int addDetail(CategoryVO cvo);
	
	//주문 상품 번호를 이용해서 상품 가져오기
	ProductVO selectByOidx(int oidx);
	
	//상품 좋아요
	int prodLike(int pidx);
	
	//상품 조회수 up
	int prodRead(int pidx);

}
