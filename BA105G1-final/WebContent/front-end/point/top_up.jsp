<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.* ,com.commodity.model.*,com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
MemberService memSvc = new MemberService();
MemberVO memVO = (MemberVO)session.getAttribute("loginMemberVO");
memVO = memSvc.getOneMember(memVO.getMem_id());
%>

<%-- 	<c:if test="${not empty loginMemberVO}"> --%>
<%-- 		${loginMemberVO.mem_name} 您目前的點數:為 <b><%=memVO.getMem_val() %></b>點<br> --%>
<%-- 	</c:if> --%>




<!DOCTYPE html >
<html>
<%@ include file="/front-end/HtmlHeadPage.jsp"%>
<%--~~必須include1/3~~ --%>

<%@ include file="/front-end/HeaderPage.jsp"%>
<%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>



<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-4">
						<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
						<h4>親愛的 <span >${memVO.mem_name}</span></span></h4>
						<h3>您目前有<span class="point" style="color:#28a418"><%=memVO.getMem_val() %></span>點 <i class="fa fa-money"></i></h3>
						</div>
					</div>
				</div>

				<div class="container">
					<div class="row">
						<!-- 輸入格 -->
						<div class="col-xs-12 col-sm-4">
							<div class="card-container">
								<div class="form-container">
									

									<form id="test" action="">
										<label for="number">信用卡/Debit卡號碼</label>
										<input placeholder="XXXX  XXXX  XXXX  XXXX" type="text" name="number">
										<label for="name">持卡者姓名</label>
										<input placeholder="Full Name" type="text" name="name"><br>

											<div class="cardForm-Field50">
												<label for="expiry">有效期至</label><br>
												<input placeholder="MM/YY" type="text" name="expiry" class="secondRow" >
											</div>
											<div class="cardForm-Field50">
												<label for="cvc">CVC/CVV代碼</label><br>
												<input placeholder="XXX" type="text" name="cvc" class="secondRow incorrectInfo" maxlength="3">
											</div>
									</form>
											

											<!-- Button Default -->
										<form action="<%=request.getContextPath()%>/front-end/point/point.do" method="post" name="form1">
											<label>請輸入要的儲值點數</label><br>
											<input type="text" name="buy_value" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="8" >
											<input type="hidden" name="mem_id" value="${memVO.mem_id}">
											<input type="hidden" name="action" value="BUYPOINTS">
											<input type="submit" value="付款儲值" class="button CardDefault">
										</form>
									</div>
								</div>

						</div>
						<!-- 卡片 -->
						<div class="col-xs-12 col-sm-8">
							<div class="card-wrapper"></div>
					
							

						</div>
					</div>
				</div>
							<!-- 信用卡CSS -->
							<script src="<%=request.getContextPath() %>/front-end/point/card_js/index.js"></script>
							<link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/point/card_css/style.css"/>   
							<script src="https://use.fontawesome.com/f33d2f4ae3.js"></script>


<%-- ~~body結束:無需寫body或html標籤~~ --%>
<%@ include file="/front-end/FooterPage.jsp"%>
<%-- ~~必須include3/3~~ --%>
<script>
//   從/point/card_js/index.js  2113~2115行剪過來
//並將信用卡的form 註冊id
new Card({
	   form: document.querySelector('#test'),
	   container: '.card-wrapper'
	});


</script>
</html>