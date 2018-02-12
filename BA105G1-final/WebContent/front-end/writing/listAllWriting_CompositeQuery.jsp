<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.writing.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listAllWriting_CompositeQuery" scope="page" type="java.util.List<WritingVO>" />
<jsp:useBean id="writingSvc" scope="page" class="com.writing.model.WritingService" />


<html>
<head><title>複合查詢 - listAll_CompositeQuery.jsp</title>

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
<body bgcolor='white'>

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllWriting_compositeQuery.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/writing/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>文章編號</th>
		<th>文章標題</th>
		<th>文章內容</th>
		
	</tr>
	
	<jsp:useBean id="WritingSvc" scope="request" class="com.writing.model.WritingService" />
	
	<c:forEach var="writingVO" items="${listAllWriting_CompositeQuery}">
		<tr align='center' valign='middle'>
			<td>${writingVO.wrt_no}</td>
			<td>${writingVO.wrt_title}</td>
			<td>${writingVO.wrt_cont}</td>
						
			<td><c:forEach var="writingVO" items="${WritingSvc.all}">
                    <c:if test="${empVO.deptno==deptVO.deptno}">
	                    
                    </c:if>
                </c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>