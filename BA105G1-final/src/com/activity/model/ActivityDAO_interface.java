package com.activity.model;

import java.util.List;

import com.act_cat.model.Act_catVO;

public interface ActivityDAO_interface {
	 //新增活動
	 public void insert(ActivityVO activityVO);
	 //自增主鍵綁定0112+
	 public void insertWithActCat(ActivityVO activityVO , List<Act_catVO> list);
	 //修改活動資料(不含活動狀態)
     public void update(ActivityVO activityVO);  
     //修改活動資料(只有活動狀態)
     public void updateActStatus(String act_status, String act_no); 
     //修改活動資料(只有直播狀態)
     public void updateLiveStatus(String act_ls, String act_no); 
    
     //查看活動詳情
     public ActivityVO findByActNo(String act_no);
     //顯示自家活動列表
     public List<ActivityVO> findByShopNo(String shop_no);
     //顯示所有活動列表(會員)
     public List<ActivityVO> getAllAct();
     //關鍵字搜尋活動
     public List<ActivityVO> search(String pattern); 
     
     public void updateActStatus2(String act_status2, String act_no);
     
     //世霖開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
     public List<ActivityVO> findByShopno(String shop_no);
     //世霖結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
       
    
}
