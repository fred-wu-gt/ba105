package com.act_rep.model;

import java.util.List;

public class TestJDBCDAO {

	public static void main(String[] args) {
		Act_repJDBCDAO dao = new Act_repJDBCDAO();

		// 新
//		Act_repVO act_repVO = new Act_repVO();
//		act_repVO.setAct_no("ACT0000001");
//		act_repVO.setMem_no("MEM0000001");
//		act_repVO.setAr_rsn("怪怪的活動");
//		act_repVO.setAct_status("待審核");
//		act_repVO.setAr_cnt("怪怪的活動~~");
//		dao.insert(act_repVO); 
//		System.out.println("-------SUCCESS--------");

		// 修
//		Act_repVO act_repVO = new Act_repVO();
//		act_repVO.setAct_no("ACT0000010");
//		act_repVO.setMem_no("MEM0000007");
//		act_repVO.setAr_rsn("怪怪的活動");
//		act_repVO.setAct_status("已審核");
//		act_repVO.setAr_cnt(("怪怪的活動"));
//		act_repVO.setAr_no("AR00000001");
//		dao.update(act_repVO); 
//		System.out.println("-------SUCCESS--------");
		
		// 
//		Act_repVO act_repVO = dao.findByPrimaryKey("AR00000001");
//		System.out.print(act_repVO.getAr_no() + ",");
//		System.out.print(act_repVO.getAct_no() + ",");		
//		System.out.print(act_repVO.getMem_no()+ ",");
//		System.out.print(act_repVO.getAr_rsn() + ",");
//		System.out.print(act_repVO.getAct_status() + ",");
//		System.out.println(act_repVO.getAr_cnt());
//		System.out.println("-------SUCCESS------");
		
	
		// 
//		List<Act_repVO> list = dao.getAll();
//		for (Act_repVO act_repVO : list) {
//			System.out.println(act_repVO.getAr_no() + ",");
//			System.out.print(act_repVO.getAct_no() + ",");		
//			System.out.print(act_repVO.getMem_no()+ ",");
//			System.out.print(act_repVO.getAr_rsn() + ",");
//			System.out.print(act_repVO.getAct_status() + ",");
//			System.out.println(act_repVO.getAr_cnt());
//			System.out.println("------SUCCESS---------");
//		}
		
		// 刪除
		dao.delete("AR00000014");
		System.out.println("-------SUCCESS--------");

	}

}
