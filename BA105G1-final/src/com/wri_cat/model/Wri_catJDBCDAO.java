package com.wri_cat.model;

import java.sql.*;
import java.util.*;

import com.wri_like.model.Wri_likeVO;

public class Wri_catJDBCDAO implements Wri_catDAO_interface {
	private static final String MY_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_INTO = "INSERT INTO WRITING_CATEGORY (WRT_NO,FRU_NO) VALUES(?,?)";
	private static final String DELETE = "DELETE FROM WRITING_CATEGORY WHERE WRT_NO=? AND  FRU_NO=?";
	private static final String FIND_BY_WRT_NO = "SELECT * FROM WRITING_CATEGORY WHERE WRT_NO=? ORDER BY FRU_NO";
	private static final String FIND_BY_FRU_NO = "SELECT * FROM WRITING_CATEGORY WHERE FRU_NO=? ORDER BY WRT_NO";
	private static final String GET_ALL = "SELECT * FROM WRITING_CATEGORY ORDER BY WRT_NO, FRU_NO";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(Wri_catVO wri_catVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(MY_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, wri_catVO.getWrt_no());
			pstmt.setString(2, wri_catVO.getFru_no());

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
	public void delete(String wrt_no, String fru_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(MY_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, wrt_no);
			pstmt.setString(2, fru_no);
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
	public List<Wri_catVO> findByWrt_no(String wrt_no) {
		List<Wri_catVO> Wri_catVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(MY_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_WRT_NO);
			pstmt.setString(1, wrt_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Wri_catVO wri_catVO = new Wri_catVO();
				wri_catVO.setWrt_no(rs.getString("wrt_no"));
				wri_catVO.setFru_no(rs.getString("fru_no"));
				Wri_catVOList.add(wri_catVO);
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
		return Wri_catVOList;

	}

	@Override
	public List<Wri_catVO> findByFru_no(String fru_no) {

		List<Wri_catVO> Wri_catVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(MY_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_FRU_NO);
			pstmt.setString(1, fru_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Wri_catVO wri_catVO = new Wri_catVO();
				wri_catVO.setFru_no(rs.getString("fru_no"));
				wri_catVO.setWrt_no(rs.getString("wrt_no"));
				Wri_catVOList.add(wri_catVO);
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
		return Wri_catVOList;

	}

	@Override
	public List<Wri_catVO> getAll() {

		List<Wri_catVO> Wri_catVOList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(MY_URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Wri_catVO wri_catVO = new Wri_catVO();
				wri_catVO.setWrt_no(rs.getString("wrt_no"));
				wri_catVO.setFru_no(rs.getString("fru_no"));
				Wri_catVOList.add(wri_catVO);
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
		return Wri_catVOList;

	}
}
