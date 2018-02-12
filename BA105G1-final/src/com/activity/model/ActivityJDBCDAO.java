package com.activity.model;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.act_cat.model.Act_catJDBCDAO;
import com.act_cat.model.Act_catVO;

public class ActivityJDBCDAO implements ActivityDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "FRUIT";
	private static final String PASSWORD = "FRUIT";
	//新增活動
	private static final String INSERT_STMT = "INSERT INTO ACTIVITY(act_no, shop_no, act_name, act_pic, act_pic_base64, act_start, act_end, act_art, act_status, act_status2, act_ls)"
			+ "VALUES('ACT'||LPAD(to_char(act_no_seq.NEXTVAL), 7, '0'), ?, ?, ?, ?, ?, ?, ?, '未開始', '正常', '未開始')";
	//修改活動資料(不含活動狀態)
	private static final String UPDATE = "UPDATE Activity SET act_name = ?, act_pic = ?, act_pic_base64 = ?, act_start = ?, "
			+ "act_end = ?, act_art = ? WHERE act_no = ?";
	//修改活動資料(只有活動狀態)
	private static final String UPDATE_ACT_STATUS = "UPDATE Activity SET act_status = ? WHERE act_no = ?";
	//修改活動資料(只有直播狀態)
	private static final String UPDATE_LIVE_STATUS = "UPDATE Activity SET act_ls = ? WHERE act_no = ?";
	
	//顯示自家活動列表(商家)新到舊
	private static final String FIND_BY_SHOP = "SELECT act_no, act_name, act_pic, act_pic_base64, act_start, act_end, act_art FROM Activity WHERE shop_no = ? ORDER BY act_start desc";
	//顯示所有活動列表(會員)新到舊
	private static final String GET_ALL_ACT = "SELECT act_no, act_name, act_pic, act_pic_base64, act_start, act_art FROM Activity where act_status2='正常' ORDER BY act_start desc";
	//查看活動詳情 
	private static final String FIND_BY_ACT_NO = "SELECT act_no, shop_no, act_name, act_pic, act_pic_base64, act_start, act_end, act_art FROM Activity WHERE act_no = ?";
	//關鍵字搜尋活動
	private static final String SEARCH = "SELECT act_no, act_name, act_pic, act_pic_base64, act_start, act_art FROM activity WHERE (act_art LIKE ? OR act_name LIKE ?) and act_status2='正常' ORDER BY act_start DESC";
	
	//新增活動
	@Override
	public void insert(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "act_no" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, activityVO.getShop_no());
			pstmt.setString(2, activityVO.getAct_name());
			pstmt.setBytes(3, activityVO.getAct_pic());
			pstmt.setString(4, activityVO.getAct_pic_base64());
			pstmt.setTimestamp(5, activityVO.getAct_start());
			pstmt.setTimestamp(6, activityVO.getAct_end());
			pstmt.setString(7, activityVO.getAct_art());
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
	
	//自增主鍵綁定0112+
	@Override
	public void insertWithActCat(ActivityVO activityVO , List<Act_catVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String[] cols = { "act_no" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, activityVO.getShop_no());
			pstmt.setString(2, activityVO.getAct_name());
			pstmt.setBytes(3, activityVO.getAct_pic());
			pstmt.setString(4, activityVO.getAct_pic_base64());
			pstmt.setTimestamp(5, activityVO.getAct_start());
			pstmt.setTimestamp(6, activityVO.getAct_end());
			pstmt.setString(7, activityVO.getAct_art());
			pstmt.executeUpdate();

			//掘取對應的自增主鍵值
			String act_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				act_no = rs.getString(1);
				System.out.println("自增主鍵值= " + act_no +"(剛新增成功的活動編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增活動類別
			Act_catJDBCDAO dao = new Act_catJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (Act_catVO act_catVO : list) {
				act_catVO.setAct_no(act_no);
				dao.insert2(act_catVO, con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增活動編號 :" + act_no + "時,共有員工" + list.size()
					+ "人同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	
	//修改活動資料(不含狀態)
	@Override
	public void update(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, activityVO.getAct_name());
			pstmt.setBytes(2, activityVO.getAct_pic());
			pstmt.setString(3, activityVO.getAct_pic_base64());
			pstmt.setTimestamp(4, activityVO.getAct_start());
			pstmt.setTimestamp(5, activityVO.getAct_end());
			pstmt.setString(6, activityVO.getAct_art());
			pstmt.setString(7, activityVO.getAct_no());
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

	//修改活動資料(只有活動狀態)
	@Override
	public void updateActStatus(String act_status, String act_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATE_ACT_STATUS);
				pstmt.setString(1, act_status);
				pstmt.setString(2, act_no);
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
	
	//修改活動資料(只有直播狀態) 
	@Override
	public void updateLiveStatus(String act_ls, String act_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATE_LIVE_STATUS);
				pstmt.setString(1, act_ls);
				pstmt.setString(2, act_no);
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
		
	//查看活動詳情
	public ActivityVO findByActNo(String act_no){
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACT_NO);
			pstmt.setString(1, act_no);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setAct_no(rs.getString("act_no"));
				activityVO.setShop_no(rs.getString("shop_no"));
				activityVO.setAct_name(rs.getString("act_name"));
				activityVO.setAct_pic(rs.getBytes("act_pic"));
				activityVO.setAct_pic_base64(rs.getString("act_pic_base64"));
				activityVO.setAct_start(rs.getTimestamp("act_start"));
				activityVO.setAct_end(rs.getTimestamp("act_end"));
				activityVO.setAct_art(rs.getString("act_art"));
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
		return activityVO;
	}
	
	//顯示自家活動列表(商家)新到舊
	@Override
	public List<ActivityVO> findByShopNo(String shop_no) {
		List<ActivityVO> actList = new ArrayList<>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_SHOP);
			pstmt.setString(1, shop_no);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setAct_no(rs.getString("act_no"));
				activityVO.setAct_name(rs.getString("act_name"));
				activityVO.setAct_pic(rs.getBytes("act_pic"));
				activityVO.setAct_pic_base64(rs.getString("act_pic_base64"));
				activityVO.setAct_start(rs.getTimestamp("act_start"));
				activityVO.setAct_end(rs.getTimestamp("act_end"));
				activityVO.setAct_art(rs.getString("act_art"));
				actList.add(activityVO);
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
		return actList;
	}
	
	//顯示所有活動列表(會員)新到舊
	@Override
	public List<ActivityVO> getAllAct() {
		List<ActivityVO> actList = new ArrayList<>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_ACT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setAct_no(rs.getString("act_no"));
				activityVO.setAct_name(rs.getString("act_name"));
				activityVO.setAct_pic(rs.getBytes("act_pic"));
				activityVO.setAct_pic_base64(rs.getString("act_pic_base64"));
				activityVO.setAct_start(rs.getTimestamp("act_start"));	
				activityVO.setAct_art(rs.getString("act_art"));
				actList.add(activityVO);
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
		return actList;
	}

	//關鍵字搜尋活動
	@Override
	public List<ActivityVO> search(String pattern) {
		List<ActivityVO> actList = new ArrayList<>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SEARCH);
			pstmt.setString(1, pattern);
			pstmt.setString(2, pattern);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setAct_no(rs.getString("act_no"));
				activityVO.setAct_name(rs.getString("act_name"));
				activityVO.setAct_pic(rs.getBytes("act_pic"));
				activityVO.setAct_pic_base64(rs.getString("act_pic_base64"));
				activityVO.setAct_start(rs.getTimestamp("act_start"));	
				activityVO.setAct_art(rs.getString("act_art"));
				actList.add(activityVO);
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
		return actList;
	}

	
	//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public void updateActStatus2(String act_status2, String act_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement("UPDATE Activity SET act_status2 = ? WHERE act_no = ?");
			pstmt.setString(1, act_status2);
			pstmt.setString(2, act_no);
			pstmt.executeUpdate();
			// Handle any DRIVER errors
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

	//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//世霖開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public List<ActivityVO> findByShopno(String shop_no) {
		List<ActivityVO> actList = new ArrayList<>();
		ActivityVO activityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_SHOP);
			pstmt.setString(1, shop_no);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				
				activityVO = new ActivityVO();
				activityVO.setAct_no(rs.getString("act_no"));
				activityVO.setShop_no(rs.getString("shop_no"));
				activityVO.setAct_name(rs.getString("act_name"));
				
				activityVO.setAct_pic(rs.getBytes("act_pic"));
				activityVO.setAct_pic_base64(rs.getString("act_pic_base64"));
				activityVO.setAct_start(rs.getTimestamp("act_start"));
				activityVO.setAct_end(rs.getTimestamp("act_end"));
				activityVO.setAct_art(rs.getString("act_art"));
				actList.add(activityVO);
				
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
		return actList;
	
	}

	//世霖結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



	
	
}
