package com.ord_mas.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ord_det.model.Ord_detJDBCDAO;
import com.ord_det.model.Ord_detVO;

//import com.ord_det.model.Ord_detJDBCDAO;
//import com.ord_det.model.Ord_detVO;

public class Ord_masJDBC_TEST {

	public static void main(String[] args) {
		
		Ord_masJDBCDAO dao = new Ord_masJDBCDAO();
		
		
		
		
		//修改訂單狀態
		Ord_masVO ord_masVO3= new Ord_masVO();
		ord_masVO3.setOrd_sta("已出貨");
		ord_masVO3.setOrd_no("2018-01-150000000063");
		dao.changOrdSta(ord_masVO3);
		System.out.println("=============修改訂單狀態成功=================");
		
		
		
		
		//從商家編號找訂單主檔
		
		
//			List<Ord_masVO> list = dao.findByShopNO("SHOP000005");
//			for( Ord_masVO ord_masVO3 :list){
//				System.out.println(ord_masVO3.getOrd_no()+",");
//				System.out.println(ord_masVO3.getMem_no()+",");
//				System.out.println(ord_masVO3.getShop_no()+",");
//				System.out.println(ord_masVO3.getOrd_time()+",");
//				System.out.println(ord_masVO3.getOrd_sta()+",");
//				System.out.println(ord_masVO3.getOrd_total()+",");
//				System.out.println(ord_masVO3.getOrd_rec()+",");
//				System.out.println(ord_masVO3.getOrd_adr()+",");
//				System.out.println(ord_masVO3.getOrd_tel()+",");
//				System.out.println(ord_masVO3.getOrd_can_rea()+",");
//				System.out.println("=================================");		
//			}
			
		
		
		
		
		
		
		//從會員編號找訂單主檔
	
		
//		List<Ord_masVO> list = dao.findByMemNO("MEM0000008");
//		for( Ord_masVO ord_masVO3 :list){
//			System.out.println(ord_masVO3.getOrd_no()+",");
//			System.out.println(ord_masVO3.getMem_no()+",");
//			System.out.println(ord_masVO3.getShop_no()+",");
//			System.out.println(ord_masVO3.getOrd_time()+",");
//			System.out.println(ord_masVO3.getOrd_sta()+",");
//			System.out.println(ord_masVO3.getOrd_total()+",");
//			System.out.println(ord_masVO3.getOrd_rec()+",");
//			System.out.println(ord_masVO3.getOrd_adr()+",");
//			System.out.println(ord_masVO3.getOrd_tel()+",");
//			System.out.println(ord_masVO3.getOrd_can_rea()+",");
//			System.out.println("=================================");		
//		}
//		
	

		
//	ord_masVO.setMem_no("MEM0000009");
//	ord_masVO.setShop_no("SHOP000008");
//	ord_masVO.setOrd_sta("客戶取消");
//	ord_masVO.setOrd_total(8100);
//	ord_masVO.setOrd_rec("@@@@@");
//	ord_masVO.setOrd_adr("地球");
//	ord_masVO.setOrd_tel("2562666");
//	
//	List<Ord_detVO> testList = new ArrayList<Ord_detVO>();
//	Ord_detVO ord_deVO1= new Ord_detVO();
//	ord_deVO1.setCom_no("COM0000005");
//	ord_deVO1.setOd_quan(20);
//	
//	Ord_detVO ord_deVO2= new Ord_detVO();
//	ord_deVO2.setCom_no("COM0000008");
//	ord_deVO2.setOd_quan(88);
//	testList.add(ord_deVO1);
//	testList.add(ord_deVO2);
//	dao.insertWithOrd_det(ord_masVO, testList);
//	System.out.println("新增成功");
//	
	

		
		
////		新增
//		Ord_masVO ord_masVO = new Ord_masVO();
////		Ord_detVO Ord_det1 = new Ord_detVO();
//		ord_masVO.setMem_no("MEM0000009");
//		ord_masVO.setShop_no("SHOP000008");
//		ord_masVO.setOrd_sta("客戶取消");
//		ord_masVO.setOrd_total(8100);
//		ord_masVO.setOrd_rec("@@@@@");
//		ord_masVO.setOrd_adr("地球");
//		ord_masVO.setOrd_tel("2562666");
//		String ord_no= dao.insert(ord_masVO);
//
//		System.out.println("======新增成功!====== ");
//		System.out.println("ord_no ="+ ord_no);
		
		//修改收件人資料
//		Ord_masVO ord_masVO3= new Ord_masVO();
//		ord_masVO3.setOrd_rec("GGYY");
//		ord_masVO3.setOrd_adr("XXXX");
//		ord_masVO3.setOrd_tel("0922336121");
//		ord_masVO3.setOrd_no("2017-12-260000000001");
//		dao.changOrdMasRec(ord_masVO3);
//		System.out.println("=============修改收件人資料成功=================");
		
		
		//		修改
//		Ord_masVO ord_masVO1 = new Ord_masVO();
//		ord_masVO1.setMem_no("MEM0000009");
//		ord_masVO1.setShop_no("SHOP000005");
//		ord_masVO1.setOrd_sta("客戶取消");
//		ord_masVO1.setOrd_total(81000);
//		ord_masVO1.setOrd_rec("XYYX");
//		ord_masVO1.setOrd_adr("幸福城市");
//		ord_masVO1.setOrd_tel("81882632");
//		ord_masVO1.setOrd_no("2017-12-220000000011");
//		dao.update(ord_masVO1);
//		System.out.println("======修改成功!====== ");
		
		
//		刪除
//		dao.delete("2017-12-190000000017");
		
//		查一筆
//		Ord_masVO ord_masVO2 = dao.findByPrimaryKey("2017-12-220000000011");
//		System.out.println(ord_masVO2.getOrd_no());
//		System.out.println(ord_masVO2.getMem_no());
//		System.out.println(ord_masVO2.getShop_no());
//		System.out.println(ord_masVO2.getOrd_time());
//		System.out.println(ord_masVO2.getOrd_sta());
//		System.out.println(ord_masVO2.getOrd_total());
//		System.out.println(ord_masVO2.getOrd_rec());
//		System.out.println(ord_masVO2.getOrd_adr());
//		System.out.println(ord_masVO2.getOrd_tel());
//		System.out.println(ord_masVO2.getOrd_can_rea());
//		System.out.println("=================================");
		
		
		//查全部
//		List<Ord_masVO> list = dao.getAll();
//		for( Ord_masVO ord_masVO3 :list){
//			System.out.println(ord_masVO3.getOrd_no()+",");
//			System.out.println(ord_masVO3.getMem_no()+",");
//			System.out.println(ord_masVO3.getShop_no()+",");
//			System.out.println(ord_masVO3.getOrd_time()+",");
//			System.out.println(ord_masVO3.getOrd_sta()+",");
//			System.out.println(ord_masVO3.getOrd_total()+",");
//			System.out.println(ord_masVO3.getOrd_rec()+",");
//			System.out.println(ord_masVO3.getOrd_adr()+",");
//			System.out.println(ord_masVO3.getOrd_tel()+",");
//			System.out.println(ord_masVO3.getOrd_can_rea()+",");
//			System.out.println("=================================");		
//		}
		
	
	//從訂單編號查明細
//		Set set = dao.getOrd_detByOrd_no("2018-01-030000000019");
//		Iterator ite = set.iterator();
//		while (ite.hasNext()) {
//			Ord_detVO ord_detVO = (Ord_detVO)ite.next();
//			System.out.println(ord_detVO.getOrd_no());
//			System.out.println(ord_detVO.getCom_no());
//			System.out.println(ord_detVO.getOd_quan());
//			System.out.println(ord_detVO.getOd_score());
//		}

	
	
		
		
		
	
	}

}
