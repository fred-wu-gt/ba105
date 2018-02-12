<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.fru.pri.model.*"%>

<%@ include file="/front-end/HtmlHeadPage.jsp"%>
<%-- ~~必須include1/3~~ --%>
<head>

<title>查詢成功</title>
</head>
<%@ include file="/front-end/HeaderPage.jsp"%>
<%-- ~~必須include2/3~~ --%>
<jsp:useBean id="searchok" scope="request" type="java.util.List<Fru_priVO>" />

<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-sm-offset=2">
			<table class="table table-hover">

				<caption><h3>本日水果批發價格<h3></caption>
				<thead>
					<tr>
						<th>水果名稱</th>
						<th>水果價錢(新台幣/單位)</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="fav_pri" items="${searchok}">
						<tr>
							<td>${fav_pri.fp_name}</td>
							<td>${fav_pri.fp_pri}(元/公斤)</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<%@ include file="/front-end/FooterPage.jsp"%>
<%-- ~~必須include3/3~~ --%>