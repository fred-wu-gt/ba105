<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



							<div class="panel panel-danger" id="chatRoom" style="display:none;">
								<div class="panel-heading">
									<table class="panel-title">
										<tr>
											<td style="width:240px">
												<b id="chatRoomTitle">聊天室</b></td>
											<td>
												<button id="fav_shopBtn">
													<i class="glyphicon glyphicon-heart"></i>
												</button>
												<button id="closeChatRoomBtn">
													<i class="glyphicon glyphicon-minus"></i>
												</button>
											</td>
										</tr>
									</table>
								</div>
								<div id="messagesArea" style="height:240px;padding-top:0;overflow-y:auto;word-break: break-all;" class="panel-body"  ondragover="dragoverHandler(event)" ondrop="dropHandler(event)"></div>
								<div class="input-group" style="width:100%;">
									<button class="form-control chatAddon" style="width:15%;padding-top:3.5px"><img src="<%=request.getContextPath()%>/res/image/home/smile.png"></button>
									<input  id="message" type="text" class="form-control" style="text-align:left;width:70%;">
									<button type="button" id="sendMessage" class="form-control chatAddon" style="width:15%;"><i class="glyphicon glyphicon-send"></i></button>
								</div>
							</div>
							
						
						<input type="hidden" id="host_guest" value="">	
						

						<div id="chat_favDiv" style="display:none;">
							<!--訪客聊天區塊開始 -->
							<c:if test="${empty loginMemberVO && empty loginShopVO && empty loginManagerVO}">
								<div id="guestChatDiv">
										<div style="margin-top:5px">
											<img class="iTemOnOffImg" style="height:10px;width:auto;display:inline;margin-right:3px;" src="<%=request.getContextPath()%>/res/image/home/off.png">
											<img style="height:40px;width:auto;display:inline;margin-right:5px;border-radius:40px;" src="<%=request.getContextPath()%>/res/image/home/service.png">
											<b class="iTemOppositeName">客服人員</b>
											<img style="width:40px;" class="iTemChatImg" src="<%=request.getContextPath()%>/res/image/home/chat2.png">
											<input type="hidden" value="manager">					
											<input type="hidden" class="iTemHost_guest" value="">	
											<div class="iTemMsgsArea" style="display:none;height:240px;padding-top:0;overflow-y:auto;word-break: break-all;" class="panel-body"></div>
											<span class="badge iTemUnreadCount"></span>
											<input class="iTemscrollHeight" type="hidden" value="">
										</div>
								</div>
							</c:if>
							<!--訪客聊天區塊結束-->
							<!--一般會員聊天區塊開始 -->
							<c:if test="${not empty loginMemberVO}">
								<div id="memberChatDiv">
									<c:forEach var="fav_shopVO" items="${fav_shopSvc.findByMem_no(loginMemberVO.mem_no)}">
									<c:set var="shopVO" scope="page" value="${shopSvc.findByPrimaryKey(fav_shopVO.shop_no)}" />
									
										<div style="margin-top:5px">
											<img class="iTemOnOffImg" style="height:10px;width:auto;display:inline;margin-right:3px;" src="<%=request.getContextPath()%>/res/image/home/off.png">
											<img style="height:40px;width:auto;display:inline;margin-right:5px" src="<%=request.getContextPath()%>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${shopVO.shop_no}">
											<b class="iTemOppositeName">${shopVO.shop_name}</b>
											<img style="width:40px;" class="iTemChatImg" src="<%=request.getContextPath()%>/res/image/home/chat2.png">
											<input type="hidden" value="${shopVO.shop_no}">
											<input type="hidden" class="iTemHost_guest" value="">	
											<div class="iTemMsgsArea" style="display:none;height:240px;padding-top:0;overflow-y:auto;word-break: break-all;" class="panel-body"></div>
											<span class="badge iTemUnreadCount"></span>	
											<input class="iTemscrollHeight" type="hidden" value="">
										</div>
										<script>$(function(){initializeC("${shopVO.shop_no}");});</script>
									</c:forEach>
										<div style="margin-top:5px">
											<img class="iTemOnOffImg" style="height:10px;width:auto;display:inline;margin-right:3px;" src="<%=request.getContextPath()%>/res/image/home/off.png">
											<img style="height:40px;width:auto;display:inline;margin-right:5px;border-radius:40px;" src="<%=request.getContextPath()%>/res/image/home/service.png">
											<b class="iTemOppositeName">客服人員</b>
											<img style="width:40px;" class="iTemChatImg" src="<%=request.getContextPath()%>/res/image/home/chat2.png">
											<input type="hidden" value="manager">			
											<input type="hidden" class="iTemHost_guest" value="">	
											<div class="iTemMsgsArea" style="display:none;height:240px;padding-top:0;overflow-y:auto;word-break: break-all;" class="panel-body"></div>
											<span class="badge iTemUnreadCount"></span>	
											<input class="iTemscrollHeight" type="hidden" value="">
										</div>
										<script>$(function(){initializeC("manager");});</script>
								</div>
							</c:if>
							<!--一般會員聊天區塊結束-->
							<!--商家聊天區塊開始 -->
							<c:if test="${not empty loginShopVO}">
								<div id="shopChatDiv">
									<c:forEach var="fav_shopVO" items="${fav_shopSvc.findByShop_no(loginShopVO.shop_no)}">
									<c:set var="memberVO" scope="page" value="${memberSvc.findByMem_no(fav_shopVO.mem_no)}" />
									
										<div style="margin-top:5px">
											<img class="iTemOnOffImg" style="height:10px;width:auto;display:inline;margin-right:3px;" src="<%=request.getContextPath()%>/res/image/home/off.png">
											<img style="height:40px;width:auto;display:inline;margin-right:5px" src="data:image/jpeg;base64,${memberVO.mem_photo_base64}">
											<b class="iTemOppositeName">${memberVO.mem_name}</b>
											<img style="width:40px;" class="iTemChatImg" src="<%=request.getContextPath()%>/res/image/home/chat2.png">
											<input type="hidden" value="${memberVO.mem_no}">
											<input type="hidden" class="iTemHost_guest" value="">	
											<div class="iTemMsgsArea" style="display:none;height:240px;padding-top:0;overflow-y:auto;word-break: break-all;" class="panel-body"></div>
											<span class="badge iTemUnreadCount"></span>
											<input class="iTemscrollHeight" type="hidden" value="">
										</div>
										<script>$(function(){initializeC("${memberVO.mem_no}");});</script>
									</c:forEach>
										<div style="margin-top:5px">
											<img class="iTemOnOffImg" style="height:10px;width:auto;display:inline;margin-right:3px;" src="<%=request.getContextPath()%>/res/image/home/off.png">
											<img style="height:40px;width:auto;display:inline;margin-right:5px;border-radius:40px;" src="<%=request.getContextPath()%>/res/image/home/service.png">
											<b class="iTemOppositeName">客服人員</b>
											<img style="width:40px;" class="iTemChatImg" src="<%=request.getContextPath()%>/res/image/home/chat2.png">
											<input type="hidden" value="manager">
											<input type="hidden" class="iTemHost_guest" value="">	
											<div class="iTemMsgsArea" style="display:none;height:240px;padding-top:0;overflow-y:auto;word-break: break-all;" class="panel-body"></div>
											<span class="badge iTemUnreadCount"></span>
											<input class="iTemscrollHeight" type="hidden" value="">
										</div>
										<script>$(function(){initializeC("manager");});</script>
								</div>
							</c:if>
							<!--商家聊天區塊結束-->
							<!--管理員聊天區塊開始 -->
							<c:if test="${empty loginMemberVO && empty loginShopVO && not empty loginManagerVO}">
								<div id="managerChatDiv">
									<c:forEach var="memberVO" items="${memberSvc.all}">
										<div style="margin-top:5px">
											<img class="iTemOnOffImg" style="height:10px;width:auto;display:inline;margin-right:3px;" src="<%=request.getContextPath()%>/res/image/home/off.png">
											<img style="height:40px;width:auto;display:inline;margin-right:5px" src="data:image/jpeg;base64,${memberVO.mem_photo_base64}">
											<b class="iTemOppositeName">${memberVO.mem_name}</b>
											<img class="iTemChatImg" src="<%=request.getContextPath()%>/res/image/home/chat2.png">
											<input type="hidden" value="${memberVO.mem_no}">
											<input type="hidden" class="iTemHost_guest" value="">	
											<div class="iTemMsgsArea" style="display:none;height:240px;padding-top:0;overflow-y:auto;word-break: break-all;" class="panel-body"></div>
											<span class="badge iTemUnreadCount"></span>
											<input class="iTemscrollHeight" type="hidden" value="">
										</div>
										<script>$(function(){initializeC("${memberVO.mem_no}");});</script>
									</c:forEach>
									<c:forEach var="shopVO" items="${shopSvc.all}">
									
										<div style="margin-top:5px">
											<img class="iTemOnOffImg" style="height:10px;width:auto;display:inline;margin-right:3px;" src="<%=request.getContextPath()%>/res/image/home/off.png">
											<img style="height:40px;width:auto;display:inline;margin-right:5px" src="<%=request.getContextPath()%>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${shopVO.shop_no}">
											<b class="iTemOppositeName">${shopVO.shop_name}</b>
											<img class="iTemChatImg" src="<%=request.getContextPath()%>/res/image/home/chat2.png">
											<input type="hidden" value="${shopVO.shop_no}">
											<input type="hidden" class="iTemHost_guest" value="">	
											<div class="iTemMsgsArea" style="display:none;height:240px;padding-top:0;overflow-y:auto;word-break: break-all;" class="panel-body"></div>
											<span class="badge iTemUnreadCount"></span>	
											<input class="iTemscrollHeight" type="hidden" value="">
										</div>
										<script>$(function(){initializeC("${shopVO.shop_no}");});</script>
									</c:forEach>
								</div>
							</c:if>
							<!--管理員聊天區塊結束-->
						</div>
						
						<div id="hintOnOff"></div>
<style>
	#hintOnOff{
	  width:300px;
	  height:80px;
	  right:100px;
	  position:fixed; /*固定位置定位*/
	  bottom:340px; /*距離上方 0 像素*/
	  margin:0;
	  z-index:30; /*重疊時會在其他元素之上*/
	  background-image:linear-gradient(to bottom,transparent,white,#FFE8E8,transparent);
	  border-radius:5px;
	  font-weight: bold;
	  font-size: 24px;
	  color: purple;
	  text-align: center;
	  padding: 10px;
	  display:none;
	}
		#chatRoom{
		  width:350px;
		  right:7px;
		  position:fixed; /*固定位置定位*/
		  bottom:0; /*距離上方 0 像素*/
		  margin:0;
		  z-index:30; /*重疊時會在其他元素之上*/
		  border:#e5cfe7 2px solid;
		  border-radius:5px;
		}

		#chatRoom button,#chatRoom .panel-heading{
			background-image: linear-gradient(to bottom,white,#FFE8E8,#FFE8E8,#FFE8E8);
		}
		#chatRoom .panel-heading{
			border-radius:5px;
			border:0;
			border-bottom:#e5cfe7 2px solid;
		}
		#chatRoom button, #chatRoom .form-control{
			border:#e5cfe7 1px solid;
		}
		button:active , .iTemChatImg:active{
		  transform: translateY(4px);
		}
		#chat_favDiv{
		  width:260px;
		  height:270px;
		  right:360px;
		  position:fixed; /*固定位置定位*/
		  bottom:0px; /*距離上方 0 像素*/
		  margin:0;
		  z-index:30; /*重疊時會在其他元素之上*/
		  border:#e5cfe7 1px solid;
		  border-radius:5px;
  		  background-image: linear-gradient(to bottom,white,#FFE8E8,#FFE8E8,#FFE8E8); 
		  display:none;
		  overflow:auto;
		  padding-left:5px;
		  padding-top:5px;
		  font-size:14px;
		}
		
		.badge{
			margin-left:-16px;
			margin-top:-30px;
			background-color:#b0acac;
			z-index:40; 
		}
		.iTemChatImg{
			height:35px;
			width:auto;
			display:inline;
			margin-left:5px;
		} 
		
	    @media screen and (max-width:700px){
			#chat_favDiv{
			  height:200px;
			  bottom:330px;
			  left:5px;
			  right:auto;
			}
			#chatRoom{
			  left:5px;
			  right:auto;
			}
			#hintOnOff{
			  bottom:520px;
			}
     	}
</style>					
<script>
	//點擊聊天icon切換聊天&focus對象
	$(".iTemChatImg").click(function(){
		if($(this).attr("src")!="<%=request.getContextPath()%>/res/image/home/chat3.png"){
			if($("#host_guest").val()!=""){ //記錄前一個聊天框的捲軸高度
				$(".iTemHost_guest[value='"+$("#host_guest").val()+"']").siblings(".iTemscrollHeight").val($("#messagesArea").scrollTop());
			}
			$(".iTemChatImg[src='<%=request.getContextPath()%>/res/image/home/chat3.png']").attr("src","<%=request.getContextPath()%>/res/image/home/chat2.png");
			$(this).attr("src","<%=request.getContextPath()%>/res/image/home/chat3.png");
			$("#host_guest").val($(this).parent().find(".iTemHost_guest").val());
			$("#messagesArea").html($(this).parent().find(".iTemMsgsArea").html());
			$("#chatRoomTitle").html($(this).parent().find(".iTemOppositeName").html());	
        	if($(this).siblings(".iTemUnreadCount").html()!=""){
        		var totalUnreadCount=parseInt($("#totalUnreadCount").html())-parseInt($(this).siblings(".iTemUnreadCount").html());
        		if(totalUnreadCount==0){
        			$("#totalUnreadCount").html("");
        		}else{
        			$("#totalUnreadCount").html(totalUnreadCount);
        		}
        	}
			$(this).siblings(".iTemUnreadCount").html("");
			if($(this).siblings(".iTemscrollHeight").val()!="") //將當前聊天框的捲軸高度調為之前記錄的值
				$("#messagesArea").animate({scrollTop:parseInt($(this).siblings(".iTemscrollHeight").val())}, 0);
		}
	});
	function initializeC(oppositeNo){
		var host_guest=connect(oppositeNo);	
		$("input[value='"+oppositeNo+"']").siblings(".iTemHost_guest").val(host_guest);
	}

	var webSocket;
	// ***呼叫此方法來連線websocket***(登入時已開啟連線到)每個對象
	function connect(oppositeNo) {
		var userNo=$("#userNo").val();
		var role=$("#role").val();
		var host;
		var guest;
		if(role==''){
			return false;
		}
		else if(role=='member'){
			guest=userNo;
			host=oppositeNo;
		}
		else if(role=='shop'){
			host=userNo;
			guest=oppositeNo;
		}else if(role=='manager'){
			if(oppositeNo.substring(0,3)=='MEM'){
				host='manager';
				guest=oppositeNo;
			}else{
				host=oppositeNo;
				guest='manager';
			}
		}
	    var MyPoint = "/MyEchoServer/"+"pair/"+host+"_"+guest+"/"+userNo;
		
	    var Host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		
		
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);

		//連線WebSocket成功時要??
		webSocket.onopen = function(event) {
		};

		//接收訊息時要??
		webSocket.onmessage = function(event) {
	        var jsonObj = JSON.parse(event.data);
	        
	        //攔截官方公告的資訊做處理
	        if(jsonObj.type=='announcement'){
	        	
	        	return false;
	        }
	        

	        //攔截上下線的資訊做處理
	        if(jsonObj.oppositeStatus=='on' || jsonObj.oppositeStatus=='off'){
	        	if(jsonObj.oppositeStatus=='on'){  //對方已上線
	        		console.log(jsonObj.host_guest+"對方已上線");
	        		$(".iTemHost_guest[value='"+jsonObj.host_guest+"']").siblings(".iTemOnOffImg").attr("src","<%=request.getContextPath()%>/res/image/home/on.png");
	        		if(jsonObj.userNo!=$("#userNo").val()){    //發上線訊息的是對方時，才跳出通知框
		        		$("#hintOnOff").html($(".iTemHost_guest[value='"+jsonObj.host_guest+"']").siblings(".iTemOppositeName").html()+" 已上線~~~");
		        		$("#hintOnOff").stop().fadeIn("fast",function(){
			        		$("#hintOnOff").fadeOut(5000);
		        		});
	        		}
	        	}
	        	else if(jsonObj.oppositeStatus=='off'){  //對方已下線(必由對方發出此訊息)
	        		console.log(jsonObj.host_guest+"對方已下線");
	        		$(".iTemHost_guest[value='"+jsonObj.host_guest+"']").siblings(".iTemOnOffImg").attr("src","<%=request.getContextPath()%>/res/image/home/off.png");
	        		$("#hintOnOff").html($(".iTemHost_guest[value='"+jsonObj.host_guest+"']").siblings(".iTemOppositeName").html()+" 已下線~~~");
	        		$("#hintOnOff").fadeIn("fast",function(){
		        		$("#hintOnOff").fadeOut(5000);
	        		});
	        	}
	        	return false;
	        }
	        
	        
	        var message="";
	        var bgi="";
	        
	        //判斷傳訊息者的身分給予訊息背景顏色(商家:綠, 一般會員:藍, 管理員:黃)
	        if(jsonObj.userRole=='shop') 
	        	bgi="background-image:linear-gradient(to bottom,white,#ceffe5,#ceffe5);";
	        else if(jsonObj.userRole=='member')
	        	bgi="background-image:linear-gradient(to bottom,white,#d9fafd,#d9fafd);";
	        else
	        	bgi="background-image:linear-gradient(to bottom,white,#fdf5c8,#fdf5c8);";
	        
	        //圖片訊息內容處理區塊
	        if(jsonObj.img!=null){
		        if(jsonObj.userName==$("#userName").val()){ //判斷傳訊息者是不是自己，是則訊息靠右，不是則訊息靠左[此區塊傳訊息者是自己]
		        	message = "<div style='text-align:right;margin-top:10px;border-radius:15px;padding:15px;margin-left:100px;"+bgi+"'><h6 style='color:gray;margin-bottom:0;'>"+jsonObj.timeStr+"</h6><b>"+jsonObj.userName + "</b> : <img style='max-height:none' src='" + jsonObj.img + "'></div>";
		        }
		        else{ //[此區塊傳訊息者是別人]
		        	message = "<div style='text-align:left;margin-top:10px;border-radius:15px;padding:15px;margin-right:100px;"+bgi+"'><h6 style='color:gray;margin-bottom:0;'>"+jsonObj.timeStr+"</h6><b>"+jsonObj.userName + "</b> : <img style='max-height:none' src='" + jsonObj.img + "'></div>";
		        }
	        }else{//文字訊息內容處理區塊
		        if(jsonObj.userName==$("#userName").val()){ //判斷傳訊息者是不是自己，是則訊息靠右，不是則訊息靠左[此區塊傳訊息者是自己]
		        	if ((jsonObj.userName+jsonObj.message).length<16) //訊息過長則以整塊背景顏色包住，短則條狀
		        		message = "<div style='text-align:right;margin-top:10px;'><h6 style='color:gray;margin-bottom:0;'>"+jsonObj.timeStr+"</h6><span style='padding:0px 10px;border-radius:15px;width:max-content;"+bgi+"'><b>"+jsonObj.userName + "</b> : " + jsonObj.message + "</span></div>";
		        	else
		        		message = "<div style='text-align:right;margin-top:10px;border-radius:15px;padding-right:5px;"+bgi+"'><h6 style='color:gray;margin-bottom:0;'>"+jsonObj.timeStr+"</h6><span style='padding:0px 5px;width:max-content;'><b>"+jsonObj.userName + "</b> : " + jsonObj.message + "</span></div>";
		        }
		        else{ //[此區塊傳訊息者是別人]
		        	if ((jsonObj.userName+jsonObj.message).length<16)
		        		message = "<div style='text-align:left;margin-top:10px;'><h6 style='color:gray;margin-bottom:0;'>"+jsonObj.timeStr+"</h6><span style='padding:0px 10px;border-radius:15px;width:max-content;"+bgi+"'><b>"+jsonObj.userName + "</b> : " + jsonObj.message + "</span></div>";
		        	else
		        		message = "<div style='text-align:left;margin-top:10px;border-radius:15px;padding-left:5px;"+bgi+"'><h6 style='color:gray;margin-bottom:0;'>"+jsonObj.timeStr+"</h6><span style='padding:0px 5px;width:max-content;'><b>"+jsonObj.userName + "</b> : " + jsonObj.message + "</span></div>";
		        }
	        	
	        }        
	        
	        //以下針對訊息內容之外的部分進行操作	
	        var markObj=$(".iTemHost_guest[value='"+jsonObj.host_guest+"']");
	        markObj.next().html(markObj.next().html() + message);
	        if(jsonObj.host_guest==$("#host_guest").val()){  //新傳來訊息的對象是正在聊天的對象，則將對話框捲軸自動往下拉
	        	$("#messagesArea").html(markObj.next().html()).animate({scrollTop:$("#messagesArea").prop('scrollHeight')}, 100);
	        }else{  //新傳來訊息的對象不是正在聊天的對象，則將聊天icon變黃色且累計未讀訊息，且聚焦在該列表高度
	        	var iTemUnreadCount=1;
	        	$("#chat_favDiv").animate({scrollTop:(markObj.parent().index()*0.5*(markObj.parent().height()+5))+5}, 500);
	        	if(markObj.siblings(".iTemUnreadCount").html()!=''){
	        		iTemUnreadCount=parseInt(markObj.siblings(".iTemUnreadCount").html())+1;
	        	}
	        	markObj.siblings(".iTemUnreadCount").html(iTemUnreadCount);
	        	if($("#totalUnreadCount").html()!=''){
	        		$("#totalUnreadCount").html(parseInt($("#totalUnreadCount").html())+1);
	        	}else{
	        		$("#totalUnreadCount").html(1);
	        	}
	        		
	        	markObj.siblings(".iTemChatImg").attr("src","<%=request.getContextPath()%>/res/image/home/chat4.png");
	        }
		}
	
		//關閉WebSocket成功時要??
		webSocket.onclose = function(event) {}
		
		
		return host+"_"+guest;
	}


	$("#sendMessage").click(sendMessage);
	$("#message").keydown(function(){
		if (event.keyCode == 13){
			sendMessage();
		}
	});
	// 呼叫此方法來發出訊息(有輸入訊息且有聊天對象時才可發送成功)
	function sendMessage () {
		if ($("#message").val()==""){
			sweetAlert("請先輸入訊息","");
			return false;
		}
		if ($("#host_guest").val()==""){
			sweetAlert("請先點選聊天對象","");
			return false;
		}
	    if ($("#message").val()!="" && $("#host_guest").val()!=""){
	    	var time=new Date();
	    	var week="日一二三四五六";
	        var timeStr=(time.getMonth()+1)+"/"+time.getDate()+"("+week.charAt(time.getDay())+") "+addZero(time.getHours())+":"+addZero(time.getMinutes())+":"+addZero(time.getSeconds());
	    	var jsonObj = {"host_guest" : $("#host_guest").val(), "userName" : $("#userName").val(),"userRole" : $("#role").val(), "message" : $("#message").val(), "timeStr" : timeStr, "type" : "pair", "oppositeStatus" : "chat"};
	        webSocket.send(JSON.stringify(jsonObj));
	        $("#message").val("");
	    }
	}
	
	// 呼叫此方法來關閉websocket
	function disconnect () {
		webSocket.close();
	}
	
    function addZero(number){
    	if(number<10)
    		return "0"+number;
    	else
    		return number;
    }
	
//     聊天室的打開與縮小
	$("#closeChatRoomBtn").click(function(){
		$("#chatRoom").hide("fast");
		$("#chat_favDiv").fadeOut("fast");
	});
	$("#openChatRoomBtn").click(function(){
		if($("#role").val()==''){
			sweetAlert("請先登入","");
		}else{
			if($("#chatRoom").css("display")=='none'){
				$("#chatRoom").show("fast");
				$("#chat_favDiv").fadeIn("fast");
			}else{
				$("#chatRoom").hide("fast");
				$("#chat_favDiv").fadeOut("fast");
			}
		}
	});
	$("#fav_shopBtn").click(function(){
		$("#chat_favDiv").fadeToggle("fast");
	});
	
	
	
	
	
	
//*******聊天室拖曳上傳圖片區快開始(已在web.xml註冊傳輸檔案大小上限)*******
    function dragoverHandler(evt) { //在上面拖曳著不放
        evt.preventDefault();
    }
    function dropHandler(evt) { //放開拖曳(evt為 DragEvent 物件)
        evt.preventDefault();   //不寫這句會跳轉至show圖片的頁面
		if ($("#host_guest").val()==""){
			sweetAlert("請先點選聊天對象","");
		}
		else{ //有聊天對象時才處理圖片
	        var files = evt.dataTransfer.files; //由DataTransfer物件的files屬性取得檔案物件們
	        for (var i in files) { //可能一次拖曳多張圖片上傳，所以跑迴圈
	        	var type = files[i].type;
	            if (type == 'image/jpeg' || type == 'image/png' || type == 'image/gif') {
	                var fr = new FileReader();
	                fr.onload = sendImg;
	                fr.readAsDataURL(files[i]);
	            }
	        }
        }
    }
    function sendImg(evt) {
        var img = evt.target.result; //base64的字串
    	var time=new Date();
    	var week="日一二三四五六";
        var timeStr=(time.getMonth()+1)+"/"+time.getDate()+"("+week.charAt(time.getDay())+") "+addZero(time.getHours())+":"+addZero(time.getMinutes())+":"+addZero(time.getSeconds());
    	var jsonObj = {"host_guest" : $("#host_guest").val(), "userName" : $("#userName").val(),"userRole" : $("#role").val(), "img" : img, "timeStr" : timeStr, "type" : "pair", "oppositeStatus" : "chat"};
        webSocket.send(JSON.stringify(jsonObj));
    }  
//*******聊天室拖曳上傳圖片區快結束*******



</script>
					