package com.mail.model;

import java.util.List;

public class TestJDBCDAO {

	public static void main(String[] args) {
		
		MailJDBCDAO dao = new MailJDBCDAO();
		// 新增 ok
//		MailVO mailVO = new MailVO();
//		mailVO.setMail_sender("客服");
//		mailVO.setMail_receiver("一般會員");
//		mailVO.setMail_status("未讀");
//		mailVO.setMail_title("活動報名截止囉");
//		mailVO.setMail_cnt("您好,本活動報名已截止,感謝您的參與!");
//		dao.insert(mailVO); 
//		System.out.println("-------新增成功----------");
		
		//查全部 ok
//		List<MailVO> list = dao.findByMailReceiver("MEM0000006");	
//		for (MailVO mailVO : list) {
//			System.out.print("信件編號 : "+ mailVO.getMail_no() + ",");
//			System.out.print("寄信者 : " + mailVO.getMail_sender()+ ",");			
//			System.out.print("收信者 : "+ mailVO.getMail_receiver() + ",");
//			System.out.print("信件標題 : "+ mailVO.getMail_title() + ",");
//			System.out.print("寄信時間 : " + mailVO.getMail_time() + ",");
//			System.out.print("活動內文 : "+ mailVO.getMail_cnt() + ",");
//			System.out.println("活動狀態 : "+ mailVO.getMail_status());
//			System.out.println("---------------------");
//		}
		
		//查單一 ok
		MailVO mailVO = (MailVO) dao.findByMailNo("MAIL000001", "MEM0000006");
		System.out.print("信件編號 : "+ mailVO.getMail_no() + ",");
		System.out.print("寄信者 : " + mailVO.getMail_sender()+ ",");			
		System.out.print("收信者 : "+ mailVO.getMail_receiver() + ",");
		System.out.print("信件標題 : "+ mailVO.getMail_title() + ",");
		System.out.print("寄信時間 : " + mailVO.getMail_time() + ",");
		System.out.print("活動內文 : "+ mailVO.getMail_cnt() + ",");
		System.out.println("活動狀態 : "+ mailVO.getMail_status());
		System.out.println("---------------------");

	}

}
