<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
   MemberVO memVO = (MemberVO) request.getAttribute("memVO");
%>

<%@ include file="/front-end/HtmlHeadPage.jsp" %> <%-- ~~必須include1/3~~ --%>
<head>

<title>會員資料新增 - addMember.jsp</title>

		<style type="text/css">
			.aa{
				width:100%
				height:100%;
			}


			.table>tbody>tr>td{
				border-top:0px;
				
			}	
			
			
		 #img{
			display:${empty memVO.mem_photo?"none":""};
			width:200px;
 			 }
 	
 			 .gender{
				
				margin-right:164px;
			}	
			 
		</style>

</head>
<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/register/RegisterHandler" name="form1"
		enctype="multipart/form-data"  >

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>


			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-3">
						

					</div>
					<div class="col-xs-12 col-sm-6">
						<div class="panel panel-info">
						  <div class="panel-heading">
						    <h3 class="panel-title">註冊會員</h3>
						  </div>
						  <div class="panel-body">
						  
						    
						  
												<div class="form-group form-inline ">
													<label>會員姓名:&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
														type="text" name="mem_name" id="mem_name" size="33"
														maxlength="30"
														value="<%=(memVO == null) ? "" : memVO.getMem_name()%>" />
												</div>

												<div class="form-group form-inline bir">
													<label>會員生日:&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
														name="mem_bir" id="f_date1" type="text" size="33"
														maxlength="30" value="" />
												</div>

												<div class="form-group form-inline ">
													<label>會員帳號:&nbsp;&nbsp;&nbsp;&nbsp;</label> 
													<input type="text" name="mem_id" id="mem_id2" maxLength="30" size="33"  
													value="<%=(memVO == null) ? "" : memVO.getMem_id()%>"/>
												</div>

												<div class="form-group form-inline ">
<!-- 													<label>輸入密碼:&nbsp;&nbsp;&nbsp;&nbsp;</label>  -->
													<input
														type="hidden" name="mem_psw" id="mem_psw2" size="33"
														maxlength="30"
														value="<%=(memVO == null) ? "" : memVO.getMem_psw()%>"/>
												</div>

												<div class="form-group form-inline ">
<!-- 													<label>確認密碼:&nbsp;&nbsp;&nbsp;&nbsp;</label>  -->
													<input
														type="hidden" name="check" id="check" size="33"
														maxlength="30"
														value="<%=(memVO == null) ? "" : memVO.getMem_psw()%>" />
												</div>

												<div class="form-group form-inline ">
													<label>電話號碼:&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
														type="text" name="mem_phone" id="mem_phone" size="33"
														maxlength="10"
														value="<%=(memVO == null) ? "" : memVO.getMem_phone()%>" />
												</div>

												<div class="form-group form-inline  gender">
													<label>性別:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
													<input type="radio" name="mem_gen" value="男" id="man"
														${(memVO.mem_gen).equals("男")?"checked":""} />男性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" name="mem_gen" value="女" id="woman"
														${(memVO.mem_gen).equals("女")?"checked":""} />女性
												</div>

												<div class="form-group form-inline ">
													<label>電子郵件:&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
														type="email" name="mem_email" id="mem_email" size="33"
														maxlength="30"
														value="<%=(memVO == null) ? "" : memVO.getMem_email()%>"/ >
												</div>

												<div class="form-group form-inline ">
													<label>聯絡地址:&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
														type="text" name="mem_loc" id="mem_loc" size="33"
														maxlength="30"
														value="<%=(memVO == null) ? "" : memVO.getMem_loc()%>" />
												</div>


												<div class="form-group form-inline ">
<!--  													<label>會員點數:&nbsp;&nbsp;&nbsp;&nbsp;</label>  -->
                                                        <input type="hidden" name="mem_val" id="mem_val" size="33" maxlength="30"
														value="<%=(memVO == null) ? "0" : memVO.getMem_val()%>" />
												</div>



												<table>
													<tr>
														<th><label>會員照片:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<th>
														<th align="left"><input type="file" name="mem_photo"
															id="uploadImage" size="28" maxlength="30"
															accept="image/*" onchange="readImage(this)"></th>
															
													</tr>
													
												</table>
													<img id="img" width="200" src="<%=(memVO == null) ? "" : "data:image/jpeg;" + memVO.getMem_photo()%>">
												<div class="form-group form-inline ">
													<!-- 														<label>一般會員狀態:&nbsp;&nbsp;&nbsp;&nbsp;</label> -->
													<input type="hidden" name="mem_stat" id="mem_stat"
														size="33" maxlength="30"
														value="<%=(memVO == null) ? "正常" : memVO.getMem_stat()%>" />
												</div>

												<div class="form-group form-inline ">
													<!-- 														<label>一般會員違規記點:&nbsp;&nbsp;&nbsp;&nbsp;</label> -->
													<input type="hidden" name="mem_poin" id="mem_poin"
														size="33" maxlength="30"
														value="<%=(memVO == null) ? "0" : memVO.getMem_poin()%>" />
												</div>


												<br> <input type="hidden" name="action" value="memberRegister">
												<input type="hidden" name="mem_photo" value="${memVO.mem_photo}">
												<input type="hidden" name="mem_name" value="${memVO.mem_name}">
												<input type="submit" value="送出新增"> <input
													type="button" value="取消"
													onclick="location.href='<%=request.getContextPath()%>/front-end/index.jsp';">
													
												<br><button type="button" class="btn btn-default btn-xs" onclick="myFunction2()">magical</button>	
										
						    
						    
						  </div>
						</div>

					</div>
					<div class="col-xs-12 col-sm-3">
						

					</div>
				</div>
			</div>


		</FORM>










<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date mem_bir = null;
  try {
	  mem_bir = memVO.getMem_bir();
   } catch (Exception e) {
	   mem_bir= new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />

<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '', // value:   new Date(),
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
        
    
//  			window.onload=init;
//  			function init(){
//  			$("#addMember2").submit(function(){
//  				if($(this).find("#mem_id").val().trim()=='' || $(this).find("#mem_psw").val().trim()==''){
//  					//sweetAlert("註冊未完成","");
//  					//event.preventDefault();
//  				}else{
//  					sweetAlert("註冊成功","");
 					
//  				}
 					
//  			});
 			
//  			}
 			
//  	window.onload=init;	
 	
    function myFunction2() {
		document.getElementById("mem_name").value = "屏東郭富城";
		document.getElementById("f_date1").value = "2017-01-04";
		document.getElementById("mem_id2").value="MEM15";
		document.getElementById("mem_psw2").value ="Q0RjUqwb";
		document.getElementById("check").value = "Q0RjUqwb";
		document.getElementById("man").checked = true;
		document.getElementById("mem_loc").value="台北市大安區忠孝東路三段300號";
	}
		
 		
</script>
<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>