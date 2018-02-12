package com.ad.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.ad.model.AdService;
import com.ad.model.AdVO;
//TODO 2018/01/21整合須併入 承霖
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class AdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String shop_no = request.getParameter("shop_no");
				String ad_no = request.getParameter("ad_no");

				java.sql.Timestamp ad_idesta = null;
				ad_idesta = java.sql.Timestamp.valueOf(request.getParameter("ad_idesta").trim());
				if (ad_idesta == null) {
					errorMsgs.add("廣告開始時間: 請勿空白");
				}
				System.out.println("step1" + ad_idesta);

				java.sql.Timestamp ad_end = null;
				ad_end = java.sql.Timestamp.valueOf(request.getParameter("ad_end").trim());
				if (ad_end == null) {
					errorMsgs.add("廣告結束時間: 請勿空白");
				}
				System.out.println("step2" + ad_end);

				AdVO adVO = new AdVO();

				adVO.setAd_idesta(ad_idesta);
				adVO.setAd_end(ad_end);
				adVO.setAd_no(ad_no);
				Part part = null;
				InputStream in = null;
				byte[] ad_photo = null;
				part = request.getPart("ad_photo");
				in = part.getInputStream();
				ad_photo = new byte[in.available()];
				in.read(ad_photo);
				if (part == null || part.getSize() == 0) {
					InputStream is = getServletContext().getResourceAsStream("ADVERTISEMENT-AD_PHOTO/1.png");
					ad_photo = new byte[is.available()];
					is.read(ad_photo);
					is.close();
				}
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("adVO", adVO);// 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/ad/adadd.jsp");
					failureView.forward(request, response);
					return;
				}
				if (ad_end.after(ad_idesta)) {
					// SweetAlert start
					String saveSuccess = "修改完成";
					request.setAttribute("saveSuccess", saveSuccess);
					request.setAttribute("adVO", adVO);
					/*************************** 2.開始新增資料 ***************************************/
					AdService adSvc = new AdService();
					adVO = adSvc.addAd(ad_no, shop_no, ad_idesta, ad_end, ad_photo);
					/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
					String url = "/front-end/shop/shop_default.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(request, response);
					/*************************** 其他可能的錯誤處理 **********************************/
				} else {
					// SweetAlert start
					String notSaveSuccess = "新增不完成";
					request.setAttribute("notSaveSuccess", notSaveSuccess);
					request.setAttribute("adVO", adVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/ad/adadd.jsp");
					failureView.forward(request, response);
				}
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/ad/adadd.jsp");
				failureView.forward(request, response);
				System.out.println("excc");

			}

		}
		
		//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if ("getAdsByAd_stat".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
	
				List<AdVO> adVOList=null;
				String chooseGroup = request.getParameter("chooseGroup");
				String ad_stat=null;
				if(chooseGroup==null){ //直接從首頁送來時
					ad_stat="待審核";
				}else{                 //從下拉式選項送來時 
					if(chooseGroup.equals("waiting"))
						ad_stat="待審核";
					else if(chooseGroup.equals("pass"))
						ad_stat="審核通過";
					else if(chooseGroup.equals("fail"))
						ad_stat="審核不通過";
					
				}
				
				/***************************2.開始查詢資料*****************************************/
				AdService adSvc = new AdService();
				if(ad_stat!=null){
					adVOList = adSvc.findByAd_stat(ad_stat);
				}else{
					adVOList = adSvc.getAll();
				}
				
				if (adVOList == null) {
					errorMsgs.add("查無資料");
				}
	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/advertisement/adReview.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				session.setAttribute("adVOList", adVOList);
				String url = "/back-end/advertisement/adReview.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); 
				successView.forward(request, response);
	
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/advertisement/adReview.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("updateStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			PrintWriter out = response.getWriter();
			request.setAttribute("errorMsgs", errorMsgs);
			response.setContentType("text/plain"); 
	        request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String originalAd_stat = request.getParameter("originalAd_stat");//原狀態
				String ad_stat = request.getParameter("ad_stat");//下拉式選單得到
				String ad_no = request.getParameter("ad_no");
				String chooseGroup = request.getParameter("chooseGroup");
				/***************************2.開始查詢資料*****************************************/
				AdService adSvc = new AdService();
				AdVO originalAdVO=adSvc.findByPrimaryKey(ad_no);
				Timestamp ad_idesta=originalAdVO.getAd_idesta();
				Timestamp ad_end=originalAdVO.getAd_end();
				Timestamp ad_start=null;
				int ad_exp=0;//2
				java.util.Date now=new java.util.Date();
				JSONObject obj = new JSONObject();
				AdVO adVO=new AdVO();
				if(ad_stat.equals("審核通過") && ad_end.getTime()<=now.getTime()){//選通過但廣告結束日期已過掉
					ad_stat="審核不通過";
					ad_start=ad_end;//1
					obj.put("result", "outTime");
				}else if(ad_stat.equals("審核通過") && ad_end.getTime()<=ad_idesta.getTime()){//選通過但開始時間比結束時間晚
					ad_stat="審核不通過";
					ad_start=ad_end;//1
					obj.put("result", "startOverEnd");
				}else if(ad_stat.equals("審核通過") && ad_idesta.getTime()<now.getTime()){//現在時間比理想開始時間晚
					ad_start=new Timestamp(now.getTime());//1
					ad_exp=(int)(100*(ad_end.getTime()-now.getTime())/(1000*60*60*24));//2
					obj.put("result", "pass");
				}else if(ad_stat.equals("審核通過") && ad_idesta.getTime()>=now.getTime()){//正常情形
					ad_start=ad_idesta;//1
					ad_exp=(int)(100*(ad_end.getTime()-ad_idesta.getTime())/(1000*60*60*24));//2
					obj.put("result", "pass");
				}else if(ad_stat.equals("審核不通過")){
					ad_start=ad_end;//1
					obj.put("result", "fail");
				}else{//選待審核
					ad_start=ad_end;//1
					obj.put("result", "waiting");
				}
				adVO.setAd_start(ad_start);//1
				adVO.setAd_exp(ad_exp);//2
				adVO.setAd_no(ad_no);//3
				adVO.setAd_stat(ad_stat);//4
				adSvc.updateAd_stat(adVO);//修改資料庫
				
				List<AdVO> adVOList=null;
				String groupShop_stat=null;
				if(chooseGroup==null){ //直接從首頁送來時
					groupShop_stat="待審核";
				}else{                 //從下拉式選項送來時 
					if(chooseGroup.equals("waiting"))
						groupShop_stat="待審核";
					else if(chooseGroup.equals("pass"))
						groupShop_stat="審核通過";
					else if(chooseGroup.equals("fail"))
						groupShop_stat="審核不通過";
					
				}
				
				if(groupShop_stat!=null){
					adVOList = adSvc.findByAd_stat(groupShop_stat);
				}else{
					adVOList = adSvc.getAll();
				}
				session.setAttribute("adVOList", adVOList);//為了換頁重取資料時能查到正確資料才存的


				obj.put("ad_exp", ad_exp);
				out.print(obj.toString());
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/advertisement/adReview.jsp");
				failureView.forward(request, response);
			}
		}
//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	        

	}

}
