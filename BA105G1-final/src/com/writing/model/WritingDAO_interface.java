package com.writing.model;

import java.util.*;

public interface WritingDAO_interface {
	public Integer insert(WritingVO writingVO);

	public Integer update(WritingVO writingVO);

	public Integer delete(String wrt_no);

	public WritingVO findByPrimaryKey(String wrt_no);

	public List<WritingVO> getAll();
	
	public List<WritingVO> getAll(Map<String, String[]> map);
	
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		public void updateWrt_sta(String wrt_sta, String wrt_no);//改成正常或隱藏
		//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		//世霖開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		public WritingVO findByshopno(String shop_no);
		//世霖結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
}