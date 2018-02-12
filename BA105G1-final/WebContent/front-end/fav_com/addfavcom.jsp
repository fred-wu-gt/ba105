<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.fav_com.model.*"%>
<%@ page import="java.util.*"%>
<%
   CommodityVO comVO = (CommodityVO) request.getAttribute("commodityVO");
%>

<%
  CommodityService comSvc = new CommodityService();
  List<CommodityVO> list = comSvc.getAll();
  pageContext.setAttribute("list",list);
%> 

<%
   Fav_comVO fav_comVO = (Fav_comVO) request.getAttribute("fav_comVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料新增 - addMember.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  
   #img{
	display:${empty memVO.mem_photo?"none":""};
	width:200px;
  }
  
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>新增收藏商品 - addfavcom.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<jsp:useBean id="commoditySvc" scope="page" class="com.commodity.model.CommodityService" />
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/fav_com/fav_com.do" >
<ul>
     <li>
        <b>輸入會員編號 (如MEM0000001):</b>
        <input type="text" name="mem_no">
     </li>
     
    
   
  <li>
   
       <b>選擇收藏商品編號:</b>
       <select size="1" name="com_no">
         <c:forEach var="comVO" items="${commoditySvc.all}" > 
          <option value="${comVO.com_no}">${comVO.com_no}
         </c:forEach>   
       </select>
       
  </li>
     
    
</ul>

<br>

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>

</body>


</script>
</html>