<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rep_com.model.*"%>

  <% int pp = 0; %>
<% Rep_comVO rep_comVO = (Rep_comVO) request.getAttribute("rep_comVO");%>
<%= (rep_comVO==null)%>
<%= rep_comVO.getRc_no() %>
<jsp:useBean id="ComSvc" scope="page" class="com.commodity.model.CommodityService" />
<jsp:useBean id="MemSvc" scope="page" class="com.member.model.MemberService" />

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品檢舉狀態修改- update_rep_com_stat</title>

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
				<h3>商品檢舉狀態修改- update_rep_com_stat</h3>
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


<FORM METHOD="post" ACTION="rep_com.do" name="form1">
<!-- enctype="multipart/form-data" 要放進FORM的屬性   -->

<!-- 
用EL取法練習
 -->
<table>
	<tr>
		<td>商品檢舉編號:<font color=red></font></td>
		<td>${rep_comVO.rc_no }</td>
<%-- 			<td><%= rep_comVO.getRc_no() %></td> --%>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><c:forEach var="comVO" items="${ComSvc.all }"	>
					<c:if test="${rep_comVO.com_no == comVO.com_no }">
						${comVO.com_name }
					</c:if>
				</c:forEach>
			</td>

<%-- 	<td><%= rep_comVO.getCom_no() %></td> --%>
	</tr>
	<tr>
		<td>一般會員編號:</td>
		<td><c:forEach var="memVO" items="${MemSvc.all }"	>
				<c:if test="${rep_comVO.mem_no == memVO.mem_no }">
					${memVO.mem_id }
				</c:if>
			</c:forEach>
		</td>
<%-- 		<td>${rep_comVO.mem_no }</td> --%>
<%-- 		<td><%= rep_comVO.getMem_no() %></td> --%>
	</tr>
	
	<tr>
		<td>檢舉原因:</td>
		<td>${rep_comVO.rc_cuz}</td>	
<%-- 		<td><%= rep_comVO.getRc_cuz() %></td>		 --%>
	</tr>
	
	<tr>
		<td>商品檢舉狀態:</td>
		<td><select size="1" name="rc_stat">
						<option value="<%=(rep_comVO == null? "待審核":rep_comVO.getRc_stat()) %>">待審核						
						<option value="審核通過">審核通過
						<option value="審核不通過">審核不通過
			</select>
		</td>
		
	</tr>
	
	<tr>
		<td>檢舉內容:</td>
		<td>${rep_comVO.rc_txt}</td>
<%-- 		<td><%= rep_comVO.getRc_txt() %></td>	 --%>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="updateRcStat">
<input type="hidden" name="rc_no" value="<%= rep_comVO.getRc_no() %>">
<input type="hidden" name="com_no" value="<%= rep_comVO.getCom_no() %>">
<input type="hidden" name="mem_no" value="<%= rep_comVO.getMem_no() %>">
<input type="hidden" name="rc_cuz" value="<%= rep_comVO.getRc_cuz() %>">
<input type="hidden" name="rc_txt" value="<%= rep_comVO.getRc_txt() %>">
<input type="submit" value="送出修改">
</FORM>


</body>
</html>