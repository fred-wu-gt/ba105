package com.writing.model;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class TestWriting {
	public static void main(String[] args) {
		WritingJDBCDAO dao = new WritingJDBCDAO();
		byte[] pic1 = null;
		byte[] pic9 = null;
		try {
			/* 1.新增 */
			// WritingVO wri1 = new WritingVO();
			// pic1 = getPictureByteArray("WRITING-WRT_PHOTO/1.jpg");
			// wri1.setShop_no("SHOP000001");
			// wri1.setWrt_title("台東有釋迦");
			// wri1.setWrt_photo(pic1);
			// wri.setWrt_photo_base64();
			// wri1.setWrt_sta("正常");
			// wri1.setWrt_cont("真的好甜");
			// dao.insert(wri1);
			// System.out.println("成功新增一筆資料!");

			/* 2. 修改 */
			// WritingVO writingVO = new WritingVO();
			// pic9 = getPictureByteArray("WebContent/WRITING-WRT_PHOTO/2.jpg");
			// writingVO.setWrt_no("WRT0000010");
			// writingVO.setShop_no("SHOP000010");
			// writingVO.setWrt_title("好吃的巧克力");
			// writingVO.setWrt_photo(pic9);
			// writingVO.setWrt_photo_base64();
			// writingVO.setWrt_sta("正常");
			// writingVO.setWrt_cont("因禍得福巧克力，下不為例炸醬麵");
			// dao.update(writingVO);
			// System.out.println("成功更新一筆資料!");

			/* 3.刪除 */
			// dao.delete("WRT0000011");
			// System.out.println("成功刪除一筆資料!");

			/* 4.查詢 */
			// System.out.println("查詢單筆資料結果:");
			// WritingVO wri5 = dao.findByPrimaryKey("WRT0000010");
			// System.out.println("WRT_NO : " + wri5.getWrt_no());
			// System.out.println("SHOP_NO : " + wri5.getShop_no());
			// System.out.println("WRT_TITLE : " + wri5.getWrt_title());
			// System.out.println("WRT_PHOTO : " + wri5.getWrt_photo());
			// System.out.println("WRT_PHOTO_BASE64() : " + wri5.getWrt_photo_base64());
			// System.out.println("WRT_STA : " + wri5.getWrt_sta());
			// System.out.println("WRT_CONT : " + wri5.getWrt_cont());
			// System.out.println("WRT_TIME : " + wri5.getWrt_time());
			// System.out.println("------------------------------");

			/* 5.查全部 */
			System.out.println("查詢多筆資料結果:");
			List<WritingVO> list = dao.getAll();
			for (WritingVO wri : list) {
				String wrt_no = wri.getWrt_no();
				System.out.println("WRT_NO: " + wri.getWrt_no());
				System.out.println("SHOP_NO: " + wri.getShop_no());
				System.out.println("WRT_TITLE: " + wri.getWrt_title());
				System.out.println("WRT_PHOTO: " + wri.getWrt_photo());
				System.out.println("WRT_PHOTO_BASE64() " + wri.getWrt_photo_base64());
				readPicture(wri.getWrt_photo(), wrt_no);
				System.out.println("WRT_STA: " + wri.getWrt_sta());
				System.out.println("WRT_CONT: " + wri.getWrt_cont());
				System.out.println("WRT_TIME: " + wri.getWrt_time());
				System.out.println();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

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

	public static void readPicture(byte[] bytes, String wrt_no) throws IOException {
		FileOutputStream fos = new FileOutputStream("image/testReadFromDB/" + wrt_no + ".jpg");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
}
