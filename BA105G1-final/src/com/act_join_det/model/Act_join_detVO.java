package com.act_join_det.model;

public class Act_join_detVO {
	private String act_no;
	private String mem_no;
	private java.sql.Timestamp aj_time;
	private String aj_status;
	
	public String getAct_no() {
		return act_no;
	}
	public void setAct_no(String act_no) {
		this.act_no = act_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public java.sql.Timestamp getAj_time() {
		return aj_time;
	}
	public void setAj_time(java.sql.Timestamp aj_time) {
		this.aj_time = aj_time;
	}
	public String getAj_status() {
		return aj_status;
	}
	public void setAj_status(String aj_status) {
		this.aj_status = aj_status;
	}


}
