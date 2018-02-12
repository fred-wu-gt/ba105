package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oreilly.servlet.Base64Encoder;



public class MemberDAO implements MemberDAO_Interface  {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	
	
	private static final String INSERT_STMT = "INSERT INTO MEMBER(MEM_NO,MEM_ID,MEM_PSW,MEM_NAME,MEM_GEN,MEM_BIR,MEM_EMAIL,MEM_PHONE,MEM_LOC,MEM_PHOTO,MEM_PHOTO_BASE64,MEM_STAT,MEM_POIN,MEM_VAL)"
			+ "VALUES(('MEM')||LPAD(TO_CHAR(MEMBER_SEQ.NEXTVAL),7,'0'), ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE MEMBER SET MEM_ID = ?, MEM_PSW = ?, MEM_NAME = ?, "
			+ "MEM_GEN = ?, MEM_BIR = ?, MEM_EMAIL = ? , MEM_PHONE = ? , MEM_LOC = ?,MEM_PHOTO = ?,MEM_PHOTO_BASE64 = ?" 
			+ ", MEM_STAT = ? , MEM_POIN = ? , MEM_VAL = ?  WHERE MEM_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM MEMBER WHERE MEM_ID = ?";
	private static final String FIND_BY_DEPTNO = "SELECT * FROM MEMBER WHERE MEM_ID = ?";
	private static final String FIND_BY_MEMNO = "SELECT * FROM MEMBER WHERE MEM_NO = ?";
	
	private static final String GET_ALL = "SELECT * FROM MEMBER";
	
	
	
	
	
	//=============宸鈞開始=====================

		//修改會員點數的方法
		 
		private static final String UPDATE_MEM_VAL = "UPDATE MEMBER SET MEM_VAL = ?  WHERE MEM_ID = ?";
		
		@Override
		public void updateMem_val(MemberVO memVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATE_MEM_VAL);
		
				pstmt.setString(1, memVO.getMem_val());
				pstmt.setString(2, memVO.getMem_id());
				
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
		
		//=============宸鈞結束=======================
	
	
	@Override
	public void add(MemberVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMem_id());
			pstmt.setString(2, memVO.getMem_psw());
			pstmt.setString(3, memVO.getMem_name());
			pstmt.setString(4, memVO.getMem_gen());
			pstmt.setDate(5, memVO.getMem_bir());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_phone());
			pstmt.setString(8, memVO.getMem_loc());
			pstmt.setBytes(9, memVO.getMem_photo());
			pstmt.setString(10, memVO.getMem_photo_base64());
			pstmt.setString(11, memVO.getMem_stat());
			pstmt.setString(12, memVO.getMem_poin());
			pstmt.setString(13, memVO.getMem_val());
		
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
	public void update(MemberVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, memVO.getMem_id());
			pstmt.setString(2, memVO.getMem_psw());
			pstmt.setString(3, memVO.getMem_name());
			pstmt.setString(4, memVO.getMem_gen());
			pstmt.setDate(5, memVO.getMem_bir());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_phone());
			pstmt.setString(8, memVO.getMem_loc());
			pstmt.setBytes(9, memVO.getMem_photo());
			pstmt.setString(10, memVO.getMem_photo_base64());
			pstmt.setString(11, memVO.getMem_stat());
			pstmt.setString(12, memVO.getMem_poin());
			pstmt.setString(13, memVO.getMem_val());
			pstmt.setString(14, memVO.getMem_no());
			
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
	public void delete(String mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, mem_id);
			
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
	public MemberVO findByMem_id(String mem_id)   {
		MemberVO mem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_DEPTNO);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				mem = new MemberVO();
				mem.setMem_no(rs.getString("MEM_NO"));
				mem.setMem_id(rs.getString("MEM_ID"));
				mem.setMem_psw(rs.getString("MEM_PSW"));
				mem.setMem_name(rs.getString("MEM_NAME"));
				mem.setMem_gen(rs.getString("MEM_GEN"));
				mem.setMem_bir(rs.getDate("MEM_BIR"));
				mem.setMem_email(rs.getString("MEM_EMAIL"));
				mem.setMem_phone(rs.getString("MEM_PHONE"));
				mem.setMem_loc(rs.getString("MEM_LOC"));
				mem.setMem_photo(rs.getBytes("MEM_PHOTO"));
				mem.setMem_photo_base64(rs.getString("MEM_PHOTO_BASE64"));
				mem.setMem_stat(rs.getString("MEM_STAT"));
				mem.setMem_poin(rs.getString("MEM_POIN"));
				mem.setMem_val(rs.getString("MEM_VAL"));
				
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

		return mem;
		
		
		
		
	}
	
	

	@Override
	public MemberVO findByMem_no(String mem_no) {
		MemberVO mem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEMNO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				mem = new MemberVO();
				mem.setMem_no(rs.getString("MEM_NO"));
				mem.setMem_id(rs.getString("MEM_ID"));
				mem.setMem_psw(rs.getString("MEM_PSW"));
				mem.setMem_name(rs.getString("MEM_NAME"));
				mem.setMem_gen(rs.getString("MEM_GEN"));
				mem.setMem_bir(rs.getDate("MEM_BIR"));
				mem.setMem_email(rs.getString("MEM_EMAIL"));
				mem.setMem_phone(rs.getString("MEM_PHONE"));
				mem.setMem_loc(rs.getString("MEM_LOC"));
				mem.setMem_photo(rs.getBytes("MEM_PHOTO"));
				mem.setMem_photo_base64(rs.getString("MEM_PHOTO_BASE64"));
				mem.setMem_stat(rs.getString("MEM_STAT"));
				mem.setMem_poin(rs.getString("MEM_POIN"));
				mem.setMem_val(rs.getString("MEM_VAL"));
				
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
		
		return mem;
		
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> memList = new ArrayList<>();
		MemberVO mem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mem = new MemberVO();
				mem.setMem_no(rs.getString("MEM_NO"));
				mem.setMem_id(rs.getString("MEM_ID"));
				mem.setMem_psw(rs.getString("MEM_PSW"));
				mem.setMem_name(rs.getString("MEM_NAME"));
				mem.setMem_gen(rs.getString("MEM_GEN"));
				mem.setMem_bir(rs.getDate("MEM_BIR"));
				mem.setMem_email(rs.getString("MEM_EMAIL"));
				mem.setMem_phone(rs.getString("MEM_PHONE"));
				mem.setMem_loc(rs.getString("MEM_LOC"));
				mem.setMem_photo(rs.getBytes("MEM_PHOTO"));
				mem.setMem_photo_base64(rs.getString("MEM_PHOTO_BASE64"));
				mem.setMem_stat(rs.getString("MEM_STAT"));
				mem.setMem_poin(rs.getString("MEM_POIN"));
				mem.setMem_val(rs.getString("MEM_VAL"));
				memList.add(mem);
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
		return memList;
	
	}

	
	
	
}
