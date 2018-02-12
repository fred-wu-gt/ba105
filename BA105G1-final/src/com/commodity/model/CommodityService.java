package com.commodity.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.oreilly.servlet.Base64Encoder;


public class CommodityService {
	private CommodityDAO_interface dao;

	public CommodityService() {
		dao = new CommodityJNDIDAO();
	}

	  //================================承霖使用=========================================

//	/**修改商品敘述**/
	public void updateCom_remarksParam(String com_remarks,String com_no) {
		dao.updateCom_remarksParam(com_remarks,com_no);
	}
	public CommodityVO getOneCommodityVO(String shop_no) {
		
		return dao.findByShopNo(shop_no);
	}
	public CommodityVO getCommodityVO(String com_no) {
		
		return dao.getCommodityVO(com_no);
	}
	public CommodityVO updateCommodity(String com_no, String com_name,String shop_no, String fru_no, Integer com_price,
			String com_weight, String com_remarks, byte[] com_pic1, byte[] com_pic2, byte[] com_pic3,
			 String com_status, Integer com_store, Double com_score, Integer com_peo) {

		CommodityVO commodityVO = new CommodityVO();
		commodityVO.setCom_no(com_no);
		commodityVO.setCom_name(com_name);
		commodityVO.setShop_no(shop_no);
		commodityVO.setFru_no(fru_no);
		commodityVO.setCom_price(com_price);
		commodityVO.setCom_weight(com_weight);
		commodityVO.setCom_remarks(com_remarks);
		commodityVO.setCom_pic1(com_pic1);
		commodityVO.setCom_pic2(com_pic2);
		commodityVO.setCom_pic3(com_pic3);
//此方法我把時間註解		commodityVO.setCom_time(com_time);
		commodityVO.setCom_status(com_status);
		commodityVO.setCom_store(com_store);
		commodityVO.setCom_score(com_score);
		commodityVO.setCom_peo(com_peo);
		dao.updateForShop(commodityVO);

		return commodityVO;
	}
	
	public List<CommodityVO> getComByShopNo(String shop_no) {
		  return dao.getComByShopNo(shop_no);
		 }
	public List<CommodityVO> getAllComposite(Map<String, String[]> map){
		return dao.getAllComposite(map);
		
	}
	

	  //================================承霖使用=========================================

	public CommodityVO addCom 	(String com_name, String shop_no, String fru_no, Integer com_price,
			String com_weight, String com_remarks, byte[] com_pic1, byte[] com_pic2, byte[] com_pic3,
			String com_status, Integer com_store) {

		CommodityVO commodityVO = new CommodityVO();
		commodityVO.setCom_name(com_name);
		commodityVO.setShop_no(shop_no);
		commodityVO.setFru_no(fru_no);
		commodityVO.setCom_price(com_price);
		commodityVO.setCom_weight(com_weight);
		commodityVO.setCom_remarks(com_remarks);
		commodityVO.setCom_pic1(com_pic1);
		commodityVO.setCom_pic2(com_pic2);
		commodityVO.setCom_pic3(com_pic3);
		commodityVO.setCom_status(com_status);
		commodityVO.setCom_store(com_store);
		commodityVO.setCom_score(0.0);
		commodityVO.setCom_peo(0);
		dao.insertCom(commodityVO);


		return commodityVO;
	}
	
	
	
	public void updateComStorage(Integer com_store, String com_no){
		CommodityVO commodityVO = new CommodityVO();
		commodityVO.setCom_store(com_store);
		commodityVO.setCom_no(com_no);
		dao.updateComStorage(commodityVO);
	}
	
	
	
	public CommodityVO updateCommodity(String com_no, String com_name, String shop_no, String fru_no, Integer com_price,
			String com_weight, String com_remarks, byte[] com_pic1, byte[] com_pic2, byte[] com_pic3,
			Timestamp com_time, String com_status, Integer com_store, Double com_score, Integer com_peo) {

		CommodityVO commodityVO = new CommodityVO();
		commodityVO.setCom_no(com_no);
		commodityVO.setCom_name(com_name);
		commodityVO.setShop_no(shop_no);
		commodityVO.setFru_no(fru_no);
		commodityVO.setCom_price(com_price);
		commodityVO.setCom_weight(com_weight);
		commodityVO.setCom_remarks(com_remarks);
		commodityVO.setCom_pic1(com_pic1);
		commodityVO.setCom_pic2(com_pic2);
		commodityVO.setCom_pic3(com_pic3);
		commodityVO.setCom_time(com_time);
		commodityVO.setCom_status(com_status);
		commodityVO.setCom_store(com_store);
		commodityVO.setCom_score(com_score);
		commodityVO.setCom_peo(com_peo);
		dao.updateForShop(commodityVO);

		return commodityVO;
	}
	
	public CommodityVO scoreCommodity(Double com_score, Integer com_peo,String com_no){
		CommodityVO commodityVO = new CommodityVO();
		commodityVO.setCom_score(com_score);
		commodityVO.setCom_peo(com_peo);
		commodityVO.setCom_no(com_no);
		dao.scoreCommodity(commodityVO);
		
		return commodityVO;
		
	}
	
	public CommodityVO addCommodity(String com_name, String shop_no, String fru_no, Integer com_price,
			String com_weight, String com_remarks, byte[] com_pic1, byte[] com_pic2, byte[] com_pic3,
			String com_status, Integer com_store) {

		CommodityVO commodityVO = new CommodityVO();
		commodityVO.setCom_name(com_name);
		commodityVO.setShop_no(shop_no);
		commodityVO.setFru_no(fru_no);
		commodityVO.setCom_price(com_price);
		commodityVO.setCom_weight(com_weight);
		commodityVO.setCom_remarks(com_remarks);
		commodityVO.setCom_pic1(com_pic1);
		commodityVO.setCom_pic2(com_pic2);
		commodityVO.setCom_pic3(com_pic3);
		java.util.Date now = new java.util.Date();
		java.sql.Timestamp com_time = new java.sql.Timestamp(now.getTime());
		commodityVO.setCom_time(com_time);
		commodityVO.setCom_status(com_status);
		commodityVO.setCom_store(com_store);
		commodityVO.setCom_score(0.0);
		commodityVO.setCom_peo(0);
		String com_no= dao.insert(commodityVO);
		commodityVO.setCom_no(com_no);

		return commodityVO;
	}
	public void deleteCommoity(String com_no,String fru_no,String shop_no) {
		
		dao.delete(com_no,fru_no,shop_no);
	}
	


	public void deleteCommoity(String com_no) {
		
		dao.delete(com_no);
	}

	

	public List<CommodityVO> getAll() {
		return dao.getAll();
	}
	
	public List<CommodityVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	

}
