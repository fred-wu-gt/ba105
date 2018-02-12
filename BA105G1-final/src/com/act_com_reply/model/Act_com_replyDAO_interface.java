package com.act_com_reply.model;

import java.util.List;

public interface Act_com_replyDAO_interface {
	public void insert(Act_com_replyVO act_com_replyVO);
    public void update(Act_com_replyVO act_com_replyVO);
    public Act_com_replyVO findByPrimaryKey(String acr_no);
    public Act_com_replyVO findByFK(String aco_no, String shop_no);//為了看商家是否檢舉過 //0108+
    public List<Act_com_replyVO> findByAcoNo(String aco_no);//撈出某筆活動留言的所有 留言回覆0108+
    public List<Act_com_replyVO> getAll();
    public void delete(String acr_no);
}
