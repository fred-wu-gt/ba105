package com.prom.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prom.model.PromService;
import com.prom.model.PromVO;

public class PromServlet extends HttpServlet {

	private static final long serialVersionUID = -781731091896332556L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");

		// TODO 2018/01/21整合須併入 承霖
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			/** 送出錯誤訊息 **/
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String prom_no = request.getParameter("prom_no");
				String shop_no = request.getParameter("shop_no");
				java.sql.Timestamp prom_start = null;
				try {
					prom_start = java.sql.Timestamp.valueOf(request.getParameter("prom_start").trim());
					System.out.println("prom1:" + prom_no);
				} catch (Exception e) {
					prom_start=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Timestamp prom_end = null;
				try {
					prom_end = java.sql.Timestamp.valueOf(request.getParameter("prom_end").trim());
					System.out.println("prom2:" + prom_no);
				} catch (Exception e) {
					prom_end = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String userSelected = request.getParameter("hasDiscount");
				try {
					PromVO promVO = null;
					if (prom_end.after(prom_start)) {
						PromService promSvc = new PromService();
						PromVO originalPromVO = promSvc.findByPrimaryKey(shop_no);
						if(originalPromVO!=null){ //資料庫原本有資料，則修改
							if(userSelected.equals("noDiscount")) {
								Double prom_dis = new Double(1.0);
								promSvc.updateProm(prom_no, shop_no, prom_dis, prom_start, prom_end);
								promVO = promSvc.findByPrimaryKey(shop_no);
								request.setAttribute("promVO", promVO);
								String saveSuccess = "修改完成";
								request.setAttribute("saveSuccess", saveSuccess);
								RequestDispatcher failureView = request.getRequestDispatcher("/front-end/prom/addprom.jsp");
								failureView.forward(request, response);
							}
							else if(userSelected.equals("discount")) {
								String selected = request.getParameter("prom_dis").trim();
								String selected2 = request.getParameter("prom_dis1").trim();
								String totalValue = selected + selected2;
								BigDecimal big = new BigDecimal(totalValue);
								BigDecimal mig = new BigDecimal(new Double(0.01));
								mig = mig.setScale(2,BigDecimal.ROUND_HALF_UP);
								BigDecimal result = big.multiply(mig);
								result = result.setScale(2,BigDecimal.ROUND_HALF_UP);
								Double prom_dis = new Double(result.toString());
								promSvc.updateProm(prom_no, shop_no, prom_dis, prom_start, prom_end);
								promVO = promSvc.findByPrimaryKey(shop_no);
								request.setAttribute("promVO", promVO);
								request.setAttribute("firstOption", String.valueOf(prom_dis).substring(2,3));
								request.setAttribute("secondOption", String.valueOf(prom_dis).substring(3));
								String saveSuccess = "修改完成";
								request.setAttribute("saveSuccess", saveSuccess);
								RequestDispatcher failureView = request.getRequestDispatcher("/front-end/prom/addprom.jsp");
								failureView.forward(request, response);
							}
						}else{//資料庫原本沒資料，則新增
							if(userSelected.equals("noDiscount")) {
								Double prom_dis = new Double(1.0);
								promSvc.addProm(shop_no, prom_dis, prom_start, prom_end);
								promVO = promSvc.findByPrimaryKey(shop_no);
								request.setAttribute("promVO", promVO);
								String saveSuccess = "新增完成";
								request.setAttribute("saveSuccess", saveSuccess);
								RequestDispatcher failureView = request.getRequestDispatcher("/front-end/prom/addprom.jsp");
								failureView.forward(request, response);
							}
							else if(userSelected.equals("discount")) {
								String selected = request.getParameter("prom_dis").trim();
								String selected2 = request.getParameter("prom_dis1").trim();
								String totalValue = selected + selected2;
								BigDecimal big = new BigDecimal(totalValue);
								BigDecimal mig = new BigDecimal(new Double(0.01));
								mig = mig.setScale(2,BigDecimal.ROUND_HALF_UP);
								BigDecimal result = big.multiply(mig);
								result = result.setScale(2,BigDecimal.ROUND_HALF_UP);
								Double prom_dis = new Double(result.toString());
								promSvc.addProm(shop_no, prom_dis, prom_start, prom_end);
								promVO = promSvc.findByPrimaryKey(shop_no);
								request.setAttribute("promVO", promVO);
								request.setAttribute("firstOption", String.valueOf(prom_dis).substring(2,3));
								request.setAttribute("secondOption", String.valueOf(prom_dis).substring(3));
								String saveSuccess = "新增完成";
								request.setAttribute("saveSuccess", saveSuccess);
								RequestDispatcher failureView = request.getRequestDispatcher("/front-end/prom/addprom.jsp");
								failureView.forward(request, response);
							}
						}
							
					} else {
						String notSaveSuccess = "修改不完成";
						request.setAttribute("notSaveSuccess", notSaveSuccess);
						RequestDispatcher failureView = request.getRequestDispatcher("/front-end/prom/addprom.jsp");
						failureView.forward(request, response);
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("請選擇折扣");
				}
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/prom/addprom.jsp");
				failureView.forward(request, response);
			}
		}
		// TODO 2018/01/21整合須併入 承霖
		if(action.equals("init")) {
			PromService promSvc = new PromService();
			PromVO promVO = promSvc.findByPrimaryKey(request.getParameter("shop_no"));
			if(promVO!=null){
				String initialData = "初始化資料";
				request.setAttribute("initialData", initialData);
				request.setAttribute("promVO", promVO);
				request.setAttribute("firstOption", String.valueOf(promVO.getProm_dis()).substring(2,3));
				request.setAttribute("secondOption", String.valueOf(promVO.getProm_dis()).substring(3));
			}
			RequestDispatcher failureView = request.getRequestDispatcher("/front-end/prom/addprom.jsp");
			failureView.forward(request, response);
		}
	}

}
