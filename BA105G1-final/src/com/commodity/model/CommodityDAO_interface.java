package com.commodity.model;

import java.util.List;
import java.util.Map;

public interface CommodityDAO_interface {
	public String insert (CommodityVO comVO);
	public void updateForShop (CommodityVO comVO);
	public void delete (String com_no);
	public CommodityVO findByPrimaryKey (String com_no);
	public List<CommodityVO> getAll();
	public void scoreCommodity (CommodityVO comVO);
	public void updateComStorage (CommodityVO comVO);
	
	
	 //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<CommodityVO> getAll(Map<String, String[]> map); 
    
  //================================承霖使用=========================================
    public void updateCom_remarksParam(String com_remarksParam,String com_no);
	public CommodityVO getCommodityVO(String com_no);
	public CommodityVO findByShopNo (String shop_no);
	public String insertCom(CommodityVO comVO);
	public List<CommodityVO> getComByShopNo(String shop_no);
	//================================承霖使用=========================================

	public void delete (String com_no,String fru_no,String shop_no);
	public List<CommodityVO> getAllComposite(Map<String, String[]> map); 
//	public void delete (String com_no,String fru_no,String shop_no);
	
}
