<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.oreilly.servlet.Base64Decoder"%>
<%@ page import="com.oreilly.servlet.Base64Encoder"  %>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    MemberService memSvc = new MemberService();
    List<MemberVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);
    
%>


<html>
<head>
<title>�Ҧ��|����� - listAllMember.jsp</title>

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
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��|����� - listAllMember.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
		<th>�@��|���s��</th>
		<th>�@��|���b��</th>
		<th>�@��|���K�X</th>
		<th>�@��|���m�W</th>
		<th>�@��|���ʧO</th>
		<th>�@��|���ͤ�</th>
		<th>�@��|���q�l�l��</th>
		<th>�@��|���q��</th>
		<th>�@��|���a�}</th>
		<th>�@��|���Ӥ�</th>
		<th>�@��|�����A</th>
		<th>�@��|���H�W�O�I</th>
		<th>�@��|���I��</th>
		<th>�ק�</th>
		<th>�R��</th>
		
		
		
		
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
<%-- 			<%String base64 = Base64Encoder.encode(=memVO.getMem_photo());%> --%>
			<td>${memVO.mem_no}</td>
			<td>${memVO.mem_id}</td>
			<td>${memVO.mem_psw}</td>
			<td>${memVO.mem_name}</td>
			<td>${memVO.mem_gen}</td>
			<td>${memVO.mem_bir}</td> 
			<td>${memVO.mem_email}</td>
			<td>${memVO.mem_phone}</td>
			<td>${memVO.mem_loc}</td>
			<td><img  width="100" src="data:image/jpeg;base64,${memVO.mem_photo_base64}"></td>
			<td>${memVO.mem_stat}</td>
			<td>${memVO.mem_poin}</td>
			<td>${memVO.mem_val}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="img" value="${memVO.mem_photo_base64}">
			     <input type="hidden" name="mem_id"  value="${memVO.mem_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="mem_id"  value="${memVO.mem_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>