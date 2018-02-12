package com.member.model;

import java.io.Serializable;
import java.sql.*;


@SuppressWarnings("serial")
public class MemberVO implements Serializable{
	
	private String mem_no;
	private String mem_id;
	private String mem_psw;
	private String mem_name;
	private String mem_gen;
	private Date mem_bir;
	private String mem_email;
	private String mem_phone;
	private String mem_loc;
	private byte[] mem_photo;
	private String mem_photo_base64;
	private String mem_stat;
	private String mem_poin;
	private String mem_val;
	private String check;
	
	public MemberVO(){
		super();
	}

	

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_psw() {
		return mem_psw;
	}

	public void setMem_psw(String mem_psw) {
		this.mem_psw = mem_psw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_gen() {
		return mem_gen;
	}

	public void setMem_gen(String mem_gen) {
		this.mem_gen = mem_gen;
	}

	public Date getMem_bir() {
		return mem_bir;
	}

	public void setMem_bir(Date mem_bir) {
		this.mem_bir = mem_bir;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_loc() {
		return mem_loc;
	}

	public void setMem_loc(String mem_loc) {
		this.mem_loc = mem_loc;
	}

	public byte[] getMem_photo() {
		return mem_photo;
	}

	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}

	public String getMem_photo_base64() {
		return mem_photo_base64;
	}



	public void setMem_photo_base64(String mem_photo_base64) {
		this.mem_photo_base64 = mem_photo_base64;
	}
	
	
	public String getMem_stat() {
		return mem_stat;
	}

	public void setMem_stat(String mem_stat) {
		this.mem_stat = mem_stat;
	}

	public String getMem_poin() {
		return mem_poin;
	}

	public void setMem_poin(String mem_poin) {
		this.mem_poin = mem_poin;
	}

	public String getMem_val() {
		return mem_val;
	}

	public void setMem_val(String mem_val) {
		this.mem_val = mem_val;
	}



	public String getCheck() {
		return check;
	}



	public void setCheck(String check) {
		this.check = check;
	}



	

	
	
	
	
	
	
}
