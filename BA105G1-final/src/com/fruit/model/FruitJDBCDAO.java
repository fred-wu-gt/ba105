package com.fruit.model;

import java.util.*;

import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;

import java.io.*;
import java.sql.*;

public class FruitJDBCDAO implements FruitDAO_Interface{
	private static final String MY_URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="FRUIT";
	private static final String PASSWORD="FRUIT";
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_INTO="insert into fruit (fru_no,fru_name,fru_kno,fru_pho_base64) values('FRU'||LPAD(to_char(fruit_seq.nextval),7,'0'),?,?,?)";
	private static final String UPDATE="update fruit set fru_name=?, fru_kno=?, fru_pho_base64=? where fru_no=?";
	private static final String DELETE="delete from fruit where fru_no=?";
	private static final String FIND_BY_FRU_NO="select * from fruit where fru_no=?";
	private static final String FIND_BY_FRU_NAME="select * from fruit where fru_name=?";
	private static final String GET_ALL="select * from fruit order by fru_no";
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}

	@Override
	public void add(FruitVO fruitVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, fruitVO.getFru_name());
			pstmt.setString(2, fruitVO.getFru_kno());
			pstmt.setString(3, fruitVO.getFru_pho_base64());
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
	public void update(FruitVO fruitVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, fruitVO.getFru_name());
			pstmt.setString(2, fruitVO.getFru_kno());
			pstmt.setString(3, fruitVO.getFru_pho_base64());
			pstmt.setString(4, fruitVO.getFru_no());
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
	public void delete(String fru_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE);
			pstmt.setString(1, fru_no);
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
	public FruitVO findByFru_no(String fru_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		FruitVO fruitVO=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_FRU_NO);
			pstmt.setString(1, fru_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				fruitVO=new FruitVO();
				fruitVO.setFru_no(rs.getString("fru_no"));
				fruitVO.setFru_name(rs.getString("fru_name"));
				fruitVO.setFru_kno(rs.getString("fru_kno"));
				fruitVO.setFru_pho_base64(rs.getString("fru_pho_base64"));
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
		return fruitVO;
	}
	@Override
	public FruitVO findByFru_name(String fru_name) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		FruitVO fruitVO=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_FRU_NAME);
			pstmt.setString(1, fru_name);
			rs=pstmt.executeQuery();
			while(rs.next()){
				fruitVO=new FruitVO();
				fruitVO.setFru_no(rs.getString("fru_no"));
				fruitVO.setFru_name(rs.getString("fru_name"));
				fruitVO.setFru_kno(rs.getString("fru_kno"));
				fruitVO.setFru_pho_base64(rs.getString("fru_pho_base64"));
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
		return fruitVO;
	}

	@Override
	public List<FruitVO> getAll() {
		List<FruitVO> fruitVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				FruitVO fruitVO=new FruitVO();
				
				fruitVO.setFru_no(rs.getString("fru_no"));
				fruitVO.setFru_name(rs.getString("fru_name"));
				fruitVO.setFru_kno(rs.getString("fru_kno"));
				fruitVO.setFru_pho_base64(rs.getString("fru_pho_base64"));
				
				fruitVOList.add(fruitVO);
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
		return fruitVOList;
	}
	
	public static void main(String[] args) throws IOException {    //方法1~3可搭配方法5使用
		/* 1. 新增 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲新增的蔬果資料");
//		System.out.println("蔬果名稱:");
//		String fru_name=sc.next();
//
//		System.out.println("蔬果基本知識:");
//		String fru_kno=sc.next();
//		
//		FruitVO fruitVO=new FruitVO();
//		fruitVO.setFru_name(fru_name);
//		fruitVO.setFru_kno(fru_kno);
//		
//		byte[] fru_pho = getPictureByteArray("image/testWriteToDB/noPicture1c.png");
//		String fru_pho_base64=Base64Encoder.encode(fru_pho);
//		fruitVO.setFru_pho_base64(fru_pho_base64);
//		
//		FruitJDBCDAO dao=new FruitJDBCDAO();
//		dao.add(fruitVO);
//		System.out.println("新增資料成功!!");
//		sc.close();
		
		/* 2. 修改 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲更新資料的(現有)蔬果編號");
//		String fru_no=sc.next();
//		
//		System.out.println("請輸入新的蔬果名稱");
//		String fru_name=sc.next();
//		
//		System.out.println("請輸入新的蔬果基本知識");
//		String fru_kno=sc.next();
//		
//		FruitVO fruitVO=new FruitVO();
//		fruitVO.setFru_no(fru_no);
//		fruitVO.setFru_name(fru_name);
//		fruitVO.setFru_kno(fru_kno);
//		byte[] fru_pho = getPictureByteArray("image/testWriteToDB/wrongParameter2c.png");
//		String fru_pho_base64=Base64Encoder.encode(fru_pho);
//		fruitVO.setFru_pho_base64(fru_pho_base64);
//		FruitJDBCDAO dao=new FruitJDBCDAO();
//		dao.update(fruitVO);
//		
//		System.out.println("更新資料成功!!");
//		sc.close();
		
		
		/* 3. 刪除 */
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲刪除的蔬果編號");
//		String fru_no=sc.next();
//		
//		FruitJDBCDAO dao=new FruitJDBCDAO();
//		dao.delete(fru_no);
//		System.out.println("刪除資料成功!!");
//		sc.close();
		
		/* 4. 以蔬果編號查一個 */   
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲查詢的蔬果編號");
//		String fru_no=sc.next();
//		FruitJDBCDAO dao=new FruitJDBCDAO();
//		FruitVO fruitVO=dao.findByFru_no(fru_no);
//		System.out.println("1.蔬果編號:"+fruitVO.getFru_no());
//		System.out.println("2.蔬果名稱:"+fruitVO.getFru_name());
//		System.out.println("3.蔬果基本知識:"+fruitVO.getFru_kno());
//		System.out.println("4.蔬果照片: 已輸出至image/testReadFromDB");
//		String fru_pho_base64=fruitVO.getFru_pho_base64();
//		byte[] fru_pho = Base64Decoder.decodeToBytes(fru_pho_base64);
//		readPicture(fru_pho,fru_no);
//		sc.close();
		
		/* 5. 查全部 */
		System.out.println("所有蔬果的資料為:");
		FruitJDBCDAO dao2=new FruitJDBCDAO();
		List<FruitVO> fruitVOList=dao2.getAll();
		for(FruitVO fruitVO2:fruitVOList){
			String fru_no2=fruitVO2.getFru_no();
			System.out.println("1.蔬果編號:"+fru_no2);
			System.out.println("2.蔬果名稱:"+fruitVO2.getFru_name());
			System.out.println("3.蔬果基本知識:"+fruitVO2.getFru_kno());
			System.out.println("4.蔬果照片: 已輸出至image/testReadFromDB");
			byte[] fru_pho2 = Base64Decoder.decodeToBytes(fruitVO2.getFru_pho_base64());
			readPicture(fru_pho2,fru_no2);
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
