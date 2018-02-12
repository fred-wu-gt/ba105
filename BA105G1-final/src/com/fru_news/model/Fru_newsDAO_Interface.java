package com.fru_news.model;

import java.util.List;

public interface Fru_newsDAO_Interface {
	void add(Fru_newsVO fru_newsVO);
	void update(Fru_newsVO fru_newsVO);
	void delete(String fn_no);
	Fru_newsVO findByFn_no(String fn_no);
	List<Fru_newsVO> getAll();
}
