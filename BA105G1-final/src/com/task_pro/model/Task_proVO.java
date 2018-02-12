package com.task_pro.model;

public class Task_proVO implements java.io.Serializable{
	private String tp_no;
	private String tp_name;
	private Integer tp_val;
	private String tp_des;
	private byte[] tp_pho;
	private String tp_sta;
	
	public String getTp_no() {
		return tp_no;
	}
	public void setTp_no(String tp_no) {
		this.tp_no = tp_no;
	}
	public String getTp_name() {
		return tp_name;
	}
	public void setTp_name(String tp_name) {
		this.tp_name = tp_name;
	}
	public Integer getTp_val() {
		return tp_val;
	}
	public void setTp_val(Integer tp_val) {
		this.tp_val = tp_val;
	}
	public String getTp_des() {
		return tp_des;
	}
	public void setTp_des(String tp_des) {
		this.tp_des = tp_des;
	}
	public byte[] getTp_pho() {
		return tp_pho;
	}
	public void setTp_pho(byte[] tp_pho) {
		this.tp_pho = tp_pho;
	}
	public String getTp_sta() {
		return tp_sta;
	}
	public void setTp_sta(String tp_sta) {
		this.tp_sta = tp_sta;
	}
	
}
