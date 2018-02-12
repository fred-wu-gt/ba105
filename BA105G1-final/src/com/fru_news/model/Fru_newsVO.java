package com.fru_news.model;

import java.sql.Timestamp;

public class Fru_newsVO implements java.io.Serializable{
	private String fn_no;
	private String fn_tit;
	private String fn_con;
	private byte[] fn_pho;
	private Timestamp fn_time;
	
	public String getFn_no() {
		return fn_no;
	}
	public void setFn_no(String fn_no) {
		this.fn_no = fn_no;
	}
	public String getFn_tit() {
		return fn_tit;
	}
	public void setFn_tit(String fn_tit) {
		this.fn_tit = fn_tit;
	}
	public String getFn_con() {
		return fn_con;
	}
	public void setFn_con(String fn_con) {
		this.fn_con = fn_con;
	}
	public byte[] getFn_pho() {
		return fn_pho;
	}
	public void setFn_pho(byte[] fn_pho) {
		this.fn_pho = fn_pho;
	}
	public Timestamp getFn_time() {
		return fn_time;
	}
	public void setFn_time(Timestamp fn_time) {
		this.fn_time = fn_time;
	}
	
}
