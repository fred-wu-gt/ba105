package com.shop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.commodity.model.CommodityService;
import com.commodity.model.CommodityVO;
import com.shop.model.MailService;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;
import com.shop.util.LatLng;

import sendMsg.Send;
import sendMsg.Sock2air;

import com.google.gson.JsonObject;
import com.ord_det.model.Ord_detVO;
import com.ord_mas.model.Ord_masService;
import com.ord_mas.model.Ord_masVO;

//使用mutiple-formdata時必須加上以下這段否則會一直取到null值
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		if ("insert".equals(action)) { // 來自shopRegister.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			/** 送出錯誤訊息 **/
			request.setAttribute("errorMsgs", errorMsgs);
			System.out.println("註冊1");

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String shop_name = request.getParameter("shop_name");
				String shop_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (shop_name == null || shop_name.trim().length() == 0) {
					errorMsgs.add("商家名稱: 請勿空白");
				} else if (!shop_name.trim().matches(shop_nameReg)) {
					errorMsgs.add("商家名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				System.out.println("註冊2");

				String shop_id = request.getParameter("shop_id");
				String shop_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{4,12}$";
				if (shop_id == null || shop_id.trim().length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				} else if (!shop_id.trim().matches(shop_idReg)) {
					errorMsgs.add("帳號: 只能是中、英文字母、數字和_ , 且長度必需在4到12之間");
				}

				System.out.println(shop_id);

				String shop_psw = request.getParameter("shop_psw");
				String shop_pswReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{4,12}$";
				if (shop_psw == null || shop_psw.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!shop_psw.trim().matches(shop_pswReg)) {
					errorMsgs.add("密碼: 只能是中、英文字母、數字和_ , 且長度必需在4到12之間");
				}

				String shop_email = request.getParameter("shop_email");
				String shop_emailReg = "^[^\\s]+@[^\\s]+\\.[^\\s]+$";
				if (shop_email == null || shop_email.trim().length() == 0) {
					errorMsgs.add("信箱: 請勿空白");
				}
				// else if (!shop_email.trim().matches(shop_emailReg)) {
				// errorMsgs.add("信箱: 英文字母、數字和_");
				// }

				String shop_phone = request.getParameter("shop_phone");
				String shop_phoneReg = "09\\d{8}";
				if (shop_phone == null || shop_phone.trim().length() == 0) {
					errorMsgs.add("手機號碼: 請勿空白");
				}
				// else if (!shop_phone.trim().matches(shop_phoneReg)) {
				// errorMsgs.add("手機號碼: 只能是數字 , 開頭為09 , 且長度必需為10");
				// }

				String shop_loc = request.getParameter("shop_loc");
				double shop_lat = LatLng.getlat(shop_loc);
				double shop_lon = LatLng.getlon(shop_loc);
				String shop_locReg = "^[(\u4e00-\u9fa5)(0-9)]+$";
				if (shop_loc == null || shop_loc.trim().length() == 0) {
					errorMsgs.add("商家地址: 請勿空白");
				} else if (!shop_loc.trim().matches(shop_locReg)) {
					errorMsgs.add("商家地址: 只能是中文和數字");
				}

				String shop_desc = request.getParameter("shop_desc");
				String shop_descReg = "^{50,200}$";
				if (shop_desc == null || shop_desc.trim().length() == 0) {
					errorMsgs.add("基本介紹: 請勿空白");
				}
				// else if (!shop_desc.trim().matches(shop_descReg)) {
				// errorMsgs.add("基本介紹: 長度必需在50到200之間");
				// }

				String shop_bank = request.getParameter("shop_bank");
				String shop_bankReg = "^[(\u4e00-\u9fa5)]+$";
				if (shop_bank == null || shop_bank.trim().length() == 0) {
					errorMsgs.add("匯款銀行: 請勿空白");
				} else if (!shop_bank.trim().matches(shop_bankReg)) {
					errorMsgs.add("匯款銀行: 只能是中文");
				}

				String shop_acc = request.getParameter("shop_acc");
				String shop_accReg = "^[(0-9)]+$";
				if (shop_acc == null || shop_acc.trim().length() == 0) {
					errorMsgs.add("匯款銀行帳號: 請勿空白");
				} else if (!shop_acc.trim().matches(shop_accReg)) {
					errorMsgs.add("匯款銀行帳號: 只能是數字 ");
				}
				System.out.println("註冊4");

				ShopVO shopVO = new ShopVO();
				shopVO.setShop_name(shop_name);
				shopVO.setShop_id(shop_id);
				shopVO.setShop_psw(shop_psw);
				shopVO.setShop_email(shop_email);
				shopVO.setShop_phone(shop_phone);
				shopVO.setShop_lat(shop_lat);
				System.out.println(shop_lat);
				shopVO.setShop_lon(shop_lon);
				System.out.println(shop_lon);
				
				shopVO.setShop_desc(shop_desc);
				shopVO.setShop_bank(shop_bank);
				shopVO.setShop_acc(shop_acc);

				/** 送圖片進資料庫 **/
				Part partPhoto = null;
				Part partProof = null;
				InputStream inputPhoto = null;
				InputStream inputProof = null;
				byte[] shop_photo = null;
				byte[] shop_proof = null;
				System.out.println("註冊5");

				partPhoto = request.getPart("shop_photo");
				partProof = request.getPart("shop_proof");
				inputPhoto = partPhoto.getInputStream();
				inputProof = partProof.getInputStream();
				shop_photo = new byte[inputPhoto.available()];
				shop_proof = new byte[inputProof.available()];
				inputPhoto.read(shop_photo);
				inputProof.read(shop_proof);
				if (partPhoto == null || partPhoto.getSize() == 0) {
					InputStream isPhoto = getServletContext().getResourceAsStream("fakeImage/SHOP-SHOP_PHOTO/1.png");
					shop_photo = new byte[isPhoto.available()];
					isPhoto.read(shop_photo);
					System.out.println(shop_photo.length);
					isPhoto.close();
				}
				if (partProof == null || partProof.getSize() == 0) {
					InputStream isProof = getServletContext().getResourceAsStream("fakeImage/SHOP-SHOP_PHOTO/1.png");
					shop_proof = new byte[isProof.available()];
					isProof.read(shop_proof);
					System.out.println(shop_proof.length);
					isProof.close();
				}
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("shopVO", shopVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shopRegister.jsp");
					failureView.forward(request, response);
					return;
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("shopVO", shopVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shopRegister.jsp");
					failureView.forward(request, response);
					return;
				}
				request.setAttribute("shopVO", shopVO);

				System.out.println("註冊6");

				/*************************** 2.開始新增資料 ***************************************/
				ShopService shopSvc = new ShopService();
				shopVO = shopSvc.addShopMember(shop_name, shop_id, shop_psw, shop_email, shop_phone, shop_loc,shop_lat , shop_lon ,
						shop_desc, shop_bank, shop_acc, shop_proof , shop_photo);

				System.out.println("註冊7");

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/index.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交shop_default.jsp
				successView.forward(request, response);

				System.out.println("註冊8");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shopRegister.jsp");
				failureView.forward(request, response);

				System.out.println("註冊9");

			}
		}


		if ("update".equals(action)) { // 來自shopUpdate.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String shop_id = request.getParameter("shop_id");
				String shop_no = request.getParameter("shop_no");

				String shop_name = request.getParameter("shop_name");
				String shop_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (shop_name == null || shop_name.trim().length() == 0) {
					errorMsgs.add("商家名稱: 請勿空白");
				} else if (!shop_name.trim().matches(shop_nameReg)) {
					errorMsgs.add("商家名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String shop_psw = request.getParameter("shop_psw");
				String shop_pswReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{8,12}$";
				if (shop_psw == null || shop_psw.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!shop_psw.trim().matches(shop_pswReg)) {
					errorMsgs.add("密碼: 只能是中、英文字母、數字和_ , 且長度必需在8到12之間");
				}

				String shop_email = request.getParameter("shop_email");
				String shop_emailReg = "^[^\\s]+@[^\\s]+\\.[^\\s]+$";
				if (shop_email == null || shop_email.trim().length() == 0) {
					errorMsgs.add("信箱: 請勿空白");
				}

				String shop_phone = request.getParameter("shop_phone");
				String shop_phoneReg = "09\\d{8}";
				if (shop_phone == null || shop_phone.trim().length() == 0) {
					errorMsgs.add("手機號碼: 請勿空白");
				}

				String shop_loc = request.getParameter("shop_loc");
				String shop_locReg = "^[(\u4e00-\u9fa5)(0-9)]+$";
				if (shop_loc == null || shop_loc.trim().length() == 0) {
					errorMsgs.add("商家地址: 請勿空白");
				} else if (!shop_loc.trim().matches(shop_locReg)) {
					errorMsgs.add("商家地址: 只能是中文和數字");
				}

				String shop_desc = request.getParameter("shop_desc");
				String shop_descReg = "^{50,200}$";
				if (shop_desc == null || shop_desc.trim().length() == 0) {
					errorMsgs.add("基本介紹: 請勿空白");
				}

				String shop_bank = request.getParameter("shop_bank");
				String shop_bankReg = "^[(\u4e00-\u9fa5)]+$";
				if (shop_bank == null || shop_bank.trim().length() == 0) {
					errorMsgs.add("匯款銀行: 請勿空白");
				} else if (!shop_bank.trim().matches(shop_bankReg)) {
					errorMsgs.add("匯款銀行: 只能是中文");
				}

				String shop_acc = request.getParameter("shop_acc");
				String shop_accReg = "^[(0-9)]+$";
				if (shop_acc == null || shop_acc.trim().length() == 0) {
					errorMsgs.add("匯款銀行帳號: 請勿空白");
				} else if (!shop_acc.trim().matches(shop_accReg)) {
					errorMsgs.add("匯款銀行帳號: 只能是數字 ");
				}

				ShopVO shopVO = new ShopVO();
				shopVO.setShop_name(shop_name);
				shopVO.setShop_id(shop_id);
				shopVO.setShop_psw(shop_psw);
				shopVO.setShop_email(shop_email);
				shopVO.setShop_phone(shop_phone);
				shopVO.setShop_loc(shop_loc);
				shopVO.setShop_desc(shop_desc);
				shopVO.setShop_bank(shop_bank);
				shopVO.setShop_acc(shop_acc);
				shopVO.setShop_no(shop_no);

				/** 送圖片進資料庫 **/
				Part partPhoto = null;
				Part partProof = null;
				InputStream inputPhoto = null;
				InputStream inputProof = null;
				byte[] shop_photo = null;
				byte[] shop_proof = null;

				partPhoto = request.getPart("shop_photo");
				partProof = request.getPart("shop_proof");
				inputPhoto = partPhoto.getInputStream();
				inputProof = partProof.getInputStream();
				shop_photo = new byte[inputPhoto.available()];
				shop_proof = new byte[inputProof.available()];
				inputPhoto.read(shop_photo);
				inputProof.read(shop_proof);

				if (partPhoto == null || partPhoto.getSize() == 0) {
					InputStream isPhoto = getServletContext().getResourceAsStream("SHOP-SHOP_PHOTO/fakeImage/1.png");
					shop_photo = new byte[isPhoto.available()];
					isPhoto.read(shop_photo);
					System.err.println(shop_photo.length);
					isPhoto.close();

				}
				if (partProof == null || partProof.getSize() == 0) {
					InputStream isProof = getServletContext().getResourceAsStream("SHOP-SHOP_PROOF/fakeImage/1.jpg");
					shop_proof = new byte[isProof.available()];
					isProof.read(shop_proof);
					System.err.println(shop_proof.length);
					isProof.close();
				}
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("shopVO", shopVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shopUpdate.jsp");
					failureView.forward(request, response);
					return;
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("shopVO", shopVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shopUpdate.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				ShopService shopSvc = new ShopService();
				shopVO = shopSvc.updateShop(shop_no, shop_name, shop_id, shop_psw, shop_email, shop_phone, shop_loc,
						shop_desc, shop_bank, shop_acc, shop_photo, shop_proof);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				// SweetAlert start
				String saveSuccess = "修改完成";
				request.setAttribute("saveSuccess", saveSuccess);
				session.setAttribute("shopVO", shopVO);
				String url = "/front-end/shop/shopUpdate.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交shop_default.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shopUpdate.jsp");
				failureView.forward(request, response);
			}
		}
		/** 登入 **/
		if (action.equals("login")) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			/** 1.接收請求參數，使用者登入輸入的值，如果等同於資料庫裡面的帳號及密碼，將傳至首頁，失敗則重導致登入頁面 **/
			try {
				String shop_id = request.getParameter("shop_id").trim();
				String shop_psw = request.getParameter("shop_psw").trim();
				if (shop_id == null || shop_id.trim().length() == 0) {
					errorMsgs.add("會員帳號必填");
					System.out.println("進來了1");
				}
				if (shop_psw == null || shop_psw.trim().length() == 0) {
					errorMsgs.add("會員密碼必填");
					System.out.println("進來了2");
				}

				ShopVO shopVO = new ShopVO();
				shopVO.setShop_id(shop_id);
				shopVO.setShop_psw(shop_psw);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/login.jsp");
					failureView.forward(request, response);
					System.out.println("進來了3");
					return;
				}

				ShopService shopSvc = new ShopService();

				shopVO = shopSvc.login(shop_id, shop_psw);
				if (shopVO == null) {
					errorMsgs.add("帳號或密碼輸入錯誤，請重新輸入");
					System.out.println("進來了4");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/login.jsp");
					failureView.forward(request, response);
					System.out.println("進來了5");
				}
				if (shop_id.equals(shopVO.getShop_id()) && shop_psw.equals(shopVO.getShop_psw())) { // 【帳號 , 密碼有效時,
																									// 才做以下工作】

					session.setAttribute("shopVO", shopVO);
					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							System.out.println("進來了6");
							session.removeAttribute("location"); // *工作2:
																	// 看看有無來源網頁
																	// (-->如有來源網頁:則重導至來源網頁)
																	// 如原本在購物途中卻沒有註冊或未登入則會重導至購物頁面

							response.sendRedirect(location);
							return;
						}
					} catch (Exception ignored) {
					}
					/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
					String url = request.getContextPath() + "/front-end/shop/shop_default.jsp";
					response.sendRedirect(url);
				} else {
					response.sendRedirect(request.getContextPath() + "/front-end/shop/login.jsp");
					System.out.println("進來了7");
				}

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage() + "<font color='red'>帳號密碼不能為空</font>");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/login.jsp");
				failureView.forward(request, response);
				System.out.println("進來了8");
			}

		}

		/** 登出 **/
		if (action.equals("logout")) {
			session.removeAttribute("shopVO");
			session.invalidate();

			response.sendRedirect(request.getContextPath() + "/front-end/shop/login.jsp");

		}

		/** 新增商品 **/

		if ("addProduct".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			System.out.println("1");

			/** 送出錯誤訊息 **/
			request.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			try {
				String com_name = (request.getParameter("com_name"));
				String com_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_()-/]{1,33}$";
				if (com_name == null || com_name.trim().length() == 0) {
					errorMsgs.add("商品名稱:請勿空白");
				} else if (!com_name.trim().matches(com_nameReg)) {
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_()/ , 且長度必需在1到33之間");
				}
				System.out.println("insert的com_name =" + com_name);

				String shop_no = request.getParameter("shop_no");
				System.out.println("insert的shop_no =" + shop_no);

				String fru_no = request.getParameter("fru_no").trim();
				System.out.println("insert的fru_no =" + fru_no);
				//
				Integer com_price = null;
				String com_priceS = request.getParameter("com_price");
				if (com_priceS == null || com_priceS.trim().length() == 0) {
					errorMsgs.add("商品價格:請勿空白");
				}

				try {
					com_price = new Integer(com_priceS);
				} catch (NumberFormatException e) {
					errorMsgs.add("商品價格:請填數字");
					com_price = 0;
				}

				System.out.println("insert的com_price =" + com_price);

				String com_weight = request.getParameter("com_weight");
				if (com_weight == null || com_weight.trim().length() == 0) {
					errorMsgs.add("商品規格:請勿空白");
				}
				System.out.println("insert的com_weight =" + com_weight);

				String com_remarks = request.getParameter("com_remarks");
				if (com_remarks == null || com_remarks.length() == 0) {
					errorMsgs.add("商品敘述:請勿空白");
				}
				System.out.println("insert的com_remarks =" + com_remarks);

				// String com_pic1S = req.getParameter("com_pic1");
				// if(com_pic1S == null){
				// errorMsgs.add("請至少上傳一張圖片到照片欄位1");
				// }

				Part part1 = (request.getPart("com_pic1"));
				InputStream in = null;
				byte[] com_pic1 = null;
				if (part1.getSize() == 0) {
					errorMsgs.add("請上傳照片到欄位1");
				}
				if (part1 != null) {
					String type = part1.getContentType();
					System.out.println("insert的com_pic1的ContentType =" + type);
					// file沒上傳檔案時回傳 =application/octet-stream
					// file上傳圖檔時回傳=image/png
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
				Part part2 = (request.getPart("com_pic2"));
				if (part2 != null) {
					String type = part2.getContentType();
					System.out.println("insert的com_pic2的ContentType =" + type);
					// file沒上傳檔案時回傳 =application/octet-stream
					// file上傳圖檔時回傳=image/png
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
						} else if (type.equals("application/octet-stream")) {

						} else {
							errorMsgs.add("照片請上傳jpeg, png, gif檔案");
						}
					}
				}

				Part part3 = (request.getPart("com_pic3"));
				byte[] com_pic3 = null;

				if (part3 != null) {
					String type = part3.getContentType();
					System.out.println("insert的com_pic3的ContentType =" + type);
					// file沒上傳檔案時回傳 =application/octet-stream
					// file上傳圖檔時回傳=image/png
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
						} else if (type.equals("application/octet-stream")) {

						} else {
							errorMsgs.add("照片請上傳jpeg, png, gif檔案");
						}
					}
				}

				String com_status = request.getParameter("com_status").trim();
				if (com_status == null || com_status.length() == 0) {
					errorMsgs.add("請選擇上/下架狀態");
				}
				System.out.println("insert的com_status =" + com_status);

				String com_storeS = request.getParameter("com_store");
				Integer com_store = null;
				if (com_storeS == null || com_storeS.trim().length() == 0) {
					errorMsgs.add("商品庫存量:請勿空白");
				} else {
					try {
						com_store = new Integer(com_storeS);
					} catch (NumberFormatException e) {
						errorMsgs.add("商品庫存請填數字");
						com_store = 0;
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
					request.setAttribute("comVO", comVO);// 將含有錯誤格式的comVo物件存入req
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/shop/addShopCommodity.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 *****************************************/
				CommodityService comSvc = new CommodityService();
				comVO = comSvc.addCom(com_name, shop_no, fru_no, com_price, com_weight, com_remarks, com_pic1, com_pic2,
						com_pic3, com_status, com_store);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 ****************************/
				// SweetAlert start
				String saveSuccess = "修改完成";
				request.setAttribute("saveSuccess", saveSuccess);
				request.setAttribute("comVO", comVO); // 資料庫insert成功後，將正確的comVO物件，存入req
				String url = "/front-end/shop/addShopCommodity.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/addShopCommodity.jsp");
				failureView.forward(request, response);
			}
		}

		/** 商品上下架(update) **/
		if ("updateCOM".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String com_no = request.getParameter("com_no");
				String com_name = request.getParameter("com_name");
				String shop_no = request.getParameter("shop_no");
				String fru_no = request.getParameter("fru_no");
				Integer com_price = null;
				try {
					com_price = new Integer(request.getParameter("com_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品價格請填數字");
				}
				String com_weight = (request.getParameter("com_weight").trim());
				if (com_weight == null || com_weight.length() == 0) {
					errorMsgs.add("商品規格請勿空白");
				}
				String com_remarks = request.getParameter("com_remarks").trim();
				if (com_remarks == null || com_remarks.length() == 0) {
					errorMsgs.add("商品敘述請勿空白");
				}
				Part part1 = (request.getPart("com_pic1"));
				InputStream in = null;
				byte[] com_pic1 = null;
				if (part1 != null) {
					String type1 = part1.getContentType();
					System.out.println("insert的com_pic1的ContentType =" + type1);

					if (type1 != null) {
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
				Part part2 = (request.getPart("com_pic2"));
				InputStream in2 = null;
				byte[] com_pic2 = null;
				if (part2 != null) {
					String type2 = part2.getContentType();
					if (type2 != null) {
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
				Part part3 = (request.getPart("com_pic3"));
				InputStream in3 = null;
				byte[] com_pic3 = null;
				if (part3 != null) {
					String type3 = part3.getContentType();
					if (type3 != null) {
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
				String com_status = request.getParameter("com_status");
				Integer com_store = null;
				try {
					com_store = new Integer(request.getParameter("com_store"));
				} catch (NumberFormatException e) {
					errorMsgs.add("商品庫存請填數字");
				}
				Integer com_peo = new Integer(request.getParameter("com_peo"));
				Double com_score = new Double(request.getParameter("com_score"));
				com_peo = com_peo++;
				CommodityService comSvc = new CommodityService();
				CommodityVO comVO = null;
				comSvc.updateCommodity(com_no, com_name, shop_no, fru_no, com_price, com_weight, com_remarks, com_pic1,
						com_pic2, com_pic3, com_status, com_store, com_score, com_peo);
				comVO = comSvc.getOneCommodityVO(com_no);
				// SweetAlert start
				String saveSuccess = "修改完成";
				request.setAttribute("saveSuccess", saveSuccess);
				request.setAttribute("comVO", comVO);
				String url = "/front-end/shop/allShopProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/allShopProduct.jsp");
				failureView.forward(request, response);
			}
		}

		// delete
		if ("deleteCom".equals(action)) {
			String com_no = request.getParameter("com_no");
			String fru_no = request.getParameter("fru_no");
			String shop_no = request.getParameter("shop_no");
			CommodityService comSvc = new CommodityService();
			comSvc.deleteCommoity(com_no, fru_no, shop_no);
			String url = "/front-end/shop/shop_default.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

		/** TODO **/
		if ("listEmps_ByCompositeQuery".equals(action)) {
			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();

				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (request.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(request.getParameterMap());
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = new HashMap<String, String[]>(request.getParameterMap());
				}
				System.out.println("複合查詢1 : " + map);
				/*************************** 2.開始複合查詢 ***************************************/
				CommodityService comSvc = new CommodityService();
				List<CommodityVO> list = comSvc.getAllComposite(map);
				System.out.println("複合查詢2 : " + list);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				session.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/shop/allShopProduct.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(request, response);
				System.out.println("複合查詢3");
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shop_default.jsp");
				failureView.forward(request, response);
			}

		}

		if ("getOne_For_Update".equals(action)) { // 來自allshopproduct.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String com_no = request.getParameter("com_no");
				String fru_no = request.getParameter("fru_no");

				/*************************** 2.開始查詢資料 ****************************************/
				CommodityService comSvc = new CommodityService();
				CommodityVO comVO = comSvc.getOneCommodityVO(com_no);
				System.out.println("查一個修改1 : " + com_no);
				/****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				request.setAttribute("comVO", com_no);
				String url = "/front-end/shop/shop_default.jsp";
				System.out.println("查一個修改2 : " + com_no);
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				System.out.println("查一個修改3 : " + com_no);
				/*
				 * =============================其他可能的錯誤處理=======================
				 * 
				 */
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料 :" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shop_default.jsp");
				failureView.forward(request, response);
			}
		}

		/** 連結至allshopproduct.jsp物件傳遞 **/
		if ("getOne_For_Display".equals(action)) {
			try {
				String shop_no = request.getParameter("shop_no");
				CommodityService comSvc = new CommodityService();
				CommodityVO comVO = comSvc.getOneCommodityVO(shop_no);
				System.out.println("getOne_For_Display的"+shop_no);
				session.setAttribute("comVO", comVO);
				String url = "/front-end/shop/allShopProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shop_default.jsp");
				failureView.forward(request, response);
			}
		}

		/** 修改com_remarksParam **/
		if (action.equals("saveCom_remarksParam")) {
			String com_remarks = request.getParameter("com_remarks");
			String com_no = request.getParameter("com_no");
			CommodityService comSvc = new CommodityService();
			comSvc.updateCom_remarksParam(com_remarks, com_no);
			CommodityVO comVO = comSvc.getCommodityVO(com_no);
			request.setAttribute("comVO", comVO);
			RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/allShopProduct.jsp");
			failureView.forward(request, response);
		}

		/**
		 * AJAX檢查會員註冊,資料庫帳號是否重複
		 * 
		 * @param:shop_id
		 **/
		if (action.equals("checkRegisteredID")) {
			System.out.println("1.ajax驗證");

			String shop_id = request.getParameter("shop_id").trim();
			ShopService shopSvc = new ShopService();

			System.out.println("2.ajax驗證");
			ShopVO shopVO = shopSvc.checkShopIdRepeat(shop_id);
			JsonObject jobj = new JsonObject();
			System.out.println("3.ajax驗證" + shop_id);
			if (shopVO == null || !request.getParameter("shop_id").equals(shopVO.getShop_id())) {
				System.out.println("4.ajax驗證" + shop_id);
				jobj.addProperty("result", "false");
				jobj.addProperty("message", "帳號不存在,可以註冊");
				System.out.println("5.ajax驗證" + shop_id);
				out.write(jobj.toString());
				out.flush();
				out.close();
				return;
			}
			jobj.addProperty("result", "true");
			jobj.addProperty("message", "帳號已存在,請重新更換帳號");
			out.write(jobj.toString());
			out.flush();
			out.close();
			return;
		}

		/** 連結至orderMaster.jsp **/
		if ("shopOrder".equals(action)) {
			try {
				String shop_no = request.getParameter("shop_no");
				Ord_masService ordSvc = new Ord_masService();
				List<Ord_masVO> list = ordSvc.getOrderMasByShop(shop_no);
				request.setAttribute("list", list);
				String url = "/front-end/shop/orderMaster.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shop_default.jsp");
				failureView.forward(request, response);
			}
		}
		/** 至order_detail **/
		if ("shopOrderDetail".equals(action)) {
			try {
				String ord_no = request.getParameter("ord_no");
				Ord_masService ordSvc = new Ord_masService();
				List<Ord_detVO> list = ordSvc.getOrderDetailByComNo(ord_no);
				System.out.println(ord_no);
				request.setAttribute("list", list);
				String url = "/front-end/shop/orderDetail.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/shop/shop_default.jsp");
				failureView.forward(request, response);
			}
		}
		/**寄信通知出貨**/
		if (action.equals("updateOrd_sta")) {
			String ord_sta = request.getParameter("ord_sta");
			String ord_no = request.getParameter("ord_no");
			if(ord_sta==null) {
				ord_sta =new String("已出貨");
			}
			//信件標題
			String subject = "果書商品出貨通知Email";
			//產生信件內文
			StringBuilder sb = new StringBuilder();
			sb.append("<html>您好:請點擊下方的連結<br>");
			sb.append("<a href=\"");
			sb.append(request.getScheme()+":/"+request.getServerName()+":"+request.getServerPort()); 
			sb.append(request.getContextPath()+"/front-end/shop/shop.do?action=shopOrder&ord_no="+ord_no);
			sb.append("\" > 回至果書 </a></html>" );
			//寄送信件
			MailService mailSvc = new MailService();
			mailSvc.sendMail("tibame0963@gmail.com", subject, sb.toString());

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 ************/
			Ord_masService ord_masSvc = new Ord_masService();
			ord_masSvc.updateOrd_sta(ord_sta,ord_no);
			Ord_masVO ord_masVO = ord_masSvc.getOneOrd_mas(ord_no);
			
			request.setAttribute("ord_masVO", ord_masVO);
			/**sweet**/
			String export = "出貨完成";
			request.setAttribute("export", export);
			String url = "/front-end/shop/orderMaster.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);		
		}
		
		
		
		//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if ("getShopsByShop_stat".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
	
				List<ShopVO> shopVOList=null;
				String chooseGroup = request.getParameter("chooseGroup");
				String shop_stat=null;
				if(chooseGroup==null){ //直接從首頁送來時
					shop_stat="待審核";
				}else{                 //從下拉式選項送來時 
					if(chooseGroup.equals("waiting"))
						shop_stat="待審核";
					else if(chooseGroup.equals("pass"))
						shop_stat="正常";
					else if(chooseGroup.equals("fail"))
						shop_stat="停權";
					
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShopService shopSvc = new ShopService();
				if(shop_stat!=null){
					shopVOList = shopSvc.findByShop_stat(shop_stat);
				}else{
					shopVOList = shopSvc.getAll();
				}
				
				if (shopVOList == null) {
					errorMsgs.add("查無資料");
				}
	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/cli_aut/shopAut.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				session.setAttribute("shopVOList", shopVOList);
				String url = "/back-end/cli_aut/shopAut.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); 
				successView.forward(request, response);
	
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/cli_aut/shopAut.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("updateStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			request.setAttribute("errorMsgs", errorMsgs);
			response.setContentType("text/plain"); 
	        request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String originalShop_stat = request.getParameter("originalShop_stat");//原狀態
				String shop_stat = request.getParameter("shop_stat");//下拉式選單得到
				String shop_no = request.getParameter("shop_no");
				String pswKey = request.getParameter("pswKey");
				String chooseGroup = request.getParameter("chooseGroup");
				System.out.println("*商家編號"+shop_no+"所在清單群組為"+chooseGroup);
				System.out.println("   原狀態為"+originalShop_stat+"，新送出的狀態為"+shop_stat);
				/***************************2.開始查詢資料*****************************************/
				ShopService shopSvc = new ShopService();
				shopSvc.updateShop_stat(shop_no, shop_stat);

				JSONObject obj = new JSONObject();
				obj.put("result", "success");
				if(pswKey!=null){
					if(pswKey.equals("20120325")){
						ShopVO shopVO=shopSvc.findByPrimaryKey(shop_no);
						String shop_phone=shopVO.getShop_phone();
						String shop_name=shopVO.getShop_name();
					 	Send se = new Send();
					 	String[] tel ={shop_phone};
					 	String[] myTel ={"0983557357"};
					 	String message = null;
					 	if("待審核".equals(originalShop_stat) && "正常".equals(shop_stat)){
					 		message = "商家「"+shop_name+"」您好，您已通過「果書」的後台審核，成功註冊成為「商家」，快快登入去上架商品吧!啾咪^.<";
					 		se.sendMessage(tel , message);//小心這會真的寄簡訊出去!!!!!!!!!!!!!!!!!!!!!!!!!!!
					 		se.sendMessage(myTel , "提醒您，有人使用果書後端系統發送簡訊一封至"+shop_phone);
					 		obj.put("result", "msgSuccess");
					 	}
					}
				}
				
				
				
				
				
				
				
				List<ShopVO> shopVOList=null;
				String groupShop_stat=null;
				if(chooseGroup==null){ //直接從首頁送來時
					groupShop_stat="待審核";
				}else{                 //從下拉式選項送來時 
					if(chooseGroup.equals("waiting"))
						groupShop_stat="待審核";
					else if(chooseGroup.equals("pass"))
						groupShop_stat="正常";
					else if(chooseGroup.equals("fail"))
						groupShop_stat="停權";
					
				}
				
				if(groupShop_stat!=null){
					shopVOList = shopSvc.findByShop_stat(groupShop_stat);
				}else{
					shopVOList = shopSvc.getAll();
				}
				session.setAttribute("shopVOList", shopVOList);//為了換頁重取資料時能查到正確資料才存的


				
				
				
				out.print(obj.toString());
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/cli_aut/shopAut.jsp");
				failureView.forward(request, response);
			}
		}
//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	       

			
	}
	

}
