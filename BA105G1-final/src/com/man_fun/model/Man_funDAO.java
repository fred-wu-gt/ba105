package com.man_fun.model;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class Man_funDAO implements Man_funDAO_Interface{
	private static final String INSERT_INTO="insert into manager_function (mf_no,mf_name,mf_des) values('MF'||LPAD(to_char(manager_function_seq.nextval),8,'0'),?,?)";
	private static final String UPDATE="update manager_function set mf_name=?, mf_des=? where mf_no=?";
	private static final String DELETE="delete from manager_function where mf_no=?";
	private static final String FIND_BY_MF_NO="select * from manager_function where mf_no=?";
	private static final String GET_ALL="select * from manager_function order by mf_no";
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(Man_funVO man_funVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(INSERT_INTO);
			pstmt.setString(1, man_funVO.getMf_name());
			pstmt.setString(2, man_funVO.getMf_des());
			pstmt.executeUpdate();
		}catch (SQLException se) {
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
	public void update(Man_funVO man_funVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, man_funVO.getMf_name());
			pstmt.setString(2, man_funVO.getMf_des());
			pstmt.setString(3, man_funVO.getMf_no());
			pstmt.executeUpdate();
		}catch (SQLException se) {
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
	public void delete(String mf_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(DELETE);
			pstmt.setString(1, mf_no);
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
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
	public Man_funVO findByMf_no(String mf_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Man_funVO man_funVO=null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(FIND_BY_MF_NO);
			pstmt.setString(1, mf_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				man_funVO=new Man_funVO();
				man_funVO.setMf_no(rs.getString("mf_no"));
				man_funVO.setMf_name(rs.getString("mf_name"));
				man_funVO.setMf_des(rs.getString("mf_des"));
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
		return man_funVO;
	}
	
	@Override
	public List<Man_funVO> getAll() {
		List<Man_funVO> man_funVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Man_funVO man_funVO=new Man_funVO();
				
				man_funVO.setMf_no(rs.getString("mf_no"));
				man_funVO.setMf_name(rs.getString("mf_name"));
				man_funVO.setMf_des(rs.getString("mf_des"));
				man_funVOList.add(man_funVO);
			}// Handle any driver errors
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
		return man_funVOList;
	}
}
