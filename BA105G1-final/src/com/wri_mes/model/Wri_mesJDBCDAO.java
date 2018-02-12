package com.wri_mes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Wri_mesJDBCDAO implements Wri_mesDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FRUIT";
	String password = "FRUIT";

	private static final String INSERT_STMT = "INSERT INTO WRITING_MESSAGE(WMSG_NO,WRT_NO,MEM_NO,WMSG_DATE,WMSG_CONT,WMSG_STAT)"
			+ "VALUES(('WMSG'||LPAD(TO_CHAR(WMSG_NO_SEQ.NEXTVAL),6,'0')), ?, ?, SYSTIMESTAMP, ?, '正常')";

	private static final String UPDATE_STMT = "UPDATE WRITING_MESSAGE SET  WMSG_CONT = ? , WMSG_STAT = ? "
			+ "WHERE WMSG_NO = ? ";

	private static final String DELETE = "DELETE FROM WRITING_MESSAGE WHERE WMSG_NO = ? ";

	private static final String GET_ONE_STMT = "SELECT * FROM WRITING_MESSAGE WHERE WMSG_NO = ? ";

	private static final String GET_ALL = "SELECT * FROM WRITING_MESSAGE";
	
	private static final String GET_MESSAGE_FROM_WRT_NO = "SELECT WMSG_CONT FROM WRITING_MESSAGE WHERE WRT_NO = ? ";

	@Override
	public Integer insert(Wri_mesVO wri_mesVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, wri_mesVO.getWrt_no());
			pstmt.setString(2, wri_mesVO.getMem_no());
			pstmt.setString(3, wri_mesVO.getWmsg_cont());
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCount;
	}

	public Integer update(Wri_mesVO wri_mesVO) {

		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, wri_mesVO.getWmsg_cont());
			pstmt.setString(2, wri_mesVO.getWmsg_stat());
			pstmt.setString(3, wri_mesVO.getWmsg_no());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCount;

	}

	public Integer delete(String wmsg_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, wmsg_no);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return updateCount;

	}

	public Wri_mesVO findByPrimaryKey(String wmsg_no) {

		Wri_mesVO wri_mesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, wmsg_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri_mesVO= new Wri_mesVO();
				wri_mesVO.setWmsg_no(rs.getString("WMSG_NO"));
				wri_mesVO.setWrt_no(rs.getString("WRT_NO"));
				wri_mesVO.setMem_no(rs.getString("MEM_NO"));
				wri_mesVO.setWmsg_date(rs.getTimestamp("WMSG_DATE"));
				wri_mesVO.setWmsg_cont(rs.getString("WMSG_CONT"));
				wri_mesVO.setWmsg_stat(rs.getString("WMSG_STAT"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return wri_mesVO;
	}

	public List<Wri_mesVO> getAll() {
		List<Wri_mesVO> wriList = new ArrayList<>();
		Wri_mesVO wri = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri = new Wri_mesVO();
				wri.setWmsg_no(rs.getString("WMSG_NO"));
				wri.setWrt_no(rs.getString("WRT_NO"));
				wri.setMem_no(rs.getString("MEM_NO"));
				wri.setWmsg_date(rs.getTimestamp("WMSG_DATE"));
				wri.setWmsg_cont(rs.getString("WMSG_CONT"));
				wri.setWmsg_stat(rs.getString("WMSG_STAT"));
				wriList.add(wri);
				
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

		return wriList;
		
	}

	@Override
	public List<Wri_mesVO> findAllWri_mesByWrt_no(String wrt_no) {
		List<Wri_mesVO> wri_mesList =  new ArrayList<>();
		Wri_mesVO wri_mesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_MESSAGE_FROM_WRT_NO);
			pstmt.setString(1, wrt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri_mesVO= new Wri_mesVO();
				wri_mesVO.setWmsg_no(rs.getString("WMSG_NO"));
				wri_mesVO.setWrt_no(rs.getString("WRT_NO"));
				wri_mesVO.setMem_no(rs.getString("MEM_NO"));
				wri_mesVO.setWmsg_date(rs.getTimestamp("WMSG_DATE"));
				wri_mesVO.setWmsg_cont(rs.getString("WMSG_CONT"));
				wri_mesVO.setWmsg_stat(rs.getString("WMSG_STAT"));
				wri_mesList.add(wri_mesVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return wri_mesList;
	}

}
