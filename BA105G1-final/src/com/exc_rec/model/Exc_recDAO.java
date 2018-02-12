package com.exc_rec.model;
import java.util.*;
import java.sql.*;

public class Exc_recDAO implements Exc_recDAO_Interface{
	private static final String MY_URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="FRUIT";
	private static final String PASSWORD="FRUIT";
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_INTO="insert into exchange_record (er_no,tp_no,mem_no,er_time) values('ER'||LPAD(to_char(exchange_record_seq.nextval),8,'0'),?,?,?)";
	private static final String UPDATE="update exchange_record set tp_no=?, mem_no=?, er_time=? where er_no=?";
	private static final String DELETE="delete from exchange_record where er_no=?";
	private static final String FIND_BY_ER_NO="select * from exchange_record where er_no=?";
	private static final String GET_ALL="select * from exchange_record order by er_no";
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}

	@Override
	public void add(Exc_recVO exc_recVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, exc_recVO.getTp_no());
			pstmt.setString(2, exc_recVO.getMem_no());
			pstmt.setTimestamp(3, exc_recVO.getEr_time());
			pstmt.executeUpdate();
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
	public void update(Exc_recVO exc_recVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, exc_recVO.getTp_no());
			pstmt.setString(2, exc_recVO.getMem_no());
			pstmt.setTimestamp(3, exc_recVO.getEr_time());
			pstmt.setString(4, exc_recVO.getEr_no());
			pstmt.executeUpdate();
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
	public void delete(String er_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE);
			pstmt.setString(1, er_no);
			pstmt.executeUpdate();
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
	public Exc_recVO findByEr_no(String er_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Exc_recVO exc_recVO=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_ER_NO);
			pstmt.setString(1, er_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				exc_recVO=new Exc_recVO();
				exc_recVO.setEr_no(rs.getString("er_no"));
				exc_recVO.setTp_no(rs.getString("tp_no"));
				exc_recVO.setMem_no(rs.getString("mem_no"));
				exc_recVO.setEr_time(rs.getTimestamp("er_time"));
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
		return exc_recVO;
	}

	@Override
	public List<Exc_recVO> getAll() {
		List<Exc_recVO> exc_recVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Exc_recVO exc_recVO=new Exc_recVO();

				exc_recVO.setEr_no(rs.getString("er_no"));
				exc_recVO.setTp_no(rs.getString("tp_no"));
				exc_recVO.setMem_no(rs.getString("mem_no"));
				exc_recVO.setEr_time(rs.getTimestamp("er_time"));
				
				exc_recVOList.add(exc_recVO);
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
		return exc_recVOList;
	}
	
	public static void main(String[] args){    //方法1~3可搭配方法5使用
		/* 1. 新增 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲新增的兌換記錄");
//		System.out.println("任務商品編號:");
//		String tp_no=sc.next();	
//		
//		System.out.println("一般會員編號:");
//		String mem_no=sc.next();
//		java.util.Date now=new java.util.Date();
//		Timestamp er_time=new Timestamp(now.getTime());
//		Exc_recVO exc_recVO=new Exc_recVO();
//		exc_recVO.setTp_no(tp_no);
//		exc_recVO.setMem_no(mem_no);
//		exc_recVO.setEr_time(er_time);
//		
//		Exc_recDAO dao=new Exc_recDAO();
//		dao.add(exc_recVO);
//		System.out.println("新增資料成功!!");
//		sc.close();
		
		/* 2. 修改 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲更新資料的(現有)兌換編號");
//		String er_no=sc.next();	
//	
//		System.out.println("請輸入新的任務商品編號");
//		String tp_no=sc.next();
//
//		System.out.println("請輸入新的一般會員編號");
//		String mem_no=sc.next();
//
//		Date now=new Date();
//		Timestamp er_time=new Timestamp(now.getTime());
//		Exc_recVO exc_recVO=new Exc_recVO();
//		exc_recVO.setEr_no(er_no);
//		exc_recVO.setTp_no(tp_no);
//		exc_recVO.setMem_no(mem_no);
//		exc_recVO.setEr_time(er_time);
//		
//		Exc_recDAO dao=new Exc_recDAO();
//		dao.update(exc_recVO);
//		
//		System.out.println("更新資料成功!!");
//		sc.close();

		/* 3. 刪除 */
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲刪除的兌換編號");
//		String er_no=sc.next();
//		
//		Exc_recDAO dao=new Exc_recDAO();
//		dao.delete(er_no);
//		System.out.println("刪除資料成功!!");
//		sc.close();
		
		/* 4. 以兌換編號查一個 */   
//		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲查詢的兌換編號");
//		String er_no=sc.next();
//		Exc_recDAO dao=new Exc_recDAO();
//		Exc_recVO exc_recVO=dao.findByEr_no(er_no);
//		System.out.println("1.兌換編號:"+exc_recVO.getEr_no());
//		System.out.println("2.任務商品編號:"+exc_recVO.getTp_no());
//		System.out.println("3.一般會員編號:"+exc_recVO.getMem_no());
//		System.out.println("4.兌換時間:"+df.format(exc_recVO.getEr_time()));
//		sc.close();
		
		/* 5. 查全部 */
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println("所有兌換記錄的資料為:");
		Exc_recDAO dao2=new Exc_recDAO();
		List<Exc_recVO> exc_recVOList=dao2.getAll();
		for(Exc_recVO exc_recVO2:exc_recVOList){
			System.out.println("1.兌換編號:"+exc_recVO2.getEr_no());
			System.out.println("2.任務商品編號:"+exc_recVO2.getTp_no());
			System.out.println("3.一般會員編號:"+exc_recVO2.getMem_no());
			System.out.println("4.兌換時間:"+df.format(exc_recVO2.getEr_time()));
			System.out.println("============================");
		}

		
		
	}
}
