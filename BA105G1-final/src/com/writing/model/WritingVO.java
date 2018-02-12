package com.writing.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class WritingVO implements Serializable {

	private String wrt_no;
	private String shop_no;
	private String wrt_title;
	private String wrt_cont;
	private byte[] wrt_photo;
	private String wrt_photo_base64;
	private String wrt_sta;
	private Timestamp wrt_time;
	public String getWrt_no() {
		return wrt_no;
	}
	public void setWrt_no(String wrt_no) {
		this.wrt_no = wrt_no;
	}
	public String getShop_no() {
		return shop_no;
	}
	public void setShop_no(String shop_no) {
		this.shop_no = shop_no;
	}
	public String getWrt_title() {
		return wrt_title;
	}
	public void setWrt_title(String wrt_title) {
		this.wrt_title = wrt_title;
	}
	public String getWrt_cont() {
		return wrt_cont;
	}
	public void setWrt_cont(String wrt_cont) {
		this.wrt_cont = wrt_cont;
	}
	public byte[] getWrt_photo() {
		return wrt_photo;
	}
	public void setWrt_photo(byte[] wrt_photo) {
		this.wrt_photo = wrt_photo;
	}
	public String getWrt_photo_base64() {
		return wrt_photo_base64;
	}
	public void setWrt_photo_base64(String wrt_photo_base64) {
		this.wrt_photo_base64 = wrt_photo_base64;
	}
	public String getWrt_sta() {
		return wrt_sta;
	}
	public void setWrt_sta(String wrt_sta) {
		this.wrt_sta = wrt_sta;
	}
	public Timestamp getWrt_time() {
		return wrt_time;
	}
	public void setWrt_time(Timestamp wrt_time) {
		this.wrt_time = wrt_time;
	}

	

	
}
