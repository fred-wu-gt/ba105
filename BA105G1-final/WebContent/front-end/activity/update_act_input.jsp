<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.regex.*" %>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.act_cat.model.*"%>
<%@ page import="com.fruit.model.*"%>
<%
ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO");
%>
<%@ include file="/front-end/HtmlHeadPage.jsp" %>
	<head>
		<style type="text/css">
			#img{
				width:200px;
				display:${empty activityVO.act_pic_base64?"none":""};
			}
			#memhome{
				display:${empty sessionScope.shop_no ? "":"none"}
			}
			#shophome{
				display:${empty sessionScope.shop_no ? "none":""}
			}
			.centerTable {
			    border: solid 10px;
			    padding-top: 10px;
			    padding-bottom: 10px;
			}
			.inputTable td{
				padding: 5px;
			}
			.t4textarea{
				resize: none;
			}
			.subBtn:hover{
				background-color: 	#f8db95;
			}
			
			.BacklistBtn:hover{
				background-color: #cff6d8;
			}
		</style>
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
$(function(){
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
		  $.datetimepicker.setLocale('zh');
	        $('#f_date1').datetimepicker({
	           theme: '',              //theme: 'dark',
	 	       timepicker:true,
	 	       step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
	 	       format:'Y-m-d H:i:s',
	 		   value: '<%=new java.sql.Date(activityVO.getAct_start().getTime())%>',
	 		   onShow:function(){
		   		this.setOptions({
		    		maxDate:$('#f_date2').val()?$('#f_date2').val():false
		   		})
 			   }
	          
	        });
	        
	        $('#f_date2').datetimepicker({
	           theme: '',              //theme: 'dark',
	  	       timepicker:true,
	  	       step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
	  	       format:'Y-m-d H:i:s',
	  		   value: '<%=new java.sql.Date(activityVO.getAct_end().getTime())%>', // value:   new Date(),
	  		   onShow:function(){
	  		   	 this.setOptions({
	  		    	 minDate:$('#f_date1').val()?$('#f_date1').val():false
	  		     })
	  		   }
	  		  
	         });    
})
  
</script>
	</head>
	<%@ include file="/front-end/HeaderPage.jsp" %><!-- 內文開始 -->
				<div class="col-xs-12 col-sm-10 col-sm-offset-1 centerTable">
					<table border='1' cellpadding='5' cellspacing='0' style='width: 100%'>
						<tr align='center' valign='middle' height='20'>
							<td>
								<div class='form-title'>
									<h1>活動修改</h1>
								</div>
								<hr>
						    </td>
						</tr>
					</table>				

						<%-- 錯誤列表--%>
						<c:if test="${not empty errorMsgs}">
							<font color='red'>請修正以下錯誤:
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ul>
							</font>
						</c:if>
					<FORM METHOD="post" ACTION="activity.do" name="form1" enctype="multipart/form-data">
					<table class="inputTable">
						<tr>
							<td>活動標題</td>
							<td colspan="2"><input type="text" name="act_name" size="45" maxLength="30"
								 value="<%= (activityVO==null)? "" : activityVO.getAct_name()%>" /></td>
						</tr>
						<jsp:useBean id="act_catSvc" scope="page" class="com.act_cat.model.Act_catService" />
						<jsp:useBean id="fruitSvc" scope="page" class="com.fruit.model.FruitService" />
						<tr>
							<td>活動分類:</td>
							<td>
								<c:forEach var="fruitVO" items="${fruitSvc.all}">
								${act_catSvc.findByActNo(activityVO.act_no).fru_no==fruitVO.fru_no? fruitVO.fru_name:''}
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>活動開始時間</td>
							<td colspan="2"><input type="text" name="act_start" id="f_date1"/></td>
						</tr>
						<tr>
							<td>活動結束時間</td>
							<td colspan="2"><input type="TEXT" name="act_end" id="f_date2"/></td>
						</tr>
						<tr>
							<td>活動內文</td>
							<td colspan="2"><textarea name="act_art" maxlength="66" rows="4" cols="50" class="t4textarea">${activityVO.act_art}</textarea></td>
						</tr>
						<tr>
							<td>上傳照片:</td>
							<td> <input width="100" accept="image/*" id="uploadImage" type="file" name="act_pic"></td>
						</tr>
					</table>
					<br>
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="img" value="${empty activityVO.act_pic_base64? '' : activityVO.act_pic_base64}">
					<input type="hidden" name="act_no" value="${activityVO.act_no}">
					<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
					<input type="hidden" name="shop_no" value="${activityVO.shop_no}">
					<input type="submit" value="送出修改" class="btn btn-default btn-lg subBtn">
					<a href="<%=request.getContextPath()%>/front-end/activity/activity_shop_home.jsp" class="btn btn-default btn-lg BacklistBtn" >回活動列表</a>	
					</FORM>
			    </div>
				<img id="img" src="<%= (activityVO==null)? "" : "data:image/jpeg;base64,"+activityVO.getAct_pic_base64() %>" />
				
			<%@ include file="/front-end/FooterPage.jsp" %>