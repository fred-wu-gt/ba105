package com.mail.model;
import java.util.List;

public interface MailDAO_interface {
	public void insert(MailVO mailVO);
	public List<MailVO> findByMailReceiver(String mail_receiver);
	public MailVO findByMailNo(String mail_no, String mail_receiver);
}
