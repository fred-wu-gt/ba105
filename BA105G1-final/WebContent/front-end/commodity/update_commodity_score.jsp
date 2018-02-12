<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>

    
<%  CommodityVO comVO = (CommodityVO) request.getAttribute("comVO");%>

<%= (comVO==null)%>



    
<!DOCTYPE html >
<html>
<%@ include file="/front-end/HtmlHeadPage.jsp"%><%--~~必須include1/3~~ --%>
<head>





</head>
<%-- ~~必須include2/3~~ --%>
<%@ include file="/front-end/HeaderPage.jsp"%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>
	

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
		 <a href="<%=request.getContextPath() %>/front-end/ord_mas/listAllByMemNo.jsp">返回訂單明細</a>	
			<h3>對此商品評分</h3>  
			<FORM METHOD="post" ACTION="commodity.do" name="form1" enctype="multipart/form-data">
<!-- enctype="multipart/form-data" 要放進FORM的屬性   -->

<!-- 
用EL取法練習
 --><table class="table table-hover">
 	<caption style="font-size:35px;"><b>${comVO.com_name }</b></caption>
 	<thead>
 	</thead>
 	<tbody>

 		<tr>
 			<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${comVO.com_no}&i=1" alt="暫無圖片"  width="70"></td>
 			
  		</tr>
  		<tr>
<%--   			<jsp:useBean id="shopSvc" class="com.shop.model.ShopService"/> --%>
			<c:set var="shop_no" value="${comVO.shop_no}"/>
 			<td style="font-size:20px;">商家名稱</td>
 			<td style="font-size:20px;">${shopSvc.findByPrimaryKey(shop_no).getShop_name() }</td>
  		</tr>
  		<tr>
 			<td style="font-size:20px;">商品單價</td>
 			<td style="font-size:20px;">${comVO.com_price }</td>
  		</tr>
  		<tr>
 			<td style="font-size:20px;">商品規格</td>
 			<td style="font-size:20px;">${comVO.com_weight }</td>
  		</tr>
  		<tr>
 			<td style="width:500px ;font-size:20px;">商品評分 </td>
 			<td style="font-size:20px;"><input  name="com_score" value="0" type="text" 
 					class="rating" data-min=0 data-max=5 data-step=0.5 data-size="sm" title="">
 			</td>
 			
  		</tr>
 	</tbody>
 </table>
 <div style="text-align:center;">
	<input type="submit" value="送出修改">
</div>



<br>
<input type="hidden" name="action" value="scoreCommodity">
<input type="hidden" name="com_no" value="<%=comVO.getCom_no()%>">
<input type="hidden" name="com_name" value="<%=comVO.getCom_name()%>">
<input type="hidden" name="shop_no" value="<%=comVO.getShop_no()%>">
<input type="hidden" name="com_price" value="<%=comVO.getCom_price()%>">
<input type="hidden" name="com_weight" value="<%=comVO.getCom_weight()%>">
<input type="hidden" name="com_remarks" value="<%=comVO.getCom_remarks()%>">
<input type="hidden" name="com_peo" value="<%=comVO.getCom_peo()%>">
<!-- <input type="submit" value="送出修改"> -->
</FORM>

			



		</div>
	</div>
</div>


<link rel="stylesheet" href="css/star-rating.css" type="text/css">
<script src="js/star-rating.js"></script>

</html>

<%@ include file="/front-end/FooterPage.jsp"%>