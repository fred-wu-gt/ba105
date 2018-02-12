package com.act_like.model;

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

import com.activity.model.ActivityVO;

public class Act_likeDAO implements Act_likeDAO_interface {
	private static DataSource ds = null;
	//使用JNDI技術尋找及存取DataSource
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//會員對活動按讚
	private static final String INSERT_STMT = "INSERT INTO Activity_like(act_no, mem_no) VALUES(?, ?)";
	private static final String FIND_BY_PK = "SELECT * FROM Activity_like WHERE act_no = ? AND mem_no=?";
	private static final String GET_ALL = "SELECT * FROM Activity_like";
	//查看已按讚活動
	private static final String GET_BY_MEM_NO = "SELECT * FROM activity_like where mem_no = ?";
	//查看單一活動按讚數
	private static final String GET_ONE_ACT_COUNT_LIKE = "SELECT COUNT(ACT_NO) FROM ACTIVITY_LIKE WHERE ACT_NO = ?";
	
	private static final String DELETE = "DELETE FROM Activity_like WHERE act_no = ? AND mem_no = ?";

	
	@Override
	public void insert(Act_likeVO act_likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, act_likeVO.getAct_no());
			pstmt.setString(2, act_likeVO.getMem_no());
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
	public Act_likeVO findByPrimaryKey(String act_no, String mem_no) {
		Act_likeVO act_likeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, act_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				act_likeVO = new Act_likeVO();
				act_likeVO.setAct_no(rs.getString("act_no"));
				act_likeVO.setMem_no(rs.getString("mem_no"));
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
		return act_likeVO;
	}
	
	//查看已按讚活動
	@Override
	public List<Act_likeVO> findByMemNo(String mem_no) {
		List<Act_likeVO> list = new ArrayList<Act_likeVO>();
		Act_likeVO act_likeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEM_NO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				act_likeVO = new Act_likeVO();
				act_likeVO.setAct_no(rs.getString("act_no"));
				act_likeVO.setMem_no(rs.getString("mem_no"));
				list.add(act_likeVO);
			}
			
			// Handle any DRIVER errors
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
	
	//查看單一活動按讚數
	@Override
	public int getOneActCountLike(String act_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;//按讚數
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACT_COUNT_LIKE);
			pstmt.setString(1, act_no);
			rs = pstmt.executeQuery();
			
			rs.next();
			count = rs.getInt("COUNT(ACT_NO)");

			// Handle any DRIVER errors
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
		return count;
	}
	
	@Override
	public List<Act_likeVO> getAll() {
		List<Act_likeVO> actList = new ArrayList<>();
		Act_likeVO act_likeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_likeVO = new Act_likeVO();
				act_likeVO.setAct_no(rs.getString("act_no"));
				act_likeVO.setMem_no(rs.getString("mem_no"));
				actList.add(act_likeVO);
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
	public void delete(String act_no, String mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, act_no);
			pstmt.setString(2, mem_no);
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
