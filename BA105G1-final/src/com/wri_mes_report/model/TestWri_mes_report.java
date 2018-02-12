package com.wri_mes_report.model;

import java.util.List;

public class TestWri_mes_report {
	public static void main(String[] args) {
		Wri_mes_reportJDBCDAO dao = new Wri_mes_reportJDBCDAO();

		try {
			/* 1.新增 */
			Wri_mes_reportVO wri_mes_report_vo1 = new Wri_mes_reportVO();

			wri_mes_report_vo1.setWmsg_no("WMSG000010");
			wri_mes_report_vo1.setMem_no("MEM0000010");
			wri_mes_report_vo1.setWmrpt_rsn("內容不適當");
			wri_mes_report_vo1.setWmrpt_stat("隱藏");
			wri_mes_report_vo1.setWmrpt_cont("!!!!!!");
			dao.insert(wri_mes_report_vo1);
			System.out.println("Insert success!");

			/* 2. 修改 */
			// Wri_mes_reportVO wri_mes_report_vo2 = new Wri_mes_reportVO();
			// wri_mes_report_vo2.setWmrpt_rsn("偷渡政治文，壞壞");
			// wri_mes_report_vo2.setWmrpt_stat("正常");
			// wri_mes_report_vo2.setWmrpt_cont("3.1415926");
			// wri_mes_report_vo2.setWmrpt_no("WMRPT00010");
			// dao.update(wri_mes_report_vo2);
			// System.out.println("Update success");

			/* 3.刪除 */
			// dao.delete("WMRPT00012");
			// System.out.println("Delete success!");

			/* 4.查詢 */
			// System.out.println("依據主鍵 WMRPT_NO 查詢單筆資料");
			// Wri_mes_reportVO wri_mes_report_vo3 =
			// dao.findByPrimaryKey("WMRPT00010");
			// System.out.println("WMRPT_NO: " +
			// wri_mes_report_vo3.getWmrpt_no());
			// System.out.println("WMSG_NO: " +
			// wri_mes_report_vo3.getWmsg_no());
			// System.out.println("MEM_NO: " + wri_mes_report_vo3.getMem_no());
			// System.out.println("WMRPT_RSN:" +
			// wri_mes_report_vo3.getWmrpt_rsn());
			// System.out.println("WMRPT_STAT:" +
			// wri_mes_report_vo3.getWmrpt_stat());
			// System.out.println("WMRPT_CONT:" +
			// wri_mes_report_vo3.getWmrpt_cont());
			// System.out.println("Select success!");

			/* 5.查全部 */
			// System.out.println("查詢多筆資料");
			// List<Wri_mes_reportVO> list = dao.getAll();
			// for (Wri_mes_reportVO wri : list) {
			// System.out.println("WMRPT_NO: " + wri.getWmrpt_no());
			// System.out.println("WMSG_NO: " + wri.getWmsg_no());
			// System.out.println("MEM_NO: " + wri.getMem_no());
			// System.out.println("WMRPT_RSN:" + wri.getWmrpt_rsn());
			// System.out.println("WMRPT_STAT:" + wri.getWmrpt_stat());
			// System.out.println("WMRPT_CONT:" + wri.getWmrpt_cont());
			// System.out.println();

			// }

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
