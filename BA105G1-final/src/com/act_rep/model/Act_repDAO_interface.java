package com.act_rep.model;

import java.util.List;

public interface Act_repDAO_interface {
    public void insert(Act_repVO act_repVO);
    public void update(Act_repVO act_repVO);
    //修改活動檢舉狀態
  	public void updateActStatus(String act_status, String ar_no);
    public Act_repVO findByPrimaryKey(String ar_no);
    public Act_repVO findByFK(String act_no, String mem_no);
    public List<Act_repVO> getAll();
    public void delete(String ar_no);
    
  //子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public List<Act_repVO> findByAct_status(String act_status);
//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
