package com.man_aut.model;

import java.util.List;

public interface Man_autDAO_Interface {
	void add(Man_autVO man_autVO);
	void delete(String mf_no, String man_no);
	List<Man_autVO> findByMf_no(String mf_no);
	List<Man_autVO> findByMan_no(String man_no);
	List<Man_autVO> getAll();
}
