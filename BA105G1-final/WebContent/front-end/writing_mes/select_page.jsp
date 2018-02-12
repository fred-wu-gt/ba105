<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_mes.model.*"%>
<html>

<%@ include file="/front-end/HtmlHeadPage.jsp" %>

<head>
<title>文章留言專區</title>

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


	<h3>查詢文章留言:</h3>

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
		<li><a href='listAllWriting_mes.jsp'>List</a> all Writing messages. <br>
			<br></li>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/writing_mes/writing_mes.do">
				<b>輸入文章留言編號 (如WMSG000001):</b> <input type="text" name="wmsg_no">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="Wri_mesSvc" scope="page"
			class="com.wri_mes.model.Wri_mesService" />

		<jsp:useBean id="wri_mesSvc" scope="page"
			class="com.wri_mes.model.Wri_mesService" />

	</ul>

	<h3>文章留言管理</h3>

	<ul>
		<li><a href='addWriting_mes.jsp'>新增</a>留言.</li>
	</ul>
	<%@ include file="/front-end/FooterPage.jsp" %>
</body>
</html>