package com.rep_com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotsales.model.HotsalesVO;

public class Rep_comJDBCDAO implements Rep_comDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FRUIT";
	String passwd = "FRUIT";
	
	private static final String INSERT_STMT = "INSERT INTO REPORT_COMMODITY(RC_NO,COM_NO,MEM_NO,RC_CUZ,RC_STAT,RC_TXT)"
			+ "VALUES('RC'||LPAD(TO_CHAR(RC_NO_SEQ.NEXTVAL),8,'0'),?,?,?,'待審核',?)";
	
	private static final String UPDATE = "UPDATE REPORT_COMMODITY SET COM_NO=?,MEM_NO=? ,RC_CUZ=? ,RC_STAT=?,RC_TXT=?  WHERE RC_NO =?";

	private static final String DELETE = "DELETE FROM REPORT_COMMODITY WHERE RC_NO =?";
	
	private static final String GET_ONE_STMT = "SELECT RC_NO,COM_NO,MEM_NO,RC_CUZ,RC_STAT,RC_TXT "
			+ "FROM REPORT_COMMODITY WHERE  RC_NO =?";
	
	private static final String GET_ALL_STMT ="SELECT RC_NO,COM_NO,MEM_NO,RC_CUZ,RC_TXT,RC_STAT "
			+ "FROM REPORT_COMMODITY ORDER BY RC_NO";
	
	private static final String UPDATE_REP_COM_STAT = "UPDATE REPORT_COMMODITY SET RC_STAT=? WHERE RC_NO =?";


	@Override
	public String  insert(Rep_comVO rep_comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null ; 
		String rc_no = null ;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String []cols = {"RC_NO"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setString(1, rep_comVO.getCom_no());
			pstmt.setString(2, rep_comVO.getMem_no());
			pstmt.setString(3, rep_comVO.getRc_cuz());
			pstmt.setString(4, rep_comVO.getRc_txt());
			
			pstmt.executeUpdate();
			rs= pstmt.getGeneratedKeys();
			if(rs.next()){
			rc_no = rs.getString(1);
			System.out.println("新增商品檢舉:"+ rc_no);
			}

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
		return rc_no;

	}
	

	@Override
	public void updateRep_comStat(Rep_comVO rep_comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(UPDATE_REP_COM_STAT);
			pstmt.setString(1, rep_comVO.getRc_stat());
			pstmt.setString(2, rep_comVO.getRc_no());
		
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
					
					se.printStackTrace(System.err);
				}
			}

		}
		
		
	}


	@Override
	public void update(Rep_comVO rep_comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, rep_comVO.getCom_no());
			pstmt.setString(2, rep_comVO.getMem_no());
			pstmt.setString(3, rep_comVO.getRc_cuz());
			pstmt.setString(4, rep_comVO.getRc_stat());
			pstmt.setString(5, rep_comVO.getRc_txt());
			pstmt.setString(6, rep_comVO.getRc_no());
		
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
					
					se.printStackTrace(System.err);
				}
			}

		}
		
		
		
	}

	@Override
	public void delete(String rc_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, rc_no);
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
	public Rep_comVO findByPrimaryKey(String rc_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		Rep_comVO rep_comVO = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, rc_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				rep_comVO = new Rep_comVO ();
				rep_comVO.setRc_no(rs.getString("rc_no"));
				rep_comVO.setCom_no(rs.getString("com_no"));
				rep_comVO.setMem_no(rs.getString("mem_no"));
				rep_comVO.setRc_cuz(rs.getString("rc_cuz"));
				rep_comVO.setRc_stat(rs.getString("rc_stat"));
				rep_comVO.setRc_txt(rs.getString("rc_txt"));
				
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
		
		return rep_comVO;
	}
		

	@Override
	public List<Rep_comVO> getAll() {
		List<Rep_comVO> list = new ArrayList<Rep_comVO>();
		Rep_comVO rep_comVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				rep_comVO = new Rep_comVO() ;
				rep_comVO.setRc_no(rs.getString("rc_no"));
				rep_comVO.setCom_no(rs.getString("com_no"));
				rep_comVO.setMem_no(rs.getString("mem_no"));
				rep_comVO.setRc_cuz(rs.getString("rc_cuz"));
				rep_comVO.setRc_stat(rs.getString("rc_stat"));
				rep_comVO.setRc_txt(rs.getString("rc_txt"));
				list.add(rep_comVO);
				
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
	


