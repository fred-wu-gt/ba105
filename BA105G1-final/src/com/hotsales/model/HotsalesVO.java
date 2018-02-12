package com.hotsales.model;


import java.sql.Timestamp;

public class HotsalesVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String hot_no ;
	private String com_no ;
	private Timestamp hot_date ;
	
	
	
	public String getHot_no() {
		return hot_no;
	}
	public void setHot_no(String hot_no) {
		this.hot_no = hot_no;
	}
	public String getCom_no() {
		return com_no;
	}
	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}
	public Timestamp getHot_date() {
		return hot_date;
	}
	public void setHot_date(Timestamp hot_date) {
		this.hot_date = hot_date;
	}



}
