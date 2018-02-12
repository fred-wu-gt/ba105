<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.prom.model.*"%>
<%@ page import="com.ad.model.*"%>
<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<jsp:useBean id="fruitSvc" class="com.fruit.model.FruitService"/>
<jsp:useBean id="commoditySvc" class="com.commodity.model.CommodityService"/>

<jsp:useBean id="promSvc" class="com.prom.model.PromService"/>
<head>

	<style type="text/css">
			#home_navbar{
				margin-bottom: 0;
			}
			#home_carousel-id{
				margin-bottom: 20px;
			}

			.home_writing_panel>.panel-heading{
				background-color: #D8F09A;
				color: #652825;
			}


			.home_writing_panel{
	            background-color: rgba(255,255,255,0.4);
			}
			.home_writing_item{
			    border-bottom: 1px #d6e9c6 solid;
			    overflow: hidden;
	            text-overflow: ellipsis;
	            word-break: break-all;
	            padding-top: 5px;
	            padding-bottom: 5px;
	            padding-left: 0;
	            padding-right: 5px;
			}
			.home_writing_item:hover{
				background-color:white;
			}
			.home_writing_item img{
			    margin-right: -15px;
			}
			.home_writing_item h6{
			    margin:0;
			}


			.home_activity_panel>.panel-heading{
				background-color: rgba(255,158,13,0.5);
				color: #652825;
			}
			.home_activity_panel{
	            background-color: rgba(255,255,255,0.4);
	            border-color: rgba(255,158,13,0.5);
			}
			.home_activity_item{
			    border-bottom: 1px rgba(255,158,13,0.5) solid;
			    overflow: hidden;
	            text-overflow: ellipsis;
	            word-break: break-all;
	            padding-top: 5px;
	            padding-bottom: 5px;
	            padding-left: 0;
	            padding-right: 5px;
			}
			.home_activity_item:hover{
				background-color:white;
			}
			.home_activity_item img{
			    margin-right: -15px;
			}
			.home_activity_item h6{
			    margin:0;
			}

			#home_new_icon>h4{
				width: 220px;
				margin-right: 0px;
				margin-left: 20px;
				padding-bottom:0;
				margin-top: -20px;
				padding-top:0;
				margin-bottom: -10px;

			}
			.home_product_nav-tabs{
				border-bottom: 3px white solid;
				margin-bottom: 10px;
			}
			.home_product_nav-tabs>li>a:hover {
			    background-image: linear-gradient(to top, white , #7FFFD4);
			}
			.home_product_nav-tabs>li.active>a, .home_product_nav-tabs>li.active>a:focus, .home_product_nav-tabs>li.active>a:hover {
			    background-color: rgba(255,255,255,0.7);
			    border: 1px white inset;
			    color: #652825;
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
				height: 80px;
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
      		@media screen and (max-width:992px){
				.home_product_table .pro_name{
					height: 130px;
	      		}
	      		.home_product_nav-tabs>li>a{
					padding: 3px;
				}
      		}
      		#carousel_circle{
      			z-index:8;
      		}
      		
      		
      		

		</style>
	
	<script>
		$(document).ready(function(){
			$(".carousel").carousel({
			interval:4000,
			pause:false
			});
		});
	</script>
</head>

<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>

<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>
<div id="home_carousel-id" class="carousel slide" data-ride="carousel">
				    <!-- 幻燈片小圓點區 -->
				    <ol class="carousel-indicators" id="carousel_circle">
				    </ol>
				    <!-- 幻燈片主圖區 -->
				    <div class="carousel-inner">
				    
				   <% java.util.Date now=new java.util.Date(); 
				   AdService adSvc=new AdService();
				   List<AdVO> adVOList=adSvc.findByAd_stat("審核通過");
				   int i=0;
				   for(AdVO adVO:adVOList){
					    Timestamp ad_start=adVO.getAd_start();          
						Timestamp ad_end=adVO.getAd_end();  
					   if(ad_start.getTime()<=now.getTime() && ad_end.getTime()>=now.getTime()){
						   %> 
						<div class="item <%if(i==0){ %><%="active" %><%}%>">
				            <img src="<%=request.getContextPath()%>/front-end/DBPicReader/DBPicReader.do?action=ad&id_no=<%=adVO.getAd_no()%>">
				        </div>
						<script>
							$("#carousel_circle").append("<li data-target='#home_carousel-id' data-slide-to='<%=i%>' class='<%if(i==0){ %><%= "active" %><%}%>'></li>");
						</script>
				        <%i++;
					   }
				   }
				   
				   %>  
				    </div>
				    <!-- 上下頁控制區 -->
				    <a class="left carousel-control" href="#home_carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
				    <a class="right carousel-control" href="#home_carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
				</div>


				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-md-push-4 col-md-8" style=""><!-- 產品區開始 -->
					
			            		           
							<div role="tabpanel">
							    <!-- 標籤面板：標籤區 -->
							   		<% Set<String> fru_noSet=new LinkedHashSet<String>();
							   		List<CommodityVO> commodityVOList =commoditySvc.getAll();
							   		for(CommodityVO commodityVO:commodityVOList){
							   			if(fru_noSet.size()<6)
							   				fru_noSet.add(commodityVO.getFru_no());
							   		}
							   		pageContext.setAttribute("fru_noSet", fru_noSet);
							   		%>
							    <ul class="nav nav-tabs home_product_nav-tabs" role="tablist">
							    	<c:forEach var="fru_no" varStatus="s" items="${fru_noSet}">
								        <c:if test="${s.index==0}"><li role="presentation" class="active" ></c:if>
								        <c:if test="${s.index!=0}"><li role="presentation"></c:if>
								            <a href="#${fru_no}" aria-controls="tab1" role="tab" data-toggle="tab">
								            <h4>
								            	<b>${fruitSvc.getOneFruit(fru_no).fru_name}</b>
								            </h4>
								            </a>
								        </li>
									</c:forEach>
									<li role="presentation">
										<img src="<%=request.getContextPath()%>/res/image/home/new.png" style="width:200px;margin-top:-15px;margin-bottom:-5px">
							
									</li>
							    </ul>
							    <!-- 標籤面板：內容區 -->
							    <div class="tab-content" id="tabContentDiv">

							        
							    </div>
							    <c:forEach var="commodityVO" varStatus="s" items="${commoditySvc.all}">
							    	<c:if test="${fru_noSet.contains(commodityVO.fru_no)}">
<%
CommodityVO commodityVO =(CommodityVO)pageContext.getAttribute("commodityVO");
PromVO promVO= promSvc.findByPrimaryKey(commodityVO.getShop_no());
double discount = 1;
if(promVO!=null){
	Timestamp start_time = promVO.getProm_start();
	Timestamp end_time = promVO.getProm_end();
	Timestamp now_time = new Timestamp( new java.util.Date().getTime());
	if(now_time.getTime() > start_time.getTime() && end_time.getTime() >now_time.getTime()){
		discount = promVO.getProm_dis();
	}
}
pageContext.setAttribute("discount", discount);
%>
							    	
							    	
								    	<script>
								    		if($("#${commodityVO.fru_no}").size() == 0){ //尚無此tab
								    			if(${s.index==0}){//第一個tab要class active
								    				$("#tabContentDiv").append("<div role='tabpanel' class='tab-pane active' id='${commodityVO.fru_no}'><form id='${commodityVO.com_no}Form' action='<%=request.getContextPath() %>/front-end/shopping/shopping.do' method='POST'><div class='col-xs-12 col-sm-4'><div class='thumbnail home_product_item'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${commodityVO.com_no}'><img src='<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${commodityVO.com_no}&i=1'></a><table class='home_product_table'><tr><td colspan='2' class='pro_name'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${commodityVO.com_no}'>${commodityVO.com_name}</a></td></tr><tr><td>$ <span class='priceSpan'><fmt:formatNumber var='com_pri' type='number' value='${discount*commodityVO.com_price}' pattern='#,#00'/>${com_pri}</span><c:if test='${discount!=1}'><img src='<%=request.getContextPath()%>/res/image/home/onSale.png' class='home_onSale_img'></c:if><img src='<%=request.getContextPath()%>/res/image/home/shoppingcart_plus.png' class='home_product_img' align='right'></td></tr></table></div></div><input type='hidden' name='com_no' value='${commodityVO.com_no}'><input type='hidden' name='com_name' value='${commodityVO.com_name}'><input type='hidden' name='com_price' value='${com_pri}'><input type='hidden' name='shop_no' value='${commodityVO.shop_no }'><input type='hidden' name='com_store' value='${commodityVO.com_store }'><input type='hidden' name='sentURL' value='<%=request.getServletPath() %>'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value=1></form></div>");
								    				
								    			}else{
								    				$("#tabContentDiv").append("<div role='tabpanel' class='tab-pane' id='${commodityVO.fru_no}'><form id='${commodityVO.com_no}Form' action='<%=request.getContextPath() %>/front-end/shopping/shopping.do' method='POST'><div class='col-xs-12 col-sm-4'><div class='thumbnail home_product_item'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${commodityVO.com_no}'><img src='<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${commodityVO.com_no}&i=1'></a><table class='home_product_table'><tr><td colspan='2' class='pro_name'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${commodityVO.com_no}'>${commodityVO.com_name}</a></td></tr><tr><td>$ <span class='priceSpan'><fmt:formatNumber var='com_pri' type='number' value='${discount*commodityVO.com_price}' pattern='#,#00'/>${com_pri}</span><c:if test='${discount!=1}'><img src='<%=request.getContextPath()%>/res/image/home/onSale.png' class='home_onSale_img'></c:if><img src='<%=request.getContextPath()%>/res/image/home/shoppingcart_plus.png' class='home_product_img' align='right'></td></tr></table></div></div><input type='hidden' name='com_no' value='${commodityVO.com_no}'><input type='hidden' name='com_name' value='${commodityVO.com_name}'><input type='hidden' name='com_price' value='${com_pri}'><input type='hidden' name='shop_no' value='${commodityVO.shop_no }'><input type='hidden' name='com_store' value='${commodityVO.com_store }'><input type='hidden' name='sentURL' value='<%=request.getServletPath() %>'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value=1></form></div>");
								    			}
								    		}else{
							    				$("#${commodityVO.fru_no}").append("<form id='${commodityVO.com_no}Form' action='<%=request.getContextPath() %>/front-end/shopping/shopping.do' method='POST'><div class='col-xs-12 col-sm-4'><div class='thumbnail home_product_item'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${commodityVO.com_no}'><img src='<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${commodityVO.com_no}&i=1'></a><table class='home_product_table'><tr><td colspan='2' class='pro_name'><a href='<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${commodityVO.com_no}'>${commodityVO.com_name}</a></td></tr><tr><td>$ <span class='priceSpan'><fmt:formatNumber var='com_pri' type='number' value='${discount*commodityVO.com_price}' pattern='#,#00'/>${com_pri}</span><c:if test='${discount!=1}'><img src='<%=request.getContextPath()%>/res/image/home/onSale.png' class='home_onSale_img'></c:if><img src='<%=request.getContextPath()%>/res/image/home/shoppingcart_plus.png' class='home_product_img' align='right'></td></tr></table></div></div><input type='hidden' name='com_no' value='${commodityVO.com_no}'><input type='hidden' name='com_name' value='${commodityVO.com_name}'><input type='hidden' name='com_price' value='${com_pri}'><input type='hidden' name='shop_no' value='${commodityVO.shop_no }'><input type='hidden' name='com_store' value='${commodityVO.com_store }'><input type='hidden' name='sentURL' value='<%=request.getServletPath() %>'><input type='hidden' name='action' value='ADD'><input type='hidden' name='quan' value=1></form>");
								    		}
								    		$("#${commodityVO.com_no}Form .home_product_img").on('click', function(){
												swal('成功加入購物車!', '再繼續逛逛吧', 'success').then(function() {
													  setTimeout(function(){
													  	$("#${commodityVO.com_no}Form").submit();
													  }, 200);
												  });
											})
								    	</script>
								    	
								    </c:if>
								</c:forEach>
							    
							</div>
						</div><!-- 產品區結束 -->
						<div class="col-xs-12 col-md-pull-8 col-md-4">
<img src="<%=request.getContextPath()%>/res/image/home/bonus.jpg" style="width:300px;">
<img src="<%=request.getContextPath()%>/res/image/home/welcome.png" style="width:300px;">
						
<img src="<%=request.getContextPath()%>/res/image/home/happy.png" style="width:300px;">


						</div>
					</div>
				</div>		
		



<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>
<%-- ~~body結束:無需寫body或html標籤~~ --%>
					