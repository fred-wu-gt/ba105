package com.finance.model;

import java.util.List;

public interface FinanceDAO_Interface {
	void add(FinanceVO financeVO);
	void update(FinanceVO financeVO);
	void delete(String fin_no);
	FinanceVO findByFin_no(String fin_no);
	List<FinanceVO> getAll();
}
