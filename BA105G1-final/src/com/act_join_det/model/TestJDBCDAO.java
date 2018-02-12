package com.act_join_det.model;

import java.util.List;

public class TestJDBCDAO {

	public static void main(String[] args) {
		Act_join_detJDBCDAO dao = new Act_join_detJDBCDAO();
		
		// 新增
//		Act_join_detVO act_join_detVO = new Act_join_detVO();
//		act_join_detVO.setAct_no("ACT0000008");
//		act_join_detVO.setMem_no("MEM0000008");
//		act_join_detVO.setAj_status("aaa");
//		dao.insert(act_join_detVO); 
//		System.out.println("----SUCCESS----------");
		
		// 修改
//		Act_join_detVO act_join_detVO = new Act_join_detVO();
//		act_join_detVO.setAj_status("已報到");
//		act_join_detVO.setAct_no("ACT0000009");
//		act_join_detVO.setMem_no("MEM0000007");
//		dao.update(act_join_detVO); 
//		System.out.println("----SUCCESS----------");
		
		// 查單一
//		Act_join_detVO act_join_detVO = dao.findByPrimaryKey("ACT0000009", "MEM0000007");
//		System.out.print( "活動編號: " + act_join_detVO.getAct_no() + ",");
//		System.out.println("一般會員編號: " + act_join_detVO.getMem_no() + ",");
//		System.out.println("報名時間: " + act_join_detVO.getAj_time() + ",");
//		System.out.println("狀態: "+ act_join_detVO.getAj_status());
//		System.out.println("-----SUCCESS---------");
		
	
		// 查全部
//		List<Act_join_detVO> list = dao.getAll();
//		for (Act_join_detVO act_join_detVO : list) {
//			System.out.print("活動編號: " + act_join_detVO.getAct_no() + ",");
//			System.out.println("一般會員編號: " + act_join_detVO.getMem_no() + ",");
//			System.out.print("報名時間: " + act_join_detVO.getAj_time()+ ",");
//			System.out.println("狀態: "+ act_join_detVO.getAj_status());
//			System.out.println("------SUCCESS---------");
//		}
		
		//查看已報名活動 ok
		List<Act_join_detVO> list = dao.findByMemNo("MEM0000007");
		for (Act_join_detVO act_join_detVO : list) {
			System.out.print("活動編號: " + act_join_detVO.getAct_no() + ",");
			System.out.println("一般會員編號: " + act_join_detVO.getMem_no() + ",");
			System.out.print("報名時間: " + act_join_detVO.getAj_time()+ ",");
			System.out.println("狀態: "+ act_join_detVO.getAj_status());
			System.out.println("------SUCCESS---------");
		}
		
		// 刪除
//		dao.delete("ACT0000008", "MEM0000008");
//		System.out.println("-------SUCCESS--------");
	}

}
