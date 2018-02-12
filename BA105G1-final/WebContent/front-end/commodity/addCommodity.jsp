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
<title>�ӫ~�s�W-addCommdity.jsp</title>

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
		 <h3>�ӫ~�s�W-addCommdity.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front-end/Commodity/select_page.jsp"><img src="" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>
<h3>��Ʒs�W:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<form action="commodity.do" method="post" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>�ӫ~�W��</td>
				<td><input type="text" name="com_name" size="100"
					maxlength="33"
					value="<%=(comVO == null) ? "" : comVO.getCom_name()%>"></td>
			</tr>
			
			<tr>
				<td>�Ӯa�s��</td>
				<td><input type="text" name="shop_no" size="100" maxlength="10"
					value="<%=(comVO == null) ? "SHOP000001" : comVO.getShop_no()%>"></td>
			</tr>


			<jsp:useBean id="fruSvc" scope="page" class="com.fruit.model.FruitService" />
			<tr>
				<td>���G����:<font color=red><b>*</b></font></td>
				<td><select size="1" name="fru_no">
						<c:forEach var="fruitVO" items="${fruSvc.all}">
							<option value="${fruitVO.fru_no}"${(comVO.fru_no==fruitVO.fru_no)? 'selected':'' }>${fruitVO.fru_name}
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<td>���</td>
				<td><input type="text" name="com_price" size="100" maxlength="10" value="<%=(comVO == null) ? "" : comVO.getCom_price()%>"></td>
			</tr>

			<tr>
				<td>�W��</td>
				<td><input type="text" name="com_weight" size="100"
					maxlength="10"
					value="<%=(comVO == null) ? "" : comVO.getCom_weight()%>">
				</td>
			</tr>

			<tr>
				<td>�ӫ~�ԭz</td>
				<td><textarea rows="5" cols="50" name="com_remarks" ><%=(comVO == null)?"" : comVO.getCom_remarks()%></textarea>
				</td>
			</tr>

			<tr>
				<td>�ӫ~�Ӥ�1:</td>
				<td><input type="file" name="com_pic1" size="100"
					value="<%=(comVO == null)?"" : comVO.getCom_pic1()%>" /></td>
			</tr>
			<tr>
				<td>�ӫ~�Ӥ�2:</td>
				<td><input type="file" name="com_pic2" size="100"
					value="<%=(comVO == null)?"" : comVO.getCom_pic2()%>" /></td>
			</tr>
			<tr>
				<td>�ӫ~�Ӥ�3:</td>
				<td><input type="file" name="com_pic3" size="100"
					value="<%=(comVO == null)?"" : comVO.getCom_pic3()%>" /></td>
			</tr>

			<tr>
				<td>�W/�U�[���A:</td>
				<td><select size="1" name="com_status">
						<option value="<%=(comVO == null? "�U�[":comVO.getCom_status()) %>">�U�[
						
						<option value="�W�[">�W�[
				</select></td>
			</tr>

			<tr>
				<td>�w�s�ƶq</td>
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
		<input type="submit" value="�e�X�s�W">
	</form>

</body>
</html>