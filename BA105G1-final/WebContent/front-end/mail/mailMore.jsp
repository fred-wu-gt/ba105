<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mail.model.*"%>
<%@ page import="com.manager.model.*"%>
<jsp:useBean id="mailSvc" scope="page" class="com.mail.model.MailService" />
<jsp:useBean id="managerSvc" scope="page" class="com.manager.model.ManagerService" />
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
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
			.moreCap{
				margin-left: 7px;
				font: bold 24px "微軟正黑體";
			}
			.mailMoreTable{
				background-color: #fcf0d4;
			}
			
			.mailMoreThead{
				
			}
			.mailMoreSpan{
				margin-right: 20px;
			}
			.mailMoreSend{
				font: bold 20px "微軟正黑體";
				margin-right: 20px;
			}
			.mailMoreSender{
				font: 20px "微軟正黑體";
				color: #666;
				margin-right: 5px;
			}
			.mailMoretime{
				font: 18px "微軟正黑體";
			}
			.mailmorepic{
				display: inline;
				width: 80px;
			}
			.mailMorep{
				font: 18px "微軟正黑體";
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
					<table class="table mailMoreTable">
							<caption class="moreCap">${mailVO.mail_title}</caption>
							<thead class="mailMoreThead">
							
								<tr>
									<th>
									<span class="mailMoreSpan"><img src="data:image/jpeg;base64,${managerSvc.getOneManager(mailVO.mail_sender).man_pho_base64}" class="media-objec mailmorepic"></span>
									<span class="mailMoreSend"><span class="mailMoreSender">寄信人:</span>${managerSvc.getOneManager(mailVO.mail_sender).man_name}</span> 
									<span class="mailMoretime"><span class="mailMoreSender">寄信時間:</span><fmt:formatDate type ="both" value ="${mailVO.mail_time}" pattern ="yyyy-MM-dd HH:mm" /></span>
									</th>
								</tr>
							</thead>
							<tbody class="mailMoreTbody">
								<tr>
									<td> <div class="mailMorep"><p>${mailVO.mail_cnt}</p></div></td>
								</tr>
							</tbody>
						</table>
				</div>
			</div>
		</div>
		<%@ include file="/front-end/FooterPage.jsp" %>