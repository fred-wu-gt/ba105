package com.ord_det.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.ord_mas.model.Ord_masJDBCDAO;

//import com.commodity.model.CommodityVO;

public class Ord_detJDBCDAO implements Ord_detDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FRUIT";
	String passwd = "FRUIT";

//	private static final String INSERT_STMT = "INSERT INTO ORDER_DETAIL VALUES(TO_CHAR(SYSTIMESTAMP,'YYYY-MM-DD')||LPAD(TO_CHAR(ORD_NO_SEQ.CURRVAL),10,'0'),"
//			+ "?,?,0)";
	private static final String INSERT_STMT = "INSERT INTO ORDER_DETAIL VALUES(?,?,?,0)";
	private static final String UPDATE = "UPDATE ORDER_DETAIL SET OD_SCORE=? "
			+ "WHERE ORD_NO=? AND COM_NO=? ";
	
	private static final String DELETE = "DELETE FROM ORDER_DETAIL WHERE ORD_NO=? AND COM_NO=?";
	
	private static final String GET_ONE_STMT_BY_ORD_NO = 
			"SELECT ORD_NO ,COM_NO,OD_QUAN,OD_SCORE FROM ORDER_DETAIL WHERE ORD_NO = ?";
	
	private static final String GET_ONE_STMT_BY_COM_NO = 
			"SELECT ORD_NO ,COM_NO,OD_QUAN,OD_SCORE FROM ORDER_DETAIL WHERE COM_NO =?";
	
	private static final String GET_ALL = 
			"SELECT ORD_NO ,COM_NO,OD_QUAN,OD_SCORE FROM ORDER_DETAIL ORDER BY ORD_NO";
	

	@Override
	public void insertOrd_det(Ord_detVO ord_det) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, ord_det.getCom_no());
			pstmt.setInt(2, ord_det.getOd_quan());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException ce) {

			ce.printStackTrace();
		} catch (SQLException se) {
			
			se.printStackTrace();
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
	public void insertOrd_det2(Ord_detVO ord_det, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, ord_det.getOrd_no());
			pstmt.setString(2, ord_det.getCom_no());
			pstmt.setInt(3, ord_det.getOd_quan());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.println("rollback由Ord_det產生");
					con.rollback();
				} catch (SQLException ex) {
					throw new RuntimeException("rollback error occured!!" + ex.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	
	

	@Override
	public void update(Ord_detVO ord_det) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setDouble(1, ord_det.getOd_score());
			pstmt.setString(2, ord_det.getOrd_no());
			pstmt.setString(3, ord_det.getCom_no());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
				try {
					con.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}

		}

	}

	@Override
	public void delete(String ord_no,String com_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ord_no);
			pstmt.setString(2, com_no);
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException ce) {
			
			ce.printStackTrace();
		} catch (SQLException se) {

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
	public List<Ord_detVO> findByOrd_no(String ord_no) {
		List<Ord_detVO> listByOrd_no = new ArrayList<Ord_detVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		Ord_detVO ord_detVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_ORD_NO);
			pstmt.setString(1, ord_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ord_detVO = new Ord_detVO();
				ord_detVO.setOrd_no(rs.getString("ord_no"));
				ord_detVO.setCom_no(rs.getString("com_no"));
				ord_detVO.setOd_quan(rs.getInt("od_quan"));
				ord_detVO.setOd_score(rs.getDouble("od_score"));
				listByOrd_no.add(ord_detVO);
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

		return listByOrd_no;
	}

	@Override
	public List<Ord_detVO> findByCom_no(String com_no) {
	
			List<Ord_detVO> listByCom_no = new ArrayList<Ord_detVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null ;
			Ord_detVO ord_detVO1 = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT_BY_COM_NO);
				pstmt.setString(1, com_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					ord_detVO1 = new Ord_detVO();
					ord_detVO1.setOrd_no(rs.getString("ord_no"));
					ord_detVO1.setCom_no(rs.getString("com_no"));
					ord_detVO1.setOd_quan(rs.getInt("od_quan"));
					ord_detVO1.setOd_score(rs.getDouble("od_score"));
					listByCom_no.add(ord_detVO1);
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

			return listByCom_no;
		}
		
		
		
		
	
	@Override
	public List<Ord_detVO> getAll() {
		
		List<Ord_detVO> list = new ArrayList<Ord_detVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		Ord_detVO ord_detVO2 = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ord_detVO2 = new Ord_detVO();
				ord_detVO2.setOrd_no(rs.getString("ord_no"));
				ord_detVO2.setCom_no(rs.getString("com_no"));
				ord_detVO2.setOd_quan(rs.getInt("od_quan"));
				ord_detVO2.setOd_score(rs.getDouble("od_score"));
				list.add(ord_detVO2);
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

		return list;
	}

	

	
	

}
