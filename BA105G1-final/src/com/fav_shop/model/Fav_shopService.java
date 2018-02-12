package com.fav_shop.model;

import java.util.List;



public class Fav_shopService {

	private Fav_shopDAO_Interface dao;
	
	
	public Fav_shopService(){
		dao = new Fav_shopDAO();
	}
	
	public Fav_shopVO addFav_shop(String mem_no, String shop_no){
		Fav_shopVO fav_shopVO = new Fav_shopVO();
		
		fav_shopVO.setMem_no(mem_no);
		fav_shopVO.setShop_no(shop_no);
		dao.add(fav_shopVO);
		System.out.println("新增資料成功!!");
		
		return fav_shopVO;
		
	}
	
	public void deletFav_shop(String mem_no,String shop_no){
		dao.delete(mem_no, shop_no);
	}
	
	
	public List<Fav_shopVO>findByMem_no(String mem_no){
		return dao.findByMem_no(mem_no);
	}
	
	public List<Fav_shopVO>findByShop_no(String shop_no){
		return dao.findByShop_no(shop_no);
		
	}
	
	public Fav_shopVO findByFK(String shop_no, String mem_no){
		return dao.findByFK(shop_no, mem_no);
	}
	
	public List<Fav_shopVO>getAll(){
		return dao.getAll();
	}
	
	
	
}
