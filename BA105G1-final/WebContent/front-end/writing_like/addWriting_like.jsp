<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_like.model.*"%>
<%
	Wri_likeVO wri_likeVO = (Wri_likeVO) request.getAttribute("wri_likeVO");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增文章留言 - addWriting_like.jsp</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

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

#img {
	display: ${empty writingVO.wrt_photo_base64?"none":""
}

;
width { 200 px;
	
}

div {
	float: left;
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
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>點讚畫面 - addWriting_like.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/writing_like/select_page.jsp"><img
						src="images/tomcat.png" width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>點讚:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM METHOD="post" ACTION="writing_like.do" name="form1">



		<table>
		
			<tr>
				<td>文章編號 :</td>
				<td colspan="2"><input type="text" name="wrt_no" size="45"
					maxLength="30"
					value="<%=(wri_likeVO == null) ? "" : wri_likeVO.getWrt_no()%>" /></td>
			</tr>
			<tr>
				<td>文章留言狀態 :</td>
				<td colspan="2"><input type="text" name="wmsg_stat" size="45"
					maxLength="30"
					value="<%=(wri_likeVO == null) ? "" : wri_likeVO.getMem_no()%>" /></td>
			</tr>
			
			
		</table>

		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">
	</FORM>
</body>
</html>