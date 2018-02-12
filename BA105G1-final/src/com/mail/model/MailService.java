package com.mail.model;

import java.util.List;

public class MailService {
	
	private MailDAO_interface dao;
	
	public MailService(){
		dao = new MailDAO();
	}
	
	// mail_no, mem_no, man_no, mail_title, mail_sender, mail_time, mail_cnt
	
	public MailVO insert(String mail_sender, String mail_receiver, String mail_title, String mail_cnt){
		MailVO mailVO = new MailVO();
		mailVO.setMail_sender(mail_sender);
		mailVO.setMail_receiver(mail_receiver);
		mailVO.setMail_title(mail_title);
		mailVO.setMail_cnt(mail_cnt);
		mailVO.setMail_status("未讀");
		dao.insert(mailVO);
		return mailVO;
	}
	
	public List<MailVO> findByMailReceiver(String mail_receiver){
		return dao.findByMailReceiver(mail_receiver);
	}
	
	public MailVO findByMailNo(String mail_no, String mail_receiver){
		return dao.findByMailNo(mail_no, mail_receiver);
	}
	
}
