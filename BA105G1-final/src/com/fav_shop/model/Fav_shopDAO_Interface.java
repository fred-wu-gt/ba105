package com.fav_shop.model;

import java.util.List;


@SuppressWarnings("unused")
public interface Fav_shopDAO_Interface {

	void add(Fav_shopVO fav_shopVO);
	void delete(String mem_no,String shop_no);
	public List<Fav_shopVO> findByMem_no(String mem_no);
	public List<Fav_shopVO> findByShop_no(String shop_no);
	public Fav_shopVO findByFK(String shop_no,String mem_no);
	public List<Fav_shopVO> getAll();
	
}
