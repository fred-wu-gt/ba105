<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%> 採用 Script 的寫法取值 --%>
<%@ page import="com.fav_com.model.*"%>
<%@ page import="java.util.*"%>
<%CommodityVO comVO=(CommodityVO)request.getAttribute("comVO"); %> 
<% List fav_comVO=(List<Fav_comVO>)request.getAttribute("list"); %>  


<html>
<head>
<title>收藏商品列表 - listOneFav_com.jsp</title>

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
<jsp:include page="/front-end/HtmlHeadPage.jsp"></jsp:include>
</head>
<body bgcolor='white'>
<jsp:include page="/front-end/HeaderPage.jsp"></jsp:include>
<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>收藏商品列表  - ListOneFav_com.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
 <jsp:useBean id="commoditySvc" scope="page" class="com.commodity.model.CommodityService" />

<table>
	<tr>
		<th>一般會員編號</th>
		<th>商品編號</th>
		<th>商品照片</th>
		<th>商品名稱</th>
		<th>商品價錢</th>
		<th>刪除</th>
		
	</tr>
 	<c:forEach var="favcomVO" items="${list}" > 
	<tr>
		
		<td>${favcomVO.mem_no}</td>
		<td>${favcomVO.com_no}</td>
<%-- 		<td><a href="<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${comVO.com_no}"> --%>
<%-- 		<img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${favcomVO.com_no}&i=1" alt="暫無圖片"height="200" width="300"></a></td>		 --%>
		<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${favcomVO.com_no}&i=1" alt="暫無圖片"height="200" width="300"></td>
		<td>${commoditySvc.getOneCommodityVO(favcomVO.com_no).com_name}</td>
		<td>${commoditySvc.getOneCommodityVO(favcomVO.com_no).com_price}</td>
		
		
		<td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/fav_com/fav_com.do" style="margin-bottom: 0px;">
		<input type="hidden" name="mem_no"  value="${favcomVO.mem_no}">
		<input type="hidden" name="com_no"  value="${favcomVO.com_no}">
		<input type="hidden" name="action" value="delete">
		<input type="submit" value="刪除">
		</FORM>
		</td>
		
		
		
		
		
	</tr>
	
	</c:forEach>
	
</table>
	<jsp:include page="/front-end/FooterPage.jsp"></jsp:include>
</body>
</html>