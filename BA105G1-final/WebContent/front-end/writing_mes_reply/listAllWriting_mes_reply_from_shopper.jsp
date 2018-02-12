<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.wri_mes.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<html>

<head>
</head>

<body>	

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div id="wrt_mes_reply_area" >
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/writing_mes_reply/writing_mes_reply.do" name="form1">
						<div class="media">
							 <div class="media-left">
							
							 		</div>
							<div class="media-body" style="width:1000px;">
								 <h4 class="media-heading" style="width:90px;margin-top:25px;"></h4>
						 			</div>
						 					<div class="media-body" style="padding-left: 245px;">
									 			<textarea style="resize: none;" name="wcrg_cont" maxlength='66' rows="9" cols="100" class="t4textarea"></textarea>
									 				</div>
									  			<div class="media-body" style="width:1500px;">
											<input type="hidden" name="action"   value="insert_from_writing_mes_reply"> 
										<input type="hidden" name="wmsg_no" value="${wri_mes_replyVO.wmsg_no }">
									<input type="hidden" name="wrt_no"   value="${writingVO.wrt_no}"> 
								<input type="hidden" name="mem_no"   value="<%= ((MemberVO) session.getAttribute("loginMemberVO")).getMem_no() %>"> 
							</div>
						</div>	
							<div style="margin-left: 700px">
									<input type="submit" value="回覆" class="btn btn-default btn-lg subBtn" name="">
								
							</div>
					</FORM>
				</div>
		</div>
	
</body>
</html>