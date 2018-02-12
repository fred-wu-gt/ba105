<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ~~在此請taglib JSTL標籤庫, page import Java類別等等~~ --%>

<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>

<head>
 	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAM-JnQqjuQ4UEYgFrtfR6lCw-kHM17H8I"></script>
	<script>

	    function showMap() {  //一開始把空地圖show出來	
			var mapOptions = {
				center:new google.maps.LatLng(23.61, 120.88),
				zoom:8,
				mapTypeId:google.maps.MapTypeId.ROADMAP
			}
			var map_canvas = document.getElementById("map_canvas");
			var map = new google.maps.Map(map_canvas, mapOptions);
		}
		$(function(){
			showMap();
			$.ajax({  //一開始把下拉式選單查詢出來(from農委會)	
				 type: "POST",
				 url: "<%=request.getContextPath()%>/front-end/fruit/fruit.do",
				 data: "action=showSelect",
				 dataType: "json",
				 success: function (data){
					 $.each(data, function(i, item){
							$('#selectFru').append("<option value='"+item+"'>"+item+"</option>");
					 });
			     },
	             error: function(){}
	         });
			
			
			$("#selectFru").change(function(){  //選取水果時將盛產月和盛產地查詢出來(from農委會)	
				if($(this).val()!=""){
					$('#commodityDiv').empty();
					$("#tabTitle").remove();
					$("#imgDiv").empty();
					$("#tabHead").empty();
					$("#tabContent").empty();
					$("#tabDiv").hide();
					$("#selectMonth>option").first().prop("selected","true");
					$.ajax({
						 type: "POST",
						 url: "<%=request.getContextPath()%>/front-end/fruit/fruit.do",
						 data: "action=findBySelectFru&selectFru="+$("#selectFru").val(),
						 dataType: "json",
						 timeout:20000,
						 success: function (data){
							var mapOptions = {
								center:new google.maps.LatLng(23.61, 120.88),
								zoom:8,
								mapTypeId:google.maps.MapTypeId.ROADMAP
							}
							var map_canvas = document.getElementById("map_canvas");
							var map = new google.maps.Map(map_canvas, mapOptions);
							
					        var image = {
					        		  url: "<%=request.getContextPath()%>/res/image/home/logo2_transparent.png",
					        		  origin: new google.maps.Point(0, 0),
					        		  anchor: new google.maps.Point(30, 30),
					        		  scaledSize: new google.maps.Size(60, 60)
					        		};
							var lastMonth="";
					        var geocoder = new google.maps.Geocoder();
					        $("#tabDiv").show();
							 $.each(data, function(i, item){
								 if(item.img!=null){
									 if(item.img!=""){
										 image = {
								        		  url: "data:image/jpeg;base64,"+item.img,
								        		  origin: new google.maps.Point(0, 0),
								        		  anchor: new google.maps.Point(30, 25),
								        		  scaledSize: new google.maps.Size(60, 45)
								        		};
										 $("#imgDiv").append("<img style='width:180px;margin-bottom:20px;' src='data:image/jpeg;base64,"+item.img+"'>");
									 }
								 }else{
									 if($("#"+item.month).size() == 0){ //無該月份id，代表第一次建該月份的tab
										 if(lastMonth==""){  //第一個tab，要加class:acive
											 $("#tabHead").before("<span id='tabTitle' style='color:blue'>盛產月</span>");
											 $("#tabHead").append("<li role='presentation' class='active'><a href='#"+item.month+"' aria-controls='tab1' role='tab' data-toggle='tab'>"+item.month+"月</a></li>");
											 $("#tabContent").append("<div role='tabpanel' class='tab-pane active input-group' id='"+item.month+"'><span class='showAddon form-control'>盛產地</span><span class='form-control'>"+item.position+"</span></div>");
										 
											//出現推薦商品開始~~~
											$.ajax({
												 type: "POST",
												 url: $("#contextPath").val()+"/front-end/fruit/fruit.do",
												 data: "action=findCommodity&fru_no="+item.fru_no,
												 dataType: "json",
												 success: function (data){
													 var firstC=true;
													 $.each(data, function(i, item){
														 if(data.result!="fail" && firstC==true){
															 $('#commodityDiv').append("<div style='margin-left:10px;margin-bottom:5px;color:deeppink'><b>❤ 您可能會喜歡:</b></div>");
															 firstC=false;
														 }
														 if(item.discount!=1) //有打折(出現紅色特價圖示)
															$('#commodityDiv').append("<form id='"+item.com_no+"Form' action='<%=request.getContextPath() %>/front-end/shopping/shopping.do' method='POST'><div class='col-xs-12 col-sm-4'><div class='thumbnail home_product_item'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'><img src='<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no="+item.com_no+"&i=1'></a><table class='home_product_table'><tr><td colspan='2' class='pro_name'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'>"+item.com_name+"</a></td></tr><tr><td>$ <span class='priceSpan'>"+Math.round(item.com_price)+"</span><img src='<%=request.getContextPath()%>/res/image/home/onSale.png' class='home_onSale_img'><img src='<%=request.getContextPath()%>/res/image/home/shoppingcart_plus.png' class='home_product_img' align='right'></td></tr></table></div></div><input type='hidden' name='com_no' value='"+item.com_no+"'><input type='hidden' name='com_name' value='"+item.com_name+"'><input type='hidden' name='com_price' value='"+Math.round(item.com_price)+"'><input type='hidden' name='shop_no' value='"+item.shop_no+"'><input type='hidden' name='com_store' value='"+item.com_store+"'><input type='hidden' name='sentURL' value='<%=request.getServletPath() %>'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' name='quan' value='1'><input type='hidden' class='lat' value='"+item.lat+"'><input type='hidden' class='lng' value='"+item.lng+"'><input type='hidden' class='shop_name' value='"+item.shop_name+"'><input type='hidden' class='shop_loc' value='"+item.shop_loc+"'></form>");
														 else //沒打折
															 $('#commodityDiv').append("<form id='"+item.com_no+"Form' action='<%=request.getContextPath() %>/front-end/shopping/shopping.do' method='POST'><div class='col-xs-12 col-sm-4'><div class='thumbnail home_product_item'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'><img src='<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no="+item.com_no+"&i=1'></a><table class='home_product_table'><tr><td colspan='2' class='pro_name'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'>"+item.com_name+"</a></td></tr><tr><td>$ <span class='priceSpan'>"+Math.round(item.com_price)+"</span><img src='<%=request.getContextPath()%>/res/image/home/shoppingcart_plus.png' class='home_product_img' align='right'></td></tr></table></div></div><input type='hidden' name='com_no' value='"+item.com_no+"'><input type='hidden' name='com_name' value='"+item.com_name+"'><input type='hidden' name='com_price' value='"+Math.round(item.com_price)+"'><input type='hidden' name='shop_no' value='"+item.shop_no+"'><input type='hidden' name='com_store' value='"+item.com_store+"'><input type='hidden' name='sentURL' value='<%=request.getServletPath() %>'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' name='quan' value='1'><input type='hidden' class='lat' value='"+item.lat+"'><input type='hidden' class='lng' value='"+item.lng+"'><input type='hidden' class='shop_name' value='"+item.shop_name+"'><input type='hidden' class='shop_loc' value='"+item.shop_loc+"'></form>");
														 
														 $("#"+item.com_no+"Form .home_product_img").on('click', function(){
																swal('成功加入購物車!', '再繼續逛逛吧', 'success').then(function() {
																	  setTimeout(function(){
																	  	$("#"+item.com_no+"Form").submit();
																	  }, 200);
																  });
															})
													 });
													 	//hover商品來定位商家~~~~~開始
													 	var shop_marker;
														$('form').hover(function(){
															var shop_img = {
													        		  url: "<%=request.getContextPath()%>/res/image/home/shop_marker.png",
													        		  scaledSize: new google.maps.Size(100, 160)
													        		};
																shop_marker = new google.maps.Marker({
																position:new google.maps.LatLng($(this).find(".lat").val(), $(this).find(".lng").val()),
																map:map, 
																title:"商家: "+$(this).find(".shop_name").val()+" ("+$(this).find(".shop_loc").val()+")",
																icon: shop_img});
																shop_marker.setAnimation(google.maps.Animation.BOUNCE);
																var infowindow = new google.maps.InfoWindow({
																    content:"商家  :  "+$(this).find(".shop_name").val()+" ("+$(this).find(".shop_loc").val()+")   "
																  });
																  infowindow.open(shop_marker.get('map'), shop_marker);
															
														},function(){
															shop_marker.setMap(null);
														});
														//hover商品來定位商家~~~~~結束
													 
											     },
									             error: function(){}
									         });
									
											//出現推薦商品結束~~~
										 
										 }
										 else{
											 $("#tabHead").append("<li role='presentation'><a href='#"+item.month+"' aria-controls='tab1' role='tab' data-toggle='tab'>"+item.month+"月</a></li>");
											 $("#tabContent").append("<div role='tabpanel' class='tab-pane input-group' id='"+item.month+"'><span class='showAddon form-control'>盛產地</span><span class='form-control'>"+item.position+"</span></div>");
										 }
									 }
									 else{ 
										 $("#"+item.month).append("<span class='form-control'>"+item.position+"</span>");
									 }
									var marker = new google.maps.Marker({
									position:new google.maps.LatLng(item.lat, item.lng),
									map:map, 
							        draggable: true,
									title:"ヽ（・∀・）ノ"+item.month+"月 ♡ "+item.position,
									icon: image});
									marker.addListener('click', 
											function() { //使停止
										var infowindow = new google.maps.InfoWindow({
										    content:item.month+"月 ♡ "+item.position
										  });
										  if (marker.getAnimation() !== null) {
										    marker.setAnimation(null);
										  } else { //使彈跳
										    marker.setAnimation(google.maps.Animation.BOUNCE);
										    infowindow.open(marker.get('map'), marker);
										  }
									});
									lastMonth=item.month;
								 }
							 });
					     },
			             error: function(){}
			         });
				}
			});
			
			
			$("#selectMonth").change(function(){  //選取月份時將水果和盛產地查詢出來(from農委會)	
				if($(this).val()!=""){
					$('#commodityDiv').empty();
					$("#tabTitle").remove();
					$("#imgDiv").empty();
					$("#tabHead").empty();
					$("#tabContent").empty();
					$("#tabDiv").hide();
					$("#selectFru>option").first().prop("selected","true");
					$.ajax({
						 type: "POST",
						 url: "<%=request.getContextPath()%>/front-end/fruit/fruit.do",
						 data: "action=findBySelectMonth&selectMonth="+$("#selectMonth").val(),
						 dataType: "json",
						 timeout:20000,
						 success: function (data){
							var mapOptions = {
								center:new google.maps.LatLng(23.61, 120.88),
								zoom:8,
								mapTypeId:google.maps.MapTypeId.ROADMAP
							}
							var map_canvas = document.getElementById("map_canvas");
							var map = new google.maps.Map(map_canvas, mapOptions);
							
					        var image = {
					        		  url: "<%=request.getContextPath()%>/res/image/home/logo2_transparent.png",
					        		  origin: new google.maps.Point(0, 0),
					        		  anchor: new google.maps.Point(30, 30),
					        		  scaledSize: new google.maps.Size(60, 60)
					        		};
							var lastFru="";
							var j=0;
							var fru_nameArray=[];
							var imgArray=[];
							fru_nameArray.push("default");
							imgArray.push(image);
					        var geocoder = new google.maps.Geocoder();
					        $("#tabDiv").show();
							 $.each(data, function(i, item){
								 if(item.img!=null){
									 if(item.img!=""){
										 var img = {
								        		  url: "data:image/jpeg;base64,"+item.img,
								        		  origin: new google.maps.Point(0, 0),
								        		  anchor: new google.maps.Point(30, 25),
								        		  scaledSize: new google.maps.Size(60, 45)
								        		};
										fru_nameArray.push(item.fru_name);
										imgArray.push(img);
										$("#imgDiv").append("<img style='display:none;width:180px;margin-bottom:20px;' id='"+item.fru_name+"img' src='data:image/jpeg;base64,"+item.img+"'>");
									 }
								 }else{
									 if($("#"+item.fru_name).size() == 0){ //無該水果id，代表第一次建該水果的tab
										 if(lastFru==""){  //第一個tab，要加class:acive
											 $("#tabHead").before("<span id='tabTitle' style='color:blue'>盛產水果</span>");
											 $("#tabHead").append("<li role='presentation' class='active'><a href='#"+item.fru_name+"' aria-controls='tab1' role='tab' data-toggle='tab'>"+item.fru_name+"</a></li>");
											 $("#tabContent").append("<div role='tabpanel' class='tab-pane active input-group' id='"+item.fru_name+"'><span class='showAddon form-control'>盛產地</span><span class='form-control'>"+item.position+"</span></div>");
											 $("#"+item.fru_name+"img").show();
											 
											//出現推薦商品開始~~~
											 $("a[href='#"+item.fru_name+"']").click(function(){
													$("#imgDiv img").hide();
													$("#"+$(this).html()+"img").show();
													$('#commodityDiv').append(item.fru_name);
													$('#commodityDiv').empty();
													$.ajax({
														 type: "POST",
														 url: $("#contextPath").val()+"/front-end/fruit/fruit.do",
														 data: "action=findCommodity&fru_no="+item.fru_no,
														 dataType: "json",
														 success: function (data){
															 var firstC=true;
															 $.each(data, function(i, item){
																 if(data.result!="fail" && firstC==true){
																	 $('#commodityDiv').append("<div style='margin-left:10px;margin-bottom:5px;color:deeppink'><b>❤ 您可能會喜歡:</b></div>");
																	 firstC=false;
																 }
																 if(item.discount!=1) //有打折(出現紅色特價圖示)
																	$('#commodityDiv').append("<form id='"+item.com_no+"Form' action='<%=request.getContextPath() %>/front-end/shopping/shopping.do' method='POST'><div class='col-xs-12 col-sm-4'><div class='thumbnail home_product_item'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'><img src='<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no="+item.com_no+"&i=1'></a><table class='home_product_table'><tr><td colspan='2' class='pro_name'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'>"+item.com_name+"</a></td></tr><tr><td>$ <span class='priceSpan'>"+Math.round(item.com_price)+"</span><img src='<%=request.getContextPath()%>/res/image/home/onSale.png' class='home_onSale_img'><img src='<%=request.getContextPath()%>/res/image/home/shoppingcart_plus.png' class='home_product_img' align='right'></td></tr></table></div></div><input type='hidden' name='com_no' value='"+item.com_no+"'><input type='hidden' name='com_name' value='"+item.com_name+"'><input type='hidden' name='com_price' value='"+Math.round(item.com_price)+"'><input type='hidden' name='shop_no' value='"+item.shop_no+"'><input type='hidden' name='com_store' value='"+item.com_store+"'><input type='hidden' name='sentURL' value='<%=request.getServletPath() %>'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' class='lat' value='"+item.lat+"'><input type='hidden' class='lng' value='"+item.lng+"'><input type='hidden' class='shop_name' value='"+item.shop_name+"'><input type='hidden' class='shop_loc' value='"+item.shop_loc+"'></form>");
																 else //沒打折
																	 $('#commodityDiv').append("<form id='"+item.com_no+"Form' action='<%=request.getContextPath() %>/front-end/shopping/shopping.do' method='POST'><div class='col-xs-12 col-sm-4'><div class='thumbnail home_product_item'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'><img src='<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no="+item.com_no+"&i=1'></a><table class='home_product_table'><tr><td colspan='2' class='pro_name'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'>"+item.com_name+"</a></td></tr><tr><td>$ <span class='priceSpan'>"+Math.round(item.com_price)+"</span><img src='<%=request.getContextPath()%>/res/image/home/shoppingcart_plus.png' class='home_product_img' align='right'></td></tr></table></div></div><input type='hidden' name='com_no' value='"+item.com_no+"'><input type='hidden' name='com_name' value='"+item.com_name+"'><input type='hidden' name='com_price' value='"+Math.round(item.com_price)+"'><input type='hidden' name='shop_no' value='"+item.shop_no+"'><input type='hidden' name='com_store' value='"+item.com_store+"'><input type='hidden' name='sentURL' value='<%=request.getServletPath() %>'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' class='lat' value='"+item.lat+"'><input type='hidden' class='lng' value='"+item.lng+"'><input type='hidden' class='shop_name' value='"+item.shop_name+"'><input type='hidden' class='shop_loc' value='"+item.shop_loc+"'></form>");
																 
																 $("#"+item.com_no+"Form .home_product_img").on('click', function(){
																		swal('成功加入購物車!', '再繼續逛逛吧', 'success').then(function() {
																			  setTimeout(function(){
																			  	$("#"+item.com_no+"Form").submit();
																			  }, 200);
																		  });
																	})
															 });
															 	//hover商品來定位商家~~~~~開始
															 	var shop_marker;
																$('form').hover(function(){
																	var shop_img = {
															        		  url: "<%=request.getContextPath()%>/res/image/home/shop_marker.png",
															        		  scaledSize: new google.maps.Size(100, 160)
															        		};
																		shop_marker = new google.maps.Marker({
																		position:new google.maps.LatLng($(this).find(".lat").val(), $(this).find(".lng").val()),
																		map:map, 
																		title:"商家: "+$(this).find(".shop_name").val()+" ("+$(this).find(".shop_loc").val()+")",
																		icon: shop_img});
																		shop_marker.setAnimation(google.maps.Animation.BOUNCE);
																		var infowindow = new google.maps.InfoWindow({
																		    content:"商家  :  "+$(this).find(".shop_name").val()+" ("+$(this).find(".shop_loc").val()+")   "
																		  });
																		  infowindow.open(shop_marker.get('map'), shop_marker);
																	
																},function(){
																	shop_marker.setMap(null);
																});
																//hover商品來定位商家~~~~~結束
															 
													     },
											             error: function(){}
											         });
											});
											//出現推薦商品結束~~~
											 $("a[href='#"+item.fru_name+"']").click();
										 }
										 else{ //第二個以後的tab，不加class:acive
											 $("#tabHead").append("<li role='presentation'><a href='#"+item.fru_name+"' aria-controls='tab1' role='tab' data-toggle='tab'>"+item.fru_name+"</a></li>");
											 $("#tabContent").append("<div role='tabpanel' class='tab-pane input-group' id='"+item.fru_name+"'><span class='showAddon form-control'>盛產地</span><span class='form-control'>"+item.position+"</span></div>");
											 
											 //出現推薦商品開始~~~
											 $("a[href='#"+item.fru_name+"']").click(function(){
													$("#imgDiv img").hide();
													$("#"+$(this).html()+"img").show();
													$('#commodityDiv').append(item.fru_name);
													$('#commodityDiv').empty();
													$.ajax({
														 type: "POST",
														 url: $("#contextPath").val()+"/front-end/fruit/fruit.do",
														 data: "action=findCommodity&fru_no="+item.fru_no,
														 dataType: "json",
														 success: function (data){
															 var firstC=true;
															 $.each(data, function(i, item){
																 if(data.result!="fail" && firstC==true){
																	 $('#commodityDiv').append("<div style='margin-left:10px;margin-bottom:5px;color:deeppink'><b>❤ 您可能會喜歡:</b></div>");
																	 firstC=false;
																 }
																 if(item.discount!=1) //有打折(出現紅色特價圖示)
																	$('#commodityDiv').append("<form id='"+item.com_no+"Form' action='<%=request.getContextPath() %>/front-end/shopping/shopping.do' method='POST'><div class='col-xs-12 col-sm-4'><div class='thumbnail home_product_item'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'><img src='<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no="+item.com_no+"&i=1'></a><table class='home_product_table'><tr><td colspan='2' class='pro_name'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'>"+item.com_name+"</a></td></tr><tr><td>$ <span class='priceSpan'>"+Math.round(item.com_price)+"</span><img src='<%=request.getContextPath()%>/res/image/home/onSale.png' class='home_onSale_img'><img src='<%=request.getContextPath()%>/res/image/home/shoppingcart_plus.png' class='home_product_img' align='right'></td></tr></table></div></div><input type='hidden' name='com_no' value='"+item.com_no+"'><input type='hidden' name='com_name' value='"+item.com_name+"'><input type='hidden' name='com_price' value='"+Math.round(item.com_price)+"'><input type='hidden' name='shop_no' value='"+item.shop_no+"'><input type='hidden' name='com_store' value='"+item.com_store+"'><input type='hidden' name='sentURL' value='<%=request.getServletPath() %>'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' class='lat' value='"+item.lat+"'><input type='hidden' class='lng' value='"+item.lng+"'><input type='hidden' class='shop_name' value='"+item.shop_name+"'><input type='hidden' class='shop_loc' value='"+item.shop_loc+"'></form>");
																 else //沒打折
																	 $('#commodityDiv').append("<form id='"+item.com_no+"Form' action='<%=request.getContextPath() %>/front-end/shopping/shopping.do' method='POST'><div class='col-xs-12 col-sm-4'><div class='thumbnail home_product_item'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'><img src='<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no="+item.com_no+"&i=1'></a><table class='home_product_table'><tr><td colspan='2' class='pro_name'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no="+item.com_no+"'>"+item.com_name+"</a></td></tr><tr><td>$ <span class='priceSpan'>"+Math.round(item.com_price)+"</span><img src='<%=request.getContextPath()%>/res/image/home/shoppingcart_plus.png' class='home_product_img' align='right'></td></tr></table></div></div><input type='hidden' name='com_no' value='"+item.com_no+"'><input type='hidden' name='com_name' value='"+item.com_name+"'><input type='hidden' name='com_price' value='"+Math.round(item.com_price)+"'><input type='hidden' name='shop_no' value='"+item.shop_no+"'><input type='hidden' name='com_store' value='"+item.com_store+"'><input type='hidden' name='sentURL' value='<%=request.getServletPath() %>'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value='1'><input type='hidden' class='lat' value='"+item.lat+"'><input type='hidden' class='lng' value='"+item.lng+"'><input type='hidden' class='shop_name' value='"+item.shop_name+"'><input type='hidden' class='shop_loc' value='"+item.shop_loc+"'></form>");
																 
																 $("#"+item.com_no+"Form .home_product_img").on('click', function(){
																		swal('成功加入購物車!', '再繼續逛逛吧', 'success').then(function() {
																			  setTimeout(function(){
																			  	$("#"+item.com_no+"Form").submit();
																			  }, 200);
																		  });
																	})
															 });
															 	//hover商品來定位商家~~~~~開始
															 	var shop_marker;
																$('form').hover(function(){
																	var shop_img = {
															        		  url: "<%=request.getContextPath()%>/res/image/home/shop_marker.png",
															        		  scaledSize: new google.maps.Size(100, 160)
															        		};
																		shop_marker = new google.maps.Marker({
																		position:new google.maps.LatLng($(this).find(".lat").val(), $(this).find(".lng").val()),
																		map:map, 
																		title:"商家: "+$(this).find(".shop_name").val()+" ("+$(this).find(".shop_loc").val()+")",
																		icon: shop_img});
																		shop_marker.setAnimation(google.maps.Animation.BOUNCE);
																		var infowindow = new google.maps.InfoWindow({
																		    content:"商家  :  "+$(this).find(".shop_name").val()+" ("+$(this).find(".shop_loc").val()+")   "
																		  });
																		  infowindow.open(shop_marker.get('map'), shop_marker);
																	
																},function(){
																	shop_marker.setMap(null);
																});
																//hover商品來定位商家~~~~~結束
													     },
											             error: function(){}
											         });
											});
											//出現推薦商品結束~~~
										 
										 }
									 }
									 else{  //已有該水果id，代表已有該水果的tab，所以只要增加position item
										 $("#"+item.fru_name).append("<span class='form-control'>"+item.position+"</span>");
									 }
									var index= fru_nameArray.indexOf(item.fru_name);
									var img;
									if(index==-1){
										img=imgArray[0];
									}else{
										img=imgArray[index];
									}
									var marker = new google.maps.Marker({
									position:new google.maps.LatLng(item.lat, item.lng),
									map:map, 
							        draggable: true,
									title:"ヽ（・∀・）ノ"+item.fru_name+" ♡ "+item.position,
									icon: img});
									marker.addListener('click', 
											function() { //使停止
												var infowindow = new google.maps.InfoWindow({
												    content:item.fru_name+" ♡ "+item.position
												  });
												  if (marker.getAnimation() !== null) {
												    marker.setAnimation(null);
												  } else { //使彈跳
												    marker.setAnimation(google.maps.Animation.BOUNCE);
												    infowindow.open(marker.get('map'), marker);
												  }
									});
									lastFru=item.fru_name;
								 }
							 });
					     },
			             error: function(){}
			         });
				}
			});
			
			
			
			



			
			
			
			
		});

			
</script>
<style type="text/css">
#contentDiv div,#contentDiv span{
	font-size:18px;
}
#contentDiv select{
	font-size:18px;
	padding:3px 6px;
}
.selectAddon{
	background-image: radial-gradient(ellipse,white,#FFE8E8);
}

#tabDiv li{
	float:none;
}
#tabDiv a{
	padding:0px;
}
#tabDiv{
	text-align:center;
	display:none;
}

.showAddon{
	background-image: radial-gradient(ellipse,white,#f0ffe0,#f0ffe0);
	padding: 2px 12px;
    font-size: 18px;
    font-weight: 400;
    color: #555;
    text-align: center;
    border: 1px solid #ccc;
    border-radius: 4px;
}
#tabDiv .form-control{
	padding: 2px 12px;
}
#tabDiv ul{
	border:0;
}
#tabDiv .nav-tabs>li.active>a,#tabDiv .nav-tabs>li.active>a:focus,#tabDiv .nav-tabs>li.active>a:hover{
	background-image: radial-gradient(ellipse,white,#fffde8,#d8ea0c);
    border: 0.5px dotted brown;
    border-radius: 4px;
}
#tabDiv .nav>li>a:focus,#tabDiv .nav>li>a:hover {
    background-color: #fdfff3;
}
			.home_product_item{
				background-color: rgba(255,255,255,0.4);
				border: 1px rgba(255,255,255,0.4) solid;
				margin-bottom:15px;
				margin-left: -7.5px;
				margin-right: -7.5px;
			}
			

			.home_product_item:hover{
				background-color: rgba(255,255,255,0.9);
				border: 1px white outset;
			}
			.home_product_table{
	            width:100%;
	            position: relative;
	            bottom: 0px;
	            overflow: hidden;
	            text-overflow: ellipsis;
	            word-break: break-all;
      		}
      		.home_product_table .pro_name{
				height: 100px;
      		}
      		.home_onSale_img{
      		 	width: 20%;
      		 	margin-left:3px;
      		 	display: inline;
      		 	margin-top: -10px;
			}
      		.home_product_img{
      		 	width: 20%;
      		 	margin-right:0;
      		 	display: inline;
      		 	margin-bottom: 2.5px;
      		}
      		.home_product_img:hover,.home_product_img:active{
      		 	background-image: linear-gradient(to top, white , #7FFFD4);
      		 	border:1px white outset;
      		 	width: 21%;
      		 	margin-bottom: 0px;
      		}
      		.home_product_img:active{
			  transform: translateY(4px);
			}
      		.home_product_table span{
      		 	color:#B22222;
      		 	font-size: 30px;
      		}
      		
      		#map_canvas{
      			width:100%;
      			height:690px;
      			margin-left:auto;
      			margin-right:auto;
      		}
      	@media screen and (max-width:992px){
			#contentDiv span, #tabDiv div{
				font-size:16px;
			}
			#contentDiv select{
				font-size:16px;
			}
		}
</style>    
</head>
<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>

<div class="container" id="contentDiv">
	<div class="row">
		<div class="col-xs-12 col-sm-6" style="text-align:center">
			<span>ヽ（・∀・）ノ點擊地圖上的<b style="color:purple">水果標記</b>可查看相關資訊唷!</span>
			<div id="map_canvas"></div>
			<h6>資料來源:農委會資料開放平台</h6>
		</div>
		<br>
		<div class="col-xs-5 col-sm-2">

			<div class="input-group" style="margin-bottom:10px;">
				<span class="input-group-addon selectAddon">水果</span>
				<select id="selectFru" name="selectFru" class="form-control">
					<option value=""></option>
				</select>
			</div>
			<div class="input-group" style="margin-bottom:10px;">
				<span class="input-group-addon selectAddon">盛產月</span>
				<select id="selectMonth" name="selectMonth" class="form-control">
					<option value=""></option>
					<option value="1">1月</option>
					<option value="2">2月</option>
					<option value="3">3月</option>
					<option value="4">4月</option>
					<option value="5">5月</option>
					<option value="6">6月</option>
					<option value="7">7月</option>
					<option value="8">8月</option>
					<option value="9">9月</option>
					<option value="10">10月</option>
					<option value="11">11月</option>
					<option value="12">12月</option>
				</select>
			</div>
			<div id="imgDiv">
			</div>
		</div>
		
		
		<div class="col-xs-7 col-sm-3">
			<div role="tabpanel" id="tabDiv">
			    <div class="col-xs-3 col-sm-5">
			    	<div class="row">
					    <ul class="nav nav-tabs" role="tablist" id="tabHead">
					    </ul>
					</div>
				</div>
			    <div class="col-xs-7 col-sm-7">
			    	<div class="row">
					    <div class="tab-content" id="tabContent" style="background-color:white">
					     </div>   
				    </div>
			    </div>
			</div>
		</div>
		<div id="commodityDiv"  class="col-xs-12 col-sm-6">
		</div>
		
		
	</div>
</div>













<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>
<%-- ~~body結束:無需寫body或html標籤~~ --%>
					