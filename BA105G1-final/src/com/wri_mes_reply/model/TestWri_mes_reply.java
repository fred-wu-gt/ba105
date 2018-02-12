package com.wri_mes_reply.model;

//import java.util.List;

public class TestWri_mes_reply {
	public static void main(String[] args) {

		Wri_mes_replyJDBCDAO dao = new Wri_mes_replyJDBCDAO();

		try {
			/* 1.新增 */
			Wri_mes_replyVO wri_mes_replyVO1 = new Wri_mes_replyVO();
			wri_mes_replyVO1.setWmsg_no("WMSG000001");
			wri_mes_replyVO1.setShop_no("SHOP000001");
			wri_mes_replyVO1.setWcr_cont("其中一定有甚麼誤會");

			dao.insert(wri_mes_replyVO1);
			System.out.println("Insert success!");

			/* 2. 修改 */
			// Wri_mes_replyVO wri_mes_replyVO2 = new Wri_mes_replyVO();
			// wri_mes_replyVO2.setWcr_cont("無心插柳柳橙汁，鳩佔鵲巢檸檬茶");
			// wri_mes_replyVO2.setWmsgr_no("WMSGR00001");
			// dao.update(wri_mes_replyVO2);
			// System.out.println("Update success!");

			/* 3. 刪除 */
			// dao.delete("WMSGR00011");
			// System.out.println("Delete success!");

			/* 4. 查詢單筆資料 */
			// System.out.println("依據主鍵 WMSGR_NO 查詢單筆資料");
			// Wri_mes_replyVO wri_mes_replyVO3 =
			// dao.findByPrimaryKey("WMSGR00010");
			// System.out.println("WMSGR_NO: " +
			// wri_mes_replyVO3.getWmsgr_no());
			// System.out.println("WMSG_NO: " + wri_mes_replyVO3.getWmsg_no());
			// System.out.println("SHOP_NO: " + wri_mes_replyVO3.getShop_no());
			// System.out.println("WCR_CONT: " +
			// wri_mes_replyVO3.getWcr_cont());
			// System.out.println("WCR_TIME: " +
			// wri_mes_replyVO3.getWcr_time());

			// System.out.println("Select success!");

			/* 5. 查詢全部資料 */
			// System.out.println("查詢多筆資料");
			// List<Wri_mes_replyVO> list = dao.getAll();
			// for (Wri_mes_replyVO wri : list) {
			// System.out.println("WMSGR_NO: " + wri.getWmsgr_no());
			// System.out.println("WMSG_NO: " + wri.getWmsg_no());
			// System.out.println("SHOP_NO:" + wri.getShop_no());
			// System.out.println("WCR_CONT:" + wri.getWcr_cont());
			// System.out.println("WCR_TIME:" + wri.getWcr_time());
			// System.out.println();
			//
			// }

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
