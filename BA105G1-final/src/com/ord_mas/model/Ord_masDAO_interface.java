package com.ord_mas.model;

import java.util.List;
import java.util.Set;

import com.commodity.model.CommodityVO;
import com.ord_det.model.Ord_detVO;

public interface Ord_masDAO_interface {
	public  String insert(Ord_masVO ord_mas);
	public void update(Ord_masVO ord_mas);
	public void delete(String ord_no);
	public Ord_masVO findByPrimaryKey(String ord_no);
	public List<Ord_masVO> getAll();
	
	public void changOrdMasRec (Ord_masVO ord_masVO);
	
	
	public void changOrdSta (Ord_masVO ord_masVO);
	
	
	
	//從會員編號找訂單主檔
	public List<Ord_masVO> findByMemNO(String mem_no);
	//從商家編號找訂單主檔
	public List<Ord_masVO> findByShopNO(String shop_no);
	  
	//同時新增訂單主檔與訂單明細 
	public void insertWithOrd_det(Ord_masVO ord_mas , List<Ord_detVO> list);

	 //查詢某訂單主檔的訂單明細(一對多)(回傳 Set)
    public Set<Ord_detVO> getOrd_detByOrd_no(String ord_no);
    
    
    
    //======================承霖開始============================
    public Ord_masVO findByShop_no(String shop_no);
    public List<Ord_masVO> getOrderMasByShop(String shop_no);
    public List<Ord_detVO> getOrderDetailByComNo(String ord_no);
    public void updateOrd_sta(String ord_sta, String ord_no);
    //======================承霖結束============================
    
	

}
