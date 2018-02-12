package com.fav_shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Fav_shopDAO implements Fav_shopDAO_Interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	
	
	private static final String INSERT_STMT = "INSERT INTO FAVORITE_SHOP(MEM_NO,SHOP_NO) VALUES(?,?)";
	private static final String DELETE_STMT = "DELETE FROM FAVORITE_SHOP WHERE MEM_NO = ? AND SHOP_NO =?"; 
	private static final String FIND_BY_MEM_NO = "SELECT * FROM FAVORITE_SHOP WHERE MEM_NO = ? ORDER BY SHOP_NO";
	private static final String FIND_BY_SHOP_NO = "SELECT * FROM FAVORITE_SHOP WHERE SHOP_NO = ? ORDER BY MEM_NO";
	private static final String FIND_FK ="SELECT * FROM FAVORITE_SHOP WHERE SHOP_NO=? and MEM_NO= ?";
	private static final String GET_ALL = "SELECT * FROM FAVORITE_SHOP ORDER BY MEM_NO, SHOP_NO";


	@Override
	public void add(Fav_shopVO fav_shopVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, fav_shopVO.getMem_no());
			pstmt.setString(2, fav_shopVO.getShop_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String mem_no, String shop_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, mem_no);
			pstmt.setString(2, shop_no);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public List<Fav_shopVO>findByMem_no(String mem_no) {
		
		List<Fav_shopVO> Fav_shopVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Fav_shopVO fav_shopVO=new Fav_shopVO();
				
				fav_shopVO.setMem_no(rs.getString("mem_no"));
				fav_shopVO.setShop_no(rs.getString("shop_no"));
				Fav_shopVOList.add(fav_shopVO);
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return Fav_shopVOList;
		
	}

	@Override
	public List<Fav_shopVO> findByShop_no(String shop_no) {
		List<Fav_shopVO> Fav_shopVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_SHOP_NO);
			pstmt.setString(1, shop_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Fav_shopVO fav_shopVO=new Fav_shopVO();
				
				fav_shopVO.setMem_no(rs.getString("mem_no"));
				fav_shopVO.setShop_no(rs.getString("shop_no"));
				Fav_shopVOList.add(fav_shopVO);
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return Fav_shopVOList;
	
	}

	@Override
	public List<Fav_shopVO> getAll() {
		List<Fav_shopVO> Fav_shopVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Fav_shopVO fav_shopVO=new Fav_shopVO();
				
				fav_shopVO.setMem_no(rs.getString("mem_no"));
				fav_shopVO.setShop_no(rs.getString("shop_no"));
				Fav_shopVOList.add(fav_shopVO);
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return Fav_shopVOList;
		
	}

	@Override
	public Fav_shopVO findByFK(String shop_no, String mem_no) {
		Fav_shopVO fav_shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_FK);
			pstmt.setString(1, shop_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fav_shopVO = new Fav_shopVO();
				fav_shopVO.setMem_no(rs.getString("mem_no"));
				fav_shopVO.setShop_no(rs.getString("shop_no"));
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return fav_shopVO;
	}
	
	
	
	public static void main(String[] argc){
		
		Fav_shopDAO dao=new Fav_shopDAO();
		
		
		//新增
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入一般會員編號");
//		String mem_no=sc.next();
//		System.out.println("請輸入商家編號");
//		String shop_no=sc.next();
//		
//		Fav_shopVO fav_shopVO = new Fav_shopVO();
//		
//		fav_shopVO.setMem_no(mem_no);
//		fav_shopVO.setShop_no(shop_no);
//		dao.add(fav_shopVO);
//		System.out.println("新增資料成功!!");
//		sc.close();
		
		//刪除
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入一般會員編號");
//		String mem_no=sc.next();
//		System.out.println("請輸入商家編號");
//		String shop_no=sc.next();
//		
//		dao.delete(mem_no, shop_no);
//		System.out.println("刪除資料成功!!");
//		sc.close();
		
		//一般會員編號查詢收藏商家編號
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入一般會員編號");
//		String mem_no=sc.next();
//		List<Fav_shopVO> list=dao.findByMem_no(mem_no);
//		for(Fav_shopVO fav_shopVO:list){
//			System.out.println("搜尋到收藏商家編號");
//			System.out.println(fav_shopVO.getShop_no());
//		}
		
		
		//商家編號查詢收藏一般會員編號
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入商家編號");
//		String shop_no=sc.next();
//		List<Fav_shopVO> list=dao.findByShop_no(shop_no);
//		for(Fav_shopVO fav_shopVO:list){
//			System.out.println("搜尋到收藏一般會員編號");
//			System.out.println(fav_shopVO.getMem_no());
//		}
		
		//查詢全部收藏商家列表
		
		List<Fav_shopVO> list=dao.getAll();
		for(Fav_shopVO fav_shopVO:list){
			System.out.println("搜尋到收藏一般會員編號");
			System.out.println(fav_shopVO.getMem_no());
			System.out.println("搜尋到收藏商家編號");
			System.out.println(fav_shopVO.getShop_no());
		}
		
		
		
		
	}

	
	
}
