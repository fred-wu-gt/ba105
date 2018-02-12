package com.wri_mes_report.model;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Wri_mes_reportJDBCDAO implements Wri_mes_reportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FRUIT";
	String password = "FRUIT";
	private static final String INSERT_STMT = "INSERT INTO WRITING_MESSAGE_REPORT(WMRPT_NO,WMSG_NO,MEM_NO,WMRPT_RSN,WMRPT_STAT,WMRPT_CONT) "
			+ "VALUES(('WMRPT'||LPAD(TO_CHAR(WMRPT_NO_SEQ.NEXTVAL),5,'0')),?,?,?,?,?)";

	private static final String UPDATE_STMT = "UPDATE WRITING_MESSAGE_REPORT SET WMRPT_RSN = ? ,WMRPT_STAT = ? ,WMRPT_CONT = ? "
			+ "WHERE WMRPT_NO = ? ";

	private static final String DELETE = "DELETE FROM WRITING_MESSAGE_REPORT WHERE WMRPT_NO = ? ";

	private static final String GET_ONE_STMT = "SELECT * FROM WRITING_MESSAGE_REPORT WHERE WMRPT_NO = ? ";

	private static final String GET_ALL = "SELECT * FROM WRITING_MESSAGE_REPORT";

	@Override
	public Integer insert(Wri_mes_reportVO wri_mes_reportVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, wri_mes_reportVO.getWmsg_no());
			pstmt.setString(2, wri_mes_reportVO.getMem_no());
			pstmt.setString(3, wri_mes_reportVO.getWmrpt_rsn());
			pstmt.setString(4, wri_mes_reportVO.getWmrpt_stat());
			pstmt.setString(5, wri_mes_reportVO.getWmrpt_cont());

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

	public Integer update(Wri_mes_reportVO wri_mes_reportVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, wri_mes_reportVO.getWmrpt_rsn());
			pstmt.setString(2, wri_mes_reportVO.getWmrpt_stat());
			pstmt.setString(3, wri_mes_reportVO.getWmrpt_cont());
			pstmt.setString(4, wri_mes_reportVO.getWmrpt_no());

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

	public Integer delete(String wmrpt_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, wmrpt_no);
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

	public Wri_mes_reportVO findByPrimaryKey(String wmrpt_no) {
		Wri_mes_reportVO wri_mes_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, wmrpt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri_mes_reportVO = new Wri_mes_reportVO();
				wri_mes_reportVO.setWmrpt_no(rs.getString("WMRPT_NO"));
				wri_mes_reportVO.setWmsg_no(rs.getString("WMSG_NO"));
				wri_mes_reportVO.setMem_no(rs.getString("MEM_NO"));
				wri_mes_reportVO.setWmrpt_rsn(rs.getString("WMRPT_RSN"));
				wri_mes_reportVO.setWmrpt_stat(rs.getString("WMRPT_STAT"));
				wri_mes_reportVO.setWmrpt_cont(rs.getString("WMRPT_CONT"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wri_mes_reportVO;
	}

	public List<Wri_mes_reportVO> getAll() {
		List<Wri_mes_reportVO> wriList = new ArrayList<>();
		Wri_mes_reportVO wri = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri = new Wri_mes_reportVO();
				wri.setWmrpt_no(rs.getString("WMRPT_NO"));
				wri.setWmsg_no(rs.getString("WMSG_NO"));
				wri.setMem_no(rs.getString("MEM_NO"));
				wri.setWmrpt_rsn(rs.getString("WMRPT_RSN"));
				wri.setWmrpt_stat(rs.getString("WMRPT_STAT"));
				wri.setWmrpt_cont(rs.getString("WMRPT_CONT"));
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

}
