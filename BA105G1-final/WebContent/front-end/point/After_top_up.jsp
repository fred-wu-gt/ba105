<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.* ,com.commodity.model.*,com.member.model.*"%>



${loginMap.get('member').mem_id }
${loginMap.get('member').mem_val }

<%
// MemberService memSvc = new MemberService();
// MemberVO memVO= memSvc.getOneMember("MEM8");
// pageContext.setAttribute("memVO", memVO);

 MemberVO memVO = (MemberVO)session.getAttribute("memVO");
%>



<!DOCTYPE html >
<html>
<%@ include file="/front-end/HtmlHeadPage.jsp"%>
<%--~~必須include1/3~~ --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://use.fontawesome.com/f33d2f4ae3.js"></script>



</head>
<%@ include file="/front-end/HeaderPage.jsp"%>
<%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>

<style>

.
</style>

<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">

			<div style="text-align: center;">
				<h4>
					親愛的 <span style="color: blue">${memVO.mem_id}</span>
				</h4>
				<h3>
					您目前有<span class="point" style="color: red">${memVO.mem_val}</span>點
					<i class="fa fa-money"></i>
				</h3>



				<a href="<%=request.getContextPath()%>/front-end/shopping/listAllCommodityBS.jsp">
					點此開始購物
				 </a>
			</div>
		</div>

	</div>
</div>

<%-- ~~body結束:無需寫body或html標籤~~ --%>
<%@ include file="/front-end/FooterPage.jsp"%>
<%-- ~~必須include3/3~~ --%>
</html>