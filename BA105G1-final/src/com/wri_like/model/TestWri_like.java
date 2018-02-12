package com.wri_like.model;

import java.util.List;

public class TestWri_like {
	public static void main(String[] args) {
		Wri_likeJDBCDAO dao = new Wri_likeJDBCDAO();

		/* 1.新增 */
		Wri_likeVO wri_likeVO = new Wri_likeVO();
		wri_likeVO.setWrt_no("WRT0000010");
		wri_likeVO.setMem_no("MEM0000010");
		dao.insert(wri_likeVO);
		System.out.println("成功新增一筆資料!");

		/* 2.刪除 */
		// dao.delete("WRT0000010", "MEM0000010");
		// System.out.println("成功刪除一筆資料!");

		/* 3.查詢 */
		// List<Wri_likeVO> Wri_likeVOList = dao.findByWrt_no("WRT0000002");
		// System.out.println("查詢文章按讚明細:");
		// for (Wri_likeVO wri_likeVO : Wri_likeVOList) {
		// System.out.println("文章編號為:"+wri_likeVO.getWrt_no());
		// System.out.println("會員編號為:"+wri_likeVO.getMem_no());
		// }

		/* 4.查詢 */
		// List<Wri_likeVO> Wri_likeVOList=dao.findByMem_no("MEM0000002");
		// System.out.println("查詢會員按讚明細");
		// for(Wri_likeVO wri_likeVO:Wri_likeVOList){
		// System.out.println("會員編號為:"+wri_likeVO.getMem_no());
		// System.out.println("文章編號為:"+wri_likeVO.getWrt_no());
		// }

		/* 5.查全部 */
		// System.out.println("所有文章按讚明細為:");
		// Wri_likeJDBCDAO dao2 = new Wri_likeJDBCDAO();
		// List<Wri_likeVO> Wri_likeVOList=dao2.getAll();
		// for(Wri_likeVO wri_likeVO2:Wri_likeVOList){
		// System.out.println("文章編號為:"+wri_likeVO2.getWrt_no());
		// System.out.println("會員編號為:"+wri_likeVO2.getMem_no());
		// System.out.println();
		// }
	}
}
