package login;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import com.manager.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;
public class LoginHandler extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
//三種身分的驗證都送過來
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/plain"); 
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		HttpSession session=req.getSession();
		
		
		if ("managerLogin".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String account =req.getParameter("account").trim();   //輸入盒
				String password = req.getParameter("password").trim();//輸入盒
				
				/***************************2.開始查詢資料*****************************************/
				ManagerService managerSvc = new ManagerService();
				ManagerVO managerVO=managerSvc.getOneManagerByMan_id(account);//以帳號去查資料庫得到該VO或null
				if (managerVO == null) {    //無此帳號
					errorMsgs.add("帳號或密碼錯誤");
				}else{
					String man_pas = managerVO.getMan_pas();
					String man_sta = managerVO.getMan_sta();
					if (!man_pas.equals(password))    //有此帳號但密碼錯誤
						errorMsgs.add("帳號或密碼錯誤");
					else if(man_sta.equals("停權"))
						errorMsgs.add("您的帳號已被停權!");
						
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/login/be_login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//帳號密碼皆正確
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				session.setAttribute("loginManagerVO", managerVO);

		         String location = (String) session.getAttribute("location");
		         if (location != null) {
		           session.removeAttribute("location");   //看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
		           res.sendRedirect(location);            
		           return;
		         }
		         
		         String url = "/back-end/manager/select_page.jsp";  //若無，則轉交給首頁
		         RequestDispatcher successView = req.getRequestDispatcher(url);
		         successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/login/be_login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("managerLogout".equals(action)) {
			
			try {
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				session.removeAttribute("loginManagerVO");
				String url = "/login/be_login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/login/be_login.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if (action.equals("shopLogin")) {
			try {
				String shop_id = req.getParameter("shop_id").trim();
				String shop_psw = req.getParameter("shop_psw").trim();
				JSONObject obj = new JSONObject();
				PrintWriter out = res.getWriter();
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.login(shop_id, shop_psw);
				if (shopVO == null) {
					obj.put("result", "fail");
					System.out.println("帳號或密碼錯誤!");
				}else{
					String shop_stat=shopVO.getShop_stat();
					if(shop_stat.equals("停權")){
						obj.put("result", "ban");
						System.out.println("帳號已被停權!");
					}else if(shop_stat.equals("待審核")){
						obj.put("result", "checking");
						System.out.println("帳號待審核");
					}else{
						session.setAttribute("loginShopVO", shopVO);
						obj.put("shop_name", shopVO.getShop_name());
						obj.put("shop_no", shopVO.getShop_no());
						obj.put("result", "success");	
						System.out.println("商家 : "+shopVO.getShop_name()+" 成功登入");
					}
				}
				
				out.print(obj.toString());
				
				out.flush();
		        out.close();

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		
		
		if (action.equals("shopLogout")) {

			try {
				session.removeAttribute("loginShopVO");
				if(session.getAttribute("shopActlist")!=null)
					session.removeAttribute("shopActlist");
				JSONObject obj = new JSONObject();
				PrintWriter out = res.getWriter();
				obj.put("result", "success");
				out.print(obj.toString());
				System.out.println("商家成功登出");
				out.flush();
		        out.close();

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		
		if (action.equals("memberLogin")) {
			try {
				String account = req.getParameter("mem_id").trim();
				String password = req.getParameter("mem_psw").trim();
				JSONObject obj = new JSONObject();
				PrintWriter out = res.getWriter();
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(account);
				if (memberVO == null) {    //無此帳號
					obj.put("result", "fail");
					System.out.println("無此帳號!");
				}else{    //有此帳號
					String mem_psw = memberVO.getMem_psw();
					if (!mem_psw.equals(password)){//有此帳號但密碼錯誤
						obj.put("result", "fail");
						System.out.println("密碼錯誤!");
					}
					else{    //有此帳號且密碼正確
						String mem_stat=memberVO.getMem_stat();
						if(mem_stat.equals("停權")){
							obj.put("result", "ban");
							System.out.println("帳號已被停權!");
						}else{
							session.setAttribute("loginMemberVO", memberVO);
							obj.put("mem_name", memberVO.getMem_name());
							obj.put("mem_no", memberVO.getMem_no());
							obj.put("result", "success");	
							System.out.println("買家 : "+memberVO.getMem_name()+" 成功登入");
						}
					}
				}
				out.print(obj.toString());
				out.flush();
		        out.close();

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		
		
		if (action.equals("memberLogout")) {
			try {
				session.removeAttribute("loginMemberVO");
				if(session.getAttribute("shoppingcart")!=null)
					session.removeAttribute("shoppingcart");
				if(session.getAttribute("count")!=null)
					session.removeAttribute("count");
				System.out.println("一般會員shoppingcart:"+session.getAttribute("shoppingcart"));
				JSONObject obj = new JSONObject();
				PrintWriter out = res.getWriter();
				obj.put("result", "success");
				out.print(obj.toString());
				System.out.println("買家成功登出");
				out.flush();
		        out.close();

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		
		
	}

}
