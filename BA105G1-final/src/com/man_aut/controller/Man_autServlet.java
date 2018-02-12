package com.man_aut.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.man_fun.model.*;
import com.man_aut.model.*;
import com.manager.model.*;

public class Man_autServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		    // 來自select_page.jsp的請求                                  // 來自/back-end/man_aut/listAllMan_aut.jsp的請求
		if ("listManagers_ByMf_no_A".equals(action) || "listManagers_ByMf_no_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String mf_no =req.getParameter("mf_no");
				
				/*************************** 2.開始查詢資料 ****************************************/
				Man_funVO man_funVO = new Man_funVO();
				Man_funService man_funSvc = new Man_funService();
				man_funVO.setMf_name(man_funSvc.getOneMan_fun(mf_no).getMf_name());
				man_funVO.setMf_no(mf_no);
				Man_autService man_autSvc = new Man_autService();
				List<Man_autVO> man_autVOList = man_autSvc.getMan_autListByMf_no(mf_no);//得到可使用此功能的權限明細
				ManagerService managerSvc = new ManagerService();
				List<ManagerVO> managerVOList =new ArrayList<>();
				for(Man_autVO man_autVO:man_autVOList){
					managerVOList.add(managerSvc.getOneManager(man_autVO.getMan_no()));
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("managerVOList", managerVOList);    
				req.setAttribute("man_funVO", man_funVO);
				String url = null;

				if ("listManagers_ByMf_no_A".equals(action)){    //從select_page選指定功能時
					url = "/back-end/man_aut/listManagers_ByMf_no.jsp";
				}
				else if ("listManagers_ByMf_no_B".equals(action)){
					url = "/back-end/man_aut/listAllMan_aut.jsp"; //從select_page選List all Man_auts.時
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {

				throw new ServletException(e);
			}
		}
		
		
		if ("delete_Man_fun".equals(action)) { // 來自/back-end/man_aut/listAllMan_aut.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mf_no =req.getParameter("mf_no");
				
				/***************************2.開始刪除資料***************************************/
				Man_autService man_autSvc = new Man_autService();
				List<Man_autVO> man_autVOList = man_autSvc.getMan_autListByMf_no(mf_no);//得到可使用此功能的權限明細
				for(Man_autVO man_autVO:man_autVOList){
					man_autSvc.deleteMan_aut(mf_no, man_autVO.getMan_no());
				}
				Man_funService man_funSvc = new Man_funService();
				man_funSvc.deleteMan_fun(mf_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/man_aut/listAllMan_aut.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到/back-end/man_aut/listAllMan_aut.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/man_aut/listAllMan_aut.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("deleteAut".equals(action)) { // 來自/back-end/man_aut/listAllMan_aut.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL=null;
			try {
				/***************************1.接收請求參數***************************************/
				String mf_no =req.getParameter("mf_no");
				String man_no =req.getParameter("man_no");
				requestURL =req.getParameter("requestURL");
				
				/***************************2.開始刪除資料***************************************/
				Man_autService man_autSvc = new Man_autService();
				man_autSvc.deleteMan_aut(mf_no, man_no);
				
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				Man_funVO man_funVO = new Man_funVO();
				Man_funService man_funSvc = new Man_funService();
				man_funVO.setMf_name(man_funSvc.getOneMan_fun(mf_no).getMf_name());
				man_funVO.setMf_no(mf_no);
				man_autSvc = new Man_autService();
				List<Man_autVO> man_autVOList = man_autSvc.getMan_autListByMf_no(mf_no);//得到可使用此功能的權限明細
				ManagerService managerSvc = new ManagerService();
				List<ManagerVO> managerVOList =new ArrayList<>();
				for(Man_autVO man_autVO:man_autVOList){
					managerVOList.add(managerSvc.getOneManager(man_autVO.getMan_no()));
				}
				req.setAttribute("managerVOList", managerVOList);    
				req.setAttribute("man_funVO", man_funVO);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);// 刪除成功後, 成功轉交 回到/back-end/man_aut/listManagers_ByMf_no.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}


	}
}

