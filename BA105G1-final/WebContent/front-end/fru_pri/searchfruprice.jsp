<%@ page language="java" contentType="text/html; charset=UTF-8"%>



<%@ include file="/front-end/HtmlHeadPage.jsp"%>
<%-- ~~必須include1/3~~ --%>
<head>

<title>查詢蔬果價格</title>
<style type="text/css">
.sa_searchbar {
	margin-left: 100px;
}
</style>

</head>
<%@ include file="/front-end/HeaderPage.jsp"%>
<%-- ~~必須include2/3~~ --%>

<!-- 請在此加內文 -->

<div class="col-xs-12 col-sm-7 col-sm-offset-2">
	<div><h3>資料來源:行政院農委會開放資料平台</h3></div>

	<div class="col-xs-12 col-sm-12 text-center"><h3>查詢水果批發價格</h3></div>
	
	<div class="col-xs-12 col-sm-12 text-left">
		<!-- 搜尋表單 -->
		<form METHOD="post" class="navbar-form navbar-right" action="<%=request.getContextPath()%>/front-end/fru_pri/fru_pri.do">
			<div class="input-group sa_searchbar">
				
				<input type="text" class="form-control" placeholder="Search for..." name="fp_name" value="">
				<input type="hidden" name="action" value="listfru_pri">
				<span class="input-group-btn">
				<input class="btn btn-danger form-control" type="submit" value="GO!">
				</span>
			</div>
		</form>
		
	</div>
		<p>身在健康意識覺醒時代，能取得合理水果價格，貨比三家避免買貴。以最合理價格買到最超值商品，使用此查詢功能，<b>查詢水果批發價格</b>，已達聰明消費目的</p>
	    <div><img src="<%=request.getContextPath()%>/front-end/fru_pri/32.jpg"></div>
</div>






<br>
<br>
<br>
<%@ include file="/front-end/FooterPage.jsp"%>
<%-- ~~必須include3/3~~ --%>