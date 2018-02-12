<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.rep_com.model.*"%>

<%
Rep_comVO rep_comVO= (Rep_comVO) request.getAttribute("rep_comVO");
%>

rep_comVO == null? 	<%=rep_comVO==null %>
<br>
rep_comVO = 	<%=rep_comVO.getRc_no()%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品檢舉資料 - listOneRep_com.jsp</title>

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
		 <h3>商品檢舉資料 - listOneRep_com.jsp</h3>
		 <h4><a href="<%=request.getContextPath() %>/front-end/rep_com/select_page.jsp"><img src="" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

		${rep_comVO.rc_no}
<table>
	<tr>
		<th>商品檢舉編號</th>
		<th>商品編號</th>
		<th>一般會員編號</th>
		<th>檢舉原因</th>
		<th>商品檢舉狀態</th>
		<th>檢舉內容</th>
	
	</tr>
	<tr>
			<td>${rep_comVO.getRc_no()}</td>
<%-- 			<td><%= rep_comVO.getRc_no() %></td> --%>
			<td>${rep_comVO.com_no}</td>
			<td>${rep_comVO.mem_no }</td>
			<td>${rep_comVO.rc_cuz}</td>
			<td>${rep_comVO.rc_stat}</td>
			<td>${rep_comVO.rc_txt}</td>
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