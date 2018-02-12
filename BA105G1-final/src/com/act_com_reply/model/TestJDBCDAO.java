package com.act_com_reply.model;

import java.io.IOException;
import java.util.List;

public class TestJDBCDAO {

	public static void main(String[] args) {
		Act_com_replyJDBCDAO dao = new Act_com_replyJDBCDAO();

		// 新增 OK
//		Act_com_replyVO act_com_replyVO = new Act_com_replyVO();
//		act_com_replyVO.setAco_no("ACO0000003");
//		act_com_replyVO.setShop_no("SHOP000003");
//		act_com_replyVO.setAcr_cnt("您好,活動當天我們有開放直播喔");
//		dao.insert(act_com_replyVO); 
//		System.out.println("-------新增成功----------");
		
		
		// 修改 OK 
//		Act_com_replyVO act_com_replyVO  = new Act_com_replyVO();
//		act_com_replyVO.setAcr_no("ACR0000001");
//		act_com_replyVO.setAco_no("ACO0000003");
//		act_com_replyVO.setShop_no("SHOP000008");
//		act_com_replyVO.setAcr_cnt("您好,活動當天我們有開放直播喔");
//		dao.update(act_com_replyVO); 
//		System.out.println("-------修改成功---------");
		
		// 查單一 ok
//		Act_com_replyVO act_com_replyVO = dao.findByPrimaryKey("ACR0000001");
//		System.out.print(act_com_replyVO.getAcr_no() + ",");
//		System.out.print(act_com_replyVO.getAco_no() + ",");
//		System.out.println(act_com_replyVO.getShop_no() + ",");
//		System.out.print(act_com_replyVO.getAcr_cnt()+ ",");
//		System.out.println(act_com_replyVO.getAcr_time());
//		System.out.println("------查單一成功-----------");
		
		// 查全部
//		List<Act_com_replyVO> list = dao.getAll();
//		for (Act_com_replyVO act_com_replyVO : list) {
//			System.out.print(act_com_replyVO.getAcr_no() + ",");
//			System.out.print(act_com_replyVO.getAco_no() + ",");
//			System.out.println(act_com_replyVO.getShop_no() + ",");
//			System.out.print(act_com_replyVO.getAcr_cnt()+ ",");
//			System.out.println(act_com_replyVO.getAcr_time());
//			System.out.println("------------------");
//		}
//		System.out.println("-------查全部成功-----------");
		
		
		// 刪除 child record found
//		dao.delete("ACR0000001");
//		System.out.println("-------SUCCESS--------");
	}


}
