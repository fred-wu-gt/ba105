package com.act_rep.model;
import java.util.List;

public class Act_repService {
	private Act_repDAO_interface dao;
	
	public Act_repService(){
		dao = new Act_repDAO();
	}
	//ar_no, act_no, mem_no,ar_rsn, act_status, ar_cnt
	
	//新增
	public Act_repVO addAct_rep(String act_no, String mem_no, String ar_rsn, String ar_cnt){
		Act_repVO act_repVO = new Act_repVO();
		act_repVO.setAct_no(act_no);
		act_repVO.setMem_no(mem_no);
		act_repVO.setAr_rsn(ar_rsn);
		act_repVO.setAct_status("待審核");
		act_repVO.setAr_cnt(ar_cnt);
		dao.insert(act_repVO);
		return act_repVO;
	}
	
	public void update(String ar_no, String act_no, String mem_no, String ar_rsn, String ar_cnt){
		
		Act_repVO act_repVO = new Act_repVO();
		act_repVO.setAr_no(ar_no);
		act_repVO.setAct_no(act_no);
		act_repVO.setMem_no(mem_no);
		act_repVO.setAr_rsn(ar_rsn);
		act_repVO.setAct_status("待審核");
		act_repVO.setAr_cnt(ar_cnt);
		dao.update(act_repVO);
	}
	
	//修改活動檢舉狀態
  	public void updateAjStatus(String act_status, String ar_no){
  		Act_repVO act_repVO = new Act_repVO();
  		act_repVO.setAct_status(act_status);
		act_repVO.setAr_no(ar_no);
		dao.updateActStatus(act_status, ar_no);
  	}
	
  	public Act_repVO findByFK(String act_no, String mem_no){
    	return dao.findByFK(act_no, mem_no);
    }
  	
	public Act_repVO findByPrimaryKey(String ar_no){
		return dao.findByPrimaryKey(ar_no);
	}
	
	public List<Act_repVO> getAll(){
		return dao.getAll();
	}

	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		public List<Act_repVO> findByAct_status(String act_status){
			return dao.findByAct_status(act_status);
		}
	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	
}
