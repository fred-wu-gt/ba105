package com.act_join_det.controller;

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

import com.act_join_det.model.*;
import com.act_like.model.Act_likeService;
import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;

public class Act_join_detServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");// 先
		String action = req.getParameter("action");// 後
		HttpSession session = req.getSession();

		if ("activity_more_join".equals(action)) { // 來自activity_more.jsp的我要報名

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************ 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String act_no = req.getParameter("act_no").trim();	// hidden
				String mem_no = req.getParameter("mem_no").trim();	// hidden
				System.out.println("目前活動編號==" + act_no + "  會員編號==" + mem_no);
				
				Act_join_detVO act_join_detVO = new Act_join_detVO();
				Act_join_detService ajSvc = new Act_join_detService();
				act_join_detVO = ajSvc.findByPrimaryKey(act_no, mem_no);
				if(act_join_detVO == null){
					System.out.println("會員編號:" + mem_no + " 沒有報名過 活動編號: " + act_no );
					act_join_detVO = ajSvc.insert(act_no, mem_no);// 須避免INSERT重複的複合PK,否則例外
					System.out.println("新增完資料囉");
				}else if(act_join_detVO.getAj_status().equals("取消報名")) {
					ajSvc.updateAjStatus("未報到", act_no, mem_no);
					System.out.println("活動報名狀態由 : " + act_join_detVO.getAj_status() + " 改成 : 未報到");
				}
						
				/*************************** 2.開始新增資料 ***************************************/

				//req.setAttribute("act_join_detVO", act_join_detVO);//1230拿掉的
				//forward之前 先清空session的joinActList 讓activity_join.jsp能當場重新new一個裝ActivityVO的list
				if (session.getAttribute("joinActList") != null) {
					session.removeAttribute("joinActList");
				}
				System.out.println("after removeAttribute(joinActList)");
		ActivityService activitySvc = new ActivityService();
		ActivityVO activityVO = activitySvc.findByActNo(act_no);
		req.setAttribute("activityVO", activityVO);
				/**************************** 3.新增完成,準備轉交(Send the Success view) ***********///<tag01>
				String url = "/front-end/activity/activity_more.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交<tag01>
																				
				System.out.println("由<tag 01> Act_join_detServlet轉交activity_more.jsp");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/// <tag02>
																							 
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_more.jsp");
				System.out.println("由<tag 02> Act_join_detServlet轉交activity_more.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getAllJoin_For_Display".equals(action)) { //來自activity_shop_home.jsp的查詢報名名單請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String act_no = req.getParameter("act_no");
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/activity_shop_home.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************2.開始查詢資料*****************************************/
				Act_join_detService ajSvc = new Act_join_detService();
				List<Act_join_detVO> list = ajSvc.findByActNo(act_no);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/activity/activity_shop_home.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/front-end/activity/listAllJoin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllJoin.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		
		
//		if ("update_status_ByActivity_more".equals(action)) { // 來自activity_more.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			Act_join_detVO act_join_detVO = new Act_join_detVO();
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String act_no = req.getParameter("act_no").trim();//hidden欄位來的,不用錯誤處理
//				System.out.println(act_no);
//				String mem_no = req.getParameter("mem_no").trim();//hidden欄位來的,不用錯誤處理
//				System.out.println(mem_no);
//				String aj_status = req.getParameter("aj_status").trim();//hidden欄位來的,不用錯誤處理
//				System.out.println(aj_status);
//				act_join_detVO.setAct_no(act_no);
//				act_join_detVO.setMem_no(mem_no);
//				act_join_detVO.setAj_status(aj_status);
//				System.out.println("act_join_detVO包裝好囉");
//				
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("act_join_detVO", act_join_detVO); // 含有輸入格式錯誤的activityVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/activity/activity_more.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				Act_join_detService ajSvc = new Act_join_detService();
//				ajSvc.updateAjStatus(aj_status, act_no, mem_no);
//				System.out.println("aj_status已修改成 : " + aj_status);
//			ActivityService activitySvc = new ActivityService();
//			ActivityVO activityVO = activitySvc.findByActNo(act_no);
//			req.setAttribute("activityVO", activityVO);
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("act_join_detVO", act_join_detVO); // 資料庫update成功後,正確的的activityVO物件,存入req
//				String url = "/front-end/activity/activity_more.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/activity/activity_more.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if ("delete_ByActivity_more".equals(action)) { // 來自activity_more.jsp的取消報名
			
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
				
				Act_join_detService act_join_detSvc = new Act_join_detService();
				act_join_detSvc.delete(act_no, mem_no);
				System.out.println("刪除完資料囉");
				//forward之前 先清空session的joinActList 讓activity_join.jsp能當場重新new一個裝ActivityVO的list
				//forward之前 先清空session的likeList 讓activity_like.jsp能當場重新new一個裝ActivityVO的list
				if (session.getAttribute("joinActList") != null) {
					session.removeAttribute("joinActList");
				}
				System.out.println("after removeAttribute(joinActList)");
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.findByActNo(act_no);
				req.setAttribute("activityVO", activityVO);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/// 新增成功後轉交<tag 01>
				String url = "/front-end/activity/activity_join.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交<tag01>
																				
				System.out.println("由<tag 01> Act_join_detServlet轉交activity_join.jsp");
				successView.forward(req, res);			
				
				/***************************其他可能的錯誤處理**********************************///<tag 02>
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/activity/activity_more.jsp");
				System.out.println("由<tag 02> Act_join_detServlet轉交activity_more.jsp"); 
				failureView.forward(req, res);
			}
		}
	}
}
