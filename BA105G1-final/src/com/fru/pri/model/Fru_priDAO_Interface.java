package com.fru.pri.model;

import java.util.List;
import java.util.Map;

import com.commodity.model.CommodityVO;

public interface Fru_priDAO_Interface {
	void add(Fru_priVO fru_priVO);
	void update(Fru_priVO fru_priVO);
	void delete(String fp_no);
	Fru_priVO findByFp_no(String fp_no);
	Fru_priVO findByFp_name(String fp_name);
	public List<Fru_priVO> getAll();
	
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<Fru_priVO> getAll(Map<String, String[]> map); 
	
	//自增主鍵綁定新增蔬果時價編號同時增加蔬果編號
	//public void insert2(Fru_priVO fru_priVO,java.sql.Connection con);
}
