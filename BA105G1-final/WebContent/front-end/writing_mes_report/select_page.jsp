<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_mes_report.model.*"%>
<html>

<%@ include file="/front-end/HtmlHeadPage.jsp" %>

<head>
<title>文章留言檢舉專區</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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


</head>

<%@ include file="/front-end/HeaderPage.jsp" %>

<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>文章留言檢舉區塊</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for FruitBook Writing_mes_report: Home</p>

	<h3>查詢文章檢舉:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllWriting_mes_report.jsp'>List</a> all Writing message reports. <br>
			<br></li>
		<li>
			<FORM METHOD="post" ACTION="writing_mes_report.do">
				<b>輸入文章留言檢舉編號 (如WMRPT00001):</b> <input type="text" name="wmrpt_no">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="Wri_mes_reportSvc" scope="page"
			class="com.wri_mes_report.model.Wri_mes_reportService" />

		<jsp:useBean id="wri_mes_reportSvc" scope="page"
			class="com.wri_mes_report.model.Wri_mes_reportService" />

	</ul>

	<h3>文章留言檢舉管理</h3>

	<ul>
		<li><a href='addWriting_mes_report.jsp'>Add</a> a new Writing_mes_report.</li>
	</ul>
</body>
<%@ include file="/front-end/FooterPage.jsp" %>
</html>