package com.ord_det.model;

import java.util.List;

public interface Ord_detDAO_interface {
	
	public void insertOrd_det(Ord_detVO ord_det);
	public void update(Ord_detVO ord_det);
	public void delete(String ord_no,String com_no);
	public List<Ord_detVO> findByOrd_no(String ord_no);
	public List<Ord_detVO> findByCom_no(String com_no);
	public List<Ord_detVO> getAll();
	 
	//同時新增訂單主檔與訂單明細 
	public void insertOrd_det2(Ord_detVO ord_det,java.sql.Connection con);

	
	
		
	

}
