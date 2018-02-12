package com.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import com.member.model.*;
import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 30 * 1024 * 1024, maxRequestSize = 30 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	

	public MemberServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
		
	}

	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_id = req.getParameter("mem_id");
				//System.out.println(mem_id);
				if (mem_id == null || (mem_id.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_id1 = null;
				try {
					mem_id1 = new String(mem_id);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMember(mem_id);
				//System.out.println(memVO);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				} 
//				if(memVO != null){
//					errorMsgs.add("帳號重複");
//					
//				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
//				String mem_photo_base64 = Base64Encoder.encode(memVO.getMem_photo());
//				req.setAttribute("mem_photo_base64", mem_photo_base64);
				String url = "/front-end/member/listOneMember2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_id = req.getParameter("mem_id");
				//System.out.println(mem_id);
				/***************************2.開始查詢資料****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMember(mem_id);
				String photo=req.getParameter("img");
				//System.out.println("隱藏欄位送來空字串"+photo);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/member/update_Member_input2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Member_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_Member_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			    
				String mem_no = req.getParameter("mem_no").trim();
				
				String mem_id = req.getParameter("mem_id").trim();
				
				String mem_psw = req.getParameter("mem_psw").trim();
				String mem_pswReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,30}$";
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				}else if(!mem_psw.trim().matches(mem_pswReg)){
					errorMsgs.add("密碼設定: 只能是中、英文字母、數字和_ , 且長度必需在3到30之間");
				}
				
				
				String mem_name = req.getParameter("mem_name").trim();
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} 
				else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
	            }
				
				
				String mem_gen = req.getParameter("mem_gen");
				
				
				Date mem_bir = java.sql.Date.valueOf(req.getParameter("mem_bir").trim());
			
				
				
				String mem_email = req.getParameter("mem_email");

				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("電子郵件: 請勿空白");
				} 

				
				
				
				String mem_phone = req.getParameter("mem_phone");
				String mem_phoneReg = "^[(0-9_)]{9,10}$";
				if(mem_phone==null|| mem_phone.trim().length() == 0){
					errorMsgs.add("電話號碼: 請勿空白");
				}else if(!mem_phone.trim().matches(mem_phoneReg)){
					errorMsgs.add("電話號碼: 只能是數字長度必需在10");
				}
				
				
				String mem_loc = req.getParameter("mem_loc");
				String mem_locReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,30}$";
				if (mem_loc == null || mem_loc.trim().length() == 0) {
					errorMsgs.add("會員地址: 請勿空白");
				} else if(!mem_loc.trim().matches(mem_locReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員地址: 只能是中、英文字母、數字和_ , 且長度必需在3到30之間");
	            }
				
				
				String photo=req.getParameter("img");
				byte[] mem_photo=null;
				String mem_photo_base64=null;
				Part part = req.getPart("mem_photo");
					if(part!=null){
						String str = part.getContentType();
						if(str.equals("application/octet-stream")){
									    mem_photo_base64=photo;
										if(mem_photo_base64!=null && mem_photo==null)
											mem_photo=Base64Decoder.decodeToBytes(mem_photo_base64);		 					   
							 }
						else {
							if(str.startsWith("image/") ){
								if(part.getSize()<(10*1024 * 1024)){
									mem_photo = getPictureByteArray(part.getInputStream());
									mem_photo_base64=Base64Encoder.encode(mem_photo);
									
								}else{
									errorMsgs.add("照片需小於10MB");
								}
								
							}else{
								errorMsgs.add("照片格式錯誤，請上傳jpeg, png, gif檔案");
							}
							
						}	
					}
					
				
				
				//System.out.println(mem_photo_base64);
				System.out.println("=============================================");
				
				String mem_stat = req.getParameter("mem_stat");
				String mem_statReg = "^[(\u4e00-\u9fa5)]{1,10}$";
				if (mem_stat == null || mem_stat.trim().length() == 0) {
					errorMsgs.add("會員狀態: 請勿空白");
				} else if(!mem_stat.trim().matches(mem_statReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員狀態: 只能正常或違規 ");
	            }
				
				
				String mem_poin = req.getParameter("mem_poin");
				String mem_poinReg = "^[(0-9)]{1,10}$";
				if (mem_poin == null || mem_poin.trim().length() == 0) {
					errorMsgs.add("違規記點: 請勿空白");
				} else if(!mem_poin.trim().matches(mem_poinReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("違規記點: 只能是數字");
	            }
			
				
				String mem_val = req.getParameter("mem_val");
				String mem_valReg = "^[(0-9)]{1,30}$";
				if (mem_val == null || mem_val.trim().length() == 0) {
					errorMsgs.add("會員點數: 請勿空白");
				} else if(!mem_val.trim().matches(mem_valReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員點數: 只能是數字");
	            }
			
				
				MemberVO memVO = new MemberVO();
				memVO.setMem_no(mem_no);
				memVO.setMem_id(mem_id);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_name(mem_name);
				memVO.setMem_gen(mem_gen);
				memVO.setMem_bir(mem_bir);
				memVO.setMem_email(mem_email);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_loc(mem_loc);
				
				memVO.setMem_photo(mem_photo);
				memVO.setMem_photo_base64(mem_photo_base64);
				memVO.setMem_stat(mem_stat);
				memVO.setMem_poin(mem_poin);
				memVO.setMem_val(mem_val);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_Member_input2.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memSvc = new MemberService();
		
				memSvc.updateMember(mem_no,mem_id,mem_psw, mem_name, mem_gen, mem_bir,mem_email,mem_phone,mem_loc,
						mem_photo,mem_photo_base64,mem_stat,mem_poin,mem_val);
				System.out.println();
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				HttpSession session = req.getSession();
				//session.setAttribute("memberVO", memberVO);
				session.setAttribute("loginMemberVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				String url = "/front-end/member/listOneMember2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/update_Member_input2.jsp");
				failureView.forward(req, res);
			}
		}

 
		
		if ("delete".equals(action)) { // 來自listAllMember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_id = new String(req.getParameter("mem_id"));
				
				/***************************2.開始刪除資料***************************************/
				MemberService memSvc = new MemberService();
				memSvc.deleteMember(mem_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
	}
	
	//從part得到使用者上傳的InputStream，再透過此自訂方法，轉成byte[]回傳
		public static byte[] getPictureByteArray(InputStream in) throws IOException {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  //ByteArrayOutputStream有內建byte陣列透過write()方法寫入資料,toByteArray()方法回傳 
			byte[] buffer = new byte[8192];
			int i;
			while ((i = in.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			baos.close();
			in.close();

			return baos.toByteArray();
		}
	
	

}
