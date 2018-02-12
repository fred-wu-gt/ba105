package com.act_like.model;

import java.util.List;

public class TestJDBCDAO {

	public static void main(String[] args) {
		Act_likeJDBCDAO dao = new Act_likeJDBCDAO();

		// 新
//		Act_likeVO act_likeVO = new Act_likeVO();
//		act_likeVO.setAct_no("ACT0000001");
//		act_likeVO.setMem_no("MEM0000001");
//		dao.insert(act_likeVO); 
//		System.out.println("-------SUCCESS---------");
	
		// 查一
//		Act_likeVO act_likeVO = dao.findByPrimaryKey("ACT0000001", "MEM0000001");
//		System.out.print(act_likeVO.getAct_no() + ",");
//		System.out.println(act_likeVO.getMem_no() );	
//		System.out.println("-------SUCCESS---------");		
	
		// 查全
//		List<Act_likeVO> list = dao.getAll();
//		for (Act_likeVO act_likeVO : list) {
//			System.out.print(act_likeVO.getAct_no() + ",");
//			System.out.println(act_likeVO.getMem_no() );
//			System.out.println("-------SUCCESS---------");		
//		}
		
		//查看已按讚活動 ok
		List<Act_likeVO> list = dao.findByMemNo("MEM0000001");
		for (Act_likeVO act_likeVO : list) {
			System.out.print(act_likeVO.getAct_no() + ",");
			System.out.println(act_likeVO.getMem_no() );
			System.out.println("-------SUCCESS---------");		
		}
		
		//查看單一活動按讚數 ok
		int count = dao.getOneActCountLike("ACT0000009");
		System.out.println(count);
		System.out.println("-------SUCCESS---------");	
		
		// 刪除
//		dao.delete("ACT0000001", "MEM0000001");
//		System.out.println("-------SUCCESS--------");
	}


}
