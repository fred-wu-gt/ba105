<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.fav_shop.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.act_like.model.*"%>
<%@ page import="com.wri_like.model.*"%>
<%@ page import="com.writing.model.*"%>
<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>
<% 
	 MemberService memSvc = new MemberService();
     MemberVO memVO= (MemberVO)session.getAttribute("loginMemberVO"); 
     Fav_shopService favShopSvc = new Fav_shopService();
     Fav_shopVO fav_shopVO = new  Fav_shopVO();
     List<Fav_shopVO> list = favShopSvc.findByMem_no(memVO.getMem_no());
     request.setAttribute("list", list);

     
     Act_likeService act_likeSvc = new Act_likeService();
     Act_likeVO act_likeVO = new Act_likeVO();
     List<Act_likeVO> list1 =  act_likeSvc.findByMemNo(memVO.getMem_no());
     request.setAttribute("list1", list1);
     
     
     
     Wri_likeService wri_likeSvc = new Wri_likeService();
     Wri_likeVO wri_likeVO = new Wri_likeVO();
     List<Wri_likeVO> list2 = wri_likeSvc.findByMem_no(memVO.getMem_no());
     request.setAttribute("list2", list2);
     
%>



							
<head>


<title>收藏商家區</title>


</head>
<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>




<div class="container">
		<div class="row">									
<%-- 			<jsp:useBean id="ShopSvc" scope="page" class="com.shop.model.ShopService" /> --%>
			<jsp:useBean id="actiSvc" scope="page" class="com.activity.model.ActivityService" />
			<jsp:useBean id="wrilSvc" scope="page" class="com.writing.model.WritingService" />
			
			<div class="col-xs-12 col-sm-12">
				<table class="table table-hover"  >
				
					<caption>收藏商家列表</caption>

					<tr >

						<th >商家名稱</th>
						<th >商家照片</th>
						<th >商家電話</th>
						<th >商家地址</th>
						<th >刪除</th>
					</tr>

				
					
					<c:forEach var="favshopVO" items="${list}">
						<tr>

							<td>${shopSvc.findByPrimaryKey(favshopVO.shop_no).shop_name}</td>
<%-- 							<td width="100"  height=auto><img src="<%=request.getContextPath() %>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${favshopVO.shop_no}" alt="暫無圖片"  ></td> --%>
							
							<td width="100"  height=auto><a href='#shop_area${favshopVO.shop_no}' data-toggle="modal" > <img
									src="<%=request.getContextPath() %>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${favshopVO.shop_no}"
									alt="暫無圖片" ></a> 
									
							
							
											
<div  class="modal fade" id="shop_area${favshopVO.shop_no}">
	<div class="modal-dialog">
		<div class="modal-content">
			<div role="tabpanel1">
			    <!-- 標籤面板：標籤區 -->
			    <ul class="nav nav-tabs" role="tablist">
			        <li role="presentation" >
			            <a href="#m_table1${favshopVO.shop_no}" aria-controls="m_table1" role="tab" data-toggle="tab">商家區</a>
			        </li>
			        <li role="presentation"  >
			            <a href="#m_table2${favshopVO.shop_no}" aria-controls="m_table2" role="tab" data-toggle="tab">活動區</a>
			        </li>
					<li role="presentation"  >
			            <a href="#m_table3${favshopVO.shop_no}" aria-controls="m_table3" role="tab" data-toggle="tab">文章區</a>
			        </li>

			    </ul>
			
			    <!-- 標籤面板：內容區 -->
			    <div class="tab-content">
			        <div role="tabpanel1" class="tab-pane active" id="m_table1${favshopVO.shop_no}"><!--商家區塊開始  -->
						
							<div class="modal-header">
								<h3 class="modal-title"><b>商家區</b></h3>
								<img src="<%=request.getContextPath() %>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${favshopVO.shop_no}" alt="暫無圖片" >
							</div>
							<div class="modal-body">
								<p>${shopSvc.findByPrimaryKey(favshopVO.shop_no).shop_desc}</p>
									
							</div>
							<div class="modal-footer">

								<div class="input-group" style="width:100%">

									<button type="button" class="btn btn-primary" data-dismiss="modal" style="width:50%">取消</button>
								</div>
							</div>
						
					</div><!-- 商家區塊結束  -->
					
			        <div role="tabpanel1" class="tab-pane " id="m_table2${favshopVO.shop_no}"><!-- 活動區塊開始  -->

                          
							<div class="modal-header">
						
								<h3 class="modal-title"><b>活動區</b></h3>
								<c:if test="${not empty actiSvc.findByShopNo(favshopVO.shop_no).act_pic_base64}">
								<a href="<%=request.getContextPath()%>/front-end/activity/activity_mem_home.jsp">
								<img width="200" height="200" src="data:image/jpeg;base64,${actiSvc.findByShopNo(favshopVO.shop_no).act_pic_base64}"></a>
								</c:if>
								
								<c:if test="${ empty actiSvc.findByShopno(favshopVO.shop_no).act_pic_base64}">
											<p>此商家無舉辦活動</p>
								</c:if>
							</div>
							<div class="modal-body">
									${actiSvc.findByShopNo(favshopVO.shop_no).act_name}		
							</div>
							<div class="modal-footer">

								<div class="input-group" style="width:100%">

									<button type="button" class="btn btn-primary" data-dismiss="modal" style="width:50%">取消</button>
								</div>
							</div>

					</div><!-- 活動區塊開始結束  -->
					
					<div role="tabpanel1" class="tab-pane " id="m_table3${favshopVO.shop_no}"><!-- 文章區塊開始  -->

							<div class="modal-header">

								<h3 class="modal-title"><b>文章區</b></h3>
								<c:if test="${not empty wrilSvc.findByshopno(favshopVO.shop_no).wrt_photo_base64}">
								<a href="<%=request.getContextPath()%>/front-end/writing/Writing_Home_Page.jsp">
								<img width="200" height="200" src="data:image/jpeg;base64,${wrilSvc.findByshopno(favshopVO.shop_no).wrt_photo_base64}">
								</a>
								</c:if>
								
								<c:if test="${ empty wrilSvc.findByshopno(favshopVO.shop_no).wrt_photo_base64}">
											<p>此商家無發佈文章</p>
								</c:if>
							</div>
							<div class="modal-body">
								
								
                                ${wrilSvc.findByshopno(favshopVO.shop_no).wrt_title}
								
								
							</div>
							<div class="modal-footer">

								<div class="input-group" style="width:100%">

									<button type="button" class="btn btn-primary" data-dismiss="modal" style="width:50%">取消</button>
								</div>
							</div>

					</div><!-- 文章區塊開始結束  -->
					
					
					
			    </div>
			</div><!-- 標籤面板：標籤區結束 -->	
		</div>
	</div>
</div>	

							<td>${shopSvc.findByPrimaryKey(favshopVO.shop_no).shop_phone}</td>
							<td>${shopSvc.findByPrimaryKey(favshopVO.shop_no).shop_loc}</td>
							
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





<!-- 		    <div class="tab-content"> -->
<!-- 		        <div role="tabpanel" class="tab-pane active" id="tab1"> -->
		        
<!-- <!-- 		        <=START=> --> 
<!-- 		        <div class="container"> -->
<!-- 		<div class="row"> -->
			
<!-- 			<div class="col-xs-12 col-sm-12"> -->
<!-- 				<table class="table table-hover"  > -->
				
<%-- 					<caption>收藏商家列表</caption> --%>

<!-- 					<tr > -->
<!-- <!-- 						<th>一般會員編號</th> --> 
<!-- <!-- 						<th>商家編號</th> --> 
<!-- 						<th style="text-align:center;">商家名稱</th> -->
<!-- 						<th style="text-align:center;">商家照片</th> -->
<!-- 						<th style="text-align:center;">商家電話</th> -->
<!-- 						<th style="text-align:center;">商家地址</th> -->
<!-- 						<th style="text-align:center;">刪除</th> -->
<!-- 					</tr> -->



					
<%-- 					<c:forEach var="favshopVO" items="${list}"> --%>
<!-- 						<tr> -->
<%-- <%-- 							<td>${favshopVO.mem_no}</td> --%> 
<%-- <%-- 							<td>${favshopVO.shop_no}</td> --%> 
<%-- 							<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_name}</td> --%>
<%-- 														<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?shop_no=${favshopVO.shop_no}" alt="暫無圖片"height="200" width="300"></td> --%>

<%-- 							<td><a href='#modal-id${favshopVO.shop_no}' --%>
<!-- 								data-toggle="modal"> <img -->
<%-- 									src="<%=request.getContextPath() %>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${favshopVO.shop_no}" --%>
<!-- 									alt="暫無圖片" height="300" width="100"></a>  -->
<%-- 								<div class="modal fade" id="modal-id${favshopVO.shop_no}"> --%>
<!-- 									<div class="modal-dialog"> -->
<!-- 										<div class="modal-content"> -->
<!-- 											<div class="modal-header"> -->

<!-- 												<h4 class="modal-title"> -->
<!-- 													<img -->
<%-- 														src="<%=request.getContextPath() %>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${favshopVO.shop_no}" --%>
<!-- 														alt="暫無圖片" height="300" width="300"> -->
<!-- 												</h4> -->
<!-- 											</div> -->
<!-- 											<div class="modal-body"> -->
<%-- 												<p>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_desc}</p> --%>

<!-- 											</div> -->
<!-- 											<div class="modal-footer"> -->
<!-- 												<button type="button" class="btn btn-primary" -->
<!-- 													data-dismiss="modal">關閉</button> -->

<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div></td> -->

<%-- 							<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_phone}</td> --%>
<%-- 							<td>${ShopSvc.findByPrimaryKey(favshopVO.shop_no).shop_loc}</td> --%>
							
<!-- 							<td> -->
<!-- 								<FORM METHOD="post" -->
<%-- 									ACTION="<%=request.getContextPath()%>/front-end/fav_shop/fav_shop.do" --%>
<!-- 									style="margin-bottom: 0px;"> -->
<!-- 									<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 										name="mem_no" value="${favshopVO.mem_no}"> <input --%>
<%-- 										type="hidden" name="shop_no" value="${favshopVO.shop_no}"> --%>
<!-- 									<input type="hidden" name="action" value="delete"> -->
<!-- 								</FORM> -->
<!-- 							</td> -->

<!-- 						</tr> -->

<%-- 					</c:forEach> --%>


<!-- 				</table> -->

<!-- 			</div> -->
			
<!-- 		</div> -->
<!-- 	</div> -->
		        
		        
<!-- 		        </div> -->
<!-- 		        <div role="tabpanel" class="tab-pane" id="tab2"> -->
				


<!-- 				<div class="container"> -->
<!-- 					<div class="row"> -->
						
<!-- 						<div class="col-xs-12 col-sm-12"> -->
<!-- 							<table class="table table-hover"> -->
<%-- 							<jsp:useBean id="actiSvc" scope="page" class="com.activity.model.ActivityService" /> --%>
							
<%-- 								<caption>商家活動列表</caption> --%>
								
<!-- 									<tr> -->
<!-- 										<th>活動照片</th> -->
<!-- 										<th>活動標題</th> -->
<!-- 										<th>活動開始</th> -->
<!-- 										<th>活動結束</th> -->
<!-- 										<th>活動內文</th> -->
<!-- 									</tr> -->
								
<%-- 								<c:forEach var="actlikeVO" items="${list1}"> --%>
									
<!-- 									<tr> -->
<%-- 										<td><img width="200" height="200" src="data:image/jpeg;base64,${actiSvc.findByActNo(actlikeVO.act_no).act_pic_base64}"></td> --%>
<%-- 										<td>${actiSvc.findByActNo(actlikeVO.act_no).act_name }</td> --%>
<%-- 										<td>${actiSvc.findByActNo(actlikeVO.act_no).act_start }</td> --%>
<%-- 										<td>${actiSvc.findByActNo(actlikeVO.act_no).act_end }</td> --%>
<%-- 										<td>${actiSvc.findByActNo(actlikeVO.act_no).act_art }</td> --%>
<!-- 									</tr> -->
								
<%-- 								</c:forEach> --%>
<!-- 							</table> -->

<!-- 						</div> -->
						
<!-- 					</div> -->
<!-- 				</div> -->




<!-- 			</div> -->
<!-- 		        <div role="tabpanel" class="tab-pane" id="tab3"> -->


<!-- 				<div class="container"> -->
<!-- 					<div class="row"> -->
<%-- 						<jsp:useBean id="wrilSvc" scope="page" class="com.writing.model.WritingService" /> --%>
<!-- 						<div class="col-xs-12 col-sm-8"> -->
<!-- 							<table class="table table-hover"> -->
<%-- 								<caption>商家文章列表</caption> --%>
								
<!-- 									<tr> -->
<!-- 										<th>文章照片</th> -->
<!-- 										<th>文章標題</th> -->
<!-- 										<th>文章內容</th> -->
<!-- 										<th>文章發表時間</th> -->
<!-- 									</tr> -->
								
<%-- 								<c:forEach var="wrilikeVO" items="${list2}"> --%>
<!-- 									<tr> -->
<%-- 										<td><img width="200" height="200" src="data:image/jpeg;base64,${wrilSvc.getOneWriting(wrilikeVO.wrt_no).wrt_photo_base64}"></td> --%>
<%-- 										<td>${wrilSvc.getOneWriting(wrilikeVO.wrt_no).wrt_title}</td> --%>
<%-- 										<td>${wrilSvc.getOneWriting(wrilikeVO.wrt_no).wrt_cont}</td> --%>
<%-- 										<td>${wrilSvc.getOneWriting(wrilikeVO.wrt_no).wrt_time}</td> --%>
<!-- 									</tr> -->
									
<%-- 							   </c:forEach> --%>
<!-- 							</table> -->

<!-- 						</div> -->
					
<!-- 					</div> -->
<!-- 				</div> -->






<!-- 			</div> -->
<!-- 		    </div> -->
<!-- 		</div> -->






<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>