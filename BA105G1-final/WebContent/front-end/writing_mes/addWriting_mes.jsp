<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_mes.model.*"%>

<%
	Wri_mesVO wri_mesVO = (Wri_mesVO) request.getAttribute("wri_mesVO");
%>


<%@ include file="/front-end/HtmlHeadPage.jsp" %>
<%@ include file="/front-end/HeaderPage.jsp" %>

	<h3>發表留言:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM METHOD="post" ACTION="writing_mes.do" name="form1">
		<table>
			<tr>
				<td>留言內容 :</td>
				
				<td colspan="3"><input type="text" name="wmsg_cont" size="45"	maxLength="300"
					value="<%=(wri_mesVO == null) ? "" : wri_mesVO.getWmsg_cont()%>" />
				</td>
				
			</tr>
		</table>

		<input type="hidden" name="action"     value="insert_from_writing_mes"> 	 
		<input type="hidden" name="mem_no"     value="mem0000001"> 
		<input type="hidden" name="wrt_no"     value="wrt0000001"> 
		<input type="hidden" name="wmsg_stat"  value="已審核"> 		
		<input type="submit" value="送出新增">
	</FORM>
	<%@ include file="/front-end/FooterPage.jsp" %>
</body>
</html>