package com.manager.model;

import java.util.*;
import java.io.*;
import java.sql.*;

public class ManagerJDBCDAO implements ManagerDAO_Interface{
	private static final String MY_URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="FRUIT";
	private static final String PASSWORD="FRUIT";
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_INTO="insert into manager (man_no,man_id,man_pas,man_name,man_gen,man_tel,man_add,man_pho_base64,man_sta) values('MAN'||LPAD(to_char(manager_seq.NEXTVAL),7,'0'),?,?,?,?,?,?,?,?)";
	private static final String UPDATE="update manager set man_id=?, man_pas=?, man_name=?, man_gen=?, man_tel=?, man_add=?, man_pho_base64=?, man_sta=? where man_no=?";
	private static final String FIND_BY_MAN_NO="select * from manager where man_no=?";
	private static final String FIND_BY_MAN_ID="select * from manager where man_id=?";
	private static final String FIND_BY_MAN_STA="select * from manager where man_sta=?";
	private static final String GET_ALL="select * from manager order by man_no";
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}

	@Override
	public String add(ManagerVO managerVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String man_no=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			String[] cols = {"man_no"};
			pstmt=con.prepareStatement(INSERT_INTO, cols);
			pstmt.setString(1, managerVO.getMan_id());
			pstmt.setString(2, managerVO.getMan_pas());
			pstmt.setString(3, managerVO.getMan_name());
			pstmt.setString(4, managerVO.getMan_gen());
			pstmt.setString(5, managerVO.getMan_tel());
			pstmt.setString(6, managerVO.getMan_add());
			pstmt.setString(7, managerVO.getMan_pho_base64());
			pstmt.setString(8, managerVO.getMan_sta());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				man_no = rs.getString(1);
			} System.out.println("新增成功管理員"+man_no);
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("帳號重覆"
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
		return man_no;
	}

	@Override
	public void update(ManagerVO managerVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, managerVO.getMan_id());
			pstmt.setString(2, managerVO.getMan_pas());
			pstmt.setString(3, managerVO.getMan_name());
			pstmt.setString(4, managerVO.getMan_gen());
			pstmt.setString(5, managerVO.getMan_tel());
			pstmt.setString(6, managerVO.getMan_add());
			pstmt.setString(7, managerVO.getMan_pho_base64());
			pstmt.setString(8, managerVO.getMan_sta());
			pstmt.setString(9, managerVO.getMan_no());
			pstmt.executeUpdate();
			// Handle any SQL errors
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
	public ManagerVO findByMan_no(String man_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ManagerVO managerVO=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_MAN_NO);
			pstmt.setString(1, man_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				managerVO=new ManagerVO();
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_pas(rs.getString("man_pas"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_gen(rs.getString("man_gen"));
				managerVO.setMan_tel(rs.getString("man_tel"));
				managerVO.setMan_add(rs.getString("man_add"));
				managerVO.setMan_pho_base64(rs.getString("man_pho_base64"));
				managerVO.setMan_sta(rs.getString("man_sta"));
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
		return managerVO;
	}
	
	@Override
	public ManagerVO findByMan_id(String man_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ManagerVO managerVO=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_MAN_ID);
			pstmt.setString(1, man_id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				managerVO=new ManagerVO();
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_pas(rs.getString("man_pas"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_gen(rs.getString("man_gen"));
				managerVO.setMan_tel(rs.getString("man_tel"));
				managerVO.setMan_add(rs.getString("man_add"));
				managerVO.setMan_pho_base64(rs.getString("man_pho_base64"));
				managerVO.setMan_sta(rs.getString("man_sta"));
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
		return managerVO;
	}
	
	@Override
	public List<ManagerVO> findByMan_sta(String man_sta) {
		List<ManagerVO> managerVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_MAN_STA);
			pstmt.setString(1, man_sta);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ManagerVO managerVO=new ManagerVO();
				
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_pas(rs.getString("man_pas"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_gen(rs.getString("man_gen"));
				managerVO.setMan_tel(rs.getString("man_tel"));
				managerVO.setMan_add(rs.getString("man_add"));
				managerVO.setMan_pho_base64(rs.getString("man_pho_base64"));
				managerVO.setMan_sta(rs.getString("man_sta"));
				
				managerVOList.add(managerVO);
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
		return managerVOList;
	}
	
	
	@Override
	public List<ManagerVO> getAll() {
		List<ManagerVO> managerVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ManagerVO managerVO=new ManagerVO();
				
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_pas(rs.getString("man_pas"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_gen(rs.getString("man_gen"));
				managerVO.setMan_tel(rs.getString("man_tel"));
				managerVO.setMan_add(rs.getString("man_add"));
				managerVO.setMan_pho_base64(rs.getString("man_pho_base64"));
				managerVO.setMan_sta(rs.getString("man_sta"));
				
				managerVOList.add(managerVO);
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
		return managerVOList;
	}
	
	public static void main(String[] args) throws IOException {    //方法1~3可搭配方法5使用
		/* 1. 新增 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲新增的管理員資料");
//		System.out.println("帳號:");
//		String man_id=sc.next();		
//		
//		System.out.println("密碼:");
//		String man_pas=sc.next();
//
//		System.out.println("姓名:");
//		String man_name=sc.next();
//
//		System.out.println("性別:");
//		String man_gen=sc.next();
//
//		System.out.println("手機:");
//		String man_tel=sc.next();
//
//		System.out.println("地址:");
//		String man_add=sc.next();
//		
//		System.out.println("狀態:");
//		String man_sta=sc.next();
//		
//		ManagerVO managerVO=new ManagerVO();
//		managerVO.setMan_id(man_id);
//		managerVO.setMan_pas(man_pas);
//		managerVO.setMan_name(man_name);
//		managerVO.setMan_gen(man_gen);
//		managerVO.setMan_tel(man_tel);
//		managerVO.setMan_add(man_add);
//		
//		byte[] man_pho = getPictureByteArray("image/testWriteToDB/noPicture1c.png");
//		managerVO.setMan_pho_base64(Base64.getEncoder().encodeToString(man_pho));
//		managerVO.setMan_sta(man_sta);
//		
//		ManagerJDBCDAO dao=new ManagerJDBCDAO();
//		dao.add(managerVO);
//		System.out.println("新增資料成功!!");
//		sc.close();
		
		/* 2. 修改 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲更新資料的(現有)管理員編號");
//		String man_no=sc.next();
//		
//		System.out.println("請輸入新的帳號");
//		String man_id=sc.next();
//
//		System.out.println("請輸入新的密碼");
//		String man_pas=sc.next();
//		
//		System.out.println("請輸入新的姓名");
//		String man_name=sc.next();
//		
//		System.out.println("請輸入新的性別");
//		String man_gen=sc.next();
//		
//		System.out.println("請輸入新的手機");
//		String man_tel=sc.next();
//		
//		System.out.println("請輸入新的地址");
//		String man_add=sc.next();
//		
//		System.out.println("請輸入新的狀態");
//		String man_sta=sc.next();
//		
//		ManagerVO managerVO=new ManagerVO();
//		managerVO.setMan_no(man_no);
//		managerVO.setMan_id(man_id);
//		managerVO.setMan_pas(man_pas);
//		managerVO.setMan_name(man_name);
//		managerVO.setMan_gen(man_gen);
//		managerVO.setMan_tel(man_tel);
//		managerVO.setMan_add(man_add);
//		byte[] man_pho = getPictureByteArray("image/testWriteToDB/wrongParameter2c.png");
//		managerVO.setMan_pho_base64(Base64.getEncoder().encodeToString(man_pho));
//		managerVO.setMan_sta(man_sta);
//		ManagerJDBCDAO dao=new ManagerJDBCDAO();
//		dao.update(managerVO);
//		
//		System.out.println("更新資料成功!!");
//		sc.close();
		

		/* 3. 以管理員編號查一個 */   
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲查詢的管理員編號");
//		String man_no=sc.next();
//		ManagerJDBCDAO dao=new ManagerJDBCDAO();
//		ManagerVO managerVO=dao.findByMan_no(man_no);
//		System.out.println("1.管理員編號:"+managerVO.getMan_no());
//		System.out.println("2.管理員帳號:"+managerVO.getMan_id());
//		System.out.println("3.管理員密碼:"+managerVO.getMan_pas());
//		System.out.println("4.管理員姓名:"+managerVO.getMan_name());
//		System.out.println("5.管理員性別:"+managerVO.getMan_gen());
//		System.out.println("6.管理員手機:"+managerVO.getMan_tel());
//		System.out.println("7.管理員地址:"+managerVO.getMan_add());
//		System.out.println("9.管理員狀態:"+managerVO.getMan_sta());
//		sc.close();
	
		
		/* 4. 以狀態查管理員 */
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲查詢的狀態");  //ex:停權
//		String man_sta=sc.next();
//		
//		ManagerJDBCDAO dao=new ManagerJDBCDAO();
//		List<ManagerVO> managerVOList=dao.findByMan_sta(man_sta);
//		for(ManagerVO managerVO:managerVOList){
//			System.out.println("1.管理員編號:"+managerVO.getMan_no());
//			System.out.println("2.管理員帳號:"+managerVO.getMan_id());
//			System.out.println("3.管理員密碼:"+managerVO.getMan_pas());
//			System.out.println("4.管理員姓名:"+managerVO.getMan_name());
//			System.out.println("5.管理員性別:"+managerVO.getMan_gen());
//			System.out.println("6.管理員手機:"+managerVO.getMan_tel());
//			System.out.println("7.管理員地址:"+managerVO.getMan_add());
//			System.out.println("9.管理員狀態:"+managerVO.getMan_sta());
//			System.out.println("============================");
//		}
//		sc.close();
		
		
		/* 5. 查全部 */
		System.out.println("所有管理員的資料為:");
		ManagerJDBCDAO dao2=new ManagerJDBCDAO();
		List<ManagerVO> managerVOList=dao2.getAll();
		for(ManagerVO managerVO2:managerVOList){
			String man_no2=managerVO2.getMan_no();
			System.out.println("1.管理員編號:"+man_no2);
			System.out.println("2.管理員帳號:"+managerVO2.getMan_id());
			System.out.println("3.管理員密碼:"+managerVO2.getMan_pas());
			System.out.println("4.管理員姓名:"+managerVO2.getMan_name());
			System.out.println("5.管理員性別:"+managerVO2.getMan_gen());
			System.out.println("6.管理員手機:"+managerVO2.getMan_tel());
			System.out.println("7.管理員地址:"+managerVO2.getMan_add());
			System.out.println("9.管理員狀態:"+managerVO2.getMan_sta());
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
