package com.commodity.controller;


import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/DBGifReader_v4_pic3.do")
public class DBGifReader_v4_pic3 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String com_no = req.getParameter("com_no");
			String i = req.getParameter("i");
			String com_no2 = new String(com_no.getBytes("ISO-8859-1"),"UTF-8");
			ResultSet rs = stmt.executeQuery(
				"SELECT com_pic"+i+" FROM COMMODITY WHERE COM_NO ='"+com_no2+"'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("COM_PIC3"));	 ////中文處理			 
				//getBinaryStream()回傳一個InputStream。其實也可以寫InputStream in = (rs.getBinaryStream("PICTURE"))
				//但是用BufferedInputStream效能較好 
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = req.getServletContext().getResourceAsStream("/NoData/nopic.jpg");
				byte[]buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = req.getServletContext().getResourceAsStream("/NoData/nopic.jpg");
			byte[] buf = new byte [in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
			
			
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
