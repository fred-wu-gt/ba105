package com.prom.model;

import java.sql.Timestamp;
import java.util.List;

public class PromService {

	private PromDAO_interface dao;

	public PromService() {
		dao = new PromJNDIDAOImpl();
	}

	public PromVO addProm(String shop_no,Double prom_dis,
			Timestamp prom_start,Timestamp prom_end) {

		PromVO promVO = new PromVO();
		promVO.setShop_no(shop_no);
		promVO.setProm_dis(prom_dis);
		promVO.setProm_start(prom_start);
		promVO.setProm_end(prom_end);

		dao.insert(promVO);

		return promVO;
	}
	
	
	public void deleteProm(String prom_no) {
		dao.delete(prom_no);
	}
	
	
	
	
	public PromVO updateProm(String prom_no,String shop_no,Double prom_dis,
			Timestamp prom_start,Timestamp prom_end) {
		
		PromVO promVO = new PromVO();

		promVO.setShop_no(shop_no);
		promVO.setProm_dis(prom_dis);
		promVO.setProm_start(prom_start);
		promVO.setProm_end(prom_end);
		promVO.setProm_no(prom_no);
		
		dao.update(promVO);
		return promVO;
	}
	
	
	public PromVO findByPrimaryKey(String shop_no) {
		return dao.findByPrimaryKey(shop_no);
	}
	
	
	public List<PromVO> getAll(){
		return dao.getAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
