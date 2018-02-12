package com.fruit.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;

public class FruitDAO implements FruitDAO_Interface{
	private static final String INSERT_INTO="insert into fruit (fru_no,fru_name,fru_kno,fru_pho_base64) values('FRU'||LPAD(to_char(fruit_seq.nextval),7,'0'),?,?,?)";
	private static final String UPDATE="update fruit set fru_name=?, fru_kno=?, fru_pho_base64=? where fru_no=?";
	private static final String DELETE="delete from fruit where fru_no=?";
	private static final String FIND_BY_FRU_NO="select * from fruit where fru_no=?";
	private static final String GET_ALL="select * from fruit order by fru_no";
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(FruitVO fruitVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=ds.getConnection();
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
			con=ds.getConnection();
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
			con=ds.getConnection();
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
			con=ds.getConnection();
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
			con=ds.getConnection();
			pstmt=con.prepareStatement("select * from fruit where fru_name =?");
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
			con=ds.getConnection();
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
