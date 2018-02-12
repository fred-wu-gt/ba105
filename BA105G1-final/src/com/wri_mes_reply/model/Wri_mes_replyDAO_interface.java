package com.wri_mes_reply.model;

import java.util.List;

public interface Wri_mes_replyDAO_interface {
	public Integer insert(Wri_mes_replyVO wri_mes_replyVO);

	public Integer update(Wri_mes_replyVO wri_mes_replyVO);

	public Integer delete(String wmsgr_no);

	public Wri_mes_replyVO findByPrimaryKey(String wmsgr_no);
	
	public List<Wri_mes_replyVO> findAllWri_mes_replyByWmsg_no(String wmsg_no);

	public List<Wri_mes_replyVO> getAll();
}

