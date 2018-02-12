package com.wri_mes_reply.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Wri_mes_replyJDBCDAO implements Wri_mes_replyDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FRUIT";
	String password = "FRUIT";
	private static final String INSERT_STMT = "INSERT INTO WRITING_MESSAGE_REPLY (WMSGR_NO,WMSG_NO,SHOP_NO,WCR_CONT,WCR_TIME) "
			+ "VALUES(('WMSGR'||LPAD(TO_CHAR(WMSGR_NO_SEQ.NEXTVAL),5,'0')),?,?,?,SYSTIMESTAMP)";

	private static final String UPDATE_STMT = "UPDATE WRITING_MESSAGE_REPLY  SET WCR_CONT = ? " + "WHERE WMSGR_NO = ? ";

	private static final String DELETE = "DELETE FROM WRITING_MESSAGE_REPLY  WHERE WMSGR_NO = ? ";

	private static final String GET_ONE_STMT = "SELECT * FROM WRITING_MESSAGE_REPLY  WHERE WMSGR_NO = ? ";

	private static final String GET_ALL = "SELECT * FROM WRITING_MESSAGE_REPLY ";

	private static final String GET_MESSAGE_REPLY_FROM_WMSG_NO = "SELECT * FROM WRITING_MESSAGE_REPLY WHERE WMSG_NO = ? ORDER BY WMSGR_NO";

	public Integer insert(Wri_mes_replyVO wri_mes_replyVO) {

		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, wri_mes_replyVO.getWmsg_no());
			pstmt.setString(2, wri_mes_replyVO.getShop_no());
			pstmt.setString(3, wri_mes_replyVO.getWcr_cont());

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

	public Integer update(Wri_mes_replyVO wri_mes_replyVO) {

		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, wri_mes_replyVO.getWcr_cont());
			pstmt.setString(2, wri_mes_replyVO.getWmsgr_no());
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

	public Integer delete(String wmsgr_no) {

		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, wmsgr_no);
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

	public Wri_mes_replyVO findByPrimaryKey(String wmsgr_no) {

		Wri_mes_replyVO wri_mes_replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, wmsgr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri_mes_replyVO = new Wri_mes_replyVO();
				wri_mes_replyVO.setWmsgr_no(rs.getString("WMSGR_NO"));
				wri_mes_replyVO.setWmsg_no(rs.getString("WMSG_NO"));
				wri_mes_replyVO.setShop_no(rs.getString("SHOP_NO"));
				wri_mes_replyVO.setWcr_cont(rs.getString("WCR_CONT"));
				wri_mes_replyVO.setWcr_time(rs.getTimestamp("WCR_TIME"));

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wri_mes_replyVO;
	}

	public List<Wri_mes_replyVO> getAll() {


		List<Wri_mes_replyVO> wriList = new ArrayList<>();
		Wri_mes_replyVO wri = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri = new Wri_mes_replyVO();
				wri.setWmsgr_no(rs.getString("WMSGR_NO"));
				wri.setWmsg_no(rs.getString("WMSG_NO"));
				wri.setShop_no(rs.getString("SHOP_NO"));
				wri.setWcr_cont(rs.getString("WCR_CONT"));
				wri.setWcr_time(rs.getTimestamp("WCR_TIME"));
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
	public List<Wri_mes_replyVO> findAllWri_mes_replyByWmsg_no(String wmsg_no) {
		List<Wri_mes_replyVO> wri_mes_replylist = new ArrayList<>();
		Wri_mes_replyVO wri_mes_replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_MESSAGE_REPLY_FROM_WMSG_NO);
			pstmt.setString(1, wmsg_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri_mes_replyVO = new Wri_mes_replyVO();
				wri_mes_replyVO.setWmsgr_no(rs.getString("WMSGR_NO"));
				wri_mes_replyVO.setWmsg_no(rs.getString("WMSG_NO"));
				wri_mes_replyVO.setShop_no(rs.getString("SHOP_NO"));
				wri_mes_replyVO.setWcr_cont(rs.getString("WCR_CONT"));
				wri_mes_replyVO.setWcr_time(rs.getTimestamp("WCR_TIME"));
				wri_mes_replylist.add(wri_mes_replyVO);
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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

		return wri_mes_replylist;
	}
}
