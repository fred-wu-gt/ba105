package com.fru.pri.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.commodity.model.CommodityVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Commodity;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Fru_price;



public class Fru_priDAO implements Fru_priDAO_Interface {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";

	private static final String INSERT_STMT = "INSERT INTO FRUIT_PRICE(FP_NO,FRU_NO,FP_NAME,FP_TIME,FP_PRI)"
			+ "VALUES(('FP'||LPAD(to_char(FRUIT_PRICE_SEQ.NEXTVAL),8,'0')), ?, ?,SYSTIMESTAMP, ?)";
	private static final String UPDATE_STMT = "UPDATE FRUIT_PRICE SET FRU_NO = ?, FP_NAME = ?, FP_TIME = SYSTIMESTAMP, FP_PRI = ? WHERE FP_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM FRUIT_PRICE WHERE FP_NO = ?";
	private static final String FIND_BY_DEPTNO = "SELECT * FROM FRUIT_PRICE WHERE FP_NO = ?";
	private static final String FIND_BY_FPNAME = "SELECT * FROM FRUIT_PRICE WHERE FP_NAME = ?";
	private static final String GET_ALL = "SELECT * FROM FRUIT_PRICE";

	@Override
	public void add(Fru_priVO fru_priVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, fru_priVO.getFru_no());
			pstmt.setString(2, fru_priVO.getFp_name());
			//pstmt.setTimestamp(3, fru_priVO.getFp_time());
			pstmt.setDouble(3, fru_priVO.getFp_pri());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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

	}

	@Override
	public void update(Fru_priVO fru_priVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, fru_priVO.getFru_no());
			pstmt.setString(2, fru_priVO.getFp_name());
			// pstmt.setTimestamp(3, fru_privo.getFp_time());
			pstmt.setDouble(3, fru_priVO.getFp_pri());
			pstmt.setString(4, fru_priVO.getFp_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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

	}

	@Override
	public void delete(String fp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, fp_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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

	}

	@Override
	public Fru_priVO findByFp_no(String fp_no) {
		Fru_priVO fru_priVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_DEPTNO);
			pstmt.setString(1, fp_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fru_priVO = new Fru_priVO();
				fru_priVO.setFp_no(rs.getString("FP_NO"));
				fru_priVO.setFru_no(rs.getString("FRU_NO"));
				fru_priVO.setFp_name(rs.getString("FP_NAME"));
				fru_priVO.setFp_time(rs.getTimestamp("FP_TIME"));
				fru_priVO.setFp_pri(rs.getDouble("FP_PRI"));

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

		return fru_priVO;

	}

	@Override
	public List<Fru_priVO> getAll() {

		List<Fru_priVO> fru_priVOList = new ArrayList<>();
		Fru_priVO fru_priVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fru_priVO = new Fru_priVO();
				fru_priVO.setFp_no(rs.getString("FP_NO"));
				fru_priVO.setFru_no(rs.getString("FRU_NO"));
				fru_priVO.setFp_name(rs.getString("FP_NAME"));
				fru_priVO.setFp_time(rs.getTimestamp("FP_TIME"));
				fru_priVO.setFp_pri(rs.getDouble("FP_PRI"));

				fru_priVOList.add(fru_priVO);
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
		return fru_priVOList;

	}
	
	
	
	@Override
	public Fru_priVO findByFp_name(String fp_name) {
		Fru_priVO fru_priVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_FPNAME);
			pstmt.setString(1, fp_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fru_priVO = new Fru_priVO();
				fru_priVO.setFp_no(rs.getString("FP_NO"));
				fru_priVO.setFru_no(rs.getString("FRU_NO"));
				fru_priVO.setFp_name(rs.getString("FP_NAME"));
				fru_priVO.setFp_time(rs.getTimestamp("FP_TIME"));
				fru_priVO.setFp_pri(rs.getDouble("FP_PRI"));

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

		return fru_priVO;
		
	}

	

	public static void main(String[] argc) {

		Fru_priDAO dao = new Fru_priDAO();

		// 新增
//		Fru_priVO fru_priVO = new Fru_priVO();
//		Scanner sc = new Scanner(System.in);
//		System.out.println("請輸入欲新增的蔬果資料");
//
//		System.out.println("新增蔬果編號:");
//		String fru_no = sc.next();
//		System.out.println("新增蔬果名稱:");
//		String fp_name = sc.next();
//		System.out.println("新增蔬果批發價:");
//		Double fp_pri = sc.nextDouble();
//
//		fru_priVO.setFru_no(fru_no);
//		fru_priVO.setFp_name(fp_name);
//		
//		fru_priVO.setFp_pri(fp_pri);
//
//		dao.add(fru_priVO);
//		sc.close();
//		System.out.println("=================新增成功=================");

		// 修改
//		 Fru_priVO fru_priVO = new Fru_priVO();
//		 Scanner sc=new Scanner(System.in);
//		 System.out.println("請輸入欲修改的蔬果資料");
//		 System.out.println("修改蔬果編號:");
//		 String fru_no=sc.next();
//		 System.out.println("修改蔬果名稱:");
//		 String fp_name=sc.next();
//		 System.out.println("修改蔬果批發價:");
//		 Double fp_pri=sc.nextDouble();
//		 System.out.println("修改蔬果時價編號:");
//		 String fp_no=sc.next();
//		
//		
//		
//		 fru_priVO.setFru_no(fru_no);
//		 fru_priVO.setFp_name(fp_name);
//		 fru_priVO.setFp_pri(fp_pri);
//		 fru_priVO.setFp_no(fp_no);
//		
//		
//		 dao.update(mem2);
//		 sc.close();
//		 System.out.println("================修改成功==================");

		// 刪除

//		 Scanner sc=new Scanner(System.in);
//		 System.out.println("請輸入刪除蔬果時價編號:");
//		 String fp_no=sc.next();
//		 dao.delete(fp_no);
//		 sc.close();
//		 System.out.println("================刪除成功==================");

		// 查詢一筆蔬果時價

//		Scanner sc = new Scanner(System.in);
//		System.out.println("請輸入查詢一筆蔬果時價編號:");
//		String fp_no = sc.next().trim();
//		Fru_priVO fru_priVO = dao.findByFp_no(fp_no);
//
//		System.out.println(fru_priVO.getFp_no() + ",");
//		System.out.println(fru_priVO.getFru_no() + ",");
//		System.out.println(fru_priVO.getFp_name() + ",");
//		System.out.println(fru_priVO.getFp_time() + ",");
//		System.out.println(fru_priVO.getFp_pri() + ",");
//
//		sc.close();
//		System.out.println("===============查詢一筆資料成功===================");

		
		// 查詢一筆蔬果時價NAME

//		Scanner sc = new Scanner(System.in);
//		System.out.println("請輸入查詢一筆蔬果名稱:");
//		String fp_name = sc.next().trim();
//		Fru_priVO fru_priVO = dao.findByFp_name(fp_name);
////
//		System.out.println(fru_priVO.getFp_no() + ",");
//		System.out.println(fru_priVO.getFru_no() + ",");
//		System.out.println(fru_priVO.getFp_name() + ",");
//		System.out.println(fru_priVO.getFp_time() + ",");
//		System.out.println(fru_priVO.getFp_pri() + ",");
////
//		sc.close();
//		System.out.println("===============查詢一筆資料成功===================");
		
		
		
		
		// 查詢全部蔬果時價
		//
		// List<Fru_priVO> list = dao.getAll();
		// for(Fru_priVO fru_priVO:list){
		//
		// System.out.println(fru_priVO.getFp_no() + ",");
		// System.out.println(fru_priVO.getFru_no() + ",");
		// System.out.println(fru_priVO.getFp_name() + ",");
		// System.out.println(fru_priVO.getFp_time() + ",");
		// System.out.println(fru_priVO.getFp_pri() + ",");
		//
		// System.out.println("================查詢全部資料==================");
		//
		// }

	}

	@Override
	public List<Fru_priVO> getAll(Map<String, String[]> map) {
		List<Fru_priVO> list = new ArrayList<Fru_priVO>();
		Fru_priVO fru_priVO = null ;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ; 
		
try {
			
	       Class.forName(DRIVER);
	       con = DriverManager.getConnection(URL, USER, PASSWORD);
			String finalSQL = "select * from FRUIT_PRICE"
							+jdbcUtil_CompositeQuery_Fru_price.get_WhereCondition(map)
							+" order by fp_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("萬用查詢的SQL指令 finalSQL:"+finalSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fru_priVO = new Fru_priVO();
				fru_priVO.setFp_no(rs.getString("fp_no"));
				fru_priVO.setFru_no(rs.getString("fru_no"));
				fru_priVO.setFp_name(rs.getString("fp_name"));
				fru_priVO.setFp_time(rs.getTimestamp("fp_time"));
				fru_priVO.setFp_pri(rs.getDouble("fp_pri"));
				
				list.add(fru_priVO);
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

	
}
