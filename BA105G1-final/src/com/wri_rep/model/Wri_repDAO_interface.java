package com.wri_rep.model;

import java.util.*;

public interface Wri_repDAO_interface {
	public Integer insert(Wri_repVO wri_repVO);

	public Integer update(Wri_repVO wri_repVO);

	public Integer delete(String wre_no);

	public Wri_repVO findByPrimaryKey(String wre_no);

	public List<Wri_repVO> getAll();
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public List<Wri_repVO> findByWre_stat(String wre_stat);
    public void updateWre_stat(String wre_stat, String wre_no);
//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
