<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ord_mas.model.*"%>

    
<%  Ord_masVO ord_masvo = (Ord_masVO) request.getAttribute("ord_masvo");%>

<%= (ord_masvo==null)%>

<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>

    
<!DOCTYPE html>



<head>
<style>

</style>
</head>

<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>


<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
		
		<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
			
	<FORM METHOD="post" ACTION="ord_mas.do" name="form1">
		<table class="table table-hover">
			<caption><h3>資料修改:</h3></caption>
			<tbody>
			<tr> 
				<tr> 
					<td>訂單主檔編號:<font color=red></font></td>
					<td>${ord_masvo.ord_no }</td>
				</tr>
				<tr>
					<td>商家編號:</td>
					<td>${ord_masvo.shop_no}</td>
				</tr>
				<tr>
					<td>下單時間:</td>
					<td>${ord_masvo.ord_time}</td>
				</tr>
				<tr>
					<td>訂單金額:</td>
					<td>${ord_masvo.ord_total}</td>
				</tr>
				<tr>
					<td>訂單狀態:</td>
					<td>${ord_masvo.ord_sta}</td>
				</tr>
				<tr>
					<td>收貨人姓名:</td>
					<td><input type="text" name="ord_rec" size="45"	value="<%=ord_masvo.getOrd_rec()%>" /></td>						
				</tr>
				<tr>
					<td>收貨人地址:</td>
					<td><input type="text" name="ord_adr" size="45"	value="<%=ord_masvo.getOrd_adr()%>" /></td>		
				</tr>
				<tr>
					<td>收貨人電話:</td>
					<td><input type="text" name="ord_tel" size="45"	value="<%=ord_masvo.getOrd_tel()%>" /></td>
				</tr>	
			</tbody>
		</table>
				
		<input type="hidden" name="action" value="updateRec">
		<input type="hidden" name="ord_no" value="${ord_masvo.ord_no }">
		<input type="hidden" name="mem_no" value="${ord_masvo.mem_no}">
		<input type="hidden" name="shop_no" value="${ord_masvo.shop_no }">
		<input type="hidden" name="ord_time" value="${ord_masvo.ord_time}">
		<input type="hidden" name="ord_total" value="${ord_masvo.ord_total}">
		<input type="hidden" name="ord_sta" value="${ord_masvo.ord_sta}">
		<input type="hidden" name="ord_can_rea" value="${ord_masvo.ord_can_rea}">
		<input type="submit" value="送出修改">
		</FORM>
					
				</div>
			</div>
		</div>
			












	


<!-- <FORM METHOD="post" ACTION="ord_mas.do" name="form1"> -->
<!-- <!-- enctype="multipart/form-data" 要放進FORM的屬性   -->

<!-- <!--  -->
<!-- 用EL取法練習 -->
<!--  --> 
<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<td>訂單主檔編號:<font color=red></font></td> -->
<%-- 		<td>${ord_masvo.ord_no }</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>一般會員編號:</td> -->
<%-- 		<td>${ord_masvo.mem_no}</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>商家編號:</td> -->
<%-- 		<td>${ord_masvo.shop_no}</td> --%>
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td>下單時間:</td> -->
<%-- 		<td>${ord_masvo.ord_time}</td> --%>
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td>訂單金額:</td> -->
<%-- 		<td>${ord_masvo.ord_total}</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>訂單狀態:</td> -->
<%-- 		<td>${ord_masvo.ord_sta}</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>收貨人姓名:</td> -->
<%-- 		<td><input type="text" name="ord_rec" size="45"	value="<%=ord_masvo.getOrd_rec()%>" /></td>		 --%>
		
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>收貨人地址:</td> -->
<%-- 		<td><input type="text" name="ord_adr" size="45"	value="<%=ord_masvo.getOrd_adr()%>" /></td>		 --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>收貨人電話:</td> -->
<%-- 		<td><input type="text" name="ord_tel" size="45"	value="<%=ord_masvo.getOrd_tel()%>" /></td> --%>
<!-- 	</tr> -->

	
	
<!-- </table> -->
<!-- <br> -->
<!-- <input type="hidden" name="action" value="updateRec"> -->
<%-- <input type="hidden" name="ord_no" value="${ord_masvo.ord_no }"> --%>
<%-- <input type="hidden" name="mem_no" value="${ord_masvo.mem_no}"> --%>
<%-- <input type="hidden" name="shop_no" value="${ord_masvo.shop_no }"> --%>
<%-- <input type="hidden" name="ord_time" value="${ord_masvo.ord_time}"> --%>
<%-- <input type="hidden" name="ord_total" value="${ord_masvo.ord_total}"> --%>
<%-- <input type="hidden" name="ord_sta" value="${ord_masvo.ord_sta}"> --%>
<%-- <input type="hidden" name="ord_can_rea" value="${ord_masvo.ord_can_rea}"> --%>
<!-- <input type="submit" value="送出修改"> -->
<!-- </FORM> -->


<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>
<%-- ~~body結束:無需寫body或html標籤~~ --%>