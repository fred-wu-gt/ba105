package com.act_com_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Act_com_reportDAO implements Act_com_reportDAO_interface{
	private static DataSource ds = null;
	// 使用JNDI技術尋找及存取DataSource
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//新增一筆活動留言檢舉
	private static final String INSERT_STMT = "INSERT INTO activity_comment_report(acr_no, aco_no, mem_no, acr_rsn, acr_status, acr_cnt)"
			+ "VALUES('ACR'||LPAD(to_char(act_no_seq.NEXTVAL), 7, '0'), ?, ?, ?, ?, ?)";
	//單一FK能修改
	private static final String UPDATE_STMT = "UPDATE activity_comment_report SET aco_no=?, mem_no=?, acr_rsn = ?, acr_status = ?, "
			+ "acr_cnt = ? WHERE acr_no = ?";
	//修改活動留言檢舉狀態
	private static final String UPDATE_ACR_STATUS = "UPDATE activity_comment_report SET acr_status = ? WHERE acr_no = ?";
	//為了看會員是否檢舉過 //1230+
	private static final String FIND_BY_FK = "SELECT * FROM activity_comment_report WHERE aco_no = ? AND mem_no = ?";
	private static final String FIND_BY_PK = "SELECT * FROM activity_comment_report WHERE acr_no = ?";
	private static final String GET_ALL = "SELECT * FROM activity_comment_report";
	private static final String DELETE = "DELETE FROM activity_comment_report WHERE acr_no = ?";
	
	@Override
	public void insert(Act_com_reportVO act_com_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String[] cols = { "acr_no" }; 
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, act_com_reportVO.getAco_no());
			pstmt.setString(2, act_com_reportVO.getMem_no());
			pstmt.setString(3, act_com_reportVO.getAcr_rsn());
			pstmt.setString(4, act_com_reportVO.getAcr_status());
			pstmt.setString(5, act_com_reportVO.getAcr_cnt());
			pstmt.executeUpdate();

			// Handle any DRIVER errors
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
	public void update(Act_com_reportVO act_com_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, act_com_reportVO.getAco_no());
			pstmt.setString(2, act_com_reportVO.getMem_no());
			pstmt.setString(3, act_com_reportVO.getAcr_rsn());
			pstmt.setString(4, act_com_reportVO.getAcr_status());
			pstmt.setString(5, act_com_reportVO.getAcr_cnt());
			pstmt.setString(6, act_com_reportVO.getAcr_no());
			pstmt.executeUpdate();

			// Handle any DRIVER errors
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
	
	//修改活動留言檢舉狀態
	@Override
	public void updateAcrStatus(String acr_status, String acr_no) {
  		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ACR_STATUS);
			pstmt.setString(1, acr_status);
			pstmt.setString(2, acr_no);
			pstmt.executeUpdate();

			// Handle any DRIVER errors
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
	
	//為了看會員是否檢舉過 //1230+
	@Override
	public Act_com_reportVO findByFK(String aco_no, String mem_no) {
		Act_com_reportVO act_com_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_FK);
			pstmt.setString(1, aco_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				act_com_reportVO = new Act_com_reportVO();
				act_com_reportVO.setAcr_no(rs.getString("acr_no"));
				act_com_reportVO.setAco_no(rs.getString("aco_no"));
				act_com_reportVO.setMem_no(rs.getString("mem_no"));
				act_com_reportVO.setAcr_rsn(rs.getString("acr_rsn"));
				act_com_reportVO.setAcr_status(rs.getString("acr_status"));
				act_com_reportVO.setAcr_cnt(rs.getString("acr_cnt"));
			}

			// Handle any DRIVER errors
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
		return act_com_reportVO;
	}
	
	@Override
	public Act_com_reportVO findByPrimaryKey(String acr_no) {
		Act_com_reportVO act_com_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, acr_no);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				act_com_reportVO = new Act_com_reportVO();
				act_com_reportVO.setAcr_no(rs.getString("acr_no"));
				act_com_reportVO.setAco_no(rs.getString("aco_no"));
				act_com_reportVO.setMem_no(rs.getString("mem_no"));
				act_com_reportVO.setAcr_rsn(rs.getString("acr_rsn"));
				act_com_reportVO.setAcr_status(rs.getString("acr_status"));
				act_com_reportVO.setAcr_cnt(rs.getString("acr_cnt"));
			}

			// Handle any DRIVER errors
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
		return act_com_reportVO;
	}

	@Override
	public List<Act_com_reportVO> getAll() {
		List<Act_com_reportVO> actList = new ArrayList<>();
		Act_com_reportVO act_com_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				act_com_reportVO = new Act_com_reportVO();
				act_com_reportVO.setAcr_no(rs.getString("acr_no"));
				act_com_reportVO.setAco_no(rs.getString("aco_no"));
				act_com_reportVO.setMem_no(rs.getString("mem_no"));
				act_com_reportVO.setAcr_rsn(rs.getString("acr_rsn"));
				act_com_reportVO.setAcr_status(rs.getString("acr_status"));
				act_com_reportVO.setAcr_cnt(rs.getString("acr_cnt"));
				actList.add(act_com_reportVO);
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
		return actList;
	}

	@Override
	public void delete(String acr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, acr_no);
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

}

