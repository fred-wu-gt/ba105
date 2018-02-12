 package com.shop.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ShopVO implements Serializable {
	private String shop_no;
	private String shop_name;
	private String shop_id;
	private String shop_psw;
	private String shop_email;
	private String shop_phone;
	private String shop_loc;
	private Double shop_lat;
	private Double shop_lon;
	private String shop_desc;
	private Integer shop_val;
	private Integer shop_cosum;
	private Integer shop_adsum;
	private String shop_bank;
	private String shop_acc;
	private String shop_vis;
	private String shop_stat;
	private Integer shop_point;
	private Double shop_score;
	private byte[] shop_proof;
	private byte[] shop_photo;
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
	 * @return the shop_name
	 */
	public String getShop_name() {
		return shop_name;
	}
	/**
	 * @param shop_name the shop_name to set
	 */
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	/**
	 * @return the shop_id
	 */
	public String getShop_id() {
		return shop_id;
	}
	/**
	 * @param shop_id the shop_id to set
	 */
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	/**
	 * @return the shop_psw
	 */
	public String getShop_psw() {
		return shop_psw;
	}
	/**
	 * @param shop_psw the shop_psw to set
	 */
	public void setShop_psw(String shop_psw) {
		this.shop_psw = shop_psw;
	}
	/**
	 * @return the shop_email
	 */
	public String getShop_email() {
		return shop_email;
	}
	/**
	 * @param shop_email the shop_email to set
	 */
	public void setShop_email(String shop_email) {
		this.shop_email = shop_email;
	}
	/**
	 * @return the shop_phone
	 */
	public String getShop_phone() {
		return shop_phone;
	}
	/**
	 * @param shop_phone the shop_phone to set
	 */
	public void setShop_phone(String shop_phone) {
		this.shop_phone = shop_phone;
	}
	/**
	 * @return the shop_loc
	 */
	public String getShop_loc() {
		return shop_loc;
	}
	/**
	 * @param shop_loc the shop_loc to set
	 */
	public void setShop_loc(String shop_loc) {
		this.shop_loc = shop_loc;
	}
	/**
	 * @return the shop_lat
	 */
	public Double getShop_lat() {
		return shop_lat;
	}
	/**
	 * @param shop_lat the shop_lat to set
	 */
	public void setShop_lat(Double shop_lat) {
		this.shop_lat = shop_lat;
	}
	/**
	 * @return the shop_lon
	 */
	public Double getShop_lon() {
		return shop_lon;
	}
	/**
	 * @param shop_lon the shop_lon to set
	 */
	public void setShop_lon(Double shop_lon) {
		this.shop_lon = shop_lon;
	}
	/**
	 * @return the shop_desc
	 */
	public String getShop_desc() {
		return shop_desc;
	}
	/**
	 * @param shop_desc the shop_desc to set
	 */
	public void setShop_desc(String shop_desc) {
		this.shop_desc = shop_desc;
	}
	/**
	 * @return the shop_val
	 */
	public Integer getShop_val() {
		return shop_val;
	}
	/**
	 * @param shop_val the shop_val to set
	 */
	public void setShop_val(Integer shop_val) {
		this.shop_val = shop_val;
	}
	/**
	 * @return the shop_cosum
	 */
	public Integer getShop_cosum() {
		return shop_cosum;
	}
	/**
	 * @param shop_cosum the shop_cosum to set
	 */
	public void setShop_cosum(Integer shop_cosum) {
		this.shop_cosum = shop_cosum;
	}
	/**
	 * @return the shop_adsum
	 */
	public Integer getShop_adsum() {
		return shop_adsum;
	}
	/**
	 * @param shop_adsum the shop_adsum to set
	 */
	public void setShop_adsum(Integer shop_adsum) {
		this.shop_adsum = shop_adsum;
	}
	/**
	 * @return the shop_bank
	 */
	public String getShop_bank() {
		return shop_bank;
	}
	/**
	 * @param shop_bank the shop_bank to set
	 */
	public void setShop_bank(String shop_bank) {
		this.shop_bank = shop_bank;
	}
	/**
	 * @return the shop_acc
	 */
	public String getShop_acc() {
		return shop_acc;
	}
	/**
	 * @param shop_acc the shop_acc to set
	 */
	public void setShop_acc(String shop_acc) {
		this.shop_acc = shop_acc;
	}
	/**
	 * @return the shop_vis
	 */
	public String getShop_vis() {
		return shop_vis;
	}
	/**
	 * @param shop_vis the shop_vis to set
	 */
	public void setShop_vis(String shop_vis) {
		this.shop_vis = shop_vis;
	}
	/**
	 * @return the shop_stat
	 */
	public String getShop_stat() {
		return shop_stat;
	}
	/**
	 * @param shop_stat the shop_stat to set
	 */
	public void setShop_stat(String shop_stat) {
		this.shop_stat = shop_stat;
	}
	/**
	 * @return the shop_point
	 */
	public Integer getShop_point() {
		return shop_point;
	}
	/**
	 * @param shop_point the shop_point to set
	 */
	public void setShop_point(Integer shop_point) {
		this.shop_point = shop_point;
	}
	/**
	 * @return the shop_score
	 */
	public Double getShop_score() {
		return shop_score;
	}
	/**
	 * @param shop_score the shop_score to set
	 */
	public void setShop_score(Double shop_score) {
		this.shop_score = shop_score;
	}
	/**
	 * @return the shop_proof
	 */
	public byte[] getShop_proof() {
		return shop_proof;
	}
	/**
	 * @param shop_proof the shop_proof to set
	 */
	public void setShop_proof(byte[] shop_proof) {
		this.shop_proof = shop_proof;
	}
	/**
	 * @return the shop_photo
	 */
	public byte[] getShop_photo() {
		return shop_photo;
	}
	/**
	 * @param shop_photo the shop_photo to set
	 */
	public void setShop_photo(byte[] shop_photo) {
		this.shop_photo = shop_photo;
	}

	

}
