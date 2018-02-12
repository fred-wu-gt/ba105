<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
   MemberVO memVO1 = (MemberVO) request.getAttribute("memVO1"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>會員註冊驗證</title>

</head>
<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>

<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>


<div class="container">
		<div class="row">
			<div class="col-sm-12 col-sm-6 col-sm-offset-3 ">
				<div  class="col-sm-6 col-sm-offset-3">
					<h3>會員圖片</h3>
					<p><img  width="100" src="data:image/jpeg;base64,${memVO1.mem_photo_base64}"></p>
					<h3>會員:${memVO1.mem_name}，您好</h3>
					<h3>帳號:${memVO1.mem_id}</h3>
				</div>
				<div  class="col-sm-6 col-sm-offset-3">
					
					<h3>
						<p>請前往您的信箱，收取認證信，並在下方輸入信件中的認證碼</p>
						
					</h3>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/register/RegisterHandler" name="form1" >
					<input type="hidden" name="mem_photo" value="${memVO1.mem_photo}">
					<input type="hidden" name="mem_name" value="${memVO1.mem_name}">
					 <input type="hidden" name="action" value="memberverify">
                      <input type="text" name="check" id="check" size="10" maxlength="10" value="" />
						<br>
						<br>
						<input type="submit" class=" btn btn-lg btn-success" value="確認驗證碼"> 
					</FORM>	
				</div>
			</div>
		</div>
	</div>

<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>
