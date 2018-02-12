<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rep_Com: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
</style>

</head>
<body>

<table id="table-1">
   <tr><td><h3>Rep_Com: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Rep_Com: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllRep_com.jsp'>List</a> all Order_Master. <br><br></li>
  

  

<%--   <jsp:useBean id="ord_masSvc" scope="page" class="com.ord_mas.model.Ord_masService"/> --%>
   					
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/Ord_Mas/ord_mas.do" > --%>
<!--        <b>選擇檢舉商品報告編號:</b> -->
<!--        <select size="1" name="ord_no"> -->
<%--          <c:forEach var="ord_masVO" items="${ord_masSvc.all}" >  --%>
<%--           <option value="${ord_masVO.ord_no}">${ord_masVO.ord_no} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--     <FORM METHOD="post" ACTION="ord_mas.do" > -->
<!--         <b>輸入檢舉商品報告編號: (2017-12-260000000003):</b> -->
<!--         <input type="text" name="ord_no"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  

<!-- </ul> -->


<h3>商品檢舉報告管理</h3>

<ul>
  <li><a href='addRep_com.jsp'>新增</a>商品檢舉</li>
</ul>




</body>
</html>