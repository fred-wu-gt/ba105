
package com.wri_mes.controller;

import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.Base64Encoder;
import com.wri_mes.model.*;

public class Wri_mesServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		System.out.println("已執行 Wri_mesServlet  ");// for debug
		System.out.println("action = " + action);// for debug

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("getOne_For_Display 區塊 已執行");// for debug
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 1.接收請求參數-輸入格式的錯誤處理
				String wmsg_no = req.getParameter("wmsg_no");
				System.out.println(wmsg_no);// 追蹤使用者輸入的值
				if (wmsg_no == null || (wmsg_no.trim()).length() == 0) {
					errorMsgs.add("請輸入文章留言編號");
				}
				// 對輸入狀況進行流程處理判斷
				else {
					if (wmsg_no.trim().length() != 10) {
						errorMsgs.add("文章留言編號為10碼");
					}
					if (!wmsg_no.trim().matches("WMSG\\d{6}")) {
						errorMsgs.add("文章留言編號格式為: WMSG加上6位數字");
					} // Regular Expression

				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 2.開始查詢資料
				Wri_mesService writing_mesSvc = new Wri_mesService();
				Wri_mesVO wri_mesVO = writing_mesSvc.getOneWriting_mes(wmsg_no);
				if (wri_mesVO == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 3.查詢完成，準備轉交(Send the Success view)

				req.setAttribute("wri_mesVO", wri_mesVO);// 資料庫取出的wri_mesVO物件,存入req
				System.out.println(wri_mesVO == null);
				String url = "/front-end/writing_mes/listOneWriting_mes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting_mes.jsp
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {

				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAllWriting_mesByWrt_no".equals(action)) { // 來自的請求
			System.out.println("getAllWriting_mesByWrt_no 區塊 已執行");// for debug
			List<String> errorMsgs = new LinkedList<String>();
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
					if (!wrt_no.trim().matches("WMSG\\d{6}")) {
						errorMsgs.add("文章編號格式為: WRT加上7位數字");
					} // Regular Expression

				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 2.開始查詢資料
				Wri_mesService writing_mesSvc = new Wri_mesService();
				List<Wri_mesVO> wri_mesVO = writing_mesSvc.getAllWriting_mesByWrt_no(wrt_no);
				if (wri_mesVO == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 3.查詢完成，準備轉交(Send the Success view)

				req.setAttribute("wri_mesVO", wri_mesVO);// 資料庫取出的wri_mesVO物件,存入req
				System.out.println(wri_mesVO == null);
				String url = "/front-end/writing_mes/listOneWriting_mes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting_mes.jsp
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {

				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自update_writing_mes_input.jsp的請求
			System.out.println("update_writing_mes_input.jsp 區塊 已執行");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/* 1.接收請求參數-輸入格式的錯誤處理 */

				String wmsg_no = new String(req.getParameter("wmsg_no"));

				/* 2.開始修改資料 */

				Wri_mesService writing_mesSvc = new Wri_mesService();
				Wri_mesVO wri_mesVO = writing_mesSvc.getOneWriting_mes(wmsg_no);

				/* 3.查詢完成，準備轉交(Send the success view) */
				req.setAttribute("wri_mesVO", wri_mesVO);

				String url = "/front-end/writing_mes/update_writing_mes_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWriting_mes.jsp

				successView.forward(req, res);
				return;

				/* 其他可能的錯誤處理 */
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/writing_mes/update_writing_mes_input.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) { // 來自update_writing_mes_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Wri_mesVO wri_mesVO = new Wri_mesVO();
			System.out.println("step1");// for debug

			try {
				/************ 1.接收請求參數 - 輸入格式的錯誤處理 ****************/

				String wmsg_no = req.getParameter("wmsg_no").trim();// *使用者無法更改
				String mem_no = req.getParameter("mem_no").trim();// *使用者無法更改
				String wrt_no = req.getParameter("wrt_no");// *使用者無法更改
				System.out.println("step2");// for debug
				String wmsg_cont = req.getParameter("wmsg_cont");
				System.out.println("step3");// for debug
				if (wmsg_cont == null || wmsg_cont.trim().length() == 0) {
					errorMsgs.add("留言內容:請勿空白");
				}

				System.out.println("step5");// for debug
				String wmsg_stat = req.getParameter("wmsg_stat");
				System.out.println("step6");// for debug

				System.out.println("step7");// for debug

				wri_mesVO.setWmsg_no(wmsg_no);
				wri_mesVO.setMem_no(mem_no);
				wri_mesVO.setWmsg_cont(wmsg_cont);
				wri_mesVO.setWrt_no(wrt_no);
				wri_mesVO.setWmsg_stat(wmsg_stat);

				// Send the use back to the form, if there were errors
				System.out.println("step8");// for debug
				System.out.println(wmsg_no);
				System.out.println(mem_no);
				System.out.println(wmsg_cont);
				System.out.println(wrt_no);
				System.out.println(wmsg_stat);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wri_mesVO", wri_mesVO); // 含有輸入格式錯誤的wri_mesVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/writing_mes/update_writing_mes_input.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給update_writing_mes_input.jsp");// debug

					return; // 程式中斷
				}

				/*** 2.開始修改資料 ************/
				Wri_mesService wri_mesSvc = new Wri_mesService();
				wri_mesVO = wri_mesSvc.updateWriting_mes(wmsg_no, wrt_no, mem_no, wmsg_cont, wmsg_stat);

				/*** 3.修改完成,準備轉交(Send the Success view) ******/
				req.setAttribute("wri_mesVO", wri_mesVO);
				// 資料庫update成功後,正確的的wri_mesVO物件,存入req
				String url = "/front-end/writing_mes/listOneWriting_mes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWriting_mes.jsp
				successView.forward(req, res);

				/****** 其他可能的錯誤處理 ************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/writing_mes/update_writing_mes_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addWriting_mes.jsp的請求

			System.out.println("insert區塊 已執行");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/** 1.接收請求參數 - 輸入格式的錯誤處理 **/

				String wmsg_no = req.getParameter("wmsg_no");
				System.out.println("step1");
				String wrt_no = req.getParameter("wrt_no");
				System.out.println("step2");
				String mem_no = req.getParameter("mem_no");
				System.out.println("step3");
				String wmsg_cont = req.getParameter("wmsg_cont");
				System.out.println("step4");
				String wmsg_stat = req.getParameter("wmsg_stat");
				Timestamp wmsg_date = null;

				if (wmsg_cont == null || wmsg_cont.trim().length() == 0) {
					errorMsgs.add("您沒有輸入留言內容唷!");
				}

				System.out.println(wmsg_no);
				System.out.println(wrt_no);
				System.out.println(mem_no);
				System.out.println(wmsg_cont);
				System.out.println(wmsg_stat);

				Wri_mesVO wri_mesVO = new Wri_mesVO();
				wri_mesVO.setMem_no(mem_no);
				wri_mesVO.setWmsg_cont(wmsg_cont);
				wri_mesVO.setWmsg_date(wmsg_date);
				wri_mesVO.setWmsg_stat(wmsg_stat);
				wri_mesVO.setWrt_no(wrt_no);

				Wri_mesService wri_mesSvc = new Wri_mesService();

				wri_mesVO = wri_mesSvc.addWriting_mes(wrt_no, mem_no, wmsg_cont);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wri_mesVO", wri_mesVO); // 含有輸入格式錯誤的wri_mesVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/writing_mes/addWriting_mes.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給addWriting_mes.jsp");// (幫助debug)
					return;
				}
				// 準備轉交
				String url = "/front-end/writing/listaAllWriting.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes/addWriting_mes.jsp");
				failureView.forward(req, res);
			}

		}

		if ("Delete_Message_From_Shopper".equals(action)) {// 來自listOneWriting_Shopper.jsp
			System.out.println("Delete_Message_From_Shopper區塊  已執行");//for debug
				
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String wrt_no = req.getParameter("wrt_no");
			
			try {
				/****** 1.接收請求參數 **********/
				String wmsg_no = req.getParameter("wmsg_no");

				System.out.println("已接收請求參數  WRT_NO / WMSG_NO    :  " +  wrt_no  +  " / " +  wmsg_no );//for debug

				/****** 2.開始刪除資料 **********/
				Wri_mesService wri_mesSvc = new Wri_mesService();
				wri_mesSvc.deleteWriting_mes(wmsg_no);

				/****** 3.刪除完成,準備轉交(Send the Success view) ******/
				String url = "/front-end/writing/writing.do?action=getOne_For_Display_From_Shopper&wrt_no="+wrt_no;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/***** 其他可能的錯誤處理 ********/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/writing.do?action=getOne_For_Display_From_Shopper&wrt_no="+wrt_no);
				failureView.forward(req, res);
			}
			
		}

		if ("insert_from_writing_mes".equals(action)) {// 來自listOneWriting.jsp的請求

			System.out.println("insert_from_writing_mes  區塊已執行");// for debug

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try { // 接收請求參數

				String wrt_no = req.getParameter("wrt_no");
				System.out.println("wrt_no的值為 : " + wrt_no);// for debug
				String mem_no = req.getParameter("mem_no");
				System.out.println("mem_no的值為 : " + mem_no);// for debug
				String wmsg_cont = req.getParameter("wmsg_cont");
				System.out.println("wmsg_cont的值為 : " + wmsg_cont);// for debug

				if (wmsg_cont == null || wmsg_cont.trim().length() == 0) {
					System.out.println("您沒有輸入留言內容唷!");
				} // for debug


				Wri_mesVO wri_mesVO = new Wri_mesVO();
				wri_mesVO.setWrt_no(wrt_no);
				wri_mesVO.setMem_no(mem_no);
				wri_mesVO.setWmsg_cont(wmsg_cont);

				Wri_mesService wri_mesSvc = new Wri_mesService();
				wri_mesVO = wri_mesSvc.addWriting_mes(wrt_no, mem_no, wmsg_cont);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wri_mesVO", wri_mesVO); // 含有輸入格式錯誤的wri_mesVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes/listOneWriting_mes.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給listOneWriting.jsp");// (幫助debug)
					return;
				}

				res.sendRedirect(req.getContextPath()+"/front-end/writing/writing.do?action=getOne_For_Display&wrt_no="+wrt_no);
				// 其他可能的錯誤處理

//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/listOneWriting.jsp");
//				failureView.forward(req, res);
//				return;
//			}

		}

		

	}
}
