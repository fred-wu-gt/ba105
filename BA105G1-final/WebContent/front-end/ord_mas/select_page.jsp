<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ord_Mas: Home</title>

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
   <tr><td><h3>Ord_Mas: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Commodity: Home</p>

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
  <li><a href='listAllOrd_Mas.jsp'>List</a> all Order_Master. <br><br></li>
  

  

  <jsp:useBean id="ord_masSvc" scope="page" class="com.ord_mas.model.Ord_masService"/>
  
      <li>
    <FORM METHOD="post" ACTION="ord_mas.do" >
        <b>由會員編號找訂單主檔: (MEM0000008):</b>
        <input type="text" name="mem_no">
        <input type="hidden" name="action" value="getList_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  
  
  
  
   					
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/ord_mas/ord_mas.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="ord_no">
         <c:forEach var="ord_masVO" items="${ord_masSvc.all}" > 
          <option value="${ord_masVO.ord_no}">${ord_masVO.ord_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="ord_mas.do" >
        <b>輸入訂單編號: (2017-12-260000000003):</b>
        <input type="text" name="ord_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  

  
  
  
  
  
  
  <li>
      <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/ord_mas/ord_mas.do" >
       <b><font color=orange>選擇訂單編號查明細:</font></b>
       <select size="1" name="ord_no">
         <c:forEach var="ord_masVO" items="${ord_masSvc.all}" > 
          <option value="${ord_masVO.ord_no}">${ord_masVO.ord_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="listOrd_det_ByOrd_no">
     </FORM>
  </li>
  
</ul>


<h3>訂單主檔管理</h3>

<ul>
  <li><a href='addOrd_Mas.jsp'>新增</a>訂單主檔</li>
</ul>




</body>
</html>