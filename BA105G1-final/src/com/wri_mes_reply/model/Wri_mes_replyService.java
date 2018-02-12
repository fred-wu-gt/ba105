package com.wri_mes_reply.model;

import java.util.List;

public class Wri_mes_replyService {

	private Wri_mes_replyDAO_interface dao;

	public Wri_mes_replyService() {

		dao = new Wri_mes_replyJDBCDAO();
	}

	public Wri_mes_replyVO addWriting_mes_reply(String wmsgr_no, String wmsg_no, String shop_no, String wcr_cont) {

		Wri_mes_replyVO wri_mes_replyVO = new Wri_mes_replyVO();

		wri_mes_replyVO.setWmsgr_no(wmsgr_no);
		wri_mes_replyVO.setWmsg_no(wmsg_no);
		wri_mes_replyVO.setShop_no(shop_no);
		wri_mes_replyVO.setWcr_cont(wcr_cont);
		dao.insert(wri_mes_replyVO);

		return wri_mes_replyVO;

	}

	public Wri_mes_replyVO updateWriting_mes_reply(String wmsgr_no, String wmsg_no, String shop_no, String wcr_cont) {

		Wri_mes_replyVO wri_mes_replyVO = new Wri_mes_replyVO();

		wri_mes_replyVO.setWmsgr_no(wmsgr_no);
		wri_mes_replyVO.setWmsg_no(wmsg_no);
		wri_mes_replyVO.setShop_no(shop_no);
		wri_mes_replyVO.setWcr_cont(wcr_cont);
		dao.update(wri_mes_replyVO);

		return wri_mes_replyVO;

	}

	public void deleteWriting_mes_reply(String wmsgr_no) {
		dao.delete(wmsgr_no);
	}

	public Wri_mes_replyVO getOneWriting_mes_reply(String wmsgr_no) {
		return dao.findByPrimaryKey(wmsgr_no);
	}
	
	public List<Wri_mes_replyVO> getAllWriting_mes_replyByWmsg_no(String wmsg_no){
		return dao.findAllWri_mes_replyByWmsg_no(wmsg_no);
	}

	public List<Wri_mes_replyVO> getAll() {
		return dao.getAll();

	}

}
