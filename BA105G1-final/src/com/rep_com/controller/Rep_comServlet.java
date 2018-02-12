package com.rep_com.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ord_mas.model.Ord_masService;
import com.ord_mas.model.Ord_masVO;
import com.rep_com.model.Rep_comService;
import com.rep_com.model.Rep_comVO;

/**
 * Servlet implementation class Rep_comServlet
 */
@WebServlet("/Rep_comServlet")
public class Rep_comServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Rep_comServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
    	req.setCharacterEncoding("UTF-8");
    	res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllOrd_mas.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rc_no = req.getParameter("rc_no");
//				System.out.println("getOne_For_Update的rc_no ="+rc_no);
				/*************************** 2.開始查詢資料 ****************************************/
				Rep_comService rep_comSVC = new Rep_comService();
				Rep_comVO rep_comVO = rep_comSVC.getOneRep_com(rc_no);
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("rep_comVO", rep_comVO);
//				System.out.println("update_rep_com_stat ="+rep_comVO);
				String url = "/front-end/rep_com/update_rep_com_stat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//				System.out.println("==============TEST2==============");
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rep_com/listAllRep_com.jsp");
				failureView.forward(req, res);
			}

		}
		/*
		 * ==================================================================
		 */

		if("updateRcStat".equals(action)){		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String rc_no = req.getParameter("rc_no");
			String com_no = req.getParameter("com_no");
			String mem_no = req.getParameter("mem_no");
			String rc_cuz = req.getParameter("rc_cuz");
			String rc_stat = req.getParameter("rc_stat");
			String rc_txt	= req.getParameter("rc_txt");
			if(rc_cuz != null){
				if(rc_cuz.trim().length() == 0){
					errorMsgs.add("檢舉原因請勿空白");
				}
			}
			
			Rep_comService rep_comSvc = new Rep_comService();
			Rep_comVO rep_comVO = rep_comSvc.getOneRep_com(rc_no);
		
			rep_comVO.setRc_no(rc_no);
			rep_comVO.setCom_no(com_no);
			rep_comVO.setMem_no(mem_no);
			rep_comVO.setRc_cuz(rc_cuz);
			rep_comVO.setRc_stat(rc_stat);
			rep_comVO.setRc_txt(rc_txt);

			 if (!errorMsgs.isEmpty()) {
					req.setAttribute("update_rep_com_stat", rep_comVO);// 將含有錯誤格式的comVo物件存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rep_com/update_rep_com_stat.jsp");
					failureView.forward(req, res);
					return;
				}
			 
			 /*************************** 2.開始修改資料 *****************************************/
			 rep_comSvc = new Rep_comService();
			 rep_comVO =rep_comSvc.updateRep_comStat(rc_stat, rc_no);
			rep_comVO.setRc_no(rc_no);
			rep_comVO.setCom_no(com_no);
			rep_comVO.setMem_no(mem_no);
			rep_comVO.setRc_cuz(rc_cuz);
			rep_comVO.setRc_stat(rc_stat);
			rep_comVO.setRc_txt(rc_txt);

			 /***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 ****************************/
			 req.setAttribute("rep_comVO", rep_comVO); // 資料庫update成功後，將正確的comVO物件，存入req
				String url = "/front-end/rep_com/listOneRep_com.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
/*
 * ====================================insert開始=====================================		
 */
		if("insert".equals(action)){		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String com_no = req.getParameter("com_no");
			String mem_no = req.getParameter("mem_no");
			String rc_cuz = req.getParameter("rc_cuz");
			if(rc_cuz != null){
				if(rc_cuz.equals("請選擇")){
					errorMsgs.add("請選擇檢舉原因");
				}
			}
			String rc_stat = req.getParameter("rc_stat");
			String rc_txt	= req.getParameter("rc_txt");
			
			Rep_comVO rep_comVO = new Rep_comVO();
			rep_comVO.setCom_no(com_no);
			rep_comVO.setMem_no(mem_no);
			rep_comVO.setRc_cuz(rc_cuz);
			rep_comVO.setRc_stat(rc_stat);
			rep_comVO.setRc_txt(rc_txt);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("rep_comVO", rep_comVO);// 將含有錯誤格式的comVo物件存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rep_com/addRep_com.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/*************************** 2.開始修改資料 *****************************************/
			Rep_comService rep_comSvc = new Rep_comService();
			rep_comVO = rep_comSvc.addRep_com(com_no, mem_no, rc_cuz, rc_stat, rc_txt);
			/***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 ****************************/
			req.setAttribute("rep_comVO", rep_comVO); // 資料庫update成功後，將正確的comVO物件，存入req
			String url = "/front-end/rep_com/listOneRep_com.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
		}
	}
    
    

}
		
    	



