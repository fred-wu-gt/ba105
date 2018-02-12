package com.ord_mas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.ord_det.model.Ord_detJDBCDAO;
import com.ord_det.model.Ord_detVO;

public class Ord_masJDBCDAO implements Ord_masDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FRUIT";
	String passwd = "FRUIT";
	private static final String INSERT_STMT_ORD_MA = "INSERT INTO ORDER_MASTER (ord_no,mem_no,shop_no,ord_time,ord_sta,ord_total,ord_rec,ord_adr,ord_tel,ord_can_rea) "
			+ "VALUES (TO_CHAR(SYSTIMESTAMP,'YYYY-MM-DD')||LPAD(TO_CHAR(ORD_NO_SEQ.NEXTVAL),10,'0'), ?,?,SYSTIMESTAMP,?,?,?,?,?,?)";
	
	private static final String UPDATE = "UPDATE ORDER_MASTER set mem_no=?,shop_no=?,ord_sta=?,ord_total=?,ord_rec=?,ord_adr=?,ord_tel=?,ord_can_rea=?"
			+ "where ord_no =?";
	private static final String DELETE = "DELETE FROM ORDER_MASTER where ord_no = ?";
	private static final String GET_ONE_STMT = 
	"SELECT ord_no,mem_no,shop_no,ord_time,ord_sta,ord_total,ord_rec,ord_adr,ord_tel,ord_can_rea "
	+ "FROM ORDER_MASTER where ord_no=?";
	private static final String GET_ALL_STMT = "SELECT ord_no,mem_no,shop_no,ord_time,ord_sta,ord_total,ord_rec,ord_adr,ord_tel,ord_can_rea "
			+ "FROM ORDER_MASTER ORDER BY ORD_NO";
	private static final String CHANGE_ORDMA_REC= "UPDATE ORDER_MASTER SET ORD_REC=?,ORD_ADR=?,ORD_TEL=?  WHERE ORD_NO =?";
	private static final String GET_Ord_det_ByOrd_no_STMT= "SELECT ORD_NO,COM_NO,OD_QUAN,OD_SCORE "
			+ "FROM ORDER_DETAIL where ORD_NO = ? order by ORD_NO";

	private static final String FIND_BY_MEMNO = "SELECT * FROM ORDER_MASTER where mem_no=? order by ORD_TIME Desc";
	
	private static final String FIND_BY_SHOPNO = "SELECT * FROM ORDER_MASTER where shop_no=? order by ORD_TIME Desc";
	
	private static final String CHANG_ORD_STA= "UPDATE ORDER_MASTER SET ORD_STA=?  WHERE ORD_NO =?";
	
	private static final String GET_SHOPNO_STMT = "SELECT ord_no,mem_no,shop_no,ord_time,ord_sta,ord_total,ord_rec,ord_adr,ord_tel,ord_can_rea "
			+ "FROM ORDER_MASTER where shop_no=?";
	
	private static final String GET_ORDERDETAIL_BY_COMNO = "SELECT * FROM ORDER_DETAIL WHERE ORD_NO = ? ORDER BY ORD_NO";
	
	
	
    //======================承霖開始============================
//	@Override
//	public Ord_masVO findByShop_no(String shop_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Ord_masVO ord_masVO = null;
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_SHOPNO_STMT);
//
//			pstmt.setString(1, shop_no);
//			ord_masVO = new Ord_masVO();
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//
//				ord_masVO.setOrd_no(rs.getString("ord_no"));
//				ord_masVO.setMem_no(rs.getString("mem_no"));
//				ord_masVO.setShop_no(rs.getString("shop_no"));
//				ord_masVO.setOrd_time(rs.getTimestamp("ord_time"));
//				ord_masVO.setOrd_sta(rs.getString("ord_sta"));
//				ord_masVO.setOrd_total(rs.getInt("ord_total"));
//				ord_masVO.setOrd_rec(rs.getString("ord_rec"));
//				ord_masVO.setOrd_adr(rs.getString("ord_adr"));
//				ord_masVO.setOrd_tel(rs.getString("ord_tel"));
//				ord_masVO.setOrd_can_rea(rs.getString("ord_can_rea"));
//			}
//
//		} catch (SQLException se) {
//
//			se.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//		}
//		return ord_masVO;
//	}
//
//	@Override
//	public List<Ord_detVO> getOrderDetailByComNo(String ord_no) {
//		List<Ord_detVO> list = new ArrayList<Ord_detVO>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Ord_detVO ord_detVO = null;
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ORDERDETAIL_BY_COMNO);
//			pstmt.setString(1, ord_no);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				ord_detVO = new Ord_detVO();
//				ord_detVO.setOrd_no(rs.getString("ord_no"));
//				ord_detVO.setCom_no(rs.getString("com_no"));
//				ord_detVO.setOd_quan(rs.getInt("od_quan"));
//				ord_detVO.setOd_score(rs.getDouble("od_score"));
//				list.add(ord_detVO);
//			}
//		} catch (SQLException se) {
//
//			se.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//		}
//		return list;
//	}

    //======================承霖結束============================

	
	

	@Override
	public void changOrdSta(Ord_masVO ord_masVO) {
		
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(CHANG_ORD_STA);

				pstmt.setString(1,ord_masVO.getOrd_sta() );
				pstmt.setString(2,ord_masVO.getOrd_no()  );
				
				pstmt.executeUpdate();

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



	@Override
	public List<Ord_masVO> findByShopNO(String shop_no) {
		List<Ord_masVO> list = new ArrayList<Ord_masVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		Ord_masVO ord_masVO1 = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_SHOPNO);
			
			pstmt.setString(1, shop_no);
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				
				ord_masVO1 = new Ord_masVO();
				ord_masVO1.setOrd_no(rs.getString("ord_no"));
				ord_masVO1.setMem_no(rs.getString("mem_no"));
				ord_masVO1.setShop_no(rs.getString("shop_no"));
				ord_masVO1.setOrd_time(rs.getTimestamp("ord_time"));
				ord_masVO1.setOrd_sta(rs.getString("ord_sta"));
				ord_masVO1.setOrd_total(rs.getInt("ord_total"));
				ord_masVO1.setOrd_rec(rs.getString("ord_rec"));
				ord_masVO1.setOrd_adr(rs.getString("ord_adr"));
				ord_masVO1.setOrd_tel(rs.getString("ord_tel"));
				ord_masVO1.setOrd_can_rea(rs.getString("ord_can_rea"));
				list.add(ord_masVO1);
			}		
						
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		catch (SQLException se) {
			
			se.printStackTrace();
		}
		
		finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return list;

	}

	
	
	@Override
	public List<Ord_masVO> findByMemNO(String mem_no) {
		List<Ord_masVO> list = new ArrayList<Ord_masVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		Ord_masVO ord_masVO1 = null ;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_MEMNO);
			
			pstmt.setString(1, mem_no);
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				
				ord_masVO1 = new Ord_masVO();
				ord_masVO1.setOrd_no(rs.getString("ord_no"));
				ord_masVO1.setMem_no(rs.getString("mem_no"));
				ord_masVO1.setShop_no(rs.getString("shop_no"));
				ord_masVO1.setOrd_time(rs.getTimestamp("ord_time"));
				ord_masVO1.setOrd_sta(rs.getString("ord_sta"));
				ord_masVO1.setOrd_total(rs.getInt("ord_total"));
				ord_masVO1.setOrd_rec(rs.getString("ord_rec"));
				ord_masVO1.setOrd_adr(rs.getString("ord_adr"));
				ord_masVO1.setOrd_tel(rs.getString("ord_tel"));
				ord_masVO1.setOrd_can_rea(rs.getString("ord_can_rea"));
				list.add(ord_masVO1);
			}		
						
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		catch (SQLException se) {
			
			se.printStackTrace();
		}
		
		finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return list;
	}
		
		
		
		
		
	

	
	
	
	
	
	
	

	@Override
	public String insert(Ord_masVO ord_mas) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null ; 
		String ord_no = null ;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String []cols = {"ORD_NO"};
			pstmt = con.prepareStatement(INSERT_STMT_ORD_MA,cols);
			pstmt.setString(1, ord_mas.getMem_no());
			pstmt.setString(2, ord_mas.getShop_no());
			pstmt.setString(3, ord_mas.getOrd_sta());
			pstmt.setInt(4, ord_mas.getOrd_total());
			pstmt.setString(5, ord_mas.getOrd_rec());
			pstmt.setString(6, ord_mas.getOrd_adr());
			pstmt.setString(7, ord_mas.getOrd_tel());
			pstmt.setString(8, ord_mas.getOrd_can_rea());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				 ord_no = rs.getString(1);
				System.out.println("新增訂單編號 :"+ ord_no);
			}
			
			con.commit();

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			try {
				// 發生例外即進行rollback動作
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			se.printStackTrace();
		}

		finally {
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
		return ord_no;

	}
	
	@Override
	public void insertWithOrd_det(Ord_masVO ord_mas, List<Ord_detVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null ; 
		String next_ord_no = null ;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			//先新增訂單主檔
			String []cols = {"ORD_NO"};
			pstmt = con.prepareStatement(INSERT_STMT_ORD_MA,cols);
			pstmt.setString(1, ord_mas.getMem_no());
			pstmt.setString(2, ord_mas.getShop_no());
			pstmt.setString(3, ord_mas.getOrd_sta());
			pstmt.setInt(4, ord_mas.getOrd_total());
			pstmt.setString(5, ord_mas.getOrd_rec());
			pstmt.setString(6, ord_mas.getOrd_adr());
			pstmt.setString(7, ord_mas.getOrd_tel());
			pstmt.setString(8, ord_mas.getOrd_can_rea());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				 next_ord_no = rs.getString(1);
				System.out.println("新增訂單編號 :"+ next_ord_no);
			}else{
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			//再同時新增訂單明細
			Ord_detJDBCDAO ord_detJDBCDAO= new Ord_detJDBCDAO();
			for(Ord_detVO aOrd_det : list ){
				aOrd_det.setOrd_no(next_ord_no);
				ord_detJDBCDAO.insertOrd_det2(aOrd_det, con);
			}
			// ●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("新增訂單編號"+next_ord_no+"時，共有"+list.size()+"筆訂單明細同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. "
					+ ce.getMessage());
		} catch (SQLException se) {
			try {
				// 3●設定於當有exception發生時之catch區塊內
				System.err.println("rollback由Ord_mas產生");
				con.rollback();
			} catch (SQLException exception) {
				throw new RuntimeException("rollback error occured. "
						+ exception.getMessage());
			}
			
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public void update(Ord_masVO ord_mas) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, ord_mas.getMem_no());
			pstmt.setString(2, ord_mas.getShop_no());
			pstmt.setString(3, ord_mas.getOrd_sta());
			pstmt.setInt(4, ord_mas.getOrd_total());
			pstmt.setString(5, ord_mas.getOrd_rec());
			pstmt.setString(6, ord_mas.getOrd_adr());
			pstmt.setString(7, ord_mas.getOrd_tel());
			pstmt.setString(8, ord_mas.getOrd_can_rea());
			pstmt.setString(9, ord_mas.getOrd_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException ce) {

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
	public void delete(String ord_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ord_no);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException ce) {

			ce.printStackTrace();
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

	}

	@Override
	public Ord_masVO findByPrimaryKey(String ord_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		Ord_masVO ord_masVO1 = null ;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, ord_no);
			ord_masVO1 = new Ord_masVO();
			rs= pstmt.executeQuery();
			while (rs.next()) {
				
				ord_masVO1.setOrd_no(rs.getString("ord_no"));
				ord_masVO1.setMem_no(rs.getString("mem_no"));
				ord_masVO1.setShop_no(rs.getString("shop_no"));
				ord_masVO1.setOrd_time(rs.getTimestamp("ord_time"));
				ord_masVO1.setOrd_sta(rs.getString("ord_sta"));
				ord_masVO1.setOrd_total(rs.getInt("ord_total"));
				ord_masVO1.setOrd_rec(rs.getString("ord_rec"));
				ord_masVO1.setOrd_adr(rs.getString("ord_adr"));
				ord_masVO1.setOrd_tel(rs.getString("ord_tel"));
				ord_masVO1.setOrd_can_rea(rs.getString("ord_can_rea"));
			}		
						
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		catch (SQLException se) {
			
			se.printStackTrace();
		}
		
		finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return ord_masVO1;
	}

	@Override
	public Set<Ord_detVO> getOrd_detByOrd_no(String ord_no) {
		Set<Ord_detVO> set = new LinkedHashSet<Ord_detVO>();
		Ord_detVO ord_detVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_Emps_ByDeptno_STMT);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Ord_det_ByOrd_no_STMT);
			pstmt.setString(1, ord_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				ord_detVO = new Ord_detVO();
				ord_detVO.setOrd_no(rs.getString("ord_no"));
				ord_detVO.setCom_no(rs.getString("com_no"));
				ord_detVO.setOd_quan(rs.getInt("od_quan"));
				ord_detVO.setOd_score(rs.getDouble("od_score"));
				set.add(ord_detVO);
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;	
	}
	
	
	
	
	@Override
	public List<Ord_masVO> getAll() {
		List<Ord_masVO> list = new ArrayList<Ord_masVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Ord_masVO ord_masVO2 = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				ord_masVO2 = new Ord_masVO();
				ord_masVO2.setOrd_no(rs.getString("ord_no"));
				ord_masVO2.setMem_no(rs.getString("mem_no"));
				ord_masVO2.setShop_no(rs.getString("shop_no"));
				ord_masVO2.setOrd_time(rs.getTimestamp("ord_time"));
				ord_masVO2.setOrd_sta(rs.getString("ord_sta"));
				ord_masVO2.setOrd_total(rs.getInt("ord_total"));
				ord_masVO2.setOrd_rec(rs.getString("ord_rec"));
				ord_masVO2.setOrd_adr(rs.getString("ord_adr"));
				ord_masVO2.setOrd_tel(rs.getString("ord_tel"));
				ord_masVO2.setOrd_can_rea(rs.getString("ord_can_rea"));
				list.add(ord_masVO2);

			}
		} catch (ClassNotFoundException ce) {

			ce.printStackTrace();
		} catch (SQLException se) {

			se.printStackTrace();
		}

		
		finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		return list;
	}

	@Override
	public void changOrdMasRec(Ord_masVO ord_masVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHANGE_ORDMA_REC);

			pstmt.setString(1,ord_masVO.getOrd_rec() );
			pstmt.setString(2,ord_masVO.getOrd_adr() );
			pstmt.setString(3,ord_masVO.getOrd_tel() );
			pstmt.setString(4,ord_masVO.getOrd_no()  );
			
			pstmt.executeUpdate();

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



	@Override
	public Ord_masVO findByShop_no(String shop_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Ord_masVO> getOrderMasByShop(String shop_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Ord_detVO> getOrderDetailByComNo(String ord_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void updateOrd_sta(String ord_sta, String ord_no) {
		// TODO Auto-generated method stub
		
	}













	

	
	
}

