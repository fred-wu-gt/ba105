package com.shop.util;

import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.text.DecimalFormat;
import java.text.Format;
import java.io.*;

import com.shop.model.ShopJDBCDAOImpl;
import com.shop.model.ShopVO;

public class LatLng {
	private static double shop_lat ; //緯度
	private static double shop_lon ; //經度
	
	public static double getlat(String shop_loc){//緯度
		List<Double> list2 = new ArrayList<>();
		double lat_avg = 0 ;
		URL url = null;

		try {
			url = new URL("http://maps.googleapis.com/maps/api/geocode/xml?address="+java.net.URLEncoder.encode(shop_loc,"UTF-8")+"&sensor=false&language=zh-TW"); // 建立URL物件url , 以 中文台北市(之地址換算經緯度為例)
		} catch (MalformedURLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			// 以URL物件建立URLConnection物件
			URLConnection urlConn = url.openConnection();
			// 以URLConnection物件取得輸入資料流
			InputStream ins = urlConn.getInputStream();

			// 建立URL資料流
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String data;
			while ((data = br.readLine()) != null) {
				if (data.contains("<lat>")) {
					if(!(data.substring(data.indexOf("<lat>") + 5, data.indexOf("</lat>")).contains("�"))){
						list2.add(new Double(data.substring(data.indexOf("<lat>") + 5, data.indexOf("</lat>"))));
					}
				}
			}
			
			double lat_sum = 0 ;
			for(int j = 0 ; j < list2.size() ; j++){
				lat_sum +=  list2.get(j);
			}
			Format dfm1 = new DecimalFormat("###.0000");
			lat_avg = new Double(dfm1.format(lat_sum/list2.size()));
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return lat_avg;
	}
	
	
	public static double getlon(String shop_loc){//經度
		List<Double> list3 = new ArrayList<Double>();
		double lng_avg = 0 ;
		URL url = null;

		try {
			url = new URL("http://maps.googleapis.com/maps/api/geocode/xml?address="+java.net.URLEncoder.encode(shop_loc,"UTF-8")+"&sensor=false&language=zh-TW"); // 建立URL物件url , 以 中文台北市(之地址換算經緯度為例)
		} catch (MalformedURLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			// 以URL物件建立URLConnection物件
			URLConnection urlConn = url.openConnection();
			// 以URLConnection物件取得輸入資料流
			InputStream ins = urlConn.getInputStream();

			// 建立URL資料流
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String data;
			while ((data = br.readLine()) != null) {
				if (data.contains("<lng>")) {
					if(!(data.substring(data.indexOf("<lng>") + 5, data.indexOf("</lng>")).contains("�"))){
						list3.add(new Double(data.substring(data.indexOf("<lng>") + 5, data.indexOf("</lng>"))));
					}
												
				}
			}
			
			Format dfm1 = new DecimalFormat("###.0000");		
			double lng_sum = 0 ;
			for(int k = 0 ; k < list3.size() ; k++){
				lng_sum +=  list3.get(k);
			}
			lng_avg = new Double(dfm1.format(lng_sum/list3.size()));
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return lng_avg;
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		
		ShopJDBCDAOImpl dao = new ShopJDBCDAOImpl() ;
		List<ShopVO> list = dao.getAll();
		ShopVO shopVO ;
		for(int i = 0 ; i < list.size() ; i++){
			shopVO = list.get(i);
			double shop_lat = getlat(shopVO.getShop_loc()) ;
			double shop_lon = getlon(shopVO.getShop_loc()) ;
			System.out.println(i+":"+shop_lat);
			System.out.println(i+":"+shop_lon);
			dao.update_lat_lon(shop_lat, shop_lon, shopVO.getShop_no());
			
			
			
			
//			List<String> list9 = new ArrayList<>();
//			list9.add("苗栗縣卓蘭鎮");
//			list9.add("嘉義縣竹崎鄉");
//			list9.add("嘉義縣梅山鄉");
//			list9.add("嘉義縣番路鄉");
//			list9.add("雲林縣古坑鄉");
//			list9.add("苗栗縣卓蘭鎮");
//			list9.add("嘉義縣竹崎鄉");
//			list9.add("嘉義縣梅山鄉");
//			list9.add("嘉義縣番路鄉");
//			list9.add("雲林縣古坑鄉");
//			list9.add("苗栗縣卓蘭鎮");
//			list9.add("嘉義縣竹崎鄉");
//			list9.add("嘉義縣梅山鄉");
//			list9.add("嘉義縣番路鄉");
//			list9.add("雲林縣古坑鄉");
//			list9.add("苗栗縣卓蘭鎮");
//			list9.add("嘉義縣竹崎鄉");
//			list9.add("嘉義縣梅山鄉");
//			list9.add("嘉義縣番路鄉");
//			list9.add("雲林縣古坑鄉");
//			for(int j = 0 ; j < list9.size() ; j++){
//				double shop_lat2 = getlat(list9.get(j)) ;
//				double shop_lon2 = getlon(list9.get(j)) ;
//				System.out.println(j+":"+shop_lat2);
//				System.out.println(j+":"+shop_lon2);
//		}
		
		}
		
		
	}


}


