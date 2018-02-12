<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.writing.model.*"%>
<%@ page import="java.util.*"%>
<%-- 商家文章首頁 --%>

<%@ include file="/front-end/HtmlHeadPage.jsp"%>

<%
	String str = request.getParameter("wrt_select");
	System.out.println("來自request.getParameter('wrt_select')= " + str);

	List<WritingVO> list = (List<WritingVO>) session.getAttribute("writingVOList");

	if (str == null) {

	}

	else if (str.equals("getAll")) {

		Collections.sort(list, new Comparator<WritingVO>() {

			@Override
			public int compare(WritingVO o1, WritingVO o2) {
				int result = o1.getWrt_time().compareTo(o2.getWrt_time());
				return result;
			}
		});

	}

	else if (str.equals("getAllReverse")) {

		Collections.sort(list, new Comparator<WritingVO>() {

			@Override
			public int compare(WritingVO o1, WritingVO o2) {
				int result = o2.getWrt_time().compareTo(o1.getWrt_time());
				return result;
			}
		});

	}

	pageContext.setAttribute("list", list);
%>



<style type="text/css">
.nowrap {
	white-space: nowrap;
}

.wrt_title {
	margin-top: 0px;
}

.writing-item {
	margin-top: 20px;
}

.pagination>li>a {
	font: 20px bold;
	color: #fe8c0a;
}

.wrt_time {
	font-size: 20px;
	margin-left: 100px;
}

.wrt_tr {
	font-size: 20px;
	font-weight: 600px;
	padding-left: 970px;
}
</style>
<%@ include file="/front-end/HeaderPage.jsp"%>


<div class="container search_bar">
	<!-- 大container開始 -->
	<div class="row">
		<!-- row開始 -->
		<div class="col-xs-12 col-sm-6" style="margin-left: 530px;">
			<!-- 搜尋開始 -->
			<form name="formPattern" method="post" action="<%=request.getContextPath()%>/front-end/writing/writing.do">
				<div class="input-group searchbar">
					<input type="text" name="KeyWordSearch_From_Shopper" class="form-control" placeholder="請輸入關鍵字" style="z-index: 0;">
					<input type="hidden" name="action" value="Search_For_KeyWord_From_Shopper">
					<span class="input-group-btn">
						<button class="btn btn-success" style="z-index: 0; border: 0px; height: 32px;" type="button" onClick="document.formPattern.submit()">搜尋</button>
					</span>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 搜尋結束 -->

<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-3">
			<!-- 漢堡開始 -->
			<!-- 左欄漢堡開始 -->
			<div class="col-xs-12 col-sm-12">
				<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					<!-- 區塊1 -->
					<div class="panel panel-default nowrap">
						<div class="panel-heading" role="tab" id="panel2">
							<h4 class="panel-title">
								<a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="aaa">
									<h3>所有文章</h3>
								</a>
							</h4>
						</div>
						<div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
							<div class="panel-body">
								<ul class="list-unstyled">
									<li><a href="#"><b>商家資料查詢</b></a></li>
									<li>
										<a href="">
											<b>商家文章修改</b>
										</a>
									</li>
								</ul>
							</div>
						</div>
						<!-- 區塊1結束 -->
					</div>
					<!-- 區塊2 -->
					<div class="panel panel-default nowrap">
						<div class="panel-heading" role="tab" id="panel2">
							<h4 class="panel-title">
								<a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
									<h3>我的文章</h3>
								</a>
							</h4>
						</div>
						<div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
							<div class="panel-body">
								<ul class="list-unstyled">
								
									<li>
										<a href="#">
											<b></b>
										</a>
									</li>
									<li>
										<a href="#">
											<b></b>
										</a>
									</li>
									<li>
										<a href="#">
											<b></b>
										</a>
									</li>
								</ul>
							</div>
						</div>
						<!-- 區塊2結束 -->
					</div>
					<!-- 區塊3 -->
					<div class="panel panel-default nowrap">
						<div class="panel-heading" role="tab" id="panel3">
							<h4 class="panel-title">
								<a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
									<h3><a href='addWriting.jsp'>新增文章</a></h3>
								</a>
							</h4>
						</div>
						<div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
							<div class="panel-body">

								<ul class="list-unstyled">
									<li>
										<a href="#">
											<b>商品出貨狀態</b>
										</a>
									</li>
									<li>
										<a href="#">
											<b>款項狀態</b>
										</a>
									</li>
								</ul>

							</div>
						</div>
					</div>
					<!-- 區塊3結束 -->
					<!-- 區塊4 -->
					<div class="panel panel-default nowrap">
						<div class="panel-heading" role="tab" id="panel3">
							<h4 class="panel-title">
								<a href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed " aria-expanded="false" aria-controls="ddd">
									<h3>文章分類</h3>
								</a>
							</h4>
						</div>
						<div id="ddd" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel3">
							<div class="panel-body">
									<ul class="list-unstyled">
									
									<li>
										<a href="#">
											<b>柳橙(1)</b>
										</a>
									</li>
									
									<li>
										<a href="#">
											<b>蘋果(1)</b>
										</a>
									</li>
									
										
									<li>
										<a href="#">
											<b>香蕉(1)</b>
										</a>
									</li>
									
									<li>
										<a href="#">
											<b>番石榴(1)</b>
										</a>
									</li>
									
									<li>
										<a href="#">
											<b>茂谷柑(1)</b>
										</a>
									</li>
									
									<li>
										<a href="#">
											<b>番茄(1)</b>
										</a>
									</li>
									
								
								</ul>
							
							</div>
						
						</div>
					</div>
					<!-- 區塊4結束 -->
				</div>
			</div>
			<!-- 左欄漢堡結束 -->
		</div>
		<!-- 漢堡結束 -->


		<div class="col-xs-12 col-sm-9">
			<!-- 圖文開始 -->

			<%-- 			<div class="col-xs-12 col-sm-6 col-sm-offset-3" style="margin-top: 30px;"><%@ include file="pages/page1.file"%></div> --%>
			<!-- 			<div class="col-xs-12 col-sm-6 col-sm-offset-3" style="margin-top: 30px;"></div> -->
			<%-- 			<c:forEach var="writingVO" items="${list}" varStatus="status" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
			<c:forEach var="writingVO" items="${list}" varStatus="status">
				<c:if test="${writingVO.shop_no == loginShopVO.shop_no}">
					<!-- 要只看到自已的文章就把IF放在這裡 -->

					<div class="col-xs-12 col-sm-12" style="margin-top: 30px;">
						<div class="col-xs-12 col-sm-5" style="margin-top: 30px;">
							<img class="img-circle" style="width: 250px;" src="data:image/jpeg;base64,${writingVO.wrt_photo_base64}">
						</div>

						<div class="col-xs-12 col-sm-7" style="margin-top: 30px;">
							<c:set var="wrt_cont" value="${writingVO.wrt_cont}" scope="page" />

							<%
								String wrt_cont2 = String.valueOf(pageContext.getAttribute("wrt_cont"));
										int length = wrt_cont2.length();
										String part = "";
										if (length < 200) {
											part = wrt_cont2;
										} else {
											part = wrt_cont2.substring(0, 200);
										}
							%>
							<div class="col-xs-12 col-sm-12">
								<FORM name="${writingVO.wrt_no}" METHOD="post" ACTION="writing.do">
									<h3 id="wrt_head">${writingVO.wrt_title}</h3>
									<h4 id="wrt_time">${writingVO.wrt_time}</h4>
									<%=part%>
									<input type="hidden" name="action" value="getOne_For_Display_From_Shopper">
									<input type="hidden" name="wrt_no" value="${writingVO.wrt_no}">
									<a href="javascript:document.${writingVO.wrt_no}.submit()">...(閱讀全文)</a>
								</FORM>
							</div>
							<div class="col-xs-12 col-sm-12">
								<%-- 						<c:if test="${writingVO.shop_no == loginShopVO.shop_no}"> <!--要顯示修改按鈕就把IF放這裡-->--%>
								<FORM METHOD="post" ACTION="writing.do" style="margin-bottom: 0px;">
									<input type="submit" value="修改文章內容" class="btn btn-primary">
									<input type="hidden" name="wrt_no" value="${writingVO.wrt_no}">
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>
<!-- ROW圖文結束 -->


<%-- <div class="col-xs-12 col-sm-12 text-center" style="margin-top: 30px;"><%@ include file="pages/page2.file"%></div> --%>

</div>


<script type="text/javascript">
	$(document).ready(function() {
		$(".carousel").carousel({
			interval : 4000,
			pause : false
		});
	});
</script>
<%@ include file="/front-end/FooterPage.jsp"%>
