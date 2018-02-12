<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_mes_report.model.*"%>

<%
	Wri_mes_reportVO wri_mes_reportVO = (Wri_mes_reportVO) request.getAttribute("wri_mes_reportVO"); //Wri_mes_reportServlet.java (Controller) 存入req的wri_mes_reportVO物件 (包括幫忙取出的wri_mes_reportVO, 也包括輸入資料錯誤時的wri_mes_reportVO物件)
%>

<html>

<%@ include file="/front-end/HtmlHeadPage.jsp" %>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章留言檢舉資料修改 - update_writing_mes_report_input.jsp</title>

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

<%@ include file="/front-end/HeaderPage.jsp" %>

<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>文章留言檢舉資料修改 - update_writing_mes_report_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>文章留言檢舉資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="writing_mes_report.do" name="form1">
		<table>
			<tr>
				<td>文章留言檢舉編號:</td>
				<td><input type="TEXT" name="wmrpt_no" size="45"
					value="<%=wri_mes_reportVO.getWmrpt_no()%>" /></td>
			</tr>
			<tr>
				<td>文章留言編號:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="wmsg_no" size="45"
					value="<%=wri_mes_reportVO.getWmsg_no()%>" /></td>

			</tr>
			<tr>
				<td>一般會員編號:</td>
				<td><input type="TEXT" name="mem_no" size="45"
					value="<%=wri_mes_reportVO.getMem_no()%>" /></td>
			</tr>
			<tr>
				<td>文章留言檢舉原因:</td>
				<td><input type="TEXT" name="wmrpt_rsn" size="45"
					value="<%=wri_mes_reportVO.getWmrpt_rsn()%>" /></td>

			</tr>
			<tr>
				<td>文章留言檢舉狀態:</td>
				<td><input type="TEXT" name="wmrpt_stat" size="45"
					value="<%=wri_mes_reportVO.getWmrpt_stat()%>" /></td>
			</tr>

			<tr>
				<td>文章留言檢舉內容:</td>
				<td><input type="TEXT" name="wmrpt_cont" size="45"
					value="<%=wri_mes_reportVO.getWmrpt_cont()%>" /></td>
			</tr>


			<jsp:useBean id="wri_mes_reportSvc" scope="page"
				class="com.wri_mes_report.model.Wri_mes_reportService" />


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="wmrpt_no"
			value="<%=wri_mes_reportVO.getWmrpt_no()%>"> <input
			type="submit" value="送出修改">
	</FORM>
	<%@ include file="/front-end/FooterPage.jsp" %>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',

	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>