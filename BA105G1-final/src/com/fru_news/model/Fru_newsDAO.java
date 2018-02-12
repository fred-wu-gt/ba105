package com.fru_news.model;

import java.util.*;
import java.io.*;
import java.sql.*;

public class Fru_newsDAO implements Fru_newsDAO_Interface{
	private static final String MY_URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="FRUIT";
	private static final String PASSWORD="FRUIT";
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_INTO="insert into fruit_news (fn_no,fn_tit,fn_con,fn_pho,fn_time) values('FN'||LPAD(to_char(fruit_news_seq.nextval),8,'0'),?,?,?,?)";
	private static final String UPDATE="update fruit_news set fn_tit=?, fn_con=?, fn_pho=?, fn_time=? where fn_no=?";
	private static final String DELETE="delete from fruit_news where fn_no=?";
	private static final String FIND_BY_FN_NO="select * from fruit_news where fn_no=?";
	private static final String GET_ALL="select * from fruit_news order by fn_no";
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}

	@Override
	public void add(Fru_newsVO fru_newsVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, fru_newsVO.getFn_tit());
			pstmt.setString(2, fru_newsVO.getFn_con());
			pstmt.setBytes(3, fru_newsVO.getFn_pho());
			pstmt.setTimestamp(4, fru_newsVO.getFn_time());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Fru_newsVO fru_newsVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, fru_newsVO.getFn_tit());
			pstmt.setString(2, fru_newsVO.getFn_con());
			pstmt.setBytes(3, fru_newsVO.getFn_pho());
			pstmt.setTimestamp(4, fru_newsVO.getFn_time());
			pstmt.setString(5, fru_newsVO.getFn_no());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String fn_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE);
			pstmt.setString(1, fn_no);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Fru_newsVO findByFn_no(String fn_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Fru_newsVO fru_newsVO=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_FN_NO);
			pstmt.setString(1, fn_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				fru_newsVO=new Fru_newsVO();
				fru_newsVO.setFn_no(rs.getString("fn_no"));
				fru_newsVO.setFn_tit(rs.getString("fn_tit"));
				fru_newsVO.setFn_con(rs.getString("fn_con"));
				fru_newsVO.setFn_pho(rs.getBytes("fn_pho"));
				fru_newsVO.setFn_time(rs.getTimestamp("fn_time"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return fru_newsVO;
	}

	@Override
	public List<Fru_newsVO> getAll() {
		List<Fru_newsVO> fru_newsVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Fru_newsVO fru_newsVO=new Fru_newsVO();
				
				fru_newsVO.setFn_no(rs.getString("fn_no"));
				fru_newsVO.setFn_tit(rs.getString("fn_tit"));
				fru_newsVO.setFn_con(rs.getString("fn_con"));
				fru_newsVO.setFn_pho(rs.getBytes("fn_pho"));
				fru_newsVO.setFn_time(rs.getTimestamp("fn_time"));
				
				fru_newsVOList.add(fru_newsVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return fru_newsVOList;
	}
	
	public static void main(String[] args) throws IOException {    //方法1~3可搭配方法5使用
		/* 1. 新增 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲新增的蔬果新聞資料");
//		System.out.println("蔬果新聞標題:");
//		String fn_tit=sc.next();
//
//		System.out.println("蔬果新聞內文:");
//		String fn_con=sc.next();
//
//		java.util.Date now=new java.util.Date();
//		Timestamp fn_time=new Timestamp(now.getTime());
//		Fru_newsVO fru_newsVO=new Fru_newsVO();
//		
//		fru_newsVO.setFn_tit(fn_tit);
//		fru_newsVO.setFn_con(fn_con);
//		
//		byte[] fn_pho = getPictureByteArray("image/testWriteToDB/noPicture1c.png");
//		fru_newsVO.setFn_pho(fn_pho);
//		
//		fru_newsVO.setFn_time(fn_time);
//		
//		Fru_newsDAO dao=new Fru_newsDAO();
//		dao.add(fru_newsVO);
//		System.out.println("新增資料成功!!");
//		sc.close();
		
		/* 2. 修改 */
		Scanner sc=new Scanner(System.in);		
		System.out.println("請輸入欲更新資料的(現有)蔬果新聞編號");
		String fn_no=sc.next();
		
		System.out.println("請輸入新的蔬果新聞標題");
		String fn_tit=sc.next();
		
		System.out.println("請輸入新的蔬果新聞內文");
		String fn_con=sc.next();
		
		java.util.Date now=new java.util.Date();
		Timestamp fn_time=new Timestamp(now.getTime());
		Fru_newsVO fru_newsVO=new Fru_newsVO();
		fru_newsVO.setFn_no(fn_no);
		fru_newsVO.setFn_tit(fn_tit);
		fru_newsVO.setFn_con(fn_con);
		
		byte[] fn_pho = getPictureByteArray("image/testWriteToDB/wrongParameter2c.png");
		fru_newsVO.setFn_pho(fn_pho);
		
		fru_newsVO.setFn_time(fn_time);
		
		Fru_newsDAO dao=new Fru_newsDAO();
		dao.update(fru_newsVO);
		
		System.out.println("更新資料成功!!");
		sc.close();
		
		/* 3. 刪除 */
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲刪除的蔬果新聞編號");
//		String fn_no=sc.next();
//		
//		Fru_newsDAO dao=new Fru_newsDAO();
//		dao.delete(fn_no);
//		System.out.println("刪除資料成功!!");
//		sc.close();
		
		/* 4. 以蔬果新聞編號查一個 */   
//		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲查詢的蔬果新聞編號");
//		String fn_no=sc.next();
//		Fru_newsDAO dao=new Fru_newsDAO();
//		Fru_newsVO fru_newsVO=dao.findByFn_no(fn_no);
//		System.out.println("1.蔬果新聞編號:"+fru_newsVO.getFn_no());
//		System.out.println("2.蔬果新聞標題:"+fru_newsVO.getFn_tit());
//		System.out.println("3.蔬果新聞內文:"+fru_newsVO.getFn_con());
//		System.out.println("4.蔬果新聞照片: 已輸出至image/testReadFromDB");
//		readPicture(fru_newsVO.getFn_pho(),fn_no);
//		System.out.println("5.更新時間:"+df.format(fru_newsVO.getFn_time()));
//		sc.close();
		
		/* 5. 查全部 */
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println("所有蔬果新聞的資料為:");
		Fru_newsDAO dao2=new Fru_newsDAO();
		List<Fru_newsVO> fru_newsVOList=dao2.getAll();
		for(Fru_newsVO fru_newsVO2:fru_newsVOList){
			String fn_no2=fru_newsVO2.getFn_no();
			System.out.println("1.蔬果新聞編號:"+fru_newsVO2.getFn_no());
			System.out.println("2.蔬果新聞標題:"+fru_newsVO2.getFn_tit());
			System.out.println("3.蔬果新聞內文:"+fru_newsVO2.getFn_con());
			System.out.println("4.蔬果新聞照片: 已輸出至image/testReadFromDB");
			readPicture(fru_newsVO2.getFn_pho(),fn_no2);
			System.out.println("5.更新時間:"+df.format(fru_newsVO2.getFn_time()));
			System.out.println("============================");
		}

		

	}
	
	
	
	
	
	
	
	
	
	
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  //ByteArrayOutputStream有內建byte陣列透過write()方法寫入資料,toByteArray()方法回傳 
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	public static void readPicture(byte[] bytes, String man_no) throws IOException {
		FileOutputStream fos = new FileOutputStream("image/testReadFromDB/"+man_no+".jpg");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}


}
