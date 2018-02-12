<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.writing.model.*"%>
<%@ page import="com.wri_mes.model.*"%>
<%@ page import="com.wri_mes_reply.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="wri_mesSvc2" class="com.wri_mes.model.Wri_mesService" />
<jsp:useBean id="wri_mes_replySvc2" class="com.wri_mes_reply.model.Wri_mes_replyService" />


<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%--會員頁面，可回覆留言，以及查看商家回覆 --%>

<%@ include file="/front-end/HtmlHeadPage.jsp"%>
<%
	//WritingVO writingVO = (WritingVO) request.getAttribute("writingVO");
	//WritingServlet.java(Controller),存入req的writingVO物件

// 	Wri_mesVO wri_mesVO = (Wri_mesVO) request.getAttribute("wri_mesVO");
	//Wri_mesServlet.java(Controller),存入req的wri_mesVO物件

// 	String wrt_no = request.getParameter("wrt_no");
// 	if (wrt_no == null) {
// 		wrt_no = (String) request.getAttribute("wrt_no");
// 	}

// 	WritingService writingSvc = new WritingService();
// 	WritingVO writingVO = writingSvc.getOneWriting(wrt_no);

// 	Wri_mesService wri_mesSvc = new Wri_mesService();
// 	List<Wri_mesVO> list = wri_mesSvc.getAll();
// 	pageContext.setAttribute("list", list);
%>

<style type="text/css">
	#img {
		display: ${empty writingVO.wrt_photo_base64?"none":""}
		width:125px;
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

.wrt_title{
	width: 20px;
	height: 20;

}

pre{
white-space:pre-wrap;

}
</style>




<%@ include file="/front-end/HeaderPage.jsp"%>

<!-- 標題、圖片的container開始-->
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-sm-offset-2">
		
				<div class="col-xs-12 col-sm-12">
					<td class="wrt_title"><h1>${writingVO.wrt_title}</h1><td>
				</div>
				
			<div class="col-xs-12 col-sm-8">
					<img id="img" width="350px" src="data:image/png;base64,${writingVO.wrt_photo_base64}">
			</div>
			
			<div class="col-xs-12 col-sm-8">
				<div></div>
			</div>
			
			<div class="col-xs-12 col-sm-12">
				<h4 class="serif"><pre>${writingVO.wrt_cont}</pre></h4>
			</div>
			
		</div>
	</div>
</div>
<!-- 標題、圖片的container結束-->


<!-- 內文container開始-->
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-sm-offset-2">
			<c:forEach var="wri_mesVO" items="${wri_mesSvc2.getAllWriting_mesByWrt_no(writingVO.wrt_no)}">

				<div class="col-xs-12 col-sm-12 mwt_border" style="margin-top: 10px; text-align: left;">
					會員${memberSvc.findByMem_no(wri_mesVO.mem_no).mem_name}: ${wri_mesVO.wmsg_cont}
					<span class="arrow_l_out"></span>
					<span class="arrow_l_int"></span>
				</div>


				<div class="col-xs-12 col-sm-12">
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
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 " style="margin-top: 30px;">
		 <%@ include file="/front-end/writing_mes/listAllWriting_mes.jsp"%>
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