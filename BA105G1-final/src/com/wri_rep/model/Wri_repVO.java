package com.wri_rep.model;

import java.io.Serializable;

public class Wri_repVO implements Serializable {

	private String wre_no;
	private String wrt_no;
	private String mem_no;
	private String wre_rsn;
	private String wre_stat;
	private String wre_cont;

	public String getWre_no() {
		return wre_no;
	}

	public void setWre_no(String wre_no) {
		this.wre_no = wre_no;
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

	public String getWre_rsn() {
		return wre_rsn;
	}

	public void setWre_rsn(String wre_rsn) {
		this.wre_rsn = wre_rsn;
	}

	public String getWre_stat() {
		return wre_stat;
	}

	public void setWre_stat(String wre_stat) {
		this.wre_stat = wre_stat;
	}

	public String getWre_cont() {
		return wre_cont;
	}

	public void setWre_cont(String wre_cont) {
		this.wre_cont = wre_cont;
	}

}
