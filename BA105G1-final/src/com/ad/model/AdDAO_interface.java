package com.ad.model;

import java.util.List;

import com.shop.model.ShopVO;

public interface AdDAO_interface {

	public void insert(AdVO adVo);
	public void update(AdVO adVo);
	public void delete(String ad_no);
	public AdVO findByPrimaryKey(String ad_no);
	public List<AdVO> getAll();
	
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public List<AdVO> findByAd_stat(String ad_stat);
	public void updateAd_stat(AdVO adVo);
	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
