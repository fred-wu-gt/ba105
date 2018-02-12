package com.act_join_det.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Act_join_detJDBCDAO implements Act_join_detDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	//新增時 若pk是複合主鍵 value不能用自增主鍵 所以要給? 且不能用fk的table不存在的值
	//新增時 若pk是自增主鍵 value不能用? 
	private static final String INSERT_STMT = "INSERT INTO activity_join_detail(act_no, mem_no, aj_time, aj_status) "
			+ "VALUES(?, ?, SYSTIMESTAMP, ?)";
	//複合PK不能修改
	//修改報名活動狀態
	private static final String UPDATE_AJ_STATUS = "UPDATE activity_join_detail SET aj_status = ? WHERE act_no = ? AND mem_no= ?";
	private static final String FIND_BY_PK = "SELECT * FROM activity_join_detail WHERE act_no=? AND mem_no= ?";
	//查詢報名名單0112+
	private static final String FIND_BY_ACT_NO = "SELECT * FROM activity_join_detail where act_no = ? ORDER BY aj_time desc";
	private static final String GET_ALL = "SELECT * FROM activity_join_detail";
	//查看已報名活動
	private static final String FIND_BY_MEM_NO = "SELECT * FROM activity_join_detail where mem_no = ?";
	private static final String DELETE = "DELETE FROM activity_join_detail WHERE act_no = ? AND mem_no= ?";
	
	@Override
	public void insert(Act_join_detVO act_join_detVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, act_join_detVO.getAct_no());
			pstmt.setString(2, act_join_detVO.getMem_no());
			pstmt.setString(3, act_join_detVO.getAj_status());
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
	public void updateAjStatus(String aj_status, String act_no, String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_AJ_STATUS);
			pstmt.setString(1, aj_status);
			pstmt.setString(2, act_no);
			pstmt.setString(3, mem_no);
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
	public Act_join_detVO findByPrimaryKey(String act_no, String mem_no) {
		Act_join_detVO act_join_detVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, act_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_join_detVO = new Act_join_detVO();
				act_join_detVO.setAct_no(rs.getString("act_no"));
				act_join_detVO.setMem_no(rs.getString("mem_no"));
				
				java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long sec = rs.getTimestamp("aj_time").getTime();
				String formatDate = df.format(new Date(sec));
				System.out.println("format timestamp : "+ java.sql.Timestamp.valueOf(formatDate));
				act_join_detVO.setAj_time(java.sql.Timestamp.valueOf(formatDate));
				
				act_join_detVO.setAj_status(rs.getString("aj_status"));
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
		return act_join_detVO;
	}
	
	//查詢報名名單0112+
    public List<Act_join_detVO> findByActNo(String act_no){
    	List<Act_join_detVO> list = new ArrayList<Act_join_detVO>();
		Act_join_detVO act_join_detVO = null;
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
				act_join_detVO = new Act_join_detVO();
				act_join_detVO.setAct_no(rs.getString("act_no"));
				act_join_detVO.setMem_no(rs.getString("mem_no"));
				act_join_detVO.setAj_time(rs.getTimestamp("aj_time"));
				act_join_detVO.setAj_status(rs.getString("aj_status"));
				list.add(act_join_detVO);
			}
			
			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
    }

	@Override
	public List<Act_join_detVO> getAll() {
		List<Act_join_detVO> actList = new ArrayList<>();
		Act_join_detVO act_join_detVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_join_detVO = new Act_join_detVO();
				act_join_detVO.setAct_no(rs.getString("act_no"));
				act_join_detVO.setMem_no(rs.getString("mem_no"));
				act_join_detVO.setAj_time(rs.getTimestamp("aj_time"));
				act_join_detVO.setAj_status(rs.getString("aj_status"));
				actList.add(act_join_detVO);
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

	//查看已報名活動
	@Override
	public List<Act_join_detVO> findByMemNo(String mem_no) {
		List<Act_join_detVO> list = new ArrayList<Act_join_detVO>();
		Act_join_detVO act_join_detVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				act_join_detVO = new Act_join_detVO();
				act_join_detVO.setAct_no(rs.getString("act_no"));
				act_join_detVO.setMem_no(rs.getString("mem_no"));
				act_join_detVO.setAj_time(rs.getTimestamp("aj_time"));
				act_join_detVO.setAj_status(rs.getString("aj_status"));
				list.add(act_join_detVO);
			}
			
			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}
	
	@Override
	public void delete(String act_no, String mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, act_no);
			pstmt.setString(2, mem_no);
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
