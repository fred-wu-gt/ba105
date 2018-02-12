<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.wri_mes.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div id="wrt_mes_area" >
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/writing_mes/writing_mes.do" name="form1">
						<div class="media">
							 <div class="media-left">
							
							 		</div>
							<div class="media-body" style="width:1000px;">
								 <h4 class="media-heading" style="width:90px;margin-top:25px;"></h4>
						 			</div>
						 				<div class="media-body" style="padding-left: 245px;">
									 		<textarea style="resize: none;" name="wmsg_cont" maxlength='66' rows="9" cols="100" class="t4textarea"></textarea>
									 				</div>
									  		<div class="media-body" style="width:1500px;">
										<input type="hidden" name="action"   value="insert_from_writing_mes"> 
									<input type="hidden" name="wrt_no"   value="${writingVO.wrt_no}"> 
								<input type="hidden" name="mem_no"   value="<%= ((MemberVO) session.getAttribute("loginMemberVO")).getMem_no() %>"> 
							</div>
						</div>	
							<div style="margin-left: 700px">
									<input type="submit" value="送出留言" class="btn btn-default btn-lg subBtn" name="">
								<a href="<%=request.getContextPath()%>/front-end/writing/Writing_Home_Page.jsp"	class="btn btn-default btn-lg BacklistBtn">回文章列表</a>
							</div>
					</FORM>
				</div>
		</div>
