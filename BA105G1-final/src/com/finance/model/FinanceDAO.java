package com.finance.model;
import com.man_fun.model.Man_funVO;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class FinanceDAO implements FinanceDAO_Interface{
	private static final String MY_URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="FRUIT";
	private static final String PASSWORD="FRUIT";
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_INTO="insert into Finance (fin_no,shop_no,fin_cosum,fin_adsum,fin_year,fin_mon) values('FIN'||LPAD(to_char(Finance_seq.nextval),7,'0'),?,?,?,?,?)";
	private static final String UPDATE="update Finance set shop_no=?, fin_cosum=?, fin_adsum=?, fin_year=?, fin_mon=? where fin_no=?";
	private static final String DELETE="delete from Finance where fin_no=?";
	private static final String FIND_BY_FIN_NO="select * from Finance where fin_no=?";
	private static final String GET_ALL="select * from Finance order by fin_no";
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}

	@Override
	public void add(FinanceVO financeVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, financeVO.getShop_no());
			pstmt.setInt(2, financeVO.getFin_cosum());
			pstmt.setInt(3, financeVO.getFin_adsum());
			pstmt.setInt(4, financeVO.getFin_year());
			pstmt.setInt(5, financeVO.getFin_mon());
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
	public void update(FinanceVO financeVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, financeVO.getShop_no());
			pstmt.setInt(2, financeVO.getFin_cosum());
			pstmt.setInt(3, financeVO.getFin_adsum());
			pstmt.setInt(4, financeVO.getFin_year());
			pstmt.setInt(5, financeVO.getFin_mon());
			pstmt.setString(6, financeVO.getFin_no());
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
	public void delete(String fin_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE);
			pstmt.setString(1, fin_no);
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
	public FinanceVO findByFin_no(String fin_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		FinanceVO financeVO=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_FIN_NO);
			pstmt.setString(1, fin_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				financeVO=new FinanceVO();
				financeVO.setFin_no(rs.getString("fin_no"));
				financeVO.setShop_no(rs.getString("shop_no"));
				financeVO.setFin_cosum(rs.getInt("fin_cosum"));
				financeVO.setFin_adsum(rs.getInt("fin_adsum"));
				financeVO.setFin_year(rs.getInt("fin_year"));
				financeVO.setFin_mon(rs.getInt("fin_mon"));
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
		return financeVO;
	}

	@Override
	public List<FinanceVO> getAll() {
		List<FinanceVO> financeVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				FinanceVO financeVO=new FinanceVO();
				
				financeVO.setFin_no(rs.getString("fin_no"));
				financeVO.setShop_no(rs.getString("shop_no"));
				financeVO.setFin_cosum(rs.getInt("fin_cosum"));
				financeVO.setFin_adsum(rs.getInt("fin_adsum"));
				financeVO.setFin_year(rs.getInt("fin_year"));
				financeVO.setFin_mon(rs.getInt("fin_mon"));
				
				financeVOList.add(financeVO);
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
		return financeVOList;
	}
	
	public static void main(String[] args) {    //方法1~3可搭配方法5使用
		/* 1. 新增 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲新增的帳務資訊");	
//		
//		System.out.println("商家編號:");
//		String shop_no=sc.next();
//
//		System.out.println("月結商品銷售額:");
//		Integer fin_cosum=sc.nextInt();
//		
//		System.out.println("月成交廣告費:");
//		Integer fin_adsum=sc.nextInt();
//		
//		System.out.println("年:");
//		Integer fin_year=sc.nextInt();
//		
//		System.out.println("月:");
//		Integer fin_mon=sc.nextInt();
//
//		FinanceVO financeVO=new FinanceVO();
//		financeVO.setShop_no(shop_no);
//		financeVO.setFin_cosum(fin_cosum);
//		financeVO.setFin_adsum(fin_adsum);
//		financeVO.setFin_year(fin_year);
//		financeVO.setFin_mon(fin_mon);
//		
//		FinanceDAO dao=new FinanceDAO();
//		dao.add(financeVO);
//		System.out.println("新增資料成功!!");
//		sc.close();
		
		/* 2. 修改 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲更新資料的(現有)帳務編號");
//		String fin_no=sc.next();
//		
//		System.out.println("請輸入新的商家編號:");
//		String shop_no=sc.next();
//
//		System.out.println("請輸入新的月結商品銷售額:");
//		Integer fin_cosum=sc.nextInt();
//		
//		System.out.println("請輸入新的月成交廣告費:");
//		Integer fin_adsum=sc.nextInt();
//		
//		System.out.println("請輸入新的年:");
//		Integer fin_year=sc.nextInt();
//		
//		System.out.println("請輸入新的月:");
//		Integer fin_mon=sc.nextInt();
//		
//		FinanceVO financeVO=new FinanceVO();
//		financeVO.setFin_no(fin_no);
//		financeVO.setShop_no(shop_no);
//		financeVO.setFin_cosum(fin_cosum);
//		financeVO.setFin_adsum(fin_adsum);
//		financeVO.setFin_year(fin_year);
//		financeVO.setFin_mon(fin_mon);
//		
//		FinanceDAO dao=new FinanceDAO();
//		dao.update(financeVO);
//		
//		System.out.println("更新資料成功!!");
//		sc.close();
		
		
		/* 3. 刪除 */
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲刪除的帳務編號");
//		String fin_no=sc.next();
//		
//		FinanceDAO dao=new FinanceDAO();
//		dao.delete(fin_no);
//		System.out.println("刪除資料成功!!");
//		sc.close();
		
		/* 4. 以帳務編號查一個 */   
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲查詢的帳務編號");
//		String fin_no=sc.next();
//		FinanceDAO dao=new FinanceDAO();
//		FinanceVO financeVO=dao.findByFin_no(fin_no);
//		System.out.println("1.帳務編號:"+financeVO.getFin_no());
//		System.out.println("2.商家編號:"+financeVO.getShop_no());
//		System.out.println("3.月結商品銷售額:"+financeVO.getFin_cosum());
//		System.out.println("4.月成交廣告費:"+financeVO.getFin_adsum());
//		System.out.println("5.年:"+financeVO.getFin_year());
//		System.out.println("6.月:"+financeVO.getFin_mon());
//		sc.close();
		
		/* 5. 查全部 */
		System.out.println("所有帳務編號的資料為:");
		FinanceDAO dao2=new FinanceDAO();
		List<FinanceVO> financeVOList=dao2.getAll();
		for(FinanceVO financeVO2:financeVOList){
			System.out.println("1.帳務編號:"+financeVO2.getFin_no());
			System.out.println("2.商家編號:"+financeVO2.getShop_no());
			System.out.println("3.月結商品銷售額:"+financeVO2.getFin_cosum());
			System.out.println("4.月成交廣告費:"+financeVO2.getFin_adsum());
			System.out.println("5.年:"+financeVO2.getFin_year());
			System.out.println("6.月:"+financeVO2.getFin_mon());
			System.out.println("============================");
		}

		
		
	}
}
