<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.member.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  MemberVO memVO = (MemberVO) request.getAttribute("memVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�|����� - listOneMember.jsp</title>

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
	width: 600px;
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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�|����� - ListOneMember.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

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
	</tr>
	<tr>
		<td><%=memVO.getMem_no()%></td>
		<td><%=memVO.getMem_id()%></td>
		<td><%=memVO.getMem_psw()%></td>
		<td><%=memVO.getMem_name()%></td>
		<td><%=memVO.getMem_gen()%></td>
		<td><%=memVO.getMem_bir()%></td>
		<td><%=memVO.getMem_email()%></td>
		<td><%=memVO.getMem_phone()%></td>
		<td><%=memVO.getMem_loc()%></td>
		<td><img  width="100" src="data:image/jpeg;base64,${memVO.mem_photo_base64}"></td>
		<td><%=memVO.getMem_stat()%></td>
		<td><%=memVO.getMem_poin()%></td>
		<td><%=memVO.getMem_val()%></td>
		
		
	</tr>
</table>

</body>
</html>