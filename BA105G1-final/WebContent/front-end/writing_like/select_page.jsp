<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_like.model.*"%>
<html>
<head>
<title>文章按讚專區</title>

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
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>文章按讚區塊</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for FruitBook Writing_like: Home</p>

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
		<li><a href='listAllWriting_like.jsp'>List</a> all Writing like. <br>
			<br></li>
		<li>
			<FORM METHOD="post" ACTION="writing_mes.do">
				<b>輸入文章編號 (如WRT0000001):</b> <input type="text" name="wrt_no">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="Wri_likeSvc" scope="page"
			class="com.wri_like.model.Wri_likeService" />

		<jsp:useBean id="wri_likeSvc" scope="page"
			class="com.wri_like.model.Wri_likeService" />

	</ul>

	<h3>文章按讚管理</h3>

	<ul>
		<li><a href='addWriting_like.jsp'>Add</a> a new Writing_like.</li>
	</ul>
</body>
</html>