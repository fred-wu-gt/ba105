<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.man_fun.model.*"%>

<jsp:useBean id="man_funSvc" scope="page" class="com.man_fun.model.Man_funService" />

<html>
<head><title>�Ҧ��޲z���\��M�v������ - listAllMan_aut.jsp</title>

<style>
  table#table-1 {
	background-color: orange;
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
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align:center;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
  }
  .man_funTd{
  	text-align:left;
  }
</style>

</head>
<body>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��޲z���\��M�v������- ListAllMan_aut.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/select_page.jsp"><img src="images/home.png" width="50" border="0">�^����</a></h4>
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
		<th>�\��s��</th>
		<th>�\��W��</th>
		<th>�\��ԭz</th>
		<th>�ק�\��</th>
		<th>�R���\��</th>
		<th>�i�ϥΦ��\�઺���u</th>
	</tr>
	
	<c:forEach var="man_funVO" items="${man_funSvc.all}">
		<tr>
			<td>${man_funVO.mf_no}</td>
			<td>${man_funVO.mf_name}</td>
			<td class="man_funTd">${man_funVO.mf_des}</td>
			
<!-- 			�קﭶ�����X�R -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/man_aut/man_aut.do" style="margin-bottom: 0px;">
			    <input type="submit" value="�ק�\��"   disabled="disabled"> 
			    <input type="hidden" name="mf_no" value="${man_funVO.mf_no}">
			    <input type="hidden" name="action" value="getOne_For_Update_Man_fun"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/man_aut/man_aut.do" style="margin-bottom: 0px;">
			    <input type="submit" value="�R���\��">
			    <input type="hidden" name="mf_no" value="${man_funVO.mf_no}">
			    <input type="hidden" name="action" value="delete_Man_fun"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/man_aut/man_aut.do" style="margin-bottom: 0px;">
			    <input type="submit" value="�d��"> 
			    <input type="hidden" name="mf_no" value="${man_funVO.mf_no}">
			    <input type="hidden" name="action" value="listManagers_ByMf_no_B"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%if (request.getAttribute("managerVOList")!=null){%>
       <jsp:include page="listManagers_ByMf_no.jsp" />
<%} %>

</body>
</html>