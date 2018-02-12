<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_rep.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	Wri_repVO wri_repVO = (Wri_repVO) request.getAttribute("wri_repVO");//Wri_repServlet.java(Controller),存入req的wri_repVO物件
%>

<%
	if (wri_repVO == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/writing_rep/select_page.jsp");
		System.out.println("inside sendRedirect");//若wri_mesVO為空值，則重導回到首頁
	}
%>>
<html>

<%@ include file="/front-end/HtmlHeadPage.jsp" %>
<head>

<title>單筆文章檢舉資料 - listOneWriting_rep.jsp</title>
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
<%@ include file="/front-end/HeaderPage.jsp" %>
<body bgcolor='white'>
	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>單筆文章檢舉資料 - listOneWriting_rep.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>


	<table>
		<tr>
			<th>文章檢舉編號</th>
			<th>文章編號</th>
			<th>會員編號</th>
			<th>檢舉原因</th>
			<th>文章檢舉內文</th>			
			<th>文章檢舉狀態</th>
			

		</tr>

		<tr>
			<th><%=wri_repVO.getWre_no()%></th>
			<th><%=wri_repVO.getWrt_no()%></th>
			<th><%=wri_repVO.getMem_no()%></th>
			<th><%=wri_repVO.getWre_rsn()%></th>
			<th><%=wri_repVO.getWre_cont()%></th>
			<th><%=wri_repVO.getWre_stat()%></th>
		</tr>

	</table>

<%@ include file="/front-end/FooterPage.jsp" %>
</body>
</html>