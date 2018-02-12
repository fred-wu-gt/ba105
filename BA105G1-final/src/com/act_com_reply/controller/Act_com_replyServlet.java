package com.act_com_reply.controller;
import com.act_com_reply.model.Act_com_replyService;
import com.act_com_reply.model.Act_com_replyVO;
import com.act_com_report.model.*;
import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Act_com_replyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		if ("getOneActCom_For_reply".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.****************************************/
				String act_no = req.getParameter("act_no").trim();
				/*************************** 3.(Send the Success view) ************/
				//Bootstrap_modal
				boolean openModal_for_reply=true;
				req.setAttribute("openModal_for_reply",openModal_for_reply );
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.findByActNo(act_no);
				req.setAttribute("activityVO", activityVO);
//				req.setAttribute("act_no",act_no);
//				System.out.println("act_no: " + act_no);
//				req.setAttribute("acReportVO", acReportVO);    
				String url = "/front-end/activity/activity_more.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/**************************************************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		 if ("insert_reply".equals(action)) { // 來自actCommentReply.jsp
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				
//				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String aco_no = req.getParameter("aco_no").trim();//hidden
					String shop_no = req.getParameter("shop_no").trim();//hidden
					String acr_cnt = req.getParameter("acr_cnt").trim();
					String act_no = req.getParameter("act_no").trim();//hidden
					System.out.println("目前活動留言編號: "+aco_no + "  會員編號: " + shop_no + " 回覆內容: "+ acr_cnt);
					Act_com_replyVO act_com_replyVO = new Act_com_replyVO();
					act_com_replyVO.setAco_no(aco_no);
					act_com_replyVO.setShop_no(shop_no);
					act_com_replyVO.setAcr_cnt(acr_cnt);
					System.out.println("包裝完資料囉");
					/***************************2.開始新增資料***************************************/
					Act_com_replyService actComReplySvc = new Act_com_replyService();
					act_com_replyVO = actComReplySvc.insert(aco_no, shop_no, acr_cnt);
					System.out.println("新增完資料囉");
					ActivityService activitySvc = new ActivityService();
					ActivityVO activityVO = activitySvc.findByActNo(act_no);
					req.setAttribute("activityVO", activityVO);
//					req.setAttribute("act_com_reportVO", act_com_replyVO);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/// 新增成功後轉交<tag 01>
					
					res.sendRedirect(req.getContextPath()+"/front-end/activity/activity.do?action=getOneAct_For_Display&act_no="+act_no);				
					
					/***************************其他可能的錯誤處理**********************************///<tag 02>
//				} catch (Exception e) {
//					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_more.jsp");
//					System.out.println("由<tag 02> Act_repServlet轉交activity_more.jsp"); 
//					failureView.forward(req, res);
//				}
			}
		
	}
}
