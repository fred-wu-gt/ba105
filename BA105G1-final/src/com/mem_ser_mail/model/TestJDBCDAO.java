package com.mem_ser_mail.model;

public class TestJDBCDAO {

	public static void main(String[] args) {
		
		Mem_ser_mailJDBCDAO dao = new Mem_ser_mailJDBCDAO();
		// 新增 ok
		Mem_ser_mailVO mem_ser_mailVO = new Mem_ser_mailVO();
		mem_ser_mailVO.setMem_no("MEM0000001");
		mem_ser_mailVO.setMan_no("MAN0000002");
		mem_ser_mailVO.setMail_title("活動報名截止囉");
		mem_ser_mailVO.setMail_sender("客服");
		mem_ser_mailVO.setMail_cnt("您好,本活動報名已截止,感謝您的參與!");
		dao.insert(mem_ser_mailVO); 
		System.out.println("-------新增成功----------");
	}

}
