package com.mail.controller;
import com.act_com_report.model.*;
import com.mail.model.MailService;
import com.mail.model.MailVO;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MailServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		if ("getOneMail_For_display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.****************************************/
				String mail_no = req.getParameter("mail_no");
				String mem_no = req.getParameter("mem_no");

				/*************************** 2.****************************************/
				MailService mailSvc = new MailService();
				MailVO mailVO = mailSvc.findByMailNo(mail_no, mem_no);

				/*************************** 3.(Send the Success view) ************/
				req.setAttribute("mailVO",mailVO);
				String url = "/front-end/mail/mailMore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/**************************************************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("insert_mail".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String aco_no = req.getParameter("aco_no").trim();//hidden
				String mem_no = req.getParameter("mem_no").trim();//hidden
				String acr_rsn = req.getParameter("acr_rsn").trim();
				String acr_cnt = req.getParameter("acr_cnt").trim();
				System.out.println("目前活動留言編號: "+aco_no + "  會員編號: " + mem_no + " 檢舉原因: " + acr_rsn + " 檢舉內容: "+ acr_cnt);
				Act_com_reportVO act_com_reportVO = new Act_com_reportVO();
				act_com_reportVO.setAco_no(aco_no);
				act_com_reportVO.setMem_no(mem_no);
				act_com_reportVO.setAcr_rsn(acr_rsn);
				act_com_reportVO.setAcr_cnt(acr_cnt);
				System.out.println("包裝完資料囉");
				/***************************2.開始新增資料***************************************/
				Act_com_reportService actComReportSvc = new Act_com_reportService();
				act_com_reportVO = actComReportSvc.insert(aco_no, mem_no, acr_rsn, acr_cnt);
				System.out.println("新增完資料囉");
				req.setAttribute("act_com_reportVO", act_com_reportVO);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/// 新增成功後轉交<tag 01>
				String url = "/front-end/activity/activity_more.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				System.out.println("由<tag 01> Act_repServlet轉交activity_more.jsp");
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************///<tag 02>
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_more.jsp");
				System.out.println("由<tag 02> Act_repServlet轉交activity_more.jsp"); 
				failureView.forward(req, res);
			}
		 }
		
	}
}
