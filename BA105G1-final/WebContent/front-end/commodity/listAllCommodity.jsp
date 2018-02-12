<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html >
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.commodity.model.*"%>
<%@ page import="java.util.List"%>

<%
CommodityService comSvc= new CommodityService();
List<CommodityVO> list = comSvc.getAll();
pageContext.setAttribute("list", list);
%>
<jsp:useBean id="fruitSvc" scope="page" class="com.fruit.model.FruitService" />
<%=fruitSvc==null %>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有商品資料-listAllCommodity.jsp</title>

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
		 <h3>所有商品資料-listAllCommodity.jsp</h3>
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
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商家編號</th>
		<th>蔬果種類</th>
		<th>單價</th>
		<th>規格</th>
		<th>商品敘述</th>
		<th>商品照片1</th>
		<th>商品照片2</th>
		<th>商品照片3</th>
		<th>上次修改時間</th>
		<th>上/下架狀態</th>
		<th>庫存數量</th>
		<th>評分平均</th>
		<th>評分人數</th>

		
	</tr>
	<%@ include file="page1.file" %>
<%-- 	<%=request.getContextPath()%>/front-end/commodity --%>
	
	<c:forEach var="comVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		<tr>
			<td>${comVO.com_no}</td>
			<td>${comVO.com_name}</td>
			<td>${comVO.shop_no }</td>
			<td><c:forEach var="fruitVO" items="${fruitSvc.getAll()}">
                    <c:if test="${comVO.fru_no==fruitVO.fru_no}">
	                    	【${fruitVO.fru_name}】
                    </c:if>
                </c:forEach>
			</td>
			<td>${comVO.com_price}</td>
			<td>${comVO.com_weight}</td>
			<td>${comVO.com_remarks}</td>
			<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${comVO.com_no}&i=1"alt="尚無圖片"height="200" width="300"></td>
			<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?com_no=${comVO.com_no}&i=2"alt="尚無圖片"height="200" width="300"></td>
			<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic3.do?com_no=${comVO.com_no}&i=3"alt="尚無圖片"height="200" width="300"></td>
<%-- 			<td><img alt="Embedded Image" src="data:image/png;base64,${com_pic1S}" /></td> --%>
<%-- 			<td><img alt="Embedded Image" src="data:image/png;base64,${com_pic3S}" /></td> --%>
<%-- 			<td><img alt="Embedded Image" src="data:image/png;base64,${com_pic2S}" /></td> --%>
			<td><fmt:formatDate value="${comVO.com_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${comVO.com_status}</td>
			<td>${comVO.com_store}</td>
			<td>${comVO.com_score}</td>
			<td>${comVO.com_peo}</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/commodity/commodity.do" style="margin-bottom: 0px;">
				     <input type="submit" value="修改">
				     <input type="hidden" name="com_no"  value="${comVO.com_no}">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				</FORM>			
			</td>				 	
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/commodity/commodity.do" style="margin-bottom: 0px;">
					     <input type="submit" value="刪除">
					     <input type="hidden" name="empno"  value="${comVO.com_no}">
					     <input type="hidden" name="action" value="delete">
				 </FORM>		
			</td>
		</tr>	
	</c:forEach>
</table>
<%@ include file="page2.file" %>



</body>
</html>