package com.wri_cat.model;

import java.util.List;

public class TestWri_cat {
	public static void main(String[] args) {
		Wri_catJDBCDAO dao = new Wri_catJDBCDAO();

		/* 1.新增 */
		Wri_catVO wri_catVO = new Wri_catVO();
		wri_catVO.setWrt_no("WRT0000010");
		wri_catVO.setFru_no("FRU0000010");
		dao.insert(wri_catVO);
		System.out.println("成功新增一筆資料!");

		/* 2.刪除 */
		// dao.delete("WRT0000010", "FRU0000010");
		// System.out.println("成功刪除一筆資料!");

		/* 3.查詢 */
		// List<Wri_catVO> Wri_catVOList = dao.findByWrt_no("WRT0000002");
		// System.out.println("查詢文章明細:");
		// for (Wri_catVO wri_catVO : Wri_catVOList) {
		// System.out.println("文章編號為:" + wri_catVO.getWrt_no());
		// System.out.println("蔬果編號為:" + wri_catVO.getFru_no());
		// }

		/* 4.查詢 */
		// List<Wri_catVO> Wri_catVOList = dao.findByFru_no("FRU0000002");
		// System.out.println("查詢蔬果明細:");
		// for (Wri_catVO wri_catVO : Wri_catVOList) {
		// System.out.println("蔬果編號為:" + wri_catVO.getFru_no());
		// System.out.println("文章編號為:" + wri_catVO.getWrt_no());
		// }

		/* 5.查全部 */
		// System.out.println("所有分類明細為:");
		// Wri_catJDBCDAO dao2 = new Wri_catJDBCDAO();
		// List<Wri_catVO> Wri_catVOList = dao2.getAll();
		// for (Wri_catVO wri_catVO2 : Wri_catVOList) {
		// System.out.println("文章編號為:" + wri_catVO2.getWrt_no());
		// System.out.println("蔬果編號為:" + wri_catVO2.getFru_no());
		// System.out.println();
		// }

	}

}
