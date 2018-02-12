package com.manager.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;

public class ManagerDAO implements ManagerDAO_Interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_INTO="insert into manager (man_no,man_id,man_pas,man_name,man_gen,man_tel,man_add,man_pho_base64,man_sta) values('MAN'||LPAD(to_char(manager_seq.NEXTVAL),7,'0'),?,?,?,?,?,?,?,?)";
	private static final String UPDATE="update manager set man_id=?, man_pas=?, man_name=?, man_gen=?, man_tel=?, man_add=?, man_pho_base64=?, man_sta=? where man_no=?";
	private static final String FIND_BY_MAN_NO="select * from manager where man_no=?";
	private static final String FIND_BY_MAN_ID="select * from manager where man_id=?";
	private static final String FIND_BY_MAN_STA="select * from manager where man_sta=?";
	private static final String GET_ALL="select * from manager order by man_no";

	@Override
	public String add(ManagerVO managerVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String man_no=null;
		try{
			con = ds.getConnection();
			String[] cols = {"man_no"};
			pstmt=con.prepareStatement(INSERT_INTO, cols);
			pstmt.setString(1, managerVO.getMan_id());
			pstmt.setString(2, managerVO.getMan_pas());
			pstmt.setString(3, managerVO.getMan_name());
			pstmt.setString(4, managerVO.getMan_gen());
			pstmt.setString(5, managerVO.getMan_tel());
			pstmt.setString(6, managerVO.getMan_add());
			pstmt.setString(7, managerVO.getMan_pho_base64());
			pstmt.setString(8, managerVO.getMan_sta());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				man_no = rs.getString(1);
			} System.out.println("新增成功管理員"+man_no);
			
			// Handle any SQL errors
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			throw new RuntimeException("帳號重覆!");
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
		return man_no;
	}

	@Override
	public void update(ManagerVO managerVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, managerVO.getMan_id());
			pstmt.setString(2, managerVO.getMan_pas());
			pstmt.setString(3, managerVO.getMan_name());
			pstmt.setString(4, managerVO.getMan_gen());
			pstmt.setString(5, managerVO.getMan_tel());
			pstmt.setString(6, managerVO.getMan_add());
			pstmt.setString(7, managerVO.getMan_pho_base64());
			pstmt.setString(8, managerVO.getMan_sta());
			pstmt.setString(9, managerVO.getMan_no());
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
	public ManagerVO findByMan_no(String man_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ManagerVO managerVO=null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(FIND_BY_MAN_NO);
			pstmt.setString(1, man_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				managerVO=new ManagerVO();
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_pas(rs.getString("man_pas"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_gen(rs.getString("man_gen"));
				managerVO.setMan_tel(rs.getString("man_tel"));
				managerVO.setMan_add(rs.getString("man_add"));
				managerVO.setMan_pho_base64(rs.getString("man_pho_base64"));
				managerVO.setMan_sta(rs.getString("man_sta"));
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
		return managerVO;
	}


	@Override
	public ManagerVO findByMan_id(String man_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ManagerVO managerVO=null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(FIND_BY_MAN_ID);
			pstmt.setString(1, man_id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				managerVO=new ManagerVO();
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_pas(rs.getString("man_pas"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_gen(rs.getString("man_gen"));
				managerVO.setMan_tel(rs.getString("man_tel"));
				managerVO.setMan_add(rs.getString("man_add"));
				managerVO.setMan_pho_base64(rs.getString("man_pho_base64"));
				managerVO.setMan_sta(rs.getString("man_sta"));
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
		return managerVO;
	}
	
	
	@Override
	public List<ManagerVO> findByMan_sta(String man_sta) {
		List<ManagerVO> managerVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(FIND_BY_MAN_STA);
			pstmt.setString(1, man_sta);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ManagerVO managerVO=new ManagerVO();
				
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_pas(rs.getString("man_pas"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_gen(rs.getString("man_gen"));
				managerVO.setMan_tel(rs.getString("man_tel"));
				managerVO.setMan_add(rs.getString("man_add"));
				managerVO.setMan_pho_base64(rs.getString("man_pho_base64"));
				managerVO.setMan_sta(rs.getString("man_sta"));
				
				managerVOList.add(managerVO);
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
		return managerVOList;
	}
	
	
	
	@Override
	public List<ManagerVO> getAll() {
		List<ManagerVO> managerVOList=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ManagerVO managerVO=new ManagerVO();
				
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_pas(rs.getString("man_pas"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_gen(rs.getString("man_gen"));
				managerVO.setMan_tel(rs.getString("man_tel"));
				managerVO.setMan_add(rs.getString("man_add"));
				managerVO.setMan_pho_base64(rs.getString("man_pho_base64"));
				managerVO.setMan_sta(rs.getString("man_sta"));
				
				managerVOList.add(managerVO);
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
		return managerVOList;
	}

}
