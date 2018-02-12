package com.writing.model;

import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.sql.Timestamp;

public class WritingService {
	private WritingDAO_interface dao;

	public WritingService() {
		dao = new WritingJNDIDAO();
	}

	public WritingVO addWriting(String shop_no, String wrt_title, String wrt_cont, byte[] wrt_photo,String wrt_photo_base64
			) {

		WritingVO writingVO = new WritingVO();

		writingVO.setShop_no(shop_no);
		writingVO.setWrt_title(wrt_title);
		writingVO.setWrt_cont(wrt_cont);
		writingVO.setWrt_photo(wrt_photo);
		writingVO.setWrt_photo_base64(wrt_photo_base64);
		writingVO.setWrt_sta("正常");
		dao.insert(writingVO);

		return writingVO;
	}

	public WritingVO updateWriting(String wrt_no, String shop_no, String wrt_title, String wrt_cont, byte[] wrt_photo,String wrt_photo_base64,
			String wrt_sta, Date wrt_time) {

		WritingVO writingVO = new WritingVO();
		writingVO.setWrt_no(wrt_no);
		writingVO.setShop_no(shop_no);
		writingVO.setWrt_title(wrt_title);
		writingVO.setWrt_cont(wrt_cont);
		writingVO.setWrt_photo(wrt_photo);
		writingVO.setWrt_photo_base64(wrt_photo_base64);
		writingVO.setWrt_sta(wrt_sta);
		
		
		dao.update(writingVO);

		return writingVO;
	}

	public void deleteWriting(String wrt_no) {
		dao.delete(wrt_no);
	}

	public WritingVO getOneWriting(String wrt_no) {
		return dao.findByPrimaryKey(wrt_no);
	}

	public List<WritingVO> getAll() {
		return dao.getAll();
	}

	public List<WritingVO> getAll(Map<String, String[]> map) {

		return dao.getAll(map);
	}
	
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		public void updateWrt_sta(String wrt_sta, String wrt_no) {
			dao.updateWrt_sta(wrt_sta, wrt_no);
		}
		//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		//世霖開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		public WritingVO findByshopno(String shop_no) {
			return dao.findByshopno(shop_no);
		}
		//世霖結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
}
