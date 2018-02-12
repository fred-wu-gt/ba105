<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.writing.model.*"%>
<%@ include file="/front-end/HtmlHeadPage.jsp"%>
<%@ include file="/front-end/HeaderPage.jsp"%>
<jsp:useBean id="WritingSvc" scope="page" class="com.writing.model.WritingService" />
<jsp:useBean id="writingSvc" scope="page" class="com.writing.model.WritingService" />

	<%-- 錯誤表列 --%>
	
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<!-- 	<ul> -->

<!-- 		<li> -->
<!-- 			<FORM METHOD="post" ACTION="writing.do"> -->
<!-- 				<b>輸入文章編號 (如WRT0000001):</b> <input type="text" name="wrt_no"> -->
<!-- 				<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 				<input type="submit" value="送出"> -->
<!-- 			</FORM> -->
<!-- 		</li> -->


<!-- 		<li> -->
<!-- 			<FORM METHOD="post" -->
<%-- 				ACTION="<%=request.getContextPath()%>/front-end/writing/writing.do"> --%>
<!-- 				<b>選擇文章標題:</b> <select size="1" name="wrt_no"> -->
<%-- 					<c:forEach var="writingVO" items="${writingSvc.all}"> --%>
<%-- 						<option value="${writingVO.wrt_no}">${writingVO.wrt_title} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> <input type="submit" value="送出"> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			</FORM> -->
<!-- 		</li> -->


<!-- 		<li> -->
<!-- 			<FORM METHOD="post" -->
<%-- 				ACTION="<%=request.getContextPath()%>/front-end/writing/writing.do"> --%>
<!-- 				<b>選擇發布日期:</b> <select size="1" name="wrt_no"> -->
<%-- 					<c:forEach var="writingVO" items="${writingSvc.all}"> --%>
<%-- 						<option value="${writingVO.wrt_no}">${writingVO.wrt_time} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> <input type="submit" value="送出"> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			</FORM> -->
<!-- 		</li> -->

<!-- 	</ul> -->



	<!-- 	<ul> -->
<!-- 		<li><a href='addWriting.jsp'>新增一篇文章</a></li> -->
<!-- 	</ul> -->
	<%@ include file="/front-end/FooterPage.jsp"%>