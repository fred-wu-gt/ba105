package com.prom.model;

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

public class PromJNDIDAOImpl implements PromDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT = "INSERT INTO PROMOTION (PROM_NO,SHOP_NO,PROM_DIS,PROM_START,PROM_END)"
			+ "VALUES('PROM'||LPAD(TO_CHAR(PROM_NO_SEQUENCES.NEXTVAL),6,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT PROM_NO,SHOP_NO,PROM_DIS,PROM_START,PROM_END FROM PROMOTION ORDER BY PROM_NO";
	private static final String GET_ONE_STMT = "SELECT PROM_NO,SHOP_NO,PROM_DIS,PROM_START,PROM_END FROM PROMOTION WHERE SHOP_NO = ?";
	private static final String DELETE = "DELETE FROM PROMOTION WHERE PROM_NO = ?";
	private static final String UPDATE = "UPDATE PROMOTION SET SHOP_NO =?,PROM_DIS =?,PROM_START=?,PROM_END=? WHERE PROM_NO=?";

	@Override
	public void insert(PromVO promVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promVo.getShop_no());
			pstmt.setDouble(2, promVo.getProm_dis());
			pstmt.setTimestamp(3, promVo.getProm_start());
			pstmt.setTimestamp(4, promVo.getProm_end());

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

	}

	@Override
	public void update(PromVO promVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promVO.getShop_no());
			pstmt.setDouble(2, promVO.getProm_dis());
			pstmt.setTimestamp(3, promVO.getProm_start());
			pstmt.setTimestamp(4, promVO.getProm_end());
			pstmt.setString(5, promVO.getProm_no());

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
	}

	@Override
	public void delete(String prom_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prom_no);
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
	}

	@Override
	public PromVO findByPrimaryKey(String shop_no) {

		PromVO promVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, shop_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				promVo = new PromVO();

				promVo.setProm_no(rs.getString("PROM_NO"));
				promVo.setShop_no(rs.getString("SHOP_NO"));
				promVo.setProm_dis(rs.getDouble("PROM_DIS"));
				promVo.setProm_start(rs.getTimestamp("PROM_START"));
				promVo.setProm_end(rs.getTimestamp("PROM_END"));

			}

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

		return promVo;
	}

	@Override
	public List<PromVO> getAll() {

		List<PromVO> list = new ArrayList<PromVO>();
		PromVO promVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promVo = new PromVO();

				promVo.setProm_no(rs.getString("PROM_NO"));
				promVo.setShop_no(rs.getString("SHOP_NO"));
				promVo.setProm_dis(rs.getDouble("PROM_DIS"));
				promVo.setProm_start(rs.getTimestamp("PROM_START"));
				promVo.setProm_end(rs.getTimestamp("PROM_END"));

				list.add(promVo);
			}

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
		return list;
	}

}
