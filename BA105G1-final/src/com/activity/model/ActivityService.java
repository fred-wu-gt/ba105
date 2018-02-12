package com.activity.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.act_cat.model.Act_catVO;

public class ActivityService {
	
	private ActivityDAO_interface dao;
	
	public ActivityService(){
		dao = new ActivityDAO();
	}
	
	//新增活動
	public ActivityVO addActivity(String shop_no, String act_name, 
			byte[] act_pic, String act_pic_base64, Timestamp act_start,
			Timestamp act_end, String act_art){
		ActivityVO activityVO = new ActivityVO();
		
		activityVO.setShop_no(shop_no);
		activityVO.setAct_name(act_name);
		activityVO.setAct_pic(act_pic);
		activityVO.setAct_pic_base64(act_pic_base64);
		activityVO.setAct_start(act_start);
		activityVO.setAct_end(act_end);
		activityVO.setAct_art(act_art);
		activityVO.setAct_status("未開始");
		activityVO.setAct_ls("未開始");
		dao.insert(activityVO);
		return activityVO;
	}
	//自增主鍵綁定0112+
	public void insertWithActCat(ActivityVO activityVO , List<Act_catVO> list){
		dao.insertWithActCat(activityVO, list);
	}
	
	//修改活動資料(不含狀態)
	public ActivityVO updateActivity(String act_no, String shop_no, String act_name,
			byte[] act_pic, String act_pic_base64, Timestamp act_start,
			Timestamp act_end, String act_art){
		ActivityVO activityVO = new ActivityVO();
		activityVO.setAct_no(act_no);
		activityVO.setShop_no(shop_no);
		activityVO.setAct_name(act_name);
		activityVO.setAct_pic(act_pic);
		activityVO.setAct_pic_base64(act_pic_base64);
		activityVO.setAct_start(act_start);
		activityVO.setAct_end(act_end);
		activityVO.setAct_art(act_art);
		dao.update(activityVO);
		return activityVO;
	}
	
	//修改活動資料(只有活動狀態)
	public void updateActStatus(String act_status, String act_no){
		dao.updateActStatus(act_status, act_no);
	}
	
	//修改活動資料(只有直播狀態)
	public void updateLiveStatus(String act_ls, String act_no){
		dao.updateActStatus(act_ls, act_no);
	}
	
	
	//顯示自家活動列表(商家)新到舊
	public List<ActivityVO> findByShopNo(String shop_no) {
		return dao.findByShopNo(shop_no);
	}
	
	//顯示所有活動列表(會員)舊到新
	public List<ActivityVO> getAllRev() {
		return dao.getAllAct().stream().sorted((s1, s2)-> s1.getAct_start().compareTo(s2.getAct_start())).collect(Collectors.toList());
	}
	//顯示所有活動列表(會員)新到舊
	public List<ActivityVO> getAll() {
		return dao.getAllAct();
	}
	//查看活動詳情
	public ActivityVO findByActNo(String act_no){
		return dao.findByActNo(act_no);
	}
	
	//關鍵字搜尋活動
	public List<ActivityVO> search(String pattern){
		return dao.search(pattern);
	}
	
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void updateActStatus2(String act_status2, String act_no){
		dao.updateActStatus2(act_status2, act_no);
	}		
	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
	//世霖開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public List<ActivityVO> findByShopno(String shop_no) {
		return dao.findByShopno(shop_no);
	}
//世霖結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~		
	
	
	
	
}
