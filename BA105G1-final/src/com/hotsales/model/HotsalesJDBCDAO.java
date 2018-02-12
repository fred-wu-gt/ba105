package com.hotsales.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.commodity.model.CommodityVO;

public class HotsalesJDBCDAO implements  HotsalesDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FRUIT";
	String passwd = "FRUIT";
	
	private static final String INSERT_STMT = "INSERT INTO HOTSALES(HOT_NO,COM_NO,HOT_DATE)"
			+ "VALUES('HOT'||LPAD(TO_CHAR(HOT_NO_SEQ.NEXTVAL),7,'0'),?,SYSTIMESTAMP)";
	
	private static final String UPDATE = "UPDATE HOTSALES SET COM_NO=?,HOT_DATE=SYSTIMESTAMP"
			+ " WHERE HOT_NO =?";
	
	private static final String DELETE = "DELETE FROM HOTSALES WHERE HOT_NO =?";
	
	private static final String GET_ONE_STMT = "SELECT HOT_NO,COM_NO,"
			+ "TO_CHAR(HOT_DATE,'YYYY-MM-DD HH:MM:SS')HOT_DATE FROM HOTSALES WHERE HOT_NO =?";
	
	private static final String GET_ALL_STMT ="SELECT HOT_NO,COM_NO,"
			+ "TO_CHAR(HOT_DATE,'YYYY-MM-DD HH:MM:SS')HOT_DATE FROM HOTSALES ORDER BY HOT_NO";

	private static final String FINDBYOD_QUAN = "SELECT COM_NO, SUM(OD_QUAN) AS QUAN_SUM FROM ORDER_DETAIL "
			+ "GROUP BY COM_NO ORDER BY QUAN_SUM DESC";
	
	
	
	
	
	@Override
	public List<String> findByOd_quan() {
		
			List<String> list = new ArrayList<String>();
			HotsalesVO hotVO1 = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(FINDBYOD_QUAN);
				rs = pstmt.executeQuery();
				while(rs.next()){		
					list.add(rs.getString("com_no"));				
				}
				
			} catch (ClassNotFoundException ce) {

				ce.printStackTrace();
			}

			catch (SQLException se) {

				se.printStackTrace();
			}
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
			
			
			return list;
		
	}

	

	@Override
	public void insert(HotsalesVO hotsalesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, hotsalesVO.getCom_no());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
		finally{
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}

			}
			
		}

	}
	

	@Override
	public void update(HotsalesVO hotsalesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, hotsalesVO.getCom_no());
			pstmt.setString(2, hotsalesVO.getHot_no());
			pstmt.executeUpdate();
			

		} catch (ClassNotFoundException ce) {
			
			ce.printStackTrace();
		}

		catch (SQLException se) {
		
			se.printStackTrace();
		}
		finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
				try {
					con.close();
				} catch (SQLException se) {
					
					se.printStackTrace();
				}
			}

		}
		

	}

	@Override
	public void delete(String hot_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, hot_no);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}

		catch (SQLException se) {

			se.printStackTrace();
		}
		
		finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	@Override
	public HotsalesVO findByPrimaryKey(String hot_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		HotsalesVO hotsalesVO = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, hot_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				hotsalesVO = new HotsalesVO();
				hotsalesVO.setHot_no(rs.getString("hot_no"));
				hotsalesVO.setCom_no(rs.getString("com_no"));
				hotsalesVO.setHot_date(rs.getTimestamp("hot_date"));
			}
			
			
		}

		catch (SQLException se) {
			
			se.printStackTrace();
		}

		catch (ClassNotFoundException ce) {
			
			ce.printStackTrace();
		}
		
		finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
		
					e.printStackTrace();
				}
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
		
		return hotsalesVO;
	}

	@Override
	public List<HotsalesVO> getAll() {
		List<HotsalesVO> list = new ArrayList<HotsalesVO>();
		HotsalesVO hotVO1 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				hotVO1 = new HotsalesVO() ;
				hotVO1.setHot_no(rs.getString("hot_no"));
				hotVO1.setCom_no(rs.getString("com_no"));
				hotVO1.setHot_date(rs.getTimestamp(3));
				list.add(hotVO1);
				
			}
			
		} catch (ClassNotFoundException ce) {

			ce.printStackTrace();
		}

		catch (SQLException se) {

			se.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
		
		
		return list;
	}



}
