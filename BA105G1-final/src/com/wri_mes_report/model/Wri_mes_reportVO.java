package com.wri_mes_report.model;

import java.io.Serializable;

public class Wri_mes_reportVO implements Serializable {

	private String wmrpt_no;
	private String wmsg_no;
	private String mem_no;
	private String wmrpt_rsn;
	private String wmrpt_stat;
	private String wmrpt_cont;

	public String getWmrpt_no() {
		return wmrpt_no;
	}

	public void setWmrpt_no(String wmrpt_no) {
		this.wmrpt_no = wmrpt_no;
	}

	public String getWmsg_no() {
		return wmsg_no;
	}

	public void setWmsg_no(String wmsg_no) {
		this.wmsg_no = wmsg_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getWmrpt_rsn() {
		return wmrpt_rsn;
	}

	public void setWmrpt_rsn(String wmrpt_rsn) {
		this.wmrpt_rsn = wmrpt_rsn;
	}

	public String getWmrpt_stat() {
		return wmrpt_stat;
	}

	public void setWmrpt_stat(String wmrpt_stat) {
		this.wmrpt_stat = wmrpt_stat;
	}

	public String getWmrpt_cont() {
		return wmrpt_cont;
	}

	public void setWmrpt_cont(String wmrpt_cont) {
		this.wmrpt_cont = wmrpt_cont;
	}

}
