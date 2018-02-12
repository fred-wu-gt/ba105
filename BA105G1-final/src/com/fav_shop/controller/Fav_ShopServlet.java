package com.fav_shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.fav_shop.model.Fav_shopService;
import com.fav_shop.model.Fav_shopVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.oreilly.servlet.Base64Encoder;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;



/**
 * Servlet implementation class Fav_ShopServlet
 */
@WebServlet("/Fav_ShopServlet")
public class Fav_ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fav_ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_no");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/fav_shop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_no = null;
				try {
					mem_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/fav_shop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				Fav_shopService favShopSvc = new Fav_shopService();
				List<Fav_shopVO> list= favShopSvc.findByMem_no(mem_no);
				
				if (list.size() == 0) {
					errorMsgs.add("查無資料");
				} 
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/fav_shop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的Fav_comVo物件,存入req

				String url = "/front-end/fav_shop/listOneFav_shop2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/fav_shop/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("insert_by_activity_more".equals(action)){ // 來自activity_more.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); 
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();
				String shop_no = req.getParameter("shop_no");
				String act_no = req.getParameter("act_no").trim();	// hidden
				Fav_shopVO fav_shopVO = new Fav_shopVO();
				fav_shopVO.setMem_no(mem_no);
				fav_shopVO.setShop_no(shop_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fav_shopVO", fav_shopVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Fav_shopService fav_shopSvc = new Fav_shopService();
				fav_shopSvc.addFav_shop(mem_no, shop_no);
				/***************************3.開始查詢資料***************************************/
	
				//活動~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.findByActNo(act_no);
				
				/***************************4.查詢完成,準備轉交(Send the Success view)***********/
				req.setAttribute("activityVO", activityVO);

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllMember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_no = req.getParameter("mem_no");
				String shop_no = req.getParameter("shop_no");
				System.out.println(mem_no);
				System.out.println(shop_no);
				/***************************2.開始刪除/查詢資料***************************************/
				Fav_shopService fav_shopSvc = new Fav_shopService();
				fav_shopSvc.deletFav_shop(mem_no, shop_no);
				
				
				Fav_shopService favShopSvc = new Fav_shopService();
				List<Fav_shopVO> list= favShopSvc.findByMem_no(mem_no);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
				req.setAttribute("list", list);
				String url = "/front-end/fav_shop/listOneFav_shop2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/fav_shop/listOneFav_shop.jsp");
				failureView.forward(req, res);
			}
		}
		//品萱
		if ("delete_ByActivity_more".equals(action)) { // 來自listAllMember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); 
			try {
				/***************************1.接收請求參數***************************************/
				String mem_no = req.getParameter("mem_no");
				String shop_no = req.getParameter("shop_no");
				String act_no = req.getParameter("act_no").trim();	// hidden
				System.out.println(mem_no + shop_no + act_no);
				System.out.println();
				/***************************2.開始刪除/查詢資料***************************************/
				Fav_shopService fav_shopSvc = new Fav_shopService();
				fav_shopSvc.deletFav_shop(mem_no, shop_no);

				/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
				ActivityService activitySvc = new ActivityService();
				ActivityVO activityVO = activitySvc.findByActNo(act_no);
				req.setAttribute("activityVO", activityVO);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		//子怡開始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~		
				if ("findForMemberToJSON".equals(action)) {
					res.setContentType("text/plain"); 
			        req.setCharacterEncoding("utf-8");
			        res.setCharacterEncoding("utf-8");
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						String mem_no = req.getParameter("mem_no");

						
						/***************************2.開始查詢資料*****************************************/
						Fav_shopService favShopSvc = new Fav_shopService();
						ShopService shopSvc = new ShopService();
						List<Fav_shopVO> Fav_shopVOList= favShopSvc.findByMem_no(mem_no);
						
						JSONArray jsonArray =new JSONArray();
						for(Fav_shopVO fav_shopVO:Fav_shopVOList){
							JSONObject obj = new JSONObject();
							ShopVO shopVO=shopSvc.findByPrimaryKey(fav_shopVO.getShop_no());
							obj.put("shop_no", shopVO.getShop_no());
							obj.put("shop_name", shopVO.getShop_name());
							obj.put("shop_photo_bas64", Base64Encoder.encode(shopVO.getShop_photo()));
							jsonArray.put(obj);
						}

						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						PrintWriter out = res.getWriter();
						out.print(jsonArray.toString());
						
						out.flush();
				        out.close();
						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if ("findForShopToJSON".equals(action)) {
					res.setContentType("text/plain"); 
			        req.setCharacterEncoding("utf-8");
			        res.setCharacterEncoding("utf-8");
					try {
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						String shop_no = req.getParameter("shop_no");

						
						/***************************2.開始查詢資料*****************************************/
						Fav_shopService favShopSvc = new Fav_shopService();
						MemberService memberSvc = new MemberService();
						List<Fav_shopVO> Fav_shopVOList= favShopSvc.findByShop_no(shop_no);
						
						JSONArray jsonArray =new JSONArray();
						for(Fav_shopVO fav_shopVO:Fav_shopVOList){
							JSONObject obj = new JSONObject();
							MemberVO memberVO=memberSvc.findByMem_no(fav_shopVO.getMem_no());
							obj.put("mem_no", memberVO.getMem_no());
							obj.put("mem_name", memberVO.getMem_name());
							obj.put("mem_photo_bas64", memberVO.getMem_photo_base64());
							jsonArray.put(obj);
						}

						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						PrintWriter out = res.getWriter();
						out.print(jsonArray.toString());
						
						out.flush();
				        out.close();
						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
				
		//子怡結束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	     
		
		
	}

}
