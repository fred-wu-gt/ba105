package com.shop.model;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBGifReader1 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		System.out.println("DBGIF0");
		req.setCharacterEncoding("UTF-8");//��
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		System.out.println("DBGIF1");
		try {
			Statement stmt = con.createStatement();
			String shop_no = req.getParameter("shop_no");//��
			String shopCol = req.getParameter("shopCol");//��
			String shop_no2 = new String(shop_no.getBytes("ISO8859-1"),"UTF-8");//�A
			String shopCol2 = new String(shopCol.getBytes("ISO8859-1"),"UTF-8");//�A
			System.out.println("DBGIF2");
			ResultSet rs = stmt.executeQuery(
				"SELECT "+shopCol2+" FROM SHOP WHERE SHOP_NO ='"+shop_no2+"'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(shopCol2));
				byte[] buf = new byte[5 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
					System.out.println("DBGIF3");
				}
				in.close();
			} else {
				System.out.println("DBGIF4");
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/nopic.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
				
			}
		
			
			rs.close();
			System.out.println("DBGIF5");
			stmt.close();
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/nopic.jpg");
			byte[] buf = new byte[in.available()];
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
