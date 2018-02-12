<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>

<%@ page import="com.commodity.model.*"%> 
<%@ page import="com.fav_com.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%CommodityVO comVO=(CommodityVO)request.getAttribute("comVO"); %> 
<% 
		MemberService memSvc = new MemberService();
		MemberVO memVO= (MemberVO)session.getAttribute("loginMemberVO"); 
		MemberVO memVO1 = memSvc.findByMem_no(memVO.getMem_no());
		Fav_comService favComSvc = new Fav_comService();
		List<Fav_comVO> list= favComSvc.findByMem_no(memVO.getMem_no());
		request.setAttribute("list", list);

%>  


<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>
<head>
<title>收藏商品列表 - listOneFav_com.jsp</title>
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
			 <jsp:useBean id="commoditySvc" scope="page" class="com.commodity.model.CommodityService" />
				<table class="table table-hover">
					<caption>收藏商品列表</caption>

					<tr>
						<th>商品照片</th>
						<th>商品名稱</th>
						<th>商品規格</th>
						<th>商品單價</th>
						<th>刪除</th>

					</tr>

					<c:forEach var="favcomVO" items="${list}" > 
					<tr>
					
					<td><a href="<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${favcomVO.com_no}">
		            <img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${favcomVO.com_no}&i=1" alt="暫無圖片"height=auto width="300"></a></td>		
				
<!-- 						<td><img -->
<%-- 							src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${favcomVO.com_no}&i=1" --%>
<!-- 							alt="暫無圖片" height="200" width="300"></td> -->
						<td>${commoditySvc.getOneCommodityVO(favcomVO.com_no).com_name}</td>
						<td>${commoditySvc.getOneCommodityVO(favcomVO.com_no).com_price}</td>
						<td>${commoditySvc.getOneCommodityVO(favcomVO.com_no).com_weight}</td>
						
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/fav_com/fav_com.do"
								style="margin-bottom: 0px;">
								<input type="hidden" name="mem_no" value="${favcomVO.mem_no}">
								<input type="hidden" name="com_no" value="${favcomVO.com_no}">
								<input type="hidden" name="action" value="delete"> <input
									type="submit" value="刪除">
							</FORM>
						</td>

					</tr>

					</c:forEach>
				</table>

			</div>
			<div class="col-xs-12 col-sm-3"></div>
		</div>





<!-- 	<h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>收藏商品列表  - ListOneFav_com.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->
<%--  <jsp:useBean id="commoditySvc" scope="page" class="com.commodity.model.CommodityService" /> --%>

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>一般會員編號</th> -->
<!-- 		<th>商品編號</th> -->
<!-- 		<th>商品照片</th> -->
<!-- 		<th>商品名稱</th> -->
<!-- 		<th>商品價錢</th> -->
<!-- 		<th>刪除</th> -->
		
<!-- 	</tr> -->
<%--  	<c:forEach var="favcomVO" items="${list}" >  --%>
<!-- 	<tr> -->
		
<%-- 		<td>${favcomVO.mem_no}</td> --%>
<%-- 		<td>${favcomVO.com_no}</td> --%>
<%-- <%-- 		<td><a href="<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=${comVO.com_no}"> --%> 
<%-- <%-- 		<img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${favcomVO.com_no}&i=1" alt="暫無圖片"height="200" width="300"></a></td>		 --%> 
<%-- 		<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${favcomVO.com_no}&i=1" alt="暫無圖片"height="200" width="300"></td> --%>
<%-- 		<td>${commoditySvc.getOneCommodityVO(favcomVO.com_no).com_name}</td> --%>
<%-- 		<td>${commoditySvc.getOneCommodityVO(favcomVO.com_no).com_price}</td> --%>
		
		
<!-- 		<td> -->
<%-- 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/fav_com/fav_com.do" style="margin-bottom: 0px;"> --%>
<%-- 		<input type="hidden" name="mem_no"  value="${favcomVO.mem_no}"> --%>
<%-- 		<input type="hidden" name="com_no"  value="${favcomVO.com_no}"> --%>
<!-- 		<input type="hidden" name="action" value="delete"> -->
<!-- 		<input type="submit" value="刪除"> -->
<!-- 		</FORM> -->
<!-- 		</td> -->
		
		
		
		
		
<!-- 	</tr> -->
	
<%-- 	</c:forEach> --%>
	
<!-- </table> -->
<br>
<br>
<br>

<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>