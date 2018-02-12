<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.fav_com.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%CommodityVO comVO=(CommodityVO)request.getAttribute("comVO"); %> 

<%
     Fav_comService favcomSvc = new Fav_comService();
     List<Fav_comVO> list = favcomSvc.getAll();
     pageContext.setAttribute("list",list);
    
 %> 
<jsp:useBean id="comSvc" scope="page" class="com.commodity.model.CommodityService" />

<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料 - listAllMember.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

 <jsp:useBean id="commoditySvc" scope="page" class="com.commodity.model.CommodityService" />
<table>
	<tr>
		<th>一般會員編號</th>
		<th>商品編號</th>
		<th>商品照片</th>
		<th>商品名稱</th>
		<th>商品價錢</th>
<!-- 		<th>修改</th> -->
		<th>刪除</th>
		
	
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="favComVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>

			<td>${favComVO.mem_no}</td>
			<td>${favComVO.com_no}</td>
<%-- 			<td><a href="<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${comVO.com_no}"><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${favComVO.com_no}&i=1" alt="暫無圖片"height="200" width="300"></a></td> --%>
			<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${favComVO.com_no}&i=1" alt="暫無圖片"height="200" width="300"></td>
			<td>${commoditySvc.getOneCommodityVO(favComVO.com_no).com_name}</td>
		    <td>${commoditySvc.getOneCommodityVO(favComVO.com_no).com_price}</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="img" value="${memVO.mem_photo_base64}"> --%>
<%-- 			     <input type="hidden" name="mem_id"  value="${memVO.mem_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/fav_com/fav_com.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="mem_no"  value="${favComVO.mem_no}">
			     <input type="hidden" name="com_no"  value="${favComVO.com_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>