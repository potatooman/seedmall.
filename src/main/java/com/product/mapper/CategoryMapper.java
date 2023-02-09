package com.product.mapper;

import java.util.List;

import com.product.model.CategoryVO;
//datasource-context.xml에 mybatis-spring:scan설정하면 XXXMapper인터페이스를 구현한 객체(Proxy)를 스프링이 대신 만들어준다
public interface CategoryMapper {
	
	public List<CategoryVO> getUpcategory();
	
	public List<CategoryVO> getDowncategory(String upCg_code);
	public List<CategoryVO> getCgDetail();
		
	public List<CategoryVO> getAllDowncategory();
	public List<CategoryVO> getAllCategory();

	public int categoryAdd(CategoryVO cvo);
	public int categoryDelete(int cg_code);

	public List<CategoryVO> getCgDetail(String downCg_code);

	public int deleteUpCategory(int n);

	public int deleteDownCategory(int n);

	public int deleteCgDetail(int n);
	
	public List<CategoryVO> getAllCgDetail();


}
