package com.shop.model;

import java.util.List;
import java.util.Set;

import com.commodity.model.CommodityVO;

/**我是介面**/
public interface ShopDAO_interface {
	public void insert(ShopVO shopVO);
	public void update(ShopVO shopVO);
	public void delete(String shop_no);
	public ShopVO findByPrimaryKey(String shop_no);
	public List<ShopVO> getAll();
	public ShopVO findById(String shop_id,String shop_psw);
	public ShopVO checkShopIdRepeat(String shop_id);
	public Set<CommodityVO> findByShopNo(String shop_no);
	
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		public void updateShop_stat(String shop_no, String shop_stat);
		public void updateShop_point(String shop_no, Integer shop_point);
		public List<ShopVO> findByShop_stat(String shop_stat);
	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
