package com.ord_mas.model;


import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.ord_det.model.Ord_detVO;

public class Ord_masVO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ord_no ;
	private String mem_no ;
	private String shop_no ;
	private Timestamp ord_time ; 
	private String ord_sta ;
	private Integer ord_total ;
	private String ord_rec ;
	private String ord_adr;	
	private String ord_tel ;
	private String ord_can_rea;
	private Set<Ord_detVO> ord_set ;
	
	
	
	
	
	
	
	public Set<Ord_detVO> getOrd_set() {
		return ord_set;
	}
	public void setOrd_set(Set<Ord_detVO> ord_set) {
		this.ord_set = ord_set;
	}
	
	
	
	
	public String getOrd_no() {
		return ord_no;
	}
	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
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
	public Timestamp getOrd_time() {
		return ord_time;
	}
	public void setOrd_time(Timestamp ord_time) {
		this.ord_time = ord_time;
	}
	public String getOrd_sta() {
		return ord_sta;
	}
	public void setOrd_sta(String ord_sta) {
		this.ord_sta = ord_sta;
	}
	public Integer getOrd_total() {
		return ord_total;
	}
	public void setOrd_total(Integer ord_total) {
		this.ord_total = ord_total;
	}
	public String getOrd_rec() {
		return ord_rec;
	}
	public void setOrd_rec(String ord_rec) {
		this.ord_rec = ord_rec;
	}
	public String getOrd_adr() {
		return ord_adr;
	}
	public void setOrd_adr(String ord_adr) {
		this.ord_adr = ord_adr;
	}
	public String getOrd_tel() {
		return ord_tel;
	}
	public void setOrd_tel(String ord_tel) {
		this.ord_tel = ord_tel;
	}
	public String getOrd_can_rea() {
		return ord_can_rea;
	}
	public void setOrd_can_rea(String ord_can_rea) {
		this.ord_can_rea = ord_can_rea;
	}
	
	
	
}
