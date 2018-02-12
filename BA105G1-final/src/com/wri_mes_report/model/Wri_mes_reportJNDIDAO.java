package com.wri_mes_report.model;

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

import com.wri_mes.model.Wri_mesVO;

public class Wri_mes_reportJNDIDAO implements Wri_mes_reportDAO_interface {
	// 一個應用程式中，針對一個資料庫，只需要共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO WRITING_MESSAGE_REPORT(WMRPT_NO,WMSG_NO,MEM_NO,WMRPT_RSN,WMRPT_STAT,WMRPT_CONT)"
			+ "VALUES(('WMRPT'||LPAD(TO_CHAR(WMSG_NO_SEQ.NEXTVAL),5,'0')),?,?,?,?,?)";

	private static final String UPDATE_STMT = "UPDATE WRITING_MESSAGE_REPORT SET  WMRPT_RSN = ?,  WMRPT_STAT = ? WMRPT_CONT = ?"
			+ "WHERE WMRPT_NO = ?";

	private static final String DELETE = "DELETE FROM WRITING_MESSAGE_REPORT WHERE WMRPT_NO = ?";

	private static final String GET_ONE_STMT = "SELECT * FROM WRITING_MESSAGE_REPORT WHERE WMRPT_NO = ?";

	private static final String GET_ALL = "SELECT * FROM WRITING_MESSAGE_REPORT  ";

	@Override
	public Integer insert(Wri_mes_reportVO wri_mes_reportVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = null;
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, wri_mes_reportVO.getWmsg_no());
			pstmt.setString(2, wri_mes_reportVO.getMem_no());
			pstmt.setString(3, wri_mes_reportVO.getWmrpt_rsn());
			pstmt.setString(4, wri_mes_reportVO.getWmrpt_stat());
			pstmt.setString(5, wri_mes_reportVO.getWmrpt_cont());
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
	public Integer update(Wri_mes_reportVO wri_mes_reportVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, wri_mes_reportVO.getWmrpt_rsn());
			pstmt.setString(2, wri_mes_reportVO.getWmrpt_stat());
			pstmt.setString(3, wri_mes_reportVO.getWmrpt_cont());
			pstmt.setString(4, wri_mes_reportVO.getWmrpt_no());

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
	public Integer delete(String wmrpt_no) {

		int deleteCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, wmrpt_no);
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
	public Wri_mes_reportVO findByPrimaryKey(String wmrpt_no) {
		Wri_mes_reportVO wri_mes_reportVO = new Wri_mes_reportVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, wmrpt_no);
			rs = pstmt.executeQuery();

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

		return wri_mes_reportVO;
	}

	@Override
	public List<Wri_mes_reportVO> getAll() {
		List<Wri_mes_reportVO> wri_mes_report_list = new ArrayList<>();
		Wri_mes_reportVO wri = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri = new Wri_mes_reportVO();
				wri.setWmrpt_no(rs.getString("WMRPT_NO"));
				wri.setWmsg_no(rs.getString("WMSG_NO"));
				wri.setMem_no(rs.getString("Mem_no"));
				wri.setWmrpt_stat(rs.getString("WMRPT_STAT"));
				wri.setWmrpt_cont(rs.getString("WMRPT_CONT"));
				
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

		return wri_mes_report_list;
	}
}
