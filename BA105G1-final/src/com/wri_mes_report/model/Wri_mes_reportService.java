package com.wri_mes_report.model;

import java.util.List;

import com.wri_mes.model.Wri_mesVO;

public class Wri_mes_reportService {
	private Wri_mes_reportDAO_interface dao;

	public Wri_mes_reportService() {
		dao = new Wri_mes_reportJDBCDAO();
	}

	public Wri_mes_reportVO addWriting_mes_report(String wmrpt_no, String wmsg_no, String mem_no, String wmrpt_rsn,
			String wmrpt_stat, String wmrpt_cont) {

		Wri_mes_reportVO wri_mes_reportVO = new Wri_mes_reportVO();

		wri_mes_reportVO.setWmrpt_no(wmrpt_no);
		wri_mes_reportVO.setWmsg_no(wmsg_no);
		wri_mes_reportVO.setMem_no(mem_no);
		wri_mes_reportVO.setWmrpt_rsn(wmrpt_rsn);
		wri_mes_reportVO.setWmrpt_stat("正常");
		wri_mes_reportVO.setWmrpt_cont(wmrpt_cont);
		dao.insert(wri_mes_reportVO);
		return wri_mes_reportVO;

	}

	public Wri_mes_reportVO updateWriting_mes_report(String wmrpt_no, String wmsg_no, String mem_no, String wmrpt_rsn,
			String wmrpt_stat, String wmrpt_cont) {

		Wri_mes_reportVO wri_mes_reportVO = new Wri_mes_reportVO();

		wri_mes_reportVO.setWmrpt_no(wmrpt_no);
		wri_mes_reportVO.setWmsg_no(wmsg_no);
		wri_mes_reportVO.setMem_no(mem_no);
		wri_mes_reportVO.setWmrpt_rsn(wmrpt_rsn);
		wri_mes_reportVO.setWmrpt_stat("正常");
		wri_mes_reportVO.setWmrpt_cont(wmrpt_cont);
		dao.update(wri_mes_reportVO);
		return wri_mes_reportVO;

	}

	public void deleteWriting_mes_report(String wmrpt_no) {
		dao.delete(wmrpt_no);
	}

	public Wri_mes_reportVO getOneWriting_mes_report(String wmrpt_no) {
		return dao.findByPrimaryKey(wmrpt_no);
	}

	public List<Wri_mes_reportVO> getAll() {
		return dao.getAll();

	}

}
