<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ord_mas.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    
<%  Ord_masVO ord_masvo = (Ord_masVO) request.getAttribute("ord_masvo");%>

<%= (ord_masvo==null)%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單主檔資料修改- update_Ord_mas_input</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>訂單主檔資料修改- update_Ord_mas_input</h3>
				<h4>
					<a href="select_page.jsp"><img src="" width="100" height="32"
						border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>收件資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


<FORM METHOD="post" ACTION="ord_mas.do" name="form1">
<!-- enctype="multipart/form-data" 要放進FORM的屬性   -->

<!-- 
用EL取法練習
 -->
<table>
	<tr>
		<td>訂單主檔編號:<font color=red></font></td>
		<td>${ord_masvo.ord_no }</td>
	</tr>
	<tr>
		<td>一般會員編號:</td>
		<td>${ord_masvo.mem_no}</td>
	</tr>
	<tr>
		<td>商家編號:</td>
		<td>${ord_masvo.shop_no}</td>
	</tr>
	
	<tr>
		<td>下單時間:</td>
		<td><fmt:formatDate value="${ord_masvo.ord_time}" pattern="yyyy-MM-dd HH:mm"/></td>
	</tr>
	
	<tr>
		<td>訂單金額:</td>
		<td>${ord_masvo.ord_total}</td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td>${ord_masvo.ord_sta}</td>
	</tr>
	<tr>
		<td>收貨人姓名:</td>
		<td><input type="text" name="ord_rec" size="45"	value="<%=ord_masvo.getOrd_rec()%>" /></td>		
		
	</tr>
	<tr>
		<td>收貨人地址:</td>
		<td><input type="text" name="ord_adr" size="45"	value="<%=ord_masvo.getOrd_adr()%>" /></td>		
	</tr>
	<tr>
		<td>收貨人電話:</td>
		<td><input type="text" name="ord_tel" size="45"	value="<%=ord_masvo.getOrd_tel()%>" /></td>
	</tr>
	<tr>
		<td>訂單取消原因:</td>
		<td>${ord_masvo.ord_can_rea}</td>
	</tr>
	
	
	
</table>
<br>
<input type="hidden" name="action" value="updateRec">
<input type="hidden" name="ord_no" value="${ord_masvo.ord_no }">
<input type="hidden" name="mem_no" value="${ord_masvo.mem_no}">
<input type="hidden" name="shop_no" value="${ord_masvo.shop_no }">
<input type="hidden" name="ord_time" value="${ord_masvo.ord_time}">
<input type="hidden" name="ord_total" value="${ord_masvo.ord_total}">
<input type="hidden" name="ord_sta" value="${ord_masvo.ord_sta}">
<input type="hidden" name="ord_can_rea" value="${ord_masvo.ord_can_rea}">
<input type="submit" value="送出修改">
</FORM>


</body>
</html>