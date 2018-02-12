package com.mem_ser_mail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Mem_ser_mailDAO implements Mem_ser_mailDAO_interface {
	private static DataSource ds = null;
	//使用JNDI技術尋找及存取DataSource
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//新增信件
		private static final String INSERT_STMT = "INSERT INTO MEMBER_SERVICE_MAIL(mail_no, mem_no, man_no, mail_title, mail_sender, mail_time, mail_cnt)"
				+ "VALUES('MAIL'||LPAD(to_char(memser_mail_no_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, SYSTIMESTAMP, ?)";
		
		@Override
		public void insert(Mem_ser_mailVO mem_ser_mailVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				String[] cols = { "mail_no" };
				pstmt = con.prepareStatement(INSERT_STMT, cols);
				pstmt.setString(1, mem_ser_mailVO.getMem_no());
				pstmt.setString(2, mem_ser_mailVO.getMan_no());
				pstmt.setString(3, mem_ser_mailVO.getMail_title());
				pstmt.setString(4, mem_ser_mailVO.getMail_sender());
				pstmt.setString(5, mem_ser_mailVO.getMail_cnt());
				pstmt.executeUpdate();

				// Handle any DRIVER errors
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
}
