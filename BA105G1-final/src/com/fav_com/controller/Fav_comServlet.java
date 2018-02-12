package com.fav_com.controller;

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
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.fav_com.model.Fav_comService;
import com.fav_com.model.Fav_comVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.oreilly.servlet.Base64Encoder;

public class Fav_comServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fav_comServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
							.getRequestDispatcher("/front-end/fav_com/select_page.jsp");
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
							.getRequestDispatcher("/front-end/fav_com/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				Fav_comService favComSvc = new Fav_comService();
				List<Fav_comVO> list= favComSvc.findByMem_no(mem_no);
				
				if (list.size() == 0) {
					errorMsgs.add("查無資料");
				} 
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/fav_com/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的Fav_comVo物件,存入req
//				String mem_photo_base64 = Base64Encoder.encode(memVO.getMem_photo());
//				req.setAttribute("mem_photo_base64", mem_photo_base64);
				String url = "/front-end/fav_com/listOneFav_com2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/fav_com/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		 if ("insert".equals(action)) { // 來自addfavcom.jsp的請求  
			 
			 System.out.println("近來insert");
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				res.setContentType("text/plain"); 
		        req.setCharacterEncoding("utf-8");
		        res.setCharacterEncoding("utf-8");
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String mem_no = req.getParameter("mem_no").trim();
					System.out.println("-------------mem_no=" +mem_no);
					String str1=null;
					
					if (mem_no == null || mem_no.trim().length() == 0) {
						errorMsgs.add("會員編號: 請勿空白");
					}
					
					try {
						str1 = new String(mem_no);
					} catch (Exception e) {
						errorMsgs.add("會員編號格式不正確");
					}
					System.out.println(mem_no);
					
					String com_no = req.getParameter("com_no");
					System.out.println("------------com_no =" +com_no);
					
					Fav_comService fav_comSvc1 = new Fav_comService();
					List<Fav_comVO> list1= fav_comSvc1.findByMem_no(mem_no);
					List<Fav_comVO> list2= fav_comSvc1.getAll();
					
					for(Fav_comVO abc:list1){
						if((abc.getCom_no()).equals(com_no)){
//							System.out.println("again choose");
//							System.out.println(mem_no);
//							System.out.println(com_no);
							errorMsgs.add("商品重複選擇"+com_no);
							
							
						}	
					}
					
					
					
					Fav_comVO fav_comVO = new Fav_comVO();
					fav_comVO.setMem_no(mem_no);
					fav_comVO.setCom_no(com_no);
					
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("fav_comVO", fav_comVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/fav_com/addfavcom.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					Fav_comService fav_comSvc = new Fav_comService();
					fav_comSvc.addFav_com(mem_no, com_no);
					
					
					/***************************3.開始查詢資料***************************************/
					List<Fav_comVO> list= fav_comSvc.findByMem_no(mem_no);
					if (list.size() == 0) {
						errorMsgs.add("查無資料");
					} 
					
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/fav_com/select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************4.查詢完成,準備轉交(Send the Success view)***********/
//					req.setAttribute("list", list);
//					String url = "/front-end/fav_com/listOneFav_com.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneFav_com.jsp
//					successView.forward(req, res);	
					
//					HashMap map = new HashMap();
//					map.put("ok", "成功");
					JSONObject obj = new JSONObject();
					PrintWriter out = res.getWriter();
					obj.put("result", "success");
					out.print(obj.toString());
					System.out.println("ok");
					out.flush();
			        out.close();
					
					
					
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/fav_com/addfavcom.jsp");
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
					String com_no = req.getParameter("com_no");
					System.out.println(mem_no);
					System.out.println(com_no);
					/***************************2.開始刪除/查詢資料***************************************/
					Fav_comService fav_comSvc = new Fav_comService();
					fav_comSvc.deletFav_com(mem_no,com_no);
					
					Fav_comService favComSvc = new Fav_comService();
					List<Fav_comVO> list= favComSvc.findByMem_no(mem_no);
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
					req.setAttribute("list", list);
					String url = "/front-end/fav_com/listOneFav_com2.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/fav_com/listOneFav_com2.jsp");
					failureView.forward(req, res);
				}
			}
		
		
		
		
		
		
		
		
		
	}

}
