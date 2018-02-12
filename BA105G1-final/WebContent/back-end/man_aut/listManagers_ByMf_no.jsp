<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.man_fun.model.*"%>
<%@ page import="com.manager.model.*"%>

<jsp:useBean id="managerVOList" scope="request" type="java.util.List<ManagerVO>" /> <!-- ��EL����i�ٲ� -->
<jsp:useBean id="man_funSvc" scope="page" class="com.man_fun.model.Man_funService" />


<html>
<head><title>�i�ϥΦ��\�઺�޲z�� - listManagers_ByMf_no.jsp</title>

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
	width: 1250px;
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
		 <h3>�i�ϥΦ��\�઺�޲z�� - listManagers_ByMf_no.jsp</h3>
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
		<th>�s��</th>
		<th>�b��</th>
		<th>�K�X</th>
		<th>�m�W</th>
		<th>�ʧO</th>
		<th>���</th>
		<th>�a�}</th>
		<th>�Ӥ�</th>
		<th>���A</th>
		<th>�޲z���\��</th>
		<th>�ק���</th>
		<th>�M�P�v��</th>
	</tr>
	
	<c:forEach var="managerVO" items="${managerVOList}" >
		<tr>
			<td>${managerVO.man_no}</td>
			<td>${managerVO.man_id}</td>
			<td>${managerVO.man_pas}</td>
			<td>${managerVO.man_name}</td>
			<td>${managerVO.man_gen}</td>
			<td>${managerVO.man_tel}</td>
			<td>${managerVO.man_add}</td>
			<td><img width="100" src="data:image/jpeg;base64,${managerVO.man_pho_base64}"></td>			
			<td>${managerVO.man_sta}</td>
			<td>
	            ${man_funVO.mf_no}<br><font color=blue>�i${man_funVO.mf_name}�j</font>
			</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/manager/manager.do" style="margin-bottom: 0px;">
			    <input type="submit" value="�ק���"> 
			    <input type="hidden" name="man_no"      value="${managerVO.man_no}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--><!-- �ثe�|���Ψ�  -->
			    <input type="hidden" name="action"	   value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/man_aut/man_aut.do" style="margin-bottom: 0px;">
			    <input type="submit" value="�M�P�v��">
			    <input type="hidden" name="mf_no"      value="${man_funVO.mf_no}">
			    <input type="hidden" name="man_no"      value="${managerVO.man_no}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="action"     value="deleteAut"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>