package com.act_join_det.model;

import java.util.List;

public interface Act_join_detDAO_interface {
	public void insert(Act_join_detVO act_join_detVO);
	//修改報名活動狀態
	public void updateAjStatus(String aj_status, String act_no, String mem_no);
    public Act_join_detVO findByPrimaryKey(String act_no, String mem_no);//複合主鍵
    //查看已報名活動
    public List<Act_join_detVO> findByMemNo(String mem_no);
    //查詢報名名單0112+
    public List<Act_join_detVO> findByActNo(String act_no);
    public List<Act_join_detVO> getAll();
    public void delete(String act_no, String mem_no);
}
