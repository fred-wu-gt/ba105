package com.man_fun.model;

import java.util.List;

public class Man_funService {
	private Man_funDAO_Interface dao;
	public Man_funService(){
		dao=new Man_funDAO();
	}
	public Man_funVO addMan_fun(String mf_name, String mf_des){
		Man_funVO man_funVO=new Man_funVO();
		man_funVO.setMf_name(mf_name);
		man_funVO.setMf_des(mf_des);
		dao.add(man_funVO);
		return man_funVO;
	}
	public Man_funVO updateMan_fun(String mf_no, String mf_name, String mf_des){
		Man_funVO man_funVO=new Man_funVO();
		man_funVO.setMf_no(mf_no);
		man_funVO.setMf_name(mf_name);
		man_funVO.setMf_des(mf_des);
		dao.update(man_funVO);
		return man_funVO;
	}
	public void deleteMan_fun(String mf_no){
		dao.delete(mf_no);
	}
	public Man_funVO getOneMan_fun(String mf_no){
		return dao.findByMf_no(mf_no);
	}
	
	public List<Man_funVO> getAll(){
		return dao.getAll();
	}
	
}
