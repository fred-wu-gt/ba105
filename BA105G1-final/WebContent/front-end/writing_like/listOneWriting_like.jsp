<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_like.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	Wri_likeVO wri_likeVO = (Wri_likeVO) request.getAttribute("wri_likeVO");//Wri_likeServlet.java(Controller),存入req的wri_likeVO物件
%>

<%
	if (wri_likeVO == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/writing_like/select_page.jsp");
		System.out.println("inside sendRedirect");//若wri_likeVO為空值，則重導回到首頁
	}
%>>
<html>
<head>

<title>單筆文章按讚資料 - listOneWriting_like.jsp</title>
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
<body bgcolor='white'>
	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>單筆文章按讚資料 - listOneWriting_like.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>


	<table>
		<tr>
			<th>文章編號</th>
			<th>會員編號</th>



		</tr>

		<tr>
			<th><%=wri_likeVO.getWrt_no()%></th>
			<th><%=wri_likeVO.getMem_no()%></th>

		</tr>

	</table>


</body>
</html>