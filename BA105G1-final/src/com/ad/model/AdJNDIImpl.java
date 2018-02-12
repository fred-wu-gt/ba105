package com.ad.model;

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

public class AdJNDIImpl implements AdDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ADVERTISEMENT (AD_NO,SHOP_NO,AD_IDESTA,AD_END,AD_STAT,AD_PHOTO)"
			+ "VALUES('AD'||LPAD(TO_CHAR(AD_NO_SEQUENCES.NEXTVAL),8,'0'),?,?,?,'待審核',?)";
	private static final String GET_ALL_STMT = "SELECT AD_NO,SHOP_NO,AD_IDESTA,AD_START,AD_END,AD_EXP,AD_STAT,AD_PHOTO FROM ADVERTISEMENT ORDER BY AD_NO";
	private static final String GET_ONE_STMT = "SELECT AD_NO,SHOP_NO,AD_IDESTA,AD_START,AD_END,AD_EXP,AD_STAT,AD_PHOTO FROM ADVERTISEMENT WHERE AD_NO = ?";
	private static final String DELETE = "DELETE FROM ADVERTISEMENT WHERE AD_NO = ?";
	private static final String UPDATE = "UPDATE ADVERTISEMENT SET SHOP_NO=?,AD_IDESTA=?,AD_START=SYSTIMESTAMP,AD_END=?,AD_EXP=?,AD_STAT='上架',AD_PHOTO =? WHERE AD_NO =?";

	@Override
	public void insert(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adVO.getShop_no());
			pstmt.setTimestamp(2, adVO.getAd_idesta());
			pstmt.setTimestamp(3, adVO.getAd_end());
			pstmt.setBytes(4, adVO.getAd_photo());

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
	public void update(AdVO adVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adVO.getShop_no());
			pstmt.setTimestamp(2, adVO.getAd_idesta());
			pstmt.setTimestamp(3, adVO.getAd_end());
			pstmt.setInt(4, adVO.getAd_exp());
			pstmt.setBytes(5, adVO.getAd_photo());
			pstmt.setString(6, adVO.getAd_no());

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
	public void delete(String ad_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, ad_no);
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
	public AdVO findByPrimaryKey(String ad_no) {

		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ad_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				adVO = new AdVO();
				adVO.setAd_no(rs.getString("AD_NO"));
				adVO.setShop_no(rs.getString("SHOP_NO"));
				adVO.setAd_idesta(rs.getTimestamp("AD_IDESTA"));
				adVO.setAd_start(rs.getTimestamp("AD_START"));
				adVO.setAd_end(rs.getTimestamp("AD_END"));
				adVO.setAd_exp(rs.getInt("AD_EXP"));
				adVO.setAd_stat(rs.getString("AD_STAT"));
				adVO.setAd_photo(rs.getBytes("AD_PHOTO"));

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
		return adVO;
	}

	@Override
	public List<AdVO> getAll() {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				adVO = new AdVO();
				adVO.setAd_no(rs.getString("AD_NO"));
				adVO.setShop_no(rs.getString("SHOP_NO"));
				adVO.setAd_idesta(rs.getTimestamp("AD_IDESTA"));
				adVO.setAd_start(rs.getTimestamp("AD_START"));
				adVO.setAd_end(rs.getTimestamp("AD_END"));
				adVO.setAd_exp(rs.getInt("AD_EXP"));
				adVO.setAd_stat(rs.getString("AD_STAT"));
				adVO.setAd_photo(rs.getBytes("AD_PHOTO"));

				list.add(adVO);
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
	
	
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public List<AdVO> findByAd_stat(String ad_stat) {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM ADVERTISEMENT WHERE AD_STAT =? ORDER BY AD_NO desc");
			pstmt.setString(1, ad_stat);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				adVO = new AdVO();
				adVO.setAd_no(rs.getString("AD_NO"));
				adVO.setShop_no(rs.getString("SHOP_NO"));
				adVO.setAd_idesta(rs.getTimestamp("AD_IDESTA"));
				adVO.setAd_start(rs.getTimestamp("AD_START"));
				adVO.setAd_end(rs.getTimestamp("AD_END"));
				adVO.setAd_exp(rs.getInt("AD_EXP"));
				adVO.setAd_stat(rs.getString("AD_STAT"));
				adVO.setAd_photo(rs.getBytes("AD_PHOTO"));

				list.add(adVO);
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
	
	@Override
	public void updateAd_stat(AdVO adVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("UPDATE ADVERTISEMENT SET AD_START=?,AD_EXP=?,AD_STAT=? WHERE AD_NO =?");

			pstmt.setTimestamp(1, adVO.getAd_start());
			pstmt.setInt(2, adVO.getAd_exp());
			pstmt.setString(3, adVO.getAd_stat());
			pstmt.setString(4, adVO.getAd_no());

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

	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
}
