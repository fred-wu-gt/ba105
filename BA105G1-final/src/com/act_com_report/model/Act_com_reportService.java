package com.act_com_report.model;

import java.util.List;

import com.act_com_reply.model.Act_com_replyVO;

public class Act_com_reportService {
private Act_com_reportDAO_interface dao;
	
	public Act_com_reportService(){
		dao = new Act_com_reportDAO();
	}
	
	//acr_no, aco_no, mem_no, acr_rsn, acr_status, acr_cnt
	
	public Act_com_reportVO insert(String aco_no, String mem_no, String acr_rsn, String acr_cnt) {
		Act_com_reportVO act_com_reportVO = new Act_com_reportVO();
		act_com_reportVO.setAco_no(aco_no);
		act_com_reportVO.setMem_no(mem_no);
		act_com_reportVO.setAcr_rsn(acr_rsn);
		act_com_reportVO.setAcr_status("待審核");
		act_com_reportVO.setAcr_cnt(acr_cnt);
		dao.insert(act_com_reportVO);
		return act_com_reportVO;
	}
	
	public Act_com_reportVO update(String acr_no, String aco_no, String mem_no, String acr_rsn, String acr_status, String acr_cnt){
		Act_com_reportVO act_com_reportVO = new Act_com_reportVO();
		act_com_reportVO.setAcr_no(acr_no);
		act_com_reportVO.setAco_no(aco_no);
		act_com_reportVO.setMem_no(mem_no);
		act_com_reportVO.setAcr_rsn(acr_rsn);
		act_com_reportVO.setAcr_status("待審核");
		act_com_reportVO.setAcr_cnt(acr_cnt);
		dao.update(act_com_reportVO);
		return act_com_reportVO;
	}
	
	//修改活動留言狀態
	public void updateAcrStatus(String aco_status, String act_no) {
		dao.updateAcrStatus(aco_status, act_no);
	}
	
	//為了看會員是否檢舉過 //1230+
	public Act_com_reportVO findByFK(String aco_no, String mem_no) {
		return dao.findByFK(aco_no, mem_no);
	}
	
	public Act_com_reportVO findByPrimaryKey(String acr_no) {
		return dao.findByPrimaryKey(acr_no);
	}
	
	public List<Act_com_reportVO> getAll() {
		return dao.getAll();
	}
	
	public void delete(String acr_no) {
		dao.delete(acr_no);
	}
}
