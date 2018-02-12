package com.act_join_det.model;
import java.util.List;


public class Act_join_detService {
	
	private Act_join_detDAO_interface dao;
	
	public Act_join_detService(){
		dao = new Act_join_detDAO();
	}
	//新增
	public Act_join_detVO insert(String act_no, String mem_no){
		
		Act_join_detVO act_join_detVO = new Act_join_detVO();
		act_join_detVO.setAct_no(act_no);
		act_join_detVO.setMem_no(mem_no);
		act_join_detVO.setAj_status("未報到");
		dao.insert(act_join_detVO);
		return act_join_detVO;
	}
	//修改報名活動狀態
	public void updateAjStatus(String aj_status, String act_no, String mem_no) {
		
		Act_join_detVO act_join_detVO = new Act_join_detVO();
		act_join_detVO.setAj_status(aj_status);
		act_join_detVO.setAct_no(act_no);
		act_join_detVO.setMem_no(mem_no);
		dao.updateAjStatus(aj_status, act_no, mem_no);
	}
	//查看已報名活動
	public List<Act_join_detVO> findByMemNo(String mem_no) {
		return dao.findByMemNo(mem_no);
	}
	
	//查詢報名名單0112+
    public List<Act_join_detVO> findByActNo(String act_no) {
    	return dao.findByActNo(act_no);
    }
	
	public List<Act_join_detVO> getAll() {
		return dao.getAll();
	}
	
	public Act_join_detVO findByPrimaryKey(String act_no, String mem_no) {
		return dao.findByPrimaryKey(act_no, mem_no);
	}
	
	public void delete(String act_no, String mem_no){
		dao.delete(act_no, mem_no);
	}
	
}
