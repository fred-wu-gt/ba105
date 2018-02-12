<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.lang.Math"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.prom.model.*"%>


<jsp:useBean id="promSvc" class="com.prom.model.PromService"/>
 

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
					console.log('a');
					var xxx = $(this);
					swal('成功加入購物車!', '', 'success').then(function() {
						  setTimeout(function(){
						  	$(xxx).parents('form').submit();
						  }, 200);
					  });
				})
			})
// 			var cartbtn = document.getElementsByClassName("cartbtn2");
// 			var cartbtn = $('.cartbtn2');
// 			// console.log(cartbtn.length);
// 			for (var i = 0; i < cartbtn.length; i++)
// 				cartbtn[i].addEventListener("click", addcar, false);
		}
	</script>
</head>

<%
	CommodityService comSvc = new CommodityService();
	List<CommodityVO> list = comSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>

<jsp:useBean id="hsSvc" scope="page" class="com.hotsales.model.HotsalesService" />

<%@ include file="/front-end/HeaderPage.jsp"%>
<%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>


<!-- 請在此加內文 -->
<div class="col-xs-12 col-sm-offset-9" >
	<!-- 搜尋表單 -->
	<form METHOD="post" class="navbar-form" action="<%=request.getContextPath()%>/front-end/commodity/commodity.do">
		<div class="input-group sa_searchbar">
			<input type="text" class="form-control" placeholder="找找你愛吃的..." name="com_name" value="">
			<input type="hidden" name="action" value="listEmps_ByCompositeQuery">
			<span class="input-group-btn">
<!-- 			 	<input class="btn btn-danger" type="submit" value=""> -->
				<button class="btn btn-danger" type="submit" >Go!</button>
			</span>
		</div>
		<!-- /input-group -->
	</form>
</div>
<div class="container">
	<div class="row">
	
		<div class="col-xs-12 col-sm-2">

			<h3 align="center"><b>熱門商品</b></h3>
<%-- 			<a href="<%=request.getContextPath()%>/front-end/ord_mas/listAllByMemNo.jsp">訂單查詢</a> --%>
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
	
		<%@ include file="page1.file"%>
		<div class="col-xs-12 col-sm-10">
			<c:forEach var="comVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<c:if test="${comVO.com_status == '上架' }">
				<div class="col-xs-12 col-sm-4" style="width:300px; height:400px" >
						<a href="<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${comVO.com_no}"class="thumbnail"> 
							<img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${comVO.com_no}&i=1"alt="尚無圖片" height="200" width="300">
						</a>
						<div class="caption">
							<form name="shoppingForm" action="<%=request.getContextPath() %>/front-end/shopping/shopping.do" method="POST">
								<table>
									<tr>
										<td colspan="2">
										<h4>${comVO.com_name}</h4>
										</td>
										
									</tr>
									

<%
	// 要從JSTL取出comVO時，必須先將comVO從pageContext取出來
		CommodityVO comVO = (CommodityVO) pageContext.getAttribute("comVO");
		// 

		PromVO promVO = promSvc.findByPrimaryKey(comVO.getShop_no());
		//重要!!! 防止promVO為null的處理
		if (promVO != null) {
			pageContext.setAttribute("promVO", promVO);
			// 取出折扣開始，結束時間 及 當下時間
			Timestamp start_time = promVO.getProm_start();
			Timestamp end_time = promVO.getProm_end();
			Timestamp now_time = new Timestamp(new java.util.Date().getTime());

			// 用旗標的方式給符合時間條件 為 true。 時間用long來比較
			boolean prom = false;
			if (now_time.getTime() > start_time.getTime() && end_time.getTime() > now_time.getTime()) {
				// 	時間在區段內，改為TRUE
				prom = true;
			}

			pageContext.setAttribute("prom", prom);
		}
%>
									<tr>
										<td>
<%-- 											<span class="sa_newprice">${prom? (promVO.prom_dis*comVO.com_price):comVO.com_price}</span> --%>
<!-- 											四捨五入的格式化 -->
											
<%-- 											<font color="gray"><span class="sa_newprice"><del>${comVO.com_price}</del></span></font> --%>

											<c:set var="xxx" value="${prom ? (promVO.prom_dis*comVO.com_price):comVO.com_price}"></c:set>										
											<%
											Object yyy = pageContext.getAttribute("xxx");
											long showPrice = Math.round(Double.parseDouble(yyy.toString()));
											pageContext.setAttribute("showPrice", showPrice);
											%>	
																
											<c:if test="${showPrice == comVO.com_price }"> 
											<b><span class="sa_newprice"> <fmt:formatNumber type="number" value="${showPrice}" pattern="#,#00"/></span></b>元
											</c:if>
											
											<c:if test="${showPrice != comVO.com_price }"> 
											<font color="gray">
												<span class="sa_newprice"> <del><fmt:formatNumber type="number" value="${comVO.com_price}" pattern="#,#00"/></del></span>
											</font>
											<font color="red">
												<b><span class="sa_newprice"> <fmt:formatNumber type="number" value="${showPrice}" pattern="#,#00"/></span></b></font>
												元
											</c:if>
											
											
											
										</td>
										<td>
											<div class="cartbtn">
												<input type="button" class="cartbtn2 ${comVO.com_store=='0'?'btn-warning':'btn-info'}" value="${comVO.com_store=='0'?'補貨中':'放入購物車'}" ${(comVO.com_store=='0'||not empty loginShopVO)?'disabled':''}>
											</div>
											
<%-- 											<c:if test="${not empty loginMemberVO || }">		 --%>
<!-- 												<div class="cartbtn"> -->
<%-- 													<input type="button" class="cartbtn2 ${comVO.com_store=='0'?'btn-warning':'btn-info'}" value="${comVO.com_store=='0'?'補貨中':'放入購物車'}" ${comVO.com_store=='0'?'disabled':''}> --%>
<!-- 												</div>						 				 -->
<%-- 											</c:if> --%>
											
											
<%-- 											<c:if test="${not empty loginShopVO}">		 --%>
<!-- 												<div class="cartbtn"> -->
<%-- 													<input type="button" class="cartbtn2 ${comVO.com_store=='0'?'btn-warning':'btn-info'}" value="${comVO.com_store=='0'?'補貨中':'放入購物車'}" disabled> --%>
<!-- 												</div>						 				 -->
<%-- 											</c:if> --%>
										</td>
									</tr>
								</table>
								<input type="hidden" name="com_no" value="${comVO.com_no}">
								<input type="hidden" name="com_name" value="${comVO.com_name}">
								<input type="hidden" name="com_price" value="${comVO.com_price}">
								<input type="hidden" name="shop_no" value="${comVO.shop_no }">
								<input type="hidden" name="com_store" value="${comVO.com_store }">
								<input type="hidden" name="sentURL" value="<%=request.getRequestURI() %>">
								<input type="hidden" name="action" value="ADD">
								<input type="hidden" name="quan" value=1>
							</form>
						</div>				
				</div>
				</c:if>
			</c:forEach>

			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-sm-offset-5">
						<%@ include file="page2.file"%>
					</div>

				</div>
			</div>
		</div>	
</div>	

	<%-- ~~body結束:無需寫body或html標籤~~ --%>
<%@ include file="/front-end/FooterPage.jsp"%><%-- ~~必須include3/3~~ --%>