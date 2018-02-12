package com.writing.model;

import java.sql.*;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;

public class WritingJDBCDAO implements WritingDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FRUIT";
	String password = "FRUIT";

	private static final String INSERT_STMT = "INSERT INTO WRITING(wrt_no,shop_no,wrt_title,wrt_cont,wrt_photo,wrt_photo_base64,wrt_sta,wrt_time)"
			+ "VALUES(('WRT'||LPAD(TO_CHAR(WRT_NO_SEQ.NEXTVAL),7,'0')),?,?,?,?,?,?,SYSTIMESTAMP)";

	private static final String UPDATE_STMT = "UPDATE WRITING SET SHOP_NO = ?, WRT_TITLE = ?, WRT_CONT = ?, WRT_PHOTO = ?, WRT_PHOTO_BASE64 = ?, WRT_STA = ?, wrt_time = SYSTIMESTAMP "
			+ "WHERE WRT_NO = ?";

	private static final String DELETE = "DELETE FROM WRITING WHERE wrt_no = ?";

	private static final String GET_ONE_STMT = "SELECT * FROM WRITING WHERE wrt_no = ?";

	private static final String GET_ALL = "SELECT * FROM WRITING  ";
	
	//世霖開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		private static final String FIND_BY_SHOP = "SELECT *  FROM WRITING WHERE shop_no = ?";
		//世霖結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		

	public Integer insert(WritingVO writingVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, writingVO.getShop_no());
			pstmt.setString(2, writingVO.getWrt_title());
			pstmt.setString(3, writingVO.getWrt_cont());
			pstmt.setBytes(4, writingVO.getWrt_photo());
			pstmt.setString(5, writingVO.getWrt_photo_base64());
			pstmt.setString(6, writingVO.getWrt_sta());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return updateCount;
	};

	public Integer update(WritingVO writingVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, writingVO.getShop_no());
			pstmt.setString(2, writingVO.getWrt_title());
			pstmt.setString(3, writingVO.getWrt_cont());
			pstmt.setBytes(4, writingVO.getWrt_photo());
			pstmt.setString(5, writingVO.getWrt_photo_base64());
			pstmt.setString(6, writingVO.getWrt_sta());
			pstmt.setString(7, writingVO.getWrt_no());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
				} catch (Exception e) {
					e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, wrt_no);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		return wriList;
	}

	public List<WritingVO> getAll(Map<String, String[]> map) {
		List<WritingVO> list = new ArrayList<WritingVO>();
		WritingVO writingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			String finalSQL = "select * from writing " + jdbcUtil_CompositeQuery_Writing.get_WhereCondition(map)
					+ " order by wrt_no ";
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			int updateCount = 0;
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, password);
				pstmt = con.prepareStatement("UPDATE WRITING SET WRT_STA = ? WHERE WRT_NO = ?");

				pstmt.setString(1, wrt_sta);
				pstmt.setString(2, wrt_no);

				pstmt.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
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
					} catch (Exception e) {
						e.printStackTrace();
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
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, password);
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
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return writingVO;
		}
		//世霖結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
}
