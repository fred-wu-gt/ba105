package com.fav_com.model;

import java.io.Serializable;
import java.sql.*;

public class Fav_comVO implements Serializable{

	private String mem_no;
	private String com_no;
	
	public Fav_comVO(){
		super();
	}

	public Fav_comVO(String mem_no, String com_no) {
		super();
		this.mem_no = mem_no;
		this.com_no = com_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getCom_no() {
		return com_no;
	}

	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}
	
	
	
	
}
