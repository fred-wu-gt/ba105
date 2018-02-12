package com.ord_det.model;

public class Ord_detVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ord_no;
	private String com_no;
	private Integer od_quan;
	private Double od_score;

	public String getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}

	public String getCom_no() {
		return com_no;
	}

	public void setCom_no(String com_no) {
		this.com_no = com_no;
	}

	public Integer getOd_quan() {
		return od_quan;
	}

	public void setOd_quan(Integer od_quan) {
		this.od_quan = od_quan;
	}

	public Double getOd_score() {
		return od_score;
	}

	public void setOd_score(Double od_score) {
		this.od_score = od_score;
	}

}
