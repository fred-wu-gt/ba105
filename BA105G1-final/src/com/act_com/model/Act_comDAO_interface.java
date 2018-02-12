package com.act_com.model;
import java.util.List;

public interface Act_comDAO_interface {
	 public void insert(Act_comVO act_comVO);
	 public void update(Act_comVO act_comVO); 
	 //修改活動留言狀態
	 public void updateAcoStatus(String aco_status, String aco_no);
     public Act_comVO findByPrimaryKey(String aco_no);
     //為了查單一活動的留言區塊 //1230加的
     public List<Act_comVO> findByActNo(String act_no);
     public List<Act_comVO> getAll();
     public void delete(String aco_no);
}
