package com.writing.model;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBGifReader4 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		
		try {
			String wrt_no = req.getParameter("wrt_no");
			String wrt_no2 = new String(wrt_no.getBytes("ISO-8859-1"),"UTF-8");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
				"SELECT WRT_PHOTO FROM WRITING WHERE WRT_NO = '"+wrt_no2+"'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("WRT_PHOTO"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/nopic.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/NoData/nopic.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
	      try {
			Context ctx = new javax.naming.InitialContext();
			  DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
			   con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
