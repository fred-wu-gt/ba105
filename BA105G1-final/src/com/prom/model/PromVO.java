package com.prom.model;

import java.sql.Timestamp;

public class PromVO {
	private String prom_no;
	private String shop_no;
	private Double prom_dis;
	private Timestamp prom_start;
	private Timestamp prom_end;
	/**
	 * @return the prom_no
	 */
	public String getProm_no() {
		return prom_no;
	}
	/**
	 * @param prom_no the prom_no to set
	 */
	public void setProm_no(String prom_no) {
		this.prom_no = prom_no;
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
	 * @return the prom_dis
	 */
	public Double getProm_dis() {
		return prom_dis;
	}
	/**
	 * @param prom_dis the prom_dis to set
	 */
	public void setProm_dis(Double prom_dis) {
		this.prom_dis = prom_dis;
	}
	/**
	 * @return the prom_start
	 */
	public Timestamp getProm_start() {
		return prom_start;
	}
	/**
	 * @param prom_start the prom_start to set
	 */
	public void setProm_start(Timestamp prom_start) {
		this.prom_start = prom_start;
	}
	/**
	 * @return the prom_end
	 */
	public Timestamp getProm_end() {
		return prom_end;
	}
	/**
	 * @param prom_end the prom_end to set
	 */
	public void setProm_end(Timestamp prom_end) {
		this.prom_end = prom_end;
	}
	
}
