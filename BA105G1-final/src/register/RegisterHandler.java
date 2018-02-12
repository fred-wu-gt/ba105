package register;
import java.io.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import com.manager.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.oreilly.servlet.Base64Encoder;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;

import mail.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 30 * 1024 * 1024, maxRequestSize = 30 * 1024 * 1024)
public class RegisterHandler extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
//2種身分的驗證都送過來
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8"); 
        res.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		System.out.println(action);
	       if ("memberRegister".equals(action)) { // 來自addEmp.jsp的請求  
	        	System.out.println("in");
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String mem_id = req.getParameter("mem_id").trim();
					String mem_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,30}$";
					if (mem_id == null || mem_id.trim().length() == 0) {
						errorMsgs.add("會員帳號: 請勿空白");
					}else if(!mem_id.trim().matches(mem_idReg)){
						errorMsgs.add("會員帳號設定: 只能是中、英文字母、數字和_ , 且長度必需在3到30之間");
					}
					
					MemberService memSvc1 = new MemberService();
					MemberVO memVO1 = memSvc1.getOneMember(mem_id);
					if(memVO1!=null){
						errorMsgs.add("會員帳號已有申請，請另選帳號");
					}
					
					
					String mem_psw = req.getParameter("mem_psw").trim();
					String mem_pswReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,30}$";
					if (mem_psw == null || mem_psw.trim().length() == 0) {
						errorMsgs.add("密碼: 請勿空白");
					}else if(!mem_psw.trim().matches(mem_pswReg)){
						errorMsgs.add("密碼設定: 只能是中、英文字母、數字和_ , 且長度必需在3到30之間");
					}
					
					String check = req.getParameter("check").trim();
					String checkReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,30}$";
					if(check==null||check.trim().length()==0){
						errorMsgs.add("確認密碼: 請勿空白");
					}else if(!check.trim().matches(checkReg)){
						errorMsgs.add("密碼設定: 只能是中、英文字母、數字和_ , 且長度必需在3到30之間");
					}
					
					if(mem_psw.equals(check)){
						System.out.println("密碼相同");
					}else{
						errorMsgs.add("密碼與確認密碼輸入不相同 請再次檢查 ");
					}
					
					String mem_name = req.getParameter("mem_name");
					String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{3,10}$";
					if (mem_name == null || mem_name.trim().length() == 0) {
						errorMsgs.add("會員姓名:請勿空白");
					} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("會員姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
		            }
					
					String mem_gen = req.getParameter("mem_gen");
					if(mem_gen == null){
						errorMsgs.add("會員性別:請選擇");
					}
					
					
					java.sql.Date mem_bir = null;
					try {
						mem_bir = java.sql.Date.valueOf(req.getParameter("mem_bir").trim());
					} catch (IllegalArgumentException e) {
						mem_bir=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入生日!");
					}

					String mem_email = req.getParameter("mem_email");
//					String mem_emailReg = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,4}*$";
					if (mem_email == null || mem_email.trim().length() == 0) {
						errorMsgs.add("電子郵件: 請勿空白");
					} 
//						else if(!mem_email.trim().matches(mem_emailReg)) { //以下練習正則(規)表示式(regular-expression)
//						errorMsgs.add("電子郵件格式:XXXX@XXXX.COM.TW ");
//		            }
					
					
					String mem_phone = req.getParameter("mem_phone");
					String mem_phoneReg = "^[(0-9_)]{9,10}$";
					if(mem_phone==null|| mem_phone.trim().length() == 0){
						errorMsgs.add("電話號碼: 請勿空白");
					}else if(!mem_phone.trim().matches(mem_phoneReg)){
						errorMsgs.add("電話號碼: 只能是數字長度必需在10");
					}
					
					String mem_loc = req.getParameter("mem_loc");
					String mem_locReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,30}$";
					if (mem_loc == null || mem_loc.trim().length() == 0) {
						errorMsgs.add("會員地址: 請勿空白");
					} else if(!mem_loc.trim().matches(mem_locReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("會員地址: 只能是中文字母、數字和_ , 且長度必需在3到30之間");
		            }
					
					byte[] mem_photo=null;
					String mem_photo_base64=null;
					
					
					Part part = req.getPart("mem_photo");
					if(part!=null){
						String str = part.getContentType();
						if(str.equals("application/octet-stream")){
							errorMsgs.add("動動手選擇上傳照片");
						}
					
						else{
							if(str.startsWith("image/") ){
								
								if(part.getSize()<(10*1024 * 1024)){
									mem_photo = getPictureByteArray(part.getInputStream());
									mem_photo_base64=Base64Encoder.encode(mem_photo);
								}
								else
									{
										errorMsgs.add("照片需小於10MB");
									}
								
							}else
								{
									errorMsgs.add("照片格式錯誤，請上傳jpeg, png, gif檔案");
								}
							
						}	
					
				}
					//System.out.println(mem_photo_base64);
					
					
					String mem_stat = req.getParameter("mem_stat");
					String mem_statReg = "^[(\u4e00-\u9fa5)]{2,10}$";
					if (mem_stat == null || mem_stat.trim().length() == 0) {
						errorMsgs.add("會員狀態: 請勿空白");
					} else if(!mem_stat.trim().matches(mem_statReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("會員狀態: 只能正常或違規 ");
		            }
					
					
					String mem_poin = req.getParameter("mem_poin");
					String mem_poinReg = "^[(0-9)]{1,9}$";
					if (mem_poin == null || mem_poin.trim().length() == 0) {
						errorMsgs.add("違規記點: 請勿空白");
					} else if(!mem_poin.trim().matches(mem_poinReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("違規記點: 只能是數字");
		            }
					
					
					
					String mem_val = req.getParameter("mem_val");
					String mem_valReg = "^[(0-9)]{1,10}$";
					if (mem_val == null || mem_val.trim().length() == 0) {
						errorMsgs.add("會員點數: 請勿空白");
					} else if(!mem_val.trim().matches(mem_valReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("會員點數: 只能是數字");
		            }

					

					MemberVO memVO = new MemberVO();
					memVO.setMem_id(mem_id);
					memVO.setMem_psw(mem_psw);
					memVO.setMem_name(mem_name);
					memVO.setMem_gen(mem_gen);
					memVO.setMem_bir(mem_bir);
					memVO.setMem_email(mem_email);
					memVO.setMem_loc(mem_loc);
					memVO.setMem_phone(mem_phone);
					memVO.setMem_photo_base64(mem_photo_base64);
					memVO.setMem_stat(mem_stat);
					memVO.setMem_poin(mem_poin);
					memVO.setMem_val(mem_val);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/register/addMember2.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					MemberService memSvc = new MemberService();
					memSvc.addMember(mem_id,mem_psw, mem_name, mem_gen, mem_bir,mem_email,mem_phone,mem_loc,
								mem_photo,mem_photo_base64,mem_stat,mem_poin,mem_val,check);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					MailService mailService = new MailService();
					String to = mem_email.trim();
				    String subject = "密碼通知";
				      
				      String ch_name = mem_name;
				      //String passWord = new GenAuthCode().GiveMeCode(8);
				      String passWord="Q0RjUqwb";
				      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passWord + "\n" +" (已經啟用)"; 
				      mailService.sendMail(to, subject, messageText);
				      System.out.println("====================================================");
					
					req.setAttribute("memVO1", memVO);
					String url = "/front-end/register/addMemberverify.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/register/addMember2.jsp");
					failureView.forward(req, res);
				}
				
				
			}
			
	      
	       if ("memberverify".equals(action)) {
	    	   
	    	   List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				MemberVO memVO = new MemberVO();
	    	   String check = req.getParameter("check").trim();
	    	   if(check==null||check.trim().length()==0){
					errorMsgs.add("驗證碼: 請勿空白");
	          }else if(check.equals("Q0RjUqwb")){
	        	  req.setAttribute("memVO1", memVO);
	        	  String url = "/front-end/register/Registerok.jsp";
				  RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
				  successView.forward(req, res);	
	          }else{
	        	  errorMsgs.add("驗證碼錯誤");
	          }
	    	   
	    	   
	    	   if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/register/addMemberverify.jsp");
					failureView.forward(req, res);
					return;
				} 
	    	   
	    	   /***************************驗證確認無誤，準備轉交(Send the Success view)***********/
	    	   
	    	   
	       }
	}
	
	//從part得到使用者上傳的InputStream，再透過此自訂方法，轉成byte[]回傳
	public static byte[] getPictureByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  //ByteArrayOutputStream有內建byte陣列透過write()方法寫入資料,toByteArray()方法回傳 
		byte[] buffer = new byte[8192];
		int i;
		while ((i = in.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		in.close();

		return baos.toByteArray();
	}
	
	

	

}
