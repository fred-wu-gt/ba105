package com.wri_rep.model;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Wri_repJDBCDAO implements Wri_repDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FRUIT";
	String password = "FRUIT";

	private static final String INSERT_STMT = "INSERT INTO WRITING_REPORT(WRE_NO,WRT_NO,MEM_NO,WRE_RSN,WRE_STAT,WRE_CONT) "
			+ "VALUES(('WRE'||LPAD(TO_CHAR(WRE_NO_SEQ.NEXTVAL),7,'0')),?,?,?,?,?)";

	private static final String UPDATE_STMT = "UPDATE WRITING_REPORT SET WRT_NO = ?, MEM_NO = ? , WRE_RSN = ?, WRE_STAT = ? , WRE_CONT = ? "
			+ "WHERE WRE_NO = ? ";

	private static final String DELETE = "DELETE FROM WRITING_REPORT WHERE WRE_NO = ? ";

	private static final String GET_ONE_STMT = "SELECT * FROM WRITING_REPORT WHERE WRE_NO = ? ";

	private static final String GET_ALL = "SELECT * FROM WRITING_REPORT";

	public Integer insert(Wri_repVO wri_repVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, wri_repVO.getWrt_no());
			pstmt.setString(2, wri_repVO.getMem_no());
			pstmt.setString(3, wri_repVO.getWre_rsn());
			pstmt.setString(4, wri_repVO.getWre_stat());
			pstmt.setString(5, wri_repVO.getWre_cont());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
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
		return updateCount;
	}

	public Integer update(Wri_repVO wri_repVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, wri_repVO.getWrt_no());
			pstmt.setString(2, wri_repVO.getMem_no());
			pstmt.setString(3, wri_repVO.getWre_rsn());
			pstmt.setString(4, wri_repVO.getWre_stat());
			pstmt.setString(5, wri_repVO.getWre_cont());
			pstmt.setString(6, wri_repVO.getWre_no());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
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
		return updateCount;
	}

	public Integer delete(String wre_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, wre_no);
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

	public Wri_repVO findByPrimaryKey(String wre_no) {

		Wri_repVO wri_repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, wre_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri_repVO = new Wri_repVO();
				wri_repVO.setWre_no(rs.getString("wre_no"));
				wri_repVO.setWrt_no(rs.getString("wrt_no"));
				wri_repVO.setMem_no(rs.getString("mem_no"));
				wri_repVO.setWre_rsn(rs.getString("wre_rsn"));
				wri_repVO.setWre_stat(rs.getString("wre_stat"));
				wri_repVO.setWre_cont(rs.getString("wre_cont"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wri_repVO;
	}

	public List<Wri_repVO> getAll() {
		List<Wri_repVO> wriList = new ArrayList<>();
		Wri_repVO wri = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wri = new Wri_repVO();
				wri.setWre_no(rs.getString("WRE_NO"));
				wri.setWrt_no(rs.getString("WRT_NO"));
				wri.setMem_no(rs.getString("MEM_NO"));
				wri.setWre_rsn(rs.getString("WRE_RSN"));
				wri.setWre_stat(rs.getString("WRE_STAT"));
				wri.setWre_cont(rs.getString("WRE_CONT"));
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

	//子怡開始	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		@Override
		public List<Wri_repVO> findByWre_stat(String wre_stat) {
			List<Wri_repVO> wriList = new ArrayList<>();
			Wri_repVO wri = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, password);
				pstmt = con.prepareStatement("SELECT * FROM WRITING_REPORT WHERE wre_stat = ?  order by WRE_NO");
				pstmt.setString(1, wre_stat);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					wri = new Wri_repVO();
					wri.setWre_no(rs.getString("WRE_NO"));
					wri.setWrt_no(rs.getString("WRT_NO"));
					wri.setMem_no(rs.getString("MEM_NO"));
					wri.setWre_rsn(rs.getString("WRE_RSN"));
					wri.setWre_stat(rs.getString("WRE_STAT"));
					wri.setWre_cont(rs.getString("WRE_CONT"));
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
		
		public void updateWre_stat(String wre_stat, String wre_no) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, password);
				pstmt = con.prepareStatement("UPDATE WRITING_REPORT SET wre_stat = ? WHERE wre_no = ?");
				pstmt.setString(1, wre_stat);
				pstmt.setString(2, wre_no);
				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

			}
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

	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
