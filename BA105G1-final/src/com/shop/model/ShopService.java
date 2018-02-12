package com.shop.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.commodity.model.CommodityVO;

public class ShopService {
	
	private ShopDAO_interface dao;

	public ShopService() {
		dao = new ShopJNDIDAOImpl();
	}
	
	public ShopVO getOneMember(String shop_no) {
		return dao.findByPrimaryKey(shop_no);
	}
	
	
	public ShopVO addShopMember(String shop_name,String shop_id,String shop_psw,
			String shop_email,String shop_phone,String shop_loc , Double shop_lat , Double shop_lon
			,String shop_desc,
			String shop_bank,String shop_acc,byte[] shop_proof,byte[] shop_photo) {
		
		ShopVO shopVO = new ShopVO();
		
		shopVO.setShop_name(shop_name);
		shopVO.setShop_id(shop_id);
		shopVO.setShop_psw(shop_psw);
		shopVO.setShop_email(shop_email);
		shopVO.setShop_phone(shop_phone);
		shopVO.setShop_loc(shop_loc);
		shopVO.setShop_lat(shop_lat);
		shopVO.setShop_lon(shop_lon);
		shopVO.setShop_desc(shop_desc);
		shopVO.setShop_bank(shop_bank);
		shopVO.setShop_acc(shop_acc);
		shopVO.setShop_proof(shop_proof);
		shopVO.setShop_photo(shop_photo);
		
		dao.insert(shopVO);
		
		return shopVO;
		
	}
	
	public ShopVO updateShop(String shop_no,String shop_name,String shop_id,String shop_psw,
			String shop_email,String shop_phone,String shop_loc,String shop_desc,
			String shop_bank,String shop_acc,byte[] shop_proof,byte[] shop_photo) {
				
		ShopVO shopVO = new ShopVO();
		
		shopVO.setShop_name(shop_name);
		shopVO.setShop_id(shop_id);
		shopVO.setShop_psw(shop_psw);
		shopVO.setShop_email(shop_email);
		shopVO.setShop_phone(shop_phone);
		shopVO.setShop_loc(shop_loc);
		shopVO.setShop_desc(shop_desc);
		shopVO.setShop_bank(shop_bank);
		shopVO.setShop_acc(shop_acc);
		shopVO.setShop_proof(shop_proof);
		shopVO.setShop_photo(shop_photo);
		shopVO.setShop_no(shop_no);
		
		dao.update(shopVO);
		return shopVO;
		
	}

	public void delete(String shop_no) {
		dao.delete(shop_no);
	}

	public ShopVO findByPrimaryKey(String shop_no) {
		return dao.findByPrimaryKey(shop_no);
	}

	public List<ShopVO> getAll() {
		return dao.getAll();
	}
	
	public ShopVO login(String shop_id,String shop_psw) {
		return dao.findById(shop_id, shop_psw);		
	}
	
	public ShopVO checkShopIdRepeat(String shop_id) {
		return dao.checkShopIdRepeat(shop_id);
	}
	public Set<CommodityVO> getComByShopNo(String shop_no) {
		return dao.findByShopNo(shop_no);
	}
	
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void updateShop_stat(String shop_no, String shop_stat) {
		dao.updateShop_stat(shop_no, shop_stat);
	}
	public void updateShop_point(String shop_no, Integer shop_point) {
		dao.updateShop_point(shop_no, shop_point);
	}
	
	public List<ShopVO> findByShop_stat(String shop_stat) {
		return dao.findByShop_stat(shop_stat);
	}
	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
}
