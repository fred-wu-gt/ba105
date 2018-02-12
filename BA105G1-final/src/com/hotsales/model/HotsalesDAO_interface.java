package com.hotsales.model;

import java.util.List;

import com.commodity.model.CommodityVO;

public interface HotsalesDAO_interface {
	
	public void insert(HotsalesVO hotsalesVO);

	public void update(HotsalesVO hotsalesVO);

	public void delete(String hot_no);

	public HotsalesVO findByPrimaryKey(String hot_no);

	public List<HotsalesVO> getAll();
	
	public List <String> findByOd_quan	( );

}
