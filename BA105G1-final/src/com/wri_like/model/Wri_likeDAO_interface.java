package com.wri_like.model;

import java.util.List;

public interface Wri_likeDAO_interface {
	void insert(Wri_likeVO wri_likeVO);

	void delete(String wrt_no, String mem_no);

	List<Wri_likeVO> findByWrt_no(String wrt_no);

	List<Wri_likeVO> findByMem_no(String mem_no);

	List<Wri_likeVO> getAll();
}
