package chat;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

//import org.json.JSONException;
//import org.json.JSONObject;

import com.google.gson.Gson; // 新增
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyEchoServer/{type}/{host_guest}/{userNo}")
public class MyEchoServer {
	private static Map<String, Set<Session>> pairMap = new HashMap<String, Set<Session>>();
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(@PathParam("type") String type, @PathParam("host_guest") String host_guest, @PathParam("userNo") String userNo, Session userSession) throws IOException {
		// 設定成500KB為了配合Android bundle傳送大小		
//		int maxBufferSize = 500*1024 ;
//		userSession.setMaxTextMessageBufferSize(maxBufferSize);
//		userSession.setMaxBinaryMessageBufferSize(maxBufferSize);
		
		
		
		
		if(type.equals("pair")){
			JsonObject obj;
			if(pairMap.keySet().contains(host_guest)){//專屬房間已建立，也已存在set(已上線的可能是對方或第二個自己)
				Set<Session> sessions=pairMap.get(host_guest);
				sessions.add(userSession);
				boolean bothOn=false;
				for (Session session : sessions) {
					Map<String, String> sessionParameters=session.getPathParameters();
					if (!sessionParameters.get("userNo").equals(userNo)){ //待在此房間的session有和自己不同userNo則代表對方已上線
						bothOn=true;
						break;
					}
				}
				if(bothOn){ //雙方都已上線才推訊息告訴彼此[自己和對方都已上線]
					obj = new JsonObject();
					try {
						obj.addProperty("oppositeStatus", "on");
						obj.addProperty("host_guest", host_guest);
						obj.addProperty("userNo", userNo);					
						for (Session session : sessions) {
							if (session.isOpen())
								session.getAsyncRemote().sendText(obj.toString());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
			}else{//專屬房間未建立，所以也無session存進來(對方未上線)
				Set<Session> sessions=new HashSet<Session>();
				sessions.add(userSession);
				pairMap.put(host_guest, sessions);
			}
//			System.out.println(userNo+ "已連線, 房間位址: "+ host_guest+", websocket session id: "+(userSession.getId()));
		}
		else if(type.equals("announcement")){
			
		}
		
	}

	
	@OnMessage
	public void onMessage(@PathParam("userNo") String userNo, Session userSession, String message) {
		JsonObject obj;					
		try {
			obj = gson.fromJson(message, JsonObject.class);
			String type = obj.get("type").getAsString();
			if(type.equals("pair")){
				Set<Session> sessions=pairMap.get(obj.get("host_guest").getAsString());
				for (Session session : sessions) {
					if (session.isOpen())
						session.getAsyncRemote().sendText(message);
				}
				System.out.println(userNo+"傳訊息到房間: "+obj.get("host_guest").getAsString()+", 訊息內容為: "+message);
			}else if(type.equals("announcement")){
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(@PathParam("type") String type, @PathParam("host_guest") String host_guest, @PathParam("userNo") String userNo, Session userSession, CloseReason reason) {
		if(type.equals("pair")){
			JsonObject obj = new JsonObject();
			Set<Session> sessions=pairMap.get(host_guest);
			sessions.remove(userSession);
			boolean informOffNecessary=true;  //自己離開後，當房間裡無其他個自己時才要通知對方自己已下線
			
			for (Session session : sessions) {
				Map<String, String> sessionParameters=session.getPathParameters();
				if (sessionParameters.get("userNo").equals(userNo)){  //房間裡有其他個自己
					informOffNecessary=false;
					break;
				}
			}
			
			if(informOffNecessary){
				try {
					obj.addProperty("oppositeStatus", "off");
					obj.addProperty("host_guest", host_guest);
					obj.addProperty("userNo", userNo);
					for (Session session : sessions) {
						if (session.isOpen())
							session.getAsyncRemote().sendText(obj.toString()); 
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
//			System.out.println(userNo+ "已離線, 房間位址: "+ host_guest+", websocket session id: "+(userSession.getId()));
//			System.out.println(", 離線原因: " + Integer.toString(reason.getCloseCode().getCode()));
		}
		
		else if(type.equals("announcement")){
			
		}
	}

}
