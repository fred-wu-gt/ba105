package com.wri_mes_reply.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class Wri_mes_replyVO implements Serializable{

	private String wmsgr_no;
	private String wmsg_no;
	private String shop_no;
	private String wcr_cont;
	private Timestamp wcr_time;
	
	public String getWmsgr_no() {
		return wmsgr_no;
	}
	public void setWmsgr_no(String wmsgr_no) {
		this.wmsgr_no = wmsgr_no;
	}
	public String getWmsg_no() {
		return wmsg_no;
	}
	public void setWmsg_no(String wmsg_no) {
		this.wmsg_no = wmsg_no;
	}
	public String getShop_no() {
		return shop_no;
	}
	public void setShop_no(String shop_no) {
		this.shop_no = shop_no;
	}
	public String getWcr_cont() {
		return wcr_cont;
	}
	public void setWcr_cont(String wcr_cont) {
		this.wcr_cont = wcr_cont;
	}
	public Timestamp getWcr_time() {
		return wcr_time;
	}
	public void setWcr_time(Timestamp wcr_time) {
		this.wcr_time = wcr_time;
	}
	
}
