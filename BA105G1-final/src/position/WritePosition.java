package position;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shop.util.LatLng;

public class WritePosition {//2次測都輸出119個地點

	public static void main(String[] args) {
		Map<String,String> lat_lngMap=new HashMap<>(); 
		String MY_URL = "http://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=061&$top=100000&$skip=0&$filter=type+like+%E6%B0%B4%E6%9E%9C";

		URL url;
		try {
			url = new URL(MY_URL);
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
			
			
			br.close();
			isr.close();
			is.close();
			
			// ==============  JSON處理   ================ //
			String jsonStr = sb.toString();
			JSONArray sourceArray, outputArray;
			Set<String> set=new HashSet<>();
			
			sourceArray = new JSONArray(jsonStr);
			for (int i = 0; i < sourceArray.length(); i++) {
				JSONObject data = sourceArray.getJSONObject(i);
				String county = data.getString("county");
				String town = data.getString("town");
				lat_lngMap.put(county+town, "");
			
			}
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File file = new File("fruitPosition2.ser");
		Set<PositionVO> positionVOList=new HashSet<>();
		int count=0;
		Iterator<String> iterator=lat_lngMap.keySet().iterator();
		while(iterator.hasNext()){
			PositionVO positionVO=new PositionVO();
			String countyTown=iterator.next();
			double lat=LatLng.getlat(countyTown);
			double lng=LatLng.getlon(countyTown);
			System.out.println(++count+":"+countyTown+lat+"_"+lng);
			positionVO.setPosition(countyTown);
			positionVO.setLat(lat);
			positionVO.setLng(lng);
			positionVOList.add(positionVO);
			
		}
		System.out.println("共輸出"+positionVOList.size()+"個地點");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (PositionVO positionVO : positionVOList)
				oos.writeObject(positionVO);
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		

	}

}
