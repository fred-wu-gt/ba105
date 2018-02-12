

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddressSelect extends HttpServlet {

	Map<String,Set<String>> map1=new LinkedHashMap<>(); //縣市,[鄉鎮區]
	Map<String,Set<String>> map2=new LinkedHashMap<>(); //鄉鎮區,[路]
	Map<String,Set<String>> map3=new LinkedHashMap<>(); //路,[5碼郵遞區號]
	public void init() throws ServletException {

		ServletContext context=getServletContext();
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader isr = new InputStreamReader(context.getResourceAsStream("/address.json"));
			BufferedReader br = new BufferedReader(isr);
			String str;
			while ((str = br.readLine()) != null)
				sb.append(str);
			br.close();
			isr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String jsonStr = sb.toString();
		try {
			JSONArray jArray = new JSONArray(jsonStr);
			
			for (int i = 1; i < jArray.length(); i++) {//i=0為欄位說明
				Set<String> areaSet1=new LinkedHashSet<>();
				Set<String> roadSet2=new LinkedHashSet<>();
				Set<String> zip_scopeSet3=new LinkedHashSet<>();
				JSONObject data = jArray.getJSONObject(i);

				String zip5 = data.getString("Zip5");//郵遞區號
				String zip = zip5.substring(0,3);
				String city = data.getString("City");//縣市
				String area_zip = data.getString("Area")+"_"+zip;//鄉鎮區
				String road_zip = data.getString("Road")+"_"+zip;//路
				String scope = data.getString("Scope");//郵遞區號範圍說明
				
				if(map1.containsKey(city)){//此縣市已在map1的key裡
					areaSet1=map1.get(city);//得到此縣市包含的鄉鎮區set1
					if(map2.containsKey(area_zip)){//此鄉鎮區已在set1和map2的key裡
						roadSet2=map2.get(area_zip);//得到鄉鎮區包含的路set2
						if(map3.containsKey(road_zip)){//此路已在set2和map3的key裡
							zip_scopeSet3=map3.get(road_zip);//得到此路包含的郵遞區號
							zip_scopeSet3.add(zip5+"_"+scope);
						}else{//此路不在set2和map3的key裡
							zip_scopeSet3.add(zip5+"_"+scope);
							map3.put(road_zip, zip_scopeSet3);
							roadSet2.add(road_zip);
						}
					}else{//此鄉鎮區不在set1和map2的key裡
						zip_scopeSet3.add(zip5+"_"+scope);
						map3.put(road_zip, zip_scopeSet3);
						roadSet2.add(road_zip);
						map2.put(area_zip, roadSet2);
						areaSet1.add(area_zip);
					}
				}else{//此縣市不在map1的key裡
					zip_scopeSet3.add(zip5+"_"+scope);
					map3.put(road_zip, zip_scopeSet3);
					roadSet2.add(road_zip);
					map2.put(area_zip, roadSet2);
					areaSet1.add(area_zip);
					map1.put(city, areaSet1);
				}
			}
			context.setAttribute("addressMap1",map1);
			
//			System.out.println(map1.keySet());//for confirming
//			System.out.println(map2.keySet());//for confirming
//			System.out.println(map3.get("國光路_402"));//for confirming
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8"); 
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        PrintWriter out = res.getWriter();
        StringBuffer sb=new StringBuffer();
        
        String city=req.getParameter("city");
        String area=req.getParameter("area");
        String road=req.getParameter("road");
        if(city!=null){
        	Set<String> areaSet1=map1.get(city);
        	for(String dis:areaSet1){
        		sb.append(","+dis);
        	}
        	out.print(sb.toString());
        	return;
        }
        if(area!=null){
        	Set<String> roadSet2=map2.get(area);
        	for(String roa:roadSet2){
        		sb.append(","+roa);
        	}
        	out.print(sb.toString());
        	return;
        }
        if(road!=null){
        	Set<String> zip_scopeSet3=map3.get(road);
        	if(zip_scopeSet3!=null){
        		for(String zip:zip_scopeSet3){
            		sb.append(","+zip);
            	}
            	out.print(sb.toString());
        	}
        	else
        		out.print("");
        	return;
        }
        out.flush();
        out.close();
        
	}

}
