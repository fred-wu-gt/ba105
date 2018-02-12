package com.man_aut.model;
import java.sql.*;
import java.util.*;

public class Man_autJDBCDAO implements Man_autDAO_Interface{
	private static final String MY_URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="FRUIT";
	private static final String PASSWORD="FRUIT";
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_INTO="INSERT INTO manager_authority (mf_no,man_no) VALUES(?,?)";
	private static final String DELETE="DELETE FROM manager_authority WHERE mf_no=? AND  man_no=?";
	private static final String FIND_BY_MF_NO="SELECT * FROM manager_authority WHERE mf_no=? ORDER BY man_no";
	private static final String FIND_BY_MAN_NO="SELECT * FROM manager_authority WHERE man_no=? ORDER BY mf_no";
	private static final String GET_ALL="SELECT * FROM manager_authority ORDER BY man_no, mf_no";
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}

	@Override
	public void add(Man_autVO man_autVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, man_autVO.getMf_no());
			pstmt.setString(2, man_autVO.getMan_no());
			pstmt.executeUpdate();
			// Handle any driver errors
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
	public void delete(String mf_no, String man_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE);
			pstmt.setString(1, mf_no);
			pstmt.setString(2, man_no);
			pstmt.executeUpdate();
			// Handle any driver errors
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
	public List<Man_autVO> findByMf_no(String mf_no) {
		List<Man_autVO> man_autVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_MF_NO);
			pstmt.setString(1, mf_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Man_autVO man_autVO=new Man_autVO();
				man_autVO.setMf_no(rs.getString("mf_no"));
				man_autVO.setMan_no(rs.getString("man_no"));
				man_autVOList.add(man_autVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return man_autVOList;
	}
	
	
	@Override
	public List<Man_autVO> findByMan_no(String man_no) {
		List<Man_autVO> man_autVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_MAN_NO);
			pstmt.setString(1, man_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Man_autVO man_autVO=new Man_autVO();
				man_autVO.setMf_no(rs.getString("mf_no"));
				man_autVO.setMan_no(rs.getString("man_no"));
				man_autVOList.add(man_autVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return man_autVOList;
	}

	@Override
	public List<Man_autVO> getAll() {
		List<Man_autVO> man_autVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Man_autVO man_autVO=new Man_autVO();

				man_autVO.setMf_no(rs.getString("mf_no"));
				man_autVO.setMan_no(rs.getString("man_no"));
				man_autVOList.add(man_autVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return man_autVOList;
	}
	
	public static void main(String[] args){    //方法1~2可搭配方法5使用
		/* 1. 新增 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲新增的管理員權限明細");
//			
//		System.out.println("管理員編號:");
//		String man_no=sc.next();
//		
//		System.out.println("功能編號:");
//		String mf_no=sc.next();	
//		
//		Man_autVO man_autVO=new Man_autVO();
//		man_autVO.setMf_no(mf_no);
//		man_autVO.setMan_no(man_no);
//		
//		Man_autJDBCDAO dao=new Man_autJDBCDAO();
//		dao.add(man_autVO);
//		System.out.println("新增資料成功!!");
//		sc.close();
		
		/* 2. 刪除 */
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲刪除的管理員權限明細");
//		System.out.println("管理員編號:");
//		String man_no=sc.next();
//		
//		System.out.println("功能編號:");
//		String mf_no=sc.next();	
//		
//		Man_autJDBCDAO dao=new Man_autJDBCDAO();
//		dao.delete(mf_no,man_no);
//		System.out.println("刪除資料成功!!");
//		sc.close();
		
		/* 3. 以功能編號查管理員 */   
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入功能編號來查詢可使用的管理員");
//		String mf_no=sc.next();
//		
//		Man_autJDBCDAO dao=new Man_autJDBCDAO();
//		List<Man_autVO> man_autVOList=dao.findByMf_no(mf_no);
//		System.out.println("可使用此功能的管理員有:");
//		for(Man_autVO man_autVO:man_autVOList){
//			System.out.println(man_autVO.getMan_no());
//		}
//		sc.close();
		
		/* 4. 以管理員編號查功能 */   
		Scanner sc=new Scanner(System.in);
		System.out.println("請輸入管理員編號來查詢可使用的功能");
		String man_no=sc.next();
		
		Man_autJDBCDAO dao=new Man_autJDBCDAO();
		List<Man_autVO> man_autVOList=dao.findByMan_no(man_no);
		System.out.println("此管理員可使用的功能有:");
		for(Man_autVO man_autVO:man_autVOList){
			System.out.println(man_autVO.getMf_no());
		}
		sc.close();
		
		/* 5. 查全部 */
//		System.out.println("所有管理員權限明細為:");
//		Man_autJDBCDAO dao2=new Man_autJDBCDAO();
//		List<Man_autVO> man_autVOList=dao2.getAll();
//		for(Man_autVO man_autVO2:man_autVOList){
//			System.out.print("管理員編號:"+man_autVO2.getMan_no());
//			System.out.println(", 功能編號:"+man_autVO2.getMf_no());
//		}

		
		
	}
}
