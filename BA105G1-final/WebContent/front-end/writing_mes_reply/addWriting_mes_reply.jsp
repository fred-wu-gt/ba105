<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_mes_reply.model.*"%>
<%
	Wri_mes_replyVO wri_mes_replyVO = (Wri_mes_replyVO) request.getAttribute("wri_mes_replyVO");
%>
<html>
<head>

<%@ include file="/front-end/HtmlHeadPage.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增文章留言回覆 - addWriting_mes_reply.jsp</title>
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

<%@ include file="/front-end/HeaderPage.jsp" %>

<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>新增文章留言回覆畫面 - addWriting_mes_reply.jsp</h3>
			</td>
			<td>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/writing_mes_reply/select_page.jsp"><img
						src="images/tomcat.png" width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>送出文章留言回覆:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM METHOD="post" ACTION="writing_mes_reply.do" name="form1">



		<table>
			<tr>
				<td>文章留言回覆編號 :</td>
				<td colspan="2"><input type="text" name="wmsgr_no" size="45"
					maxLength="30"></td>
			</tr>

			<tr>
				<td>文章留言編號 :</td>
				<td colspan="2"><input type="text" name="wmsg_no" size="45"
					maxLength="30"></td>
			</tr>
			<tr>
				<td>商家編號 :</td>
				<td colspan="2"><input type="text" name="shop_no" size="45"
					maxLength="30"
					value="<%=(wri_mes_replyVO == null) ? "" : wri_mes_replyVO.getShop_no()%>" /></td>
			</tr>
			<tr>
				<td>文章留言回覆內容 :</td>
				<td colspan="2"><input type="text" name="wcr_cont" size="45"
					maxLength="30"
					value="<%=(wri_mes_replyVO == null) ? "" : wri_mes_replyVO.getWcr_cont()%>" /></td>
			</tr>
		</table>

		<input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
	<%@ include file="/front-end/FooterPage.jsp" %>
</body>
</html>