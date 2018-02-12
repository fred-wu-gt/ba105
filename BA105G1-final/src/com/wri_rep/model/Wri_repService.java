package com.wri_rep.model;

import java.sql.Date;
import java.util.List;

import com.wri_mes.model.Wri_mesVO;
import com.writing.model.WritingVO;

public class Wri_repService {
	private Wri_repDAO_interface dao;

	public Wri_repService() {
		dao = new Wri_repJDBCDAO();
	}

	public Wri_repVO addWriting_rep(String wre_no, String wrt_no, String mem_no, String wre_rsn, String wre_stat,
			String wre_cont) {
		Wri_repVO wri_repVO = new Wri_repVO();

		wri_repVO.setWre_no(wre_no);
		wri_repVO.setWrt_no(wrt_no);
		wri_repVO.setMem_no(mem_no);
		wri_repVO.setWre_rsn(wre_rsn);
		wri_repVO.setWre_stat(wre_stat);
		wri_repVO.setWre_cont(wre_cont);
		dao.insert(wri_repVO);
		return wri_repVO;
	}
	
	public Wri_repVO updateWriting_rep(String wre_no, String wrt_no, String mem_no, String wre_rsn, String wre_stat,
			String wre_cont){
		
		Wri_repVO wri_repVO = new Wri_repVO();
		
		wri_repVO.setWre_no(wre_no);
		wri_repVO.setWrt_no(wrt_no);
		wri_repVO.setMem_no(mem_no);
		wri_repVO.setWre_rsn(wre_rsn);
		wri_repVO.setWre_stat(wre_stat);
		wri_repVO.setWre_cont(wre_cont);
		
		dao.update(wri_repVO);
		return wri_repVO;
		
	}
	
	public void deleteWriting_rep(String wre_no) {
		dao.delete(wre_no);
	}
	
	public Wri_repVO getOneWriting_rep(String wre_no) {
		return dao.findByPrimaryKey(wre_no);
	}
	
	public List<Wri_repVO> getAll() {
		return dao.getAll();

	}

	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		public List<Wri_repVO> findByWre_stat(String wre_stat){
			return dao.findByWre_stat(wre_stat);
		}
		public void updateWre_stat(String wre_stat, String wre_no){
			dao.updateWre_stat(wre_stat, wre_no);
		}
	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
}
