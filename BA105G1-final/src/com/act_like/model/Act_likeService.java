package com.act_like.model;

import java.util.List;

public class Act_likeService {
private Act_likeDAO_interface dao;
	
	public Act_likeService(){
		dao = new Act_likeDAO();
	}
	
	public Act_likeVO insert(String act_no, String mem_no){
		Act_likeVO act_likeVO = new Act_likeVO();
		act_likeVO.setAct_no(act_no);//1230改的
		act_likeVO.setMem_no(mem_no);//1230改的
		dao.insert(act_likeVO);//1230加的
		return act_likeVO;
	}
	
	public Act_likeVO findByPrimaryKey(String act_no, String mem_no) {
		return dao.findByPrimaryKey(act_no, mem_no);
	}
	
	//查看單一活動按讚數
	public int getAll(String act_no){
		return dao.getOneActCountLike(act_no);
	}
	
	//查看已按讚活動
	public List<Act_likeVO> findByMemNo(String mem_no) {
		return dao.findByMemNo(mem_no);
	}

	
	//刪除 //1230加的
	public void delete(String act_no, String mem_no) {
		dao.delete(act_no, mem_no);
	}
	
}
