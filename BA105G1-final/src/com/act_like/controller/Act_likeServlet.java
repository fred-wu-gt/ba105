package com.act_like.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.act_like.model.Act_likeService;
import com.act_like.model.Act_likeVO;
import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

public class Act_likeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//先
		String action = req.getParameter("action");//後
		HttpSession session = req.getSession();
		
		 if ("insert_ByActivity_more".equals(action)) { // 來自activity_more.jsp的活動很讚
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String act_no = req.getParameter("act_no").trim();//hidden
					String mem_no = req.getParameter("mem_no").trim();//hidden
					System.out.println("目前活動編號=="+act_no + "  會員編號==" + mem_no);
					Act_likeVO act_likeVO =  new Act_likeVO();
					act_likeVO.setAct_no(act_no);
					act_likeVO.setMem_no(mem_no);
					System.out.println("包裝完資料囉");
					/***************************2.開始新增資料***************************************/
					Act_likeService ajSvc = new Act_likeService();
					act_likeVO = ajSvc.insert(act_no, mem_no);//須避免INSERT重複的複合PK,否則例外
					System.out.println("新增完資料囉");
					ActivityService activitySvc = new ActivityService();
					ActivityVO activityVO = activitySvc.findByActNo(act_no);
					req.setAttribute("activityVO", activityVO); 
					//req.setAttribute("act_likeVO", act_likeVO);// 不用setAttribute 在被forward頁面會重新findByMemNo(mem_no)撈出這會員所有按讚活動
					//forward之前 先清空session的likeList  讓activity_like.jsp能當場重新new一個裝ActivityVO的list
					if (session.getAttribute("likeList") != null) {
						session.removeAttribute("likeList");
					}
					System.out.println("after removeAttribute(likeList)");				
					/***************************3.新增完成,準備轉交(Send the Success view)***********/// 新增成功後轉交<tag 01>
					String url = "/front-end/activity/activity_more.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					System.out.println("由<tag 01> Act_likeServlet轉交activity_more.jsp");
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************///<tag 02>
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_more.jsp");
					System.out.println("由<tag 02> Act_likeServlet轉交activity_more.jsp"); 
					failureView.forward(req, res);
				}
			}
		 
		 if ("delete_ByActivity_more".equals(action)) { // 來自activity_more.jsp的取消案讚
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String act_no = req.getParameter("act_no").trim();//hidden
					String mem_no = req.getParameter("mem_no").trim();//hidden
					System.out.println("目前活動編號=="+act_no + "  會員編號==" + mem_no);
					
					/***************************2.開始新增資料***************************************/
					Act_likeService ajSvc = new Act_likeService();
					ajSvc.delete(act_no, mem_no);
					System.out.println("刪除完資料囉");
					ActivityService activitySvc = new ActivityService();
					ActivityVO activityVO = activitySvc.findByActNo(act_no);
					req.setAttribute("activityVO", activityVO); 
					//req.setAttribute("act_likeVO", act_likeVO);
					//forward之前 先清空session的likeList 讓activity_like.jsp能當場重新new一個裝ActivityVO的list
					if (session.getAttribute("likeList") != null) {
						session.removeAttribute("likeList");
					}
					System.out.println("after removeAttribute(likeList)");				
					/***************************3.新增完成,準備轉交(Send the Success view)***********/// 新增成功後轉交<tag 01>
					String url = "/front-end/activity/activity_more.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					System.out.println("由<tag 01> Act_likeServlet轉交activity_more.jsp");
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************///<tag 02>
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_more.jsp");
					System.out.println("由<tag 02> Act_likeServlet轉交activity_more.jsp"); 
					failureView.forward(req, res);
				}
			}
	}
}
