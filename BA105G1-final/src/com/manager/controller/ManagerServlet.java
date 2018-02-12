package com.manager.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.man_aut.model.*;
import com.manager.model.*;
import com.oreilly.servlet.*;
//限定上傳檔案最大30MB
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 30 * 1024 * 1024, maxRequestSize = 30 * 1024 * 1024)
public class ManagerServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8"); 
        res.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		HttpSession session=req.getSession();
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String requestURL =req.getParameter("requestURL");
				String whichPage =req.getParameter("whichPage");
				String man_no = req.getParameter("man_no");
				if (man_no == null || (man_no.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				//有輸入的狀況
				else{
					if (man_no.trim().length() != 10) {
						errorMsgs.add("管理員編號為10碼");
					}
					if (!man_no.trim().matches("MAN\\d{7}")){
						errorMsgs.add("管理員編號格式為MAN加七位數字");
					}
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				ManagerService managerSvc = new ManagerService();
				ManagerVO managerVO = managerSvc.getOneManager(man_no);
				if (managerVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				Man_autService man_autSvc = new Man_autService();
				List<Man_autVO> man_autVOList=man_autSvc.getMan_autListByMan_no(man_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("managerVO", managerVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("man_autVOList", man_autVOList);
				session.setAttribute("requestURL", requestURL);
				session.setAttribute("whichPage", whichPage);
				String url = "/back-end/manager/listOneManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneManager.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllManager.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String man_no =req.getParameter("man_no");
				String requestURL =req.getParameter("requestURL");
				String whichPage =req.getParameter("whichPage");
				/***************************2.開始查詢資料****************************************/
				ManagerService managerSvc = new ManagerService();
				ManagerVO managerVO = managerSvc.getOneManager(man_no);
		
				//管理員權限的區塊
				Man_autService man_autSvc = new Man_autService();
				List<Man_autVO> man_autVOList = man_autSvc.getMan_autListByMan_no(man_no);			
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("managerVO", managerVO); // 資料庫取出的managerVO物件,存入req
				req.setAttribute("man_autVOList", man_autVOList); 
				if(requestURL!=null){
					session.setAttribute("requestURL", requestURL);
					session.setAttribute("whichPage", whichPage);
				}
				String url = "/back-end/manager/update_manager_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_manager_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_manager_input.jsp的請求
			System.out.println(new Date()+"進入update區塊");//(幫助debug)
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			ManagerVO managerVO=new ManagerVO();
			List<Man_autVO> man_autVOList=new ArrayList<>();
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String man_no =req.getParameter("man_no").trim(); //*使用者不能改的
				String man_id =req.getParameter("man_id").trim(); //*使用者不能改的
				
				String man_pas = req.getParameter("man_pas").trim();
				if (man_pas == null || man_pas.trim().length() == 0) {
					errorMsgs.put("man_pas","密碼請勿空白");
				}
				String man_pas2 = req.getParameter("man_pas2").trim();
				if (man_pas2 == null || man_pas2.trim().length() == 0) {
					errorMsgs.put("man_pas2","確認密碼請勿空白");
					man_pas="";
				}
				if (man_pas.trim().length() != 0 && man_pas2.trim().length() != 0 && !man_pas.trim().equals(man_pas2.trim())) {
					errorMsgs.put("man_pas","密碼與確認密碼不相同");
					man_pas="";
				}
				String man_name = req.getParameter("man_name").trim();
				String man_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (man_name == null || man_name.trim().length() == 0) {
					errorMsgs.put("man_name","姓名請勿空白");
				} else if(!man_name.trim().matches(man_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("man_name","姓名只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String man_gen =req.getParameter("man_gen");
				if (man_gen == null) {
					errorMsgs.put("man_gen","請選性別");
				}
				String man_tel =req.getParameter("man_tel").trim();
				if (man_tel == null || man_tel.trim().length() == 0) {
					errorMsgs.put("man_tel","手機請勿空白");
				} else if(!man_tel.trim().matches("09\\d{8}")) { 
					errorMsgs.put("man_tel","手機只能是09開頭的數字組合, 總長度為10");
	            }
				String citySelect = req.getParameter("citySelect"); //此下拉式選單未選則null,選預設則空字串
				if (citySelect == null || "".equals(citySelect)) {
					errorMsgs.put("citySelect","請選縣市");
				}
				String areaSelect = req.getParameter("areaSelect"); //此下拉式選單未選則null,選預設則空字串
				if (areaSelect == null || "".equals(areaSelect)) {
					errorMsgs.put("areaSelect","請選鄉鎮區");
				}
				String roadSelect = req.getParameter("roadSelect").trim(); //輸入盒
				if (roadSelect.trim().length() == 0) {
					errorMsgs.put("roadSelect","請選路");
				}
				String zipSelect = req.getParameter("zipSelect").trim(); //輸入盒
				if (zipSelect.trim().length() == 0) {
					errorMsgs.put("zipSelect","請選郵遞區號");
				} else if(!zipSelect.trim().matches("\\d{5}")) { 
					errorMsgs.put("zipSelect","郵遞區號只能是5碼的數字");
	            }
				String add = req.getParameter("add").trim(); //輸入盒
				if (add == null || add.trim().length() == 0) {
					errorMsgs.put("add","請輸入完整地址");
				}
				
				String man_sta = req.getParameter("man_sta").trim(); //*使用者只能用下拉式選單選


				byte[] man_pho=null;
				String man_pho_base64=null;
				if(!req.getParameter("img").equals(""))
					man_pho_base64=req.getParameter("img");
				
				Part part = req.getPart("man_pho");
				if (part!=null) {
					String type = part.getContentType();
					//處理input type="file"的部分
					if(type!=null){
						//這次沒上傳檔案的狀況
						if(type.equals("application/octet-stream")){
							//這次沒上傳且上次也沒上傳檔案的狀況
							if(man_pho_base64==null)
								errorMsgs.put("man_pho","請上傳照片");
						}
						//這次有上傳檔案的狀況
						else{
							if(type.startsWith("image/") ){
								if(part.getSize()<(1024 * 1024)){
									man_pho = getPictureByteArray(part.getInputStream());
									man_pho_base64=Base64Encoder.encode(man_pho);
								}
								else{
									errorMsgs.put("man_pho","照片需小於1MB");
								}
							}else{
								errorMsgs.put("man_pho","照片格式錯誤，請上傳jpeg, png, gif檔案");
							}
						}
					}
				}

				

				if(zipSelect.equals("")) //輸入盒
					zipSelect="?";
				if(citySelect == null || "".equals(citySelect)) //此下拉式選單未選則null,選預設則空字串
					citySelect="?";
				if(areaSelect == null || "".equals(areaSelect)) //此下拉式選單未選則null,選預設則空字串
					areaSelect="?";
				if(roadSelect.equals("")) //輸入盒
					roadSelect="?";
				if(add.equals("")) //輸入盒
					add="?";

				String man_add=zipSelect+"_"+citySelect+"_"+areaSelect.split("_")[0]+"_"+roadSelect+"_"+add;
				
				managerVO.setMan_no(man_no);
				managerVO.setMan_id(man_id);
				managerVO.setMan_pas(man_pas);
				managerVO.setMan_name(man_name);
				managerVO.setMan_gen(man_gen);
				managerVO.setMan_tel(man_tel);
				managerVO.setMan_add(man_add);
				managerVO.setMan_pho_base64(man_pho_base64);
				managerVO.setMan_sta(man_sta);
				
				//管理員權限的區塊
				String[] mf_noArray = req.getParameterValues("mf_no");
				if(mf_noArray!=null){
					for(int i=0;i<mf_noArray.length;i++){
						String mf_no=mf_noArray[i];
						Man_autVO man_autVO=new Man_autVO();
						man_autVO.setMf_no(mf_no);
						man_autVOList.add(man_autVO);
					}
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("managerVO", managerVO); // 含有輸入格式錯誤的managerVO物件,也存入req
					req.setAttribute("man_autVOList", man_autVOList);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manager/update_manager_input.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給update_manager_input.jsp");//(幫助debug)
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ManagerService managerSvc = new ManagerService();
				managerSvc.updateManager(man_no,man_id,man_pas,man_name,man_gen,man_tel,man_add,man_pho_base64,man_sta);
				
				Man_autService man_autSvc = new Man_autService();
				List<Man_autVO> OriMan_autVOList=man_autSvc.getMan_autListByMan_no(man_no);
				List<String> OriMf_noList=new ArrayList<>();
				for(Man_autVO OriMan_autVO:OriMan_autVOList){
					OriMf_noList.add(OriMan_autVO.getMf_no());
				}
				for(Man_autVO man_autVO:man_autVOList){
					if(OriMf_noList.contains(man_autVO.getMf_no())){
						OriMf_noList.remove(man_autVO.getMf_no());
					}else{
						man_autSvc.addMan_aut(man_autVO.getMf_no(), man_no);//新增權限
					}
				}
				for(String deMf_no:OriMf_noList){
					man_autSvc.deleteMan_aut(deMf_no, man_no);//刪除權限
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("managerVO", managerVO); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("man_autVOList", man_autVOList);
				String url = "/back-end/manager/listOneManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);


				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.put("exception","修改資料失敗:"+e.getMessage());
				req.setAttribute("managerVO", managerVO);
				req.setAttribute("man_autVOList", man_autVOList);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manager/update_manager_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
        	System.out.println(new Date()+"進入insert區塊");//(幫助debug)
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			ManagerVO managerVO=new ManagerVO();
			List<Man_autVO> man_autVOList=new ArrayList<>();
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String man_id =req.getParameter("man_id").trim(); 
				if (man_id == null || man_id.trim().length() == 0) {
					errorMsgs.put("man_id","帳號請勿空白");
				}
				ManagerService managerSvc = new ManagerService();
				if (managerSvc.getOneManagerByMan_id(man_id) != null) {
					errorMsgs.put("man_id","帳號重複");
				}
				
				String man_pas = req.getParameter("man_pas").trim();
				if (man_pas == null || man_pas.trim().length() == 0) {
					errorMsgs.put("man_pas","密碼請勿空白");
				}
				String man_pas2 = req.getParameter("man_pas2").trim();
				if (man_pas2 == null || man_pas2.trim().length() == 0) {
					errorMsgs.put("man_pas2","確認密碼請勿空白");
					man_pas="";
				}
				if (man_pas.trim().length() != 0 && man_pas2.trim().length() != 0 && !man_pas.trim().equals(man_pas2.trim())) {
					errorMsgs.put("man_pas","密碼與確認密碼不相同");
					man_pas="";
				}
				String man_name = req.getParameter("man_name").trim();
				String man_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (man_name == null || man_name.trim().length() == 0) {
					errorMsgs.put("man_name","姓名請勿空白");
				} else if(!man_name.trim().matches(man_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("man_name","姓名只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String man_gen =req.getParameter("man_gen");
				if (man_gen == null) {
					errorMsgs.put("man_gen","請選性別");
				}
				String man_tel =req.getParameter("man_tel").trim();
				if (man_tel == null || man_tel.trim().length() == 0) {
					errorMsgs.put("man_tel","手機請勿空白");
				} else if(!man_tel.trim().matches("09\\d{8}")) { 
					errorMsgs.put("man_tel","手機只能是09開頭的數字組合, 總長度為10");
	            }
				String citySelect = req.getParameter("citySelect"); //此下拉式選單未選則null,選預設則空字串
				if (citySelect == null || "".equals(citySelect)) {
					errorMsgs.put("citySelect","請選縣市");
				}
				String areaSelect = req.getParameter("areaSelect"); //此下拉式選單未選則null,選預設則空字串
				if (areaSelect == null || "".equals(areaSelect)) {
					errorMsgs.put("areaSelect","請選鄉鎮區");
				}
				String roadSelect = req.getParameter("roadSelect").trim(); //輸入盒
				if (roadSelect.trim().length() == 0) {
					errorMsgs.put("roadSelect","請選路");
				}
				String zipSelect = req.getParameter("zipSelect").trim(); //輸入盒
				if (zipSelect.trim().length() == 0) {
					errorMsgs.put("zipSelect","請選郵遞區號");
				} else if(!zipSelect.trim().matches("\\d{5}")) { 
					errorMsgs.put("zipSelect","郵遞區號只能是5碼的數字");
	            }
				String add = req.getParameter("add").trim(); //輸入盒
				if (add == null || add.trim().length() == 0) {
					errorMsgs.put("add","請輸入完整地址");
				}
				
				byte[] man_pho=null;
				String man_pho_base64=null;
				if(!req.getParameter("img").equals(""))
					man_pho_base64=req.getParameter("img");
				
				Part part = req.getPart("man_pho");
				if (part!=null) {
					String type = part.getContentType();
					//處理input type="file"的部分
					if(type!=null){
						//這次沒上傳檔案的狀況
						if(type.equals("application/octet-stream")){
							//這次沒上傳且上次也沒上傳檔案的狀況
							if(man_pho_base64==null)
								errorMsgs.put("man_pho","請上傳照片");
						}
						//這次有上傳檔案的狀況
						else{
							if(type.startsWith("image/") ){
								if(part.getSize()<(1024 * 1024)){
									man_pho = getPictureByteArray(part.getInputStream());
									man_pho_base64=Base64Encoder.encode(man_pho);
								}
								else{
									errorMsgs.put("man_pho","照片需小於1MB");
								}
							}else{
								errorMsgs.put("man_pho","照片格式錯誤，請上傳jpeg, png, gif檔案");
							}
						}
					}
				}

				
				if(zipSelect.equals("")) //輸入盒
					zipSelect="?";
				if(citySelect == null || "".equals(citySelect)) //此下拉式選單未選則null,選預設則空字串
					citySelect="?";
				if(areaSelect == null || "".equals(areaSelect)) //此下拉式選單未選則null,選預設則空字串
					areaSelect="?";
				if(roadSelect.equals("")) //輸入盒
					roadSelect="?";
				if(add.equals("")) //輸入盒
					add="?";

				String man_add=zipSelect+"_"+citySelect+"_"+areaSelect.split("_")[0]+"_"+roadSelect+"_"+add;
				managerVO.setMan_id(man_id);
				managerVO.setMan_pas(man_pas);
				managerVO.setMan_name(man_name);
				managerVO.setMan_gen(man_gen);
				managerVO.setMan_tel(man_tel);
				managerVO.setMan_add(man_add);
				managerVO.setMan_pho_base64(man_pho_base64);
				
				
				//管理員權限的區塊
				String[] mf_noArray = req.getParameterValues("mf_no");
				if(mf_noArray!=null){
					for(int i=0;i<mf_noArray.length;i++){
						String mf_no=mf_noArray[i];
						Man_autVO man_autVO=new Man_autVO();
						man_autVO.setMf_no(mf_no);
						man_autVOList.add(man_autVO);
					}
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("managerVO", managerVO); // 含有輸入格式錯誤的managerVO物件,也存入req
					req.setAttribute("man_autVOList", man_autVOList);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manager/addManager.jsp");
					failureView.forward(req, res);
					System.out.println("成功轉交錯誤訊息給addManager.jsp");//(幫助debug)
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				managerVO = managerSvc.addManager(man_id,man_pas,man_name,man_gen,man_tel,man_add,man_pho_base64);
				if(mf_noArray!=null){
					String man_no = managerVO.getMan_no();
					Man_autService man_autSvc = new Man_autService();
					for(int i=0;i<mf_noArray.length;i++){
						String mf_no=mf_noArray[i];
						man_autSvc.addMan_aut(mf_no,man_no);
					}
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("managerVO", managerVO); 
				req.setAttribute("man_autVOList", man_autVOList);
				String url = "/back-end/manager/listOneManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllManager.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("exception",e.getMessage());
				req.setAttribute("managerVO", managerVO);
				req.setAttribute("man_autVOList", man_autVOList);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manager/addManager.jsp");
				failureView.forward(req, res);
				System.out.println("發生例外");//(幫助debug)
			}
		}
        
		if ("getManagersByMan_sta".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

				ManagerService managerSvc = new ManagerService();
				List<ManagerVO> managerVOList=null;
				String chooseGroup = req.getParameter("chooseGroup");
				String man_sta=null;
				if(chooseGroup==null){ //直接從select_page.jsp送來時
					man_sta="正常";
				}else{                 //從listAllManager.jsp下拉式選項送來時 
					if(chooseGroup.equals("normal"))
						man_sta="正常";
					else if(chooseGroup.equals("forbidden"))
						man_sta="停權";
				}
				
				

				
				/***************************2.開始查詢資料*****************************************/
				if(man_sta!=null){
					managerVOList = managerSvc.getManagersByMan_sta(man_sta);
				}else{
					managerVOList = managerSvc.getAll();
				}
				
				if (managerVOList == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manager/listAllManager.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				session.setAttribute("managerVOList", managerVOList);
				String url = "/back-end/manager/listAllManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/manager/listAllManager.jsp");
				failureView.forward(req, res);
			}
		}
       

	}
	
	
	
	
	
	//從part得到使用者上傳的InputStream，再透過此自訂方法，轉成byte[]回傳
	public static byte[] getPictureByteArray(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  //ByteArrayOutputStream有內建byte陣列透過write()方法寫入資料,toByteArray()方法回傳 
		byte[] buffer = new byte[8192];
		int i;
		while ((i = is.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		is.close();

		return baos.toByteArray();
	}

}
