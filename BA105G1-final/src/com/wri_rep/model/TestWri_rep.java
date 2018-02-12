package com.wri_rep.model;

import java.util.List;

public class TestWri_rep {
	public static void main(String[] args) {
		Wri_repJDBCDAO dao = new Wri_repJDBCDAO();

		try {
			/* 1.新增 */
			Wri_repVO wri_rep_vo1 = new Wri_repVO();
			wri_rep_vo1.setWre_no("WRE0000011");
			wri_rep_vo1.setWrt_no("WRT0000010");
			wri_rep_vo1.setMem_no("MEM0000010");
			wri_rep_vo1.setWre_rsn("內容有點奇怪");
			wri_rep_vo1.setWre_stat("隱藏");
			wri_rep_vo1.setWre_cont("月落烏啼霜滿天，枕頭裡面有海綿");
			dao.insert(wri_rep_vo1);
			System.out.println("Insert success!");

			/* 2. 修改 */
			// Wri_repVO wri_rep_vo2 = new Wri_repVO();
			// wri_rep_vo2.setWre_no("WRE0000011");
			// wri_rep_vo2.setWrt_no("WRT0000010");
			// wri_rep_vo2.setMem_no("MEM0000010");
			// wri_rep_vo2.setWre_rsn("偷渡政治文，壞壞");
			// wri_rep_vo2.setWre_stat("正常");
			// wri_rep_vo2.setWre_cont("兩岸猿聲啼不住，主席已換洪秀柱");
			// dao.update(wri_rep_vo2);
			// System.out.println("Update success!");

			/* 3.刪除 */
			// dao.delete("WRE0000011");
			// System.out.println("Delete success!");

			/* 4.查詢 */
			// System.out.println("依據主鍵 WRE_NO，查詢單筆資料:");
			// Wri_repVO wri_rep_vo3 = dao.findByPrimaryKey("WRE0000001");
			// System.out.println("WRE_NO: " + wri_rep_vo3.getWre_no());
			// System.out.println("WRT_NO: " + wri_rep_vo3.getWrt_no());
			// System.out.println("MEM_NO: " + wri_rep_vo3.getMem_no());
			// System.out.println("WRE_RSN:" + wri_rep_vo3.getWre_rsn());
			// System.out.println("WRE_STAT: "+ wri_rep_vo3.getWre_stat());
			// System.out.println("WRE_CONT: "+ wri_rep_vo3.getWre_cont());
			// System.out.println("Select success!");

			/* 5.查全部 */
			// System.out.println("查詢多筆資料");
			// List<Wri_repVO> list = dao.getAll();
			// for (Wri_repVO wri : list) {
			// System.out.println("WRE_NO: " + wri.getWre_no());
			// System.out.println("WRT_NO: " + wri.getWrt_no());
			// System.out.println("MEM_NO: " + wri.getMem_no());
			// System.out.println("WRE_RSN:" + wri.getWre_rsn());
			// System.out.println("WRE_STAT:" + wri.getWre_stat());
			// System.out.println("WRE_CONT:" + wri.getWre_cont());
			// System.out.println();
			//
			// }

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
