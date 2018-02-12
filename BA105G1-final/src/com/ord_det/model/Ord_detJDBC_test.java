package com.ord_det.model;

import java.util.List;

public class Ord_detJDBC_test {

	public static void main(String[] args) {
		
		Ord_detJDBCDAO dao = new Ord_detJDBCDAO();

		/*
		 * 
		 * (淘汰) 新增    
		 * 跟著訂單主檔一起新增
		 *
		 */
//		Ord_detVO Ord_det1 = new Ord_detVO();
//		Ord_det1.setCom_no("COM0000001");
//		Ord_det1.setOd_quan(30);
//		dao.insertOrd_det(Ord_det1);
//	
//		System.out.println("======新增成功!====== ");
		
		
//		修改
		Ord_detVO Ord_det2 = new Ord_detVO();
		Ord_det2.setOd_score(5.5);
		Ord_det2.setOrd_no("2017-12-220000000011");
		Ord_det2.setCom_no("COM0000006");
		dao.update(Ord_det2);
		System.out.println("==========修改成功!!============");
		
//		刪除
//		dao.delete("2017-12-220000000011","COM0000006");
//		System.out.println("==========刪除成功!!============");
		
		
		//用 ord_no來查
//		 List<Ord_detVO> list= dao.findByOrd_no("2017-12-220000000002");
//		 for(Ord_detVO ord_detVO :list){
//			 System.out.println(ord_detVO.getOrd_no());
//			 System.out.println(ord_detVO.getCom_no());
//			 System.out.println(ord_detVO.getOd_quan());
//			 System.out.println(ord_detVO.getOd_score());
//			 System.out.println("=========================");	 
//		 }
	
		
		//用 com_no來查
//		 List<Ord_detVO> list= dao.findByCom_no("COM0000008");
//		 for(Ord_detVO ord_detVO :list){
//			 System.out.println(ord_detVO.getOrd_no());
//			 System.out.println(ord_detVO.getCom_no());
//			 System.out.println(ord_detVO.getOd_quan());
//			 System.out.println(ord_detVO.getOd_score());
//			 System.out.println("=========================");
//			 
//		 }
		
		
//		查全部
//		 List<Ord_detVO> list = dao.getAll();
//		 for(Ord_detVO ord_detVO :list){
//			 System.out.println(ord_detVO.getOrd_no());
//			 System.out.println(ord_detVO.getCom_no());
//			 System.out.println(ord_detVO.getOd_quan());
//			 System.out.println(ord_detVO.getOd_score());
//			 System.out.println("=========================");
//			 
//		 }
		
		
		
	}
	

}
