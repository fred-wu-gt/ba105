<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Commodity: Home</title>

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
   <tr><td><h3>Commodity: Home</h3><h4>( MVC )</h4></td></tr>
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
  <li><a href='listAllCommodity.jsp'>List</a> all Commodity. <br><br></li>
  
   <li><a href='listAllCommodityToScore.jsp'>對商品評分</a>  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="commodity.do" >
        <b>輸入商品編號 (COM0000010):</b>
        <input type="text" name="com_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  

  <jsp:useBean id="comSvc" scope="page" class="com.commodity.model.CommodityService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/commodity/commodity.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="com_no">
         <c:forEach var="comVO" items="${comSvc.all}" > 
          <option value="${comVO.com_no}">${comVO.com_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/commodity/commodity.do" >
       <b>選擇商品名稱:</b>
       <select size="1" name="com_no">
         <c:forEach var="comVO" items="${comSvc.all}" > 
          <option  value="${comVO.com_no}">${comVO.com_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>


</ul>


<h3>商品管理</h3>

<ul>
  <li><a href='addCommodity.jsp'>新增</a>商品</li>
</ul>




</body>
</html>