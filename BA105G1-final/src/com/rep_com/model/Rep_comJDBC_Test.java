package com.rep_com.model;

import java.util.List;

import com.hotsales.model.HotsalesVO;

public class Rep_comJDBC_Test {

	public static void main(String[] args) {
		Rep_comJDBCDAO dao  = new Rep_comJDBCDAO() ;
		
		
//		新增
//		Rep_comVO repcomVO1 = new Rep_comVO ();
//		repcomVO1.setCom_no("COM0000009");
//		repcomVO1.setMem_no("MEM0000009");
//		repcomVO1.setRc_cuz("違反善良風俗");
//		repcomVO1.setRc_txt("安安你好XXXXX");
//		dao.insert(repcomVO1);
//		System.out.println("新增成功!  還.......不錯吧?");
		

		
		//修改狀態
		Rep_comVO repcomVO = new Rep_comVO ();
		repcomVO.setRc_stat("審核不通過");
		repcomVO.setRc_no("RC00000002");
		dao.updateRep_comStat(repcomVO);
		System.out.println("狀態修改成功");
		
		
//		修改
//		Rep_comVO repcomVO2 = new Rep_comVO ();
//		
//		repcomVO2.setRc_no("RC00000011");
//		repcomVO2.setCom_no("COM0000001");
//		repcomVO2.setMem_no("MEM0000002");
//		repcomVO2.setRc_cuz("違規食品");
//		repcomVO2.setRc_txt("YYYY安安你好");
//		repcomVO2.setRc_stat("審核不通過");
//		dao.update(repcomVO2);
//		System.out.println("修改成功!  還~~~~~~~不錯吧?");
		
//		刪除
//		dao.delete("RC00000011");
//		System.out.println("刪除成功!  真的還~~~~~~~不錯吧?");
		
//		查一筆
//		Rep_comVO rep_comVO = dao.findByPrimaryKey("RC00000011");
//		System.out.println(rep_comVO.getRc_no());
//		System.out.println(rep_comVO.getCom_no());
//		System.out.println(rep_comVO.getMem_no());
//		System.out.println(rep_comVO.getRc_cuz());
//		System.out.println(rep_comVO.getRc_txt());
//		System.out.println(rep_comVO.getRc_stat());	
//		System.out.println("===============單筆查詢成功!==============");
		
//		查全部
//		List<Rep_comVO> list = dao.getAll();
//		for(Rep_comVO rep_comVO1:list ){
//			System.out.println(rep_comVO1.getRc_no());
//			System.out.println(rep_comVO1.getCom_no());
//			System.out.println(rep_comVO1.getMem_no());
//			System.out.println(rep_comVO1.getRc_cuz());
//			System.out.println(rep_comVO1.getRc_txt());
//			System.out.println(rep_comVO1.getRc_stat());	
//			System.out.println("=============================");
//		}

	}

}
