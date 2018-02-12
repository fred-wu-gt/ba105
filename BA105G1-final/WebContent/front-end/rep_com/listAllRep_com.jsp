<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.rep_com.model.*"%>

<%@ page import="java.util.List"%>

<jsp:useBean id="ComSvc" scope="page" class="com.commodity.model.CommodityService" />
<jsp:useBean id="MemSvc" scope="page" class="com.member.model.MemberService" />

<%
Rep_comService rep_comSvc= new Rep_comService();
List<Rep_comVO> list = rep_comSvc.getAll();
pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有訂單主檔資料-listAllRep_com.jsp</title>

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

<body>
<table id="table-1">
	<tr><td>
		 <h3>所有商品檢舉資料-listAllRep_com.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>商品檢舉編號</th>
		<th>商品名稱</th>
		<th>一般會員編號</th>
		<th>檢舉原因</th>
		<th>商品檢舉狀態</th>
		<th>檢舉內容</th>		
	</tr>
	
	<%@ include file="page/page1.file" %> 
	<c:forEach var="rep_comVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		<tr>
			<td>${rep_comVO.rc_no}</td>
			<td><c:forEach var="comVO" items="${ComSvc.all }"	>
					<c:if test="${rep_comVO.com_no == comVO.com_no }">
						${comVO.com_name }
					</c:if>
				</c:forEach>
			</td>
			
<%-- 			<td>${rep_comVO.com_no}</td> --%>

			<td><c:forEach var="memVO" items="${MemSvc.all }"	>
					<c:if test="${rep_comVO.mem_no == memVO.mem_no }">
						${memVO.mem_id }
					</c:if>
				</c:forEach>
			</td>
			
<%-- 			<td>${rep_comVO.mem_no }</td> --%>
			<td>${rep_comVO.rc_cuz}</td>
			<td>${rep_comVO.rc_stat}</td>
			<td>${rep_comVO.rc_txt}</td>
		
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/rep_com/rep_com.do" style="margin-bottom: 0px;">
				     <input type="submit" value="審核">
				     <input type="hidden" name="rc_no"  value="${rep_comVO.rc_no}">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				</FORM>			
			</td>				 	
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/rep_com/rep_com.do" style="margin-bottom: 0px;">
					     <input type="submit" value="刪除">
					     <input type="hidden" name="empno"  value="${comVO.com_no}">
					     <input type="hidden" name="action" value="delete">
				 </FORM>		
			</td>
		</tr>	
	</c:forEach>
</table>
<%@ include file="page/page2.file" %>



</body>
</html>