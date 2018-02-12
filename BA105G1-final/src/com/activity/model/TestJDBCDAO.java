package com.activity.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJDBCDAO {

	public static void main(String[] args) throws IOException {

		ActivityJDBCDAO dao = new ActivityJDBCDAO();
		
		// 新增活動 ok
//		ActivityVO activityVO = new ActivityVO();
//		activityVO.setShop_no("SHOP000001");
//		activityVO.setAct_name("TEST");
//		
//		try {
//			byte[] pic = getPictureByteArray("image/SendToDB/3.jpg");
//			activityVO.setAct_pic(pic);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
//		activityVO.setAct_start(java.sql.Timestamp.valueOf("2018-12-12 9:10:00"));
//		activityVO.setAct_end(java.sql.Timestamp.valueOf("2018-12-12 5:10:00"));
//		activityVO.setAct_art("AAA");
//		dao.insert(activityVO); 
//		System.out.println("-------新增成功----------");

		// 修改
//		ActivityVO activityVO = new ActivityVO();
//		activityVO.setAct_no("ACT0000010");
//		activityVO.setShop_no("SHOP000002");
//		activityVO.setAct_name("aaa");
//		
//		try {
//			byte[] pic = getPictureByteArray("image/SendToDB/2.jpg");
//			activityVO.setAct_pic(pic);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		activityVO.setAct_start(java.sql.Timestamp.valueOf("2018-9-12 9:10:00"));
//		activityVO.setAct_end(java.sql.Timestamp.valueOf("2018-9-13 5:10:00"));
//		activityVO.setAct_art("bbbbbbbbbbbbbbbbbbbbbb");
//		activityVO.setAct_status("BBB");
//		activityVO.setAct_ls("已開始");
//		dao.update(activityVO); 
//		System.out.println("-------修改成功---------");
		
		// 查單一
//		ActivityVO activityVO = dao.findByPrimaryKey("ACT0000001");
//		try {
//			System.out.println("start");
//			readPicture(activityVO.getAct_pic());
//			System.out.println("enddd");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.print(activityVO.getAct_no() + ",");
//		System.out.println(activityVO.getShop_no() + ",");
//		System.out.print(activityVO.getAct_name()+ ",");
//		System.out.print(activityVO.getAct_pic() + ",");
//		System.out.print(activityVO.getAct_start() + ",");
//		System.out.print(activityVO.getAct_end() + ",");
//		System.out.print("活動內文 : "+ activityVO.getAct_art() + ",");
//		System.out.print(activityVO.getAct_status() + ",");
//		System.out.println(activityVO.getAct_ls());
//		System.out.println("---------------------");
	
	
		// 查全部
//		List<ActivityVO> list = dao.getAll();
//		for (ActivityVO activityVO : list) {
//			System.out.print("活動編號 : "+ activityVO.getAct_no() + ",");
//			System.out.println("商家編號 : " + activityVO.getShop_no() + ",");
//			System.out.print("活動標題 : " + activityVO.getAct_name()+ ",");
//			
//			readPicture(activityVO.getAct_pic(), activityVO.getAct_no());
//			System.out.println("活動照片: 已輸出至image/ReadFromDB");
//			
//			System.out.print("活動開始時間 : "+ activityVO.getAct_start() + ",");
//			System.out.print("活動結束時間 : "+ activityVO.getAct_end() + ",");
//			System.out.print("活動內文 : "+ activityVO.getAct_art() + ",");
//			System.out.print("活動狀態 : "+ activityVO.getAct_status() + ",");
//			System.out.println("直播狀態 : " +activityVO.getAct_ls());
//			System.out.println("---------------------");
//		}
		
		// 刪除  child record found
//		dao.delete("ACT0000001");
//		System.out.println("-------SUCCESS--------");

//======================================================================
		//查看活動詳情 ok
//		ActivityVO activityVO = dao.findByActNo("ACT0000001");
//		try {
//		System.out.println("start");
//		readPicture(activityVO.getAct_pic());
//		System.out.println("enddd");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.print(activityVO.getAct_no() + ",");
//		System.out.print(activityVO.getAct_name()+ ",");
//		System.out.print(activityVO.getAct_pic() + ",");
//		System.out.print(activityVO.getAct_start() + ",");
//		System.out.print(activityVO.getAct_end() + ",");
//		System.out.println("活動內文 : "+ activityVO.getAct_art() );
//		System.out.println("-------SUCCESS-------");

		//顯示自家活動列表 ok
//		List<ActivityVO> list = dao.findByShopNo("SHOP000006");
//		for (ActivityVO activityVO : list) {
//			try {
//				System.out.println("start");
//				readPicture(activityVO.getAct_pic(), activityVO.getAct_no());
//				System.out.println("enddd");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			System.out.print("活動標題 : " + activityVO.getAct_name()+ ",");
//			System.out.println("活動開始時間 : "+ activityVO.getAct_start() + ",");
//			System.out.print("活動結束時間 : "+ activityVO.getAct_end() + ",");
//			System.out.println("活動內文 : "+ activityVO.getAct_art());
//			System.out.println("--------------------");
//		}
//		System.out.println("-------SUCCESS-------");
		
		//顯示所有活動列表(會員) 
//		List<ActivityVO> list = dao.getAllAct();
//		for (ActivityVO activityVO : list) {
//			System.out.print("活動編號 : "+ activityVO.getAct_no() + ",");
//			System.out.print("活動標題 : " + activityVO.getAct_name()+ ",");
//			readPicture(activityVO.getAct_pic(), activityVO.getAct_no());
//			System.out.println("活動照片: 已輸出至image/ReadFromDB");
//			System.out.print("活動開始時間 : "+ activityVO.getAct_start() + ",");	
//			System.out.println("活動內文 : "+ activityVO.getAct_art());
//			System.out.println("---------------------");
//		}
//		System.out.println("-------SUCCESS-------");
		
		//關鍵字搜尋活動 ok
//		List<ActivityVO> list = dao.search("%小番茄%");
//		for (ActivityVO activityVO : list) {
//			System.out.print("活動編號 : "+ activityVO.getAct_no() + ",");
//			System.out.print("活動標題 : " + activityVO.getAct_name()+ ",");
//			readPicture(activityVO.getAct_pic(), activityVO.getAct_no());
//			System.out.println("活動照片: 已輸出至image/ReadFromDB");
//			System.out.print("活動開始時間 : "+ activityVO.getAct_start() + ",");	
//			System.out.println("活動內文 : "+ activityVO.getAct_art());
//			System.out.println("---------------------");
//		}
//		System.out.println("-------SUCCESS-------");
		
		//修改活動資料(不含狀態) ok
//		ActivityVO activityVO = new ActivityVO();
//		activityVO.setAct_no("ACT0000001");
//		activityVO.setShop_no("SHOP000003");
//		activityVO.setAct_name("aaa");
//		
//		try {
//			byte[] pic = getPictureByteArray("image/SendToDB/1.jpg");
//			activityVO.setAct_pic(pic);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		activityVO.setAct_start(java.sql.Timestamp.valueOf("2018-9-12 9:10:00"));
//		activityVO.setAct_end(java.sql.Timestamp.valueOf("2018-9-13 4:10:00"));
//		activityVO.setAct_art("活動內容被修改囉!");
//		dao.update(activityVO); 
//		System.out.println("-------修改成功---------");
		
		//修改活動資料(只有活動狀態:活動開始時間到了改成'已開始'; 活動被商家取消改成'活動取消') OK 
//		dao.updateActStatus("已開始", "ACT0000001"); 
//		System.out.println("活動狀態被修改囉!");
//		System.out.println("-------修改成功---------");
		
		//修改活動資料(只有直播狀態) ok
//		dao.updateLiveStatus("已開始", "ACT0000001"); 
//		System.out.println("直播狀態被修改囉!");
//		System.out.println("-------修改成功---------");
		
	}
	
	
	
	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		//把fileInputStream讀到的資料寫進buffer,在把buffer的資料取出來 在寫到ByteArrayOutputStream
		//再從ByteArrayOutputStream取出byte陣列 再用pstmt setBytes到資料庫
		
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();// 目的地:byte[],看不到
		byte[] buffer = new byte[bis.available()];
		bis.read(buffer);
		baos.write(buffer);
		fis.close();
		baos.close();

		return baos.toByteArray();// 回傳byte[]
	}
	

	//handle file name
//	public static byte[] getPicture(String pk) throws IOException {
//		if(pk.contains(pk)){
//			
//		}
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		BufferedInputStream bis = new BufferedInputStream(fis);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();// 目的地:byte[],看不到
//		byte[] buffer = new byte[bis.available()];
//		bis.read(buffer);
//		baos.write(buffer);
//		fis.close();
//		baos.close();
//
//		return baos.toByteArray();// 回傳byte[]
//	}
	
	
//===============================================================
	
	// Handle with byte array data
	public static void readPicture(byte[] bytes) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		BufferedInputStream bis2 = new BufferedInputStream(bis);

		FileOutputStream fos = new FileOutputStream("image/ReadFromDB/3.jpg");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		byte[] buf = new byte[8192];
		//第一次 把ByteArrayInputStream讀到的資料(從DB) 不是檔尾就寫進buffer
		int read=  bis2.read(buf);
		while(read != -1) {
			 //把buffer的資料取出來 在寫到OutputStream
			 bos.write(buf);
			 read = bis2.read(buf);//在判斷讀到資料不是檔尾 就在寫
		}
	
		bos.flush();
		bis2.close();
		bos.close();
		
	}
	
	// handle file name
	public static void readPicture(byte[] bytes, String act_no) throws IOException {
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		BufferedInputStream bis2 = new BufferedInputStream(bis);
		

		// handle file name
		FileOutputStream fos = new FileOutputStream("image/ReadFromDB/"+ act_no +".jpg");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		byte[] buf = new byte[8192];
		//第一次 把ByteArrayInputStream讀到的資料(從DB) 不是檔尾就寫進buffer
		int read=  bis2.read(buf);
		while(read != -1) {
			 //把buffer的資料取出來 在寫到OutputStream
			 bos.write(buf);
			 read = bis2.read(buf);//在判斷讀到資料不是檔尾 就在寫
		}
	
		bos.flush();
		bis2.close();
		bos.close();

	}
	
	
	
	

}
