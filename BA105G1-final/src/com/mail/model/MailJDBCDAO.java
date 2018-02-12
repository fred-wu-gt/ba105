package com.mail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MailJDBCDAO implements MailDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	//寄信-新增信件
	private static final String INSERT_STMT = "INSERT INTO MAIL(mail_no, mail_sender, mail_receiver, mail_title, mail_time, mail_cnt, mail_status)"
			+ "VALUES('MAIL'||LPAD(to_char(mail_no_seq.NEXTVAL), 6, '0'), ?, ?, ?, SYSTIMESTAMP, ?, ?)";
	//撈收信者所有收到的信件
	private static final String FIND_BY_MAIL_RECEIVER = "SELECT * FROM MAIL WHERE mail_receiver = ? ORDER BY MAIL_TIME DESC";
	//看一封信
	private static final String FIND_BY_MAIL_NO = "SELECT * FROM MAIL WHERE mail_no = ? AND mail_receiver = ?";
	
	@Override
	public void insert(MailVO mailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "mail_no" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, mailVO.getMail_sender());
			pstmt.setString(2, mailVO.getMail_receiver());
			pstmt.setString(3, mailVO.getMail_title());
			pstmt.setString(4, mailVO.getMail_cnt());
			pstmt.setString(5, mailVO.getMail_status());
			pstmt.executeUpdate();

			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
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
	public List<MailVO> findByMailReceiver(String mail_receiver) {
		List<MailVO> list = new ArrayList<>();
		MailVO mailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MAIL_RECEIVER);
			pstmt.setString(1, mail_receiver);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				mailVO = new MailVO();
				mailVO.setMail_no(rs.getString("mail_no"));
				mailVO.setMail_sender(rs.getString("mail_sender"));
				mailVO.setMail_receiver(rs.getString("mail_receiver"));
				mailVO.setMail_title(rs.getString("mail_title"));
				mailVO.setMail_time(rs.getTimestamp("mail_time"));
				mailVO.setMail_cnt(rs.getString("mail_cnt"));
				mailVO.setMail_status(rs.getString("mail_status"));
				list.add(mailVO);
			}
			
			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
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
		return list;
	}

	@Override
	public MailVO findByMailNo(String mail_no, String mail_receiver) {
		MailVO mailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MAIL_NO);
			pstmt.setString(1, mail_no);
			pstmt.setString(2, mail_receiver);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				mailVO = new MailVO();
				mailVO.setMail_no(rs.getString("mail_no"));
				mailVO.setMail_sender(rs.getString("mail_sender"));
				mailVO.setMail_receiver(rs.getString("mail_receiver"));
				mailVO.setMail_title(rs.getString("mail_title"));
				mailVO.setMail_time(rs.getTimestamp("mail_time"));
				mailVO.setMail_cnt(rs.getString("mail_cnt"));
				mailVO.setMail_status(rs.getString("mail_status"));
			}
			
			// Handle any DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database DRIVER. " + e.getMessage());
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
		return mailVO;
	}
	
	
}
