package com.point.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.commodity.model.CommodityService;
import com.commodity.model.CommodityVO;
import com.member.model.MemberDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.ord_det.model.Ord_detVO;
import com.ord_mas.model.Ord_masService;
import com.ord_mas.model.Ord_masVO;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet("/PointServlet")
public class PointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PointServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html ; charset=UTF-8");
		HttpSession session = req.getSession();
		
		
		System.out.println("===========TEST1============");
		String action = req.getParameter("action");
		MemberService memSvc = new MemberService();
	
		
		if("BUYPOINTS".equals(action)){  //從top_up.jsp傳來
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			MemberVO memberVO=(MemberVO)session.getAttribute("loginMemberVO");
			String mem_id = memberVO.getMem_id();
			System.out.println(mem_id);
			Integer inner_value= new Integer(memberVO.getMem_val());
			System.out.println(mem_id+"加值前點數 :" + inner_value );
			 String buy_valueS = req.getParameter("buy_value");
			 if(buy_valueS !=null && buy_valueS.trim().length()!=0){
				 Integer buy_value = new Integer(buy_valueS);
				 System.out.println("本次加值 :" + buy_value +" 點");
				 String  mem_val = String.valueOf (inner_value+buy_value) ;
				 memberVO.setMem_val(mem_val);//把新的值塞進會員VO
				 memSvc.updateMem_val(mem_id, mem_val);
				 System.out.println(mem_id+"目前點數為 :" + mem_val +" 點");
			 }else{
				errorMsgs.add("請輸入儲值金額!");
			 }
			

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/point/top_up.jsp");
					failureView.forward(req, res);
					return;
				}
			
			/***************************
			 * 修改完成,準備轉交(Send the Success view)
			 ****************************/
			session.setAttribute("memVO", memberVO); 
			String url = "/front-end/point/After_top_up.jsp";
			res.sendRedirect(req.getContextPath()+url);
		}
		

	}
}
