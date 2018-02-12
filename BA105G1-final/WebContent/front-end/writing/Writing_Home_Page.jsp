<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.writing.model.*"%>
<%@ page import="java.util.*"%>
<%-- 會員文章首頁--%>

<%@ include file="/front-end/HtmlHeadPage.jsp"%>

<%
	String str = request.getParameter("wrt_select");
	System.out.println(str);
	
	WritingService dao = new WritingService();
	List<WritingVO> list = null;
	
	if(request.getAttribute("writingVOList")!=null)
 		list = (List<WritingVO>) request.getAttribute("writingVOList");
	else
		list = dao.getAll();
	 
	if (str == null) {

	}else if (str.equals("getAll")) {

		Collections.sort(list, new Comparator<WritingVO>() {
			
			public int compare(WritingVO o1, WritingVO o2) {
				int result = o1.getWrt_time().compareTo(o2.getWrt_time());
				return result;
			}
			
		});

	}else if (str.equals("getAllReverse")) {

		Collections.sort(list, new Comparator<WritingVO>() {
		
			public int compare(WritingVO o1, WritingVO o2) {
				int result = o2.getWrt_time().compareTo(o1.getWrt_time());
				return result;
			}
			
		});

	}

	pageContext.setAttribute("list", list);
%>


			<style type="text/css">

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


<div id="main-content">
	<!-- 內文開始 -->

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<!-- container查詢、選單開始 -->
	<div style="margin-left: 700px">
		<%@ include file="pages/page1.file"%>
	</div>
	<div class="container">
		<div class="row">


			<div class="col-xs-12 col-sm-12">
				<div class="container">
					<div class="row">

						<div class="col-xs-12 col-sm-3">
							<td class="wrt_td">文章依照時間排序</td>
						</div>

						<div class="col-xs-12 col-sm-9">

							<td>
								<form name="SearchBar_From_Time" method="post"
									action="<%=request.getContextPath()%>/front-end/writing/writing.do">

									<select class="wrt_select" name="wrt_select">
										<option value="getAllReverse" selected>新到舊</option>
										<option value="getAll">舊到新</option>
									</select> <input type="submit" value="查詢"> <input type="hidden"
										name="action" value="Search_From_Time_From_Member"> <input
										type="hidden" name="search" value="sort"> <span
										class="research-btn"> </span>
								</form>
							</td>

							<table>
								<tr>
									<td style="padding-left: 300px">
										<!-- 搜尋表單 -->
										<form name="formPattern" method="post"
											action="<%=request.getContextPath()%>/front-end/writing/writing.do">
											<div class="input-group searchbar">
												<input type="text" name="KeyWordSearch" class="form-control"
													placeholder="請輸入關鍵字" style="z-index: 0;"> 
													<input type="hidden" name="action" value="Search_For_KeyWord_From_Member">
												<span class="input-group-btn">
													<button class="btn btn-success"
														style="z-index: 0; border: 0px; height: 32px;"
														type="button" onClick="document.formPattern.submit()">
														搜尋
													</button>
												</span>
											</div>
										</form>

									</td>
								</tr>
							</table>

						</div>

					</div>
				</div>
			</div>


		</div>
	</div>
	<!-- container查詢、選單結束 -->

	<!-- =========container分隔線==========-->


	<!-- container文章列表開始 -->

	<c:forEach var="writingVO" items="${list}" varStatus="status"
		begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr class="wrt_tr">
			<div>
				<div class="container">
					<div class="row" style="margin-bottom: 20px;">
							<div class="col-xs-12 col-sm-3">
								<td>
								<img width="125px"	src="data:image/jpeg;base64,${writingVO.wrt_photo_base64}">
								</td>
							</div>

						<div class="col-xs-12 col-sm-9">
							<div class="txt">
								<p>
									<td>									
										<c:set var="wrt_cont" value="${writingVO.wrt_cont}" scope="page" />
										<% 
											String wrt_cont2 = String.valueOf(pageContext.getAttribute("wrt_cont")); 
										   	int length = wrt_cont2.length();
										  	String part = "";
										   	if(length < 200){
											   part = wrt_cont2;
										   }else{
											   part = wrt_cont2.substring(0, 200);
										   }
										%>	
										
											<form name="${writingVO.wrt_no}" METHOD="post" ACTION="writing.do" >
											<h2 id="wrt_head">${writingVO.wrt_title}</h2>
											<h3 id="wrt_time">${writingVO.wrt_time}</h3>
											<%= part %>
											<input type="hidden" name="action"	value="getOne_For_Display">
											<input type="hidden" name="wrt_no"  value="${writingVO.wrt_no}">
											<a href="javascript:document.${writingVO.wrt_no}.submit()">...(閱讀全文)</a>
											</form>
									</td>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</tr>
	</c:forEach>

	<div style="margin-left: 850px">
		<%@ include file="pages/page2.file"%>
	</div>

	<!-- container文章列表結束 -->

</div>




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
