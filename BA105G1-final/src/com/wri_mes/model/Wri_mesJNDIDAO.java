package com.wri_mes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Wri_mesJNDIDAO implements Wri_mesDAO_interface {
	/* 一個應用程式中，針對一個資料庫，只需要共用一個DataSource即可 */

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO WRITING_MESSAGE(WMSG_NO, WRT_NO, MEM_NO, WMSG_DATE, WMSG_CONT, WMSG_STAT)"
			+ "VALUES(('WMSG'||LPAD(TO_CHAR(WMSG_NO_SEQ.NEXTVAL),6,'0')), ?, ?,  SYSTIMESTAMP, ?, '正常')";

	private static final String UPDATE_STMT = "UPDATE WRITING_MESSAGE SET  WMSG_CONT = ?,  WMSG_STAT = ? "
			+ "WHERE WMSG_NO = ?";

	private static final String DELETE = "DELETE FROM WRITING_MESSAGE WHERE WMSG_NO = ?";

	private static final String GET_ONE_STMT = "SELECT * FROM WRITING_MESSAGE WHERE WMSG_NO = ?";

	private static final String GET_ALL = "SELECT * FROM WRITING_MESSAGE  ";

	private static final String GET_MESSAGE_FROM_WRT_NO = "SELECT * FROM WRITING_MESSAGE WHERE WRT_NO = ? ";

	@Override
	public Integer insert(Wri_mesVO wri_mesVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, wri_mesVO.getWrt_no());
			pstmt.setString(2, wri_mesVO.getMem_no());
			pstmt.setString(3, wri_mesVO.getWmsg_cont());

			pstmt.executeUpdate();

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
		return updateCount;
	}

	@Override
	public Integer update(Wri_mesVO wri_mesVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, wri_mesVO.getWmsg_cont());
			pstmt.setString(2, wri_mesVO.getWmsg_stat());
			pstmt.setString(3, wri_mesVO.getWmsg_no());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public Integer delete(String wmsg_no) {
		int deleteCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, wmsg_no);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return deleteCount;
	}

	@Override
	public Wri_mesVO findByPrimaryKey(String wmsg_no) {
		Wri_mesVO wri_mesVO = new Wri_mesVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, wmsg_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri_mesVO = new Wri_mesVO();
				wri_mesVO.setWmsg_no(rs.getString("WMSG_NO"));
				wri_mesVO.setWrt_no(rs.getString("WRT_NO"));
				wri_mesVO.setMem_no(rs.getString("MEM_NO"));
				wri_mesVO.setWmsg_date(rs.getTimestamp("WMSG_DATE"));
				wri_mesVO.setWmsg_cont(rs.getString("WMSG_CONT"));
				wri_mesVO.setWmsg_stat(rs.getString("WMSG_STAT"));
			}

		} catch (SQLException se) {
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

		return wri_mesVO;
	}

	@Override
	public List<Wri_mesVO> getAll() {
		List<Wri_mesVO> wri_mes_list = new ArrayList<>();
		Wri_mesVO wri = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri = new Wri_mesVO();
				wri.setWmsg_no(rs.getString("WMSG_NO"));
				wri.setWrt_no(rs.getString("WRT_NO"));
				wri.setMem_no(rs.getString("MEM_NO"));
				wri.setWmsg_cont(rs.getString("WMSG_CONT"));
				wri.setWmsg_stat(rs.getString("WMSG_STAT"));
				wri.setWmsg_date(rs.getTimestamp("WMSG_DATE"));
				wri_mes_list.add(wri);
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

		return wri_mes_list;
	}

	@Override
	public List<Wri_mesVO> findAllWri_mesByWrt_no(String wrt_no) {

		List<Wri_mesVO> wri_mes_list = new ArrayList<>();
		Wri_mesVO wri = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MESSAGE_FROM_WRT_NO);
			pstmt.setString(1, wrt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri = new Wri_mesVO();
				wri.setWmsg_no(rs.getString("WMSG_NO"));
				wri.setWrt_no(rs.getString("WRT_NO"));
				wri.setMem_no(rs.getString("MEM_NO"));
				wri.setWmsg_cont(rs.getString("WMSG_CONT"));
				wri.setWmsg_stat(rs.getString("WMSG_STAT"));
				wri.setWmsg_date(rs.getTimestamp("WMSG_DATE"));
				wri_mes_list.add(wri);
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

		return wri_mes_list;
	}

}
