package com.fru.pri.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.commodity.model.CommodityService;
import com.commodity.model.CommodityVO;
import com.fru.pri.model.Fru_priService;
import com.fru.pri.model.Fru_priVO;
import com.fru.pri.model.Getfruprice;

@WebServlet("/Fru_pricontroller")
public class Fru_priServlet extends HttpServlet implements Runnable {
	Thread thread;
	Timer timer;
	int count;
	private static final String MY_URL = "http://data.coa.gov.tw/Service/OpenData/FromM/FarmTransData.aspx";
	private static final long serialVersionUID = 1L;

	public Fru_priServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");//用來比對各種指令
		PrintWriter out = res.getWriter();
		
		if ("listfru_pri".equals(action)) { // 來自listAllCommodityBS.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
					System.out.println("===========複合查詢開始==========");
				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				Map<String, String[]> map = req.getParameterMap();
				System.out.println(map.get("fp_name"));
				

				/*************************** 2.開始複合查詢 ***************************************/
				Fru_priService fru_priSvc = new Fru_priService();
				List<Fru_priVO> list = fru_priSvc.getAll(map);
				System.out.println("list ="+list.size());
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("searchok", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/fru_pri/searchok.jsp"); // 成功轉交listCommodityByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/fru_pri/searchfruprice.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
	}

	public void init() {
		thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
	}

	public void destroy() {
		super.destroy();
		thread = null;
		timer.cancel();
	}

	public void run() {

		timer = new Timer();
		Calendar calendar = new GregorianCalendar(2018, Calendar.JANUARY, 29, 8, 0, 0);
		TimerTask task = new TimerTask() {
			public void run() {
				try {
					URL url = new URL(MY_URL);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					con.setConnectTimeout(10000);
					con.setDoInput(true);

					InputStream is = con.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);

					String str;
					StringBuilder sb = new StringBuilder();
					while ((str = br.readLine()) != null) {
						sb.append(str);
					}

					// System.out.println(sb);
					br.close();
					isr.close();
					is.close();

					// ============== JSON處理 ================ //
					String jsonStr = sb.toString();
					// JSONObject jObj = new JSONObject(jsonStr);
					// JSONObject jObj2 = jObj.getJSONObject("result");
					JSONArray jArray = new JSONArray(jsonStr);

					for (int i = 0; i < jArray.length(); i++) {
						JSONObject data = jArray.getJSONObject(i);
						if (data.getString("作物名稱").contains("康") || data.getString("作物名稱").contains("花")
								|| data.getString("作物名稱").contains("休") || data.getString("作物名稱").contains("菊")
								|| data.getString("作物名稱").contains("玫") || data.getString("作物名稱").contains("美女")
								|| data.getString("作物名稱").contains("蘭") || data.getString("作物名稱").contains("朵")
								|| data.getString("作物名稱").contains("草") || data.getString("作物名稱").contains("芋")
								|| data.getString("作物名稱").contains("星") || data.getString("作物名稱").contains("可愛")
								|| data.getString("作物名稱").contains("梗") || data.getString("作物名稱").contains("鬱")
								|| data.getString("作物名稱").contains("白頭") || data.getString("作物名稱").contains("八")
								|| data.getString("作物名稱").contains("水") || data.getString("作物名稱").contains("柔")
								|| data.getString("作物名稱").contains("鳥") || data.getString("作物名稱").contains("葉")
								|| data.getString("作物名稱").contains("鶴") || data.getString("作物名稱").contains("貓")
								|| data.getString("作物名稱").contains("香") || data.getString("作物名稱").contains("竹")
								|| data.getString("作物名稱").contains("千") || data.getString("作物名稱").contains("日")
								|| data.getString("作物名稱").contains("雀") || data.getString("作物名稱").contains("秀")
								|| data.getString("作物名稱").contains("松") || data.getString("作物名稱").contains("色")
								|| data.getString("作物名稱").contains("珊") || data.getString("作物名稱").contains("睡")
								|| data.getString("作物名稱").contains("櫻") || data.getString("作物名稱").contains("百")
								|| data.getString("作物名稱").contains("薇") || data.getString("作物名稱").contains("唐")
								|| data.getString("作物名稱").contains("多") || data.getString("作物名稱").contains("柳")
								|| data.getString("作物名稱").contains("柏") || data.getString("作物名稱").contains("籐")
								|| data.getString("作物名稱").contains("菜") || data.getString("作物名稱").contains("米")
								|| data.getString("作物名稱").contains("連") || data.getString("作物名稱").contains("黃")
								|| data.getString("作物名稱").contains("甘") || data.getString("作物名稱").contains("羊")
								|| data.getString("作物名稱").contains("豆") || data.getString("作物名稱").contains("冬")
								|| data.getString("作物名稱").contains("茄") || data.getString("作物名稱").contains("南")
								|| data.getString("作物名稱").contains("椒") || data.getString("作物名稱").contains("OT")
								|| data.getString("作物名稱").contains("梁") || data.getString("作物名稱").contains("蘿")
								|| data.getString("作物名稱").contains("萵") || data.getString("作物名稱").contains("苦")
								|| data.getString("作物名稱").contains("胡") || data.getString("作物名稱").contains("扁")
								|| data.getString("作物名稱").contains("芫") || data.getString("作物名稱").contains("蔥")
								|| data.getString("作物名稱").contains("苗") || data.getString("作物名稱").contains("棗子")
								|| data.getString("作物名稱").contains("其") || data.getString("作物名稱").contains("茼")
								|| data.getString("作物名稱").contains("菇") || data.getString("作物名稱").contains("薑")
								|| data.getString("作物名稱").contains("筍") || data.getString("作物名稱").contains("菱")
								|| data.getString("作物名稱").contains("牛") || data.getString("作物名稱").contains("荸")
								|| data.getString("作物名稱").contains("蒜") || data.getString("作物名稱").contains("九")
								|| data.getString("作物名稱").contains("馬") || data.getString("作物名稱").contains("耳")
								|| data.getString("作物名稱").contains("薯") || data.getString("作物名稱").contains("漬")
								|| data.getString("作物名稱").contains("絲瓜") || data.getString("作物名稱").contains("隼人瓜")
								|| data.getString("作物名稱").contains("乾") || data.getString("作物名稱").contains("錦鳳")
								|| data.getString("作物名稱").contains("紅") || data.getString("作物名稱").contains("珠")
								|| data.getString("作物名稱").contains("包")|| data.getString("作物名稱").contains("風")) {

						} else {
							

							String FP_NAME = data.getString("作物名稱");
							double FP_PRI = data.getDouble("平均價");
//							System.out.println("FP_NAME = " + FP_NAME);
//							System.out.println("FP_PRI = " + FP_PRI);
//							System.out.println("---------------------------------");
							String fru_name = FP_NAME;
							
//							if (FP_NAME.contains("(進口)")||FP_NAME.contains("甜柿")||FP_NAME.contains("青木瓜")||FP_NAME.contains("帝王芭")||FP_NAME.contains("柿餅")||FP_NAME.contains("金煌")||FP_NAME.contains("龍眼肉")||FP_NAME.contains("本島")) {	
//								fru_name = FP_NAME.split("-")[0].trim();
//							}else if(FP_NAME.contains("巨峰")){
//								fru_name = "巨峰葡萄";
//							}else if(FP_NAME.contains("白肉")){
//								fru_name = "火龍果白肉";
//							}else if(FP_NAME.contains("白柚")){
//								fru_name = "文旦柚";
//							}else if (FP_NAME.contains("-")){
//								fru_name = FP_NAME.split("-")[1].trim();
//							}else if(FP_NAME.contains("釋迦")){
//								fru_name = "番荔枝";
//							}
//							else{
//								fru_name = FP_NAME.split("-")[0].trim();
//							}
							
//							
//							
							Fru_priService fpSvc = new Fru_priService();
							//String fru_name=FP_NAME;
							Fru_priVO fpVO = fpSvc.getoneFru_name(fru_name);
//							System.out.println(fru_name);
//							System.out.println("水果價格" + FP_PRI);

							
							if (fpVO != null) {
								fpSvc.updateFru_pri(fpVO.getFp_no(), fpVO.getFru_no(), fru_name, FP_PRI);
//								System.out.println("更新成功");
							}

						}

					}
				} catch (Exception se) {
//					System.out.println("更新未完成");
				}
			}
		};
		timer.scheduleAtFixedRate(task, calendar.getTime(), 24 * 60 * 60 * 1000);
	}

}
