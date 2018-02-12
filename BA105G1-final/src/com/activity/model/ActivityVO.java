package com.activity.model;

import java.io.Serializable;

public class ActivityVO implements Serializable{
	
	private String act_no;
	private String shop_no;
	private String act_name;
	private byte[] act_pic;
	private String act_pic_base64;
	private java.sql.Timestamp act_start;
	private java.sql.Timestamp act_end;
	private String act_art;
	private String act_status;
	private String act_status2;
	private String act_ls;
	private byte[] act_live;

	public String getAct_no() {
		return act_no;
	}
	public void setAct_no(String act_no) {
		this.act_no = act_no;
	}
	public String getShop_no() {
		return shop_no;
	}
	public void setShop_no(String shop_no) {
		this.shop_no = shop_no;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public byte[] getAct_pic() {
		return act_pic;
	}
	public void setAct_pic(byte[] act_pic) {
		this.act_pic = act_pic;
	}
	public String getAct_pic_base64() {
		return act_pic_base64;
	}
	public void setAct_pic_base64(String act_pic_base64) {
		this.act_pic_base64 = act_pic_base64;
	}
	public java.sql.Timestamp getAct_start() {
		return act_start;
	}
	public void setAct_start(java.sql.Timestamp act_start) {
		this.act_start = act_start;
	}
	public java.sql.Timestamp getAct_end() {
		return act_end;
	}
	public void setAct_end(java.sql.Timestamp act_end) {
		this.act_end = act_end;
	}
	
	public String getAct_art() {
		return act_art;
	}
	public void setAct_art(String act_art) {
		this.act_art = act_art;
	}
	public String getAct_status() {
		return act_status;
	}
	public void setAct_status(String act_status) {
		this.act_status = act_status;
	}
	public String getAct_status2() {
		return act_status2;
	}
	public void setAct_status2(String act_status2) {
		this.act_status2 = act_status2;
	}
	public String getAct_ls() {
		return act_ls;
	}
	public void setAct_ls(String act_ls) {
		this.act_ls = act_ls;
	}
	public byte[] getAct_live() {
		return act_live;
	}
	public void setAct_live(byte[] act_live) {
		this.act_live = act_live;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((act_no == null) ? 0 : act_no.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityVO other = (ActivityVO) obj;
		if (act_no == null) {
			if (other.act_no != null)
				return false;
		} else if (!act_no.equals(other.act_no))
			return false;
		return true;
	}
//	@Override
//	public int compareTo(ActivityVO that) {
//		int result;
//		result = act_start.compareTo(that.act_start);
//		return result;
//	}

	
}
