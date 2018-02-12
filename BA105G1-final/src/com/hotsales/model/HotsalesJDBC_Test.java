package com.hotsales.model;

import java.util.List;

public class HotsalesJDBC_Test {

	public static void main(String[] args) {
		HotsalesJDBCDAO dao = new HotsalesJDBCDAO();
		
		
		
		
		//從訂單的銷售量來排續
		List<String> list = dao.findByOd_quan();
		for(String com_no: list ){
			System.out.println(com_no);

			System.out.println("=========================");
		}
		
		
		
//		新增
//		HotsalesVO hotsalesVO1 = new HotsalesVO();
//		hotsalesVO1.setCom_no("COM0000008");
//		dao.insert(hotsalesVO1);
//		System.out.println("新增成功!  還.......不錯吧?");
		
		
//		修改
//		HotsalesVO hotsalesVO2 = new HotsalesVO();
//		hotsalesVO2.setCom_no("COM0000001");
//		hotsalesVO2.setHot_no("HOT0000011");
//		dao.update(hotsalesVO2);
//		System.out.println("修改成功!  還~~~~~~~不錯吧?");
		
		
//		刪除
//		dao.delete("HOT0000010");
//		System.out.println("刪除成功!  真的還~~~~~~~不錯吧?");
		
//		查一筆
//		HotsalesVO hotVO = dao.findByPrimaryKey("HOT0000011");
//		System.out.println(hotVO.getHot_no());
//		System.out.println(hotVO.getCom_no());
//		System.out.println(hotVO.getHot_date());
//		System.out.println("===============查詢成功!==============");
		
//		查全部
//		List<HotsalesVO> list = dao.getAll();
//		for(HotsalesVO hotVO1:list ){
//			System.out.println(hotVO1.getHot_no());
//			System.out.println(hotVO1.getCom_no());
//			System.out.println(hotVO1.getHot_date());
//			System.out.println("=========================");
//		}
		
		
	}

}
