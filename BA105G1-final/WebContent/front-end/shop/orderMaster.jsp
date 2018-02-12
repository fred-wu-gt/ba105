<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.ord_mas.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.List"%>
<jsp:useBean id="ord_masVO" scope="request" class="com.ord_mas.model.Ord_masVO" />
<jsp:useBean id="ord_masSvc" scope="request" class="com.ord_mas.model.Ord_masService" />
<jsp:useBean id="memSvc" scope="request" class="com.member.model.MemberService" />
<%@ include file="/front-end/HtmlHeadPage.jsp" %>
<%
	ShopVO shopVO = (ShopVO) session.getAttribute("loginShopVO");
pageContext.setAttribute("shopVO",shopVO);
	List<Ord_masVO> list = ord_masSvc.getListByShopNO(shopVO.getShop_no());
	request.setAttribute("list", list);
%>
<head>
<style>
.noWrap {
	white-space: nowrap;
}
</style>

<script>
$(document).ready(function(){		
	if ("${export}".length != 0) {
		swal("確認出貨,信件已寄出", "已出貨", "success");
	}


});



</script>
</head>
<title>商家訂單管理</title>
		<%@ include file="/front-end/HeaderPage.jsp" %>
		<div class="container">				<!-- 大容器開始 -->
			<div class="row">				<!-- ROW開始 -->
							
				<div class="col-xs-12 col-sm-2">		<!-- 左欄 -->
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
					</div>	<!-- 左欄結束 -->
			
				<div class="col-xs-12 col-sm-10">	<!-- 右欄開始 -->
						<table class="table table-hover table-bordered" id="table" >
							<thead style="background-color:#d0f28a;">
								<tr>
									<th class="noWrap">訂單編號</th>
									<th class="noWrap">取件人</th>
									<th class="noWrap">電話</th>
									<th class="noWrap">商品價格</th>
									<th class="noWrap">地址</th>
									<th class="noWrap">訂單狀態</th>
									<th class="noWrap">訂單詳情</th>
									<th class="noWrap">出貨</th>					
								</tr>
							</thead>
							<%@ include file="page1.file" %>
							<c:forEach var="ord_masVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> 
								<c:if test="${ord_masVO.shop_no ==shopVO.shop_no}">	
									<tbody>
												<tr>
													<td>${ord_masVO.ord_no}</td>
													<td>${ord_masVO.ord_rec}</td>
													<td>${ord_masVO.ord_tel}</td>
													<td>${ord_masVO.ord_total}</td>
													<td>${ord_masVO.ord_adr}</td>
													<td name="ord_sta">${ord_masVO.ord_sta}</td>
													<td>
													<a href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=shopOrderDetail&ord_no=${ord_masVO.ord_no}" class="btn btn-warning">訂單詳情</a>
													</td>
													<td>
													<a href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=updateOrd_sta&ord_no=${ord_masVO.ord_no}" class="btn btn-warning">點擊出貨</a>
													</td>
												</tr>
									</tbody>
								</c:if>
							</c:forEach>
						</table>
					<%@ include file="page2.file" %>
				</div>	<!-- 右欄結束 -->
			</div>		<!-- row結束 -->
		</div>			<!-- 大容器結束 -->	
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<br><br><br><br><br><br><br><br><br><br><br><br><br>
		<%@ include file="/front-end/FooterPage.jsp" %>