<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ord_mas.model.*"%>

<%
Ord_masVO ord_masVO= (Ord_masVO) request.getAttribute("listOneOrd_Mas");
%>
ord_masVO == null? <%=ord_masVO==null %><br>
Ord_no = <%=ord_masVO.getOrd_no()%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>訂單主檔資料 - listOneOrd_mas.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body>
<table id="table-1">
	<tr><td>
		 <h3>訂單主檔資料 - listOneOrd_mas.jsp</h3>
		 <h4><a href="<%=request.getContextPath() %>/front-end/Ord_Mas/select_page.jsp"><img src="" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

		${ord_masVO.ord_no}
<table>
	<tr>
		<th>訂單主檔編號</th>
		<th>一般會員編號</th>
		<th>商家編號</th>
		<th>下單時間</th>
		<th>訂單狀態</th>
		<th>訂單金額</th>
		<th>收貨人姓名</th>
		<th>收貨人地址</th>
		<th>收貨人電話</th>
		<th>訂單取消原因</th>
	
	</tr>
	<tr>
			<td><%=ord_masVO.getOrd_no()%></td>
			<td><%=ord_masVO.getMem_no()%></td>
			<td><%=ord_masVO.getShop_no()%></td>
			<td><%=ord_masVO.getOrd_sta()%></td>
			<td><%=ord_masVO.getOrd_total()%></td>
			<td><%=ord_masVO.getOrd_time()%></td>
			<td><%=ord_masVO.getOrd_rec()%></td>
			<td><%=ord_masVO.getOrd_adr()%></td>
			<td><%=ord_masVO.getOrd_tel()%></td>
			<td>${ord_masVO.ord_can_rea}</td>
<%-- 			<td>${ord_masVO.ord_no}</td> --%>
<%-- 			<td>${ord_masVO.mem_no}</td> --%>
<%-- 			<td>${ord_masVO.shop_no }</td> --%>
<%-- 			<td>${ord_masVO.ord_time}</td> --%>
<%-- 			<td>${ord_masVO.ord_sta}</td> --%>
<%-- 			<td>${ord_masVO.ord_total}</td> --%>
<%-- 			<td>${ord_masVO.ord_rec}</td> --%>
<%-- 			<td>${ord_masVO.ord_adr}</td> --%>
<%-- 			<td>${ord_masVO.ord_tel}</td> --%>
<%-- 			<td>${ord_masVO.ord_can_rea}</td> --%>
	</tr>
</table>


</body>
</html>