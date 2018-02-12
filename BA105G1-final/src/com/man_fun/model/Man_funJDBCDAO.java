package com.man_fun.model;
import java.util.*;
import java.sql.*;

public class Man_funJDBCDAO implements Man_funDAO_Interface{
	private static final String MY_URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="FRUIT";
	private static final String PASSWORD="FRUIT";
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String INSERT_INTO="insert into manager_function (mf_no,mf_name,mf_des) values('MF'||LPAD(to_char(manager_function_seq.nextval),8,'0'),?,?)";
	private static final String UPDATE="update manager_function set mf_name=?, mf_des=? where mf_no=?";
	private static final String DELETE="delete from manager_function where mf_no=?";
	private static final String FIND_BY_MF_NO="select * from manager_function where mf_no=?";
	private static final String GET_ALL="select * from manager_function order by mf_no";
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}

	@Override
	public void add(Man_funVO man_funVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, man_funVO.getMf_name());
			pstmt.setString(2, man_funVO.getMf_des());
			pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(Man_funVO man_funVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, man_funVO.getMf_name());
			pstmt.setString(2, man_funVO.getMf_des());
			pstmt.setString(3, man_funVO.getMf_no());
			pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String mf_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(DELETE);
			pstmt.setString(1, mf_no);
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		}
		
		
	}

	@Override
	public Man_funVO findByMf_no(String mf_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Man_funVO man_funVO=null;
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(FIND_BY_MF_NO);
			pstmt.setString(1, mf_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				man_funVO=new Man_funVO();
				man_funVO.setMf_no(rs.getString("mf_no"));
				man_funVO.setMf_name(rs.getString("mf_name"));
				man_funVO.setMf_des(rs.getString("mf_des"));
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		}
		return man_funVO;
	}

	@Override
	public List<Man_funVO> getAll() {
		List<Man_funVO> man_funVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=DriverManager.getConnection(MY_URL,USER,PASSWORD);
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Man_funVO man_funVO=new Man_funVO();
				
				man_funVO.setMf_no(rs.getString("mf_no"));
				man_funVO.setMf_name(rs.getString("mf_name"));
				man_funVO.setMf_des(rs.getString("mf_des"));
				man_funVOList.add(man_funVO);
			}
			
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
		}
		return man_funVOList;
	}
	
	public static void main(String[] args){    //方法1~3可搭配方法5使用
		/* 1. 新增 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲新增的管理員功能");
//		System.out.println("功能名稱:");
//		String mf_name=sc.next();		
//		
//		System.out.println("功能敘述:");
//		String mf_des=sc.next();
//
//		Man_funVO man_funVO=new Man_funVO();
//		man_funVO.setMf_name(mf_name);
//		man_funVO.setMf_des(mf_des);
//		
//		Man_funJDBCDAO dao=new Man_funJDBCDAO();
//		dao.add(man_funVO);
//		System.out.println("新增資料成功!!");
//		sc.close();
		
		/* 2. 修改 */
//		Scanner sc=new Scanner(System.in);		
//		System.out.println("請輸入欲更新資料的(現有)功能編號");
//		String mf_no=sc.next();
//		
//		System.out.println("請輸入新的功能名稱");
//		String mf_name=sc.next();
//
//		System.out.println("請輸入新的功能敘述");
//		String mf_des=sc.next();
//
//		Man_funVO man_funVO=new Man_funVO();
//		man_funVO.setMf_no(mf_no);
//		man_funVO.setMf_name(mf_name);
//		man_funVO.setMf_des(mf_des);
//		
//		Man_funJDBCDAO dao=new Man_funJDBCDAO();
//		dao.update(man_funVO);
//		
//		System.out.println("更新資料成功!!");
//		sc.close();
		
		
		/* 3. 刪除 */
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲刪除的功能編號");
//		String mf_no=sc.next();
//		
//		Man_funJDBCDAO dao=new Man_funJDBCDAO();
//		dao.delete(mf_no);
//		System.out.println("刪除資料成功!!");
//		sc.close();
		
		/* 4. 以功能編號查一個 */   
//		Scanner sc=new Scanner(System.in);
//		System.out.println("請輸入欲查詢的功能編號");
//		String mf_no=sc.next();
//		Man_funJDBCDAO dao=new Man_funJDBCDAO();
//		Man_funVO man_funVO=dao.findByMf_no(mf_no);
//		System.out.println("1.功能編號:"+man_funVO.getMf_no());
//		System.out.println("2.功能名稱:"+man_funVO.getMf_name());
//		System.out.println("3.功能敘述:"+man_funVO.getMf_des());
//		sc.close();
		
		/* 5. 查全部 */
		System.out.println("所有管理員功能的資料為:");
		Man_funJDBCDAO dao2=new Man_funJDBCDAO();
		List<Man_funVO> man_funVOList=dao2.getAll();
		for(Man_funVO man_funVO2:man_funVOList){
			System.out.println("1.功能編號:"+man_funVO2.getMf_no());
			System.out.println("2.功能名稱:"+man_funVO2.getMf_name());
			System.out.println("3.功能敘述:"+man_funVO2.getMf_des());
			System.out.println("============================");
		}

		
		
	}
}
