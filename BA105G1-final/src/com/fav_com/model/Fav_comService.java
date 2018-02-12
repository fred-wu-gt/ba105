package com.fav_com.model;

import java.util.List;

public class Fav_comService {

	private Fav_comDAO_Interface dao;
	
	public Fav_comService(){
		dao = new Fav_comDAO();
	}
	
	public Fav_comVO addFav_com(String mem_no, String com_no){
		
		Fav_comVO fav_comVO = new Fav_comVO();
		
		fav_comVO.setMem_no(mem_no);
		fav_comVO.setCom_no(com_no);
		dao.add(fav_comVO);
		System.out.println("新增資料成功!!");
		return fav_comVO;
		
	}
	
	public void deletFav_com(String mem_no,String com_no){
		
		dao.delete(mem_no,com_no);
	}
	
	
	public List<Fav_comVO>findByMem_no(String mem_no){
		return dao.findByMem_no(mem_no);
		
	}
	
	public List<Fav_comVO>findByCom_no(String com_no){
		return dao.findByCom_no(com_no);
		
	}
	
	public List<Fav_comVO> getAll(){
		return dao.getAll();
		
	}
	
	//=======================================================
		public Fav_comVO findByBoth(String mem_no,String com_no){
			return dao.findByBoth(mem_no, com_no);
		}
	//=======================================================
		
	
	
}
