package com.wri_like.model;

import java.sql.*;
import java.util.*;

public class Wri_likeJDBCDAO implements Wri_likeDAO_interface {
	private static final String MY_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_INTO = "INSERT INTO WRITING_LIKE (WRT_NO,MEM_NO) VALUES(?,?)";
	private static final String DELETE = "DELETE FROM WRITING_LIKE WHERE WRT_NO=? AND  MEM_NO=?";
	private static final String FIND_BY_WRT_NO = "SELECT * FROM WRITING_LIKE WHERE WRT_NO=? ORDER BY MEM_NO";
	private static final String FIND_BY_MEM_NO = "SELECT * FROM WRITING_LIKE WHERE MEM_NO=? ORDER BY WRT_NO";
	private static final String GET_ALL = "SELECT * FROM WRITING_LIKE ORDER BY WRT_NO, MEM_NO";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(Wri_likeVO wri_likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(MY_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, wri_likeVO.getWrt_no());
			pstmt.setString(2, wri_likeVO.getMem_no());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(String wrt_no, String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(MY_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, wrt_no);
			pstmt.setString(2, mem_no);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

	@Override
	public List<Wri_likeVO> findByWrt_no(String wrt_no) {

		List<Wri_likeVO> Wri_likeVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(MY_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_WRT_NO);
			pstmt.setString(1, wrt_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Wri_likeVO wri_likeVO = new Wri_likeVO();
				wri_likeVO.setWrt_no(rs.getString("wrt_no"));
				wri_likeVO.setMem_no(rs.getString("mem_no"));
				Wri_likeVOList.add(wri_likeVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return Wri_likeVOList;
	}

	@Override
	public List<Wri_likeVO> findByMem_no(String mem_no) {

		List<Wri_likeVO> Wri_likeVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(MY_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEM_NO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Wri_likeVO wri_likeVO = new Wri_likeVO();
				wri_likeVO.setWrt_no(rs.getString("wrt_no"));
				wri_likeVO.setMem_no(rs.getString("mem_no"));
				Wri_likeVOList.add(wri_likeVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return Wri_likeVOList;
	}

	@Override
	public List<Wri_likeVO> getAll() {

		List<Wri_likeVO> Wri_likeVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(MY_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Wri_likeVO wri_likeVO = new Wri_likeVO();
				wri_likeVO.setWrt_no(rs.getString("wrt_no"));
				wri_likeVO.setMem_no(rs.getString("mem_no"));
				Wri_likeVOList.add(wri_likeVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return Wri_likeVOList;
	}

}
