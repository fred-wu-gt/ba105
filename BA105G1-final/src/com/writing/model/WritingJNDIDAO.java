package com.writing.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class WritingJNDIDAO implements WritingDAO_interface {
	/* 一個應用程式中，針對一個資料庫，只需要共用一個DataSource即可 */

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO WRITING(wrt_no,shop_no,wrt_title,wrt_cont,wrt_photo,wrt_photo_base64,wrt_sta,wrt_time)"
			+ "VALUES(('WRT'||LPAD(TO_CHAR(WRT_NO_SEQ.NEXTVAL),7,'0')),?,?,?,?,?,?,SYSTIMESTAMP)";

	private static final String UPDATE_STMT = "UPDATE WRITING SET SHOP_NO = ?, WRT_TITLE = ?, WRT_CONT = ?, WRT_PHOTO = ?, WRT_PHOTO_BASE64 = ?, WRT_STA = ?, wrt_time = SYSTIMESTAMP "
			+ "WHERE WRT_NO = ?";

	private static final String DELETE = "DELETE FROM WRITING WHERE wrt_no = ?";

	private static final String GET_ONE_STMT = "SELECT * FROM WRITING WHERE wrt_no = ?";

	private static final String GET_ALL = "SELECT * FROM WRITING  ORDER BY wrt_no DESC";
	
	//世霖開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		private static final String FIND_BY_SHOP = "SELECT *  FROM WRITING WHERE shop_no = ?";
		//世霖結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Integer insert(WritingVO writingVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, writingVO.getShop_no());
			pstmt.setString(2, writingVO.getWrt_title());
			pstmt.setString(3, writingVO.getWrt_cont());
			pstmt.setBytes(4, writingVO.getWrt_photo());
			pstmt.setString(5, writingVO.getWrt_photo_base64());
			pstmt.setString(6, writingVO.getWrt_sta());

			pstmt.executeUpdate();

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
		return updateCount;
	}

	public Integer update(WritingVO writingVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, writingVO.getShop_no());
			pstmt.setString(2, writingVO.getWrt_title());
			pstmt.setString(3, writingVO.getWrt_cont());
			pstmt.setBytes(4, writingVO.getWrt_photo());
			pstmt.setString(5, writingVO.getWrt_photo_base64());
			pstmt.setString(6, writingVO.getWrt_sta());
			pstmt.setString(7, writingVO.getWrt_no());

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
		return updateCount;

	};

	public Integer delete(String wrt_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, wrt_no);
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
		return updateCount;
	};

	public WritingVO findByPrimaryKey(String wrt_no) {
		WritingVO writingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, wrt_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				writingVO = new WritingVO();
				writingVO.setWrt_no(rs.getString("wrt_no"));
				writingVO.setShop_no(rs.getString("shop_no"));
				writingVO.setWrt_title(rs.getString("wrt_title"));
				writingVO.setWrt_cont(rs.getString("wrt_cont"));
				writingVO.setWrt_photo(rs.getBytes("wrt_photo"));
				writingVO.setWrt_photo_base64(rs.getString("wrt_photo_base64"));
				writingVO.setWrt_sta(rs.getString("wrt_sta"));
				writingVO.setWrt_time(rs.getTimestamp("wrt_time"));
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
		return writingVO;

	}

	@Override
	public List<WritingVO> getAll() {
		List<WritingVO> wriList = new ArrayList<>();
		WritingVO wri = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri = new WritingVO();
				wri.setWrt_no(rs.getString("WRT_NO"));
				wri.setShop_no(rs.getString("SHOP_NO"));
				wri.setWrt_title(rs.getString("WRT_TITLE"));
				wri.setWrt_photo(rs.getBytes("WRT_PHOTO"));
				wri.setWrt_photo_base64(rs.getString("WRT_PHOTO_BASE64"));
				wri.setWrt_sta(rs.getString("WRT_STA"));
				wri.setWrt_cont(rs.getString("WRT_CONT"));
				wri.setWrt_time(rs.getTimestamp("WRT_TIME"));
				wriList.add(wri);
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

		return wriList;
	}

	@Override
	public List<WritingVO> getAll(Map<String, String[]> map) {
		List<WritingVO> list = new ArrayList<WritingVO>();
		WritingVO writingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "select * from writing " + jdbcUtil_CompositeQuery_Writing.get_WhereCondition(map)
					+ " order by wrt_no DESC";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				writingVO = new WritingVO();
				writingVO.setWrt_no(rs.getString("wrt_no"));
				writingVO.setShop_no(rs.getString("shop_no"));
				writingVO.setWrt_title(rs.getString("wrt_title"));
				writingVO.setWrt_cont(rs.getString("wrt_cont"));
				writingVO.setWrt_photo(rs.getBytes("WRT_PHOTO"));
				writingVO.setWrt_photo_base64(rs.getString("WRT_PHOTO_BASE64"));
				writingVO.setWrt_sta(rs.getString("wrt_sta"));
				writingVO.setWrt_time(rs.getTimestamp("WRT_TIME"));
				list.add(writingVO); // Store the row in the List

			}

			// Handle any SQL errors
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
		public void updateWrt_sta(String wrt_sta, String wrt_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement("UPDATE WRITING SET WRT_STA = ? WHERE WRT_NO = ?");

				pstmt.setString(1, wrt_sta);
				pstmt.setString(2, wrt_no);

				pstmt.executeUpdate();
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
		//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		//世霖開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		@Override
		public WritingVO findByshopno(String shop_no) {
			WritingVO writingVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(FIND_BY_SHOP);
				pstmt.setString(1, shop_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					writingVO = new WritingVO();
					writingVO.setWrt_no(rs.getString("wrt_no"));
					writingVO.setShop_no(rs.getString("shop_no"));
					writingVO.setWrt_title(rs.getString("wrt_title"));
					writingVO.setWrt_cont(rs.getString("wrt_cont"));
					writingVO.setWrt_photo(rs.getBytes("wrt_photo"));
					writingVO.setWrt_photo_base64(rs.getString("wrt_photo_base64"));
					writingVO.setWrt_sta(rs.getString("wrt_sta"));
					writingVO.setWrt_time(rs.getTimestamp("wrt_time"));
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
			return writingVO;
			
		}

		//世霖結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
}
