package com.act_cat.model;

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

import com.activity.model.ActivityDAO_interface;
import com.activity.model.ActivityVO;

public class Act_catDAO implements Act_catDAO_interface {
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
	
	//複合PK不能修改
	private static final String INSERT_STMT = "INSERT INTO Activity_category(act_no, fru_no) VALUES(?, ?)";
	private static final String FIND_BY_PK = "SELECT * FROM Activity_category WHERE act_no = ? AND fru_no = ?";
	private static final String GET_ALL = "SELECT * FROM Activity_category";
	//用活動編號找水果編號
	private static final String FIND_BY_ACT_NO = "SELECT * FROM Activity_category WHERE act_no = ?";
	private static final String DELETE = "DELETE FROM Activity_category WHERE act_no = ? AND fru_no = ?";
	
	@Override
	public void insert(Act_catVO act_catVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, act_catVO.getAct_no());
			pstmt.setString(2, act_catVO.getFru_no());
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
	public void insert2 (Act_catVO act_catVO , Connection con) {

		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, act_catVO.getAct_no());
			pstmt.setString(2, act_catVO.getFru_no());
			pstmt.executeUpdate();

			// Handle any DRIVER errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}

	@Override
	public Act_catVO findByPrimaryKey(String act_no, String fru_no) {
		Act_catVO act_catVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, act_no);
			pstmt.setString(2, fru_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_catVO = new Act_catVO();
				act_catVO.setAct_no(rs.getString("act_no"));
				act_catVO.setFru_no(rs.getString("fru_no"));
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
		return act_catVO;
	}

	@Override
	public Act_catVO findByActNo(String act_no){
		
		Act_catVO act_catVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_ACT_NO);
			pstmt.setString(1, act_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_catVO = new Act_catVO();
				act_catVO.setAct_no(rs.getString("act_no"));
				act_catVO.setFru_no(rs.getString("fru_no"));
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
		return act_catVO;
	}
	
	@Override
	public List<Act_catVO> getAll() {
		List<Act_catVO> actList = new ArrayList<>();
		Act_catVO act_catVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_catVO = new Act_catVO();
				act_catVO.setAct_no(rs.getString("act_no"));
				act_catVO.setFru_no(rs.getString("fru_no"));
				actList.add(act_catVO);
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
	public void delete(String act_no, String fru_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, act_no);
			pstmt.setString(2, fru_no);
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
