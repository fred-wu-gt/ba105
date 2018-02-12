package com.ord_mas.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ord_det.model.Ord_detVO;

public class Ord_masService {
	private Ord_masDAO_interface dao ;
	public Ord_masService(){
		 dao = new Ord_masJNDIDAO();
	}

	public Ord_masVO addOrd_mas(String mem_id, String shop_no,String ord_sta, Integer ord_total,
			String ord_rec, String ord_adr, String ord_tel) {

		Ord_masVO ord_masVO = new Ord_masVO();
		ord_masVO.setMem_no(mem_id);
		ord_masVO.setShop_no(shop_no);
		java.util.Date now = new java.util.Date() ; 
		java.sql.Timestamp ord_time = new java.sql.Timestamp(now.getTime());
		ord_masVO.setOrd_time(ord_time);
		ord_masVO.setOrd_sta(ord_sta);
		ord_masVO.setOrd_total(ord_total);
		ord_masVO.setOrd_rec(ord_rec);
		ord_masVO.setOrd_adr(ord_adr);
		ord_masVO.setOrd_tel(ord_tel);

		String ord_no = dao.insert(ord_masVO);
		ord_masVO.setOrd_no(ord_no);

		return ord_masVO;

	}
	
	
	public void  insertWithOrd_det(Ord_masVO ord_mas, List<Ord_detVO> list){
		
		dao.insertWithOrd_det(ord_mas, list);

		
	}
	
	
	
	public Ord_masVO changOrdSta(String ord_sta, String ord_no ){
		Ord_masVO ord_masVO = new Ord_masVO();
		ord_masVO.setOrd_sta(ord_sta);
		ord_masVO.setOrd_no(ord_no);
		
		dao.changOrdSta(ord_masVO);
		return ord_masVO ;
	}
	
	
	public Ord_masVO changeOrdMasRec(String ord_rec, String ord_adr, String ord_tel, String ord_no) {
		Ord_masVO ord_masVO = new Ord_masVO();
		ord_masVO.setOrd_rec(ord_rec);
		ord_masVO.setOrd_adr(ord_adr);
		ord_masVO.setOrd_tel(ord_tel);
		ord_masVO.setOrd_no(ord_no);

		dao.changOrdMasRec(ord_masVO);
		return ord_masVO;
	}
	
	
	public List<Ord_masVO> getAll(){
		return dao.getAll();
		
	}
	
	public Set<Ord_detVO> getOrd_detByOrd_no(String ord_no) {
		return dao.getOrd_detByOrd_no(ord_no);
	}
	
	public Ord_masVO getOneOrd_mas(String ord_no) {
		return dao.findByPrimaryKey(ord_no);
	}
	
	public List<Ord_masVO> getListByMemNO(String mem_no ){
		return dao.findByMemNO(mem_no);
		
	}
	
	
	public List<Ord_masVO> getListByShopNO(String shop_no ){
		return dao.findByShopNO(shop_no);
		
	}
	
    //======================承霖開始============================

	public List<Ord_masVO> getOrderMasByShop(String shop_no) {
		return dao.getOrderMasByShop(shop_no);
	}
	public List<Ord_detVO> getOrderDetailByComNo(String ord_no) {
		return dao.getOrderDetailByComNo(ord_no);
	}
	public void updateOrd_sta(String ord_sta, String ord_no) {
		  dao.updateOrd_sta(ord_sta, ord_no);
		 }
    //======================承霖結束============================

	

}
