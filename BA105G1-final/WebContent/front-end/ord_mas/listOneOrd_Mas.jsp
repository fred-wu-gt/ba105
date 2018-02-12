<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ord_mas.model.*"%>

<%
Ord_masVO ord_masVO= (Ord_masVO) request.getAttribute("listOneOrd_Mas");
 String ord_no =ord_masVO.getOrd_no();
 
%>

<jsp:useBean id="ord_masSvc" class="com.ord_mas.model.Ord_masService"/>
${ord_masSvc.getOneOrd_mas(ord_no).shop_no }

<%-- ord_masVO == null? <%=ord_masVO==null %><br> --%>
<%-- Ord_no = <%=ord_masVO.getOrd_no()%> --%>

<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>

<!DOCTYPE html>


<head>
<style>
</style>
</head>

<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>


		${ord_masVO.ord_no}
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<table class="table table-hover">
				
				<caption>訂單編號  ${listOneOrd_Mas.ord_no }</caption>
				<tbody>
					
					<tr>
						<td>商家編號:</td> 
						<td>${listOneOrd_Mas.shop_no }</td>
					</tr>
					<tr>
						<td>下單時間:</td>
						<td>${listOneOrd_Mas.ord_time}</td> 
					</tr>
					<tr>
						<td>訂單金額:</td>
						<td>${listOneOrd_Mas.ord_total}</td> 
					</tr>
					<tr>
						<td>訂單狀態:</td>
						<td>${listOneOrd_Mas.ord_sta}</td> 
					</tr>
					<tr>
						<td>收貨人姓名:</td>
						<td>${listOneOrd_Mas.ord_rec}</td> 
					</tr>
					<tr>
						<td>收貨人地址:</td>
						<td>${listOneOrd_Mas.ord_adr}</td>
					</tr>
					<tr>
						<td>收貨人電話:</td>
						<td>${listOneOrd_Mas.ord_tel}</td> 
					</tr>	
				</tbody>
			</table>
			
			<div align="center">
			<a href="<%=request.getContextPath()%>/front-end/ord_mas/listAllByMemNo.jsp">
			<font style="color: blue;font-size:20px;font-weight:bold;">回到訂單管理 </font>
			</a>
			</div>
			
	

		</div>
	</div>
</div>


<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>
<%-- ~~body結束:無需寫body或html標籤~~ --%>

