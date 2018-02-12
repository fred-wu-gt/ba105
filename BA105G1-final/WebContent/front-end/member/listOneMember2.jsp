<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  //MemberVO memVO = (MemberVO) request.getAttribute("memVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
   MemberVO memVO = (MemberVO)session.getAttribute("loginMemberVO");
   request.setAttribute("memVO", memVO);
%>

<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>
<head>
<title>會員資料 - listOneMember.jsp</title>

<style>
  #listAllManager_div table,#listAllManager_div th,#listAllManager_div td {
    border: 1px outset #652825;
    padding:2px 5px;
    text-align: center;
    word-break: keep-all;white-space:nowrap;
  }
  #listAllManager_div th{
	border-style:outset;
	background-image: linear-gradient(to top, white,#ecfeff,#d9fafd);
  }
  #listAllManager_div img{
  	width:100px;
  }
	#listAllManager_div .input-group-addon{
		background-image: radial-gradient(ellipse,white,#FFE8E8);
	}
	
</style>

<style>

</style>
</head>
<%@ include file="/front-end/member/HeaderPagemember .jsp" %> <%-- ~~必須include2/3~~ --%>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>



	<div class="row" id="listAllManager_div">
		<div class="col-xs-12 col-sm-3"></div>
		<div class="col-xs-12 col-sm-6">
			<table class="table table-hover">
				<caption>查詢會員表格</caption>

				<tr>
					<th>會員照片</th>
					<!-- 						<th>會員帳號</th> -->
					<!-- 						<th>會員密碼</th> -->
					<th>會員姓名</th>
					<th>會員性別</th>
					<th>會員生日</th>
					<th>會員電郵</th>
					<th>會員電話</th>
					<th>會員地址</th>

				</tr>

				<tr>
					<td Width="200"><img width="100"
						src="data:image/jpeg;base64,${memVO.mem_photo_base64}"></td>
					<%-- 						<td>${memVO.mem_id}</td> --%>
					<%-- 						<td>${memVO.mem_psw}</td> --%>
					<td>${memVO.mem_name}</td>
					<td>${memVO.mem_gen}&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>${memVO.mem_bir}</td>
					<td>${memVO.mem_email}</td>
					<td>${memVO.mem_phone}</td>
					<td Width="200">${memVO.mem_loc}</td>

				</tr>

				
			</table>
			<input type="button" value="修改會員資料"
				onclick="location.href='<%=request.getContextPath()%>/front-end/member/update_Member_input2.jsp';">
		</div>
		<div class="col-xs-12 col-sm-3"></div>

	</div>





<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>會員資料 - ListOneMember.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>一般會員編號</th> -->
<!-- 		<th>一般會員帳號</th> -->
<!-- 		<th>一般會員密碼</th> -->
<!-- 		<th>一般會員姓名</th> -->
<!-- 		<th>一般會員性別</th> -->
<!-- 		<th>一般會員生日</th> -->
<!-- 		<th>一般會員電子郵件</th> -->
<!-- 		<th>一般會員電話</th> -->
<!-- 		<th>一般會員地址</th> -->
<!-- 		<th>一般會員照片</th> -->
<!-- 		<th>一般會員狀態</th> -->
<!-- 		<th>一般會員違規記點</th> -->
<!-- 		<th>一般會員點數</th> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td><%=memVO.getMem_no()%></td> --%>
<%-- 		<td><%=memVO.getMem_id()%></td> --%>
<%-- 		<td><%=memVO.getMem_psw()%></td> --%>
<%-- 		<td><%=memVO.getMem_name()%></td> --%>
<%-- 		<td><%=memVO.getMem_gen()%></td> --%>
<%-- 		<td><%=memVO.getMem_bir()%></td> --%>
<%-- 		<td><%=memVO.getMem_email()%></td> --%>
<%-- 		<td><%=memVO.getMem_phone()%></td> --%>
<%-- 		<td><%=memVO.getMem_loc()%></td> --%>
<%-- 		<td><img  width="100" src="data:image/jpeg;base64,${memVO.mem_photo_base64}"></td> --%>
<%-- 		<td><%=memVO.getMem_stat()%></td> --%>
<%-- 		<td><%=memVO.getMem_poin()%></td> --%>
<%-- 		<td><%=memVO.getMem_val()%></td> --%>
<!-- 	</tr> -->
<!-- </table> -->

<br>
<br>
<br>
<br>
<br>
<script>

		$(".list-group a").hover(function(){
		    $(this).css("background-color", "#BAF09A");
		    }, function(){
		    $(this).css("background-color", "#FFFFFF");
		});



</script>
<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>
