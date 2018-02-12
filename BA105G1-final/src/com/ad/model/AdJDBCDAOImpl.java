package com.ad.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.ShopVO;

public class AdJDBCDAOImpl implements AdDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "FRUIT";
	String password = "FRUIT";

	private static final String INSERT_STMT = "INSERT INTO ADVERTISEMENT (AD_NO,SHOP_NO,AD_IDESTA,AD_START,AD_END,AD_EXP,AD_STAT,AD_PHOTO)"
			+ "VALUES('AD'||LPAD(TO_CHAR(AD_NO_SEQUENCES.NEXTVAL),8,'0'),?,?,SYSTIMESTAMP,?,?,'下架',?)";
	private static final String GET_ALL_STMT = "SELECT AD_NO,SHOP_NO,AD_IDESTA,AD_START,AD_END,AD_EXP,AD_STAT,AD_PHOTO FROM ADVERTISEMENT ORDER BY AD_NO";
	private static final String GET_ONE_STMT = "SELECT AD_NO,SHOP_NO,AD_IDESTA,AD_START,AD_END,AD_EXP,AD_STAT,AD_PHOTO FROM ADVERTISEMENT WHERE AD_NO = ?";
	private static final String DELETE = "DELETE FROM ADVERTISEMENT WHERE AD_NO = ?";
	private static final String UPDATE = "UPDATE ADVERTISEMENT SET SHOP_NO=?,AD_IDESTA=?,AD_START=SYSTIMESTAMP,AD_END=?,AD_EXP=?,AD_STAT='上架',AD_PHOTO =? WHERE AD_NO =?";

	@Override
	public void insert(AdVO adVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adVo.getShop_no());
			pstmt.setTimestamp(2, adVo.getAd_idesta());
			pstmt.setTimestamp(3, adVo.getAd_end());
			pstmt.setInt(4, adVo.getAd_exp());
			pstmt.setBytes(5, adVo.getAd_photo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(AdVO adVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adVo.getShop_no());
			pstmt.setTimestamp(2, adVo.getAd_idesta());
			pstmt.setTimestamp(3, adVo.getAd_end());
			pstmt.setInt(4, adVo.getAd_exp());
			pstmt.setBytes(5, adVo.getAd_photo());
			pstmt.setString(6, adVo.getAd_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String ad_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ad_no);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public AdVO findByPrimaryKey(String ad_no) {

		AdVO adVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ad_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				adVo = new AdVO();
				adVo.setAd_no(rs.getString("AD_NO"));
				adVo.setShop_no(rs.getString("SHOP_NO"));
				adVo.setAd_idesta(rs.getTimestamp("AD_IDESTA"));
				adVo.setAd_start(rs.getTimestamp("AD_START"));
				adVo.setAd_end(rs.getTimestamp("AD_END"));
				adVo.setAd_exp(rs.getInt("AD_EXP"));
				adVo.setAd_stat(rs.getString("AD_STAT"));
				adVo.setAd_photo(rs.getBytes("AD_PHOTO"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return adVo;
	}

	@Override
	public List<AdVO> getAll() {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				adVo = new AdVO();
				adVo.setAd_no(rs.getString("AD_NO"));
				adVo.setShop_no(rs.getString("SHOP_NO"));
				adVo.setAd_idesta(rs.getTimestamp("AD_IDESTA"));
				adVo.setAd_start(rs.getTimestamp("AD_START"));
				adVo.setAd_end(rs.getTimestamp("AD_END"));
				adVo.setAd_exp(rs.getInt("AD_EXP"));
				adVo.setAd_stat(rs.getString("AD_STAT"));
				adVo.setAd_photo(rs.getBytes("AD_PHOTO"));

				list.add(adVo);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	

	
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public List<AdVO> findByAd_stat(String ad_stat) {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement("SELECT * FROM ADVERTISEMENT WHERE AD_STAT =? ORDER BY AD_NO desc");
			pstmt.setString(1, ad_stat);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				adVO = new AdVO();
				adVO.setAd_no(rs.getString("AD_NO"));
				adVO.setShop_no(rs.getString("SHOP_NO"));
				adVO.setAd_idesta(rs.getTimestamp("AD_IDESTA"));
				adVO.setAd_start(rs.getTimestamp("AD_START"));
				adVO.setAd_end(rs.getTimestamp("AD_END"));
				adVO.setAd_exp(rs.getInt("AD_EXP"));
				adVO.setAd_stat(rs.getString("AD_STAT"));
				adVO.setAd_photo(rs.getBytes("AD_PHOTO"));

				list.add(adVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void updateAd_stat(AdVO adVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement("UPDATE ADVERTISEMENT SET AD_START=?,AD_EXP=?,AD_STAT=? WHERE AD_NO =?");

			pstmt.setTimestamp(1, adVO.getAd_start());
			pstmt.setInt(2, adVO.getAd_exp());
			pstmt.setString(3, adVO.getAd_stat());
			pstmt.setString(4, adVO.getAd_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ==================main=======================================================

	public static void main(String[] args) {

		// insert申請打廣告
		AdJDBCDAOImpl dao = new AdJDBCDAOImpl();
		//
		 AdVO adVo = new AdVO();
		 adVo.setShop_no("SHOP000010");
		 adVo.setAd_idesta(java.sql.Timestamp.valueOf("2018-03-15 09:30:00.0"));
		// adVo.setAd_start(java.sql.Timestamp.valueOf("2018-03-18 09:30:00"));
//		 後臺審核後的時間
		 adVo.setAd_end(java.sql.Timestamp.valueOf("2018-03-30 09:30:00.0"));
		 adVo.setAd_exp(30000);
		
		 try {
		 byte[] pic = getPictureByteArray("fakeImage/ADVERTISEMENT-AD_PHOTO/10.jpg");
		 adVo.setAd_photo(pic);
		 System.out.println(pic.length);
		
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 dao.insert(adVo);
		 System.out.println("good");
		

		// =========================================================================
		// delete刪除
		// dao.delete("AD00000017");
		// System.out.println("成功");

		// =========================================================================

		// 修改update

//		AdVO adVoUpdate = new AdVO();
//		adVoUpdate.setShop_no("SHOP000001");
//		adVoUpdate.setAd_idesta(java.sql.Timestamp.valueOf("2018-03-15 09:30:00"));
//		adVoUpdate.setAd_end(java.sql.Timestamp.valueOf("2018-03-30 09:30:00"));
//		adVoUpdate.setAd_exp(3000000);
//		// adVoUpdate.setAd_start(java.sql.Timestamp.valueOf("2018-03-18 09:30:00"));
//		// 後臺審核後的時間
//		byte[] pic;
//		try {
//			pic = getPictureByteArray("fakeImage/ADVERTISEMENT-AD_PHOTO/8.jpg");
//			adVoUpdate.setAd_photo(pic);
//			adVoUpdate.setAd_no("AD00000001");
//			System.out.println(pic.length);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		dao.update(adVoUpdate);
//		System.out.println("good");

		// =========================================================================
		// 查一個

//		AdVO adVoSelect = dao.findByPrimaryKey("AD00000001");
//
//		System.out.println("廣告編號:" + adVoSelect.getAd_no());
//		System.out.println("商家編號:" + adVoSelect.getShop_no());
//		System.out.println("理想開始:" + adVoSelect.getAd_idesta());
//		System.out.println("審核開始:" + adVoSelect.getAd_start());
//		System.out.println("結束時間:" + adVoSelect.getAd_end());
//		System.out.println("廣告狀態:" + adVoSelect.getAd_stat());
//		System.out.println("廣告照片:" + adVoSelect.getAd_photo().length);
//
//		try {
//			readPicture(adVoSelect.getAd_photo());
//			System.out.println("success");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// =========================================================================
		// 查全部

		List<AdVO> list = dao.getAll();
		for (AdVO adAll : list) {

			System.out.println("廣告編號:" + adAll.getAd_no());
			System.out.println("商家編號:" + adAll.getShop_no());
			System.out.println("理想開始:" + adAll.getAd_idesta());
			System.out.println("審核開始:" + adAll.getAd_start());
			System.out.println("結束時間:" + adAll.getAd_end());
			System.out.println("廣告金額:" + adAll.getAd_exp());
			System.out.println("廣告狀態:" + adAll.getAd_stat());
			System.out.println("廣告照片:" + adAll.getAd_photo().length);

			try {
				readPicture(adAll.getAd_photo());
				System.out.println("success");
			} catch (IOException e) {
				e.printStackTrace();

			}

		}
		
	}//main結束
	
	
	
//======================================================================
	
	
	
	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	// Handle with byte array data
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("fakeImage/ADVERTISEMENT-AD_PHOTO/1.png");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

}