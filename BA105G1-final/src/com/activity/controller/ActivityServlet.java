package com.activity.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.act_cat.model.Act_catService;
import com.act_cat.model.Act_catVO;
import com.activity.model.*;
import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 30 * 1024 * 1024, maxRequestSize = 30 * 1024 * 1024)
public class ActivityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");//先
		String action = req.getParameter("action");//後
		HttpSession session = req.getSession();

		if ("getOneAct_For_Display".equals(action)) { // 來自activity_mem_home.jsp或activity_join.jsp要查看詳情的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String act_no = req.getParameter("act_no");//hidden

				/***************************2.開始查詢資料*****************************************/
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.findByActNo(act_no);
				System.out.println("act_no = " + activityVO.getAct_no());
//				if (activityVO == null) {
//					errorMsgs.add("查無資料");
//				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/activity/activity_mem_home.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************///<tag 01> 
req.setAttribute("activityVO", activityVO); // 資料庫取出的activityVO物件,存入req, forward給activity_more.jsp使用
				String url = "/front-end/activity/activity_more.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 activity_more.jsp
				System.out.println("由<tag 01> ActivivtyServlet轉交activity_more.jsp");
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************///<tag 02>
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/activity/activity_mem_home.jsp");
				System.out.println("由<tag 02> ActivivtyServlet轉交activity_mem_home.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String act_no = req.getParameter("act_no");
				if (act_no == null || (act_no.trim()).length() == 0) {
					errorMsgs.add("請輸活動編號");
				}
				//有輸入的狀況
				else{
					if (act_no.trim().length() != 10) {
						errorMsgs.add("活動編號為10碼");
					}
					if (!act_no.trim().matches("ACT\\d{7}")){
						errorMsgs.add("活動編號格式為ACT加七位數字");
					}
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.findByActNo(act_no);
				if (activityVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("activityVO", activityVO); // 資料庫取出的activityVO物件,存入req
				String url = "/front-end/activity/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自shop_home.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				String act_no = new String(req.getParameter("act_no"));

				/***************************2.開始查詢資料****************************************/
				ActivityService actSvc = new ActivityService();
				ActivityVO activityVO = actSvc.findByActNo(act_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("activityVO", activityVO);         // 資料庫取出的activityVO物件,存入req
				String url = "/front-end/activity/update_act_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_act_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/activity/activity_shop_home.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("update".equals(action)) { // 來自update_act_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			ActivityVO activityVO = new ActivityVO();

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String act_no = req.getParameter("act_no").trim();//hidden欄位來的,不用錯誤處理
				//System.out.println(act_no);
				String shop_no = req.getParameter("shop_no").trim();//hidden欄位來的,不用錯誤處理
				//System.out.println(shop_no);
				
				String fru_name = req.getParameter("fru_name");
				req.setAttribute("fru_name", fru_name);
				String act_name = req.getParameter("act_name");
				if (act_name == null || act_name.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}	
				
				String act_art = req.getParameter("act_art").trim();
				if (act_art == null || act_art.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}	
				
				java.sql.Timestamp act_start = null;
				try {
					act_start = java.sql.Timestamp.valueOf(req.getParameter("act_start").trim());
				} catch (IllegalArgumentException e) {
					act_start = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				
				java.sql.Timestamp act_end = null;
				try {
					act_end = java.sql.Timestamp.valueOf(req.getParameter("act_end").trim());
				} catch (IllegalArgumentException e) {
					act_end = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				
				Collection<Part> parts = req.getParts();
				byte[] act_pic = null;
				String act_pic_base64 = null;
				if(!req.getParameter("img").equals("")){
					act_pic_base64=req.getParameter("img");
					System.out.println("隱藏欄位送來字串的act_pic_base64");//(幫助debug)
				}else{
					System.out.println("隱藏欄位送來字串的"+req.getParameter("img"));//(幫助debug)
				}
				
				for (Part part : parts) {
					String type = part.getContentType();
					//處理input type="file"的部分
					if(type!=null){
						//這次沒上傳檔案的狀況
						if(type.equals("application/octet-stream")){
							//這次沒上傳且上次也沒上傳檔案的狀況
							if(act_pic_base64==null)
								errorMsgs.add("請上傳照片");
						}
						//這次有上傳檔案的狀況
						else{
							if(type.startsWith("image/") ){
								if(part.getSize()<(1024 * 1024)){
									act_pic = getPictureByteArray(part.getInputStream());
									act_pic_base64=Base64Encoder.encode(act_pic);
								}
								else{
									errorMsgs.add("照片需小於1MB");
								}
							}else{
								errorMsgs.add("照片格式錯誤，請上傳jpeg, png, gif檔案");
							}
						}
					}
				}
				
				activityVO.setAct_no(act_no);
				activityVO.setShop_no(shop_no);
				activityVO.setAct_name(act_name);
				activityVO.setAct_start(act_start);
				activityVO.setAct_end(act_end);
				activityVO.setAct_art(act_art);
				if(act_pic_base64!=null && act_pic==null)
					act_pic=Base64Decoder.decodeToBytes(act_pic_base64);
				activityVO.setAct_pic(act_pic);
				activityVO.setAct_pic_base64(act_pic_base64);
				System.out.println("activityVo set ok");
				System.out.println(activityVO);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO", activityVO); // 含有輸入格式錯誤的activityVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/update_act_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ActivityService actSvc = new ActivityService();
				activityVO = actSvc.updateActivity(act_no, shop_no, act_name, act_pic,
						act_pic_base64, act_start, act_end, act_art);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("activityVO", activityVO); // 資料庫update成功後,正確的的activityVO物件,存入req
				System.out.println("vo update ok______________________");
				System.out.println(activityVO);
				if (session.getAttribute("shopActlist") != null) {
					session.removeAttribute("shopActlist");
				}
				System.out.println("after removeAttribute(joinActList)");
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/activity/update_act_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addAct.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String fru_no = req.getParameter("fru_no").trim();
				String shop_no = req.getParameter("shop_no").trim();
				
				
				String act_name = req.getParameter("act_name").trim();
				if (act_name == null || act_name.trim().length() == 0) {
					errorMsgs.add("活動標題: 請勿空白");
				}
				
				java.sql.Timestamp act_start = null;
				try {
					act_start = java.sql.Timestamp.valueOf(req.getParameter("act_start").trim());
					System.out.println(act_start);
				} catch (IllegalArgumentException e) {
					act_start = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				
				java.sql.Timestamp act_end = null;
				try {
					act_end = java.sql.Timestamp.valueOf(req.getParameter("act_end").trim());
				} catch (IllegalArgumentException e) {
					act_end = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				
				String act_art = req.getParameter("act_art").trim();
				if (act_art == null || act_art.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}	
				
				Collection<Part> parts = req.getParts();
				byte[] act_pic=null;
				String act_pic_base64=null;
				if(!req.getParameter("img").equals("")){
					act_pic_base64=req.getParameter("img");
					System.out.println("隱藏欄位送來字串的act_pic_base64");//(幫助debug)
				}else{
					System.out.println("隱藏欄位送來字串的"+req.getParameter("img"));//(幫助debug)
				}
				
				for (Part part : parts) {
					String type = part.getContentType();
					//處理input type="file"的部分
					if(type!=null){
						//這次沒上傳檔案的狀況
						if(type.equals("application/octet-stream")){
							//這次沒上傳且上次也沒上傳檔案的狀況
							if(act_pic_base64==null)
								errorMsgs.add("請上傳照片");
						}
						//這次有上傳檔案的狀況
						else{
							if(type.startsWith("image/") ){
								if(part.getSize()<(1024 * 1024)){
									act_pic = getPictureByteArray(part.getInputStream());
									act_pic_base64=Base64Encoder.encode(act_pic);
								}
								else{
									errorMsgs.add("照片需小於1MB");
								}
							}else{
								errorMsgs.add("照片格式錯誤，請上傳jpeg, png, gif檔案");
							}
						}
					}
				}
				List<Act_catVO> list = new ArrayList<Act_catVO>(); // 準備置入員工數人
				Act_catVO act_catVO = new Act_catVO();
				act_catVO.setFru_no(fru_no);System.out.println("水果分類包裝成功");
				list.add(act_catVO);System.out.println("水果分類加入LIST");
				
				ActivityVO activityVO = new ActivityVO();
				activityVO.setShop_no(shop_no);
				activityVO.setAct_name(act_name);
				activityVO.setAct_start(act_start);
				activityVO.setAct_end(act_end);
				activityVO.setAct_art(act_art);
				if(act_pic_base64!=null && act_pic==null)
					act_pic=Base64Decoder.decodeToBytes(act_pic_base64);
				activityVO.setAct_pic(act_pic);
				activityVO.setAct_pic_base64(act_pic_base64);
				System.out.println("活動VO包裝成功");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO", activityVO); // 含有輸入格式錯誤的activityVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/addAct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2-1.開始新增資料***************************************/
				ActivityService actSvc = new ActivityService();
				actSvc.insertWithActCat(activityVO, list);//自增主鍵綁定0112+
				System.out.println("自增主鍵綁定並新增成功");
				
				//req.setAttribute("activityVO", activityVO);
		if (session.getAttribute("shopActlist") != null) {
			session.removeAttribute("shopActlist");
		}
		System.out.println("after removeAttribute(joinActList)");
				
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/activity/activity_shop_home.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/activity/addAct.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				ActivityService empSvc = new ActivityService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if ("pattern".equals(action)) { // 來自activity_mem_home.jsp的關鍵字查詢請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);	
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

				String pattern2 = null;
				String str2 = req.getParameter("pattern").trim();
				if(str2.length() == 0){
					res.sendRedirect(req.getContextPath()+ "/front-end/activity/activity_mem_home.jsp");
					return;
				}
				pattern2 = new String(req.getParameter("pattern").trim());
				StringBuffer str = new StringBuffer();
				str.append('%').append(pattern2).append('%');
				String pattern = str.toString();

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/activity_mem_home.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始查詢資料*****************************************/
				List<ActivityVO> list = null;//關鍵字查詢得到的活動們
				ActivityService actSvc = new ActivityService();
				list = actSvc.search(pattern);

				req.setAttribute("patternList", list);
				for(ActivityVO act: list){
					System.out.println("關鍵字查詢得到的活動們\n"+act.getAct_name().toString());
				}
				System.out.println("-----------------");
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/activity_mem_home.jsp");
					failureView.forward(req, res);
					return;
				}
								
				/***************************3.查詢完成,準備轉交(Send the Success view)*************///<tag 03>

				String url = "/front-end/activity/activity_mem_home.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交activity_mem_home.jsp
				System.out.println("由<tag 03> ActivivtyServlet轉交activity_mem_home.jsp");
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
		//		} catch (Exception e) {
		//			errorMsgs.add("無法取得資料:" + e.getMessage());
		//			RequestDispatcher failureView = req
		//					.getRequestDispatcher("/front-end/activity/activity_mem_home.jsp");
		//			failureView.forward(req, res);
		//		}
			}
		
	}
	
	//從part得到使用者上傳的InputStream，再透過此自訂方法，轉成byte[]回傳
		public static byte[] getPictureByteArray(InputStream is) throws IOException {
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  //ByteArrayOutputStream有內建byte陣列透過write()方法寫入資料,toByteArray()方法回傳 
			byte[] buffer = new byte[8*1024];
			int i;
			while ((i = bis.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			baos.flush();
			baos.close();
			bis.close();

			return baos.toByteArray();
		}
}
