<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.shop.model.*"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.fav_shop.model.*"%>
<%@ page import="com.member.model.*"%>

<%-- <% List favshopVO=(List<Fav_shopVO>)request.getAttribute("list"); %>   --%>
<% 
	 MemberService memSvc = new MemberService();
     MemberVO memVO= (MemberVO)session.getAttribute("loginMemberVO"); 
     Fav_shopService favShopSvc = new Fav_shopService();
     Fav_shopVO fav_shopVO = new  Fav_shopVO();
     List<Fav_shopVO> list= favShopSvc.findByMem_no(memVO.getMem_no());
     request.setAttribute("list", list);
%>

<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>
<head>
<title>收藏商品列表 - listOneFav_shop.jsp</title>

<style>

</style>

<style>

</style>


</head>
<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


	<div class="container">
		<div class="row">
			
			<div class="col-xs-12 col-sm-12">
				<table class="table table-hover"  >
				
					<caption>收藏商家列表</caption>

					<tr >
<!-- 						<th>一般會員編號</th> -->
<!-- 						<th>商家編號</th> -->
						<th style="text-align:center;">商家名稱</th>
						<th style="text-align:center;">商家照片</th>
						<th style="text-align:center;">商家電話</th>
						<th style="text-align:center;">商家地址</th>
						<th style="text-align:center;">刪除</th>
					</tr>



					
					<c:forEach var="favshopVO" items="${list}">
						<tr>
<%-- 							<td>${favshopVO.mem_no}</td> --%>
<%-- 							<td>${favshopVO.shop_no}</td> --%>
							<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_name}</td>
							<%-- 							<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?shop_no=${favshopVO.shop_no}" alt="暫無圖片"height="200" width="300"></td> --%>

							<td><a href='#modal-id${favshopVO.shop_no}'
								data-toggle="modal"> <img
									src="<%=request.getContextPath() %>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${favshopVO.shop_no}"
									alt="暫無圖片" height="300" width="100"></a>
								<div class="modal fade" id="modal-id${favshopVO.shop_no}">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">

												<h4 class="modal-title">
													<img
														src="<%=request.getContextPath() %>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${favshopVO.shop_no}"
														alt="暫無圖片" height="300" width="300">
												</h4>
											</div>
											<div class="modal-body">
												<p>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_desc}</p>

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-primary"
													data-dismiss="modal">關閉</button>

											</div>
										</div>
									</div>
								</div></td>

							<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_phone}</td>
							<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_loc}</td>
							
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/front-end/fav_shop/fav_shop.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="mem_no" value="${favshopVO.mem_no}"> <input
										type="hidden" name="shop_no" value="${favshopVO.shop_no}">
									<input type="hidden" name="action" value="delete">
								</FORM>
							</td>

						</tr>

					</c:forEach>


				</table>

			</div>
			
		</div>
	</div>


<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>



	<!-- 	<table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>收藏商品列表  - ListOneFav_shop.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->
<%-- <%-- <jsp:useBean id="ShopSvc" scope="page" class="com.shop.model.ShopService" /> --%> 
<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>一般會員編號</th> -->
<!-- 		<th>商家編號</th> -->
<!-- 		<th>商品名稱</th> -->
<!-- 		<th>商品照片</th> -->
<!-- 		<th>刪除</th> -->
<!-- 	</tr> -->
<%-- <%-- 	<c:forEach var="favshopVO" items="${list}"> --%> 
<!-- 	<tr> -->
<%-- 		<td>${favshopVO.mem_no}</td> --%>
<%-- 		<td>${favshopVO.shop_no}</td> --%>
		
<%-- 		<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_name}</td> --%>
<%-- <%-- 		<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_loc}</td> --%> 
<%-- <%-- 		<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?shop_no=${favshopVO.shop_no}" alt="暫無圖片"height="200" width="300"></td> --%> 

<!-- 		<td> -->
		
<%-- 		<a href='#modal-id${favshopVO.shop_no}' data-toggle="modal"> --%>
<%-- 		<img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?shop_no=${favshopVO.shop_no}" alt="暫無圖片"height="200" width="300"></a> --%>
<%-- 		<div class="modal fade" id="modal-id${favshopVO.shop_no}"> --%>
<!-- 			<div class="modal-dialog"> -->
<!-- 				<div class="modal-content"> -->
<!-- 					<div class="modal-header"> -->
						
<%-- 						<h4 class="modal-title"><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?shop_no=${favshopVO.shop_no}" alt="暫無圖片"height="300" width="300"></h4> --%>
<!-- 					</div> -->
<!-- 					<div class="modal-body"> -->
<%-- 							<p>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_desc}</p> --%>

<!-- 					</div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 						<button type="button" class="btn btn-primary" data-dismiss="modal">關閉</button> -->
						
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
		
<!-- 		</td> -->
		
		
<!-- 		<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/fav_shop/fav_shop.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="mem_no"   value="${favshopVO.mem_no}"> --%>
<%-- 			     <input type="hidden" name="shop_no"  value="${favshopVO.shop_no}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		
		
<!-- 	</tr> -->
	
<%-- 	</c:forEach> --%>
	
	
<!-- </table> -->
<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>