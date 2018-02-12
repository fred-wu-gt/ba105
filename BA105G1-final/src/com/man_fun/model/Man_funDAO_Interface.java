package com.man_fun.model;

import java.util.List;

public interface Man_funDAO_Interface {
	void add(Man_funVO man_funVO);
	void update(Man_funVO man_funVO);
	void delete(String mf_no);
	Man_funVO findByMf_no(String mf_no);
	List<Man_funVO> getAll();
}
