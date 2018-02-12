package com.writing.controller;

import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.oreilly.servlet.*;

import com.writing.model.*;

//限定上傳檔案最大30MB
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 30 * 1024 * 1024, maxRequestSize = 30 * 1024 * 1024)

// mvc中的控制器controller
public class WritingServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		System.out.println("WritingServlet 已執行");

		System.out.println("action = " + action);// for debug

		if ("Search_For_KeyWord_From_Member".equals(action)) {// 來自Writing_Home_Page.jsp的請求,關鍵字搜尋
			System.out.println("Search_For_KeyWord_From_Member  區塊 已執行");// for debug

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				Map<String, String[]> map = req.getParameterMap();
				System.out.println("Map是否為空 : "+map.isEmpty());
				// confirm whether map is empty or not
				System.out.println("1");

				/*************************** 2.開始複合查詢 ***************************************/
				WritingService writingSvc = new WritingService();
				List<WritingVO> writingVOList = writingSvc.getAll(map);
				System.out.println("2");

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/

				req.setAttribute("writingVOList", writingVOList);// 資料庫取出的writingVO物件,存入session
				String url = "/front-end/writing/Writing_Home_Page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 Writing_Home_Page.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
				failureView.forward(req, res);
			}

		}
		
		if ("Search_For_KeyWord_From_Shopper".equals(action)) {// 來自Writing_Shopper_Page.jsp的請求,關鍵字搜尋
			System.out.println("Search_For_KeyWord_From_Shopper  區塊 已執行");// for debug

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				Map<String, String[]> map = req.getParameterMap();
				System.out.println(map.isEmpty());// confirm whether map is
													// empty or not
				System.out.println("1");

				/*************************** 2.開始複合查詢 ***************************************/
				WritingService writingSvc = new WritingService();
				List<WritingVO> writingVOList = writingSvc.getAll(map);
				System.out.println("2");

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/

				session.setAttribute("writingVOList", writingVOList);// 資料庫取出的writingVO物件,存入session
				String url = "/front-end/writing/Writing_Shopper_Page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 Writing_Home_Page.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("Search_From_Time_From_Member".equals(action)) {// 來自listAllWriting.jsp的請求,用時間順序排列文章

			System.out.println("Search_From_Time  區塊 已執行");// for debug
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				Map<String, String[]> map = req.getParameterMap();
				System.out.println(map.isEmpty());// confirm whether map is
													// empty or not
				System.out.println("1");

				/*************************** 2.開始複合查詢 ***************************************/
				WritingService writingSvc = new WritingService();
				List<WritingVO> writingVOList = writingSvc.getAll(map);
				System.out.println("2");

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/

				session.setAttribute("writingVOList", writingVOList);// 資料庫取出的writingVO物件,存入session
				String url = "/front-end/writing/Writing_Home_Page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
				failureView.forward(req, res);
			}

		}
		
		if ("Search_From_Time_From_Shopper".equals(action)) {// 來自Writing_Shopper_Page.jsp的請求,用時間順序排列文章

			System.out.println("Search_From_Time_From_Shopper  區塊 已執行");// for debug
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				Map<String, String[]> map = req.getParameterMap();
				System.out.println(map.isEmpty());// confirm whether map is
													// empty or not
				System.out.println("1");

				/*************************** 2.開始複合查詢 ***************************************/
				WritingService writingSvc = new WritingService();
				List<WritingVO> writingVOList = writingSvc.getAll(map);
				System.out.println("2");

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/

				session.setAttribute("writingVOList", writingVOList);// 資料庫取出的writingVO物件,存入session
				String url = "/front-end/writing/Writing_Shopper_Page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
				failureView.forward(req, res);
			}

		}


		if ("getOne_For_Display".equals(action)) { // 來自Writing_Home_Page.jsp的請求
			System.out.println("getOne_For_Display 區塊 已執行");// for debug
			List<String> errorMsgs = new LinkedList<String>();
//			System.out.println("1");
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數-輸入格式的錯誤處理
				String wrt_no = req.getParameter("wrt_no");
				System.out.println(wrt_no);// 追蹤使用者輸入的值
				if (wrt_no == null || (wrt_no.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// 對輸入狀況進行流程處理判斷
				else {
					if (wrt_no.trim().length() != 10) {
						errorMsgs.add("文章編號為10碼");
					}
					if (!wrt_no.trim().matches("WRT\\d{7}")) {
						errorMsgs.add("文章編號格式為: WRT加上七位數字");
					} // Regular Expression

				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 2.開始查詢資料
				WritingService writingSvc = new WritingService();
				WritingVO writingVO = writingSvc.getOneWriting(wrt_no);
				if (writingVO == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 3.查詢完成，準備轉交(Send the Success view)

				session.setAttribute("writingVO", writingVO);// 資料庫取出的writingVO物件,存入req
				String url = "/front-end/writing/listOneWriting.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting.jsp
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Display_From_Shopper".equals(action)) { // 來自Writing_Shopper_Page.jsp的請求
			System.out.println("getOne_For_Display_From_Shopper 區塊 已執行");// for debug
			List<String> errorMsgs = new LinkedList<String>();
			System.out.println("1");
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數-輸入格式的錯誤處理
				String wrt_no = req.getParameter("wrt_no");
				System.out.println(wrt_no);// 追蹤使用者輸入的值
				if (wrt_no == null || (wrt_no.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// 對輸入狀況進行流程處理判斷
				else {
					if (wrt_no.trim().length() != 10) {
						errorMsgs.add("文章編號為10碼");
					}
					if (!wrt_no.trim().matches("WRT\\d{7}")) {
						errorMsgs.add("文章編號格式為: WRT加上七位數字");
					} // Regular Expression

				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 2.開始查詢資料
				WritingService writingSvc = new WritingService();
				WritingVO writingVO = writingSvc.getOneWriting(wrt_no);
				if (writingVO == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 3.查詢完成，準備轉交(Send the Success view)

				session.setAttribute("writingVO", writingVO);// 資料庫取出的writingVO物件,存入req
				String url = "/front-end/writing/listOneWriting_Shopper.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting.jsp
				successView.forward(req, res);
					System.out.println("現在已轉交至  : "  +  url);//for debug
				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		

		if ("getOne_For_Update".equals(action)) { // 來自update_writing_input.jsp的請求
			System.out.println("update_writing_input.jsp 區塊 已執行");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/* 1.接收請求參數-輸入格式的錯誤處理 */

				String wrt_no = new String(req.getParameter("wrt_no"));

				/* 2.開始修改資料 */

				WritingService writingSvc = new WritingService();
				WritingVO writingVO = writingSvc.getOneWriting(wrt_no);

				/* 3.查詢完成，準備轉交(Send the success view) */
				req.setAttribute("writingVO", writingVO);

				String url = "/front-end/writing/update_writing_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWriting.jsp

				successView.forward(req, res);
				return;

				/* 其他可能的錯誤處理 */
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/update_writing_input.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) { // 來自update_writing_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			WritingVO writingVO = new WritingVO();
			System.out.println("step1");// for debug

			try {
				/************ 1.接收請求參數 - 輸入格式的錯誤處理 ****************/

				String wrt_no = req.getParameter("wrt_no").trim();// *使用者無法更改
				String shop_no = req.getParameter("shop_no").trim();// *使用者無法更改
				System.out.println("step2");// for debug
				String wrt_title = req.getParameter("wrt_title");
				System.out.println("step3");// for debug
				if (wrt_title == null || wrt_title.trim().length() == 0) {
					errorMsgs.add("標題:請勿空白");
				}
				System.out.println("step4");// for debug
				String wrt_cont = req.getParameter("wrt_cont");
				if (wrt_cont == null || wrt_cont.trim().length() == 0) {
					errorMsgs.add("內文:請勿空白");
				}
				System.out.println("step5");// for debug
				String wrt_sta = req.getParameter("wrt_sta");
				System.out.println("step6");// for debug
				Date wrt_time = null;

				System.out.println("step7");// for debug

				Collection<Part> parts = req.getParts();
				byte[] wrt_photo = null;
				String wrt_photo_base64 = null;
				if (!req.getParameter("img").equals("")) {
					wrt_photo_base64 = req.getParameter("img");
					System.out.println("隱藏欄位送來字串的wrt_photo_base64!");// for
																		// debug
				} else {
					System.out.println("隱藏欄位送來字串的" + req.getParameter("img"));
					// for debug
				}

				for (Part part : parts) {
					String type = part.getContentType();
					// 處理input type = "file"的部分
					if (type != null) {
						// case1.使用者未上傳檔案
						if (type.equals("application/octet-stream")) {
							// case2.使用者未上傳檔案，且上次也未上傳檔案
							if (wrt_photo_base64 == null)
								errorMsgs.add("請上傳照片");
						} else {
							System.out.println(type);
							if (type.startsWith("image/")) {
								if (part.getSize() < (1024 * 1024)) {
									wrt_photo = getPictureByteArray(part.getInputStream());
									wrt_photo_base64 = Base64Encoder.encode(wrt_photo);
								}

								else {
									errorMsgs.add("照片需小於1MB");
								}
							} else {
								errorMsgs.add("照片格式錯誤，請上傳jpeg,png,gif檔案");
							}
						}
					}
					// case3.使用者有上傳檔案的情況

				}

				writingVO.setWrt_no(wrt_no);
				writingVO.setShop_no(shop_no);
				writingVO.setWrt_cont(wrt_cont);
				writingVO.setWrt_title(wrt_title);
				writingVO.setWrt_sta(wrt_sta);
				if (wrt_photo_base64 != null && wrt_photo == null)
					writingVO.setWrt_photo(wrt_photo);
				writingVO.setWrt_photo_base64(wrt_photo_base64);
				// Send the use back to the form, if there were errors
				System.out.println("step3");// for debug

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("writingVO", writingVO); // 含有輸入格式錯誤的writingVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/update_writing_input.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給update_writing_input.jsp");// for
																			// debug
					return; // 程式中斷
				}

				/*** 2.開始修改資料 ************/
				WritingService writingSvc = new WritingService();
				writingVO = writingSvc.updateWriting(wrt_no, shop_no, wrt_title, wrt_cont, wrt_photo, wrt_photo_base64,
						wrt_sta, wrt_time);

				/*** 3.修改完成,準備轉交(Send the Success view) ******/
				req.setAttribute("writingVO", writingVO);
				// 資料庫update成功後,正確的的writingVO物件,存入req
				String url = "/front-end/writing/listOneWriting.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWriting.jsp
				successView.forward(req, res);

				/****** 其他可能的錯誤處理 ************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/update_writing_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addWriting.jsp的請求

			System.out.println("insert區塊 已執行");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/** 1.接收請求參數 - 輸入格式的錯誤處理 **/

				String wrt_title = req.getParameter("wrt_title");
				System.out.println("step1");
				String wrt_cont = req.getParameter("wrt_cont");
				System.out.println("step2");
				String shop_no = req.getParameter("shop_no");
				System.out.println("step3");

				Collection<Part> parts = req.getParts();

				byte[] wrt_photo = null;

				String wrt_photo_base64 = null;
				if (wrt_cont == null || wrt_cont.trim().length() == 0) {
					errorMsgs.add("您沒有輸入內容文章唷!");
				}

				if (req.getParameter("wrt_photo_base64") == null) {
					// wrt_photo_base64 = req.getParameter("img");
					System.out.println("查無照片");// (幫助debug)
				} else {
					System.out.println("隱藏欄位送來的字串" + req.getParameter("wrt_photo_base64"));// (幫助debug)
					// wrt_photo_base64 = req.getParameter("wrt_photo_base64");
				}

				for (Part part : parts) {
					String type = part.getContentType();
					// 處理input type="file"的部分
					if (type != null) {
						// 這次沒上傳檔案的狀況
						if (type.equals("application/octet-stream")) {
							// 這次沒上傳且上次也沒上傳檔案的狀況
							if (wrt_photo_base64 == null)
								errorMsgs.add("請上傳照片");
						}
						// 這次有上傳檔案的狀況
						else {
							if (type.startsWith("image/")) {
								if (part.getSize() < (1024 * 1024)) {
									wrt_photo = getPictureByteArray(part.getInputStream());
									wrt_photo_base64 = Base64Encoder.encode(wrt_photo);
								} else {
									errorMsgs.add("照片需小於1MB");
								}
							} else {
								errorMsgs.add("照片格式錯誤，請上傳jpeg, png, gif檔案");
							}
						}
					}
				}
				System.out.println(wrt_title);
				System.out.println(wrt_cont);
				System.out.println(shop_no);

				WritingService writingSvc = new WritingService();
				WritingVO writingVO = new WritingVO();
				writingSvc.addWriting(shop_no, wrt_title, wrt_cont, wrt_photo, wrt_photo_base64);

				writingVO.setShop_no(shop_no);
				writingVO.setWrt_title(wrt_title);
				writingVO.setWrt_cont(wrt_cont);
				writingVO.setWrt_photo_base64(wrt_photo_base64);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("writingVO", writingVO); // 含有輸入格式錯誤的writingVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/addWriting.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給addWriting.jsp");// (幫助debug)
					return;
				}

				String url = "/front-end/writing/addWriting_Successful_Page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/addWriting.jsp");
				failureView.forward(req, res);
			}

		}

		if ("delete".equals(action)) {// Writing_Shopper_Page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/****** 1.接收請求參數 **********/
				String wrt_no = req.getParameter("wrt_no");

				/****** 2.開始刪除資料 **********/
				WritingService writingSvc = new WritingService();
				writingSvc.deleteWriting(wrt_no);

				/****** 3.刪除完成,準備轉交(Send the Success view) ******/
				String url = "/front-end/writing/Writing_Shopper_Page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/***** 其他可能的錯誤處理 ********/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/listAllWriting.jsp");
				failureView.forward(req, res);
			}
		}

		if ("listAllWriting".equals(action)) { // 來自Writing_Home_Page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				// 2.開始查詢資料
				WritingService writingSvc = new WritingService();
				List<WritingVO> writingVOList = writingSvc.getAll();
				if (writingVOList == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 3.查詢完成，準備轉交(Send the Success view)

				session.setAttribute("writingVOList", writingVOList);// 資料庫取出的writingVO物件,存入req
				String url = "/front-end/writing/Writing_Home_Page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting.jsp
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listAllWritingFromShop".equals(action)) { // 來自Writing_Shopper_Page.jsp的請求
			System.out.println("1");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				// 2.開始查詢資料
				WritingService writingSvc = new WritingService();
				List<WritingVO> writingVOList = writingSvc.getAll();
				if (writingVOList == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 3.查詢完成，準備轉交(Send the Success view)

				session.setAttribute("writingVOList", writingVOList);// 資料庫取出的writingVO物件,存入req
				String url = "/front-end/writing/Writing_Shopper_Page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting.jsp
				successView.forward(req, res);
					
				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
				failureView.forward(req, res);
			}
		}
			
		
		
		if ("Search_From_Time_From_Shopper".equals(action)) {// 來自Writing_Shopper_Page.jsp的請求,用時間順序排列文章

			System.out.println("Search_From_Time_From_Shopper  區塊 已執行");// for debug
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				Map<String, String[]> map = req.getParameterMap();
				System.out.println(map.isEmpty());// confirm whether map is
													// empty or not
				System.out.println("1");

				/*************************** 2.開始複合查詢 ***************************************/
				WritingService writingSvc = new WritingService();
				List<WritingVO> writingVOList = writingSvc.getAll(map);
				System.out.println("2");

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/

				session.setAttribute("writingVOList", writingVOList);// 資料庫取出的writingVO物件,存入session
				String url = "/front-end/writing/Writing_Shopper_Page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting.jsp
				successView.forward(req, res);
				return ;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
				failureView.forward(req, res);
			}

		}

	}

	// 從part得到使用者上傳的InputStream，再透過此自訂方法，轉成byte[]回傳
	public static byte[] getPictureByteArray(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // ByteArrayOutputStream有內建byte陣列透過write()方法寫入資料,toByteArray()方法回傳
		byte[] buffer = new byte[8192];
		int i;
		while ((i = is.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		is.close();

		return baos.toByteArray();
	}

	

}