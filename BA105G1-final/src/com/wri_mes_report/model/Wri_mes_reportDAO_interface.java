package com.wri_mes_report.model;

import java.util.*;

public interface Wri_mes_reportDAO_interface {
	public Integer insert(Wri_mes_reportVO wri_mes_reportVO);

	public Integer update(Wri_mes_reportVO wri_mes_reportVO);

	public Integer delete(String wmrpt_no);

	public Wri_mes_reportVO findByPrimaryKey(String wmrpt_no);

	public List<Wri_mes_reportVO> getAll();
}
