<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/HtmlHeadPage.jsp"%>

<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>登入成功畫面</title>


</head>

<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>



<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-2 aa" align="left" >
					<div class="panel panel-info" style="width:100%">
					  <div class="panel-heading">
					    <h3 class="panel-title" align="center">會員資料</h3>
					  </div>
					  <div class="list-group" align="center">
					   <a href="<%=request.getContextPath()%>/front-end/member/listOneMember2.jsp"><b>查詢會員資料</b></a>
					   <br><a href="<%=request.getContextPath()%>/front-end/fav_com/listOneFav_com2.jsp"><b>收藏商品管理</b></a>
<%-- 					   <br><a href="<%=request.getContextPath()%>/front-end/fav_shop/listOneFav_shop2.jsp"><b>收藏商家管理</b></a> --%>
					   <br><a href="<%=request.getContextPath()%>/front-end/ord_mas/listAllByMemNo.jsp"><b>瀏覽訂單</b></a>
					   
					  </div> 
					  <!-- <div class="panel-body">
					  </div> -->
					</div>
				



				</div>
				<div class="col-xs-12 col-sm-10">
					<h3>
									Hello!!歡迎您回來<b>${loginMemberVO.mem_name}</b>先生/小姐
									 
										決定好今天要買甚麼商品了嗎^_^
								</h3>
				</div>
			</div>
		</div>


<!-- <div class="container"> -->
<!-- 			<div class="row"> -->

<!-- 				左欄 -->
<!-- 				<div class="col-xs-12 col-sm-2"> -->
<!-- 					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true"> -->
<!-- 					  區塊1 -->
<!-- 					  <div class="panel panel-default"> -->
<!-- 					    <div class="panel-heading" role="tab" id="panel2"> -->
<!-- 					      <h4 class="panel-title"> -->
<!-- 					        <a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="aaa"> -->
<!-- 					          <h4>會員中心</h4> -->
<!-- 					        </a> -->
<!-- 					      </h4> -->
<!-- 					    </div> -->
<!-- 					    <div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2"> -->
<!-- 					      <div class="panel-body"> -->
<!-- 					        <ul class="list-unstyled"> -->
<%-- 					        	<li><a href="<%=request.getContextPath()%>/front-end/member/update_Member_input.jsp"><b>查詢/修改會員資料</b></a></li> --%>
<!-- 					        	<li><a href="#"><b>收藏商品管理</b></a></li> -->
<!-- 					        	<li><a href="#"><b>收藏商家管理</b></a></li> -->
<!-- 					        	<li><a href="#"><b>瀏覽訂單</b></a></li> -->
<!-- 					        	<li><a href="#"><b>私訊管理</b></a></li> -->
<!-- 					        	<li><a href="#"><b>信件管理</b></a></li> -->
<!-- 					        </ul> -->
<!-- 					      </div> -->
<!-- 					    </div> -->
<!-- 					    區塊1結束 -->
<!-- 					  </div> -->
<!-- 					  區塊2 -->
<!-- 					  <div class="panel panel-default"> -->
					    <div class="panel-heading" role="tab" id="panel2">
<!-- 					      <h4 class="panel-title"> -->
<!-- 					        <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb"> -->
<!-- 					          <h4>商品管理</h4> -->
<!-- 					        </a> -->
<!-- 					      </h4> -->
					    </div>
<!-- 					    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2"> -->
<!-- 					      <div class="panel-body"> -->
<!-- 					        <ul class="list-unstyled"> -->
<!-- 					        	<li><a href="shop.html"><b>商品上下架</b></a></li> -->
<!-- 					        	<li><a href="#"><b>商品提問回覆</b></a></li> -->
<!-- 					        	<li><a href="shop_advertisment.html"><b>申請打廣告</b></a></li> -->
<!-- 					        	<li><a href="#"><b>促銷方案管理</b></a></li> -->
<!-- 					        </ul> -->
<!-- 					      </div> -->
<!-- 					    </div> -->
<!-- 					    區塊2結束 -->
<!-- 					  </div> -->
<!-- 					  區塊3 -->
<!-- 					  <div class="panel panel-default"> -->
<!-- 					    <div class="panel-heading" role="tab" id="panel3"> -->
<!-- 					      <h4 class="panel-title"> -->
<!-- 					        <a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc"> -->
<!-- 					          <h4>訂單管理</h4> -->
<!-- 					        </a> -->
<!-- 					      </h4> -->
<!-- 					    </div> -->
<!-- 					    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3"> -->
<!-- 					      <div class="panel-body"> -->
					        
   
<!-- 							 <ul class="list-unstyled"> -->
<!-- 					        	<li><a href="shop_order_manage.html"><b>商品出貨狀態</b></a></li> -->
<!-- 					        	<li><a href="#"><b>款項狀態</b></a></li> -->
<!-- 					        </ul> -->


<!-- 					      </div> -->
<!-- 					    </div> -->
<!-- 					  </div> -->
<!-- 					區塊3結束 -->
<!-- 					區塊4 -->
<!-- 					<div class="panel panel-default"> -->
<!-- 					    <div class="panel-heading" role="tab" id="panel3"> -->
<!-- 					      <h4 class="panel-title"> -->
<!-- 					        <a href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ddd"> -->
<!-- 					          	<h4>查詢帳務資訊</h4> -->
<!-- 					        </a> -->
<!-- 					      </h4> -->
<!-- 					    </div> -->
<!-- 					    <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3"> -->
<!-- 					      <div class="panel-body"> -->
					       
<!-- 					      </div> -->
<!-- 					    </div> -->
<!-- 					</div> -->
<!-- 					區塊4結束 -->
<!-- 					</div> -->
<!-- 					</div> -->

<!-- 						右欄開始 -->
					
<!-- 					<div class="col-xs-12 col-sm-10"> -->
<!-- 							<span style="font-family:monospace,DFKai-sb;"> -->
<!-- 								<h3> -->
<!-- 									Hello!!歡迎您回來最帥的XXX先生/小姐 -->
									 
<!-- 										決定好今天要買甚麼商品了嗎^_^ -->
<!-- 								</h3> -->
<!-- 							</span> -->
						
									
<!-- 					</div> -->
<!-- 						右欄結束	 -->
<!-- 				<div id="fastbtn">浮動區快鍵開始 -->
<!-- 				<div> -->
<!-- 					<img src="res/image/home/chat.png" class="fastbtn_icon"> -->
<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<img src="res/image/home/email.png" class="fastbtn_icon"> -->
<!-- 				</div> -->
<!-- 				<div> -->
<!-- 					<img src="res/image/home/shoppingcart.png" class="fastbtn_icon"> -->
<!-- 				</div> -->
<!-- 				<div id="fastbtn_div_top"> -->
<!-- 					<img src="res/image/home/top.png" class="fastbtn_icon"> -->
<!-- 				</div> -->
<!-- 			</div>浮動區快鍵結束 -->
						

<!-- 			</div> -->
<!-- 			</div> -->
<!-- 			<!-- 大容器結束 --> 
<!-- 			</div> -->


<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>



<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>