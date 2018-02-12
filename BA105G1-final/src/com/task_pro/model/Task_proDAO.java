package com.task_pro.model;

import java.util.*;
import java.io.*;
import java.sql.*;

public class Task_proDAO implements Task_proDAO_Interface{
	private static final String MY_URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="FRUIT";
	private static final String PASSWORD="FRUIT";
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_INTO="insert into task_product (tp_no,tp_name,tp_val,tp_des,tp_pho,tp_sta) values('TP'||LPAD(to_char(task_product_seq.NEXTVAL),8,'0'),?,?,?,?,?)";
	private static final String UPDATE="update task_product set tp_name=?, tp_val=?, tp_des=?, tp_pho=?, tp_sta=? where tp_no=?";
	private static final String DELETE="delete from task_product where tp_no=?";
	private static final String FIND_BY_TP_NO="select * from task_product where tp_no=?";
	private static final String GET_ALL="select * from task_product order by tp_no";
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}

	@Override
	public void add(Task_proVO task_proVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, task_proVO.getTp_name());
			pstmt.setInt(2, task_proVO.getTp_val());
			pstmt.setString(3, task_proVO.getTp_des());
			pstmt.setBytes(4, task_proVO.getTp_pho());
			pstmt.setString(5, task_proVO.getTp_sta());
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
	public void update(Task_proVO task_proVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, task_proVO.getTp_name());
			pstmt.setInt(2, task_proVO.getTp_val());
			pstmt.setString(3, task_proVO.getTp_des());
			pstmt.setBytes(4, task_proVO.getTp_pho());
			pstmt.setString(5, task_proVO.getTp_sta());
			pstmt.setString(6, task_proVO.getTp_no());
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
	public void delete(String tp_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE);
			pstmt.setString(1, tp_no);
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
	public Task_proVO findByTp_no(String tp_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Task_proVO task_proVO=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_TP_NO);
			pstmt.setString(1, tp_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				task_proVO=new Task_proVO();
				task_proVO.setTp_no(rs.getString("tp_no"));
				task_proVO.setTp_name(rs.getString("tp_name"));
				task_proVO.setTp_val(rs.getInt("tp_val"));
				task_proVO.setTp_des(rs.getString("tp_des"));
				task_proVO.setTp_pho(rs.getBytes("tp_pho"));
				task_proVO.setTp_sta(rs.getString("tp_sta"));
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
		return task_proVO;
	}

	@Override
	public List<Task_proVO> getAll() {
		List<Task_proVO> task_proVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Task_proVO task_proVO=new Task_proVO();
				
				task_proVO.setTp_no(rs.getString("tp_no"));
				task_proVO.setTp_name(rs.getString("tp_name"));
				task_proVO.setTp_val(rs.getInt("tp_val"));
				task_proVO.setTp_des(rs.getString("tp_des"));
				task_proVO.setTp_pho(rs.getBytes("tp_pho"));
				task_proVO.setTp_sta(rs.getString("tp_sta"));
				
				task_proVOList.add(task_proVO);
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
		return task_proVOList;
	}
	
	public static void main(String[] args) throws IOException {    //方法1~3可搭配方法5使用
		/* 1. 新增 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲新增的任務商品");
//		System.out.println("任務商品名稱:");
//		String tp_name=sc.next();		
//		
//		System.out.println("兌換點數:");
//		Integer tp_val=sc.nextInt();
//
//		System.out.println("任務商品敘述:");
//		String tp_des=sc.next();
//
//		System.out.println("任務商品狀態:");
//		String tp_sta=sc.next();
//		
//		Task_proVO task_proVO=new Task_proVO();
//		task_proVO.setTp_name(tp_name);
//		task_proVO.setTp_val(tp_val);
//		task_proVO.setTp_des(tp_des);
//		
//		byte[] tp_pho = getPictureByteArray("image/testWriteToDB/noPicture1c.png");
//		task_proVO.setTp_pho(tp_pho);
//		
//		task_proVO.setTp_sta(tp_sta);
//		
//		Task_proDAO dao=new Task_proDAO();
//		dao.add(task_proVO);
//		System.out.println("新增資料成功!!");
//		sc.close();
		
		/* 2. 修改 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲更新資料的(現有)任務商品編號");
//		String tp_no=sc.next();
//		
//		System.out.println("任務商品名稱:");
//		String tp_name=sc.next();		
//		
//		System.out.println("兌換點數:");
//		Integer tp_val=sc.nextInt();
//
//		System.out.println("任務商品敘述:");
//		String tp_des=sc.next();
//
//		System.out.println("任務商品狀態:");
//		String tp_sta=sc.next();
//		
//		Task_proVO task_proVO=new Task_proVO();
//		task_proVO.setTp_no(tp_no);
//		task_proVO.setTp_name(tp_name);
//		task_proVO.setTp_val(tp_val);
//		task_proVO.setTp_des(tp_des);
//		
//		byte[] tp_pho = getPictureByteArray("image/testWriteToDB/wrongParameter2c.png");
//		task_proVO.setTp_pho(tp_pho);
//		
//		task_proVO.setTp_sta(tp_sta);
//		
//		Task_proDAO dao=new Task_proDAO();
//		dao.update(task_proVO);
//		
//		System.out.println("更新資料成功!!");
//		sc.close();
		
		/* 3. 刪除 */
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲刪除的任務商品編號");
//		String tp_no=sc.next();
//		
//		Task_proDAO dao=new Task_proDAO();
//		dao.delete(tp_no);
//		
//		System.out.println("刪除資料成功!!");
//		sc.close();
		
		/* 4. 以任務商品編號查一個 */   
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲查詢的任務商品編號");
//		String tp_no=sc.next();
//		Task_proDAO dao=new Task_proDAO();
//		Task_proVO task_proVO=dao.findByTp_no(tp_no);
//		System.out.println("1.任務商品編號:"+task_proVO.getTp_no());
//		System.out.println("2.任務商品名稱:"+task_proVO.getTp_name());
//		System.out.println("3.兌換點數:"+task_proVO.getTp_val());
//		System.out.println("4.任務商品敘述:"+task_proVO.getTp_des());
//		
//		System.out.println("5.任務商品照片: 已輸出至image/testReadFromDB");
//		readPicture(task_proVO.getTp_pho(),tp_no);
//		
//		System.out.println("6.任務商品狀態:"+task_proVO.getTp_sta());
//		sc.close();
		
		/* 5. 查全部 */
		System.out.println("所有任務商品的資料為:");
		Task_proDAO dao2=new Task_proDAO();
		List<Task_proVO> task_proVOList=dao2.getAll();
		for(Task_proVO task_proVO2:task_proVOList){
			String tp_no2=task_proVO2.getTp_no();
			System.out.println("1.任務商品編號:"+tp_no2);
			System.out.println("2.任務商品名稱:"+task_proVO2.getTp_name());
			System.out.println("3.兌換點數:"+task_proVO2.getTp_val());
			System.out.println("4.任務商品敘述:"+task_proVO2.getTp_des());
			
			System.out.println("5.任務商品照片: 已輸出至image/testReadFromDB");
			readPicture(task_proVO2.getTp_pho(),tp_no2);
			
			System.out.println("6.任務商品狀態:"+task_proVO2.getTp_sta());
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
