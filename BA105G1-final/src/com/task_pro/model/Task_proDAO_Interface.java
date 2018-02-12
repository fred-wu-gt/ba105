package com.task_pro.model;

import java.util.List;

public interface Task_proDAO_Interface {
	void add(Task_proVO task_proVO);
	void update(Task_proVO task_proVO);
	void delete(String tp_no);
	Task_proVO findByTp_no(String tp_no);
	List<Task_proVO> getAll();
}
