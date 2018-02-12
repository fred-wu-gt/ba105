package com.fav_com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberDAO;
import com.member.model.MemberVO;

public class Fav_comJNDIDAO implements Fav_comDAO_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	
	
	private static final String INSERT_STMT = "INSERT INTO FAVORITE_COMMODITY(MEM_NO,COM_NO) VALUES(?,?)";
	private static final String DELETE_STMT = "DELETE FROM FAVORITE_COMMODITY WHERE MEM_NO = ? AND COM_NO = ? "; 
	private static final String FIND_BY_MEM_NO = "SELECT * FROM FAVORITE_COMMODITY WHERE MEM_NO = ? ORDER BY COM_NO";
	private static final String FIND_BY_COM_NO = "SELECT * FROM FAVORITE_COMMODITY WHERE COM_NO = ? ORDER BY MEM_NO";
	private static final String GET_ALL = "SELECT * FROM FAVORITE_COMMODITY ORDER BY MEM_NO, COM_NO";
	
	
	
	
	@Override
	public void add(Fav_comVO fav_comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, fav_comVO.getMem_no());
			pstmt.setString(2, fav_comVO.getCom_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String mem_no,String com_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, mem_no);
			pstmt.setString(2, com_no);
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public List<Fav_comVO> findByMem_no(String mem_no) {
	
		List<Fav_comVO> Fav_comVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Fav_comVO fav_comVO=new Fav_comVO();
				
				fav_comVO.setMem_no(rs.getString("mem_no"));
				fav_comVO.setCom_no(rs.getString("com_no"));
				Fav_comVOList.add(fav_comVO);
			}

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
		return Fav_comVOList;
	
	}
	
	@Override
	public List<Fav_comVO> findByCom_no(String com_no) {

		List<Fav_comVO> Fav_comVOList = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_COM_NO);
			pstmt.setString(1, com_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Fav_comVO fav_comVO=new Fav_comVO();
				
				fav_comVO.setMem_no(rs.getString("mem_no"));
				fav_comVO.setCom_no(rs.getString("com_no"));
				Fav_comVOList.add(fav_comVO);
			}

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
		return Fav_comVOList;
				
	}
	
	
	
	
	
	@Override
	public List<Fav_comVO> getAll() {
		
		List<Fav_comVO> Fav_comVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Fav_comVO fav_comVO=new Fav_comVO();
				
				fav_comVO.setMem_no(rs.getString("mem_no"));
				fav_comVO.setCom_no(rs.getString("com_no"));
				Fav_comVOList.add(fav_comVO);
			}

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
		return Fav_comVOList;
			
	}
	
	//===============================陳君開始=====================================
	@Override
	
	public Fav_comVO findByBoth(String mem_no,String com_no) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Fav_comVO fav_comVO =null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM FAVORITE_COMMODITY WHERE MEM_NO = ? AND COM_NO = ? ");
			pstmt.setString(1, mem_no);
			pstmt.setString(2, com_no);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				fav_comVO = new Fav_comVO();
				fav_comVO.setMem_no(rs.getString("mem_no"));
				fav_comVO.setCom_no(rs.getString("com_no"));
				System.out.println(rs.getString("mem_no"));
				System.out.println(rs.getString("com_no"));
				
			}
			System.out.println(fav_comVO);

		}  catch (SQLException se) {
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
		return fav_comVO;
	
	}
	
	
	//===============================陳君開始=====================================
	
	
}




