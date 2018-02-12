<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%  CommodityVO comVO = (CommodityVO)request.getAttribute("comVO");
%>

<%int i =1; %>
<title>商品新增-addCommdity.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>


<body bgcolor='white'>


<%-- <%= (comVO==null)%> 	 --%>

<table id="table-1">
	<tr><td>
		 <h3>商品新增-addCommdity.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front-end/Commodity/select_page.jsp"><img src="" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>
<h3>資料新增:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<form action="commodity.do" method="post" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>商品名稱</td>
				<td><input type="text" name="com_name" size="100"
					maxlength="33"
					value="<%=(comVO == null) ? "" : comVO.getCom_name()%>"></td>
			</tr>
			
			<tr>
				<td>商家編號</td>
				<td><input type="text" name="shop_no" size="100" maxlength="10"
					value="<%=(comVO == null) ? "SHOP000001" : comVO.getShop_no()%>"></td>
			</tr>


			<jsp:useBean id="fruSvc" scope="page" class="com.fruit.model.FruitService" />
			<tr>
				<td>蔬果類型:<font color=red><b>*</b></font></td>
				<td><select size="1" name="fru_no">
						<c:forEach var="fruitVO" items="${fruSvc.all}">
							<option value="${fruitVO.fru_no}"${(comVO.fru_no==fruitVO.fru_no)? 'selected':'' }>${fruitVO.fru_name}
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<td>單價</td>
				<td><input type="text" name="com_price" size="100" maxlength="10" value="<%=(comVO == null) ? "" : comVO.getCom_price()%>"></td>
			</tr>

			<tr>
				<td>規格</td>
				<td><input type="text" name="com_weight" size="100"
					maxlength="10"
					value="<%=(comVO == null) ? "" : comVO.getCom_weight()%>">
				</td>
			</tr>

			<tr>
				<td>商品敘述</td>
				<td><textarea rows="5" cols="50" name="com_remarks" ><%=(comVO == null)?"" : comVO.getCom_remarks()%></textarea>
				</td>
			</tr>

			<tr>
				<td>商品照片1:</td>
				<td><input type="file" name="com_pic1" size="100"
					value="<%=(comVO == null)?"" : comVO.getCom_pic1()%>" /></td>
			</tr>
			<tr>
				<td>商品照片2:</td>
				<td><input type="file" name="com_pic2" size="100"
					value="<%=(comVO == null)?"" : comVO.getCom_pic2()%>" /></td>
			</tr>
			<tr>
				<td>商品照片3:</td>
				<td><input type="file" name="com_pic3" size="100"
					value="<%=(comVO == null)?"" : comVO.getCom_pic3()%>" /></td>
			</tr>

			<tr>
				<td>上/下架狀態:</td>
				<td><select size="1" name="com_status">
						<option value="<%=(comVO == null? "下架":comVO.getCom_status()) %>">下架
						
						<option value="上架">上架
				</select></td>
			</tr>

			<tr>
				<td>庫存數量</td>
				<td><input type="text" name="com_store" size="100"
					maxlength="10"
					value="<%=(comVO == null) ? "" : comVO.getCom_store()%>"></td>
			</tr>

		</table>
		
<!-- 		<br>  -->
<%-- 		<input type="hidden" name="shop_no" value="<%=comVO.getShop_no()%>"> --%>
<%-- 		<input type="hidden" name="com_score" value="<%=comVO.getCom_score()%>"> --%>
<%-- 		<input type="hidden" name="com_peo" value="<%=comVO.getCom_peo()%>"> --%>
<%-- 		<input type="hidden" name="com_time" value="<%=comVO.getCom_time()%>"> --%>
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">
	</form>

</body>
</html>