package com.rep_com.model;

import java.util.List;

import com.hotsales.model.HotsalesVO;

public interface Rep_comDAO_interface {
	
	public String insert(Rep_comVO rep_comVO);

	public void update(Rep_comVO rep_comVO);
	
	public void updateRep_comStat(Rep_comVO rep_comVO);

	public void delete(String rc_no);

	public Rep_comVO findByPrimaryKey(String rc_no);

	public List<Rep_comVO> getAll();
	

}
