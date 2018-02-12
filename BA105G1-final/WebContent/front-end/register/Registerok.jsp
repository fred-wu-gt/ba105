<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.member.model.*"%>
<%
     MemberVO memVO1 = (MemberVO) request.getAttribute("memVO1"); //EmpServlet.java(Concroller), 存入req的empVO物件 -->
 %> 



<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



<title>註冊OK</title>

<style type="text/css">
</style>

</head>
<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>

<div class="container">
		<div class="row">
			<div class="col-sm-12 col-sm-6 col-sm-offset-3 ">
				<div  class="col-sm-6 col-sm-offset-3">
					
					<h3>會員圖片</h3>
<%-- 					<p><img  width="100" src="data:image/jpeg;base64,${memVO1.mem_photo_base64}"></p> --%>
				</div>
				<div  class="col-sm-6 col-sm-offset-3">
					
					<h3>
						<p>歡迎加入果書大家庭</p>
						<p>會員:屏東郭富城</p>
					</h3>
					<h4>
						
						<p>
							<a href="<%=request.getContextPath()%>/front-end/index.jsp"><button>進入果書首頁</button></a>
						</p>
					</h4>
				</div>
			</div>
		</div>
	</div>
		
		
		<div class="col-xs-12 col-sm-4"></div>
	
<br>
<br>
<br>
<br>
<br>
<br>
<br>


<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>

