package com.mem_ser_mail.model;

public class Mem_ser_mailService {
	
	private Mem_ser_mailDAO_interface  dao;
	
	public Mem_ser_mailService(){
		dao = new Mem_ser_mailDAO();
	}
	
	// mail_no, mem_no, man_no, mail_title, mail_sender, mail_time, mail_cnt
	
	public void insert(String mem_no, String man_no, String mail_title, String mail_sender, String mail_cnt){
		Mem_ser_mailVO mem_ser_mailVO = new Mem_ser_mailVO();
		mem_ser_mailVO.setMem_no(mem_no);
		mem_ser_mailVO.setMan_no(man_no);
		mem_ser_mailVO.setMail_title(mail_title);
		mem_ser_mailVO.setMail_sender(mail_sender);
		mem_ser_mailVO.setMail_cnt(mail_cnt);
	}
}
