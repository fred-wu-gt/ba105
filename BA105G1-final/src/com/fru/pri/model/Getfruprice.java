package com.fru.pri.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Getfruprice {
	
private static final String MY_URL = "http://data.coa.gov.tw/Service/OpenData/FromM/FarmTransData.aspx";
	
	public static void main(String[] args) throws IOException, JSONException {
		URL url = new URL(MY_URL);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
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
		
		//System.out.println(sb);
		br.close();
		isr.close();
		is.close();
		
		
		// ==============  JSON處理   ================ //
		String jsonStr = sb.toString();
//		JSONObject jObj = new JSONObject(jsonStr);
//		JSONObject jObj2 = jObj.getJSONObject("result");
		JSONArray jArray = new JSONArray(jsonStr);
		
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject data = jArray.getJSONObject(i);
			if(data.getString("作物名稱").contains("康")||data.getString("作物名稱").contains("花")||data.getString("作物名稱").contains("休")||data.getString("作物名稱").contains("菊")
			||data.getString("作物名稱").contains("玫")||data.getString("作物名稱").contains("美女")||data.getString("作物名稱").contains("蘭")||data.getString("作物名稱").contains("朵")
			||data.getString("作物名稱").contains("草")||data.getString("作物名稱").contains("芋")||data.getString("作物名稱").contains("星")||data.getString("作物名稱").contains("可愛")
			||data.getString("作物名稱").contains("梗")||data.getString("作物名稱").contains("鬱")||data.getString("作物名稱").contains("白頭")||data.getString("作物名稱").contains("八")
			||data.getString("作物名稱").contains("水")||data.getString("作物名稱").contains("柔")||data.getString("作物名稱").contains("鳥")||data.getString("作物名稱").contains("葉")
			||data.getString("作物名稱").contains("鶴")||data.getString("作物名稱").contains("貓")||data.getString("作物名稱").contains("香")||data.getString("作物名稱").contains("竹")
			||data.getString("作物名稱").contains("千")||data.getString("作物名稱").contains("日")||data.getString("作物名稱").contains("雀")||data.getString("作物名稱").contains("秀")
			||data.getString("作物名稱").contains("松")||data.getString("作物名稱").contains("色")||data.getString("作物名稱").contains("珊")||data.getString("作物名稱").contains("睡")
			||data.getString("作物名稱").contains("櫻")||data.getString("作物名稱").contains("百")||data.getString("作物名稱").contains("薇")||data.getString("作物名稱").contains("唐")
			||data.getString("作物名稱").contains("多")||data.getString("作物名稱").contains("柳")||data.getString("作物名稱").contains("柏")||data.getString("作物名稱").contains("籐")
			||data.getString("作物名稱").contains("菜")||data.getString("作物名稱").contains("米")||data.getString("作物名稱").contains("連")||data.getString("作物名稱").contains("黃")
			||data.getString("作物名稱").contains("甘")||data.getString("作物名稱").contains("羊")||data.getString("作物名稱").contains("豆")||data.getString("作物名稱").contains("冬")
			||data.getString("作物名稱").contains("茄")||data.getString("作物名稱").contains("南")||data.getString("作物名稱").contains("椒")||data.getString("作物名稱").contains("OT")
			||data.getString("作物名稱").contains("梁")||data.getString("作物名稱").contains("蘿")||data.getString("作物名稱").contains("萵")||data.getString("作物名稱").contains("苦")
			||data.getString("作物名稱").contains("胡")||data.getString("作物名稱").contains("扁")||data.getString("作物名稱").contains("芫")||data.getString("作物名稱").contains("蔥")
			||data.getString("作物名稱").contains("苗")||data.getString("作物名稱").contains("棗子")||data.getString("作物名稱").contains("其")||data.getString("作物名稱").contains("茼")
			||data.getString("作物名稱").contains("菇")||data.getString("作物名稱").contains("薑")||data.getString("作物名稱").contains("筍")||data.getString("作物名稱").contains("菱")
			||data.getString("作物名稱").contains("牛")||data.getString("作物名稱").contains("荸")||data.getString("作物名稱").contains("蒜")||data.getString("作物名稱").contains("九")
			||data.getString("作物名稱").contains("馬")||data.getString("作物名稱").contains("耳")||data.getString("作物名稱").contains("薯")||data.getString("作物名稱").contains("漬")
			||data.getString("作物名稱").contains("絲瓜")||data.getString("作物名稱").contains("隼人瓜")||data.getString("作物名稱").contains("乾")||data.getString("作物名稱").contains("錦鳳")
			||data.getString("作物名稱").contains("紅")||data.getString("作物名稱").contains("珠")||data.getString("作物名稱").contains("包")){
				
			}else {
				String FP_NAME = data.getString("作物名稱");
				double FP_PRI = data.getDouble("平均價");
				System.out.println("FP_NAME = " + FP_NAME);		
				System.out.println("FP_PRI = " + FP_PRI);
			}
			
			
		}
		
	}
	
}
