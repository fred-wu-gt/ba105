package com.wri_rep.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.oreilly.servlet.Base64Encoder;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;
import com.wri_mes.model.*;
import com.wri_rep.model.Wri_repService;
import com.wri_rep.model.Wri_repVO;
import com.writing.model.WritingService;
import com.writing.model.WritingVO;

public class Wri_repServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		System.out.println("action = " + action);// for debug

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("getOne_For_Display 區塊 已執行");// for debug
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數-輸入格式的錯誤處理
				String wre_no = req.getParameter("wre_no");
				System.out.println(wre_no);// 追蹤使用者輸入的值
				if (wre_no == null || (wre_no.trim()).length() == 0) {
					errorMsgs.add("請輸入文章檢舉編號");
				}
				// 對輸入狀況進行流程處理判斷
				else {
					if (wre_no.trim().length() != 10) {
						errorMsgs.add("文章檢舉編號為10碼");
					}
					if (!wre_no.trim().matches("WRE\\d{7}")) {
						errorMsgs.add("文章檢舉編號格式為: WRE加上7位數字");
					} // Regular Expression

				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_rep/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 2.開始查詢資料
				Wri_repService writing_repSvc = new Wri_repService();
				Wri_repVO wri_repVO = writing_repSvc.getOneWriting_rep(wre_no);
				if (wri_repVO == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_rep/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 3.查詢完成，準備轉交(Send the Success view)

				req.setAttribute("wri_repVO", wri_repVO);// 資料庫取出的wri_mesVO物件,存入req
				String url = "/front-end/writing_rep/listOneWriting_rep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting_mes.jsp
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_rep/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自update_writing_rep_input.jsp的請求
			System.out.println("update_writing_rep_input.jsp 區塊 已執行");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/* 1.接收請求參數-輸入格式的錯誤處理 */

				String wre_no = new String(req.getParameter("wre_no"));

				/* 2.開始修改資料 */

				Wri_repService writing_repSvc = new Wri_repService();
				Wri_repVO wri_repVO = writing_repSvc.getOneWriting_rep(wre_no);

				/* 3.查詢完成，準備轉交(Send the success view) */
				req.setAttribute("wri_repVO", wri_repVO);

				String url = "/front-end/writing_rep/update_writing_rep_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWriting_rep.jsp

				successView.forward(req, res);
				return;

				/* 其他可能的錯誤處理 */
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_rep/update_writing_rep_input.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) { // 來自update_writing_rep_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Wri_repVO wri_repVO = new Wri_repVO();
			System.out.println("step1");// for debug

			try {
				/************ 1.接收請求參數 - 輸入格式的錯誤處理 ****************/

				String wre_no = req.getParameter("wre_no").trim();// *使用者無法更改
				System.out.println("step2");// for debug

				String mem_no = req.getParameter("mem_no").trim();// *使用者無法更改
				System.out.println("step3");// for debug

				String wrt_no = req.getParameter("wrt_no");// *使用者無法更改
				System.out.println("step4");// for debug

				String wre_rsn = req.getParameter("wre_rsn");
				System.out.println("step5");// for debug
				
				if (wre_rsn == null || wre_rsn.trim().length() == 0) {
					errorMsgs.add("文章檢舉原因:請勿空白");
				}
				
				String wre_stat = req.getParameter("wre_stat");			
				System.out.println("step6");// for debug
				
				if(wre_stat == null ||wre_stat.trim().length() ==0){
					errorMsgs.add("文章檢舉狀態:請勿空白");
				}
				
				
				String wre_cont = req.getParameter("wre_cont");

				if (wre_cont == null || wre_cont.trim().length() == 0) {
					errorMsgs.add("文章檢舉內容:請勿空白");
				}


				wri_repVO.setWre_no(wre_no);
				wri_repVO.setWrt_no(wrt_no);
				wri_repVO.setMem_no(mem_no);
				wri_repVO.setMem_no(wre_rsn);
				wri_repVO.setWre_cont(wre_cont);
				wri_repVO.setWre_stat(wre_stat);

				// Send the use back to the form, if there were errors
				System.out.println("step7");// for debug
				System.out.println(wre_no);
				System.out.println(wrt_no);
				System.out.println(mem_no);
				System.out.println(wre_rsn);
				System.out.println(wre_cont);
				System.out.println(wre_stat);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wri_repVO", wri_repVO); // 含有輸入格式錯誤的wri_repVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/writing_rep/update_writing_rep_input.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給update_writing_rep_input.jsp");// debug

					return; // 程式中斷
				}

				/*** 2.開始修改資料 ************/
				Wri_repService wri_repSvc = new Wri_repService();
				wri_repVO = wri_repSvc.updateWriting_rep(wre_no, wrt_no, mem_no, wre_rsn, wre_stat, wre_cont);

				/*** 3.修改完成,準備轉交(Send the Success view) ******/
				req.setAttribute("wri_repVO", wri_repVO);
				// 資料庫update成功後,正確的的wri_repVO物件,存入req
				String url = "/front-end/writing_rep/listOneWriting_rep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWriting_rep.jsp
				successView.forward(req, res);

				/****** 其他可能的錯誤處理 ************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_rep/update_writing_rep_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addWriting_rep.jsp的請求

			System.out.println("insert區塊 已執行");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/** 1.接收請求參數 - 輸入格式的錯誤處理 **/

				String wre_no = req.getParameter("wre_no");
				System.out.println("step1");
				String wrt_no = req.getParameter("wrt_no");
				System.out.println("step2");
				String mem_no = req.getParameter("mem_no");
				System.out.println("step3");
				String wre_cont = req.getParameter("wre_cont");
				System.out.println("step4");
				String wre_stat = req.getParameter("wre_stat");
				System.out.println("step5");
				String wre_rsn = req.getParameter("wre_rsn");

				if (wre_cont == null || wre_cont.trim().length() == 0) {
					errorMsgs.add("您沒有輸入檢舉內容唷!");
				}

				System.out.println(wre_no);
				System.out.println(wrt_no);
				System.out.println(mem_no);
				System.out.println(wre_rsn);
				System.out.println(wre_stat);
				System.out.println(wre_cont);

				Wri_repVO wri_repVO = new Wri_repVO();
				wri_repVO.setWre_no(wre_no);
				wri_repVO.setWrt_no(wrt_no);
				wri_repVO.setMem_no(mem_no);
				wri_repVO.setWre_rsn(wre_rsn);
				wri_repVO.setWre_stat(wre_stat);
				wri_repVO.setWre_cont(wre_cont);

				Wri_repService wri_repSvc = new Wri_repService();

				wri_repVO = wri_repSvc.addWriting_rep(wre_no, wrt_no, mem_no, wre_rsn, wre_stat, wre_cont);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wri_repVO", wri_repVO); // 含有輸入格式錯誤的wri_repVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_rep/addWriting_rep.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給addWriting_rep.jsp");// (幫助debug)
					return;
				}
				// 準備轉交
				String url = "/front-end/writing_rep/listAllWriting_rep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_rep/addWriting_rep.jsp");
				failureView.forward(req, res);
			}

		}
		if ("delete".equals(action)) {// 來自listAllWriting_rep.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/****** 1.接收請求參數 **********/
				String wre_no = req.getParameter("wre_no");

				/****** 2.開始刪除資料 **********/
				Wri_repService wri_repSvc = new Wri_repService();
				wri_repSvc.deleteWriting_rep(wre_no);

				/****** 3.刪除完成,準備轉交(Send the Success view) ******/
				String url = "/front-end/writing_rep/listAllWriting_rep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/***** 其他可能的錯誤處理 ********/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_rep/listAllWriting_rep.jsp");
				failureView.forward(req, res);
			}
		}
		
		//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if ("getWri_repsByWre_stat".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			HttpSession session = req.getSession();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
	
				List<Wri_repVO> wri_repVOList=null;
				String chooseGroup = req.getParameter("chooseGroup");
				String wre_stat=null;
				if(chooseGroup==null){ //直接從首頁送來時
					wre_stat="待審核";
				}else{                 //從下拉式選項送來時 
					if(chooseGroup.equals("waiting"))
						wre_stat="待審核";
					else if(chooseGroup.equals("pass"))
						wre_stat="審核通過";
					else if(chooseGroup.equals("fail"))
						wre_stat="審核不通過";
					
				}
				
				
	
				
				/***************************2.開始查詢資料*****************************************/
				Wri_repService writing_repSvc = new Wri_repService();
				if(wre_stat!=null){
					wri_repVOList = writing_repSvc.findByWre_stat(wre_stat);
				}else{
					wri_repVOList = writing_repSvc.getAll();
				}
				
				if (wri_repVOList == null) {
					errorMsgs.add("查無資料");
				}
	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/listAllWriRep.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				session.setAttribute("wri_repVOList", wri_repVOList);
				String url = "/back-end/report/listAllWriRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
	
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/listAllWriRep.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("updateStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
	
			HttpSession session = req.getSession();
			
			req.setAttribute("errorMsgs", errorMsgs);
			res.setContentType("text/plain"); 
	        req.setCharacterEncoding("utf-8");
	        res.setCharacterEncoding("utf-8");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String wre_no = req.getParameter("wre_no");//隱藏欄位送出
				String originalWre_stat = req.getParameter("originalWre_stat");//原狀態
				String wre_stat = req.getParameter("wre_stat");//下拉式選單得到
				String shop_no = req.getParameter("shop_no");
				String wrt_no = req.getParameter("wrt_no");
				System.out.println("*文章編號"+wrt_no+"所在清單群組為"+req.getParameter("chooseGroup"));
				System.out.println("   原狀態為"+originalWre_stat+"，新送出的狀態為"+wre_stat);
				/***************************2.開始查詢資料*****************************************/
				Wri_repService writing_repSvc = new Wri_repService();
				writing_repSvc.updateWre_stat(wre_stat, wre_no);
				
				List<Wri_repVO> wri_repVOList=null;
				String chooseGroup = req.getParameter("chooseGroup");
				String groupWre_stat=null;
				if(chooseGroup==null){ //直接從首頁送來時
					groupWre_stat="待審核";
				}else{                 //從下拉式選項送來時 
					if(chooseGroup.equals("waiting"))
						groupWre_stat="待審核";
					else if(chooseGroup.equals("pass"))
						groupWre_stat="審核通過";
					else if(chooseGroup.equals("fail"))
						groupWre_stat="審核不通過";
					
				}
				
				if(groupWre_stat!=null){
					wri_repVOList = writing_repSvc.findByWre_stat(groupWre_stat);
				}else{
					wri_repVOList = writing_repSvc.getAll();
				}
				session.setAttribute("wri_repVOList", wri_repVOList);//為了換頁重取資料時能查到正確資料才存的
				
				ShopService ShopSvc = new ShopService();
				ShopVO shopVO=ShopSvc.findByPrimaryKey(shop_no);
				int originalShop_point=shopVO.getShop_point();//商家原本的違規點數
				int shop_point=0;//商家最後的違規點數
				
				WritingService writingSvc=new WritingService();
				WritingVO writingVO=writingSvc.getOneWriting(wrt_no);
				String originalWrt_sta=writingVO.getWrt_sta();//文章原狀態:正常或隱藏
				String wrt_sta ="";//文章最後狀態:正常或隱藏
				PrintWriter out = res.getWriter();
				if(originalWre_stat.equals("審核通過")){//原狀態為審核通過，新送出的狀態為待審核或審核不通過，則違規記點-1，並將活動狀態2改為正常
					if(originalShop_point>0){
						shop_point=originalShop_point-1;
						ShopSvc.updateShop_point(shop_no, shop_point);
					}else{//originalShop_point==0
						shop_point=originalShop_point;
					}
					wrt_sta="正常";
					writingSvc.updateWrt_sta(wrt_sta, wrt_no);
				}else{//原狀態為待審核或審核不通過
					if(wre_stat.equals("審核通過")){//原狀態為待審核或審核不通過，新送出的狀態為審核通過，則違規記點+1，並將活動狀態2改為隱藏
						shop_point=originalShop_point+1;
						ShopSvc.updateShop_point(shop_no, shop_point);
						wrt_sta="隱藏";
						writingSvc.updateWrt_sta(wrt_sta, wrt_no);
					}else{//原狀態為待審核或審核不通過，新送出的狀態為審核不通過或待審核，則不修改違規記點，也不修改活動狀態2
						shop_point=originalShop_point;
						wrt_sta=originalWrt_sta;
					}
					
				}
				String shop_name = shopVO.getShop_name();
				String wrt_title=writingVO.getWrt_title();
				JSONObject obj = new JSONObject();
				obj.put("wre_stat", wre_stat);
				obj.put("wre_no", wre_no);
				obj.put("shop_name", shop_name);
				obj.put("originalShop_point", originalShop_point);
				obj.put("shop_point", shop_point);
				obj.put("wrt_title", wrt_title);
				obj.put("originalWrt_sta", originalWrt_sta);
				obj.put("wrt_sta", wrt_sta);
				System.out.println("送出成功 : "+wre_no+"。此篇文章檢舉 : "+wre_no+"，商家名稱 : "+shop_name+"，原違規點數 : "+originalShop_point+"，目前違規點數 : "+shop_point+"，文章標題 : "+wrt_title+"，原狀態 : "+originalWrt_sta+"，目前狀態 : "+wrt_sta);
				out.print(obj.toString());
				out.flush();
		        out.close();
				
				
	
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/listAllWriRep.jsp");
				failureView.forward(req, res);
			}
		}
//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	 
	}

}
