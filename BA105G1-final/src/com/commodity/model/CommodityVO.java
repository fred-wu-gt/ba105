package com.commodity.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommodityVO implements Serializable{
	
	 private String com_no ;
	 private String com_name ;
	 private String shop_no ;
	 private String fru_no ;
	 private Integer com_price ;
	 private String com_weight ;
	 private String com_remarks ;
	 private byte[] com_pic1 ;
	 private byte[] com_pic2 ;
	 private byte[] com_pic3 ;
	 private Timestamp com_time ;
	 private String com_status ;
	 private Integer com_store ;
	 private Double com_score ;
	 private Integer com_peo ;
	 
	 
	 
	
	 @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((com_no == null) ? 0 : com_no.hashCode());
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
		CommodityVO other = (CommodityVO) obj;
		if (com_no == null) {
			if (other.com_no != null)
				return false;
		} else if (!com_no.equals(other.com_no))
			return false;
		return true;
	}
	
	
	
	
	
	
	/*
	  * 為購物車功能建立的購買數量，目前不映射到DB
	  */
	 private Integer quan;
	 
	public Integer getQuan() {
		return quan;
	}
	public void setQuan(Integer quan) {
		this.quan = quan;
	}
	/*
	 * 為購物車功能建立的購買數量，目前不映射到DB
	 */
	
	
	
	
	public String getCom_no() {
		return com_no;
	}
	
	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getShop_no() {
		return shop_no;
	}
	public void setShop_no(String shop_no) {
		this.shop_no = shop_no;
	}
	public String getFru_no() {
		return fru_no;
	}
	public void setFru_no(String fru_no) {
		this.fru_no = fru_no;
	}
	public Integer getCom_price() {
		return com_price;
	}
	public void setCom_price(Integer com_price) {
		this.com_price = com_price;
	}
	public String getCom_weight() {
		return com_weight;
	}
	public void setCom_weight(String com_weight) {
		this.com_weight = com_weight;
	}
	public String getCom_remarks() {
		return com_remarks;
	}
	public void setCom_remarks(String com_remarks) {
		this.com_remarks = com_remarks;
	}
	public byte[] getCom_pic1() {
		return com_pic1;
	}
	public void setCom_pic1(byte[] com_pic1) {
		this.com_pic1 = com_pic1;
	}
	public byte[] getCom_pic2() {
		return com_pic2;
	}
	public void setCom_pic2(byte[] com_pic2) {
		this.com_pic2 = com_pic2;
	}
	public byte[] getCom_pic3() {
		return com_pic3;
	}
	public void setCom_pic3(byte[] com_pic3) {
		this.com_pic3 = com_pic3;
	}
	public Timestamp getCom_time() {
		return com_time;
	}
	public void setCom_time(Timestamp com_time) {
		this.com_time = com_time;
	}
	public String getCom_status() {
		return com_status;
	}
	public void setCom_status(String com_status) {
		this.com_status = com_status;
	}
	public Integer getCom_store() {
		return com_store;
	}
	public void setCom_store(Integer com_store) {
		this.com_store = com_store;
	}
	public Double getCom_score() {
		return com_score;
	}
	public void setCom_score(Double com_score) {
		this.com_score = com_score;
	}
	public Integer getCom_peo() {
		return com_peo;
	}
	public void setCom_peo(Integer com_peo) {
		this.com_peo = com_peo;
	}
	
	 
	

}
