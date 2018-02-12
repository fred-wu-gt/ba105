<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.ord_mas.model.*"%>
<%@ page import="java.util.List"%>

<%
Ord_masService ord_masSvc= new Ord_masService();
List<Ord_masVO> list = ord_masSvc.getAll();
pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有訂單主檔資料-listAllOrd_Mas.jsp</title>

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
	width: 800px;
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
		 <h3>所有訂單主檔資料-listAllOrd_Mas.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
	<%@ include file="page/page1.file" %> 
	<c:forEach var="ord_masVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		<tr>
			<td>${ord_masVO.ord_no}</td>
			<td>${ord_masVO.mem_no}</td>
			<td>${ord_masVO.shop_no }</td>
			<td>${ord_masVO.ord_time}</td>
			<td>${ord_masVO.ord_sta}</td>
			<td>${ord_masVO.ord_total}</td>
			<td>${ord_masVO.ord_rec}</td>
			<td>${ord_masVO.ord_adr}</td>
			<td>${ord_masVO.ord_tel}</td>
			<td>${ord_masVO.ord_can_rea}</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/Ord_Mas/ord_mas.do" style="margin-bottom: 0px;">
				     <input type="submit" value="修改">
				     <input type="hidden" name="ord_no"  value="${ord_masVO.ord_no}">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				</FORM>			
			</td>				 	
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/Commodity/commodity.do" style="margin-bottom: 0px;">
					     <input type="submit" value="刪除">
					     <input type="hidden" name="empno"  value="${comVO.com_no}">
					     <input type="hidden" name="action" value="delete">
				 </FORM>		
			</td>
		</tr>	
	</c:forEach>
</table>
<%@ include file="page/page2.file" %>



</body>
</html>