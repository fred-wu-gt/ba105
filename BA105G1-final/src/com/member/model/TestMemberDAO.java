package com.member.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class TestMemberDAO {

	
	public static void main(String[] argc) throws IOException{
		MemberDAO dao=new MemberDAO();
		
		//新增
//		MemberVo mem1 = new MemberVo();
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲新增的會員資料");
//		System.out.println("一般會員帳號:");
//		String mem_id=sc.next();
//		System.out.println("一般會員密碼:");
//		String mem_psw=sc.next();
//		System.out.println("一般會員姓名:");
//		String mem_name=sc.next();
//		System.out.println("一般會員性別:");
//		String mem_gen=sc.next();
//		System.out.println("一般會員生日:");
//		System.out.println("格式:西元年分-月份-日期");
//		Date mem_bir=java.sql.Date.valueOf(sc.next());
//		System.out.println("一般會員電子郵件:");
//		String mem_email=sc.next();
//		System.out.println("一般會員電話:");
//		String mem_phone=sc.next();
//		System.out.println("一般會員地址:");
//		String mem_loc=sc.next();
//		System.out.println("一般會員狀態:");
//		String mem_stat=sc.next();
//		System.out.println("一般會員違規記點:");
//		String mem_poin=sc.next();
//		System.out.println("一般會員點數:");
//		String mem_val=sc.next();
//		
//		
//		mem1.setMem_id(mem_id);
//		mem1.setMem_psw(mem_psw);
//		mem1.setMem_name(mem_name);
//		mem1.setMem_gen(mem_gen);
//		mem1.setMem_bir(mem_bir);
//		mem1.setMem_email(mem_email);
//		mem1.setMem_phone(mem_phone);
//		mem1.setMem_loc(mem_loc);
//		mem1.setMem_photo(getPictureByteArray("images/17.jpg"));
//		mem1.setMem_photo_base64(Base64.getEncoder().encodeToString(mem_photo));
//		mem1.setMem_stat(mem_stat);
//		mem1.setMem_poin(mem_poin);
//		mem1.setMem_val(mem_val);
//		dao.add(mem1);
//		sc.close();
//		System.out.println("=================新增成功=================");
		
		//修改
		MemberVO mem2 = new MemberVO();
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入修改會員資料");
//		System.out.println("一般會員帳號:");
//		String mem_id=sc.next();
//		System.out.println("一般會員密碼:");
//		String mem_psw=sc.next();
//		System.out.println("一般會員姓名:");
//		String mem_name=sc.next();
//		System.out.println("一般會員性別:");
//		String mem_gen=sc.next();
//		System.out.println("一般會員生日:");
//		System.out.println("格式:西元年分-月份-日期");
//		Date mem_bir=java.sql.Date.valueOf(sc.next());
//		System.out.println("一般會員電子郵件:");
//		String mem_email=sc.next();
//		System.out.println("一般會員電話:");
//		String mem_phone=sc.next();
//		System.out.println("一般會員地址:");
//		String mem_loc=sc.next();
//		System.out.println("一般會員狀態:");
//		String mem_stat=sc.next();
//		System.out.println("一般會員違規記點:");
//		String mem_poin=sc.next();
//		System.out.println("一般會員點數:");
//		String mem_val=sc.next();		
//		System.out.println("一般會員編號:");
//		String mem_no=sc.next();		
		
		
//		mem2.setMem_id(mem_id);	
//		mem2.setMem_psw(mem_psw);
//		mem2.setMem_name(mem_name);
//		mem2.setMem_gen(mem_gen);
//		mem2.setMem_bir(mem_bir);
//		mem2.setMem_email(mem_email);
//		mem2.setMem_phone(mem_phone);
//		mem2.setMem_loc(mem_loc);
//		mem2.setMem_photo(getPictureByteArray("images/13.jpg"));
//		mem2.setMem_photo_base64(Base64.getEncoder().encodeToString(mem_photo));
//		mem2.setMem_stat(mem_stat);
//		mem2.setMem_poin(mem_poin);
//		mem2.setMem_val(mem_val);
//		mem2.setMem_no(mem_no);	
		
		
		
//		dao.update(mem2);
	
//		sc.close();
//		System.out.println("================修改成功==================");
		
		
		//刪除
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入刪除會員帳號");
//		String mem_id=sc.next();
//		dao.delete(mem_id);
//		sc.close();
//		System.out.println("================刪除成功==================");
		
		//查詢1筆id
		Scanner sc=new Scanner(System.in);		
		System.out.println("請輸入查詢一筆會員帳號");
		String mem_id=sc.next();
		MemberVO mem3 = dao.findByMem_id(mem_id);
		
		System.out.println(mem3.getMem_no() + ",");
		System.out.println(mem3.getMem_id() + ",");
		System.out.println(mem3.getMem_psw() + ",");
		System.out.println(mem3.getMem_name() + ",");
		System.out.println(mem3.getMem_gen() + ",");
		System.out.println(mem3.getMem_bir() + ",");
		System.out.println(mem3.getMem_email() + ",");
		System.out.println(mem3.getMem_phone() + ",");
		System.out.println(mem3.getMem_loc() + ",");
		readPicture(mem3.getMem_photo(),mem_id);
		System.out.println(mem3.getMem_photo_base64());
		System.out.println(mem3.getMem_stat() + ",");
		System.out.println(mem3.getMem_poin() + ",");
		System.out.println(mem3.getMem_val() + ",");
		
		sc.close();
		System.out.println("===============查詢一筆資料成功===================");
		
		//查詢1筆no
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入查詢一筆會員編號");
//		String mem_no=sc.next();
//		MemberVO mem3 = dao.findByMem_no(mem_no);
//		
//		System.out.println(mem3.getMem_no() + ",");
//		System.out.println(mem3.getMem_id() + ",");
//		System.out.println(mem3.getMem_psw() + ",");
//		System.out.println(mem3.getMem_name() + ",");
//		System.out.println(mem3.getMem_gen() + ",");
//		System.out.println(mem3.getMem_bir() + ",");
//		System.out.println(mem3.getMem_email() + ",");
//		System.out.println(mem3.getMem_phone() + ",");
//		System.out.println(mem3.getMem_loc() + ",");
//		readPicture1(mem3.getMem_photo(),mem_no);
//		System.out.println(mem3.getMem_photo_base64());
//		System.out.println(mem3.getMem_stat() + ",");
//		System.out.println(mem3.getMem_poin() + ",");
//		System.out.println(mem3.getMem_val() + ",");
//		
//		sc.close();
//		System.out.println("===============查詢一筆資料成功===================");
		
		
		
//		查詢全部資料
//		List<MemberVO> list = dao.getAll();
//		for(MemberVO mem:list){
//			String mem_id=mem.getMem_id();
//			System.out.println(mem.getMem_no() + ",");
//			System.out.println(mem.getMem_id() + ",");
//			System.out.println(mem.getMem_psw() + ",");
//			System.out.println(mem.getMem_name() + ",");
//			System.out.println(mem.getMem_gen() + ",");
//			System.out.println(mem.getMem_bir() + ",");
//			System.out.println(mem.getMem_email() + ",");
//			System.out.println(mem.getMem_phone() + ",");
//			System.out.println(mem.getMem_loc() + ",");
//			readPicture(mem.getMem_photo(),mem_id);
//		    System.out.println(mem.getMem_photo_base64());
//			System.out.println(mem.getMem_stat() + ",");
//			System.out.println(mem.getMem_poin() + ",");
//			System.out.println(mem.getMem_val() + ",");
//			System.out.println("================查詢全部資料==================");
//		
//		}
		
		
		
		
		
		
		
	}
	
	

	// 使用byte[]方式寫入一筆圖檔
		public static byte[] getPictureByteArray(String path) throws IOException {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();//建立baos ByteArray陣列
			byte[] buffer = new byte[8192];//定義陣列大小
			int i;
			while ((i = fis.read(buffer)) != -1) {//把資料讀如
				baos.write(buffer, 0, i);//資料寫入BUFFER
			}
			baos.close();
			fis.close();

			return baos.toByteArray();
		}
	
	
		// Handle with byte array data 讀出圖檔
		public static void readPicture(byte[] bytes,String mem_id) throws IOException {
			FileOutputStream fos = new FileOutputStream("images/Output/"+mem_id+".jpg");
			fos.write(bytes);
			fos.flush();
			fos.close();
		}	
		
		// Handle with byte array data 讀出圖檔
				public static void readPicture1(byte[] bytes,String mem_no) throws IOException {
					FileOutputStream fos = new FileOutputStream("images/Output/"+mem_no+".jpg");
					fos.write(bytes);
					fos.flush();
					fos.close();
				}	
		
	
	
}
