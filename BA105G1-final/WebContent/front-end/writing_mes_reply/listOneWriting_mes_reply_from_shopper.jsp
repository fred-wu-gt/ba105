<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.writing.model.*"%>
<%@ page import="com.wri_mes.model.*"%>
<!-- 商家可以查看會員在某一篇文章的留言，並對留言進行回覆 -->
<%@ include file="/front-end/HtmlHeadPage.jsp"%>

<%
	// 	WritingVO writingVO = (WritingVO) request.getAttribute("writingVO");
	//WritingServlet.java(Controller),存入req的writingVO物件

	Wri_mesVO wri_mesVO = (Wri_mesVO) request.getAttribute("wri_mesVO");
	//Wri_mesServlet.java(Controller),存入req的wri_mesVO物件

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
			
/* 			.delete_submit_button { */
/* 				background-color: #CCCCFF; */
/* 				border: 2px solid #fee; */
/* 				text-align: center; */
/* 			} */
			
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
							<img id="img" width="125px" class="img-rounded"
								src="data:image/png;base64,<%=writingVO.getWrt_photo_base64()%>">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 標題、圖片的container結束-->
<jsp:useBean id="memSvc" class="com.member.model.MemberService" />
<jsp:useBean id="wri_mesSvc2" class="com.wri_mes.model.Wri_mesService" />
<jsp:useBean id="wri_mes_replySvc2" class="com.wri_mes_reply.model.Wri_mes_replyService" />

<!-- 內文container開始-->
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">

			<div class="col-xs-12 col-sm-12">
				<div>
					<h4><%=writingVO.getWrt_cont()%></h4>
				</div>
			</div>

			<!-- 留言區開始-->

			<div class="col-xs-12 col-sm-12 " style="background-color: #DCDCDC;">
				<c:forEach var="wri_mesVO"	items="${wri_mesSvc2.getAllWriting_mesByWrt_no(writingVO.wrt_no)}">
					<div class="col-xs-12 col-sm-5" style="text-align: left; margin-top: 30px;">
						會員${memSvc.getOneMember1(wri_mesVO.mem_no).mem_name}:</div>
					<div class="col-xs-12 col-sm-12 ">
						<div class="col-xs-12 col-sm-6" style="text-align: left; height: 60px;">
							${wri_mesVO.wmsg_cont}</div>
						<div class="col-xs-12 col-sm-6" style="text-align: right;">
						
							<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/writing_mes/writing_mes.do" style="margin-bottom: 20px;">
								<input type="hidden" name="wmsg_no" value="${wri_mesVO.wmsg_no}">
								<input type="hidden" name="wrt_no" value="${writingVO.wrt_no}">
								<input type="hidden" name="action" value="Delete_Message_From_Shopper"> 
								<input type="submit" value="刪除留言" class="btn btn-danger">
							</FORM>
							
							
							<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/writing_mes_reply/writing_mes_reply.do" style="margin-bottom: 0px;">
								<input type="hidden" name="wmsgr_no" value="${wri_mes_replyVO.wmsgr_no}">
								<input type="hidden" name="wmsg_no" value="${wri_mesVO.wmsg_no}">
								<input type="hidden" name="wrt_no" value="${writingVO.wrt_no}">
								<input type="hidden" name="action" value="Reply_Message_From_Shopper"> 
								<input type="submit" value="回覆留言" class="btn btn-success">
							</FORM>							
								
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="col-xs-12 col-sm-12 " style="margin-top:30px;">
				<a	href="<%=request.getContextPath()%>/front-end/writing/Writing_Shopper_Page.jsp" class="btn btn-info btn-lg BacklistBtn">回到我的文章列表</a>
			</div>
		</div>
	</div>
</div>
<!-- 內文container結束-->

<script type="text/javascript">
	$(document).ready(function() {
		$(".carousel").carousel({
			interval : 4000,
			pause : false
		});
	});
</script>


<%@ include file="/front-end/FooterPage.jsp"%>