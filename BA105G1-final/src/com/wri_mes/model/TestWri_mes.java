package com.wri_mes.model;

//import java.util.List;

public class TestWri_mes {
	public static void main(String[] args) {
		Wri_mesJDBCDAO dao = new Wri_mesJDBCDAO();
		try {
			/* 1.新增 */
			Wri_mesVO wri_mes_vo1 = new Wri_mesVO();
			wri_mes_vo1.setWmsg_no("WMSG000011");
			wri_mes_vo1.setWrt_no("WRT0000001");
			wri_mes_vo1.setMem_no("MEM0000001");
			wri_mes_vo1.setWmsg_cont("好人一生平安");
			wri_mes_vo1.setWmsg_stat("正常");
			dao.insert(wri_mes_vo1);
			System.out.println("Insert success!");

			/* 2. 修改 */
			// Wri_mesVO wri_mes_vo2 = new Wri_mesVO();
			// wri_mes_vo2.setWmsg_no("WMSG000001");
			// wri_mes_vo2.setWmsg_cont("你是好人也是個壞人，對我坦承只為了朝他狂奔");
			// wri_mes_vo2.setWmsg_stat("正常");
			// dao.update(wri_mes_vo2);
			// System.out.println("Update success!");

			/* 3.刪除 */
			// dao.delete("WMSG000013");
			// System.out.println("Delete success!");

			/* 4.查詢 */
			// System.out.println("依據主鍵 WMSG_NO，查詢單筆資料:");
			// Wri_mesVO wri_mes_vo3 = dao.findByPrimaryKey("WMSG000001");
			// System.out.println("WMSG_NO: " + wri_mes_vo3.getWmsg_no());
			// System.out.println("WRT_NO: " + wri_mes_vo3.getWrt_no());
			// System.out.println("MEM_NO: " + wri_mes_vo3.getMem_no());
			// System.out.println("WMSG_DATE: " + wri_mes_vo3.getWmsg_date());
			// System.out.println("WMSG_CONT: " + wri_mes_vo3.getWmsg_cont());
			// System.out.println("WMSG_STAT: " + wri_mes_vo3.getWmsg_stat());
			// System.out.println("Select success!");

			/* 5.查全部 */
			// System.out.println("查詢多筆資料");
			// List<Wri_mesVO> list = dao.getAll();
			// for (Wri_mesVO wri : list) {
			// System.out.println("WMSG_NO: " + wri.getWmsg_no());
			// System.out.println("WRT_NO: " + wri.getWrt_no());
			// System.out.println("MEM_NO: " + wri.getMem_no());
			// System.out.println("WMSG_DATE: " + wri.getWmsg_date());
			// System.out.println("WMSG_CONT: " + wri.getWmsg_cont());
			// System.out.println("WMSG_STAT: " + wri.getWmsg_stat());
			// System.out.println();
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
