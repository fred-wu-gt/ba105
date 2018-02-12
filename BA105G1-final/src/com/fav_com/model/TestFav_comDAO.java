package com.fav_com.model;

import java.util.List;
import java.util.Scanner;

public class TestFav_comDAO {

	public static void main(String[] argc){
		Fav_comDAO dao=new Fav_comDAO();
		
		
		//新增
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入一般會員編號");
//		String mem_no=sc.next();
//		System.out.println("請輸入商品編號");
//		String com_no=sc.next();
//		
//		Fav_comVO fav_comVO = new Fav_comVO();
//		
//		fav_comVO.setMem_no(mem_no);
//		fav_comVO.setCom_no(com_no);
//		dao.add(fav_comVO);
//		System.out.println("新增資料成功!!");
//		sc.close();
		
		//刪除
		
		Scanner sc=new Scanner(System.in);		
		System.out.println("請輸入一般會員編號");
		String mem_no=sc.next();
		System.out.println("請輸入商品編號");
		String com_no=sc.next();
		
		dao.delete(mem_no,com_no);
		System.out.println("刪除資料成功!!");
		sc.close();
		
		//一般會員編號查詢收藏商品編號
//		
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入一般會員編號");
//		String mem_no=sc.next();
//		List<Fav_comVO> list=dao.findByMem_no(mem_no);
//		for(Fav_comVO fav_comVO:list){
//			System.out.println("搜尋到收藏商品編號");
//			System.out.println(fav_comVO.getCom_no());
//		}
		
		
		
		//商品編號查詢收藏一般會員編號
		
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入商品編號");
//		String com_no=sc.next();
//		List<Fav_comVO> list=dao.findByCom_no(com_no);
//		for(Fav_comVO fav_comVO:list){
//			System.out.println("搜尋到收藏一般會員編號");
//			System.out.println(fav_comVO.getMem_no());
//		}
		
		
		
		//查詢全部收藏商品列表
		
//		List<Fav_comVO> list=dao.getAll();
//		for(Fav_comVO fav_comVO:list){
//			System.out.println("搜尋到一般會員編號");
//			System.out.println(fav_comVO.getMem_no());
//			System.out.println("搜尋到收藏商品編號");
//			System.out.println(fav_comVO.getCom_no());
//		}
		
		
		
		
		
		
		
	}
	
	
	
	
}
