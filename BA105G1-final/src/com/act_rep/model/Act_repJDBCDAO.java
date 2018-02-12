package com.act_rep.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Act_repJDBCDAO implements Act_repDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	//新增一筆活動檢舉
	private static final String INSERT_STMT = "INSERT INTO activity_report(ar_no, act_no, mem_no, ar_rsn, act_status, ar_cnt)"
			+ "VALUES('AR'||LPAD(to_char(act_no_seq.NEXTVAL), 8, '0'), ?, ?, ?, ?, ?)";
	//單一FK能修改
	private static final String UPDATE_STMT = "UPDATE activity_report SET act_no=? , mem_no=?, ar_rsn = ?, act_status = ?, "
			+ "ar_cnt = ? WHERE ar_no = ?";
	//修改活動檢舉狀態
	private static final String UPDATE_ACT_STATUS = "UPDATE activity_report SET act_status = ? WHERE ar_no = ?";
	private static final String FIND_BY_FK = "SELECT * FROM activity_report WHERE act_no = ? AND mem_no = ?";
	private static final String FIND_BY_PK = "SELECT * FROM activity_report WHERE ar_no = ?";
	private static final String GET_ALL = "SELECT * FROM activity_report";
	private static final String DELETE = "DELETE FROM activity_report WHERE ar_no = ?";


	@Override
	public void insert(Act_repVO act_repVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "ar_no" }; 
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, act_repVO.getAct_no());
			pstmt.setString(2, act_repVO.getMem_no());
			pstmt.setString(3, act_repVO.getAr_rsn());
			pstmt.setString(4, act_repVO.getAct_status());
			pstmt.setString(5, act_repVO.getAr_cnt());
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
	public void update(Act_repVO act_repVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, act_repVO.getAct_no());
			pstmt.setString(2, act_repVO.getMem_no());
			pstmt.setString(3, act_repVO.getAr_rsn());
			pstmt.setString(4, act_repVO.getAct_status());
			pstmt.setString(5, act_repVO.getAr_cnt());
			pstmt.setString(6, act_repVO.getAr_no());
			
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
	
	//修改活動檢舉狀態
	@Override
  	public void updateActStatus(String act_status, String ar_no){
  		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_ACT_STATUS);
			pstmt.setString(1, act_status);
			pstmt.setString(2, ar_no);
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
	public Act_repVO findByFK(String act_no, String mem_no) {
		Act_repVO act_repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_FK);
			pstmt.setString(1, act_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				act_repVO = new Act_repVO();
				act_repVO.setAr_no("ar_no");
				act_repVO.setAct_no(rs.getString("act_no"));
				act_repVO.setMem_no(rs.getString("mem_no"));
				act_repVO.setAr_rsn(rs.getString("ar_rsn"));
				act_repVO.setAct_status(rs.getString("act_status"));
				act_repVO.setAr_cnt(rs.getString("ar_cnt") );
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
		return act_repVO;
	}

	@Override
	public Act_repVO findByPrimaryKey(String ar_no) {
		Act_repVO act_repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, ar_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				act_repVO = new Act_repVO();
				act_repVO.setAr_no("ar_no");
				act_repVO.setAct_no(rs.getString("act_no"));
				act_repVO.setMem_no(rs.getString("mem_no"));
				act_repVO.setAr_rsn(rs.getString("ar_rsn"));
				act_repVO.setAct_status(rs.getString("act_status"));
				act_repVO.setAr_cnt(rs.getString("ar_cnt") );
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
		return act_repVO;
	}

	@Override
	public List<Act_repVO> getAll() {
		List<Act_repVO> actList = new ArrayList<>();
		Act_repVO act_repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				act_repVO = new Act_repVO();
				act_repVO.setAr_no(rs.getString("ar_no"));
				act_repVO.setAct_no(rs.getString("act_no"));
				act_repVO.setMem_no(rs.getString("mem_no"));
				act_repVO.setAr_rsn(rs.getString("ar_rsn"));
				act_repVO.setAct_status(rs.getString("act_status"));
				act_repVO.setAr_cnt(rs.getString("ar_cnt"));
				actList.add(act_repVO);
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
	public void delete(String ar_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ar_no);
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
	//子怡開始	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		@Override
		public List<Act_repVO> findByAct_status(String act_status) {
			List<Act_repVO> actList = new ArrayList<>();
			Act_repVO act_repVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement("SELECT * FROM activity_report WHERE act_status = ? order by ar_no");
				pstmt.setString(1, act_status);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					act_repVO = new Act_repVO();
					act_repVO.setAr_no(rs.getString("ar_no"));
					act_repVO.setAct_no(rs.getString("act_no"));
					act_repVO.setMem_no(rs.getString("mem_no"));
					act_repVO.setAr_rsn(rs.getString("ar_rsn"));
					act_repVO.setAct_status(rs.getString("act_status"));
					act_repVO.setAr_cnt(rs.getString("ar_cnt"));
					actList.add(act_repVO);
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			}catch (SQLException se) {
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
	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
