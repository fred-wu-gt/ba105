package com.wri_like.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wri_like.model.Wri_likeService;
import com.wri_like.model.Wri_likeVO;

public class Wri_likeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action = " + action);

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("getOne_For_Display 區塊 已執行");// for debug
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

				} // 對輸入狀況進行流程處理判斷
				else {
					if (wrt_no.trim().length() != 10) {
						errorMsgs.add("文章編號為10碼");
					}
					if (!wrt_no.trim().matches("WRT\\d{7}")) {
						errorMsgs.add("文章編號格式為: WRT加上7位數字");
					} // Regular Expression

				} // Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_like/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 2.開始查詢資料
				Wri_likeService writing_likeSvc = new Wri_likeService();
				List<Wri_likeVO> wri_likeVO = writing_likeSvc.findByWrt_no(wrt_no);
				if (wri_likeVO == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form, if there were errors

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_like/select_page.jsp");
					failureView.forward(req, res);
					return; // break;
				}

				// 3.查詢完成，準備轉交(Send the Success view)

				req.setAttribute("wri_likeVO", wri_likeVO);// 資料庫取出的wri_likeVO物件,存入req
				String url = "/writing_like/listOneWriting_like.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交 listOneWriting_mes.jsp
				successView.forward(req, res);

				// 其他可能的錯誤處理

			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_like/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("delete_Man_fun".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String wrt_no = req.getParameter("wrt_no");
				String mem_no = req.getParameter("mem_no");
				/*************************** 2.開始刪除資料 ***************************************/
				Wri_likeService wri_likeSvc = new Wri_likeService();
				List<Wri_likeVO> wri_likeVOList = wri_likeSvc.findByWrt_no(wrt_no);
				for (Wri_likeVO wri_likeVO : wri_likeVOList) {
					wri_likeSvc.deleteWriting_like(wrt_no, wri_likeVO.getWrt_no());
				}
				wri_likeSvc.deleteWriting_like(wrt_no, mem_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/writing_like/listAllWri_like.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 刪除成功後,成功轉交回到/writing_like/listAllWri_like.jsp

				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/writing_like/listAllWri_like.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
