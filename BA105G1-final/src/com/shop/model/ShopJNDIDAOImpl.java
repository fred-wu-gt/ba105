package com.shop.model;

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
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.commodity.model.CommodityVO;

public class ShopJNDIDAOImpl implements ShopDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FRUIT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO SHOP (SHOP_NO,SHOP_NAME,SHOP_ID,SHOP_PSW,SHOP_EMAIL,SHOP_PHONE,SHOP_LOC,SHOP_LAT,SHOP_LON,SHOP_DESC,SHOP_VAL,SHOP_COSUM,SHOP_ADSUM,SHOP_BANK,SHOP_ACC,SHOP_VIS,SHOP_STAT,SHOP_POINT,SHOP_SCORE,SHOP_PROOF,SHOP_PHOTO)"
			+ "VALUES('SHOP'||LPAD(TO_CHAR(SHOP_NO_SEQUENCES.NEXTVAL),6,'0'),?,?,?,?,?,?,?,?,?,0,0,0,?,?,'上架','待審核"
			+ "',0,0,?,?)";
	private static final String GET_ALL_STMT = "SELECT SHOP_NO,SHOP_NAME,SHOP_ID,SHOP_PSW,SHOP_EMAIL,SHOP_PHONE,SHOP_LOC,SHOP_LAT,SHOP_LON,SHOP_DESC,SHOP_VAL,SHOP_COSUM,SHOP_ADSUM,SHOP_BANK,SHOP_ACC,SHOP_VIS,SHOP_STAT,SHOP_POINT,SHOP_SCORE,SHOP_PROOF,SHOP_PHOTO FROM SHOP ORDER BY SHOP_NO";
	private static final String GET_ONE_STMT = "SELECT SHOP_NO,SHOP_NAME,SHOP_ID,SHOP_PSW,SHOP_EMAIL,SHOP_PHONE,SHOP_LOC,SHOP_LAT,SHOP_LON,SHOP_DESC,SHOP_VAL,SHOP_COSUM,SHOP_ADSUM,SHOP_BANK,SHOP_ACC,SHOP_VIS,SHOP_STAT,SHOP_POINT,SHOP_SCORE,SHOP_PROOF,SHOP_PHOTO FROM SHOP WHERE SHOP_NO = ?";
	private static final String DELETE = "DELETE FROM SHOP WHERE SHOP_NO = ?";
	private static final String UPDATE = "UPDATE SHOP SET SHOP_NAME=?, SHOP_ID=?, SHOP_PSW=?,SHOP_EMAIL=?, SHOP_PHONE=?, SHOP_LOC=?, SHOP_LAT=121.191623, SHOP_LON=249.67880, SHOP_DESC=? , SHOP_VAL=0, SHOP_COSUM=0, SHOP_ADSUM=0, SHOP_BANK=?, SHOP_ACC=?, SHOP_VIS='上架', SHOP_STAT='審核中', SHOP_POINT=1, SHOP_SCORE=2.5, SHOP_PROOF=?, SHOP_PHOTO=? WHERE SHOP_NO=?";
	private static final String LOGIN = "SELECT * FROM SHOP WHERE SHOP_ID=? AND SHOP_PSW=?";
	private static final String REGISTEREDID_STMT = "SELECT * FROM SHOP WHERE SHOP_ID=?";
	private static final String GET_ONE = "SELECT * FROM COMMODITY WHERE COM_NO  = ?";
	
	
	@Override
	public void insert(ShopVO shopVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, shopVO.getShop_name());
			pstmt.setString(2, shopVO.getShop_id());
			pstmt.setString(3, shopVO.getShop_psw());
			pstmt.setString(4, shopVO.getShop_email());
			pstmt.setString(5, shopVO.getShop_phone());
			pstmt.setString(6, shopVO.getShop_loc());
			
			//
			pstmt.setDouble(7, shopVO.getShop_lat());
			
			pstmt.setDouble(8, shopVO.getShop_lon());
			//
			System.out.println("yeses");
			pstmt.setString(9, shopVO.getShop_desc());
			pstmt.setString(10, shopVO.getShop_bank());
			pstmt.setString(11, shopVO.getShop_acc());
			pstmt.setBytes(12, shopVO.getShop_proof());
			pstmt.setBytes(13, shopVO.getShop_photo());

			pstmt.executeUpdate();

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
	public void update(ShopVO shopVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, shopVO.getShop_name());
			pstmt.setString(2, shopVO.getShop_id());
			pstmt.setString(3, shopVO.getShop_psw());
			pstmt.setString(4, shopVO.getShop_email());
			pstmt.setString(5, shopVO.getShop_phone());
			pstmt.setString(6, shopVO.getShop_loc());
			pstmt.setString(7, shopVO.getShop_desc());
			pstmt.setString(8, shopVO.getShop_bank());
			pstmt.setString(9, shopVO.getShop_acc());
			pstmt.setBytes(10, shopVO.getShop_proof());
			pstmt.setBytes(11, shopVO.getShop_photo());
			pstmt.setString(12, shopVO.getShop_no());

			pstmt.executeUpdate();
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
	public void delete(String shop_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, shop_no);
			pstmt.executeUpdate();

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
	public ShopVO findByPrimaryKey(String shop_no) {
		ShopVO shopVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, shop_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				shopVo = new ShopVO();
				shopVo.setShop_no(rs.getString("SHOP_NO"));
				shopVo.setShop_name(rs.getString("SHOP_NAME"));
				shopVo.setShop_id(rs.getString("SHOP_ID"));
				shopVo.setShop_psw(rs.getString("SHOP_PSW"));
				shopVo.setShop_email(rs.getString("SHOP_EMAIL"));
				shopVo.setShop_phone(rs.getString("SHOP_PHONE"));
				shopVo.setShop_loc(rs.getString("SHOP_LOC"));
				shopVo.setShop_lat(rs.getDouble("SHOP_LAT"));
				shopVo.setShop_lon(rs.getDouble("SHOP_LON"));
				shopVo.setShop_desc(rs.getString("SHOP_DESC"));
				shopVo.setShop_val(rs.getInt("SHOP_VAL"));
				shopVo.setShop_cosum(rs.getInt("SHOP_COSUM"));
				shopVo.setShop_adsum(rs.getInt("SHOP_ADSUM"));
				shopVo.setShop_bank(rs.getString("SHOP_BANK"));
				shopVo.setShop_acc(rs.getString("SHOP_ACC"));
				shopVo.setShop_vis(rs.getString("SHOP_VIS"));
				shopVo.setShop_stat(rs.getString("SHOP_STAT"));
				shopVo.setShop_point(rs.getInt("SHOP_POINT"));
				shopVo.setShop_score(rs.getDouble("SHOP_SCORE"));
				shopVo.setShop_proof(rs.getBytes("SHOP_PROOF"));
				shopVo.setShop_photo(rs.getBytes("SHOP_PHOTO"));
				
			}
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
		return shopVo;
	}

	@Override
	public List<ShopVO> getAll() {

		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				shopVo = new ShopVO();
				shopVo.setShop_no(rs.getString("SHOP_NO"));
				shopVo.setShop_name(rs.getString("SHOP_NAME"));
				shopVo.setShop_id(rs.getString("SHOP_ID"));
				shopVo.setShop_psw(rs.getString("SHOP_PSW"));
				shopVo.setShop_email(rs.getString("SHOP_EMAIL"));
				shopVo.setShop_phone(rs.getString("SHOP_PHONE"));
				shopVo.setShop_loc(rs.getString("SHOP_LOC"));
				shopVo.setShop_lat(rs.getDouble("SHOP_LAT"));
				shopVo.setShop_lon(rs.getDouble("SHOP_LON"));
				shopVo.setShop_desc(rs.getString("SHOP_DESC"));
				shopVo.setShop_val(rs.getInt("SHOP_VAL"));
				shopVo.setShop_cosum(rs.getInt("SHOP_COSUM"));
				shopVo.setShop_adsum(rs.getInt("SHOP_ADSUM"));
				shopVo.setShop_bank(rs.getString("SHOP_BANK"));
				shopVo.setShop_acc(rs.getString("SHOP_ACC"));
				shopVo.setShop_vis(rs.getString("SHOP_VIS"));
				shopVo.setShop_stat(rs.getString("SHOP_STAT"));
				shopVo.setShop_point(rs.getInt("SHOP_POINT"));
				shopVo.setShop_score(rs.getDouble("SHOP_SCORE"));
				shopVo.setShop_proof(rs.getBytes("SHOP_PROOF"));
				shopVo.setShop_photo(rs.getBytes("SHOP_PHOTO"));
				list.add(shopVo);
			}

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
	
	/**會員登入**/
	@Override
	public ShopVO findById(String shop_id,String shop_psw) {
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, shop_id);
			pstmt.setString(2, shop_psw);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				shopVO = new ShopVO();
				shopVO.setShop_no(rs.getString("SHOP_NO"));
				shopVO.setShop_name(rs.getString("SHOP_NAME"));
				shopVO.setShop_id(rs.getString("SHOP_ID"));
				shopVO.setShop_psw(rs.getString("SHOP_PSW"));
				shopVO.setShop_email(rs.getString("SHOP_EMAIL"));
				shopVO.setShop_phone(rs.getString("SHOP_PHONE"));
				shopVO.setShop_loc(rs.getString("SHOP_LOC"));
				shopVO.setShop_lat(rs.getDouble("SHOP_LAT"));
				shopVO.setShop_lon(rs.getDouble("SHOP_LON"));
				shopVO.setShop_desc(rs.getString("SHOP_DESC"));
				shopVO.setShop_val(rs.getInt("SHOP_VAL"));
				shopVO.setShop_cosum(rs.getInt("SHOP_COSUM"));
				shopVO.setShop_adsum(rs.getInt("SHOP_ADSUM"));
				shopVO.setShop_bank(rs.getString("SHOP_BANK"));
				shopVO.setShop_acc(rs.getString("SHOP_ACC"));
				shopVO.setShop_vis(rs.getString("SHOP_VIS"));
				shopVO.setShop_stat(rs.getString("SHOP_STAT"));
				shopVO.setShop_point(rs.getInt("SHOP_POINT"));
				shopVO.setShop_score(rs.getDouble("SHOP_SCORE"));
				shopVO.setShop_proof(rs.getBytes("SHOP_PROOF"));
				shopVO.setShop_photo(rs.getBytes("SHOP_PHOTO"));
				
			}
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
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
		
		return shopVO;
	}
	
	/**檢查帳號重複**/
	@Override
	public ShopVO checkShopIdRepeat(String shop_id) {
		
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(REGISTEREDID_STMT);
			pstmt.setString(1, shop_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				shopVO = new ShopVO();
				shopVO.setShop_no(rs.getString("SHOP_NO"));
				shopVO.setShop_name(rs.getString("SHOP_NAME"));
				shopVO.setShop_id(rs.getString("SHOP_ID"));
				shopVO.setShop_psw(rs.getString("SHOP_PSW"));
				shopVO.setShop_email(rs.getString("SHOP_EMAIL"));
				shopVO.setShop_phone(rs.getString("SHOP_PHONE"));
				shopVO.setShop_loc(rs.getString("SHOP_LOC"));
				shopVO.setShop_lat(rs.getDouble("SHOP_LAT"));
				shopVO.setShop_lon(rs.getDouble("SHOP_LON"));
				shopVO.setShop_desc(rs.getString("SHOP_DESC"));
				shopVO.setShop_val(rs.getInt("SHOP_VAL"));
				shopVO.setShop_cosum(rs.getInt("SHOP_COSUM"));
				shopVO.setShop_adsum(rs.getInt("SHOP_ADSUM"));
				shopVO.setShop_bank(rs.getString("SHOP_BANK"));
				shopVO.setShop_acc(rs.getString("SHOP_ACC"));
				shopVO.setShop_vis(rs.getString("SHOP_VIS"));
				shopVO.setShop_stat(rs.getString("SHOP_STAT"));
				shopVO.setShop_point(rs.getInt("SHOP_POINT"));
				shopVO.setShop_score(rs.getDouble("SHOP_SCORE"));
				shopVO.setShop_proof(rs.getBytes("SHOP_PROOF"));
				shopVO.setShop_photo(rs.getBytes("SHOP_PHOTO"));
			}		
			
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
		return shopVO;
	}
	
	@Override
	public Set<CommodityVO> findByShopNo(String shop_no) {
		Set<CommodityVO> set = new LinkedHashSet<CommodityVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommodityVO comVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1, shop_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				comVO = new CommodityVO();
				comVO.setCom_no(rs.getString("com_no"));
				comVO.setCom_name(rs.getString("com_name"));
				comVO.setShop_no(rs.getString("shop_no"));
				comVO.setFru_no(rs.getString("fru_no"));
				comVO.setCom_price(rs.getInt("com_price"));
				comVO.setCom_remarks(rs.getString("com_remarks"));
				comVO.setCom_weight(rs.getString("com_weight"));
				comVO.setCom_pic1(rs.getBytes("com_pic1"));
				String com_pic1S = Base64.getEncoder().encodeToString(rs.getBytes("com_pic1"));
				comVO.setCom_pic2(rs.getBytes("com_pic2"));
				comVO.setCom_pic3(rs.getBytes("com_pic3"));
				comVO.setCom_time(rs.getTimestamp("com_time"));
				comVO.setCom_status(rs.getString("com_status"));
				comVO.setCom_store(rs.getInt("com_store"));
				comVO.setCom_score(rs.getDouble("com_score"));
				comVO.setCom_peo(rs.getInt("com_peo"));
				set.add(comVO);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return set;
	}
	
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void updateShop_stat(String shop_no, String shop_stat) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("UPDATE SHOP SET SHOP_STAT=? WHERE SHOP_NO=?");
			pstmt.setString(1, shop_stat);
			pstmt.setString(2, shop_no);
			pstmt.executeUpdate();
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
	public void updateShop_point(String shop_no, Integer shop_point) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("UPDATE SHOP SET shop_point=? WHERE SHOP_NO=?");
			pstmt.setInt(1, shop_point);
			pstmt.setString(2, shop_no);
			pstmt.executeUpdate();
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
	public List<ShopVO> findByShop_stat(String shop_stat) {

		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * FROM SHOP WHERE shop_stat = ?");
			pstmt.setString(1, shop_stat);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				shopVo = new ShopVO();
				shopVo.setShop_no(rs.getString("SHOP_NO"));
				shopVo.setShop_name(rs.getString("SHOP_NAME"));
				shopVo.setShop_id(rs.getString("SHOP_ID"));
				shopVo.setShop_psw(rs.getString("SHOP_PSW"));
				shopVo.setShop_email(rs.getString("SHOP_EMAIL"));
				shopVo.setShop_phone(rs.getString("SHOP_PHONE"));
				shopVo.setShop_loc(rs.getString("SHOP_LOC"));
				shopVo.setShop_lat(rs.getDouble("SHOP_LAT"));
				shopVo.setShop_lon(rs.getDouble("SHOP_LON"));
				shopVo.setShop_desc(rs.getString("SHOP_DESC"));
				shopVo.setShop_val(rs.getInt("SHOP_VAL"));
				shopVo.setShop_cosum(rs.getInt("SHOP_COSUM"));
				shopVo.setShop_adsum(rs.getInt("SHOP_ADSUM"));
				shopVo.setShop_bank(rs.getString("SHOP_BANK"));
				shopVo.setShop_acc(rs.getString("SHOP_ACC"));
				shopVo.setShop_vis(rs.getString("SHOP_VIS"));
				shopVo.setShop_stat(rs.getString("SHOP_STAT"));
				shopVo.setShop_point(rs.getInt("SHOP_POINT"));
				shopVo.setShop_score(rs.getDouble("SHOP_SCORE"));
				shopVo.setShop_proof(rs.getBytes("SHOP_PROOF"));
				shopVo.setShop_photo(rs.getBytes("SHOP_PHOTO"));
				list.add(shopVo);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//	public static void main(String[] args) {

		// insert註冊
//		ShopJDBCDAOImpl dao = new ShopJDBCDAOImpl();
		//
		// ShopVO shopVo = new ShopVO();
		// shopVo.setShop_name("水果000");
		// shopVo.setShop_id("962587");
		// shopVo.setShop_psw("qwe123");
		// shopVo.setShop_email("qwe123@hotmail.com");
		// shopVo.setShop_phone("091234567");
		// shopVo.setShop_loc("新竹市湖口區");
		// shopVo.setShop_desc("大家好我是水果好棒棒,吃了更棒棒");
		// shopVo.setShop_bank("台新銀行");
		// shopVo.setShop_acc("040055231582");
		//
		// try {
		// byte[] pic = getPictureByteArray("fakeImage/SHOP-SHOP_PROOF/3.jpg");
		// byte[] pic1 = getPictureByteArray("fakeImage/SHOP-SHOP_PHOTO/4.jpg");
		// System.out.println(pic.length);
		// shopVo.setShop_proof(pic);
		// shopVo.setShop_photo(pic1);
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// dao.insert(shopVo);
		// System.out.println("good");
		//
		//
		// ===========================================================

		// delete刪除
		// dao.delete("SHOP000040");
		// System.out.println("成功");

		// ===========================================================

		// //修改update
		// ShopVO shopVoUpdate = new ShopVO();
		// shopVoUpdate.setShop_name("水果真的棒");
		// shopVoUpdate.setShop_id("qqqqq8888");
		// shopVoUpdate.setShop_psw("qwe123");
		// shopVoUpdate.setShop_email("qwe123@hotmail.com");
		// shopVoUpdate.setShop_phone("0912345678");
		// shopVoUpdate.setShop_loc("高雄市旗山區");
		// shopVoUpdate.setShop_desc("大家好我是水果好棒棒，吃了更棒棒");
		// shopVoUpdate.setShop_bank("彰化銀行");
		// shopVoUpdate.setShop_acc("52554987955");
		// shopVoUpdate.setShop_no("SHOP000004");
		//
		// byte[] pic,pic1;
		// try {
		// pic = getPictureByteArray("fakeImage/SHOP-SHOP_PROOF/5.jpg");
		// pic1 = getPictureByteArray("fakeImage/SHOP-SHOP_PHOTO/6.jpg");
		// System.out.println(pic.length);
		// shopVoUpdate.setShop_proof(pic);
		// shopVoUpdate.setShop_photo(pic1);
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// dao.update(shopVoUpdate);
		// System.out.println("good");
		//

		// ===========================================================

		// 查詢一個

		// ShopVO shopVoSelect = dao.findByPrimaryKey("SHOP000004");
		//
		// System.out.println("編號:" + shopVoSelect.getShop_no());
		// System.out.println("名稱:" + shopVoSelect.getShop_name());
		// System.out.println("帳號" + shopVoSelect.getShop_id());
		// System.out.println("密碼:" + shopVoSelect.getShop_psw());
		// System.out.println("信箱:" + shopVoSelect.getShop_email());
		// System.out.println("電話:" + shopVoSelect.getShop_phone());
		// System.out.println("地址:" + shopVoSelect.getShop_loc());
		// System.out.println("緯度:" + shopVoSelect.getShop_lat());
		// System.out.println("經度:" + shopVoSelect.getShop_lon());
		// System.out.println("描述:" + shopVoSelect.getShop_desc());
		// System.out.println("型態:" + shopVoSelect.getShop_val());
		// System.out.println("累積:" + shopVoSelect.getShop_cosum());
		// System.out.println("不懂:" + shopVoSelect.getShop_adsum());
		// System.out.println("銀行:" + shopVoSelect.getShop_bank());
		// System.out.println("帳戶:" + shopVoSelect.getShop_acc());
		// System.out.println("可見:" + shopVoSelect.getShop_vis());
		// System.out.println("狀態:" + shopVoSelect.getShop_stat());
		// System.out.println("點數:" + shopVoSelect.getShop_point());
		// System.out.println("分數:" + shopVoSelect.getShop_score());
		// System.out.println("證明:" + shopVoSelect.getShop_proof().length);
		// System.out.println("照片:" + shopVoSelect.getShop_photo().length);
		//
		// try {
		// readPicture(shopVoSelect.getShop_proof());
		// readPicture(shopVoSelect.getShop_photo());
		// System.out.println("success");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// ===========================================================

		// 查全部
//		List<ShopVO> list = dao.getAll();
//		for (ShopVO shopAll : list) {
//			System.out.println("編號:" + shopAll.getShop_no());
//			System.out.println("名稱:" + shopAll.getShop_name());
//			System.out.println("帳號" + shopAll.getShop_id());
//			System.out.println("密碼:" + shopAll.getShop_psw());
//			System.out.println("信箱:" + shopAll.getShop_email());
//			System.out.println("電話:" + shopAll.getShop_phone());
//			System.out.println("地址:" + shopAll.getShop_loc());
//			System.out.println("緯度:" + shopAll.getShop_lat());
//			System.out.println("經度:" + shopAll.getShop_lon());
//			System.out.println("描述:" + shopAll.getShop_desc());
//			System.out.println("型態:" + shopAll.getShop_val());
//			System.out.println("累積:" + shopAll.getShop_cosum());
//			System.out.println("忘了:" + shopAll.getShop_adsum());
//			System.out.println("銀行:" + shopAll.getShop_bank());
//			System.out.println("帳戶:" + shopAll.getShop_acc());
//			System.out.println("可見:" + shopAll.getShop_vis());
//			System.out.println("狀態:" + shopAll.getShop_stat());
//			System.out.println("點數:" + shopAll.getShop_point());
//			System.out.println("分數:" + shopAll.getShop_score());
//			System.out.println("證明:" + shopAll.getShop_proof().length);
//			System.out.println("照片:" + shopAll.getShop_photo().length);
//			System.out.println("=========================================");
//			try {
//				readPicture(shopAll.getShop_proof());
//				readPicture(shopAll.getShop_photo());
//				System.out.println("success");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//	}// main結束

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
		FileOutputStream fos = new FileOutputStream("fakeImage/SHOP-SHOP_PHOTO/test.png");
		FileOutputStream fos1 = new FileOutputStream("fakeImage/SHOP-SHOP_PHOTO/test1.png");
		fos.write(bytes);
		fos1.write(bytes);
		fos.flush();
		fos.close();
	}

	


}