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
<% 
  java.sql.Date act_start = null;
  try {
	  act_start = new java.sql.Date(activityVO.getAct_start().getTime());
   } catch (Exception e) {
	  act_start = new java.sql.Date(System.currentTimeMillis());
   }
  
  java.sql.Date act_end = null;
  try {
	  act_end = new java.sql.Date(activityVO.getAct_end().getTime());
   } catch (Exception e) {
	  act_end = new java.sql.Date(System.currentTimeMillis());
   }
  
%>
<%@ include file="/front-end/HtmlHeadPage.jsp" %>
	<head>

		<style type="text/css">
			#img{
				width:200px;
				display:${empty activityVO.act_pic_base64?"none":""};
			}
			.centerTable {
			    border: solid 10px;
			    padding-top: 10px;
			    padding-bottom: 10px;
			    border-color: #ccc;
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
		       value: '<%=act_start%>',
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
		       value: '<%=act_end%>',
    		   onShow:function(){
 	   		   this.setOptions({
 	    		 minDate:$('#f_date1').val()?$('#f_date1').val():false
 	   		   })
 	  		 }
	     });  
	})
	  
	</script>
	</head>
	<%@ include file="/front-end/HeaderPage.jsp" %>
	
				<div class="col-xs-12 col-sm-10 col-sm-offset-1 centerTable">
					<table border='1' cellpadding='5' cellspacing='0' style='width: 100%'>
						<tr align='center' valign='middle' height='20'>
							<td>
								<div class='form-title'>
									<h1>活動新增</h1>
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
							<td colspan="2"><input type="text" name="act_name" id="acttitle" size="45" maxLength="30"
								 value="<%= (activityVO==null)? "" : activityVO.getAct_name()%>" /></td>
						</tr>
						<jsp:useBean id="act_catSvc" scope="page" class="com.act_cat.model.Act_catService" />
						<jsp:useBean id="fruitSvc" scope="page" class="com.fruit.model.FruitService" />
						<tr>
							<td>活動分類:</td>
							<td>
								<select size="1" name="fru_no">
									<c:forEach var="fruitVO" items="${fruitSvc.all}">
										<option value="${fruitVO.fru_no}" >${fruitVO.fru_name}
									</c:forEach>
								</select>
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
							<td colspan="2"><textarea name="act_art" id="acttextarea" maxlength="" rows="10" cols="100" class="t4textarea"><%= (activityVO==null)? "" : activityVO.getAct_art()%></textarea></td>
						</tr>
						<tr>
							<td>上傳照片:</td>
							<td> <input width="100" accept="image/*" id="uploadImage" type="file" name="act_pic"></td>
						</tr>
						
					</table>
					<br>
					<!-- 神奇小按鈕 -->
					<div id="magicInsert" style="width: 20px; height: 20px;background-color: #d2e9e6;" ></div>
					<!-- 神奇小按鈕 -->
					<input type="hidden" name="action" value="insert">
					<input type="hidden" name="shop_no" value="${loginShopVO.shop_no}" />
					
					<input type="hidden" name="img" value="${empty activityVO.act_pic_base64? '' : activityVO.act_pic_base64}">
					<input type="submit" value="送出新增" class="btn btn-default btn-lg subBtn">
					<a href="<%=request.getContextPath()%>/front-end/activity/activity_shop_home.jsp" class="btn btn-default btn-lg BacklistBtn" >回活動列表</a>	
					</FORM>
			    </div>
				<img id="img" src="<%= (activityVO==null)? "" : "data:image/jpeg;base64,"+activityVO.getAct_pic_base64() %>" />
							
<%@ include file="/front-end/FooterPage.jsp" %>

<script>
$("#magicInsert").click(function(){
	$("input[id='acttitle']").val("春節強檔！ 1月份一日遊熱烈揪團中～");
	$("textarea[id='acttextarea']").val("新年快樂！<br>春節期間，不管甚麼地方都人擠人嗎？<br>想要趁著難得的假期放鬆身心，卻愁著沒有一個合適的所在嗎？<br><br>"+
			"別擔心！「小田農創」已經備好我們的春節特別遊程，等你來體驗！<br>這回我們一次開了好多團，任君挑選唷！<br>絕對不用怕來到這裡還要人擠人～<br>"+
			"來到這裡，廣闊的農村就是你的伸展台！<br>名額有限，要搶要快喔～～～<br>集合地點：崎頂保興宮<br>集合時間：早上08:50<br>"+
			"遊程計費: 1人600元，1~12歲小孩1人50元，統一在活動報名截止前匯款，不開放現場繳費囉！<br>匯款帳號: 700 豐原郵局，帳號 0141009 - 1395560，麻煩在一日遊【當天的前一個周四】中午12點以前匯款完成～<br>"+
			"匯款成功者請以訊息通知 「小田農創」，並告知姓名以便做確認喔～<br>來來來！介紹最可愛的地瓜家族給你們認識唷～<br><br>=====此次遊程大致如下=====<br><br>"+
			"08:50~09:00 發現崎頂～崎頂保興宮集合！<br>09:10~10:00 小農之路～讓我們了解崎頂的在地農作物吧！<br>10:10~11:00 地瓜割割?～割地瓜藤體驗，把過去的壞運都割光光喔！<br>"+
			"11:00~12:00 地瓜挖挖?～挖地瓜體驗，寶藏都在沙地裡！<br>12:10~13:10 崎頂一小食～餓壞囉！在地食材風味中餐<br>13:10~13:50 地瓜拔拔?～拔絲地瓜DIY！<br>14:00~15:00 農地尋寶～小番茄採果體驗，帶走一顆顆的紅寶石吧！<br>"+
			"15:00~16:00 子母隧道～神隱少女般，充滿歷史情懷的古隧道<br>16:00~17:00 落日餘暉～我們彼此約定，下次再見<br>。==========================<br>註：地瓜另購一斤20元，小番茄一斤150元，友善小農包一包300元。<br>");
});
</script>