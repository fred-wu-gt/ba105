package com.fru.pri.model;

import java.io.Serializable;
import java.sql.*;

public class Fru_priVO implements Serializable{

	private String fp_no;
	private String fru_no;
	private String fp_name;
	private Timestamp fp_time;
	private Double fp_pri;
	
	public Fru_priVO(){
		super();
	}

	public Fru_priVO(String fp_no, String fru_no, String fp_name, Timestamp fp_time, Double fp_pri) {
		super();
		this.fp_no = fp_no;
		this.fru_no = fru_no;
		this.fp_name = fp_name;
		this.fp_time = fp_time;
		this.fp_pri = fp_pri;
	}

	public String getFp_no() {
		return fp_no;
	}

	public void setFp_no(String fp_no) {
		this.fp_no = fp_no;
	}

	public String getFru_no() {
		return fru_no;
	}

	public void setFru_no(String fru_no) {
		this.fru_no = fru_no;
	}

	public String getFp_name() {
		return fp_name;
	}

	public void setFp_name(String fp_name) {
		this.fp_name = fp_name;
	}

	public Timestamp getFp_time() {
		return fp_time;
	}

	public void setFp_time(Timestamp fp_time) {
		this.fp_time = fp_time;
	}

	public Double getFp_pri() {
		return fp_pri;
	}

	public void setFp_pri(Double fp_pri) {
		this.fp_pri = fp_pri;
	}
	
	
	
	
}
