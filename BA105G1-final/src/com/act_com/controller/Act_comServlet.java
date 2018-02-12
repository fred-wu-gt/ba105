package com.act_com.controller;
import com.act_com.model.Act_comService;
import com.act_com.model.Act_comVO;
import com.act_com_reply.model.Act_com_replyService;
import com.act_com_reply.model.Act_com_replyVO;
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


public class Act_comServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		 if ("insert_comment".equals(action)) { // 來自activity_more.jsp的新增留言
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
//				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String act_no = req.getParameter("act_no").trim();//hidden
					String mem_no = req.getParameter("mem_no").trim();//hidden
					String requestURL = req.getParameter("requestURL").trim();
					String aco_cnt = req.getParameter("aco_cnt").trim();
					System.out.println("目前活動編號: "+act_no + "  會員編號: " + mem_no + " 留言內容: "+ aco_cnt);
					Act_comVO act_comVO = new Act_comVO();
					act_comVO.setAct_no(act_no);
					act_comVO.setMem_no(mem_no);
					act_comVO.setAco_cnt(aco_cnt);
					System.out.println("包裝完資料囉");
					/***************************2.開始新增資料***************************************/
					Act_comService actComSvc = new Act_comService();
					act_comVO = actComSvc.insert(act_no, mem_no, aco_cnt);
					System.out.println("新增完資料囉");
					ActivityService activitySvc = new ActivityService();
					ActivityVO activityVO = activitySvc.findByActNo(act_no);
					req.setAttribute("activityVO", activityVO);
//					req.setAttribute("act_comVO", act_comVO);
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
