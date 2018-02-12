package com.mail.model;

import java.io.Serializable;

public class MailVO implements Serializable{
	private String mail_no;
	private String mail_sender;
	private String mail_receiver;
	private String mail_title;
	private java.sql.Timestamp mail_time;
	private String mail_cnt;
	private String mail_status;
	
	public String getMail_no() {
		return mail_no;
	}
	public void setMail_no(String mail_no) {
		this.mail_no = mail_no;
	}
	public String getMail_title() {
		return mail_title;
	}
	public void setMail_title(String mail_title) {
		this.mail_title = mail_title;
	}
	public String getMail_sender() {
		return mail_sender;
	}
	public void setMail_sender(String mail_sender) {
		this.mail_sender = mail_sender;
	}
	public String getMail_receiver() {
		return mail_receiver;
	}
	public void setMail_receiver(String mail_receiver) {
		this.mail_receiver = mail_receiver;
	}
	public java.sql.Timestamp getMail_time() {
		return mail_time;
	}
	public void setMail_time(java.sql.Timestamp mail_time) {
		this.mail_time = mail_time;
	}
	public String getMail_cnt() {
		return mail_cnt;
	}
	public void setMail_cnt(String mail_cnt) {
		this.mail_cnt = mail_cnt;
	}
	public String getMail_status() {
		return mail_status;
	}
	public void setMail_status(String mail_status) {
		this.mail_status = mail_status;
	}
}
