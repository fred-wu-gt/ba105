package com.act_com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.activity.model.ActivityVO;

public class Act_comJDBCDAO implements Act_comDAO_interface{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	//新增一筆活動留言
	private static final String INSERT_STMT = "INSERT INTO Activity_comment(aco_no, act_no, mem_no, aco_cnt, aco_date, aco_status)"
			+ "VALUES('ACO'||LPAD(to_char(act_no_seq.NEXTVAL), 7, '0'), ?, ?, ?, SYSTIMESTAMP, ?)";
	//單一FK能修改
	private static final String UPDATE_STMT = "UPDATE Activity_comment SET act_no=?, mem_no=?, aco_cnt=?, aco_date=SYSTIMESTAMP, aco_status=? WHERE aco_no = ?";
	//修改狀態
	private static final String UPDATE_ACO_STATUS = "UPDATE Activity_comment SET aco_status = ? WHERE act_no = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Activity_comment WHERE aco_no=?";
	private static final String GET_ALL = "SELECT * FROM Activity_comment";
	private static final String DELETE = "DELETE FROM Activity_comment WHERE aco_no = ?";
	//為了查單一活動的留言區塊 //1230加的
	private static final String FIND_BY_ACT_NO= "SELECT * FROM Activity_comment WHERE act_no= ?";
	
	@Override
	public void insert(Act_comVO act_comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "aco_no" }; 
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, act_comVO.getAct_no());
			pstmt.setString(2, act_comVO.getMem_no());
			pstmt.setString(3, act_comVO.getAco_cnt());
			pstmt.setString(4, act_comVO.getAco_status());
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
	public void update(Act_comVO act_comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, act_comVO.getAct_no());
			pstmt.setString(2, act_comVO.getMem_no());
			pstmt.setString(3, act_comVO.getAco_cnt());
			pstmt.setString(4, act_comVO.getAco_status());
			pstmt.setString(5, act_comVO.getAco_no());
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
	
	//修改狀態
	@Override
	public void updateAcoStatus(String aco_status, String aco_no){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_ACO_STATUS);
			pstmt.setString(1, aco_no);
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
	public Act_comVO findByPrimaryKey(String aco_no) {
		Act_comVO act_comVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, aco_no);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				act_comVO = new Act_comVO();
				act_comVO.setAco_no(rs.getString("aco_no"));
				act_comVO.setAct_no(rs.getString("act_no"));
				act_comVO.setMem_no(rs.getString("mem_no"));
				act_comVO.setAco_cnt(rs.getString("aco_cnt"));
				act_comVO.setAco_date(rs.getTimestamp("aco_date"));
				act_comVO.setAco_status(rs.getString("aco_status"));
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
		return act_comVO;
	}

	@Override
	public List<Act_comVO> getAll() {
		List<Act_comVO> actList = new ArrayList<>();
		Act_comVO act_comVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_comVO = new Act_comVO();
				act_comVO.setAco_no(rs.getString("aco_no"));
				act_comVO.setAct_no(rs.getString("act_no"));
				act_comVO.setMem_no(rs.getString("mem_no"));
				act_comVO.setAco_cnt(rs.getString("aco_cnt"));
				act_comVO.setAco_date(rs.getTimestamp("aco_date"));
				act_comVO.setAco_status(rs.getString("aco_status"));
				actList.add(act_comVO);
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
	
	//為了單一活動的留言區塊 //1230加的
	@Override
	public List<Act_comVO> findByActNo(String act_no) {
		List<Act_comVO> actList = new ArrayList<>();
		Act_comVO act_comVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACT_NO);
			pstmt.setString(1, act_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_comVO = new Act_comVO();
				act_comVO.setAco_no(rs.getString("aco_no"));
				act_comVO.setAct_no(rs.getString("act_no"));
				act_comVO.setMem_no(rs.getString("mem_no"));
				act_comVO.setAco_cnt(rs.getString("aco_cnt"));
				act_comVO.setAco_date(rs.getTimestamp("aco_date"));
				act_comVO.setAco_status(rs.getString("aco_status"));
				actList.add(act_comVO);
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
	public void delete(String aco_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, aco_no);
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
