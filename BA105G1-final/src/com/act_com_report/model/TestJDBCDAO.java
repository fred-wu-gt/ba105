package com.act_com_report.model;

import java.io.IOException;
import java.util.List;

public class TestJDBCDAO {

	public static void main(String[] args) {
		Act_com_reportJDBCDAO dao = new Act_com_reportJDBCDAO();

		// 新增 OK
//		Act_com_reportVO act_com_reportVO = new Act_com_reportVO();
//		act_com_reportVO.setAco_no("ACO0000001");
//		act_com_reportVO.setMem_no("MEM0000003");
//		act_com_reportVO.setAcr_rsn("不當留言");
//		act_com_reportVO.setAcr_status("已審核");
//		act_com_reportVO.setAcr_cnt("不當留言~~~");
//		dao.insert(act_com_reportVO); 
//		System.out.println("-------新增成功----------");
		
		
		// 修改 OK 
//		Act_com_reportVO act_com_reportVO  = new Act_com_reportVO();
//		act_com_reportVO.setAcr_no("ACR0000001");
//		act_com_reportVO.setAco_no("ACO0000003");
//		act_com_reportVO.setMem_no("MEM0000008");
//		act_com_reportVO.setAcr_rsn("引戰留言");
//		act_com_reportVO.setAcr_status("已審核");
//		act_com_reportVO.setAcr_cnt("引戰留言,為避免紛爭請刪除留言");
//		dao.update(act_com_reportVO); 
//		System.out.println("-------修改成功---------");
		
		// 查單一 ok
//		Act_com_reportVO act_com_reportVO = dao.findByPrimaryKey("ACR0000001");
//		System.out.print(act_com_reportVO.getAcr_no() + ",");
//		System.out.print(act_com_reportVO.getAco_no() + ",");
//		System.out.println(act_com_reportVO.getMem_no() + ",");
//		System.out.print(act_com_reportVO.getAcr_rsn()+ ",");
//		System.out.println(act_com_reportVO.getAcr_status());
//		System.out.println(act_com_reportVO.getAcr_cnt());
//		System.out.println("------查單一成功-----------");
		
		// 查全部 ok
//		List<Act_com_reportVO> list = dao.getAll();
//		for (Act_com_reportVO act_com_reportVO : list) {
//			System.out.print(act_com_reportVO.getAcr_no() + ",");
//			System.out.print(act_com_reportVO.getAco_no() + ",");
//			System.out.println(act_com_reportVO.getMem_no() + ",");
//			System.out.print(act_com_reportVO.getAcr_rsn()+ ",");
//			System.out.println(act_com_reportVO.getAcr_status());
//			System.out.println(act_com_reportVO.getAcr_cnt());
//			System.out.println("------------------");
//		}
//		System.out.println("-------查全部成功-----------");
		
		
		////為了看會員是否檢舉過 //1230+ OK
		Act_com_reportVO act_com_reportVO = dao.findByFK("ACO0000003", "MEM0000006");
		System.out.print(act_com_reportVO.getAcr_no() + ",");
		System.out.print(act_com_reportVO.getAco_no() + ",");
		System.out.println(act_com_reportVO.getMem_no() + ",");
		System.out.print(act_com_reportVO.getAcr_rsn()+ ",");
		System.out.println(act_com_reportVO.getAcr_status());
		System.out.println(act_com_reportVO.getAcr_cnt());
		System.out.println("------查成功-----------");
		
		// 刪除 child record found
//		dao.delete("ACR0000001");
//		System.out.println("-------SUCCESS--------");
	}


}
