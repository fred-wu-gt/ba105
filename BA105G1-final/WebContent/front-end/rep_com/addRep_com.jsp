<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rep_com.model.*"%>

<% int PP = 0 ;  %>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
Rep_comVO rep_comVO = (Rep_comVO) request.getAttribute("rep_comVO");
%>


rep_comVO == null? <%=rep_comVO == null %>

<title>�ӫ~���|�s�W-addOrd_ma.jsp</title>

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

	<table id="table-1">
		<tr>
			<td>
				<h3>�ӫ~���|�s�W-addOrd_mas.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/rep_com/select_page.jsp"><img
						src="" width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>��Ʒs�W:</h3>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form action="rep_com.do" method="post" name="form1">
		<table>
<!-- 			<tr> -->
<!-- 				<td>�ӫ~���|�s��</td> -->
<!-- 				<td><input type="text" name="rc_no" size="100" maxlength="12" -->
<%-- 					value="<%=(rep_comVO == null) ? "RC00000011" : rep_comVO.getRc_no()%>"></td> --%>
<!-- 			</tr> -->

			<tr>
				<td>�ӫ~�s��</td>
				<td><input type="text" name="com_no" size="100" maxlength="10"
					value="<%=(rep_comVO == null) ? "COM0000009" : rep_comVO.getCom_no()%>"></td>
			</tr>


			<tr>
				<td>�@��|���s��</td>
				<td><input type="text" name="mem_no" size="100" maxlength="10"
					value="<%=(rep_comVO == null) ? "MEM0000008" : rep_comVO.getMem_no()%>"></td>
			</tr>

			<tr>
				<td>���|��]</td>
				<td><select size="1" name="rc_cuz">
						<option value="<%=(rep_comVO == null? "�п��":rep_comVO.getRc_stat()) %>">�п��
						<option value="�H�W���~">�H�W���~
						<option value="�ӫ~���e�P�W�٤���">�ӫ~���e�P�W�٤���
						<option value="�ڹ�ӫ~���e�αԭz�P�줣�A">�ڹ�ӫ~���e�αԭz�P�줣�A
						<option value="�ӫ~���e�H�ϵ��}���U">�ӫ~���e�H�ϵ��}���U
				</select></td>			
			</tr>

			<tr>
				<td>�ӫ~���|���A</td>
				<td><input type="text" name="rc_stat" size="100" maxlength="10"
					value="<%=(rep_comVO == null) ? "�ݼf��" : rep_comVO.getRc_stat()%>">
				</td>
			</tr>

			<tr>
				<td>���|���e</td>
				<td><textarea rows="5" cols="50" name="rc_txt" ><%=(rep_comVO == null)?"" : rep_comVO.getRc_txt()%></textarea>
				</td>

			</tr>
			
		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�e�X�s�W">
	</form>

</body>
</html>