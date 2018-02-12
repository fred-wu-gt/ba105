package com.commodity.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.commodity.model.CommodityService;
import com.commodity.model.CommodityVO;
import com.oreilly.servlet.Base64Encoder;

/**
 * Servlet implementation class CommodityServlet
 */
@WebServlet("/CommodityServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CommodityServlet extends HttpServlet {
	Integer com_peo = null;
	private static final long serialVersionUID = 1L;
    
    public CommodityServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");//用來比對各種指令
		PrintWriter out = res.getWriter();
		
		/*
		 ===============================================INSERT方法開始================================================
		 */
		if("insert".equals(action)){  //來自addCommodity.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			try {
				String com_name = (req.getParameter("com_name"));
				String com_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_()-/]{1,33}$";
				if (com_name == null || com_name.trim().length() == 0) {
					errorMsgs.add("商品名稱:請勿空白");
				} else if (!com_name.trim().matches(com_nameReg)) {
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_()/ , 且長度必需在1到33之間");
				}
				System.out.println("insert的com_name =" + com_name);

				String shop_no = req.getParameter("shop_no");
				System.out.println("insert的shop_no =" + shop_no);

				String fru_no = req.getParameter("fru_no").trim();
				System.out.println("insert的fru_no =" + fru_no);
				//
				Integer com_price = null;
				String com_priceS = req.getParameter("com_price");
				if (com_priceS == null || com_priceS.trim().length() == 0) {
					errorMsgs.add("商品價格:請勿空白");
				}

				try {
					com_price = new Integer(com_priceS);
				} catch (NumberFormatException e) {
					errorMsgs.add("商品價格:請填數字");
					com_price =0;
				}

				System.out.println("insert的com_price =" + com_price);

				String com_weight = req.getParameter("com_weight");
				if (com_weight == null || com_weight.trim().length() == 0) {
					errorMsgs.add("商品規格:請勿空白");
				}
				System.out.println("insert的com_weight =" + com_weight);

				String com_remarks = req.getParameter("com_remarks");
				if (com_remarks == null || com_remarks.length() == 0) {
					errorMsgs.add("商品敘述:請勿空白");
				}
				System.out.println("insert的com_remarks =" + com_remarks);

				// String com_pic1S = req.getParameter("com_pic1");
				// if(com_pic1S == null){
				// errorMsgs.add("請至少上傳一張圖片到照片欄位1");
				// }
				
				Part part1 = (req.getPart("com_pic1"));
				InputStream in = null;
				byte[] com_pic1 = null;
				if (part1.getSize() == 0) {
					errorMsgs.add("請上傳照片到欄位1");
				}
				if (part1 != null) {
					String type = part1.getContentType();
					System.out.println("insert的com_pic1的ContentType =" + type);
					//file沒上傳檔案時回傳 =application/octet-stream
					//file上傳圖檔時回傳=image/png
					if (type != null) {
						// 代表有上傳檔案
						if (type.startsWith("image/")) {
							if (part1.getSize() < 1024 * 1024 * 5) {
								in = part1.getInputStream();
								com_pic1 = new byte[in.available()];
								in.read(com_pic1); // 用 in的方法讀進去buf
								in.close();
							} else {
								errorMsgs.add("照片需小於5MB");
							}
						} else {
							errorMsgs.add("照片請上傳jpeg, png, gif檔案");
						}

					}
				}
				
				byte[] com_pic2 = null;
				Part part2 = (req.getPart("com_pic2"));
				if (part2 != null) {
					String type = part2.getContentType();
					System.out.println("insert的com_pic2的ContentType =" + type);
					//file沒上傳檔案時回傳 =application/octet-stream
					//file上傳圖檔時回傳=image/png
					if (type != null) {
						// 代表有上傳檔案
						if (type.startsWith("image/")) {
							if (part2.getSize() < 1024 * 1024 * 5) {
								in = part2.getInputStream();
								com_pic2 = new byte[in.available()];
								in.read(com_pic2); // 用 in的方法讀進去buf
								in.close();
							} else {
								errorMsgs.add("照片需小於5MB");
							}
						} else if(type.equals("application/octet-stream")){
							
						}
						else {
							errorMsgs.add("照片請上傳jpeg, png, gif檔案");
						}

					}
				}
				
				Part part3 = (req.getPart("com_pic3"));	
				byte[] com_pic3 = null;
				
				if (part3 != null) {
					String type = part3.getContentType();
					System.out.println("insert的com_pic3的ContentType =" + type);
					//file沒上傳檔案時回傳 =application/octet-stream
					//file上傳圖檔時回傳=image/png
					if (type != null) {
						// 代表有上傳檔案	
						if (type.startsWith("image/")) {
							if (part3.getSize() < 1024 * 1024 * 5) {
								in = part3.getInputStream();
								com_pic3 = new byte[in.available()];
								in.read(com_pic3); // 用 in的方法讀進去buf
								in.close();
							} else {
								errorMsgs.add("照片需小於5MB");
							}
						} else if(type.equals("application/octet-stream")){
							
						} 
						else {
							errorMsgs.add("照片請上傳jpeg, png, gif檔案");
						}

					}
				}
				
//				
//				Part part = (req.getPart("com_pic1"));
//				InputStream in = null;
//				byte[] com_pic1 = null;
//				if (part != null) {
//					in = part.getInputStream();
//					com_pic1 = new byte[in.available()];
//					in.read(com_pic1); // 用 in的方法讀進去buf
//					in.close();
//				} else {
//					errorMsgs.add("請確認圖片上傳");
//				}
//
//				part = (req.getPart("com_pic2"));
//				in = part.getInputStream();
//				byte[] com_pic2 = new byte[in.available()];
//				in.read(com_pic2);
//				in.close();
//
//				part = (req.getPart("com_pic3"));
//				in = part.getInputStream();
//				byte[] com_pic3 = new byte[in.available()];
//				in.read(com_pic3);
//				in.close();
//				// System.out.println("insert的com_pic3 =" + com_pic3);

				// Timestamp com_time =
				// Timestamp.valueOf((req.getParameter("com_time")));
				// System.out.println("insert的com_time =" + com_time);

				String com_status = req.getParameter("com_status").trim();
				if (com_status == null || com_status.length() == 0) {
					errorMsgs.add("請選擇上/下架狀態");
				}
				System.out.println("insert的com_status =" + com_status);

				String com_storeS = req.getParameter("com_store");
				Integer com_store = null;
				if (com_storeS == null || com_storeS.trim().length() == 0) {
					errorMsgs.add("商品庫存量:請勿空白");
				} else {
					try {
						com_store = new Integer(com_storeS);
					} catch (NumberFormatException e) {
						errorMsgs.add("商品庫存請填數字");
						com_store=0;
					}
				}
				System.out.println("insert的com_store =" + com_store);

			
				CommodityVO comVO = new CommodityVO();
				comVO.setCom_name(com_name);
				comVO.setShop_no(shop_no);
				comVO.setFru_no(fru_no);
				comVO.setCom_price(com_price);
				comVO.setCom_weight(com_weight);
				comVO.setCom_remarks(com_remarks);
				comVO.setCom_pic1(com_pic1);
				comVO.setCom_pic2(com_pic2);
				comVO.setCom_pic3(com_pic3);
				comVO.setCom_status(com_status);
				comVO.setCom_store(com_store);
	
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("comVO", comVO);// 將含有錯誤格式的comVo物件存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/commodity/addCommodity.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 *****************************************/
				CommodityService comSvc = new CommodityService();
				comVO = comSvc.addCommodity(com_name, shop_no, fru_no, com_price, com_weight, com_remarks, com_pic1, com_pic2,
						com_pic3, com_status,com_store );

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 ****************************/
				req.setAttribute("comVO", comVO); // 資料庫insert成功後，將正確的comVO物件，存入req
				String url = "/front-end/commodity/listOneCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/commodity/addCommodity.jsp");
				failureView.forward(req, res);

			}

		}
		/*
		 ===============================================查一個方法開始================================================
		 */
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			try {
				String com_no = req.getParameter("com_no");
				 System.out.println("getOne_For_Display的com_no ="+com_no);
				if (com_no == null || com_no.trim().length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}

				// System.out.println(errorMsgs);
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/commodity/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/

				CommodityService comSvc = new CommodityService();
				CommodityVO comVO = comSvc.getOneCommodityVO(com_no);
				if (comVO == null) {
					errorMsgs.add("查無商品資料");
				}
				

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/commodity/select_page.jsp");
					failureView.forward(req, res);
					
					return;
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("comVO", comVO);
				String url = "/front-end/commodity/listOneCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
//				練習將byte[]轉成Base64
				
//				String com_pic1S = Base64Encoder.encode(comVO.getCom_pic1());
				// System.out.println(comVO.getCom_pic2());

//				if (comVO.getCom_pic2() != null) {
//					String com_pic2S = Base64Encoder.encode(comVO.getCom_pic2());
//					req.setAttribute("com_pic2S", com_pic2S);
//				}

//				if (comVO.getCom_pic3() != null) {
//					String com_pic3S = Base64Encoder.encode(comVO.getCom_pic3());
//					req.setAttribute("com_pic3S", com_pic3S);
////					System.out.println("com_pic3S" + com_pic3S);
//				}
				// String com_pic3S = Base64Encoder.encode(comVO.getCom_pic3());
//				req.setAttribute("com_pic1S", com_pic1S);
				// req.setAttribute("com_pic3S", com_pic3S);
				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				errorMsgs.add("無法取得資料 :" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Commodity/listAllCommonity.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)||"getOne_For_Update_Score".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("======TEST100=========");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String com_no = req.getParameter("com_no");

				/*************************** 2.開始查詢資料 ****************************************/
				CommodityService comSvc = new CommodityService();
				CommodityVO comVO = comSvc.getOneCommodityVO(com_no);

				/****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("comVO", comVO);
				String url= null;
				if("getOne_For_Update".equals(action))
					url=  "/front-end/commodity/update_commodity_input.jsp";
				else if("getOne_For_Update_Score".equals(action))
					url=  "/front-end/commodity/update_commodity_score.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				System.out.println("======TEST200=========");

				successView.forward(req, res);
				/*
				 * =============================其他可能的錯誤處理=======================
				 * 
				 */
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料 :" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/commodity/listAllCommonity.jsp");
				failureView.forward(req, res);
			}
		}
		

		/*
		 ===============================================UPDATE方法開始================================================
		 */
		if ("update".equals(action)) {// 來自update_commodity_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String com_no = req.getParameter("com_no");
				System.out.println("update的com_no =" + com_no);

				String com_name = (req.getParameter("com_name").trim());
				String com_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_()-/]{1,100}$";
				if (com_name == null || com_name.trim().length() == 0) {
					errorMsgs.add("商品名稱:請勿空白");
				} else if (!com_name.trim().matches(com_nameReg)) {
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_() , 且長度必需在1到100之間");
				}

				String shop_no = req.getParameter("shop_no");
				System.out.println("update的shop_no =" + shop_no);

				String fru_no = req.getParameter("fru_no").trim();

				Integer com_price = null;
				try {
					com_price = new Integer(req.getParameter("com_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品價格請填數字");
				}
				System.out.println("update的com_price =" + com_price);

				String com_weight = (req.getParameter("com_weight").trim());
				if (com_weight == null || com_weight.length() == 0) {
					errorMsgs.add("商品規格請勿空白");
				}

				String com_remarks = req.getParameter("com_remarks").trim();
				if (com_remarks == null || com_remarks.length() == 0) {
					errorMsgs.add("商品敘述請勿空白");
				}
				System.out.println("update的com_remarks =" + com_remarks);

				Part part1 = (req.getPart("com_pic1"));
				InputStream in = null;
				byte[] com_pic1 = null;
				// if (part1.getSize() == 0) {
				// errorMsgs.add("請上傳照片到欄位1");
				// }
				if (part1 != null) {
					String type1 = part1.getContentType();
					System.out.println("insert的com_pic1的ContentType =" + type1);
					// file沒上傳檔案時回傳 =application/octet-stream
					// file上傳圖檔時回傳=image/png
					if (type1 != null) {
						// 代表有上傳檔案
						if (type1.startsWith("image/")) {
							if (part1.getSize() < 1024 * 1024 * 5) {
								in = part1.getInputStream();
								com_pic1 = new byte[in.available()];
								in.read(com_pic1); // 用 in的方法讀進去buf
								in.close();
							} else {
								errorMsgs.add("照片需小於5MB");
							}

						} else if (type1.equals("application/octet-stream")) {

						} else {
							errorMsgs.add("照片請上傳jpeg, png, gif檔案");
						}

					}
				}
				Part part2 = (req.getPart("com_pic2"));
				InputStream in2 = null;
				byte[] com_pic2 = null;
				if (part2 != null) {
					String type2 = part2.getContentType();
					System.out.println("insert的com_pic1的ContentType =" + type2);
					// file沒上傳檔案時回傳 =application/octet-stream
					// file上傳圖檔時回傳=image/png
					if (type2 != null) {
						// 代表有上傳檔案
						if (type2.startsWith("image/")) {
							if (part2.getSize() < 1024 * 1024 * 5) {
								in = part2.getInputStream();
								com_pic2 = new byte[in.available()];
								in.read(com_pic2); // 用 in的方法讀進去buf
								in.close();
							} else {
								errorMsgs.add("照片需小於5MB");
							}
						} else if (type2.equals("application/octet-stream")) {

						} else {
							errorMsgs.add("照片請上傳jpeg, png, gif檔案");
						}

					}
				}

				Part part3 = (req.getPart("com_pic3"));
				InputStream in3 = null;
				byte[] com_pic3 = null;
				if (part3 != null) {
					String type3 = part3.getContentType();
					System.out.println("insert的com_pic1的ContentType =" + type3);
					// file沒上傳檔案時回傳 =application/octet-stream
					// file上傳圖檔時回傳=image/png
					if (type3 != null) {
						// 代表有上傳檔案
						if (type3.startsWith("image/")) {
							if (part3.getSize() < 1024 * 1024 * 5) {
								in = part3.getInputStream();
								com_pic3 = new byte[in.available()];
								in.read(com_pic3); // 用 in的方法讀進去buf
								in.close();
							} else {
								errorMsgs.add("照片需小於5MB");
							}
						} else if (type3.equals("application/octet-stream")) {

						} else {
							errorMsgs.add("照片請上傳jpeg, png, gif檔案");
						}

					}
				}

				// Part part = (req.getPart("com_pic1"));
				// InputStream in = null ;
				// byte[] com_pic1 = null ;
				// if (part != null){
				// in = part.getInputStream();
				// com_pic1 = new byte[in.available()];
				// in.read(com_pic1); //用 in的方法讀進去buf
				// in.close();
				// System.out.println("update的com_pic1 =" + com_pic1);
				// }else{
				// errorMsgs.add("請確認圖片上傳");
				// }
				//
				// Part part = (req.getPart("com_pic2"));
				// in = part.getInputStream();
				// byte[] com_pic2 = new byte[in.available()];
				// in.read(com_pic2);
				// in.close();
				// System.out.println("update的com_pic2 =" + com_pic2);
				//
				// part = (req.getPart("com_pic3"));
				// in = part.getInputStream();
				// byte[] com_pic3 = new byte[in.available()];
				// in.read(com_pic2);
				// in.close();
				// System.out.println("update的com_pic3 =" + com_pic3);

				Timestamp com_time = Timestamp.valueOf((req.getParameter("com_time")));
				System.out.println("update的com_time =" + com_time);

				String com_status = req.getParameter("com_status").trim();
				if (com_status == null || com_status.length() == 0) {
					errorMsgs.add("請選擇上/下架狀態");
				}
				System.out.println("update的com_status =" + com_status);
				Integer com_store = null;
				try {
					com_store = new Integer(req.getParameter("com_store").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品庫存請填數字");
				}
				System.out.println("update的com_store =" + com_store);

				String com_scoreS = (req.getParameter("com_score").trim());
				Integer com_peo = new Integer(req.getParameter("com_peo").trim());
				String com_scoreReg = "[0-5]{1}[.][0-9]{1}";
				if (com_scoreS == null) {
					com_scoreS = "0.0";
					System.out.println(com_scoreS == null);
				}
				if (!com_scoreS.matches(com_scoreReg)) {
					errorMsgs.add("評分請填1到5");
				}
				Double com_score = new Double(com_scoreS);
				com_peo = com_peo++;
				System.out.println("com_scoreS =" + com_scoreS);
				System.out.println("com_peo =" + com_peo);

				//用Service呼叫getOneCommodityVO，取得 資料庫comVO
				CommodityService comSvc = new CommodityService();
				CommodityVO comVO = comSvc.getOneCommodityVO(com_no);
				
				/*
				 * 如果此次更新沒有上傳檔案到Com_pic1
				 * 就用抓資料庫裡的Com_pic1放進本次要新增的欄位
				 * 
				 * Com_pic2、3 同上
				 */
				
				if (com_pic1 == null) {
					com_pic1 = comVO.getCom_pic1();
				}
				if (com_pic2 == null) {
					com_pic2 = comVO.getCom_pic2();
				}
				if (com_pic3 == null) {
					com_pic3 = comVO.getCom_pic3();
				}
				
				
				/*
				 * new一個新的VO，用來裝更新後的資料
				 */
				comVO = new CommodityVO();
				comVO.setCom_no(com_no);
				comVO.setCom_name(com_name);
				comVO.setShop_no(shop_no);
				comVO.setFru_no(fru_no);
				comVO.setCom_price(com_price);
				comVO.setCom_weight(com_weight);
				comVO.setCom_remarks(com_remarks);
				comVO.setCom_pic1(com_pic1);
				comVO.setCom_pic2(com_pic2);
				comVO.setCom_pic3(com_pic3);
				comVO.setCom_time(com_time);
				comVO.setCom_status(com_status);
				comVO.setCom_store(com_store);
				comVO.setCom_score(com_score);
				comVO.setCom_peo(com_peo);
				System.out.println("======TEST1======");

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("comVO", comVO);// 將含有錯誤格式的comVo物件存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/commodity/addCommodity.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				comSvc = new CommodityService();

				comSvc.updateCommodity(com_no, com_name, shop_no, fru_no, com_price, com_weight, com_remarks, com_pic1,
						com_pic2, com_pic3, com_time, com_status, com_store, com_score, com_peo);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 ****************************/
				req.setAttribute("comVO", comVO); // 資料庫update成功後，將正確的comVO物件，存入req	
				String url = "/front-end/commodity/listOneCommodity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				System.out.println("======TEST2======");
				successView.forward(req, res);

				// 練習byte[]轉Base64解法

				// String com_pic1S = Base64Encoder.encode(comVO.getCom_pic1());
				// req.setAttribute("com_pic1S", com_pic1S);
				//
				// if(comVO.getCom_pic2()!=null){
				// String com_pic2S = Base64Encoder.encode(comVO.getCom_pic2());
				// req.setAttribute("com_pic2S", com_pic2S);
				// }
				//
				// if(comVO.getCom_pic3()!=null){
				// String com_pic3S = Base64Encoder.encode(comVO.getCom_pic3());
				// req.setAttribute("com_pic3S", com_pic3S);
				// System.out.println("com_pic3S"+com_pic3S);
				// }
				//// String com_pic3S =
				// Base64Encoder.encode(comVO.getCom_pic3());
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/commodity/update_commodity_input.jsp");
				failureView.forward(req, res);

			}

		}	
//	==================================商品評分==================================
	
	if ("scoreCommodity".equals(action)) {// 來自update_commodity_input.jsp的請求
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			System.out.println("================進入scoreCommodity=================");
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			String com_no = req.getParameter("com_no");
			System.out.println("scoreCommodity的com_no ="+com_no);
			String com_name = (req.getParameter("com_name").trim());
			String shop_no = req.getParameter("shop_no");
			
			

//
			Integer com_price = null;
			com_price = new Integer(req.getParameter("com_price").trim());

			String com_weight = (req.getParameter("com_weight").trim());

			String com_remarks = req.getParameter("com_remarks").trim();
//			System.out.println("update的com_remarks ="+com_remarks);
					
			String com_scoreS =req.getParameter("com_score");
			
			if(com_scoreS != null){
//					com_scoreS = com_scoreS.trim();
//					String com_scoreReg = "[1-4]{1}[.][0-9]{1}";
//					if(!com_scoreS.matches(com_scoreReg)){
//						errorMsgs.add("評分請填1.0到5.0");
//					}
			}
			System.out.println("scoreCommodity的com_scoreS ="+com_scoreS);
			Double com_score = 0.0;
			try {
				com_score = new Double(com_scoreS);
			} catch (NumberFormatException e) {
				errorMsgs.add("請填數字1.0到5.0");
				e.printStackTrace();
			}
			System.out.println();
			//com_peo = 抓目前DB裡的人數
			com_peo = new Integer(req.getParameter("com_peo").trim());
			//成功評分後com_peo+1
			com_peo++;
			
			/*
			 * 計算新的平均分數，再存回資料庫    
			 * 算法:(目前資料庫裡存的總分*(目前人數-1)+本次評分)/目前人數
			 * */
			CommodityService cSvc = new CommodityService();
			//用service呼叫getOneCommodityVO，取得CommodityVO
			CommodityVO cVO = cSvc.getOneCommodityVO(com_no);
			double total_score = Double.valueOf(cVO.getCom_score());
			//取得CommodityVO後，呼叫getCom_score，取得儲存在DB的分數
			//跑公式取得新的平均分數
			com_score = (total_score*(com_peo-1) + com_score) / com_peo;
			
			System.out.println("com_score ="+com_score);
//			System.out.println("com_peo ="+com_peo);
			
			CommodityService comSvc = new CommodityService();
			CommodityVO comVO = comSvc.getOneCommodityVO(com_no);
	
			comVO = new CommodityVO();
			comVO.setCom_no(com_no);
			comVO.setCom_name(com_name);
			comVO.setShop_no(shop_no);
			comVO.setCom_price(com_price);
			comVO.setCom_weight(com_weight);
			comVO.setCom_remarks(com_remarks);
			comVO.setCom_score(com_score);
			comVO.setCom_peo(com_peo);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("comVO", comVO);// 將含有錯誤格式的comVo物件存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/commodity/update_commodity_score.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始修改資料 *****************************************/
			 comSvc = new CommodityService();
			 comSvc.scoreCommodity(com_score, com_peo, com_no);
			
			/***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 ****************************/
			req.setAttribute("comVO", comVO); // 資料庫update成功後，將正確的comVO物件，存入req
			String url = "/front-end/commodity/OneCommodityDetil.jsp?com_no="+comVO.getCom_no();
			System.out.println("url" +url);
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/commodity/update_commodity_score.jsp");
				failureView.forward(req, res);
			}			
		}
	
	
		if ("listEmps_ByCompositeQuery".equals(action)) { // 來自listAllCommodityBS.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
					System.out.println("===========複合查詢開始==========");
				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				Map<String, String[]> map = req.getParameterMap();
				System.out.println(map.get("com_name").length);
				

				/*************************** 2.開始複合查詢 ***************************************/
				CommodityService comSvc = new CommodityService();
				List<CommodityVO> list = comSvc.getAll(map);
System.out.println("list ="+list.size());
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("listCommodityByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/shopping/listCommodityByCompositeQuery.jsp"); // 成功轉交listCommodityByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shopping/listAllCommodityBS.jsp");
				failureView.forward(req, res);
			}
		}
	}

}



