<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.writing.model.*"%>
<%@ page import="com.wri_mes.model.*"%>
<%@ page import="com.wri_mes_reply.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="memSvc" class="com.member.model.MemberService" />
<jsp:useBean id="wri_mesSvc2" class="com.wri_mes.model.Wri_mesService" />
<jsp:useBean id="wri_mes_replySvc2" class="com.wri_mes_reply.model.Wri_mes_replyService" />



<!-- 商家可以查看會員在某一篇文章的留言，有刪除和回覆的按鈕可點擊 -->
<%@ include file="/front-end/HtmlHeadPage.jsp"%>
<%-- <<jsp:useBean id="wri_mes_replySvc" class="com.wri_mes_reply.model.Wri_mes_replyService"></jsp:useBean> --%>

<%
	// 	WritingVO writingVO = (WritingVO) request.getAttribute("writingVO");
	//WritingServlet.java(Controller),存入req的writingVO物件

	// 	Wri_mesVO wri_mesVO = (Wri_mesVO) request.getAttribute("wri_mesVO");
	//Wri_mesServlet.java(Controller),存入req的wri_mesVO物件

	// 	Wri_mes_replyVO wri_mes_replyVO =(Wri_mes_replyVO)request.getAttribute("wri_mes_replyVO");
	//Wri_mes_replyServlet.java(Controller),存入req的wri_mes_replyVO物件

	ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");

	WritingService writingSvc = new WritingService();
	String wrt_no = request.getParameter("wrt_no");
	if (wrt_no == null) {
		wrt_no = (String) request.getAttribute("wrt_no");
	}

	WritingVO writingVO = writingSvc.getOneWriting(wrt_no);
%>

<style type="text/css">
#img {
	display: ${empty writingVO.wrt_photo_base64?"none":""}
	width:125px;
}
.delete_submit_button {
	background-color: #CCCCFF;
	border: 2px solid #fee;
	text-align: center;
}

.mwt_border {
	width: 350px;
	text-align: center;
	background: #FAA;
	position: relative;
	border: solid 1px #FAA;
	margin: 10px;
	padding: 30px;
}

.mwt_border2 {
	width: 180px;
	text-align: center;
	background: #A1F09A;
	position: relative;
	border: solid 1px #A1F09A;
	margin: 5px;
	padding: 30px;
}

.mwt_border2 .arrow_r_int {
	width: 0px;
	height: 0px;
	border-width: 15px;
	border-style: solid;
	border-color: transparent transparent transparent #A1F09A;
	position: absolute;
	top: 20%;
	right: -30px;
}

.mwt_border2 .arrow_r_out {
	width: 0px;
	height: 0px;
	border-width: 15px;
	border-style: solid;
	border-color: transparent transparent transparent #A1F09A;
	position: absolute;
	top: 20%;
	right: -29px;
}

mwt_border .arrow_l_int {
	width: 0px;
	height: 0px;
	border-width: 15px;
	border-style: solid;
	border-color: transparent #FAA transparent transparent;
	position: absolute;
	top: 20%;
	left: -30px;
}

.mwt_border .arrow_l_out {
	width: 0px;
	height: 0px;
	border-width: 15px;
	border-style: solid;
	border-color: transparent #FAA transparent transparent;
	position: absolute;
	top: 20%;
	left: -29px;
}
</style>

<%@ include file="/front-end/HeaderPage.jsp"%>

<!-- 標題、圖片的container開始-->
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-7">
						<div>
							<h3><%=writingVO.getWrt_title()%></h3>
						</div>
						<div>
							<img id="img" width="125px" class="img-rounded" src="data:image/png;base64,<%=writingVO.getWrt_photo_base64()%>">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 標題、圖片的container結束-->



<!-- 內文container開始-->
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">

			<div class="col-xs-12 col-sm-12">
				<div>
					<h4><%=writingVO.getWrt_cont()%>
						${wri_mes_replySvc2.getOneWriting_mes_reply(wri_mes_replyVO.wmsgr_no).wcr_cont}
					</h4>
				</div>
			</div>
			<!-- 留言區開始-->

			<c:forEach var="wri_mesVO" items="${wri_mesSvc2.getAllWriting_mesByWrt_no(writingVO.wrt_no)}">
				<div class="col-xs-12 col-sm-12 mwt_border" style="margin-top: 10px; text-align: left;">
					會員${memSvc.findByMem_no(wri_mesVO.mem_no).mem_name}: ${wri_mesVO.wmsg_cont}
					<span class="arrow_l_out"></span>
					<span class="arrow_l_int"></span>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="col-xs-12 col-sm-8"></div>
					<div class="col-xs-12 col-sm-4" style="text-align: right;">

						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/writing_mes/writing_mes.do" style="margin-bottom: 0px;">
							<input type="hidden" name="wmsg_no" value="${wri_mesVO.wmsg_no}">
							<input type="hidden" name="wrt_no" value="${writingVO.wrt_no}">
							<input type="hidden" name="action" value="Delete_Message_From_Shopper">
							<input type="submit" value="刪除留言" class="btn btn-danger">
						</FORM>


						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/writing_mes_reply/writing_mes_reply.do" style="margin-bottom: 0px;">
							<input type="hidden" name="wrt_no" id="wrt_no_get" value="${writingVO.wrt_no}">
							<input type="hidden" name="wmsg_no" value="${wri_mesVO.wmsg_no}">
							<input type="hidden" name="wrt_no" value="${writingVO.wrt_no}">
							<input type="hidden" name="shop_no" value="${loginShopVO.shop_no}">
							<input type="hidden" name="action" value="Reply_Message_From_Shopper" />
							<a href='#${wri_mesVO.wmsg_no}' data-toggle="modal" class="btn btn-primary">回覆留言</a>
							<div class="modal fade" id="${wri_mesVO.wmsg_no}">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">請輸入您的回覆</h4>
										</div>

										<div class="modal-body">
											<input type="text" name="wcr_cont" style="width: 500px;" />
											<input type="hidden" name="action" value="Reply_Message_From_Shopper" />
										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
											<button type="submit" class="btn btn-success">送出</button>
										</div>
									</div>
								</div>
							</div>
						</FORM>
					</div>
				</div>

					<c:if test="${not empty wri_mes_replySvc2.getAllWriting_mes_replyByWmsg_no(wri_mesVO.wmsg_no) }">
					<c:forEach var="wri_mes_replyVO" items="${wri_mes_replySvc2.getAllWriting_mes_replyByWmsg_no(wri_mesVO.wmsg_no) }">
						<div class="col-xs-12 col-sm-8"></div>
						<div class="col-xs-12 col-sm-4 mwt_border2">
							<span class="arrow_r_int"></span>
							<span class="arrow_r_out"></span>
					商家: ${shopSvc.findByPrimaryKey(wri_mes_replyVO.shop_no).shop_name}    :  ${wri_mes_replyVO.wcr_cont}
						</div>
						</c:forEach>
					</c:if>
					
					
			</c:forEach>
		</div>


		<div class="col-xs-12 col-sm-12 " style="margin-top: 30px;">
			<a href="<%=request.getContextPath()%>/front-end/writing/Writing_Shopper_Page.jsp" class="btn btn-info btn-lg BacklistBtn">回到我的文章列表</a>
		</div>
	</div>
	<!-- 內文container結束-->



	<script type="text/javascript">

	<c:forEach var="wri_mesVO" items="${wri_mesSvc2.getAllWriting_mesByWrt_no(writingVO.wrt_no)}">
	
	function A${wri_mesVO.wmsg_no} (){
		document.getElementById('wmsg_no').value = 3;
		console.log( document.getElementById("wmsg_no').value );
	};
	</c:forEach>
	


	$(document).ready(function() {
		$(".carousel").carousel({
			interval : 4000,
			pause : false
		});
	});
	
</script>


	<%@ include file="/front-end/FooterPage.jsp"%>