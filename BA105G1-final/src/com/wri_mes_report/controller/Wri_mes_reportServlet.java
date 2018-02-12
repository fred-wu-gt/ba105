package com.wri_mes_report.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wri_mes.model.Wri_mesService;
import com.wri_mes.model.Wri_mesVO;
import com.wri_mes_report.model.Wri_mes_reportService;
import com.wri_mes_report.model.Wri_mes_reportVO;

public class Wri_mes_reportServlet extends HttpServlet {
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
				String wmrpt_no = req.getParameter("wmrpt_no");
				System.out.println(wmrpt_no);// 追蹤使用者輸入的值
				if (wmrpt_no == null || (wmrpt_no.trim()).length() == 0) {
					errorMsgs.add("請輸入文章留言檢舉編號");
				}
				// 對輸入狀況進行流程處理判斷
				else {
					if (wmrpt_no.trim().length() != 10) {
						errorMsgs.add("文章留言檢舉編號為10碼");
					}
					if (!wmrpt_no.trim().matches("WMRPT\\d{5}")) {
						errorMsgs.add("文章留言檢舉編號格式為: WMRPT加上5位數字");
					} // Regular Expression

				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes_report/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 2.開始查詢資料
				Wri_mes_reportService writing_mes_reportSvc = new Wri_mes_reportService();
				Wri_mes_reportVO wri_mes_reportVO = writing_mes_reportSvc.getOneWriting_mes_report(wmrpt_no);
				if (wri_mes_reportVO == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes_report/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 3.查詢完成，準備轉交(Send the Success view)

				req.setAttribute("wri_mes_reportVO", wri_mes_reportVO);// 資料庫取出的wri_mes_reportVO物件,存入req
				String url = "/front-end/writing_mes_report/listOneWriting_mes_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting_mes_report.jsp
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_mes_report/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自update_writing_mes_report_input.jsp的請求
			System.out.println("update_writing_mes_report_input.jsp 區塊 已執行");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send
			// ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/* 1.接收請求參數-輸入格式的錯誤處理 */

				String wmrpt_no = new String(req.getParameter("wmrpt_no"));

				/* 2.開始修改資料 */

				Wri_mes_reportService writing_mes_reportSvc = new Wri_mes_reportService();
				Wri_mes_reportVO wri_mes_reportVO = writing_mes_reportSvc.getOneWriting_mes_report(wmrpt_no);

				/* 3.查詢完成，準備轉交(Send the success view) */
				req.setAttribute("wri_mes_reportVO", wri_mes_reportVO);

				String url = "/front-end/writing_mes_report/update_writing_mes_report_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWriting_mes_report.jsp

				successView.forward(req, res);
				return;

				/* 其他可能的錯誤處理 */
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/writing_mes_report/update_writing_mes_report_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_writing_mes_report_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Wri_mes_reportVO wri_mes_reportVO = new Wri_mes_reportVO();
			System.out.println("step1");// for debug

			try {
				/************ 1.接收請求參數 - 輸入格式的錯誤處理 ****************/

				String wmrpt_no = req.getParameter("wmrpt_no").trim();// *使用者無法更改
				String mem_no = req.getParameter("mem_no").trim();// *使用者無法更改

				System.out.println("step2");// for debug
				String wmrpt_cont = req.getParameter("wmrpt_cont");
				System.out.println("step3");// for debug
				if (wmrpt_cont == null || wmrpt_cont.trim().length() == 0) {
					errorMsgs.add("留言內容:請勿空白");
				}
				String wmsg_no = req.getParameter("wmsg_no");
				System.out.println("step5");// for debug
				String wmrpt_stat = req.getParameter("wmrpt_stat");
				System.out.println("step6");// for debug
				String wmrpt_rsn = req.getParameter("wmrpt_rsn");
				System.out.println("step7");// for debug

				wri_mes_reportVO.setWmrpt_no(wmrpt_no);
				wri_mes_reportVO.setWmsg_no(wmsg_no);
				wri_mes_reportVO.setMem_no(mem_no);
				wri_mes_reportVO.setWmrpt_rsn(wmrpt_rsn);
				wri_mes_reportVO.setWmrpt_cont(wmrpt_cont);
				wri_mes_reportVO.setWmrpt_stat(wmrpt_stat);

				// Send the use back to the form, if there were errors
				System.out.println("step8");// for debug
				System.out.println(wmrpt_no);
				System.out.println(wmsg_no);
				System.out.println(mem_no);
				System.out.println(wmrpt_rsn);
				System.out.println(wmrpt_cont);
				System.out.println(wmrpt_stat);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wri_mes_reportVO", wri_mes_reportVO); // 含有輸入格式錯誤的wri_mes_reportVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/writing_mes_report/update_writing_mes_report_input.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給update_writing_mes_report_input.jsp");// debug

					return; // 程式中斷
				}

				/*** 2.開始修改資料 ************/
				Wri_mes_reportService wri_mes_reportSvc = new Wri_mes_reportService();
				wri_mes_reportVO = wri_mes_reportSvc.updateWriting_mes_report(wmrpt_no, wmsg_no, mem_no, wmrpt_rsn,
						wmrpt_stat, wmrpt_cont);

				/*** 3.修改完成,準備轉交(Send the Success view) ******/
				req.setAttribute("wri_mes_reportVO", wri_mes_reportVO);
				// 資料庫update成功後,正確的的wri_mes_reportVO物件,存入req
				String url = "/front-end/writing_mes_report/listOneWriting_mes_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWriting_mes_report.jsp
				successView.forward(req, res);

				/****** 其他可能的錯誤處理 ************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/writing_mes_report/update_writing_mes_report_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addWriting_mes_report.jsp的請求

			System.out.println("insert區塊 已執行");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/** 1.接收請求參數 - 輸入格式的錯誤處理 **/

				String wmrpt_no = req.getParameter("wmrpt_no");
				System.out.println("step1");
				String wmsg_no = req.getParameter("wmsg_no");
				System.out.println("step2");
				String mem_no = req.getParameter("mem_no");
				System.out.println("step3");
				String wmrpt_rsn = req.getParameter("wmrpt_rsn");
				System.out.println("step4");
				String wmrpt_cont = req.getParameter("wmrpt_cont");
				System.out.println("step5");
				String wmrpt_stat = req.getParameter("wmrpt_stat");
				System.out.println("step6");

				if (wmrpt_cont == null || wmrpt_cont.trim().length() == 0) {
					errorMsgs.add("您沒有輸入留言內容唷!");
				}

				System.out.println(wmrpt_no);
				System.out.println(wmsg_no);
				System.out.println(mem_no);
				System.out.println(wmrpt_rsn);
				System.out.println(wmrpt_stat);
				System.out.println(wmrpt_cont);

				Wri_mes_reportVO wri_mes_reportVO = new Wri_mes_reportVO();
				wri_mes_reportVO.setWmrpt_no(wmrpt_no);
				wri_mes_reportVO.setWmsg_no(wmsg_no);
				wri_mes_reportVO.setMem_no(mem_no);
				wri_mes_reportVO.setWmrpt_rsn(wmrpt_rsn);
				wri_mes_reportVO.setWmrpt_stat(wmrpt_stat);
				wri_mes_reportVO.setWmrpt_cont(wmrpt_cont);

				Wri_mes_reportService wri_mes_reportSvc = new Wri_mes_reportService();

				wri_mes_reportVO = wri_mes_reportSvc.addWriting_mes_report(wmrpt_no, wmsg_no, mem_no, wmrpt_rsn,
						wmrpt_stat, wmrpt_cont);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wri_mes_reportVO", wri_mes_reportVO); // 含有輸入格式錯誤的wri_mes_reportVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/writing_mes_report/addWriting_mes_report.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給addWriting_mes_report.jsp");// (幫助debug)
					return;
				}
				// 準備轉交
				String url = "/front-end/writing_mes_report/listAllWriting_mes_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/writing_mes_report/addWriting_mes_report.jsp");
				failureView.forward(req, res);
			}

		}
		if ("delete".equals(action)) {// 來自listAllWriting_mes_report.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/****** 1.接收請求參數 **********/
				String wmrpt_no = req.getParameter("wmrpt_no");

				/****** 2.開始刪除資料 **********/
				Wri_mes_reportService wri_mes_reportSvc = new Wri_mes_reportService();
				wri_mes_reportSvc.deleteWriting_mes_report(wmrpt_no);

				/****** 3.刪除完成,準備轉交(Send the Success view) ******/
				String url = "/front-end/writing_mes_report/listAllWriting_mes_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/***** 其他可能的錯誤處理 ********/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/writing_mes_report/listAllWriting_mes_report.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
