package com.commodity.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Commodity;

public class CommodityJDBCDAO implements CommodityDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FRUIT";
	String passwd = "FRUIT";

	private static final String INSERT_STMT = "INSERT INTO COMMODITY(COM_NO,COM_NAME,SHOP_NO,FRU_NO,COM_PRICE,COM_WEIGHT,COM_REMARKS,COM_PIC1,COM_PIC2,COM_PIC3,COM_TIME,COM_STATUS,COM_STORE,COM_SCORE,COM_PEO)"
			+ "VALUES('COM'||LPAD(TO_CHAR(COM_NO_SEQ.NEXTVAL),7,'0'),?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?)";
	
	private static final String UPDATE_FOR_SHOP = "UPDATE COMMODITY SET COM_NAME=?,FRU_NO=?, COM_PRICE=?, COM_WEIGHT=?,COM_REMARKS=?, "
			+ "COM_PIC1= ? ,COM_PIC2= ? ,COM_PIC3= ? ,COM_TIME=SYSTIMESTAMP ,COM_STATUS=?, COM_STORE=? WHERE COM_NO =?";
	
	private static final String DELETE = "DELETE FROM COMMODITY WHERE COM_NO = ?";
	
	
	private static final String GET_ONE_STMT = "SELECT COM_NO,COM_NAME,SHOP_NO,FRU_NO,COM_PRICE,COM_WEIGHT,COM_REMARKS,COM_PIC1,COM_PIC2,COM_PIC3,"
			+ "COM_TIME,COM_STATUS,COM_STORE,COM_SCORE,COM_PEO FROM COMMODITY WHERE COM_NO  = ?";
	
	private static final String GET_ALL_STMT = "SELECT COM_NO,COM_NAME,SHOP_NO,FRU_NO,COM_PRICE,COM_WEIGHT,COM_REMARKS,COM_PIC1,COM_PIC2,COM_PIC3,"
			+ "COM_TIME,COM_STATUS,COM_STORE,COM_SCORE,COM_PEO FROM COMMODITY ORDER BY COM_TIME Desc ";
	
	private static final String SCORE_COMMODITY = "UPDATE COMMODITY SET COM_SCORE=?,COM_PEO=?  WHERE COM_NO =?";
	
	private static final String UPDATE_COM_STORAGE = "UPDATE COMMODITY SET COM_STORE=? WHERE COM_NO =?";
	
	

	//================================承霖使用=========================================
//	private static final String DELETE = "DELETE FROM COMMODITY WHERE COM_NO = ? AND FRU_NO = ? AND SHOP_NO = ? ";
	
//	private static final String GET_COMVO_STMT = "SELECT COM_NO,COM_NAME,SHOP_NO,FRU_NO,COM_PRICE,COM_WEIGHT,COM_REMARKS,COM_PIC1,COM_PIC2,COM_PIC3,"
//			+ "COM_TIME,COM_STATUS,COM_STORE,COM_SCORE,COM_PEO FROM COMMODITY WHERE COM_NO  = ?";
//	private static final String UPDATE_COMMODITY = "UPDATE COMMODITY SET COM_REMARKS=? WHERE COM_NO =?";
//
//	
//	
//	@Override
//	public CommodityVO findByShopNo(String shop_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		CommodityVO comVO = null;
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			pstmt.setString(1, shop_no);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				comVO = new CommodityVO();
//				comVO.setCom_no(rs.getString("com_no"));
//				comVO.setCom_name(rs.getString("com_name"));
//				comVO.setShop_no(rs.getString("shop_no"));
//				comVO.setFru_no(rs.getString("fru_no"));
//				comVO.setCom_price(rs.getInt("com_price"));
//				comVO.setCom_remarks(rs.getString("com_remarks"));
//				comVO.setCom_weight(rs.getString("com_weight"));
//				comVO.setCom_pic1(rs.getBytes("com_pic1"));
//				comVO.setCom_pic2(rs.getBytes("com_pic2"));
//				comVO.setCom_pic3(rs.getBytes("com_pic3"));
//				comVO.setCom_time(rs.getTimestamp("com_time"));
//				comVO.setCom_status(rs.getString("com_status"));
//				comVO.setCom_store(rs.getInt("com_store"));
//				comVO.setCom_score(rs.getDouble("com_score"));
//				comVO.setCom_peo(rs.getInt("com_peo"));
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return comVO;
//	}
//
//	@Override
//	public void updateCom_remarksParam(String com_remarks, String com_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE_COMMODITY);
//
//			pstmt.setString(1, com_remarks);
//			pstmt.setString(2, com_no);
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			// TODO Auto-generated catch block
//			se.printStackTrace();
//		}
//
//		finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//		}
//	}
//	@Override
//	public CommodityVO getCommodityVO(String com_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		CommodityVO comVO = null;
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_COMVO_STMT);
//			pstmt.setString(1, com_no);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				comVO = new CommodityVO();
//				comVO.setCom_no(rs.getString("com_no"));
//				comVO.setCom_name(rs.getString("com_name"));
//				comVO.setShop_no(rs.getString("shop_no"));
//				comVO.setFru_no(rs.getString("fru_no"));
//				comVO.setCom_price(rs.getInt("com_price"));
//				comVO.setCom_remarks(rs.getString("com_remarks"));
//				comVO.setCom_weight(rs.getString("com_weight"));
//				comVO.setCom_pic1(rs.getBytes("com_pic1"));
//				String com_pic1S = Base64.getEncoder().encodeToString(rs.getBytes("com_pic1"));
//				comVO.setCom_pic2(rs.getBytes("com_pic2"));
//				comVO.setCom_pic3(rs.getBytes("com_pic3"));
//				comVO.setCom_time(rs.getTimestamp("com_time"));
//				comVO.setCom_status(rs.getString("com_status"));
//				comVO.setCom_store(rs.getInt("com_store"));
//				comVO.setCom_score(rs.getDouble("com_score"));
//				comVO.setCom_peo(rs.getInt("com_peo"));
//
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {			
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return comVO;
//	}
	
	
	 //================================承霖使用=========================================
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void delete(String com_no, String fru_no, String shop_no) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	@Override
	public void updateComStorage(CommodityVO comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_COM_STORAGE);

			pstmt.setInt(1, comVO.getCom_store());
			pstmt.setString(2, comVO.getCom_no());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException ce) {
			// TODO Auto-generated catch block
			ce.printStackTrace();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	
	
	
	@Override
	public String insert(CommodityVO comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null ; 
		String com_no = null ;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String[] cols= {"com_no"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setString(1, comVO.getCom_name());
			pstmt.setString(2, comVO.getShop_no());
			pstmt.setString(3, comVO.getFru_no());
			pstmt.setInt(4, comVO.getCom_price());
			pstmt.setString(5, comVO.getCom_weight());
			pstmt.setString(6, comVO.getCom_remarks());
			pstmt.setBytes(7, comVO.getCom_pic1());
			pstmt.setBytes(8, comVO.getCom_pic2());
			pstmt.setBytes(9, comVO.getCom_pic3());
			pstmt.setString(10, comVO.getCom_status());
			pstmt.setInt(11, comVO.getCom_store());
			pstmt.setDouble(12, comVO.getCom_score());
			pstmt.setInt(13, comVO.getCom_peo());

			pstmt.executeUpdate();
			pstmt.clearParameters();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				com_no = rs.getString(1);
			}
			System.out.println("新增成功商品編號:"+com_no);
			

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			throw new RuntimeException("帳號重覆!");
//			se.printStackTrace();
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}

			}

		}
		return com_no;

	}

	@Override
	public void updateForShop(CommodityVO comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_FOR_SHOP);

			pstmt.setString(1, comVO.getCom_name());
//			pstmt.setString(2, comVO.getShop_no());
			pstmt.setString(2, comVO.getFru_no());
			pstmt.setInt(3, comVO.getCom_price());
			pstmt.setString(4, comVO.getCom_weight());
			pstmt.setString(5, comVO.getCom_remarks());
			pstmt.setBytes(6, comVO.getCom_pic1());
			pstmt.setBytes(7, comVO.getCom_pic2());
			pstmt.setBytes(8, comVO.getCom_pic3());
			pstmt.setString(9, comVO.getCom_status());
			pstmt.setInt(10, comVO.getCom_store());
			pstmt.setString(11, comVO.getCom_no());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException ce) {
			// TODO Auto-generated catch block
			ce.printStackTrace();
		} catch (SQLException se) {
			// TODO Auto-generated catch block
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
	
	@Override
	public void scoreCommodity(CommodityVO comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SCORE_COMMODITY);

			pstmt.setDouble(1, comVO.getCom_score());
			pstmt.setInt(2, comVO.getCom_peo());
			pstmt.setString(3, comVO.getCom_no());
			
			pstmt.executeUpdate();
			System.out.println(comVO.getCom_peo());

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

//	@Override
//	public void delete(String com_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, com_no);
//
//			pstmt.executeUpdate();
//
//		} catch (ClassNotFoundException ce) {
//			// TODO Auto-generated catch block
//			ce.printStackTrace();
//		} catch (SQLException se) {
//			// TODO Auto-generated catch block
//			se.printStackTrace();
//		}
//
//		finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//	}

	@Override
	public CommodityVO findByPrimaryKey(String com_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		CommodityVO comVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, com_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				comVO = new CommodityVO();
				comVO.setCom_no(rs.getString("com_no"));
				comVO.setCom_name(rs.getString("com_name"));
				comVO.setShop_no(rs.getString("shop_no"));
				comVO.setFru_no(rs.getString("fru_no"));
				comVO.setCom_price(rs.getInt("com_price"));
				comVO.setCom_remarks(rs.getString("com_remarks"));				
				comVO.setCom_weight(rs.getString("com_weight"));
				comVO.setCom_pic1(rs.getBytes("com_pic1"));
				String com_pic1S = Base64.getEncoder().encodeToString(rs.getBytes("com_pic1"));
				comVO.setCom_pic2(rs.getBytes("com_pic2"));
				comVO.setCom_pic3(rs.getBytes("com_pic3"));
				comVO.setCom_time(rs.getTimestamp("com_time"));
				comVO.setCom_status(rs.getString("com_status"));
				comVO.setCom_store(rs.getInt("com_store"));
				comVO.setCom_score(rs.getDouble("com_score"));
				comVO.setCom_peo(rs.getInt("com_peo"));

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

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

		return comVO;
	}

	@Override
	public List<CommodityVO> getAll() {
		 List<CommodityVO> list = new ArrayList<CommodityVO>();
		 CommodityVO comVO = null ;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ; 
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comVO = new CommodityVO();
				comVO.setCom_no(rs.getString("com_no"));
				comVO.setCom_name(rs.getString("com_name"));
				comVO.setShop_no(rs.getString("shop_no"));
				comVO.setFru_no(rs.getString("Fru_no"));
				comVO.setCom_price(rs.getInt("com_price"));
				comVO.setCom_weight(rs.getString("com_weight"));
				comVO.setCom_remarks(rs.getString("com_remarks"));
				comVO.setCom_pic1(rs.getBytes("com_pic1"));
				comVO.setCom_pic2(rs.getBytes("com_pic2"));
				comVO.setCom_pic3(rs.getBytes("com_pic3"));
				comVO.setCom_time(rs.getTimestamp("com_time"));
				comVO.setCom_status(rs.getString("com_status"));
				comVO.setCom_store(rs.getInt("com_store"));
				comVO.setCom_score(rs.getDouble("com_score"));
				comVO.setCom_peo(rs.getInt("com_peo"));
				list.add(comVO);
			}
			 
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
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
	public List<CommodityVO> getAll(Map<String, String[]> map) {
		List<CommodityVO> list = new ArrayList<CommodityVO>();
		CommodityVO comVO = null ;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ; 
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from Commodity "
							+jdbcUtil_CompositeQuery_Commodity.get_WhereCondition(map)
							+"order by com_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("萬用查詢的SQL指令 finalSQL:"+finalSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comVO = new CommodityVO();
				comVO.setCom_no(rs.getString("com_no"));
				comVO.setCom_name(rs.getString("com_name"));
				comVO.setShop_no(rs.getString("shop_no"));
				comVO.setFru_no(rs.getString("Fru_no"));
				comVO.setCom_price(rs.getInt("com_price"));
				comVO.setCom_weight(rs.getString("com_weight"));
				comVO.setCom_remarks(rs.getString("com_remarks"));
				comVO.setCom_pic1(rs.getBytes("com_pic1"));
				comVO.setCom_time(rs.getTimestamp("com_time"));
				comVO.setCom_status(rs.getString("com_status"));
				comVO.setCom_store(rs.getInt("com_store"));
				comVO.setCom_score(rs.getDouble("com_score"));
				comVO.setCom_peo(rs.getInt("com_peo"));
				list.add(comVO);
			}
			 
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
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
	public void delete(String com_no) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void updateCom_remarksParam(String com_remarksParam, String com_no) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public CommodityVO getCommodityVO(String com_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public CommodityVO findByShopNo(String shop_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String insertCom(CommodityVO comVO) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<CommodityVO> getComByShopNo(String shop_no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CommodityVO> getAllComposite(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
