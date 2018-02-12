package com.member.model;

import java.util.*;

public interface MemberDAO_Interface {

	void add(MemberVO memVO);
	void update(MemberVO memVO);
	void delete(String mem_id);
	MemberVO findByMem_id(String mem_id);
	MemberVO findByMem_no(String mem_no);
	
	public List<MemberVO> getAll();
	
	
	
	//=============宸鈞開始=====================
	//修改會員點數的方法
	public void updateMem_val(MemberVO memVO);
	//=============宸鈞結束=====================
	
}
