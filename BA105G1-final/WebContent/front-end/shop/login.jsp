<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.io.*"%>


<%@ include file="/front-end/HtmlHeadPage.jsp" %>

<head>
<title>會員登入</title>
		
<script type="text/javascript">
	
</script>
<style type="text/css">
.reg {
	margin: 10px;
}

.xhr {
	text-align: center;
}
</style>
</head>
	<!-- include header -->
	<%@ include file="/front-end/HeaderPage.jsp" %>
	<!-- include header -->
	
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<div class="panel-default ">
					<div class="panel-heading">
						<h3 class="panel-title text-center" id="title">
							<font size="12">商家登入</font>
						</h3>
			  		</div>
				</div>
				
				
				<form method="post" name="myForm1" action="<%=request.getContextPath()%>/front-end/shop/shop.do">
					<div class="col-sm-12" style="margin-top:40px">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font color='red' id="msgs">
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li>${message}</li>
									</c:forEach>
								</ul>
							</font>
						</c:if>
						<br>
					</div>
					<div class="col-sm-12" style="margin-top:20px">
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-user"></span>
							</span> <input type="text" name="shop_id" id="account"
								class="form-control" onchange='valid()' placeholder="請輸入您的帳號">
						</div>
						<div class="input-group input-group-lg" style="margin-top:20px">
							<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span> 
							<input type="password" name="shop_psw" id="passWord"
								   class="form-control" placeholder="請輸入您的密碼">
						</div>
						<div class="col-sm-12" style="margin-top:40px">
							<input type="submit" name="shop_psw"  class="btn btn-success" value = "登入" style="color:#652825">
							<a href="<%=request.getContextPath()%>/front-end/shop/shop_default.jsp" class="btn btn-success">取消</a>
						</div>
						<input type="hidden" name="action" value="login">
					</div>
				</form>
<!-- 				<div class="col-sm-12 xhr"> -->
<!-- 					<a -->
<%-- 					href="<%=request.getContextPath()%>/Front-End/member/forgetPsw.jsp"  --%>
<!-- 						class="btn btn-info form-control reg">忘記密碼?</a>  -->
<!-- 					<P> -->
<%-- 						<a href="<%=request.getContextPath()%>/Front-End/member/register.jsp" class="btn btn-info form-control reg">點我加入會員</a>  --%>
				</div>
			</div>
		</div>				
	<script>
	$().ready(function(){
		$('#title').click(function(){
			$('#account').val('SHOP05');
			$('#passWord').val('SHOP05');
		});
	});
	</script>
<%@ include file="/front-end/FooterPage.jsp" %>