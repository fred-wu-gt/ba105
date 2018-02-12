<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="java.util.List"%>


<%@ include file="/front-end/HtmlHeadPage.jsp"%><%--~~必須include1/3~~ --%>

<head>


<!-- Font Awesome CDN -->
<script src="https://use.fontawesome.com/f33d2f4ae3.js"></script>

<!-- sweetalert2 cdn -->
<script
	src="https://unpkg.com/sweetalert2@7.0.9/dist/sweetalert2.all.js"></script>

<style type="text/css">
.sa_searchbar {
	margin-right: 45px;
}

.cartbtn {
	text-align: right;
}

.hotitem {
	text-align: center;
}

.hotitem div {
	color: #FF9E0D;
	font-weight: bold;
}

.thumbnail {
	background-color: transparent;
	border: 1px;
}

</style>


<script type="text/javascript">
	
		window.onload = init;
		function init() {
			$('.cartbtn2').each(function(){
				$(this).on('click', function(){
					var xxx = $(this);
					swal('成功加入購物車!', '再繼續逛逛吧', 'success').then(function() {
						  setTimeout(function(){
						  	$(xxx).parents('form').submit();
						  }, 200);
					  });
				})
			})
		}
	</script>
</head>
<%
	CommodityService comSvc = new CommodityService();
// 	List<CommodityVO> list = comSvc.getAll();
// 	pageContext.setAttribute("list", list);

// List<CommodityVO> list = (List<CommodityVO>)request.getAttribute("listCommodityByCompositeQuery");
// System.out.print(list);
%>

<jsp:useBean id="listCommodityByCompositeQuery" scope="request" type="java.util.List<CommodityVO>" />
<% System.out.print(listCommodityByCompositeQuery.size()); %>

<jsp:useBean id="hsSvc" scope="page" class="com.hotsales.model.HotsalesService" />

<%@ include file="/front-end/HeaderPage.jsp"%>
<%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>


<!-- 請在此加內文 -->
<div class="col-xs-12">
	<!-- 搜尋表單 -->
	<form METHOD="post" class="navbar-form navbar-right" action="<%=request.getContextPath()%>/front-end/commodity/commodity.do">
		<div class="input-group sa_searchbar">
			<input type="text" class="form-control" placeholder="Search for..." name="com_name" value="">
			<input type="hidden" name="action" value="listEmps_ByCompositeQuery">
			<span class="input-group-btn">
<!-- 			 	<input class="btn btn-danger" type="submit" value=""> -->
				<button class="btn btn-danger" type="submit" >Go!</button>
			</span>
		</div>
		<!-- /input-group -->
	</form>
</div>
<div class="col-xs-12">
	
</div>

<div class="container">
	<div class="row">
	
		<div class="col-xs-12 col-sm-2">
			<h3 align="center"><b>熱門商品</b></h3>
			
				<%
					 List<String> listCom_no  = hsSvc.findByOd_quan();
					for(int i = 1; i <= 5 ; i++){
						String com_no= listCom_no.get(i);
						CommodityVO hotComVO= comSvc.getOneCommodityVO(com_no);
						pageContext.setAttribute("hotComVO", hotComVO);
						%>
				
			<a href="<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${hotComVO.com_no}" class="list-group-item hotitem">
				<div>
					<img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${hotComVO.com_no}&i=1"alt="尚無圖片" height="150px" width="250px">
				</div>
				<div>
					NO.<%=i %> <span class="sa_hotitem_1">${hotComVO.com_name }</span>
				</div>
			</a> 
			<% }%>
		
		
		</div>
		
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<%@ include file="QueryPage1.file"%>
		<div class="col-xs-12 col-sm-10">
			<c:forEach var="comVO" items="${listCommodityByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					
				<div class="col-xs-12 col-sm-4">
						<a href="<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${comVO.com_no}"class="thumbnail"> 
							<img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${comVO.com_no}&i=1"alt="尚無圖片" height="200" width="300">
						</a>
						<div class="caption">
							<form name="shoppingForm" action="<%=request.getContextPath() %>/front-end/shopping/shopping.do" 	method="POST">
								<table>
									<tr>
										<td><h4>${comVO.com_name}</h4></td>
									</tr>

									<tr>
										<td>
											<span class="sa_newprice">${comVO.com_price}</span>元
										</td>
										<td>
											<div class="cartbtn">
												<input type="button" class="cartbtn2 btn-info" value="放入購物車">
											</div>
										</td>
									</tr>
								</table>
								<input type="hidden" name="com_no" value="${comVO.com_no}">
								<input type="hidden" name="com_name" value="${comVO.com_name}">
								<input type="hidden" name="com_price" value="${comVO.com_price}">
								<input type="hidden" name="shop_no" value="${comVO.shop_no }">
								<input type="hidden" name="com_store" value="${comVO.com_store }">
								<input type="hidden" name="sentURL" value="<%=request.getServletPath() %>">
								<input type="hidden" name="action" value="ADD">
								<input type="hidden" name="quan" value=1>
							</form>
<%-- 							  <font color=blue>request.getServletPath(): </font> <%=request.getServletPath()%>  --%>
						</div>				
				</div>
			</c:forEach>
			
			<div class="col-xs-12">
				<%@ include file="QueryPage2.file"%>
			</div>
		</div>
	</div>	
</div>	

	<%-- ~~body結束:無需寫body或html標籤~~ --%>
<%@ include file="/front-end/FooterPage.jsp"%><%-- ~~必須include3/3~~ --%>