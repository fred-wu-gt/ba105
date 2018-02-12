package com.act_com.model;

import java.sql.Timestamp;
import java.util.List;

import com.activity.model.ActivityVO;


public class Act_comService {
	private Act_comDAO_interface dao;
	
	public Act_comService(){
		dao = new Act_comDAO();
	}
	//aco_no, act_no, mem_no, aco_cnt, aco_date, aco_status
	//新增一筆活動留言
	public Act_comVO insert(String act_no, String mem_no, String aco_cnt) {
		Act_comVO act_comVO = new Act_comVO();
		act_comVO.setAct_no(act_no);
		act_comVO.setMem_no(mem_no);
		act_comVO.setAco_cnt(aco_cnt);
		act_comVO.setAco_status("正常");
		dao.insert(act_comVO);
		return act_comVO;
	}
	
	public Act_comVO update(String aco_no, String act_no, String mem_no, String aco_cnt) {
		Act_comVO act_comVO = new Act_comVO();
		act_comVO.setAco_no(aco_no);
		act_comVO.setAct_no(act_no);
		act_comVO.setMem_no(mem_no);
		act_comVO.setAco_cnt(aco_cnt);
		act_comVO.setAco_status("正常");
		dao.update(act_comVO);
		return act_comVO;
	}
	
	//修改活動留言狀態
	public void updateAcoStatus(String aco_status, String act_no) {
		dao.updateAcoStatus(aco_status, act_no);
	}
	
	public List<Act_comVO> getAll() {
		return dao.getAll();
	}
	
	//撈出單一活動的所有留言 //1230加的
	public List<Act_comVO> findByActNo(String act_no) {
		return dao.findByActNo(act_no);
	}
	
	public Act_comVO findByPrimaryKey(String aco_no) {
		return dao.findByPrimaryKey(aco_no);
	}
	
}
