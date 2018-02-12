<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.act_like.model.*"%>
<%@ page import="com.act_join_det.model.*"%>
<%@ include file="/front-end/HtmlHeadPage.jsp" %>

<jsp:useBean id="act_likeSvc" scope="page" class="com.act_like.model.Act_likeService" />
<jsp:useBean id="act_comSvc" scope="page" class="com.act_com.model.Act_comService" />
<jsp:useBean id="act_join_detSvc" scope="page" class="com.act_join_det.model.Act_join_detService" />
<jsp:useBean id="act_repSvc" scope="page" class="com.act_rep.model.Act_repService" />
<jsp:useBean id="act_com_reportSvc" scope="page" class="com.act_com_report.model.Act_com_reportService" />
<jsp:useBean id="act_com_replySvc" scope="page" class="com.act_com_reply.model.Act_com_replyService" />

	<head>
		<style type="text/css">
			.control{
				margin-top:40px;
			}
			.panel_self{
				border-color: #d8f09b;
			}
			.panel_title{
				font-weight: bold;
				font: bold 22px "微軟正黑體";
			}
			.panel_heading_self{
			    background-color: #d8f09b;
			}
			.item_self{
				font-size: 18px;
				font-weight: 700;
			}
			#ar_cnt{
			    margin-bottom: 10px;
			}
			.modal{
				text-align: center;
				padding: 0!important;
			}
			.modal:before{
				content: '';
				display: inline-block;
				height: 100%;
				vertical-align: middle;
				margin-right: -4px;
			}
			.modal .modal-dialog{
				display: inline-block;
				text-align: left;
				vertical-align: middle;
			}
			#cancel_like_act{ 
				color: dodgerblue; 
			} 
			.actmorepic{
			    width: 50%;
   				height: auto;
			}
			.act_more_row{
				margin-bottom: 20px;
			}
			.act_join{
				margin-top: 30px;
				margin-bottom: 30px;
			}
			.comMemImg{
				display: block;
    			margin-left: 50px;
    			margin-bottom: 20px;
			}
 			.actComTime{ 
				margin-top: 15px;
			} 
			.cancelBtn{
				padding: 10px;
				background-color: #EA9B70;
			}
			.actmore_table{
				width: 100%;
				border-collapse: collapse;
			}
			.actmore_th{
				font: bold 30px "微軟正黑體";
				padding-bottom: 10px;
			}
			.actmore_td{
				font-size: 20px;
				padding-bottom: 10px;
			}
			.media-heading{
				font: bold 20px "微軟正黑體";
				margin-left: 15px;
   				margin-right: 10px;
			}
			.media-right{
				padding-left: 10px;
    			padding-bottom: 10px;
			}
			#act_com_block{
				margin-top: 20px;
				display:${empty loginShopVO ? "":"none"}
			}
			.actComBtn{
				margin-top: 14px;
				margin-left: 10px;
			}
			.t4textarea{
				resize: none;
				margin-top: 14px;
			}
			.actComReply{
				padding-top: 20px;
			}
			.hidden1,.hidden2,.hidden3,.hidden4,.hidden5{
				display:${empty loginShopVO.shop_no ? "":"none"}
			}

			.addEmpty{
			    padding-left: 105px;
			}
			#fav_shop{
				color: #e5989b;
			}
			
			.fix{
				position: fixed;
			    right: 5%;
			    bottom: -8px;
			    width: 150px;
			    height: 109px;
			    z-index: 60;
			    font-weight: 200;
			    line-height: 1;
			    color: #FFF;
			    text-align: center;
			    animation-fill-mode: backwards;
			    cursor: pointer;
			    background-image: url(<%=request.getContextPath()%>/front-end/activity/images/gosignFix.gif);
			    -webkit-background-size: 100%;
			    background-size: 100%;
			    background-repeat: no-repeat;
			    animation-name: flowH;
			    animation-duration: 5s;
			    -webkit-animation-name: flowH;
			    -webkit-animation-duration: 5s;
			    animation-iteration-count: infinite;
			    animation-timing-function: linear;
			    display:${empty loginMemberVO ? "none":""}
			}
		
			.fix:before{
				content: "";
			    background-image: url(<%=request.getContextPath()%>/front-end/activity/images/gosignFix_before.gif);
			    background-repeat: no-repeat;
			    -webkit-background-size: 100%;
			    background-size: 100%;
			    width: 75px;
			    height: 75px;
			    position: absolute;
			    left: -55px;
			    top: -45px;
			}
			.fix a{
				width: 135%;
			    height: 140%;
			    display: block;
			    position: relative;
			    left: -35%;
			    top: -40%;
			    background-color: rgba(252,240,212,0);
			}
			@keyframes flowH {
		  		0%, 80% {transform: translate3d(0,0,0);}
				30%, 50% {transform: translate3d(-100%,0,0);}
			}
			/* Safari 4.0 - 8.0 */
			@-webkit-keyframes flowH {
			   0%, 80% {transform: translate3d(0,0,0);}
			   30%, 50% {transform: translate3d(-100%,0,0);}
			}
			
		</style>
		
	</head>
	<%@ include file="/front-end/HeaderPage.jsp" %>
	
<c:set var="act_join_detVO" value="${act_join_detSvc.findByPrimaryKey(param.act_no, loginMemberVO.mem_no)}" />
<c:set var="act_likeVO" value="${act_likeSvc.findByPrimaryKey(param.act_no, loginMemberVO.mem_no)}" />
	
				<div class="container-fluid">
					<div class="row control">
						<div class="col-xs-12 col-sm-12">
							<div class="row">
						<!-- 左邊漢堡 -->
						<c:if test="${not empty loginMemberVO}">
						<%@ include file="/front-end/activity/activity_left.jsp" %>
						</c:if>
						<c:if test="${not empty loginShopVO}">
						<%@ include file="/front-end/activity/activity_left_shop.jsp" %>
						</c:if>
						<div class="col-xs-12 col-sm-8">
							<div class="col-xs-12 col-sm-12">
									<table class="actmore_table">
										<tr>
											<th class="actmore_th">${activityVO.act_name}</th>
										</tr>
										<tr>
											<td class="actmore_td">
												活動時間:<fmt:formatDate type = "both" value = "${activityVO.act_start}" pattern = "yyyy-MM-dd HH:mm" /> 至 <fmt:formatDate type = "both" value = "${activityVO.act_end}" pattern = "yyyy-MM-dd HH:mm" />
											</td>
										</tr>
										<tr>
											<td class="actmore_td" >主辦者 :${shopSvc.findByPrimaryKey(activityVO.shop_no).shop_name}</td>
										</tr>
									</table>
							</div>

							<div class="col-xs-12 col-sm-10 col-sm-off-1">
								<div>
									<img src="data:image/jpeg;base64,${activityVO.act_pic_base64}" class="actmorepic">
								</div>
							</div>

							<div class="col-xs-12 col-sm-12">
								<div class="act_join">
									<p>${activityVO.act_art}</p>
									<table>
										<tr>
										<th>
											<c:if test="${!(act_join_detVO.aj_status=='未報到'||act_join_detVO.aj_status=='已報到')}">
										 	<div class="fix">
										 		<a href="<%=request.getContextPath()%>/front-end/activity/act_join_det.do?action=activity_more_join&act_no=${activityVO.act_no}&mem_no=${loginMemberVO.mem_no}"></a>
										 	</div>
										 	</c:if>
<%-- 										 	<c:if test="${!(act_join_detVO.aj_status=='未報到'||act_join_detVO.aj_status=='已報到')}"> --%>
<%-- 											<form name="join"  method="post"  action="<%=request.getContextPath()%>/front-end/activity/act_join_det.do" style="display:inline;">   --%>
<%-- 												<input type="hidden" name="act_no" value="${activityVO.act_no}"> --%>
<%-- 						                 		<input type="hidden" name="mem_no" value="${loginMemberVO.mem_no}"> --%>
<!-- 						                 		<input type="hidden" name="action" value="activity_more_join"> -->
						                 		
<!-- 												<button class="btn btn-md hidden4" style="background-color: #b9b93c;"> -->
<!-- 				                 					我要報名 -->
<!-- 				                 				</button> -->
<%-- 												</c:if> --%>
<!-- 											</form> -->
<%-- 											<form name="cancel"  method="post"  action="<%=request.getContextPath()%>/front-end/activity/act_join_det.do" style="display:inline;">   --%>
<%-- 						                 		<input type="hidden" name="act_no" value="${activityVO.act_no}"> --%>
<%-- 						                 		<input type="hidden" name="mem_no" value="${loginMemberVO.mem_no}"> --%>
<!-- 						                 		<input type="hidden" name="aj_status" value="取消報名"> -->
<!-- 						                 		<input type="hidden" name="action" value="update_status_ByActivity_more"> -->
						                 		<c:if test="${act_join_detVO.aj_status=='未報到'}">
						                 		<div class="cancelBtn">
													您已報名過活動囉!
												</div>
												</c:if>
<!-- 											</form> -->
										</th>
										<th class="hidden1">
											<c:if test="${empty act_likeVO}">
											<form name="like_act"  method="post"  action="<%=request.getContextPath()%>/front-end/activity/act_like.do" style="display:inline;">  
												<input type="hidden" name="act_no" value="${activityVO.act_no}">
				                 				<input type="hidden" name="mem_no" value="${loginMemberVO.mem_no}">
				                 				<input type="hidden" name="action" value="insert_ByActivity_more">
				                 				<button id="like_act" class="btn btn-default btn-md">活動很讚<i class="glyphicon glyphicon-thumbs-up"></i></button>
											</form>
											</c:if>
											<c:if test="${not empty act_likeVO}">
											<form name="cancel_like_act"  method="post"  action="<%=request.getContextPath()%>/front-end/activity/act_like.do" style="display:inline;">  
												<input type="hidden" name="act_no" value="${activityVO.act_no}">
				                 				<input type="hidden" name="mem_no" value="${loginMemberVO.mem_no}">
				                 				<input type="hidden" name="action" value="delete_ByActivity_more">
				                 				<button id="cancel_like_act" class="btn btn-default btn-md">活動很讚<i class="glyphicon glyphicon-thumbs-up"></i></button>
											</form>
											</c:if>
										</th>
										<th>
											<button class="btn btn-default btn-md hidden2" type="button" data-toggle="modal" data-target="#actRepModal" 
												${empty act_repSvc.findByFK(param.act_no, loginMemberVO.mem_no)? "":"disabled"}>
												檢舉活動<i class="glyphicon glyphicon-bullhorn"></i>
											</button>
										</th>
										<th>
											<c:if test="${empty fav_shopSvc.findByFK(activityVO.shop_no, loginMemberVO.mem_no) && not empty loginMemberVO}">
											<form name="fav_shop"  method="post"  action="<%=request.getContextPath()%>/front-end/fav_shop/fav_shop.do" style="display:inline;">  
											<input type="hidden" name="shop_no" value="${activityVO.shop_no}">
			                 				<input type="hidden" name="mem_no" value="${loginMemberVO.mem_no}">
			                 				<input type="hidden" name="act_no" value="${activityVO.act_no}">
			                 				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			                 				<input type="hidden" name="action" value="insert_by_activity_more">
			                 				<button id="cancel_fav_shop" class="btn btn-default btn-md">收藏商家<i class="glyphicon glyphicon-heart-empty"></i></button>
											</form>
											</c:if>
											<c:if test="${not empty fav_shopSvc.findByFK(activityVO.shop_no, loginMemberVO.mem_no) && not empty loginMemberVO}">
											<form name="cancel_fav_shop"  method="post"  action="<%=request.getContextPath()%>/front-end/fav_shop/fav_shop.do" style="display:inline;">  
											<input type="hidden" name="shop_no" value="${activityVO.shop_no}">
			                 				<input type="hidden" name="mem_no" value="${loginMemberVO.mem_no}">
			                 				<input type="hidden" name="act_no" value="${activityVO.act_no}">
			                 				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			                 				<input type="hidden" name="action" value="delete_ByActivity_more">
			                 				<button id="fav_shop" class="btn btn-default btn-md">收藏商家<i class="glyphicon glyphicon-heart"></i></button>
											</form>
											</c:if>
										</th>
										</tr>
									</table>
									
								</div>	
							</div>

							<div class="col-xs-12 col-sm-12">
								<div class="act_more_row">
								<fieldset style="width:100%;background-color: #fdc68e">
									<legend style="color:#a12213; font-size: 22px">活動留言區</legend>							
	<!--活動回覆區塊 跑forEach 開始--> 	
									<c:forEach var="act_comVO" items="${act_comSvc.findByActNo(param.act_no)}">
										<div class="media">
										    <div class="media-left">
										      	<img src="data:image/jpeg;base64,${memberSvc.findByMem_no(act_comVO.mem_no).mem_photo_base64}" class="media-object img-circle comMemImg" style="width: 80px;height: 80px;">
										    </div>
										    <div class="media-body" style="width:1500px;">
										      	<h4 class="media-heading" style="width:100px;margin-top: 12px;">${memberSvc.findByMem_no(act_comVO.mem_no).mem_name} </h4>
										    </div>
										     <div class="media-body" style="width:5000px;">
										     	<div class="actComTime"></div><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${act_comVO.aco_date}"/>
										     </div>
										    <div class="media-body">
		<!--留言內容--> 						 	<div><p style="padding-top: 14px;">${act_comVO.aco_cnt}</p></div>
										    </div>
										    <div class="media-body">
										    	<div id="report_block">
	<!--留言檢舉-->							    <A href="act_com_report.do?act_no=${param.act_no}&aco_no=${act_comVO.aco_no}&mem_no=${loginMemberVO.mem_no}&action=getOneActCom_For_report" class="btn btn-default btn-lg hidden5" 
										    	${empty act_com_reportSvc.findByFK(act_comVO.aco_no, loginMemberVO.mem_no) ? "": "disabled"} ${act_comVO.mem_no == loginMemberVO.mem_no ? "disabled":""} >檢舉</a>
										    	<A href="act_com_reply.do?act_no=${param.act_no}&aco_no=${act_comVO.aco_no}&shop_no=${loginShopVO.shop_no}&action=getOneActCom_For_reply" class="btn btn-default btn-lg" 
										    	${empty act_com_replySvc.findByFK(act_comVO.aco_no, loginShopVO.shop_no) ? "": "disabled"} ${empty loginShopVO.shop_no ? "disabled":""}>回覆</a>
										    	</div>
										    </div>
									    </div>
									    <!--活動留言回覆區塊 跑forEach 開始--> 
									    <c:forEach var="act_com_replyVO" items="${act_com_replySvc.findByAcoNo(act_comVO.aco_no)}">
									    	<div class="media actComReply" style="background-color: #fff">
											    <div class="media-left addEmpty">
											      	<img src="<%=request.getContextPath()%>/front-end/DBPicReader/DBPicReader.do?id_no=${act_com_replyVO.shop_no}&action=shop" class="media-object img-circle comMemImg" style="width: 80px;height: 80px;">
											    </div>
											    <div class="media-body" style="width:700px;">
											      	<h4 class="media-heading" style="width:100px;margin-top: 12px;">${shopSvc.findByPrimaryKey(act_com_replyVO.shop_no).shop_name} </h4>
											    </div>
											     <div class="media-body" style="width:2200px;">
											     	<div class="actComTime"></div><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${act_com_replyVO.acr_time}"/>
											     </div>
											    <div class="media-body">
			<!--留言內容--> 						 	<div><p style="padding-top: 14px;">${act_com_replyVO.acr_cnt}</p></div>
											    </div>
									    	</div>
									    </c:forEach>
									    <!--活動留言回覆區塊 跑forEach 結束--> 
	<!--活動回覆區塊 跑forEach 結束--> </c:forEach>
								</fieldset>
								
	<!--活動留言輸入-->			<div id="act_com_block" >
								 	<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/activity/act_com.do" name="act_com">
								 		<div class="media">
										    <div class="media-left">
										      	<img src="data:image/jpeg;base64,${loginMemberVO.mem_photo_base64}" class="media-object img-circle comMemImg" style="width: 80px;height: 80px;">
										    </div>
										    <div class="media-body" style="width:800px;">
										      	<h4 class="media-heading" style="width:100px;margin-top: 16px;">${loginMemberVO.mem_name} </h4>
										    </div>
										    <div class="media-body">
										    	<textarea class="form-control t4textarea" style="" name="aco_cnt" maxlength='66' rows="2" cols="140"></textarea>
										    </div>
										    <div class="media-body" style="width:1500px;">
												<a href="javascript:document.act_com.submit()" class="btn btn-default btn-lg actComBtn" role="button">送出留言</a>
											</div>
									    </div>
										新增送出留言
										<input type="hidden" name="act_no"      value="${param.act_no}">
										<input type="hidden" name="mem_no"      value="${loginMemberVO.mem_no}">  
										<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
										<input type="hidden" name="action"      value="insert_comment">
									</form>
	<!--活動留言輸入結束-->			</div>
	 						
								</div>
							</div>
						</div>	
						</div>
						</div>
<!-- row結束 -->		</div>
				</div>
<!-- 活動檢舉燈箱 -->
				<div class="modal fade" id="actRepModal">
				<div class="modal-dialog modal-lg">
					<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/activity/act_rep.do" name="act_report">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">檢舉活動塊塊</h4>
						</div>
						<div class="modal-body">
							<input type="hidden" name="act_no" value="${activityVO.act_no}">
				            <input type="hidden" name="mem_no" value="${loginMemberVO.mem_no}">
				            <input type="hidden" name="action" value="insert_report">
				            <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<div>
								<label for="ar_cnt">檢舉原因</label>
								<select class="form-control" name="ar_rsn" id="ar_rsn">
									<option value="無關水果的活動">無關水果的活動</option>
									<option value="不是小農的活動">不是小農的活動</option>
									<option value="傷風敗俗的活動">傷風敗俗的活動</option>
									<option value="廣告">廣告</option>
									
								</select>
							</div>
							<div>
								<label for="ar_cnt">檢舉內容</label>
								<textarea class="form-control" style="resize: none;" name="ar_cnt" id="ar_cnt" maxlength='66' rows="3" cols="100"></textarea>
							</div>
							
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onClick="document.act_report.submit()">送出檢舉</button>
						</div>
					</div>
					</form>
				</div>
				</div>
<!-- 活動檢舉燈箱 -->		
<!-- 活動留言檢舉燈箱 -->
			<c:if test="${openModal!=null}">
			
			<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
							
						<div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h3 class="modal-title" id="myModalLabel">活動留言檢舉</h3>
			            </div>
						
						<div class="modal-body">
			<!-- =========================================以下為原actCommentReport.jsp的內容========================================== -->
			               <jsp:include page="actCommentReport.jsp" />
			<!-- =========================================以上為原actCommentReport.jsp的內容========================================== -->
						</div>
						
			<!-- 			<div class="modal-footer"> -->
			<!--                 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
			<!--                 <button type="button" class="btn btn-primary">Save changes</button> -->
			<!--             </div> -->
					
					</div>
				</div>
			</div>
			
	        <script>
	    		 $("#basicModal").modal({show: true});
	        </script>
			 </c:if>
<!-- 活動留言檢舉燈箱結束 -->

<!-- 活動留言回覆燈箱 -->
			<c:if test="${openModal_for_reply!=null}">
			
			<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
							
						<div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h3 class="modal-title" id="myModalLabel">活動留言回覆</h3>
			            </div>
						
						<div class="modal-body">
			<!-- =========================================以下為原actCommentReply.jsp的內容========================================== -->
			               <jsp:include page="actCommentReply.jsp" />
			<!-- =========================================以上為原actCommentReply.jsp的內容========================================== -->
						</div>
					
					</div>
				</div>
			</div>
			
	        <script>
	    		 $("#replyModal").modal({show: true});
	        </script>
			</c:if>
<!-- 活動留言回覆燈箱結束 -->

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

		<script type="text/javascript">
			function setBstModalMaxHeight(element) {
			  this.$element          = $(element);
			  this.$modalContent     = this.$element.find('.modal-content');
			  var $window            = $(window);
			  var $modalContentOH    = this.$modalContent.outerHeight();
			  var $modalContentIH    = this.$modalContent.innerHeight();
			  var _modalBorderWidth   = $modalContentOH - $modalContentIH;
			  var _modalDialogMargin  = $window.width() < 768 ? 20 : 60;
			  var _modalContentHeight = $window.height() - (_modalDialogMargin + _modalBorderWidth);
			  var _modalHeaderHeight  = this.$element.find('.modal-header').outerHeight() || 0;
			  var _modalFooterHeight  = this.$element.find('.modal-footer').outerHeight() || 0;
			  var _modalMaxHeight     = _modalContentHeight - (_modalHeaderHeight + _modalFooterHeight);
			
			  this.$modalContent.css({
			      'overflow': 'hidden'
			  });
			  
			  this.$element
			    .find('.modal-body').css({
			      'max-height': _modalMaxHeight,
			      'overflow-y': 'auto'
			  });
			}
			
			$('.modal').on('show.bs.modal', function() {
			  $(this).show();
			  setBstModalMaxHeight(this);
			});
			
			$(window).resize(function() {
			  if ($('.modal.in').length != 0) {
			    setBstModalMaxHeight($('.modal.in'));
			  }
			});
			$(function(){
				$('.fix').click(function(){
					event.preventDefault();
					swal("活動報名成功!","");
					setTimeout(function(){location.href="<%=request.getContextPath()%>/front-end/activity/act_join_det.do?action=activity_more_join&act_no=${activityVO.act_no}&mem_no=${loginMemberVO.mem_no}"; }, 2500);
					
				});
			});
		</script>
	<%@ include file="/front-end/FooterPage.jsp" %> 