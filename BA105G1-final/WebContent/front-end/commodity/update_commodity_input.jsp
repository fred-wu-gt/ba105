<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>

    
<%  CommodityVO comVO = (CommodityVO) request.getAttribute("comVO");%>

<%= (comVO==null)%>


    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料修改- update_commodity_input</title>

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
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>商品資料修改- update_commodity_input</h3>
				<h4>
					<a href="select_page.jsp"><img src="" width="100" height="32"
						border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


<FORM METHOD="post" ACTION="commodity.do" name="form1" enctype="multipart/form-data">
<!-- enctype="multipart/form-data" 要放進FORM的屬性   -->

<!-- 
用EL取法練習
 -->
<table>
	<tr>
		<td>商品編號:<font color=red></font></td>
		<td>${comVO.com_no }</td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="com_name" size="45"  maxlength="100" value="<%=comVO.getCom_name()%>" /></td>
	</tr>
	<tr>
		<td>商家編號:</td>
		<td>${comVO.shop_no}</td>
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
		<td>單價:</td>
		<td><input type="TEXT" name="com_price" size="45" maxlength="10"	value="<%=comVO.getCom_price()%>" /></td>
	</tr>
	<tr>
		<td>規格:</td>
		<td><input type="TEXT" name="com_weight" size="45" maxlength="30" value="<%=comVO.getCom_weight()%>" /></td>
	</tr>
	<tr>
		<td>商品敘述:</td>
<%-- 		<td><input type="TEXT" name="com_remarks" size="45"	value="<%=comVO.getCom_remarks()%>" /></td> --%>
		<td><textarea rows="5" cols="50" name="com_remarks" ><%=comVO.getCom_remarks()%></textarea>
		</td>
		
	</tr>
	<tr>
		<td>商品照片1:</td>
		<td><input type="file" name="com_pic1" size="45"	value="<%=comVO.getCom_pic1()%>" /></td>		
	</tr>
	<tr>
		<td>商品照片2:</td>
		<td><input type="file" name="com_pic2" size="45"	value="<%=comVO.getCom_pic2()%>" /></td>
	</tr>
	<tr>
		<td>商品照片3:</td>
		<td><input type="file" name="com_pic3" size="45"	value="<%=comVO.getCom_pic3()%>" /></td>
	</tr>
	<tr>
		<td>上次修改時間</td>
		<td><%=comVO.getCom_time()%></td>
	</tr>
	<tr>
		<td>上/下架狀態:</td>
			<td><select size="1" name="com_status">
					<option value="下架">下架
					<option value="上架">上架
			</select></td>
	</tr>
	<tr>
		<td>庫存數量:</td>
		<td><input type="TEXT" name="com_store" size="45"	value="<%=comVO.getCom_store()%>" /></td>
	</tr>
	<tr>
		<td>商品評分:</td>
		<td>${comVO.com_score }</td>
	</tr>
	
	<tr>
		<td>評分人數:</td>
		<td>${comVO.com_peo }</td>
	</tr>
	
	
	
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="com_no" value="<%=comVO.getCom_no()%>">
<input type="hidden" name="shop_no" value="<%=comVO.getShop_no()%>">
<input type="hidden" name="com_score" value="<%=comVO.getCom_score()%>">
<input type="hidden" name="com_peo" value="<%=comVO.getCom_peo()%>">
<input type="hidden" name="com_time" value="<%=comVO.getCom_time()%>">
<input type="submit" value="送出修改">
</FORM>






</body>
</html>