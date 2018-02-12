package com.fruit.controller;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.commodity.model.CommodityService;
import com.commodity.model.CommodityVO;
import com.fruit.model.FruitService;
import com.fruit.model.FruitVO;
import com.oreilly.servlet.Base64Encoder;
import com.prom.model.PromService;
import com.prom.model.PromVO;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;
import com.shop.util.LatLng;

import position.PositionVO;


public class FruitServlet extends HttpServlet {

	
	private final String MY_URL = "http://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=061&$top=100000&$skip=0&$filter=type+like+%E6%B0%B4%E6%9E%9C";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context=getServletContext();
		res.setContentType("text/plain"); 
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		if ("showSelect".equals(action)){
			InputStream is=null;
			InputStreamReader isr=null;
//			try{
//				URL url = new URL(MY_URL);
//				HttpURLConnection con = (HttpURLConnection)url.openConnection();
//				con.setRequestMethod("GET");
//				con.setConnectTimeout(10000);
//				con.setDoInput(true);
//				is = con.getInputStream();
//				isr = new InputStreamReader(is,"utf-8");
//			}catch(java.net.UnknownHostException ue){
				is=context.getResourceAsStream("/fruit.json");
				isr = new InputStreamReader(is,"MS950");
				System.out.println("無法連線，故讀取檔案fruit.json資料");
//			}
			
			BufferedReader br = new BufferedReader(isr);
			
			String str;
			StringBuilder sb = new StringBuilder();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			
			System.out.println(sb);
			br.close();
			isr.close();
			is.close();
			
			
			// ==============  JSON處理   ================ //
			String jsonStr = sb.toString();
			JSONArray sourceArray, outputArray;
			Set<String> set=new HashSet<>();
			try {
				sourceArray = new JSONArray(jsonStr);
				for (int i = 0; i < sourceArray.length(); i++) {
					JSONObject data = sourceArray.getJSONObject(i);
					String crop = data.getString("crop");
					set.add(crop);
				}
				outputArray= new JSONArray();
				for(String fru_name:set){
					outputArray.put(fru_name);
				}
				out.print(outputArray.toString());
				out.flush();
		        out.close();
		        
//		        System.out.println(outputArray.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}

			
			
		}
		
		
		if ("findBySelectFru".equals(action)){
			InputStream is=null;
			InputStreamReader isr=null;
//			try{
//				URL url = new URL(MY_URL);
//				HttpURLConnection con = (HttpURLConnection)url.openConnection();
//				con.setRequestMethod("GET");
//				con.setConnectTimeout(10000);
//				con.setDoInput(true);
//				is = con.getInputStream();
//				isr = new InputStreamReader(is);
//			}catch(java.net.UnknownHostException ue){
				is=context.getResourceAsStream("/fruit.json");
				isr = new InputStreamReader(is,"MS950");
				System.out.println("無法連線，故讀取檔案fruit.json資料");
//			}
			
			BufferedReader br = new BufferedReader(isr);
			
			String str;
			StringBuilder sb = new StringBuilder();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			
			br.close();
			isr.close();
			is.close();
			
			
			// ==============  JSON處理   ================ //
			String jsonStr = sb.toString();
			JSONArray sourceArray;
			JSONArray outputArray= new JSONArray();	
			String selectFru = req.getParameter("selectFru");
			FruitService fruitSvc=new FruitService();
			FruitVO fruitVO=fruitSvc.findByFru_name(selectFru.trim());
			
			try {
				JSONObject imgObj = new JSONObject();
				if(fruitVO!=null){
					imgObj.put("img", fruitVO.getFru_pho_base64());
				}else{
					imgObj.put("img", "");
				}
				outputArray.put(imgObj);
				

				LinkedHashMap<String,LinkedHashSet<String>> mon_posMap = new LinkedHashMap<>();
				sourceArray = new JSONArray(jsonStr);
				for (int i = 0; i < sourceArray.length(); i++) {
					JSONObject data = sourceArray.getJSONObject(i);				
					String crop = data.getString("crop");
					if(selectFru.equals(crop)){
						String county = data.getString("county");
						String town = data.getString("town");
						String month = data.getString("month");
						LinkedHashSet<String> positionSet=null;
						if(mon_posMap.containsKey(month)){//已有此月份和其posSet
							positionSet=mon_posMap.get(month);
							positionSet.add(county+town);
						}else{
							positionSet=new LinkedHashSet<>();
							positionSet.add(county+town);
							mon_posMap.put(month, positionSet);
						}
						
					}
					
				}//已將資料查詢完裝到map裡	
				
				
				Map<String,PositionVO> lat_lngMap =(Map<String,PositionVO>)context.getAttribute("lat_lngMap");
				for(String month:mon_posMap.keySet()){
					LinkedHashSet<String> positionSet=mon_posMap.get(month);
					for(String position:positionSet){
						JSONObject obj = new JSONObject();
						obj.put("position", position);
						obj.put("month", month);
						obj.put("fru_no", fruitVO.getFru_no());
						if(lat_lngMap.containsKey(position)){
							PositionVO positionVO = lat_lngMap.get(position);
							obj.put("lat", positionVO.getLat());
							obj.put("lng", positionVO.getLng());
						}
						else{
							obj.put("lat", LatLng.getlat(position));
							obj.put("lng", LatLng.getlon(position));
						}
						
						outputArray.put(obj);
					}
				}
				
				out.print(outputArray.toString());
				out.flush();
		        out.close();
		        
		        
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		
		
		if ("findBySelectMonth".equals(action)){
			InputStream is=null;
			InputStreamReader isr=null;
//			try{
//				URL url = new URL(MY_URL);
//				HttpURLConnection con = (HttpURLConnection)url.openConnection();
//				con.setRequestMethod("GET");
//				con.setConnectTimeout(10000);
//				con.setDoInput(true);
//				is = con.getInputStream();
//				isr = new InputStreamReader(is);
//			}catch(java.net.UnknownHostException ue){
				is=context.getResourceAsStream("/fruit.json");
				isr = new InputStreamReader(is,"MS950");
				System.out.println("無法連線，故讀取檔案fruit.json資料");
//			}
			
			BufferedReader br = new BufferedReader(isr);
			
			String str;
			StringBuilder sb = new StringBuilder();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			
			br.close();
			isr.close();
			is.close();
			
			
			// ==============  JSON處理   ================ //
//			
			try {

				String jsonStr = sb.toString();
				JSONArray sourceArray;
				JSONArray outputArray= new JSONArray();	
				String selectMonth = req.getParameter("selectMonth");
				LinkedHashMap<String,LinkedHashSet<String>> fru_posMap = new LinkedHashMap<>();
				sourceArray = new JSONArray(jsonStr);
				for (int i = 0; i < sourceArray.length(); i++) {
					JSONObject data = sourceArray.getJSONObject(i);				
					String month = data.getString("month");
					if(selectMonth.equals(month)){
						String county = data.getString("county");
						String town = data.getString("town");
						String crop = data.getString("crop");
						LinkedHashSet<String> positionSet=null;
						if(fru_posMap.containsKey(crop)){//已有此水果和其posSet
							positionSet=fru_posMap.get(crop);
							positionSet.add(county+town);
						}else{
							positionSet=new LinkedHashSet<>();
							positionSet.add(county+town);
							fru_posMap.put(crop, positionSet);
						}
						
					}
					
				}//已將資料查詢完裝到map裡		

				
				FruitService fruitSvc=new FruitService();
				for(String fru_name:fru_posMap.keySet()){
					FruitVO fruitVO=fruitSvc.findByFru_name(fru_name);
					JSONObject imgObj = new JSONObject();
					if(fruitVO!=null){
						imgObj.put("img", fruitVO.getFru_pho_base64());
						imgObj.put("fru_name", fru_name);
					}else{
						imgObj.put("img", "");
					}
					outputArray.put(imgObj);				
				}//已將所有水果圖片都查到並輸出
				

				Map<String,PositionVO> lat_lngMap =(Map<String,PositionVO>)context.getAttribute("lat_lngMap");
				for(String fru_name:fru_posMap.keySet()){
					LinkedHashSet<String> positionSet=fru_posMap.get(fru_name);
					for(String position:positionSet){
						JSONObject obj = new JSONObject();
						FruitVO fruitVO=fruitSvc.findByFru_name(fru_name);
						obj.put("position", position);
						obj.put("fru_name", fru_name);
						obj.put("fru_no", fruitVO.getFru_no());
						if(lat_lngMap.containsKey(position)){
							PositionVO positionVO = lat_lngMap.get(position);
							obj.put("lat", positionVO.getLat());
							obj.put("lng", positionVO.getLng());
						}
						else{
							obj.put("lat", LatLng.getlat(position));
							obj.put("lng", LatLng.getlon(position));
						}
						
						
						outputArray.put(obj);
					}
				}
				
				out.print(outputArray.toString());
				out.flush();
		        out.close();		        
		        
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		
		if ("findCommodity".equals(action)){
			try{
				
				Map<String,PositionVO> lat_lngMap =(Map<String,PositionVO>)context.getAttribute("lat_lngMap");	
				JSONArray outputArray= new JSONArray();	
				Map<String, String[]> map = req.getParameterMap();
				CommodityService comSvc = new CommodityService();
				PromService promSvc = new PromService();
				ShopService shopSvc = new ShopService();
				List<CommodityVO> CommodityVOList = comSvc.getAll(map);
				if(CommodityVOList!=null){
					for(CommodityVO commodityVO:CommodityVOList){
						String shop_no=commodityVO.getShop_no();
						PromVO promVO= promSvc.findByPrimaryKey(shop_no);
						double discount = 1;
						if(promVO!=null){
							Timestamp start_time = promVO.getProm_start();
							Timestamp end_time = promVO.getProm_end();
							Timestamp now_time = new Timestamp( new java.util.Date().getTime());
							if(now_time.getTime() > start_time.getTime() && end_time.getTime() >now_time.getTime()){
								discount = promVO.getProm_dis();
							}
						}
						JSONObject obj = new JSONObject();
						obj.put("com_no", commodityVO.getCom_no());
						obj.put("com_name", commodityVO.getCom_name());
						obj.put("com_store", commodityVO.getCom_store());
						obj.put("com_price", (commodityVO.getCom_price())*discount);
						obj.put("shop_no", shop_no);
						obj.put("discount", discount);
						
						//回傳shop緯經度
						ShopVO shopVO=shopSvc.findByPrimaryKey(shop_no);
						String position=shopVO.getShop_loc().substring(0,6);
						obj.put("shop_name", shopVO.getShop_name());
						obj.put("shop_loc", position);
						if(lat_lngMap.containsKey(position)){
							PositionVO positionVO = lat_lngMap.get(position);
							obj.put("lat", positionVO.getLat());
							obj.put("lng", positionVO.getLng());
						}
						else{
							obj.put("lat", LatLng.getlat(position));
							obj.put("lng", LatLng.getlon(position));
						}
						outputArray.put(obj);
					}
				}else{
					JSONObject obj = new JSONObject();
					obj.put("result", "fail");
					outputArray.put(obj);
				}

					
					
				out.print(outputArray.toString());
				out.flush();
		        out.close();
		        
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

}
