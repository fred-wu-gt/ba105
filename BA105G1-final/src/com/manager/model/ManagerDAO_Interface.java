package com.manager.model;

import java.util.List;

public interface ManagerDAO_Interface {
	String add(ManagerVO managerVO);
	void update(ManagerVO managerVO);
	ManagerVO findByMan_no(String man_no);
	ManagerVO findByMan_id(String man_id);
	List<ManagerVO> findByMan_sta(String man_sta);
	List<ManagerVO> getAll();
	
}
