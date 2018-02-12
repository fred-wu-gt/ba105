<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wri_mes_reply.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Wri_mes_replyService wri_mes_replySvc = new Wri_mes_replyService();
	List<Wri_mes_replyVO> list = wri_mes_replySvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>

<%@ include file="/front-end/HtmlHeadPage.jsp" %>

<head>
<title>所有文章留言回覆資料 - listAllWriting_mes_reply.jsp</title>

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

<%@ include file="/front-end/HeaderPage.jsp" %>

<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有文章留言回覆資料 - listAllWriting_mes_reply.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>文章留言回覆編號</th>
			<th>文章留言編號</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="pages/page1.file"%>
		<c:forEach var="wri_mes_replyVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${wri_mes_replyVO.wmsgr_no}</td>
				<td>${wri_mes_replyVO.wmsg_no}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/writing_mes_reply/writing_mes_reply.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="wmsgr_no" value="${wri_mes_replyVO.wmsgr_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/writing_mes_reply/writing_mes_reply.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="wmsgr_no" value="${wri_mes_replyVO.wmsgr_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file"%>
<%@ include file="/front-end/FooterPage.jsp" %>
</body>
</html>