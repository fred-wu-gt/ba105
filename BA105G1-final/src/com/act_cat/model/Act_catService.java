package com.act_cat.model;

import java.sql.Connection;
import java.util.List;

public class Act_catService {
	private Act_catDAO_interface dao;

	public Act_catService() {
		dao = new Act_catDAO();
	}

	public Act_catVO insert(String act_no, String fru_no) {
		Act_catVO act_catVO = new Act_catVO();
		act_catVO.setAct_no(act_no);
		act_catVO.setFru_no(fru_no);
		dao.insert(act_catVO);
		return act_catVO;
	}

//	public void insert2 (Act_catVO act_catVO , Connection con){
//		dao.insert2(act_catVO, con);
//	}
	
	public Act_catVO findByPrimaryKey(String act_no, String fru_no) {
		return dao.findByPrimaryKey(act_no, fru_no);
	}

	public Act_catVO findByActNo(String act_no){
		return dao.findByActNo(act_no);
	}
	
	public List<Act_catVO> getAll() {
		return dao.getAll();
	}
}
