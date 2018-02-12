package com.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
import com.prom.model.PromService;
import com.prom.model.PromVO;


public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShoppingServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();

		PrintWriter out = res.getWriter();
		
		
		System.out.println("OOOOOOOOOO 進入ShoppingServlet OOOOOOOOOOOO");
		

		Map<String, Map> map1 = new LinkedHashMap<>(); // key:shop_no
														// value:map2

		Map<String, CommodityVO> map2 = new LinkedHashMap<>(); // key:com_no
															// value:CommodityVO
		
		map1 = (Map<String, Map>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		String shop_no = req.getParameter("shop_no");

		
		if ("DELETE".equals(action)) {
			System.out.println("=============== 進入DELETE功能=================");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Map<String,Map> memCart= new LinkedHashMap<>(); //key:mem_id
			//value:map1
			
			try {
				String delShopNO = req.getParameter("delShopNO");
				String delComNO = req.getParameter("delComNO");
				
			

				if (map1 != null) {
					map2 = map1.get(delShopNO);
					if (map2.containsKey(delComNO)) {
						map2.remove(delComNO);
					}
				}
				if(map2.size()==0){
					map1.remove(delShopNO);
				}
				
				
				// 執行成功後，轉交頁面		 
				String url = "/front-end/shopping/Cart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				
				//其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法刪除資料 :" + e.getMessage());
				RequestDispatcher failureView= req.getRequestDispatcher("/front-end/shopping/Cart.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("ADD".equals(action)) {
			System.out.println("=============== 進入ADD功能=================");
			// 取得後來新增的商品
			CommodityVO comVO = getCommodityVO(req);
			String sentURL = req.getParameter("sentURL");
			// 第一次進來
			if (map1 == null) {
				map1 = new LinkedHashMap<>();
				System.out.println("map1 =" + map1);
				map2.put(comVO.getCom_no(), comVO);
				map1.put(shop_no, map2);
				System.out.println("map1 =" + map1);
			} else {
				// 店家有重複
				if (map1.containsKey(comVO.getShop_no())) {
					map2 = map1.get(comVO.getShop_no());
					// 商品有重複，將原數量+1
					if (map2.containsKey(comVO.getCom_no())) {
						CommodityVO innerComVO = map2.get(comVO.getCom_no());
						int oriq = innerComVO.getQuan();
						int thisq = comVO.getQuan();
						innerComVO.setQuan(oriq + thisq);
						System.out.println("原本有" + oriq + "個,此次新增" + thisq + "個");
						// 商品沒重複，把新增的商品加入map2
					} else {

						map2.put(comVO.getCom_no(), comVO);
						System.out.println("商品沒重複,此次新增" + comVO.getQuan() + "個");
					}

				} else {// 店家沒重複，把comVO塞進map2。再將map2塞進map1
					map2.put(comVO.getCom_no(), comVO);
					System.out.println("店家沒重複,此次新增" + comVO.getQuan() + "個");
					map1.put(comVO.getShop_no(), map2);
				}
			}

			System.out.println("map1.keySet().size()= 商家數量" + map1.keySet().size());
			for (String str : map1.keySet()) {
				map2 = map1.get(str);

				for (CommodityVO comVO1 : map2.values()) {
					System.out.println(comVO1.getCom_no() + "的,數量:" + comVO1.getQuan());
				}

			}

			session.setAttribute("shoppingcart", map1);
//			String url = req.getParameter("sentURL");
			String url = "/front-end/shopping/listAllCommodityBS.jsp";
			System.out.println(url);
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}
		if ("CHECKOUT".equals(action)) {
			System.out.println("=============== 進入CHECKOUT功能=================");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String checkOutShopNO = req.getParameter("checkOutShopNO");
//				String login = req.getParameter("login");
				
				//因為不只一個購買數量 所以用 req.getParameterMap()
				//把form的資料用map裝 		 key:com_quan  value: com_quan[]
				Map<String, String[]> map = req.getParameterMap();
				System.out.println("CHECKOUT的map" +  map);
			
				//取出value: com_quan[] & 跑迴圈取出陣列裡的值
				String[] com_quan =map.get("com_quan");
				System.out.println("com_quan:" +  com_quan);
				for(int i=0 ; i<com_quan.length ; i++ ){
					
					System.out.println("com_quan ="+ com_quan[i]);
				}
				System.out.println("從購物車接到的checkOutShopNO = " + checkOutShopNO);
				Integer total = 0;
				
				PromService promSvc= new PromService();
				
				// 取出map1裡的所有店家
//				if(login ==null){
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shopping/Cart.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				if (map1.containsKey(checkOutShopNO)) {
					map2 = map1.get(checkOutShopNO);
					int count=0;
					if (!map2.isEmpty()) {
						Set<String> set2 = map2.keySet();
						Iterator<String> it2 = set2.iterator();
						while (it2.hasNext()) {
							String com_no = it2.next();
							System.out.println("從map2迭代出來的key:" + com_no);
							CommodityVO buyComVO = map2.get(com_no);
							
							//由商品的Shop_no取到  promVO
							PromVO promVO= promSvc.findByPrimaryKey(buyComVO.getShop_no());
							
							//拿到現在時間  比對是否在折價期間內
//							Timestamp start_time = promVO.getProm_start();
//							Timestamp end_time = promVO.getProm_end();
//							Timestamp now_time = new Timestamp( new java.util.Date().getTime());
//							if(now_time.getTime() > start_time.getTime() && end_time.getTime() >now_time.getTime()){
//								// buyCom_price = 價格*折扣   。  四捨五入：Math.round()
//								buyCom_price = (int) (Math.round(buyComVO.getCom_price()*promVO.getProm_dis()));
//							}else{
//							}					
							
							Integer  buyCom_price = 0 ;
							buyCom_price = buyComVO.getCom_price();	
							//把購物車修改的數量，存進buyComVO
							Integer buyQuan = Integer.valueOf(com_quan[count]);
							count++;
							total += buyCom_price * buyQuan;
							System.out.println("price" + buyCom_price + "，數量" + buyQuan + "共" + total);
							buyComVO.setQuan(buyQuan);
							
							
						}
					} else {
						errorMsgs.add("目前購物車內沒有商品");
					}
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("map1", map1);// 將含有錯誤格式的comVo物件存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shopping/Cart.jsp");
					failureView.forward(req, res);
					return;
				}
				// 執行成功後，轉交頁面	
				
				String amount = String.valueOf(total);
				req.setAttribute("amount", amount);
				// session.setAttribute("Checkout", map1);
				String url = "/front-end/shopping/Checkout.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("結帳發生錯誤 :" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shopping/Cart.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("NUMB_AJAX".equals(action)){
			System.out.println("=============== 進入NUMB_AJAX功能=================");
			//取到json字串(jsontext)
			String orderlist = req.getParameter("orderlist");
			System.out.println("orderlist =" + orderlist);
			
				try {
					//將json字串轉成json物件
					JSONObject orderObj = new JSONObject(orderlist);
//					System.out.println("orderObj" +orderObj);
					
					 //因為value是一個JSONarray所以orderObj.getJSONArray("orderlist")取出JSONarray
					JSONArray arr= orderObj.getJSONArray("orderlist");
					System.out.println("arr = " +arr);
					for(int i = 0 ; i<arr.length();i++){
						/*
						 * 重點!! 因為JSONarray裡面存的還是JSON字串
						 * 所以取出後還要轉成JSONobject
						 * 才能用JSONobject的取值方法
						 */
						JSONObject obj = arr.getJSONObject(i);
						String  com_no= obj.getString("com_no");
						String  com_quan= obj.getString("com_quan");
						String  shop_no2= obj.getString("shop_no");
						//取到map2的VO，再把最新的數量set進去
						map2 = map1.get(shop_no2);
						CommodityVO vo = map2.get(com_no);
						vo.setQuan(Integer.parseInt(com_quan));
						
					}
				
					/*
					 * 把JSONobject轉成JSON text
					 * 並傳回ajax的success: function()
					 */
					res.setContentType("text/plain");
					res.setCharacterEncoding("UTF-8");
					out = res.getWriter();
					out.write(orderObj.toString());
					out.flush();
					out.close();
					
					
					
				} catch (Exception e) {
					
					/*
					 * 把錯誤訊息
					 * 傳回ajax的 error: function()
					 */
					e.printStackTrace();
					res.setContentType("text/plain");
					res.setCharacterEncoding("UTF-8");
					out = res.getWriter();
					out.write(e.getMessage());
					out.flush();
					out.close();
				} 

				
		}
		if("BILL".equals(action)){
			System.out.println("=============== 進入BILL功能=================");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			PromService promSvc= new PromService();

			//抓出會員的點數
			MemberVO memVO = (MemberVO) session.getAttribute("loginMemberVO");
			MemberService memSvc = new MemberService();
			memVO= memSvc.getOneMember(memVO.getMem_id());
		
			
//			System.out.println("會員ID先寫固定的做測試，之後記得改動態!!!!!!!");
//			MemberVO memVO= memSVC.getOneMember("MEM8");
			MemberService memSVC= new MemberService();
			Integer val_before = new Integer(memVO.getMem_val());
			System.out.println("會員原本點數 = "+val_before);

			//從checkout.jsp傳來的姓名、地址、電話
			String ord_rec= req.getParameter("ord_rec");
			String ord_adr= req.getParameter("ord_adr");
			String ord_tel= req.getParameter("ord_tel");
			String discount= req.getParameter("discount");
				
			
			//取出由checkout.jsp傳來的商品參數  
			shop_no = req.getParameter("shop_no");
			String  com_no = req.getParameter("com_no");
			Integer com_quan = new Integer(req.getParameter("com_quan"));
			Integer amount = new Integer(req.getParameter("amount"));
			
			System.out.println("本次訂單金額 = "+amount);
			
			
				
			if(val_before>=amount){
				try {
					// 建立訂單主檔
					Ord_masVO ord_masVO = new Ord_masVO();
					ord_masVO.setMem_no(memVO.getMem_no());
					ord_masVO.setShop_no(shop_no);
					java.util.Date now = new java.util.Date();
					java.sql.Timestamp ord_time = new java.sql.Timestamp(now.getTime());
					ord_masVO.setOrd_time(ord_time);
					ord_masVO.setOrd_sta("已付款");
					ord_masVO.setOrd_total(amount);
					ord_masVO.setOrd_rec(ord_rec);
					ord_masVO.setOrd_adr(ord_adr);
					ord_masVO.setOrd_tel(ord_tel);
					ord_masVO.setOrd_can_rea(discount);
					// 建立訂單明細 並將商品庫存減少
					List<Ord_detVO> list = new ArrayList<Ord_detVO>();

					map2 = map1.get(shop_no);
					Set<String> set2 = map2.keySet();
					Iterator<String> it2 = set2.iterator();
					while (it2.hasNext()) {
						com_no = it2.next();
						CommodityVO comVO = map2.get(com_no);
						Ord_detVO ord_detVO = new Ord_detVO();
						ord_detVO.setCom_no(comVO.getCom_no());
						ord_detVO.setOd_quan(comVO.getQuan());
						ord_detVO.setOd_score(0.0);
						list.add(ord_detVO);

						Integer storage_before = comVO.getCom_store();
						System.out.println(com_no + "的原有庫存:" + storage_before);
						Integer storage_buy = comVO.getQuan();
						System.out.println("本次購買數量:" + storage_buy);

						Integer storage_after = storage_before - storage_buy;

						CommodityService comSvc = new CommodityService();
						comSvc.updateComStorage(storage_after, com_no);
						System.out.println(com_no + "的最新庫存量 =" + storage_after);

					}
					// 開始新增資料
					Ord_masService ordmasSvc = new Ord_masService();
					ordmasSvc.insertWithOrd_det(ord_masVO, list);
					/*
					 * 訂單新增成功後... 1.將會員點數扣除
					 */

					// 計算消費後的點數
					Integer val_after = val_before - amount;
					// 把消費後的點數用updateMem_val()寫進DB
					String mem_id = memVO.getMem_id();
					String mem_val = String.valueOf(val_after);
					memSVC.updateMem_val(mem_id, mem_val);
					System.out.println("會員帳號:" + mem_id + "剩餘點數 = " + mem_val);

					//

					map1.remove(shop_no);
					
				} catch (Exception e) {
					errorMsgs.add("結帳發生錯誤 :" + e.getMessage());
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shopping/Checkout.jsp");
					failureView.forward(req, res);
					return;
				}
								
			}else{
				//點數不足的處理
				session.setAttribute("memVO", memVO); 
				System.out.println("點數不足，轉至儲值頁面");
				String url = "/front-end/point/top_up.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);		
				return;
			}
			
			
			
//			結帳成功轉至訂單頁面
		
			String url = "/front-end/ord_mas/listAllByMemNo.jsp";
			System.out.println("===========結帳成功==========");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);		
				
		}
		
		if("BILLajax".equals(action)){
			//抓出會員的點數
			MemberVO memVO = (MemberVO) session.getAttribute("loginMemberVO");
			MemberService memSvc = new MemberService();
			memVO= memSvc.getOneMember(memVO.getMem_id());
		
			Integer val_before = new Integer(memVO.getMem_val());
			System.out.println("BILLajax的會員原本點數 = "+val_before);
			
			Integer amount = new Integer(req.getParameter("amount"));
			
			System.out.println("BILLajax的amount = " + amount);
			
			//-==============ajax處理=====================
			int  a =  0 ;
			if(val_before>amount){
				a = 200 ; 
			}
			if(amount>val_before){
				a = 404 ; 
			}
			System.out.println("ajax區塊的 a = " + a);
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				out = res.getWriter();
				out.print(a);
				out.flush();
				out.close();
				return;
			//-==============ajax處理=====================
			
			
		}
		
		
		
		
		

	}

	//從request取出商品的方法
	private CommodityVO getCommodityVO(HttpServletRequest req) {

		String com_no = req.getParameter("com_no");
		String com_name = req.getParameter("com_name");
		String com_price = req.getParameter("com_price");
		String shop_no = req.getParameter("shop_no");
		String quan = req.getParameter("quan");
		String com_store = req.getParameter("com_store");
		CommodityVO comVO = new CommodityVO();
		comVO.setCom_no(com_no);
		comVO.setCom_name(com_name);
		comVO.setCom_price(new Integer(com_price));
		comVO.setShop_no(shop_no);
		comVO.setQuan(new Integer(quan));
		comVO.setCom_store(new Integer(com_store));
		return comVO;
	}
	
	

}
