package com.exc_rec.model;

import java.util.List;

public interface Exc_recDAO_Interface {
	void add(Exc_recVO exc_recVO);
	void update(Exc_recVO exc_recVO);
	void delete(String er_no);
	Exc_recVO findByEr_no(String er_no);
	List<Exc_recVO> getAll();
}
