package com.wri_mes.model;

import java.util.List;


public class Wri_mesService {
	private Wri_mesDAO_interface dao;

	public Wri_mesService() {
		dao = new Wri_mesJNDIDAO();
	}

	public Wri_mesVO addWriting_mes(String wrt_no, String mem_no, String wmsg_cont) {

		Wri_mesVO wri_mesVO = new Wri_mesVO();
		
		wri_mesVO.setWrt_no(wrt_no);
		wri_mesVO.setMem_no(mem_no);
		wri_mesVO.setWmsg_cont(wmsg_cont);
		dao.insert(wri_mesVO);
		return wri_mesVO;
	}

	public Wri_mesVO updateWriting_mes(String wmsg_no, String wrt_no, String mem_no, String wmsg_cont,
			String wmsg_stat) {

		Wri_mesVO wri_mesVO = new Wri_mesVO();

		wri_mesVO.setWmsg_no(wmsg_no);
		wri_mesVO.setWrt_no(wrt_no);
		wri_mesVO.setMem_no(mem_no);
		wri_mesVO.setWmsg_cont(wmsg_cont);
		wri_mesVO.setWmsg_stat("已審核");
		dao.update(wri_mesVO);

		return wri_mesVO;
	}

	public void deleteWriting_mes(String wmsg_no) {
		dao.delete(wmsg_no);
	}

	public Wri_mesVO getOneWriting_mes(String wmsg_no) {
		return dao.findByPrimaryKey(wmsg_no);
	}
	
	
	public List<Wri_mesVO> getAllWriting_mesByWrt_no(String wrt_no){
		return dao.findAllWri_mesByWrt_no(wrt_no);
	}

	public List<Wri_mesVO> getAll() {
		return dao.getAll();

	}

}
