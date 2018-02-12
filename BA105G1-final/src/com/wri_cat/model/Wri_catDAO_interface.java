package com.wri_cat.model;

import java.util.List;

public interface Wri_catDAO_interface {
	void insert(Wri_catVO wri_catVO);

	void delete(String wrt_no,String fru_no);

	List<Wri_catVO> findByWrt_no(String wrt_no);

	List<Wri_catVO> findByFru_no(String fru_no);

	List<Wri_catVO> getAll();
}
