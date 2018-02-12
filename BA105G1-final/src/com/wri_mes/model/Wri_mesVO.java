package com.wri_mes.model;
import java.sql.Timestamp;

public class Wri_mesVO {

	private String wmsg_no;
	private String wrt_no;
	private String mem_no;
	private Timestamp wmsg_date;
	private String wmsg_cont;
	private String wmsg_stat;
	
	public String getWmsg_no() {
		return wmsg_no;
	}
	public void setWmsg_no(String wmsg_no) {
		this.wmsg_no = wmsg_no;
	}
	public String getWrt_no() {
		return wrt_no;
	}
	public void setWrt_no(String wrt_no) {
		this.wrt_no = wrt_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Timestamp getWmsg_date() {
		return wmsg_date;
	}
	public void setWmsg_date(Timestamp wmsg_date) {
		this.wmsg_date = wmsg_date;
	}
	public String getWmsg_cont() {
		return wmsg_cont;
	}
	public void setWmsg_cont(String wmsg_cont) {
		this.wmsg_cont = wmsg_cont;
	}
	public String getWmsg_stat() {
		return wmsg_stat;
	}
	public void setWmsg_stat(String wmsg_stat) {
		this.wmsg_stat = wmsg_stat;
	}
}
