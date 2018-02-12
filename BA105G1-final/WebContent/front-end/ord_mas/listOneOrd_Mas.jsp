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

<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~����include1/3~~ --%>

<!DOCTYPE html>


<head>
<style>
</style>
</head>

<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~����include2/3~~ --%>
<%-- ~~body�}�l:�Ъ����}�l�g����A�L���gbody��html����~~ --%>


		${ord_masVO.ord_no}
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<table class="table table-hover">
				
				<caption>�q��s��  ${listOneOrd_Mas.ord_no }</caption>
				<tbody>
					
					<tr>
						<td>�Ӯa�s��:</td> 
						<td>${listOneOrd_Mas.shop_no }</td>
					</tr>
					<tr>
						<td>�U��ɶ�:</td>
						<td>${listOneOrd_Mas.ord_time}</td> 
					</tr>
					<tr>
						<td>�q����B:</td>
						<td>${listOneOrd_Mas.ord_total}</td> 
					</tr>
					<tr>
						<td>�q�檬�A:</td>
						<td>${listOneOrd_Mas.ord_sta}</td> 
					</tr>
					<tr>
						<td>���f�H�m�W:</td>
						<td>${listOneOrd_Mas.ord_rec}</td> 
					</tr>
					<tr>
						<td>���f�H�a�}:</td>
						<td>${listOneOrd_Mas.ord_adr}</td>
					</tr>
					<tr>
						<td>���f�H�q��:</td>
						<td>${listOneOrd_Mas.ord_tel}</td> 
					</tr>	
				</tbody>
			</table>
			
			<div align="center">
			<a href="<%=request.getContextPath()%>/front-end/ord_mas/listAllByMemNo.jsp">
			<font style="color: blue;font-size:20px;font-weight:bold;">�^��q��޲z </font>
			</a>
			</div>
			
	

		</div>
	</div>
</div>


<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~����include3/3~~ --%>
<%-- ~~body����:�L�ݼgbody��html����~~ --%>

