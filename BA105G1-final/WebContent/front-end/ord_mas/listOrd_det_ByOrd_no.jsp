<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ord_det.model.*"%>

<%-- <jsp:useBean id="listOrd_det_ByOrd_no" scope="request" type="java.util.Set<Ord_detVO>" /> <!-- ��EL����i�ٲ� --> --%>
<%-- <jsp:useBean id="Ord_masSvc" scope="page" class="com.ord_mas.model.Ord_masService" /> --%>

<jsp:useBean id="ComSvc" scope="page" class="com.commodity.model.CommodityService" />
<html>
<head><title>�q����� - listOrd_det_ByOrd_no.jsp</title>



<style>
  table#table-2 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-2 h4 {
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
<table id="table-2">
	<tr><td>
		 <h3>�q����� - listOrd_det_ByOrd_no.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/Ord_Mas/select_page.jsp"><img src="" width="100" height="32" border="0">�^����</a></h4>
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

<table>
	<tr>
		<th>�q��s��</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~����</th>
		<th>�q�ʼƶq</th>
		<th>�p       �p</th>
		
	</tr>
	
	<c:forEach var="ord_detVO" items="${listOrd_det_ByOrd_no}" >
<%-- 		<tr ${(ord_detVO.ord_no==param.ord_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����--> --%>
			<td>${ord_detVO.ord_no}</td>
			<td><c:forEach var="comVO" items="${ComSvc.all }"	>
					<c:if test="${ord_detVO.com_no == comVO.com_no }">
						${comVO.com_name }
					</c:if>
				</c:forEach>
			</td>
			
			<td><c:forEach var="comVO" items="${ComSvc.all }"	>
					<c:if test="${ord_detVO.com_no == comVO.com_no }">
						${comVO.com_price }
					</c:if>
				</c:forEach>
			</td>
			
			<td>${ord_detVO.od_quan}</td>

			<td>${ord_detVO.od_quan*ComSvc.getOneCommodityVO(ord_detVO.com_no).com_price}</td>

			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Ord_det/ord_det.do" style="margin-bottom: 0px;"> --%>
<!-- 			    <input type="submit" value="�ק�">  -->
<%-- 			    <input type="hidden" name="empno"      value="${empVO.empno}"> --%>
<%-- 			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--><!-- �ثe�|���Ψ�  --> --%>
<!-- 			    <input type="hidden" name="action"	   value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Ord_det/ord_det.do" style="margin-bottom: 0px;"> --%>
<!-- 			    <input type="submit" value="�R��"> -->
<%-- 			    <input type="hidden" name="empno"      value="${empVO.empno}"> --%>
<%-- 			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<!-- 			    <input type="hidden" name="action"     value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>

<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>