package com.finance.model;

public class FinanceVO implements java.io.Serializable{
	private String fin_no;
	private String shop_no;
	private Integer fin_cosum;
	private Integer fin_adsum;
	private Integer fin_year;
	private Integer fin_mon;
	
	public String getFin_no() {
		return fin_no;
	}
	public void setFin_no(String fin_no) {
		this.fin_no = fin_no;
	}
	public String getShop_no() {
		return shop_no;
	}
	public void setShop_no(String shop_no) {
		this.shop_no = shop_no;
	}
	public Integer getFin_cosum() {
		return fin_cosum;
	}
	public void setFin_cosum(Integer fin_cosum) {
		this.fin_cosum = fin_cosum;
	}
	public Integer getFin_adsum() {
		return fin_adsum;
	}
	public void setFin_adsum(Integer fin_adsum) {
		this.fin_adsum = fin_adsum;
	}
	public Integer getFin_year() {
		return fin_year;
	}
	public void setFin_year(Integer fin_year) {
		this.fin_year = fin_year;
	}
	public Integer getFin_mon() {
		return fin_mon;
	}
	public void setFin_mon(Integer fin_mon) {
		this.fin_mon = fin_mon;
	}
	
	
}
