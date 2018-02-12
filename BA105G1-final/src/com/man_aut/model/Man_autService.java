package com.man_aut.model;

import java.util.List;


public class Man_autService {
	private Man_autDAO_Interface dao;
	public Man_autService(){
		dao=new Man_autDAO();
	}
	
	public Man_autVO addMan_aut(String mf_no, String man_no){
		Man_autVO man_autVO=new Man_autVO();
		man_autVO.setMf_no(mf_no);
		man_autVO.setMan_no(man_no);
		dao.add(man_autVO);
		return man_autVO;
	}
	public void deleteMan_aut(String mf_no, String man_no){
		dao.delete(mf_no, man_no);
	}
	public List<Man_autVO> getMan_autListByMf_no(String mf_no){
		return dao.findByMf_no(mf_no);
	}
	public List<Man_autVO> getMan_autListByMan_no(String man_no){
		return dao.findByMan_no(man_no);
	}
	public List<Man_autVO> getAll(){
		return dao.getAll();
	}
}
