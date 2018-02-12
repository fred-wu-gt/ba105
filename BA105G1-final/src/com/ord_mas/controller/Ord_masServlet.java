package com.ord_mas.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commodity.model.CommodityVO;
import com.ord_det.model.Ord_detVO;
import com.ord_mas.model.Ord_masService;
import com.ord_mas.model.Ord_masVO;

/**
 * Servlet implementation class Ord_masServlet
 */
@WebServlet("/Ord_masServlet")
public class Ord_masServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public Ord_masServlet() {
		super();

	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		String action = req.getParameter("action");
		  if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			  System.out.println("==================TEST1=================");
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String ord_no = req.getParameter("ord_no");
					String mem_no = req.getParameter("mem_no");
					String shop_no = req.getParameter("shop_no");
					Integer ord_total = Integer.valueOf(req.getParameter("ord_total"));
					String ord_sta = req.getParameter("ord_sta");
					String ord_rec	= req.getParameter("ord_rec");
					if(ord_rec == null|| ord_rec.trim().length()==0 ){
							errorMsgs.add("收件人請勿空白");
					}
						
					String ord_adr = req.getParameter("ord_adr");
					if (ord_adr != null) {
						if (ord_adr.trim().length() == 0) {
							errorMsgs.add("收件地址請勿空白");
						}
					}

					String ord_tel = req.getParameter("ord_tel");
					if (ord_tel == null || ord_tel.trim().length() == 0) {
						errorMsgs.add("聯絡號碼請勿空白");
					} else if (!ord_tel.trim().matches("09\\d{8}")) {
						errorMsgs.add("手機: 只能是09開頭的數字組合, 總長度為10");
					}
				System.out.println("==================TEST2=================");
					
					
					Ord_masVO ord_masVO = new Ord_masVO();
					 ord_masVO.setOrd_no(ord_no);
					 ord_masVO.setMem_no(mem_no);
					 ord_masVO.setShop_no(shop_no);
					 ord_masVO.setOrd_sta(ord_sta);
					 ord_masVO.setOrd_total(ord_total);
					 ord_masVO.setOrd_rec(ord_rec);
					 ord_masVO.setOrd_adr(ord_adr);
					 ord_masVO.setOrd_tel(ord_tel);
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("ord_masVO", ord_masVO);// 將含有錯誤格式的comVo物件存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord_mas/addOrd_Mas.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					Ord_masService ord_masSvc = new Ord_masService();
					ord_masVO = ord_masSvc.addOrd_mas(mem_no, shop_no, ord_sta, 
							ord_total, ord_rec, ord_adr, ord_tel);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					req.setAttribute("listOneOrd_Mas", ord_masVO);
					String url = "/front-end/ord_mas/listOneOrd_Mas.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ord_mas/addOrd_Mas.jsp");
					failureView.forward(req, res);
				}
			}
			
		  if ("listOrd_det_ByOrd_no".equals(action) ) {

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);				
				try {
					/*************************** 1.接收請求參數 ****************************************/
					String ord_no = req.getParameter("ord_no");
					System.out.println("ord_no =" + ord_no);

					/*************************** 2.開始查詢資料 ****************************************/
					Ord_masService ord_masSvc = new Ord_masService();
					Set<Ord_detVO> set = ord_masSvc.getOrd_detByOrd_no(ord_no);

					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("listOrd_det_ByOrd_no", set);    // 資料庫取出的list物件,存入request

					String url = null;
					if ("listOrd_det_ByOrd_no".equals(action))
						url = "/front-end/ord_mas/listOrd_det_ByOrd_no.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
					else if ("listEmps_ByDeptno_B".equals(action))
						url = "/dept/listAllDept.jsp";              // 成功轉交 dept/listAllDept.jsp

					RequestDispatcher successView = req.getRequestDispatcher(url);
					System.out.println("要轉到的url = "+url);
					successView.forward(req, res);
					/*************************** 其他可能的錯誤處理 ***********************************/
				} catch (Exception e) {
					throw new ServletException(e);
				}
			}
		  
		  
		  
		  
		  
		  
		  
		if ("getOne_For_Update".equals(action)) { // 來自listAllOrd_mas.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String sendURL= req.getParameter("sendURL");
			System.out.println("getOne_For_Update 的  sendURL =" +sendURL);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String ord_no = req.getParameter("ord_no");
				/*************************** 2.開始查詢資料 ****************************************/
				Ord_masService Ord_masSvc = new Ord_masService();
				Ord_masVO ord_masvo = Ord_masSvc.getOneOrd_mas(ord_no);
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				String urlA = "/front-end/ord_mas/update_ord_mas_input.jsp";
				String urlB = "/front-end/ord_mas/update_ord_mas_ByShop.jsp";
				req.setAttribute("ord_masvo", ord_masvo);
				if(sendURL.equals("/front-end/ord_mas/listAllByMemNo.jsp")){
					RequestDispatcher successView = req.getRequestDispatcher(urlA);
					successView.forward(req, res);
				}
				
				if(sendURL.equals("/front-end/ord_mas/listAllByShopNO.jsp")){
					RequestDispatcher successView = req.getRequestDispatcher(urlB);
					successView.forward(req, res);
				}
				
				
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord_mas/listAllOrd_Mas.jsp");
				failureView.forward(req, res);
			}

		}

		if ("getOne_For_Display".equals(action)) { //從select_page來
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 */
			try {
				String ord_no = req.getParameter("ord_no");
//				System.out.println("getOne_For_Display的ord_no ="+ord_no);
				if (ord_no == null || ord_no.trim().length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord_mas/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*
				 * 2.開始查詢資料
				 */
				Ord_masService ord_masSvc = new Ord_masService();
				Ord_masVO ord_masVO = ord_masSvc.getOneOrd_mas(ord_no);
				if(ord_masVO == null){
					errorMsgs.add("查無訂單資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ord_mas/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*
				 * 3.查詢完成,準備轉交(Send the Success view)
				 */
				req.setAttribute("listOneOrd_Mas", ord_masVO);
//				System.out.println("ord_masVO的"+ ord_masVO);
				String url = "listOneOrd_Mas.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
	

			} catch (Exception e) {
				errorMsgs.add("無法取得資料 :" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord_mas/listAllOrd_Mas.jsp");
				failureView.forward(req, res);				
			}
		}
		
		


		if("updateRec".equals(action)){		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String ord_no = req.getParameter("ord_no");
			String mem_no = req.getParameter("mem_no");
			String shop_no = req.getParameter("shop_no");
			Timestamp ord_time =Timestamp.valueOf(req.getParameter("ord_time")) ;
			Integer ord_total = Integer.valueOf(req.getParameter("ord_total"));
			String ord_sta = req.getParameter("ord_sta");
			String ord_can_rea = req.getParameter("ord_can_rea");
			String ord_rec	= req.getParameter("ord_rec");
			if(ord_rec != null){
				if(ord_rec.trim().length() == 0){
					errorMsgs.add("收件人請勿空白");
				}
			}
			String ord_adr = req.getParameter("ord_adr");
			if (ord_adr != null) {
				if (ord_adr.trim().length() == 0) {
					errorMsgs.add("收件地址請勿空白");
				}
			}

			String ord_tel = req.getParameter("ord_tel");
			if (ord_tel == null || ord_tel.trim().length() == 0) {
				errorMsgs.add("聯絡號碼請勿空白");
			} else if (!ord_tel.trim().matches("09\\d{8}")) {
				errorMsgs.add("手機: 只能是09開頭的數字組合, 總長度為10");
			}

			Ord_masService ord_masSvc = new Ord_masService();
			Ord_masVO ord_masVO = ord_masSvc.getOneOrd_mas(ord_no);
			if(ord_rec==null){
				ord_rec = ord_masVO.getOrd_rec();
			}
			if(ord_adr == null){
				ord_adr	= ord_masVO.getOrd_adr();
			}
			if(ord_tel == null){
				ord_tel = ord_masVO.getOrd_tel();
			}
			

			 ord_masVO= new Ord_masVO();
			 ord_masVO.setOrd_no(ord_no);
			 ord_masVO.setMem_no(mem_no);
			 ord_masVO.setShop_no(shop_no);
			 ord_masVO.setOrd_time(ord_time);
			 ord_masVO.setOrd_sta(ord_sta);
			 ord_masVO.setOrd_total(ord_total);
			 ord_masVO.setOrd_rec(ord_rec);
			 ord_masVO.setOrd_adr(ord_adr);
			 ord_masVO.setOrd_tel(ord_tel);
			 ord_masVO.setOrd_can_rea(ord_can_rea);
			 
			 if (!errorMsgs.isEmpty()) {
					req.setAttribute("ord_masvo", ord_masVO);// 將含有錯誤格式的comVo物件存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord_mas/update_ord_mas_input.jsp");
					failureView.forward(req, res);
					return;
				}
			 
			 /*************************** 2.開始修改資料 *****************************************/
			 ord_masSvc = new Ord_masService();
			 ord_masVO =ord_masSvc.changeOrdMasRec(ord_rec, ord_adr, ord_tel, ord_no);
			 ord_masVO= new Ord_masVO();
			 ord_masVO.setOrd_no(ord_no);
			 ord_masVO.setMem_no(mem_no);
			 ord_masVO.setShop_no(shop_no);
			 ord_masVO.setOrd_time(ord_time);
			 ord_masVO.setOrd_sta(ord_sta);
			 ord_masVO.setOrd_total(ord_total);
			 ord_masVO.setOrd_rec(ord_rec);
			 ord_masVO.setOrd_adr(ord_adr);
			 ord_masVO.setOrd_tel(ord_tel);
			 ord_masVO.setOrd_can_rea(ord_can_rea);
			 /***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 ****************************/
			 req.setAttribute("listOneOrd_Mas", ord_masVO); // 資料庫update成功後，將正確的comVO物件，存入req
				String url = "/front-end/ord_mas/listOneOrd_Mas.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
	
		}
		
		
		if("updateStat".equals(action)){		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String ord_no = req.getParameter("ord_no");
			String mem_no = req.getParameter("mem_no");
			String shop_no = req.getParameter("shop_no");
			Timestamp ord_time =Timestamp.valueOf(req.getParameter("ord_time")) ;
			Integer ord_total = Integer.valueOf(req.getParameter("ord_total"));			
			String ord_rec = req.getParameter("ord_rec");
			String ord_adr = req.getParameter("ord_adr");
			String ord_tel = req.getParameter("ord_tel");
			String ord_can_rea = req.getParameter("ord_can_rea");
			
			
			String ord_sta = req.getParameter("ord_sta");
			System.out.println("ord_sta = "+ ord_sta);
			if(ord_sta != null){
				if(ord_sta.trim().length() == 0){
					errorMsgs.add("請填寫訂單狀態");
				}
			}
			
			Ord_masService ord_masSvc = new Ord_masService();
			Ord_masVO ord_masVO = ord_masSvc.getOneOrd_mas(ord_no);
//			if(ord_sta == null){
//				ord_sta = ord_masVO.getOrd_sta();
//			}
			

//			 ord_masVO= new Ord_masVO();
//			 ord_masVO.setOrd_no(ord_no);
//			 ord_masVO.setMem_no(mem_no);
//			 ord_masVO.setShop_no(shop_no);
//			 ord_masVO.setOrd_time(ord_time);
//			 ord_masVO.setOrd_sta(ord_sta);
//			 ord_masVO.setOrd_total(ord_total);
//			 ord_masVO.setOrd_rec(ord_rec);
//			 ord_masVO.setOrd_adr(ord_adr);
//			 ord_masVO.setOrd_tel(ord_tel);
//			 ord_masVO.setOrd_can_rea(ord_can_rea);
			 
			 if (!errorMsgs.isEmpty()) {
					req.setAttribute("ord_masvo", ord_masVO);// 將含有錯誤格式的comVo物件存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord_mas/update_ord_mas_input.jsp");
					failureView.forward(req, res);
					return;
				}
			 
			 /*************************** 2.開始修改資料 *****************************************/
			 ord_masSvc = new Ord_masService();
			 ord_masVO =ord_masSvc.changOrdSta(ord_sta, ord_no);
			 ord_masVO= new Ord_masVO();
			 ord_masVO.setOrd_no(ord_no);
			 ord_masVO.setMem_no(mem_no);
			 ord_masVO.setShop_no(shop_no);
			 ord_masVO.setOrd_time(ord_time);
			 ord_masVO.setOrd_sta(ord_sta);
			 ord_masVO.setOrd_total(ord_total);
			 ord_masVO.setOrd_rec(ord_rec);
			 ord_masVO.setOrd_adr(ord_adr);
			 ord_masVO.setOrd_tel(ord_tel);
			 ord_masVO.setOrd_can_rea(ord_can_rea);
			 /***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 ****************************/
			 req.setAttribute("listOneOrd_Mas", ord_masVO); // 資料庫update成功後，將正確的comVO物件，存入req
				String url = "/front-end/ord_mas/listOneOrd_Mas.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
	
		}
		
		
		
		
		
		
		
		
		  
				if ("getList_For_Display".equals(action)) { // 來自select_page.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					
					
					List<Ord_masVO> listOrd_mas = new LinkedList<Ord_masVO>();
					
					try {
						/*************************** 1.接收請求參數 ****************************************/
						String mem_no = req.getParameter("mem_no");
						System.out.println("mem_no =" + mem_no);
						/*************************** 2.開始查詢資料 ****************************************/
						Ord_masService Ord_masSvc = new Ord_masService();
						 listOrd_mas = Ord_masSvc.getListByMemNO(mem_no);
						/***************************
						 * 3.查詢完成,準備轉交(Send the Success view)
						 ************/
						req.setAttribute("listOrd_mas", listOrd_mas);
						System.out.println("listOrd_mas :"+listOrd_mas);
						String url = "/front-end/ord_mas/listAllByMemNo.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						System.out.println("==========TEST1========");
						successView.forward(req, res);
						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						e.printStackTrace();
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord_mas/select_page.jsp");
						failureView.forward(req, res);
					}

				}

	}

}
