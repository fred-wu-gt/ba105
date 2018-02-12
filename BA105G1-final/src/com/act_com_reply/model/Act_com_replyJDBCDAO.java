package com.act_com_reply.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.act_com.model.Act_comVO;
import com.act_com_report.model.Act_com_reportVO;


public class Act_com_replyJDBCDAO implements Act_com_replyDAO_interface{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	//新增一筆活動留言回覆(商家)
	private static final String INSERT_STMT = "INSERT INTO activity_comment_reply(acr_no, aco_no, shop_no, acr_cnt, acr_time)"
			+ "VALUES('ACR'||LPAD(to_char(act_no_seq.NEXTVAL), 7, '0'), ?, ?, ?, SYSTIMESTAMP)";
	//單一FK能修改
	private static final String UPDATE_STMT = "UPDATE activity_comment_reply SET aco_no=?, shop_no=?, acr_cnt=?, acr_time=SYSTIMESTAMP WHERE acr_no = ?";
	private static final String FIND_BY_PK = "SELECT * FROM activity_comment_reply WHERE acr_no=?";
	//撈出某筆活動留言的所有留言回覆0108+
	private static final String FIND_BY_ACO_NO = "SELECT * FROM activity_comment_reply WHERE aco_no=?";
	//為了看商家是否檢舉過 //0108+
	private static final String FIND_BY_FK = "SELECT * FROM activity_comment_reply WHERE aco_no = ? AND shop_no = ?";
	private static final String GET_ALL = "SELECT * FROM activity_comment_reply";
	private static final String DELETE = "DELETE FROM activity_comment_reply WHERE acr_no = ?";

	@Override
	public void insert(Act_com_replyVO act_com_replyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "acr_no" }; 
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, act_com_replyVO.getAco_no());
			pstmt.setString(2, act_com_replyVO.getShop_no());
			pstmt.setString(3, act_com_replyVO.getAcr_cnt());
			pstmt.executeUpdate();

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
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

	}

	@Override
	public void update(Act_com_replyVO act_com_replyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, act_com_replyVO.getAco_no());
			pstmt.setString(2, act_com_replyVO.getShop_no());
			pstmt.setString(3, act_com_replyVO.getAcr_cnt());
			pstmt.setString(4, act_com_replyVO.getAcr_no());
			pstmt.executeUpdate();

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
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

	}
	
	//為了看商家是否檢舉過 //0108+
	@Override
	public Act_com_replyVO findByFK(String aco_no, String shop_no){
		Act_com_replyVO act_com_replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_FK);
			pstmt.setString(1, aco_no);
			pstmt.setString(2, shop_no);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				act_com_replyVO = new Act_com_replyVO();
				act_com_replyVO.setAcr_no(rs.getString("acr_no"));
				act_com_replyVO.setAco_no(rs.getString("aco_no"));
				act_com_replyVO.setShop_no(rs.getString("shop_no"));
				act_com_replyVO.setAcr_cnt(rs.getString("acr_cnt"));
				act_com_replyVO.setAcr_time(rs.getTimestamp("acr_time"));
			}

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
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
		return act_com_replyVO;
	}

	
	@Override
	public Act_com_replyVO findByPrimaryKey(String acr_no) {
		Act_com_replyVO act_com_replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, acr_no);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				act_com_replyVO = new Act_com_replyVO();
				act_com_replyVO.setAcr_no(rs.getString("acr_no"));
				act_com_replyVO.setAco_no(rs.getString("aco_no"));
				act_com_replyVO.setShop_no(rs.getString("shop_no"));
				act_com_replyVO.setAcr_cnt(rs.getString("acr_cnt"));
				act_com_replyVO.setAcr_time(rs.getTimestamp("acr_time"));
			}

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
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
		return act_com_replyVO;
	}

	//撈出某筆活動留言的所有 留言回覆0108+
	@Override
	public List<Act_com_replyVO> findByAcoNo(String aco_no){
		List<Act_com_replyVO> actList = new ArrayList<>();
		Act_com_replyVO act_com_replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACO_NO);
			pstmt.setString(1, aco_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				act_com_replyVO = new Act_com_replyVO();
				act_com_replyVO.setAcr_no(rs.getString("acr_no"));
				act_com_replyVO.setAco_no(rs.getString("aco_no"));
				act_com_replyVO.setShop_no(rs.getString("shop_no"));
				act_com_replyVO.setAcr_cnt(rs.getString("acr_cnt"));
				act_com_replyVO.setAcr_time(rs.getTimestamp("acr_time"));
				actList.add(act_com_replyVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
			// Handle any SQL errors
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
		return actList;
	}
	
	@Override
	public List<Act_com_replyVO> getAll() {
		List<Act_com_replyVO> actList = new ArrayList<>();
		Act_com_replyVO act_com_replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				act_com_replyVO = new Act_com_replyVO();
				act_com_replyVO.setAcr_no(rs.getString("acr_no"));
				act_com_replyVO.setAco_no(rs.getString("aco_no"));
				act_com_replyVO.setShop_no(rs.getString("shop_no"));
				act_com_replyVO.setAcr_cnt(rs.getString("acr_cnt"));
				act_com_replyVO.setAcr_time(rs.getTimestamp("acr_time"));
				actList.add(act_com_replyVO);
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
		return actList;
	}

	@Override
	public void delete(String acr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, acr_no);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
