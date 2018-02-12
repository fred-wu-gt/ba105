package position;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

import com.shop.util.LatLng;

public class ConstructPosition extends HttpServlet {
	public static Map<String,PositionVO> lat_lngMap = new HashMap<>(); 
	public void init() throws ServletException{

		ServletContext context=getServletContext();
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		int count=0;
		try {
			fis = new FileInputStream(context.getRealPath("/fruitPosition.ser"));
			ois = new ObjectInputStream(fis);
			
			while (true) {
				PositionVO positionVO = (PositionVO) ois.readObject();
				lat_lngMap.put(positionVO.getPosition(), positionVO);
//				System.out.print("[地點: "+positionVO.getPosition());
//				System.out.print(", 緯度: "+positionVO.getLat());
//				System.out.print(", 經度: "+positionVO.getLng());
//				System.out.print("]");
				count++;
			}
			
			
		} catch (EOFException e) {
			System.out.print("fruitPosition.ser資料讀取完畢！");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		context.setAttribute("lat_lngMap",lat_lngMap);
		System.out.println("共"+count+"個地點，已放至ServletContext");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
