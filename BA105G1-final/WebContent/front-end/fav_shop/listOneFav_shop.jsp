<%@ page contentType="text/html; charset=Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%> 採用 Script 的寫法取值 --%>
<%@ page import="java.util.*"%>
<%@ page import="com.fav_shop.model.*"%>

<%-- <% List favshopVO=(List<Fav_shopVO>)request.getAttribute("list"); %>   --%>




<html>
<head>
<title>收藏商品列表 - listOneFav_shop.jsp</title>

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
		 <h3>收藏商品列表  - ListOneFav_shop.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
<jsp:useBean id="ShopSvc" scope="page" class="com.shop.model.ShopService" />
<table>
	<tr>
		<th>一般會員編號</th>
		<th>商家編號</th>
		<th>商品名稱</th>
		<th>商品照片</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="favshopVO" items="${list}">
	<tr>
		<td>${favshopVO.mem_no}</td>
		<td>${favshopVO.shop_no}</td>
		
		<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_name}</td>
<%-- 		<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_loc}</td> --%>
<%-- 		<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?shop_no=${favshopVO.shop_no}" alt="暫無圖片"height="200" width="300"></td> --%>

		<td>
		
		<a href='#modal-id${favshopVO.shop_no}' data-toggle="modal">
		<img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?shop_no=${favshopVO.shop_no}" alt="暫無圖片"height="200" width="300"></a>
		<div class="modal fade" id="modal-id${favshopVO.shop_no}">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						
						<h4 class="modal-title"><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?shop_no=${favshopVO.shop_no}" alt="暫無圖片"height="300" width="300"></h4>
					</div>
					<div class="modal-body">
							<p>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_desc}</p>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">關閉</button>
						
					</div>
				</div>
			</div>
		</div>
		
		</td>
		
		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/fav_shop/fav_shop.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="mem_no"   value="${favshopVO.mem_no}">
			     <input type="hidden" name="shop_no"  value="${favshopVO.shop_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		
		
	</tr>
	
	</c:forEach>
	
	
	
		
	
	
	
	
	
	
</table>
<jsp:include page="/front-end/FooterPage.jsp"></jsp:include>
</body>
</html>