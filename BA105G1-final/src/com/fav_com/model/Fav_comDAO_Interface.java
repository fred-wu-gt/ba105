package com.fav_com.model;

import java.util.List;

import com.member.model.MemberVO;

@SuppressWarnings("unused")
public interface Fav_comDAO_Interface {

	void add(Fav_comVO fav_comvO);
	void delete(String mem_no,String com_no);
	public List<Fav_comVO> findByMem_no(String mem_no);
	public List<Fav_comVO> findByCom_no(String com_no);
	public List<Fav_comVO> getAll();
	//=======================================================
	public Fav_comVO findByBoth(String mem_no,String com_no);
	//=======================================================
	
	
}
