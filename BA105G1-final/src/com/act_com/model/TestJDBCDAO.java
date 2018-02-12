package com.act_com.model;

import java.io.IOException;
import java.util.List;

public class TestJDBCDAO {

	public static void main(String[] args) {
		Act_comJDBCDAO dao = new Act_comJDBCDAO();

		// 新增
//		Act_comVO act_comVO  = new Act_comVO();
//		act_comVO.setAct_no("ACT0000002");
//		act_comVO.setMem_no("MEM0000001");
//		act_comVO.setAco_cnt("很讚的活動");
////		act_comVO.setAco_date(java.sql.Timestamp.valueOf("2018-1-12 9:10:00"));
//		act_comVO.setAco_status("正常");
//		dao.insert(act_comVO); 
//		System.out.println("-------新增成功----------");
		
		
		// 修改
//		Act_comVO act_comVO  = new Act_comVO();
//		act_comVO.setAct_no("ACT0000002");
//		act_comVO.setMem_no("MEM0000002");
//		act_comVO.setAco_cnt("ccccccccccccccccc");
//		act_comVO.setAco_status("bbbbbbbbbbbbbbbbbbbbbb");
//		act_comVO.setAco_no("ACO0000001");
//		dao.update(act_comVO); 
//		System.out.println("-------修改成功---------");
		
		// 查單一
//		Act_comVO act_comVO = dao.findByPrimaryKey("ACO0000001");
//		System.out.print(act_comVO.getAco_no() + ",");
//		System.out.print(act_comVO.getAct_no() + ",");
//		System.out.println(act_comVO.getMem_no() + ",");
//		System.out.print(act_comVO.getAco_cnt()+ ",");
//		System.out.print(act_comVO.getAco_date() + ",");
//		System.out.println(act_comVO.getAco_status() );
//		System.out.println("------查單一成功-----------");
		
		// 查全部
//		List<Act_comVO> list = dao.getAll();
//		for (Act_comVO act_comVO : list) {
//			System.out.print(act_comVO.getAco_no() + ",");
//			System.out.print(act_comVO.getAct_no() + ",");
//			System.out.println(act_comVO.getMem_no() + "," );	
//			System.out.print(act_comVO.getAco_cnt() + ",");
//			System.out.print(act_comVO.getAco_date() + ",");
//			System.out.println(act_comVO.getAco_status());
//			System.out.println("------------------");
//		}
//		System.out.println("-------查全部成功-----------");
		
		//為了查單一活動的留言區塊 //1230加的 OK
//		List<Act_comVO> list = dao.findByActNo("ACT0000009");
//		for (Act_comVO act_comVO : list) {
//			System.out.print(act_comVO.getAco_no() + ",");
//			System.out.print(act_comVO.getAct_no() + ",");
//			System.out.println(act_comVO.getMem_no() + "," );	
//			System.out.print(act_comVO.getAco_cnt() + ",");
//			System.out.print(act_comVO.getAco_date() + ",");
//			System.out.println(act_comVO.getAco_status());
//			System.out.println("------------------");
//		}
//		System.out.println("-------查成功-----------");
		
		// 刪除 child record found
//		dao.delete("ACO0000001");
//		System.out.println("-------SUCCESS--------");
	}


}
