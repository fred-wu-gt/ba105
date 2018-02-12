<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mail.model.*"%>
<%@ page import="com.manager.model.*"%>
<jsp:useBean id="mailSvc" scope="page" class="com.mail.model.MailService" />
<jsp:useBean id="managerSvc" scope="page" class="com.manager.model.ManagerService" />
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<% // 先寫死一般會員

%>
<%@ include file="/front-end/HtmlHeadPage.jsp" %>
	<head>
		<style type="text/css">
			.condiv{
				margin-top: 60px;
			}
			.conTable tbody tr td a:hover{
				background-color: #c0f4f8;
			}
			.mailpic{
				width: 100%
			}
			.aa{
				width: 100%;
			}
			.mailTable tbody tr:hover{
				background-color: #caf9e0;
			}
			.mailThead{
				font: bold 20px "微軟正黑體";

			}
			.mailTbody{
				font: 20px "微軟正黑體";
			}
			.maila{
				color: #FFFFFF;
    			text-decoration: none;
			}
		</style>
	</head>
	<%@ include file="/front-end/HeaderPage.jsp" %>
		<div class="container">
			<div class="row condiv">
				<div class="col-xs-12 col-sm-3">
					
					<table class="table table-hover conTable">
						<thead>
							<tr>
							 <td> 
							 	<div class="media">
							 	  <div class="media-body">
							 	  	<img src="data:image/jpeg;base64,${memSvc.findByMem_no(sessionScope.mem_no).mem_photo_base64}" class="media-objec mailpic">
							 	  </div>
							 	</div>	
							 </td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><a href='<%=request.getContextPath()%>/front-end/mail/mailHome.jsp' class="btn btn-default btn-lg aa">收件匣</a></td>
							</tr>
							<tr>
								<td><a href='<%=request.getContextPath()%>/front-end/mail/addMail.jsp' class="btn btn-default btn-lg aa">撰寫信件</a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-xs-12 col-sm-9">
					<table class="table table-hover mailTable">
							
							<thead class="mailThead">
								<tr>
									<th>寄信者</th>
									<th>信件標題</th>
									<th>寄信時間</th>
								</tr>
							</thead>
							<tbody class="mailTbody">
							<c:forEach var="mailVO" items="${mailSvc.findByMailReceiver(mem_no)}">
								<tr>
									<td><div class="media-body">${managerSvc.getOneManager(mailVO.mail_sender).man_name}</div></td>
									<td><div class="media-body maila"><A href="<%=request.getContextPath()%>/front-end/mail/mail.do?mail_no=${mailVO.mail_no}&mem_no=${sessionScope.mem_no}&action=getOneMail_For_display">${mailVO.mail_title}</a></div></td>
									<td><div class="media-body"><fmt:formatDate type ="both" value ="${mailVO.mail_time}" pattern ="yyyy-MM-dd HH:mm" /></div></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
				</div>
			</div>
		</div>
		<%@ include file="/front-end/FooterPage.jsp" %>
	