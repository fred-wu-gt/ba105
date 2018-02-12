<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.fav_shop.model.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
     Fav_shopService favshopSvc = new Fav_shopService();
     List<Fav_shopVO> list = favshopSvc.getAll();
     pageContext.setAttribute("list",list);
    
 %> 
<jsp:useBean id="fav_shopSvc" scope="page" class="com.fav_shop.model.Fav_shopService" />

<html>
<head>
<title>�Ҧ��|�����ø�� - listAllFav_shop.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��|�����ø�� - listAllFav_shop.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

 <jsp:useBean id="ShopSvc" scope="page" class="com.shop.model.ShopService" />
<table>
	<tr>
		<th>�@��|���s��</th>
		<th>�Ӯa�s��</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~�Ӥ�</th>
		<th>�R��</th>
		
	
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="favshopVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>

			<td>${favshopVO.mem_no}</td>
			<td>${favshopVO.shop_no}</td>
			<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_name}</td>
<%-- 			<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_loc}</td> --%>
			<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?shop_no=${favshopVO.shop_no}" alt="�ȵL�Ϥ�"height="200" width="300"></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/fav_shop/fav_shop.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="mem_no"   value="${favshopVO.mem_no}">
			     <input type="hidden" name="shop_no"  value="${favshopVO.shop_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>