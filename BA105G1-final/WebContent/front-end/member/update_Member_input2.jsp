<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ include file="/front-end/HtmlHeadPage.jsp"%>
<%
    //MemberVO memVO = (MemberVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
    MemberVO memVO = (MemberVO)session.getAttribute("loginMemberVO");
    request.setAttribute("memVO", memVO);
%>

<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<title>會員資料修改 - update_Member_input.jsp</title>

<style>


</style>

<style>

 
</style>

</head>
<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>

<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<FORM METHOD="post" ACTION="member.do" name="form1"
		enctype="multipart/form-data">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-3"></div>
				<div class="col-xs-12 col-sm-6">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">修改會員資料</h3>
						</div>
						<div class="panel-body">

							<label>*必填欄位</label>
							<div class="form-group form-inline ">
								<label>*會員帳號:&nbsp;&nbsp;&nbsp;&nbsp;${memVO.mem_id}</label>
							</div>

							<div class="form-group form-inline ">
								<label>*會員密碼:&nbsp;&nbsp;&nbsp;&nbsp;<input type="password"
									name="mem_psw" size="40" value="" /></label>
							</div>

							<div class="form-group form-inline ">
								<label>*會員姓名:&nbsp;&nbsp;&nbsp;&nbsp;<input type="TEXT"
									name="mem_name" size="40" value="${memVO.mem_name}" /></label>
							</div>

							<div class="form-group form-inline ">
								<label>*會員性別:&nbsp;&nbsp;&nbsp;&nbsp;${memVO.mem_gen}</label>
							</div>

							<div class="form-group form-inline ">
								<label>*會員生日:&nbsp;&nbsp;&nbsp;&nbsp;${memVO.mem_bir}</label>
							</div>

							<div class="form-group form-inline ">
								<label>*會員電郵:&nbsp;&nbsp;&nbsp;&nbsp;<input type="Email"
									name="mem_email" size="40" value="${memVO.mem_email}" /></label>
							</div>

							<div class="form-group form-inline ">
								<label>*會員電話:&nbsp;&nbsp;&nbsp;&nbsp;<input type="TEXT"
									name="mem_phone" size="40" value="${memVO.mem_phone}" /></label>
							</div>

							<div class="form-group form-inline ">
								<label>*會員地址:&nbsp;&nbsp;&nbsp;&nbsp;<input type="TEXT"
									name="mem_loc" size="40" value="${memVO.mem_loc}" /></label>
							</div>

							<div class="form-group form-inline ">
								<label>*會員照片:&nbsp;&nbsp;&nbsp;&nbsp;<input type="file"
									name="mem_photo" id="uploadImage" accept="image/*"
									onchange="readImage(this)"></label> <img id="img"
									src="<%=(memVO == null) ? "" : "data:image/jpeg;base64," + memVO.getMem_photo_base64()%>" />
							</div>
							
							
							<input type="hidden" name="action" value="update">
							<input type="hidden" name="img" value="${memVO.mem_photo_base64}"> 
							<input type="hidden" name="mem_no" value="${memVO.mem_no}"> 
							<input type="hidden" name="mem_id" value="${memVO.mem_id}"> 
							<input type="hidden" name="mem_gen" value="${memVO.mem_gen}"> 
							<input type="hidden" name="mem_bir" value="${memVO.mem_bir}"> 
							<input type="hidden" name="mem_stat" value="${memVO.mem_stat}"> 
							<input type="hidden" name="mem_poin" value="${memVO.mem_poin}"> 
							<input type="hidden" name="mem_val" value="${memVO.mem_val}"> 
							<input type="submit" value="送出修改">

						</div>
					</div>

				</div>
				<div class="col-xs-12 col-sm-3"></div>
			</div>
		</div>


		
	</FORM>




	<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>會員資料修改 - update_Member_input.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->
<!-- <h3>資料修改:</h3> -->
<%-- <%-- 錯誤表列 --%> 
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<!-- <!-- <FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data"> --> 
<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<td>一般會員編號:<font color=red><b>*</b></font></td> -->
<%-- 		<td>${memVO.mem_no}</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>一般會員帳號:</td> -->
<%-- 		<td>${memVO.mem_id}</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>一般會員密碼:</td> -->
<%-- 		<td><input type="TEXT" name="mem_psw" size="45" value="${memVO.mem_psw}" /></td> --%>
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<td>一般會員姓名:</td> -->
<%-- 		<td><input type="TEXT" name="mem_name" size="45" value="${memVO.mem_name}" /></td> --%>
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<td>一般會員性別:<font color=red><b>*</b></font></td> -->
<%-- 		<td>${memVO.mem_gen}</td>	 		  --%>
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<td>一般會員生日:<font color=red><b>*</b></font></td> -->
<%-- 		<td>${memVO.mem_bir}</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>一般會員電子郵件:</td> -->
<%-- 		<td><input type="Email" name="mem_email" size="45" value="${memVO.mem_email}" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>一般會員電話:</td> -->
<%-- 		<td><input type="TEXT" name="mem_phone" size="45" value="${memVO.mem_phone}" /></td> --%>
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<td>一般會員地址:</td> -->
<%-- 		<td><input type="TEXT" name="mem_loc" size="45" value="${memVO.mem_loc}" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>一般會員照片:</td> -->
<!-- 		<td><input type="file" name="mem_photo"  id="uploadImage"   accept="image/*" onchange="readImage(this)" >	 -->
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>一般會員狀態:</td> -->
<%-- 		<td><input type="TEXT" name="mem_stat" size="45" value="${memVO.mem_stat}" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>一般會員違規記點:</td> -->
<%-- 		<td><input type="TEXT" name="mem_poin" size="45" value="${memVO.mem_poin}" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>一般會員點數:</td> -->
<%-- 		<td><input type="TEXT" name="mem_val" size="45" value="${memVO.mem_val}" /></td> --%>
<!-- 	</tr> -->

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

<!-- </table> -->
<!-- <br> -->
<!-- <input type="hidden" name="action" value="update"> -->
<%-- <input type="hidden" name="img" value="${memVO.mem_photo_base64}"> --%>
<%-- <input type="hidden" name="mem_no" value="${memVO.mem_no}"> --%>
<%-- <input type="hidden" name="mem_id" value="${memVO.mem_id}"> --%>
<%-- <input type="hidden" name="mem_gen" value="${memVO.mem_gen}"> --%>
<%-- <input type="hidden" name="mem_bir" value="${memVO.mem_bir}"> --%>
<!-- <input type="submit" value="送出修改"></FORM> -->
<%-- <img id="img" src="<%= (memVO==null)? "" : "data:image/jpeg;base64,"+memVO.getMem_photo_base64() %>" /> --%>






<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />

<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js">

</script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=memVO.getMem_bir()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
        
        $("#uploadImage").change(function(){
			readImage( this );
				});
    
			function readImage(input) {
			 if ( input.files && input.files[0] ) {
  			var FR= new FileReader();
  			FR.onload = function(e) {
    			//e.target.result = base64 format picture
    		$('#img').attr( "src", e.target.result );
    		$('#img').show();
  			};       
  			FR.readAsDataURL( input.files[0] );
			}
			}
        
   
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
<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>