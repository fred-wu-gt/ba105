<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.act_join_det.model.*"%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

<%@ include file="/front-end/HtmlHeadPage.jsp" %>
<head>
	<style type="text/css">
			
			.table-hover tbody tr:hover{
				background-color: #d2e9e6;
			}
			.joinTable{
				margin-top: 60px;
			}
			.joinTable th{
				word-break: keep-all;white-space:nowarp;
			}
			.joinCaption{
				font: bold 30px "微軟正黑體";
				color: #5f8c96;
			}
			.joinThead{
				font: bold 24px "微軟正黑體";
				background-color: #a9cbca;
			}
			.joinMemImg{
				display: block;
    			margin: 0px 30px;
			}

			.BacklistBtn{
				text-align:center;
				display:block;
			}
			.BacklistBtn:hover{
				background-color: #cff6d8;
			}
			.joinTd{
				width:1500px;
				font: bold 18px "微軟正黑體";
			}
			.panel_self{
				
				border-color: #d8f09b;
				margin-top: 116px;
			}
			.panel_heading_self{
			    background-color: #d8f09b;
			}
			.panel_title{
				font-weight: bold;
				font-size: 22px;
			}
			.item_self{
				font-size: 18px;
				font-weight: 700;
			}
		</style>
	</head>
	<%@ include file="/front-end/HeaderPage.jsp" %>
		<div class="container-fluid">
			<div class="row">
				<!-- 商家左邊漢堡 -->
				<%@ include file="/front-end/activity/activity_left_shop.jsp" %> 
				<div class="col-xs-12 col-sm-8">	
					<table class="table table-hover joinTable">
						<caption class="joinCaption">活動報名名單</caption>
						<thead class="joinThead">
							<tr>
								<th>會員照片</th>
								<th>會員姓名</th>
								<th>報名時間</th>
								<th>狀態</th>
								<th>會員電話</th>
								<th>會員電子郵件</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="act_join_detVO" items="${list}">
							<tr>
								<td><div class="media-left"><img src="data:image/jpeg;base64,${memSvc.findByMem_no(act_join_detVO.mem_no).mem_photo_base64}" class="media-object img-circle joinMemImg" style="width: 80px;height: 80px;"></div></td>
								<td><div class="media-body joinTd">${memSvc.findByMem_no(act_join_detVO.mem_no).mem_name}</div></td>
								<td><div class="media-body joinTd"><fmt:formatDate type = "both" value = "${act_join_detVO.aj_time}" pattern = "yyyy-MM-dd HH:mm" /></div></td>
								<td><div class="media-body joinTd">${act_join_detVO.aj_status}</div></td>
								<td><div class="media-body joinTd">${memSvc.findByMem_no(act_join_detVO.mem_no).mem_phone}</div></td>
								<td><div class="media-body joinTd">${memSvc.findByMem_no(act_join_detVO.mem_no).mem_email}</div></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<a href="<%=request.getContextPath()%>/front-end/activity/activity_shop_home.jsp" class="btn btn-default btn-lg BacklistBtn" >回活動列表</a>	
				</div>
			</div>
		</div>	
		<%@ include file="/front-end/FooterPage.jsp" %>