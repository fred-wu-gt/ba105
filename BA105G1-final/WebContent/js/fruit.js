
$(function(){
	
	var footer=document.getElementById("footer")
	if(footer.offsetTop<400){
		footer.style.top="240px";
		$("html").height("600");
	}
	

	// 設定會從左滑進來的navbar點擊事件
	$('.navbar-toggle').on('click', switchNav)
	function switchNav(){
		$('.leftIn').toggleClass('open');
	}

	// 設定頁面未置頂時會出現top按鈕
	$(document).scroll(function() {
		if($(document).scrollTop()>0){
			$("#fastbtn_div_top").fadeIn(500);
		}else{
			$("#fastbtn_div_top").fadeOut(500);
		}
	});

	// 設定點選top按鈕頁面會動態置頂
	$("#fastbtn_div_top>img").on("click",function(){
		var $body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');
		$body.animate({
			scrollTop: 0
		}, 300);
	});


	$("#memberLoginForm").submit(function(){
		 if($(this).find("#loginMem_id").val().trim()=='' || $(this).find("#loginMem_psw").val().trim()==''){
			 $(this).find("#memberAlertMsg").html("<font style='color:red'>帳號或密碼不可為空!</font>");
		 
		 }else{
			 $.ajax({
				 type: "POST",
				 url: $("#contextPath").val()+"/login/LoginHandler",
				 data: $(this).serialize(),
				 dataType: "json",
				 success: function (data){
					 if(data.result=='success'){//登入成功
						 $("#loginModal").modal('hide');
					 	 $(".loginAndout").attr( "src",$("#contextPath").val()+"/res/image/home/logout.png");
					 	 $("#sayHi").html("Hi! <span style='color:#ac04c1'><b>"+data.mem_name+"</b></span><br>今天吃水果了嗎?");
					 	 $(".loginAndoutH5").html("<b>登出</b>");
					 	 $("#loginAndoutA").attr("href","");
						$("#writingA").attr("href",$("#contextPath").val()+"/front-end/writing/Writing_Home_Page.jsp");
					 	$("#activityA").attr("href",$("#contextPath").val()+"/front-end/activity/activity_mem_home.jsp");
					 	$("#centerA").attr("href",$("#contextPath").val()+"/front-end/member/loginsuss.jsp");
					 	 $("#loginMem_id").val("");
					 	 $("#loginMem_psw").val("");
					 	 $("#memberAlertMsg").html("");
					 	 $("#loginShop_id").val("");
					 	 $("#loginShop_psw").val("");
					 	 $("#shopAlertMsg").html("");
					 	 $("#role").val("member");
					 	 $("#userName").val(data.mem_name);
					 	 $("#userNo").val(data.mem_no);
					 	 $("#registerA").hide();
						 sweetAlert("您好! 買家「"+data.mem_name+"」","");
						 
						 $.ajax({
							 type: "POST",
							 url: $("#contextPath").val()+"/front-end/fav_shop/fav_shop.do",
							 data: "action=findForMemberToJSON&mem_no="+data.mem_no,
							 dataType: "json",
							 success: function (data){
								 $.each(data, function(i, item){
										$('#guestChatDiv').prepend("<div style='margin-top:5px'><img class='iTemOnOffImg' style='height:10px;width:auto;display:inline;margin-right:3px;' src='"+$("#contextPath").val()+"/res/image/home/off.png'><img style='height:40px;width:auto;display:inline;margin-right:5px' src='data:image/jpeg;base64,"+item.shop_photo_bas64+"'><b class='iTemOppositeName'>"+item.shop_name+"</b><img style='width:40px;' class='iTemChatImg' src='"+$("#contextPath").val()+"/res/image/home/chat2.png'><input type='hidden' value='"+item.shop_no+"'><input type='hidden' class='iTemHost_guest' value=''><div class='iTemMsgsArea' style='display:none;height:240px;padding-top:0;overflow-y:auto;word-break: break-all;' class='panel-body'></div><span class='badge iTemUnreadCount'></span><input class='iTemscrollHeight' type='hidden' value=''></div>");
										initializeC(item.shop_no);
								 });
								 initializeC("manager");
								 
									$(".iTemChatImg").click(function(){
										if($(this).attr("src")!=$("#contextPath").val()+"/res/image/home/chat3.png"){
											if($("#host_guest").val()!=""){ //記錄前一個聊天框的捲軸高度
												$(".iTemHost_guest[value='"+$("#host_guest").val()+"']").siblings(".iTemscrollHeight").val($("#messagesArea").scrollTop());
											}
											$(".iTemChatImg[src='"+$("#contextPath").val()+"/res/image/home/chat3.png']").attr("src",$("#contextPath").val()+"/res/image/home/chat2.png");
											$(this).attr("src",$("#contextPath").val()+"/res/image/home/chat3.png");
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
								 
						     },
				             error: function(){}
				         });
						 
					 }else if(data.result=='fail'){//帳密錯誤
						 $("#memberAlertMsg").html("<font style='color:red'>帳號或密碼錯誤!</font>");
					 }else if(data.result=='ban'){
						 $("#shopAlertMsg").html("<font style='color:red'>此帳號已被停權!</font>");
					
					 }
					 
			     },
	             error: function(){sweetAlert("系統忙線中，請重試。","", "error");}
	         })
		 }
		
		return false;
	});
	
	
	$("#shopLoginForm").submit(function(){
		if($(this).find("#loginShop_id").val().trim()=='' || $(this).find("#loginShop_psw").val().trim()==''){
			 $(this).find("#shopAlertMsg").html("<font style='color:red'>帳號或密碼不可為空!</font>");
		 }else{
			 $.ajax({
				 type: "POST",
				 url: $("#contextPath").val()+"/login/LoginHandler",
				 data: $(this).serialize(),
				 dataType: "json",
				 success: function (data){
					 if(data.result=='success'){//登入成功
						 $("#loginModal").modal('hide');
					 	 $(".loginAndout").attr( "src",$("#contextPath").val()+"/res/image/home/logout.png");
					 	 $("#sayHi").html("Hi! <span style='color:#ac04c1'><b>"+data.shop_name+"</b></span><br>今天吃水果了嗎?");
					 	 $(".loginAndoutH5").html("<b>登出</b>");
					 	 $("#loginAndoutA").attr("href","");
						$("#writingA").attr("href",$("#contextPath").val()+"/front-end/writing/writing.do?action=listAllWritingFromShop");
					 	$("#activityA").attr("href",$("#contextPath").val()+"/front-end/activity/activity_shop_home.jsp");
					 	$("#centerA").attr("href",$("#contextPath").val()+"/front-end/shop/shop_default.jsp");
					 	 $("#loginShop_id").val("");
					 	 $("#loginShop_psw").val("");
					 	 $("#shopAlertMsg").html("");
					 	 $("#loginMem_id").val("");
					 	 $("#loginMem_psw").val("");
					 	 $("#memberAlertMsg").html("");
					 	 $("#role").val("shop");
					 	 $("#userName").val(data.shop_name);
					 	 $("#userNo").val(data.shop_no);
					 	 $("#registerA").hide();
					 	 sweetAlert("您好! 商家「"+data.shop_name+"」","");
					 	 
					 	$.ajax({
							 type: "POST",
							 url: $("#contextPath").val()+"/front-end/fav_shop/fav_shop.do",
							 data: "action=findForShopToJSON&shop_no="+data.shop_no,
							 dataType: "json",
							 success: function (data){
								 $.each(data, function(i, item){
										$('#guestChatDiv').prepend("<div style='margin-top:5px'><img class='iTemOnOffImg' style='height:10px;width:auto;display:inline;margin-right:3px;' src='"+$("#contextPath").val()+"/res/image/home/off.png'><img style='height:40px;width:auto;display:inline;margin-right:5px' src='data:image/jpeg;base64,"+item.mem_photo_bas64+"'><b class='iTemOppositeName'>"+item.mem_name+"</b><img style='width:40px;' class='iTemChatImg' src='"+$("#contextPath").val()+"/res/image/home/chat2.png'><input type='hidden' value='"+item.mem_no+"'><input type='hidden' class='iTemHost_guest' value=''><div class='iTemMsgsArea' style='display:none;height:240px;padding-top:0;overflow-y:auto;word-break: break-all;' class='panel-body'></div><span class='badge iTemUnreadCount'></span><input class='iTemscrollHeight' type='hidden' value=''></div>");
										initializeC(item.mem_no);
								 });
								 initializeC("manager");
								 
								$(".iTemChatImg").click(function(){
									if($(this).attr("src")!=$("#contextPath").val()+"/res/image/home/chat3.png"){
										if($("#host_guest").val()!=""){ //記錄前一個聊天框的捲軸高度
											$(".iTemHost_guest[value='"+$("#host_guest").val()+"']").siblings(".iTemscrollHeight").val($("#messagesArea").scrollTop());
										}
										$(".iTemChatImg[src='"+$("#contextPath").val()+"/res/image/home/chat3.png']").attr("src",$("#contextPath").val()+"/res/image/home/chat2.png");
										$(this).attr("src",$("#contextPath").val()+"/res/image/home/chat3.png");
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
								
						     },
				             error: function(){}
				         });
						
					 }else if(data.result=='fail'){
						 $("#shopAlertMsg").html("<font style='color:red'>帳號或密碼錯誤!</font>");
					
					 }else if(data.result=='ban'){
						 $("#shopAlertMsg").html("<font style='color:red'>此帳號已被停權!</font>");
					
					 }else if(data.result=='checking'){
						 $("#shopAlertMsg").html("<font style='color:red'>此帳號正在審核資格中，請耐心等待~</font>");
					
					 }
			     },
	             error: function(){sweetAlert("系統忙線中，請重試。","", "error");}
	         })
		 }
		return false;
	});

	$("#loginAndoutA").click(function(){
		if($(this).attr("href")==''){
			$.ajax({
				 type: "get",
				 url: $("#contextPath").val()+"/login/LoginHandler",
				 data: "action="+$("#role").val()+"Logout",
				 dataType: "json",
				 success: function (data){
					 if(data.result=='success'){//登出成功，回到首頁
					 	 location.replace($("#contextPath").val()+"/front-end/index.jsp");
					 }else{
						 sweetAlert("系統忙線中，請重試~","", "error");
					 }
			     },
	             error: function(){sweetAlert("系統忙線中，請重試。","", "error");}
	         })
		}
		 
	});
	
	//一般會員 商家 管理員能進入的區域
	$("#activityA, #writingA").click(function(){
		if($("#role").val()==''){
			sweetAlert("請先登入","");
			event.preventDefault();
		}
			
	});
	
	//一般會員 商家能進入的區域
	$("#centerA,#storeA").click(function(){
		if($("#role").val()=='' || $("#role").val()=='manager'){
			sweetAlert("請先登入","");
			event.preventDefault();
		}
			
	});

	//一般會員能進入的區域
	$("#fav_shopA").click(function(){
		if($("#role").val()==''){  //身分是訪客時
			sweetAlert("請先登入","");
			event.preventDefault();
		}else if($("#role").val()=='shop' || $("#role").val()=='manager'){  //身分是商家或管理員時
			sweetAlert("這是買家才能使用的功能唷~","");
			event.preventDefault();
		}//身分是一般會員時才不會被擋
	});
	
	
	$("#openShoppingcartBtn").click(function(){
		if($("#role").val()=='shop' || $("#role").val()=='manager'){  //身分是商家或管理員時
			sweetAlert("這是買家或訪客才能使用的功能唷~","");
			event.preventDefault();
		}//身分是一般會員或訪客時才不會被擋
	});
	
	
})
