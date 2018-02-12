package com.manager.model;

import java.util.*;
import java.util.List;

public class ManagerService {
	private ManagerDAO_Interface dao;
	public ManagerService(){
		dao=new ManagerDAO();
	}
	
	public ManagerVO addManager(String man_id, String man_pas, String man_name, String man_gen, String man_tel, String man_add, String man_pho_base64){
		ManagerVO managerVO=new ManagerVO();
		managerVO.setMan_id(man_id);
		managerVO.setMan_pas(man_pas);
		managerVO.setMan_name(man_name);
		managerVO.setMan_gen(man_gen);
		managerVO.setMan_tel(man_tel);
		managerVO.setMan_add(man_add);
		managerVO.setMan_pho_base64(man_pho_base64);
		managerVO.setMan_sta("正常");
		String man_no=dao.add(managerVO);
		
		managerVO.setMan_no(man_no);
		return managerVO;
	}
	
	public ManagerVO updateManager(String man_no, String man_id, String man_pas, String man_name, String man_gen, String man_tel, String man_add, String man_pho_base64, String man_sta){
		ManagerVO managerVO=new ManagerVO();
		managerVO.setMan_no(man_no);
		managerVO.setMan_id(man_id);
		managerVO.setMan_pas(man_pas);
		managerVO.setMan_name(man_name);
		managerVO.setMan_gen(man_gen);
		managerVO.setMan_tel(man_tel);
		managerVO.setMan_add(man_add);
		managerVO.setMan_pho_base64(man_pho_base64);
		managerVO.setMan_sta(man_sta);
		dao.update(managerVO);
		return managerVO;
	}
	
	public ManagerVO getOneManager(String man_no){
		return dao.findByMan_no(man_no);
	}
	
	public ManagerVO getOneManagerByMan_id(String man_id){
		return dao.findByMan_id(man_id);
	}
	
	public List<ManagerVO> getManagersByMan_sta(String man_sta){
		return dao.findByMan_sta(man_sta);
	}
	
	public List<ManagerVO> getAll(){
		return dao.getAll();
	}
}
