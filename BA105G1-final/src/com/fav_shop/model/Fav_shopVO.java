package com.fav_shop.model;

import java.io.Serializable;
import java.sql.*;


public class Fav_shopVO implements Serializable{
	private String mem_no;
	private String shop_no;
		
	public Fav_shopVO(){
		super();
	}

	public Fav_shopVO(String mem_no, String shop_no) {
		super();
		this.mem_no = mem_no;
		this.shop_no = shop_no;
	}

	public String getMem_no() {
		return mem_no;
	}


	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}


	public String getShop_no() {
		return shop_no;
	}


	public void setShop_no(String shop_no) {
		this.shop_no = shop_no;
	}
	
	
	
	
	
}
