package com.commodity.model;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommodityJDBC_Test {

	public static void main(String[] args) throws FileNotFoundException  {
		
				
		
		CommodityJDBCDAO dao = new CommodityJDBCDAO();
		
	
		
		Map<String, String[]> map =  new HashMap<String, String[]> () ;
//		map.put("com_name", "K");
		
		 List<CommodityVO> list =dao.getAll(map);
		
		
		
//		//修改庫存量
//		CommodityVO commodityVO22 = new CommodityVO();
//		commodityVO22.setCom_store(100);
//		commodityVO22.setCom_no("COM0000008");
//		dao.updateComStorage(commodityVO22);
//		System.out.println("========修改庫存量成功!!=======");
		
//		新增
//		CommodityVO commodityVO1 = new CommodityVO();
//		commodityVO1.setCom_name("goodgoodeat grape");
//		commodityVO1.setShop_no("SHOP000010");
//		commodityVO1.setFru_no("FRU0000009");
//		commodityVO1.setCom_price(1680);
//		commodityVO1.setCom_weight("足重500g");
//		commodityVO1.setCom_remarks("約4-6顆,收到後請拿出箱外，置於通風處即可。");
//		commodityVO1.setCom_pic1(Imgtobyte.imgtobyte("WebContent\\fakeImg", "2.jpg"));
//		commodityVO1.setCom_pic2(Imgtobyte.imgtobyte("WebContent\\fakeImg", "3.jpg"));
//		commodityVO1.setCom_pic3(Imgtobyte.imgtobyte("WebContent\\fakeImg", "4.jpg"));
//		commodityVO1.setCom_status("下架");
//		commodityVO1.setCom_store(87);
//		commodityVO1.setCom_score(0.0);
//		commodityVO1.setCom_peo(0);
//		dao.insert(commodityVO1);
//		System.out.println("新增成功!!  拍拍手");
				
	
//		修改   	把com_time寫死為timestamp,以記錄最後一次更新的時間
//		CommodityVO commodityVO2 = new CommodityVO();
//		commodityVO2.setCom_name("GGYY大蘋果xx");
////		commodityVO2.setShop_no("SHOP000007");
//		commodityVO2.setFru_no("FRU0000001");
//		commodityVO2.setCom_price(1);
//		commodityVO2.setCom_weight("足重500g....吧");
//		commodityVO2.setCom_remarks("ABCDEFG HIJKZZZ");
//		commodityVO2.setCom_pic1(Imgtobyte.imgtobyte("WebContent\\fakeImg","5.jpg"));
//		commodityVO2.setCom_pic2(Imgtobyte.imgtobyte("WebContent\\fakeImg", "6.jpg"));
//		commodityVO2.setCom_pic3(Imgtobyte.imgtobyte("WebContent\\fakeImg", "7.jpg"));
//		commodityVO2.setCom_status("上架");
//		commodityVO2.setCom_store(877);

//		commodityVO2.setCom_no("COM0000010");
//		dao.updateForShop(commodityVO2);
//		System.out.println("修改成功!! ");
		
		//商品評分
//		CommodityVO commodityVO3 = new CommodityVO();
//		commodityVO3.setCom_score(4.4);
//		commodityVO3.setCom_peo(1);
//		commodityVO3.setCom_no("COM0000034");
//		dao.scoreCommodity(commodityVO3);
//		System.out.println(commodityVO3+"評分成功");
		
		
		

		
		
//		刪除
//		dao.delete("COM0000011");
//		System.out.println("刪除成功!! ");
		
		
//	//	 查一筆
//		CommodityVO comVO = dao.findByPrimaryKey("COM0000010");
//		java.text.DateFormat df = new java.text.SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
//		System.out.println(comVO.getCom_no());
//		System.out.println(comVO.getCom_name());
//		System.out.println(comVO.getShop_no());
//		System.out.println(comVO.getFru_no());
//		System.out.println(comVO.getCom_price());
//		System.out.println(comVO.getCom_weight());
//		System.out.println(comVO.getCom_remarks());
////		System.out.println("已將 "+comVO.getCom_pic1()+" 送到readImgFromDB資料夾");
////			BytetoImg.bytetoImg(comVO.getCom_pic1(),comVO.getCom_no()+"-1");
////		System.out.println("已將 "+comVO.getCom_pic2()+" 送到readImgFromDB資料夾");
////			BytetoImg.bytetoImg(comVO.getCom_pic2(),comVO.getCom_no()+"-2");
////		System.out.println("已將 "+comVO.getCom_pic3()+" 送到readImgFromDB資料夾");
////			BytetoImg.bytetoImg(comVO.getCom_pic3(),comVO.getCom_no()+"-3");
//		System.out.println(df.format(comVO.getCom_time()));
//		System.out.println(comVO.getCom_status());
//		System.out.println(comVO.getCom_store());
//		System.out.println(comVO.getCom_score());
//		System.out.println(comVO.getCom_peo());
//		System.out.println("================查詢成功=================");

		// 將timestamp格式化成"yyyy-MM-dd"
//		 java.text.DateFormat df = new java.text.SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
//		 System.out.println(df.format(comVO.getCom_time()));
		
//		查全部
//		 List<CommodityVO> list = dao.getAll();
//		 for(CommodityVO comVO1 : list ){
//			 System.out.println(comVO1.getCom_no());
//			 System.out.println(comVO1.getCom_name());
//			 System.out.println(comVO1.getShop_no());
//			 System.out.println(comVO1.getFru_no());
//			 System.out.println(comVO1.getCom_price());
//			 System.out.println(comVO1.getCom_weight());
//			 System.out.println(comVO1.getCom_remarks());
////			 System.out.println(comVO1.getCom_pic1()+" 送到readImgFromDB資料夾");
////			 	BytetoImg.bytetoImg(comVO1.getCom_pic1(),comVO1.getCom_no()+"-1");
////			 System.out.println(comVO1.getCom_pic2()+" 送到readImgFromDB資料夾");
////			 	BytetoImg.bytetoImg(comVO1.getCom_pic1(),comVO1.getCom_no()+"-2");
////			 System.out.println(comVO1.getCom_pic3()+" 送到readImgFromDB資料夾");
////			 	BytetoImg.bytetoImg(comVO1.getCom_pic1(),comVO1.getCom_no()+"-3");
//			 System.out.println(comVO1.getCom_time());
//			 System.out.println(comVO1.getCom_status());
//			 System.out.println(comVO1.getCom_store());
//			 System.out.println(comVO1.getCom_score());
//			 System.out.println(comVO1.getCom_peo());
//			 System.out.println("==================================");
//		 }
		 
	}	
	
	

}