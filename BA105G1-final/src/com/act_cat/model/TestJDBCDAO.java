package com.act_cat.model;

import java.util.List;

public class TestJDBCDAO {

	public static void main(String[] args) {
		Act_catJDBCDAO dao = new Act_catJDBCDAO();

		// 新增
//		Act_catVO act_catVO = new Act_catVO();
//		act_catVO.setAct_no("ACT0000001");
//		act_catVO.setFru_no("FRU0000002");
//		dao.insert(act_catVO); 
//		System.out.println("-------SUCCESS--------");

		
		// 查一
//		Act_catVO act_catVO = dao.findByPrimaryKey("ACT0000001", "FRU0000001");
//		System.out.print(act_catVO.getAct_no() + ",");
//		System.out.println(act_catVO.getFru_no() );	
//		System.out.println("------SUCCESS-------");
		
	
		// 查全
//		List<Act_catVO> list = dao.getAll();
//		for (Act_catVO act_catVO : list) {
//			System.out.print(act_catVO.getAct_no() + ",");
//			System.out.println(act_catVO.getFru_no() );
//			System.out.println("-------SUCCESS--------");
//		}
		
		// 刪除
//		dao.delete("ACT0000001", "FRU0000001");
//		System.out.println("-------SUCCESS--------");
	}


}
