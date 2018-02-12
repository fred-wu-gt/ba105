<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ord_mas.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	Ord_masVO ord_masVO = (Ord_masVO) request.getAttribute("ord_masVO");
%>


ord_masVO == null? <%=ord_masVO == null %>

<title>�q��D�ɷs�W-addOrd_ma.jsp</title>

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
				<h3>�q��D�ɷs�W-addOrd_mas.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/Ord_Mas/select_page.jsp"><img
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

	<form action="ord_mas.do" method="post" name="form1">
		<table>
			<tr>
				<td>�q��D�ɽs��</td>
				<td><input type="text" name="ord_no" size="100" maxlength="33"
					value="<%=(ord_masVO == null) ? "2018-01-260000000010" : ord_masVO.getOrd_no()%>"></td>
			</tr>

			<tr>
				<td>�@��|���s��</td>
				<td><input type="text" name="mem_no" size="100" maxlength="10"
					value="<%=(ord_masVO == null) ? "MEM0000005" : ord_masVO.getMem_no()%>"></td>
			</tr>


			<tr>
				<td>�Ӯa�s��</td>
				<td><input type="text" name="shop_no" size="100" maxlength="10"
					value="<%=(ord_masVO == null) ? "SHOP000009" : ord_masVO.getMem_no()%>"></td>
			</tr>

			<tr>
				<td>�q�檬�A</td>
				<td><input type="text" name="ord_sta" size="100" maxlength="10"
					value="<%=(ord_masVO == null) ? "���I��" : ord_masVO.getOrd_sta()%>">
				</td>
			</tr>

			<tr>
				<td>�q����B</td>
				<td><input type="text" name="ord_total" size="100"
					maxlength="10"
					value="<%=(ord_masVO == null) ? "5000" : ord_masVO.getOrd_total()%>">
				</td>
			</tr>

			<tr>
				<td>���f�H�m�W</td>
				<td><input type="text" name="ord_rec" size="100" maxlength="10"
					value="<%=(ord_masVO == null) ? "" : ord_masVO.getOrd_rec()%>">
				</td>
			</tr>
			<tr>
				<td>���f�H�a�}</td>
				<td><input type="text" name="ord_adr" size="100" maxlength="10"
					value="<%=(ord_masVO == null) ? "" : ord_masVO.getOrd_adr()%>">
				</td>
			</tr>
			<tr>
				<td>���f�H�q��</td>
				<td><input type="text" name="ord_tel" size="100" maxlength="10"
					value="<%=(ord_masVO == null) ? "" : ord_masVO.getOrd_tel()%>">
				</td>
			</tr>

		</table>


		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�e�X�s�W">
	</form>

</body>
</html>