<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Favorite_commodity: Home</title>

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
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Favorite_commodity: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Favorite_commodity: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllFav_com.jsp'>List</a> all AllFavorite_commodity.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="fav_com.do" >
        <b>��ܷ|���s�� (�pMEM0000001)�d�ߦ��ðӫ~:</b>
        <input type="text" name="mem_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>






<%--   <jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" /> --%>
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="fav_com.do" > -->
<!--        <b>��ܷ|���b��:</b> -->
<!--        <select size="1" name="mem_id"> -->
<%--          <c:forEach var="memVO" items="${memSvc.all}" >  --%>
<%--           <option value="${memVO.mem_id}">${memVO.mem_id} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--     </FORM> -->
<!--   </li> -->
  
 
</ul>


<h3>�[�J���ú޲z</h3>

<ul>
  <li><a href='addfavcom.jsp'>Add</a> a new fav_com.</li>
</ul>

</body>
</html>