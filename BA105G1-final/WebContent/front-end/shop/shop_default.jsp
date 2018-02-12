<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<% 

ShopVO shopVO = (ShopVO) session.getAttribute("loginShopVO");
pageContext.setAttribute("shopVO",shopVO);

%>

<title>商家首頁</title>

<%@ include file="/front-end/HtmlHeadPage.jsp" %> 
<head>	
</head>
<%@ include file="/front-end/HeaderPage.jsp"%> 

					
			<!-- 大容器開始 -->
			<div class="container">
			<div class="row">

				<!-- 左欄 -->
				<div class="col-xs-12 col-sm-2">
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					  <!-- 區塊1 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel2">
					      <h4 class="panel-title">
					        <a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="aaa">
					          <h4>商家資料管理</h4>
					        </a>
					      </h4>
					    </div>
					    <div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
					      <div class="panel-body">
					        <ul class="list-unstyled">
<!-- 					        	<li><a href="#"><b>商家資料查詢</b></a></li> -->
					        	<li><a href="<%=request.getContextPath()%>/front-end/shop/shopUpdate.jsp"><b>商家資料修改</b></a></li>
					        </ul>
					      </div>
					    </div>
					    <!-- 區塊1結束 -->
					  </div>
					  <!-- 區塊2 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel2">
					      <h4 class="panel-title">
					        <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
					          <h4>商品管理</h4>
					        </a>
					      </h4>
					    </div>
					    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
					      <div class="panel-body">
					        <ul class="list-unstyled">
					        	<li><a href="<%=request.getContextPath()%>/front-end/shop/addShopCommodity.jsp"><b>新增商品</b></a></li>
					        	<li><a href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=getOne_For_Display&shop_no=${shopVO.shop_no}"><b>商品上下架</b></a></li>
					        	<li><a href="<%=request.getContextPath()%>/front-end/ad/adadd.jsp"><b>申請打廣告</b></a></li>
					        	<li><a href="<%=request.getContextPath()%>/front-end/prom/prom.do?action=init&shop_no=${shopVO.shop_no}"><b>促銷方案管理</b></a></li>
					        </ul>
					      </div>
					    </div>
					    <!-- 區塊2結束 -->
					  </div>
					  <!-- 區塊3 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel3">
					      <h4 class="panel-title">
					        <a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
					          <h4>訂單管理</h4>
					        </a>
					      </h4>
					    </div>
					    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
					      <div class="panel-body">
							 <ul class="list-unstyled">
					        	<li><a href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=shopOrder&shop_no=${shopVO.shop_no}"><b>商品出貨狀態</b></a></li>
					        </ul>
					      </div>
					    </div>
					  </div>
					<!-- 區塊3結束 -->
					</div>
					</div>

					
					<div class="col-xs-12 col-sm-10">		<!-- 右欄開始 -->
							<span style="font-family:monospace,DFKai-sb;">
								<h3>
									Hello!! ${loginShopVO.shop_name}決定好今天要賣甚麼商品了嗎^_^
								</h3>
							</span>
							
					</div>		<!-- 右欄結束 -->												
			</div>
			</div>	<!-- 大容器結束 -->
			<br><br><br><br><br><br><br><br><br><br><br><br><br>
	<%@ include file="/front-end/FooterPage.jsp" %>