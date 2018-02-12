package com.wri_mes.model;

import java.util.List;

public interface Wri_mesDAO_interface {

	public Integer insert(Wri_mesVO wri_mesVO);

	public Integer update(Wri_mesVO wri_mesVO);

	public Integer delete(String wmsg_no);

	public Wri_mesVO findByPrimaryKey(String wmsg_no);
	
	public List<Wri_mesVO> findAllWri_mesByWrt_no(String wrt_no);

	public List<Wri_mesVO> getAll();
}
