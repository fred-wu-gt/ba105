package utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class DBPicReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;    

    public DBPicReader() {
        super();
    }

	private void nopic(ServletOutputStream out) throws IOException{
		
		InputStream in = getServletContext().getResourceAsStream("/frontend/activity/images/nopic.jpg");
		byte[] buf = new byte[in.available()];
		in.read(buf);
		out.write(buf);
		in.close();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		res.setContentType("image/gif");
		req.setCharacterEncoding("big5"); 
		ServletOutputStream out = res.getOutputStream();
		
		String action = req.getParameter("action");    
		if(action!=null){
			try {
				String id_no = req.getParameter("id_no"); 
				String pk = new String (id_no.getBytes("ISO-8859-1"), "big5");
				String sql;
				System.out.println("action:"+action+", id_no:"+id_no);
				switch(action.toLowerCase()){
					case "shop":
						sql = "SELECT shop_photo FROM shop WHERE shop_no ='" + pk + "'";
						break;
					case "shop2":     //子怡新增
						sql = "SELECT shop_proof FROM shop WHERE shop_no ='" + pk + "'";
						break;
					case "ad":     //子怡新增
						sql = "SELECT ad_photo FROM advertisement WHERE ad_no ='" + pk + "'";
						break;
					case "member":
						sql = "SELECT mem_photo FROM member WHERE mem_no = '" + pk + "'";
						break;
					case "activity":
						sql = "SELECT activ_img FROM activity WHERE activ_id = '" + pk + "'";
						break;
					default:
						sql = "";
						break;
				}
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(1));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					nopic(out);
	//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				nopic(out);
			}			
		
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
