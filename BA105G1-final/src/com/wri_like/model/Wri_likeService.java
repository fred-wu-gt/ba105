package com.wri_like.model;

import java.util.List;

public class Wri_likeService {
	private Wri_likeDAO_interface dao;

	public Wri_likeService() {
		dao = new Wri_likeJDBCDAO();
	}

	public Wri_likeVO addWriting_like(String wrt_no, String mem_no) {

		Wri_likeVO wri_likeVO = new Wri_likeVO();

		wri_likeVO.setWrt_no(wrt_no);
		wri_likeVO.setMem_no(mem_no);
		dao.insert(wri_likeVO);
		return wri_likeVO;
	}

	public void deleteWriting_like(String wrt_no, String mem_no) {
		dao.delete(wrt_no, mem_no);
	}

	public List<Wri_likeVO> findByWrt_no(String wrt_no) {
		return dao.findByMem_no(wrt_no);
	}

	public List<Wri_likeVO> findByMem_no(String wrt_no) {

		return dao.findByWrt_no(wrt_no);

	}

	public List<Wri_likeVO> getAll() {
		return dao.getAll();

	}

}
