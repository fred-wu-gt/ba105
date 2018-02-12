package com.act_com_report.model;
import java.util.List;

public interface Act_com_reportDAO_interface {
	public void insert(Act_com_reportVO act_com_reportVO);
    public void update(Act_com_reportVO act_com_reportVO);
    //修改活動留言檢舉狀態
    public void updateAcrStatus(String acr_status, String acr_no);
    //為了看會員是否檢舉過 //1230+
    public Act_com_reportVO findByFK(String aco_no, String mem_no);
    public Act_com_reportVO findByPrimaryKey(String acr_no);
    public List<Act_com_reportVO> getAll();
    public void delete(String acr_no);
}
