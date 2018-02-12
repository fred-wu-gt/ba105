package com.exc_rec.model;

import java.sql.Timestamp;

public class Exc_recVO implements java.io.Serializable{
	private String er_no;
	private String tp_no;
	private String mem_no;
	private Timestamp er_time;
	
	public String getEr_no() {
		return er_no;
	}
	public void setEr_no(String er_no) {
		this.er_no = er_no;
	}
	public String getTp_no() {
		return tp_no;
	}
	public void setTp_no(String tp_no) {
		this.tp_no = tp_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Timestamp getEr_time() {
		return er_time;
	}
	public void setEr_time(Timestamp er_time) {
		this.er_time = er_time;
	}

}
