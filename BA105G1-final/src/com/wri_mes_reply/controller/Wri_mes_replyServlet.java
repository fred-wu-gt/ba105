package com.wri_mes_reply.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wri_mes_reply.model.Wri_mes_replyService;
import com.wri_mes_reply.model.Wri_mes_replyVO;
import com.shop.model.ShopVO;

public class Wri_mes_replyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action =  " + action);// for debug
		System.out.println("Wri_mes_replyServlet 已執行");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("getOne_For_Display 區塊 已經執行");// for debug
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// Store this set in the request scope ,in case we need to send the
			// ErrorPage view.

			try {
				// 1.getParameter時，輸入格式的錯誤處理
				String wmsgr_no = req.getParameter("wmsgr_no");
				System.out.println(wmsgr_no);// trace input from user
				if (wmsgr_no == null || (wmsgr_no.trim().length()) == 0) {
					errorMsgs.add("請輸入文章留言回覆編號");
				}
				// 流程判斷處理:
				else {
					if (wmsgr_no.trim().length() != 10) {
						errorMsgs.add("文章留言回覆編號為10碼");
					}
					if (!wmsgr_no.trim().matches("WMSGR\\d{5}")) {
						errorMsgs.add("文章留言回覆編號格式為: WMSGR加上5位數字");
					} // Regular Expression

				}

				// Send the use back to the form , if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/writing_mes_reply/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 2 . Querying info

				Wri_mes_replyService wri_mes_replySvc = new Wri_mes_replyService();
				Wri_mes_replyVO wri_mes_replyVO = wri_mes_replySvc.getOneWriting_mes_reply(wmsgr_no);
				if (wri_mes_replyVO == null) {
					errorMsgs.add("查無資料");
				}

				// send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/writing_mes_reply/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 3.finishing querying info ,send the success view
				req.setAttribute("wri_mes_replyVO", wri_mes_replyVO);// 資料庫取出的wri_mes_replyVO物件，存入req
				String url = "/front-end/writing_mes_reply/listOneWriting_mes_reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交listOneWriting_mes_reply.jsp
				successView.forward(req, res);

				// 其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料: " + e.getMessage());
				RequestDispatcher failureVies = req
						.getRequestDispatcher("/front-end/writing_mes_reply/select_page.jsp");
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自update_writing_mes_reply_input.jsp的請求
			System.out.println("update_writing_mes_reply_input.jsp 區塊 已執行");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/* 1.接收請求參數-輸入格式的錯誤處理 */

				String wmsgr_no = new String(req.getParameter("wmsgr_no"));

				/* 2.開始修改資料 */

				Wri_mes_replyService writing_mes_replySvc = new Wri_mes_replyService();
				Wri_mes_replyVO wri_mes_replyVO = writing_mes_replySvc.getOneWriting_mes_reply(wmsgr_no);

				/* 3.查詢完成，準備轉交(Send the success view) */
				req.setAttribute("wri_mes_replyVO", wri_mes_replyVO);

				String url = "/front-end/writing_mes_reply/update_writing_mes_reply_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWriting_mes_reply.jsp

				successView.forward(req, res);
				return;

				/* 其他可能的錯誤處理 */
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/writing_mes_reply/update_writing_mes_reply_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_writing_mes_reply_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Wri_mes_replyVO wri_mes_replyVO = new Wri_mes_replyVO();
			System.out.println("step1");// for debug

			try {
				/************ 1.接收請求參數 - 輸入格式的錯誤處理 ****************/

				String wmsgr_no = req.getParameter("wmsgr_no").trim();// *使用者無法更改
				System.out.println("step2");// for debug
				String wmsg_no = req.getParameter("wmsg_no").trim();
				System.out.println("step3");// for debug
				String shop_no = req.getParameter("shop_no");
				System.out.println("step4");// for debug

				String wcr_cont = req.getParameter("wcr_cont");
				if (wcr_cont == null || wcr_cont.trim().length() == 0) {
					errorMsgs.add("文章留言回覆內容:請勿空白");
				}
				System.out.println("step5");

				wri_mes_replyVO.setWmsgr_no(wmsgr_no);
				wri_mes_replyVO.setWmsg_no(wmsg_no);
				wri_mes_replyVO.setShop_no(shop_no);
				wri_mes_replyVO.setWcr_cont(wcr_cont);

				// Send the use back to the form, if there were errors
				System.out.println("step8");// for debug

				System.out.println(wmsgr_no);
				System.out.println(wmsg_no);
				System.out.println(shop_no);
				System.out.println(wcr_cont);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wri_mes_replyVO", wri_mes_replyVO); // 含有輸入格式錯誤的wri_mes_replyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/writing_mes_reply/update_writing_mes_reply_input.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給update_writing_mes_reply_input.jsp");// debug

					return; // 程式中斷
				}

				/*** 2.開始修改資料 ************/
				Wri_mes_replyService wri_mes_replySvc = new Wri_mes_replyService();
				wri_mes_replyVO = wri_mes_replySvc.updateWriting_mes_reply(wmsgr_no, wmsg_no, shop_no, wcr_cont);

				/*** 3.修改完成,準備轉交(Send the Success view) ******/
				req.setAttribute("wri_mes_replyVO", wri_mes_replyVO);
				// 資料庫update成功後,正確的的wri_mes_replyVO物件,存入req
				String url = "/front-end/writing_mes_reply/listOneWriting_mes_reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWriting_mes_reply.jsp
				successView.forward(req, res);

				/****** 其他可能的錯誤處理 ************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/writing_mes_reply/update_writing_mes_reply_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) {

			System.out.println("insert區塊 已執行");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/** 1.接收請求參數 - 輸入格式的錯誤處理 **/

				String wmsgr_no = req.getParameter("wmsgr_no");
				String wmsg_no = req.getParameter("wmsg_no");
				String shop_no = req.getParameter("shop_no");
				String wcr_cont = req.getParameter("wcr_cont");
				System.out.println("wmsgr_no :   " + wmsgr_no + "wmsg_no :  " + wmsg_no + "shop_no :  " + shop_no
						+ "wcr_cont : " + wcr_cont);

				if (wcr_cont == null || wcr_cont.trim().length() == 0) {
					errorMsgs.add("您沒有輸入文章留言回覆內容唷!");
				}

				Wri_mes_replyVO wri_mes_replyVO = new Wri_mes_replyVO();
				wri_mes_replyVO.setWmsgr_no(wmsgr_no);
				wri_mes_replyVO.setWmsg_no(wmsg_no);
				wri_mes_replyVO.setShop_no(shop_no);
				wri_mes_replyVO.setWcr_cont(wcr_cont);

				Wri_mes_replyService wri_mes_replySvc = new Wri_mes_replyService();

				wri_mes_replyVO = wri_mes_replySvc.addWriting_mes_reply(wmsgr_no, wmsg_no, shop_no, wcr_cont);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wri_mes_replyVO", wri_mes_replyVO); // 含有輸入格式錯誤的wri_mes_replyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/writing_mes_reply/addWriting_mes_reply.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給addWriting_mes_reply.jsp");// (幫助debug)
					return;
				}
				// 準備轉交
				String url = "/front-end/writing_mes_reply/listAllWriting_mes_reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/writing_mes_reply/addWriting_mes_reply.jsp");
				failureView.forward(req, res);
			}

		}
		if ("delete".equals(action)) {// 來自listAllWriting_mes_reply.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/****** 1.接收請求參數 **********/
				String wmsgr_no = req.getParameter("wmsgr_no");

				/****** 2.開始刪除資料 **********/
				Wri_mes_replyService wri_mes_replySvc = new Wri_mes_replyService();
				wri_mes_replySvc.deleteWriting_mes_reply(wmsgr_no);

				/****** 3.刪除完成,準備轉交(Send the Success view) ******/
				String url = "/front-end/writing_mes_reply/listAllWriting_mes_reply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/***** 其他可能的錯誤處理 ********/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/writing_mes_reply/listAllWriting_mes_reply.jsp");
				failureView.forward(req, res);
			}
		}

		if ("Reply_Message_From_Shopper".equals(action)) { // 來自listOneWriting_mes_reply_from_shopper.jsp的請求
			System.out.println("Reply_Message_From_Shopper區塊 已執行");
			
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			// 接收請求參數

			try {
				String shopVO = null;
				req.setAttribute("shopVO", shopVO);
				String wmsgr_no = req.getParameter("wmsgr_no");
				String wmsg_no = req.getParameter("wmsg_no");
				String wrt_no = req.getParameter("wrt_no");
				String shop_no = req.getParameter("shop_no");
				String wcr_cont = req.getParameter("wcr_cont");
				
				System.out.println("wmsgr_no:" + " " + wmsgr_no + " wmsg_no:" + " "+wmsg_no + " wrt_no:"+" "+ wrt_no  +" "+ " shop_no:"+" "+shop_no+" "+" wcr_cont:"+" "+wcr_cont);
				
				
				if (wcr_cont == null || wcr_cont.trim().length() == 0) {
					errorMsgs.add("您沒有輸入文章留言回覆內容唷!");
				}

				Wri_mes_replyVO wri_mes_replyVO = new Wri_mes_replyVO();
				wri_mes_replyVO.setWmsgr_no(wmsgr_no);
				wri_mes_replyVO.setWmsg_no(wmsg_no);
				wri_mes_replyVO.setShop_no(shop_no);
				wri_mes_replyVO.setWcr_cont(wcr_cont);

				Wri_mes_replyService wri_mes_replySvc = new Wri_mes_replyService();
				wri_mes_replyVO = wri_mes_replySvc.addWriting_mes_reply(wmsgr_no, wmsg_no, shop_no, wcr_cont);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wri_mes_replyVO", wri_mes_replyVO); // 含有輸入格式錯誤的wri_mes_replyVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給addWriting_mes_reply.jsp");// (幫助debug)
					return;
				}
				// 準備轉交
				res.sendRedirect(req.getContextPath()+"/front-end/writing/writing.do?action=getOne_For_Display_From_Shopper&wrt_no="+wrt_no);

				// 其他可能的錯誤處理

			}catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes_reply/addWriting_mes_reply.jsp");
					failureView.forward(req, res);
			}

		}

	}

}
