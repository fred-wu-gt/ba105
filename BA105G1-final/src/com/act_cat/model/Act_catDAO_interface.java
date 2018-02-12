package com.act_cat.model;

import java.sql.Connection;
import java.util.List;

public interface Act_catDAO_interface {
	public void insert(Act_catVO activity_categoryVO);
	public void insert2 (Act_catVO act_catVO , Connection con);//自增主鍵綁定0112+
    public Act_catVO findByPrimaryKey(String act_no, String fru_no);
    public List<Act_catVO> getAll();
    public Act_catVO findByActNo(String act_no);//用活動編號找水果編號0111+
    public void delete(String act_no, String fru_no);
    
}
