package com.prom.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.ShopVO;

public class PromJDBCDAOImpl implements PromDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "FRUIT";
	String password = "FRUIT";

	private static final String INSERT_STMT = "INSERT INTO PROMOTION (PROM_NO,SHOP_NO,PROM_DIS,PROM_START,PROM_END)"
			+ "VALUES('PROM'||LPAD(TO_CHAR(PROM_NO_SEQUENCES.NEXTVAL),6,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT PROM_NO,SHOP_NO,PROM_DIS,PROM_START,PROM_END FROM PROMOTION ORDER BY PROM_NO";
	private static final String GET_ONE_STMT = "SELECT PROM_NO,SHOP_NO,PROM_DIS,PROM_START,PROM_END FROM PROMOTION WHERE PROM_NO = ?";
	private static final String DELETE = "DELETE FROM PROMOTION WHERE PROM_NO = ?";
	private static final String UPDATE = "UPDATE PROMOTION SET SHOP_NO =?,PROM_DIS =?,PROM_START=?,PROM_END=? WHERE PROM_NO=?";

	@Override
	public void insert(PromVO promVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promVo.getShop_no());
			pstmt.setDouble(2, promVo.getProm_dis());
			pstmt.setTimestamp(3, promVo.getProm_start());
			pstmt.setTimestamp(4, promVo.getProm_end());

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
	public void update(PromVO promVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promVo.getShop_no());
			pstmt.setDouble(2, promVo.getProm_dis());
			pstmt.setTimestamp(3, promVo.getProm_start());
			pstmt.setTimestamp(4, promVo.getProm_end());
			pstmt.setString(5, promVo.getProm_no());

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
	public void delete(String prom_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prom_no);
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
	public PromVO findByPrimaryKey(String prom_no) {

		PromVO promVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prom_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				promVo = new PromVO();

				promVo.setProm_no(rs.getString("PROM_NO"));
				promVo.setShop_no(rs.getString("SHOP_NO"));
				promVo.setProm_dis(rs.getDouble("PROM_DIS"));
				promVo.setProm_start(rs.getTimestamp("PROM_START"));
				promVo.setProm_end(rs.getTimestamp("PROM_END"));

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

		return promVo;
	}

	@Override
	public List<PromVO> getAll() {

		List<PromVO> list = new ArrayList<PromVO>();
		PromVO promVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promVo = new PromVO();

				promVo.setProm_no(rs.getString("PROM_NO"));
				promVo.setShop_no(rs.getString("SHOP_NO"));
				promVo.setProm_dis(rs.getDouble("PROM_DIS"));
				promVo.setProm_start(rs.getTimestamp("PROM_START"));
				promVo.setProm_end(rs.getTimestamp("PROM_END"));

				list.add(promVo);
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

	public static void main(String[] args) {

		PromJDBCDAOImpl dao = new PromJDBCDAOImpl();
		// =================================================================
		// 新增
		// PromVO promVo = new PromVO();
		// promVo.setShop_no("SHOP000005");
		// promVo.setProm_dis(0.7);
		// promVo.setProm_start(java.sql.Timestamp.valueOf("2018-03-15 09:30:00"));
		// promVo.setProm_end(java.sql.Timestamp.valueOf("2018-03-30 09:30:00"));
		//
		// dao.insert(promVo);

		// ================================================================
		// 刪除

		// dao.delete("PROM000010");
		// System.out.println("success delete");

		// =================================================================
		// 修改

		// PromVO promVoUpdate = new PromVO();
		//
		// promVoUpdate.setShop_no("SHOP000009");
		// promVoUpdate.setProm_dis(0.2);
		// promVoUpdate.setProm_start(java.sql.Timestamp.valueOf("2018-10-20
		// 09:30:00"));
		// promVoUpdate.setProm_end(java.sql.Timestamp.valueOf("2018-12-30 09:30:00"));
		// promVoUpdate.setProm_no("PROM000011");
		//
		// dao.update(promVoUpdate);
		// System.out.println("update success");

		// ================================================================
		// 主鍵查詢
		// PromVO promVoSelect = dao.findByPrimaryKey("PROM000001");
		//
		// System.out.println("促銷編號:"+promVoSelect.getProm_no());
		// System.out.println("商家編號:"+promVoSelect.getShop_no());
		// System.out.println("促銷折扣:"+promVoSelect.getProm_dis());
		// System.out.println("促銷開始:"+promVoSelect.getProm_start());
		// System.out.println("促銷結束:"+promVoSelect.getProm_end());

		// ================================================================
		// 查全部

		List<PromVO> list = dao.getAll();

		for (PromVO promAll : list) {

			System.out.println("促銷編號:" + promAll.getProm_no());
			System.out.println("商家編號:" + promAll.getShop_no());
			System.out.println("促銷折扣:" + promAll.getProm_dis());
			System.out.println("促銷開始:" + promAll.getProm_start());
			System.out.println("促銷結束:" + promAll.getProm_end());
			System.out.println("===============================");

		}

	}//main 結束

}
