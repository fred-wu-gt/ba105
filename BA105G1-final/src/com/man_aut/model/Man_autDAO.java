package com.man_aut.model;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Man_autDAO implements Man_autDAO_Interface{
	private static final String INSERT_INTO="INSERT INTO manager_authority (mf_no,man_no) VALUES(?,?)";
	private static final String DELETE="DELETE FROM manager_authority WHERE mf_no=? AND  man_no=?";
	private static final String FIND_BY_MF_NO="SELECT * FROM manager_authority WHERE mf_no=? ORDER BY man_no";
	private static final String FIND_BY_MAN_NO="SELECT * FROM manager_authority WHERE man_no=? ORDER BY mf_no";
	private static final String GET_ALL="SELECT * FROM manager_authority ORDER BY man_no, mf_no";
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
	public void add(Man_autVO man_autVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=ds.getConnection();
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
			con=ds.getConnection();
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
			con=ds.getConnection();
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
			con=ds.getConnection();
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
			con=ds.getConnection();
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
}
