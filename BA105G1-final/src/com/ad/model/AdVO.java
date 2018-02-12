package com.ad.model;

import java.sql.Timestamp;

public class AdVO {
	
	private String ad_no;             
	private String shop_no;           
	private Timestamp ad_idesta;         
	private Timestamp ad_start;          
	private Timestamp ad_end;             
	private Integer ad_exp;            
	private String ad_stat;            
	private byte[] ad_photo;
	
	
	
	/**
	 * @return the ad_no
	 */
	public String getAd_no() {
		return ad_no;
	}
	/**
	 * @param ad_no the ad_no to set
	 */
	public void setAd_no(String ad_no) {
		this.ad_no = ad_no;
	}
	/**
	 * @return the shop_no
	 */
	public String getShop_no() {
		return shop_no;
	}
	/**
	 * @param shop_no the shop_no to set
	 */
	public void setShop_no(String shop_no) {
		this.shop_no = shop_no;
	}
	/**
	 * @return the ad_idesta
	 */
	public Timestamp getAd_idesta() {
		return ad_idesta;
	}
	/**
	 * @param ad_idesta the ad_idesta to set
	 */
	public void setAd_idesta(Timestamp ad_idesta) {
		this.ad_idesta = ad_idesta;
	}
	/**
	 * @return the ad_start
	 */
	public Timestamp getAd_start() {
		return ad_start;
	}
	/**
	 * @param ad_start the ad_start to set
	 */
	public void setAd_start(Timestamp ad_start) {
		this.ad_start = ad_start;
	}
	/**
	 * @return the ad_end
	 */
	public Timestamp getAd_end() {
		return ad_end;
	}
	/**
	 * @param ad_end the ad_end to set
	 */
	public void setAd_end(Timestamp ad_end) {
		this.ad_end = ad_end;
	}
	/**
	 * @return the ad_exp
	 */
	public Integer getAd_exp() {
		return ad_exp;
	}
	/**
	 * @param ad_exp the ad_exp to set
	 */
	public void setAd_exp(Integer ad_exp) {
		this.ad_exp = ad_exp;
	}
	/**
	 * @return the ad_stat
	 */
	public String getAd_stat() {
		return ad_stat;
	}
	/**
	 * @param ad_stat the ad_stat to set
	 */
	public void setAd_stat(String ad_stat) {
		this.ad_stat = ad_stat;
	}
	/**
	 * @return the ad_photo
	 */
	public byte[] getAd_photo() {
		return ad_photo;
	}
	/**
	 * @param ad_photo the ad_photo to set
	 */
	public void setAd_photo(byte[] ad_photo) {
		this.ad_photo = ad_photo;
	}  
	
	
}
