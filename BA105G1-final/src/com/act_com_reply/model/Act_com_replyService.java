package com.act_com_reply.model;

import java.util.List;

import com.act_rep.model.Act_repVO;

public class Act_com_replyService {
	
	private Act_com_replyDAO_interface dao;
	
	public Act_com_replyService(){
		dao = new Act_com_replyDAO();
	}
	//acr_no,aco_no,shop_no,acr_cnt,acr_time
	
	public Act_com_replyVO insert(String aco_no, String shop_no, String acr_cnt) {
		Act_com_replyVO act_com_replyVO = new Act_com_replyVO();
		act_com_replyVO.setAco_no(aco_no);
		act_com_replyVO.setShop_no(shop_no);
		act_com_replyVO.setAcr_cnt(acr_cnt);
		dao.insert(act_com_replyVO);
		return act_com_replyVO;
	}
	
	public Act_com_replyVO update(String acr_no, String aco_no, String shop_no, String acr_cnt){
		Act_com_replyVO act_com_replyVO = new Act_com_replyVO();
		act_com_replyVO.setAco_no(acr_no);
		act_com_replyVO.setAco_no(aco_no);
		act_com_replyVO.setShop_no(shop_no);
		act_com_replyVO.setAcr_cnt(acr_cnt);
		dao.update(act_com_replyVO);
		return act_com_replyVO;
	}
	
	public Act_com_replyVO findByPrimaryKey(String acr_no){
		return dao.findByPrimaryKey(acr_no);
	}
	
	//為了看商家是否檢舉過 //0108+
	public Act_com_replyVO findByFK(String aco_no, String shop_no){
		return dao.findByFK(aco_no, shop_no);
	}
	
	//撈出某筆活動留言的所有 留言回覆0108+
	public List<Act_com_replyVO> findByAcoNo(String aco_no){
		return dao.findByAcoNo(aco_no);
	}
	
	public List<Act_com_replyVO> getAll(){
		return dao.getAll();
	}
		
}
