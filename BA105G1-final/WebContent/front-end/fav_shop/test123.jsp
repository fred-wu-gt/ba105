<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page import="com.activity.model.*,java.util.*"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="actiSvc" scope="page" class="com.activity.model.ActivityService" />
<%
List<ActivityVO> listAct = actiSvc.findByShopNo("SHOP000007");
request.setAttribute("listAct", listAct);
%>

							<c:forEach var="actVO" items="${listAct}">
										${actVO.act_no }
									
									<tr>
										<td><img width="200" height="200" src="data:image/jpeg;base64,${actiSvc.findByActNo(actlikeVO.act_no).act_pic_base64}"></td>
										<td>${actiSvc.findByActNo(actVO.act_no).act_name }</td>
										<td>${actiSvc.findByActNo(actVO.act_no).act_start }</td>
										<td>${actiSvc.findByActNo(actVO.act_no).act_end }</td>
										<td>${actiSvc.findByActNo(actVO.act_no).act_art }</td>
									</tr>
								
								</c:forEach>	



</body>
</html>