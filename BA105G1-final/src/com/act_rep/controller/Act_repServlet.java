package com.act_rep.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.json.JSONObject;

import com.act_like.model.Act_likeService;
import com.act_like.model.Act_likeVO;
import com.act_rep.model.Act_repService;
import com.act_rep.model.Act_repVO;
import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;

public class Act_repServlet extends HttpServlet {
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
		
		 if ("insert_report".equals(action)) { // 來自activity_more.jsp的活動檢舉
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				String requestURL = req.getParameter("requestURL");
				
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String act_no = req.getParameter("act_no").trim();//hidden
					String mem_no = req.getParameter("mem_no").trim();//hidden
					
					String ar_rsn = req.getParameter("ar_rsn").trim();
					
					String ar_cnt = req.getParameter("ar_cnt").trim();
					if (ar_cnt == null || ar_cnt.trim().length() == 0) {
						errorMsgs.add("檢舉活動原因: 請勿空白");
					}
					System.out.println("目前活動編號: "+act_no + "  會員編號: " + mem_no + " 檢舉原因: " + ar_rsn + " 檢舉內容: "+ar_cnt);
					Act_repVO act_repVO = new Act_repVO();
					act_repVO.setAct_no(act_no);
					act_repVO.setMem_no(mem_no);
					act_repVO.setAr_rsn(ar_rsn);
					act_repVO.setAr_cnt(ar_cnt);
					System.out.println("包裝完資料囉");
					/***************************2.開始新增資料***************************************/
					Act_repService actReportSvc = new Act_repService();
					act_repVO = actReportSvc.addAct_rep(act_no, mem_no, ar_rsn, ar_cnt);
					System.out.println("新增完資料囉");
					ActivityService activitySvc = new ActivityService();
					ActivityVO activityVO = activitySvc.findByActNo(act_no);
					req.setAttribute("activityVO", activityVO); 
					//req.setAttribute("act_repVO", act_repVO);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/// 新增成功後轉交<tag 01>
					String url = requestURL;
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
			//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			if ("getAct_repsByAct_status".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
		
				
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

					List<Act_repVO> act_repVOList=null;
					String chooseGroup = req.getParameter("chooseGroup");
					String act_status=null;
					if(chooseGroup==null){ //直接從首頁送來時
						act_status="待審核";
					}else{                 //從下拉式選項送來時 
						if(chooseGroup.equals("waiting"))
							act_status="待審核";
						else if(chooseGroup.equals("pass"))
							act_status="審核通過";
						else if(chooseGroup.equals("fail"))
							act_status="審核不通過";
						
					}
					
					

					
					/***************************2.開始查詢資料*****************************************/
					Act_repService actReportSvc = new Act_repService();
					if(act_status!=null){
						act_repVOList = actReportSvc.findByAct_status(act_status);
					}else{
						act_repVOList = actReportSvc.getAll();
					}
					
					if (act_repVOList == null) {
						errorMsgs.add("查無資料");
					}

					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/report/listAllActRep.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					session.setAttribute("act_repVOList", act_repVOList);
					String url = "/back-end/report/listAllActRep.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/listAllActRep.jsp");
					failureView.forward(req, res);
				}
			}
			
			if ("updateStatus".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();

		
				req.setAttribute("errorMsgs", errorMsgs);
			
				res.setContentType("text/plain"); 
		        req.setCharacterEncoding("utf-8");
		        res.setCharacterEncoding("utf-8");

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String ar_no = req.getParameter("ar_no");//隱藏欄位送出
					String originalAre_status = req.getParameter("originalAct_status");//原狀態
					String are_status = req.getParameter("act_status");//下拉式選單得到的審核送出狀態
					String shop_no = req.getParameter("shop_no");
					String act_no = req.getParameter("act_no");
					System.out.println("*活動編號"+ar_no+"所在清單群組為"+req.getParameter("chooseGroup"));
					System.out.println("   原狀態為"+originalAre_status+"，新送出的狀態為"+are_status);
					/***************************2.開始查詢資料*****************************************/
					Act_repService actReportSvc = new Act_repService();
					actReportSvc.updateAjStatus(are_status, ar_no);
					
					List<Act_repVO> act_repVOList=null;
					String chooseGroup = req.getParameter("chooseGroup");
					String groupAct_status=null;
					if(chooseGroup==null){ //直接從首頁送來時
						groupAct_status="待審核";
					}else{                 //從下拉式選項送來時 
						if(chooseGroup.equals("waiting"))
							groupAct_status="待審核";
						else if(chooseGroup.equals("pass"))
							groupAct_status="審核通過";
						else if(chooseGroup.equals("fail"))
							groupAct_status="審核不通過";
					}
					
					if(groupAct_status!=null){
						act_repVOList = actReportSvc.findByAct_status(groupAct_status);
					}else{
						act_repVOList = actReportSvc.getAll();
					}
					session.setAttribute("act_repVOList", act_repVOList);//為了換頁重取資料時能查到正確資料才存的
					
					ShopService ShopSvc = new ShopService();
					ShopVO shopVO=ShopSvc.findByPrimaryKey(shop_no);
					int originalShop_point=shopVO.getShop_point();//商家原本的違規點數
					int shop_point=0;//商家最後的違規點數
					
					ActivityService activitySvc = new ActivityService();
					ActivityVO activityVO=activitySvc.findByActNo(act_no);
					String originalActivity_status = activityVO.getAct_status2();//活動原狀態:正常或隱藏
					String activity_status ="";//活動最後狀態:正常或隱藏
					
					PrintWriter out = res.getWriter();
					if(originalAre_status.equals("審核通過")){//原狀態為審核通過，新送出的狀態為待審核或審核不通過，則違規記點-1，並將活動狀態2改為正常
						if(originalShop_point>0){
							shop_point=originalShop_point-1;
							ShopSvc.updateShop_point(shop_no, shop_point);
						}else{//originalShop_point==0
							shop_point=originalShop_point;
						}
						activity_status="正常";
						activitySvc.updateActStatus2(activity_status, act_no);
					}else{//原狀態為待審核或審核不通過
						if(are_status.equals("審核通過")){//原狀態為待審核或審核不通過，新送出的狀態為審核通過，則違規記點+1，並將活動狀態2改為隱藏
							shop_point=originalShop_point+1;
							ShopSvc.updateShop_point(shop_no, shop_point);
							activity_status="隱藏";
							activitySvc.updateActStatus2(activity_status, act_no);
						}else{//原狀態為待審核或審核不通過，新送出的狀態為審核不通過或待審核，則不修改違規記點，也不修改活動狀態2
							shop_point=originalShop_point;
							activity_status=originalActivity_status;
						}
						
					}

					JSONObject obj = new JSONObject();
					
					String shop_name = shopVO.getShop_name();
					String act_name=activityVO.getAct_name();
					obj.put("are_status", are_status);
					obj.put("ar_no", ar_no);
					obj.put("shop_name", shop_name);
					obj.put("originalShop_point", originalShop_point);
					obj.put("shop_point", shop_point);
					obj.put("act_name", act_name);
					obj.put("originalActivity_status", originalActivity_status);
					obj.put("activity_status", activity_status);
					System.out.println("送出成功 : "+are_status+"此篇活動檢舉 : "+are_status+"，商家名稱 : "+shop_name+"，原違規點數 : "+originalShop_point+"，目前違規點數 : "+shop_point+"，活動標題 : "+act_name+"，原狀態 : "+originalActivity_status+"，目前狀態 : "+activity_status);
					out.print(obj.toString());
					out.flush();
			        out.close();
					

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/listAllActRep.jsp");
					failureView.forward(req, res);
				}
			}
//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	        
    

	
		 
	}
}
