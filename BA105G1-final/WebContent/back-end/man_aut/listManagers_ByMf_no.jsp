<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.man_fun.model.*"%>
<%@ page import="com.manager.model.*"%>

<jsp:useBean id="managerVOList" scope="request" type="java.util.List<ManagerVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="man_funSvc" scope="page" class="com.man_fun.model.Man_funService" />


<html>
<head><title>可使用此功能的管理員 - listManagers_ByMf_no.jsp</title>

<style>
  table#table-2 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-2 h4 {
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
	width: 1250px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-2">
	<tr><td>
		 <h3>可使用此功能的管理員 - listManagers_ByMf_no.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/select_page.jsp"><img src="images/home.png" width="50" border="0">回首頁</a></h4>
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
		<th>編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>姓名</th>
		<th>性別</th>
		<th>手機</th>
		<th>地址</th>
		<th>照片</th>
		<th>狀態</th>
		<th>管理員功能</th>
		<th>修改資料</th>
		<th>撤銷權限</th>
	</tr>
	
	<c:forEach var="managerVO" items="${managerVOList}" >
		<tr>
			<td>${managerVO.man_no}</td>
			<td>${managerVO.man_id}</td>
			<td>${managerVO.man_pas}</td>
			<td>${managerVO.man_name}</td>
			<td>${managerVO.man_gen}</td>
			<td>${managerVO.man_tel}</td>
			<td>${managerVO.man_add}</td>
			<td><img width="100" src="data:image/jpeg;base64,${managerVO.man_pho_base64}"></td>			
			<td>${managerVO.man_sta}</td>
			<td>
	            ${man_funVO.mf_no}<br><font color=blue>【${man_funVO.mf_name}】</font>
			</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/manager/manager.do" style="margin-bottom: 0px;">
			    <input type="submit" value="修改資料"> 
			    <input type="hidden" name="man_no"      value="${managerVO.man_no}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--><!-- 目前尚未用到  -->
			    <input type="hidden" name="action"	   value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/man_aut/man_aut.do" style="margin-bottom: 0px;">
			    <input type="submit" value="撤銷權限">
			    <input type="hidden" name="mf_no"      value="${man_funVO.mf_no}">
			    <input type="hidden" name="man_no"      value="${managerVO.man_no}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="action"     value="deleteAut"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>