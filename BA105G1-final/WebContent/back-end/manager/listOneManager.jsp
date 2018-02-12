<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.manager.model.*"%>
<%
	ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO"); //ManagerServlet.java (Concroller) 存入req的ManagerVO物件 (包括幫忙取出的ManagerVO, 也包括輸入資料錯誤時的ManagerVO物件)
%>
<%@ include file="/back-end/be_HtmlHeadPage.jsp" %>

<head>

	<style>
	#addManager_div #mf_des{
		background-image: linear-gradient(to bottom right, white , lightpink,lightpink, white);
		width:100%;
		height:290px;
		padding:10px;
		margin-top:30px;
		border-radius:10px;
		border:white 1px outset;
	}
	#addManager_div input{
		width:100%;
	}
	#addressDiv .form-control{
		padding:6px;
	}
	#addManager_div .noPadding{
		padding:0px;
	}
	#addManager_div .inputTitle{
		margin-right:5px;
	}
	#addManager_div #imgDiv{
		visibility:${empty managerVO.man_pho_base64?"hidden":"visible"};
	}
	#addressDiv .form-control input,#addressDiv select{
		height:100%;
	}
	#addManager_div .input-group-addon{
		background-image: radial-gradient(ellipse,white,#ecfeff,#d9fafd);
	}
	#addManager_div #img{
		height:100%;
	}
	#addManager_div .checkBoxSpan.form-control{
		width:50px;
	}
	#addManager_div .input-group-addon,#addManager_div button{
		font-size:1em;
	}
	.errorMsgs{
		color:red;
	}
</style>
<script>
$(function(){
	$("#ggg").addClass("in");
	$("#ggg1").css("background-image","radial-gradient(ellipse,white,white,#5bc0de)");

})
</script>
</head>
<%@ include file="/back-end/be_HeaderPage.jsp" %>


	<div class="row"  id="addManager_div">
<!-- 		基本資料開始 -->
		<div class="col-xs-12">
		    	<div>
					<h3 class="breadcrumbs">管理員>查詢/修改管理員><b>管理員詳細資料</b></h3>
				</div>
		    	<div>
					<span><b>基本資料:</b></span>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-6">				
				    	<div class="input-group">
							<span class="input-group-addon">管理員編號</span>
							<span class="form-control cannotModify"><%= (managerVO==null)? "" : managerVO.getMan_no()%></span>				
				    	</div>
			    	</div>
			    </div>
				<div class="row">
					<div class="col-xs-12 col-sm-6">				    		    	
				    	<div class="input-group">
							<span class="input-group-addon">帳號(信箱)</span>
							<span class="form-control cannotModify"><%= (managerVO==null)? "" : managerVO.getMan_id()%></span>		
				    	</div>
			    	</div>
			    </div>		    	
			    <div class="row">
					<div class="col-xs-12 col-sm-6">
				    	<div class="input-group">
				    		<span class="input-group-addon">密碼</span>
							<span class="form-control cannotModify"><%= (managerVO==null)? "" : managerVO.getMan_pas()%></span>	
				    	</div>
			    	</div>
			    </div>					    	

			    <div class="row">
					<div class="col-xs-12 col-sm-6">					    	
				    	<div class="input-group">
				    		<span class="input-group-addon">姓名</span>
							<span class="form-control cannotModify"><%= (managerVO==null)? "" : managerVO.getMan_name()%></span>	
				    	</div>
				    </div>
			    </div>		
			    			   
			    <div class="row">
					<div class="col-xs-12 col-sm-6">					   
						<div class="input-group">
							<span class="input-group-addon">性別</span>
				      		<span class="form-control">
				      			<input disabled type="radio" name="man_gen" value="男" ${(managerVO.man_gen).equals("男")?"checked":""} aria-label="Radio button for following addon"/>
				      		</span>
				      		<span class="input-group-addon" aria-label="Addon with radio button">
				      			男
				      		</span>
				      		<span class="form-control">
				      			<input disabled class="input-group-addon" type="radio" name="man_gen" value="女" ${(managerVO.man_gen).equals("女")?"checked":""} aria-label="Radio button for following addon" style="margin-top:-4px;"/>
				      		</span>
				      		<span class="input-group-addon" aria-label="Addon with radio button">
				      			女
				      		</span>
				    	</div>
				    </div>
			    </div>	
			    				    	
			    <div class="row">
					<div class="col-xs-12 col-sm-6">					    	
				    	<div class="input-group">
				    		<span class="input-group-addon">手機</span>
							<span class="form-control cannotModify"><%= (managerVO==null)? "" : managerVO.getMan_tel()%></span>
				    	</div>
				    </div>
			    </div>	

<c:set var="addPart" value="${fn:split(managerVO.man_add,'_')}"/>			    	
			    <div class="row">
					<div class="col-xs-12 col-sm-6">					    	
				    	<div class="input-group">
				    		<span class="input-group-addon">地址</span>
							<span class="form-control cannotModify">${fn:replace(managerVO.man_add,"_","")}</span>
				    	</div>
				    </div>
			    </div>
			    
			    <div class="row">
					<div class="col-xs-12 col-sm-6">					    	
				    	<div class="input-group">
				    		<span class="input-group-addon">狀態</span>
							<span class="form-control cannotModify"><%= (managerVO==null)? "" : managerVO.getMan_sta()%></span>
				    	</div>
				    </div>
			    </div>

			    <div class="row">
					<div class="col-xs-12 col-sm-6">				    
				    	<div class="input-group" id="imgDiv">
							<span class="input-group-addon">照片</span>
							<img class="form-control" id="img" src="<%= (managerVO==null)? "" : "data:image/jpeg;base64,"+managerVO.getMan_pho_base64() %>"/>
				    	</div>
					</div>
			    </div>
				    	
		<jsp:useBean id="Man_funSvc" scope="page" class="com.man_fun.model.Man_funService" />

		</div><br><br>
<!-- 		基本資料結束 -->
<!-- 			權限開始 -->
		<div class="col-xs-12">	
			<div class="col-xs-12 col-sm-4">
					<div class="row">		
			    	<div>
						<span><b>權限:</b></span>
					</div>
	
<c:forEach var="man_funVO" items="${Man_funSvc.all}" varStatus="s">
					<div class="input-group" style="margin-bottom:3px">
						<span class="mf_checkbox form-control checkBoxSpan"><input disabled type="checkbox" name="mf_no" value="${man_funVO.mf_no}" <c:forEach var="man_autVO" items="${man_autVOList}">${(man_funVO.mf_no==man_autVO.mf_no)? 'checked':'' } </c:forEach> class="checkbox"  aria-label="Checkbox for following text input"></span>
						<input type="hidden" value="${s.index}">
						<span class="mf_name input-group-addon"  style="width:100%" aria-label="Text input with checkbox">${man_funVO.mf_name}</span>
						<input type="hidden" value="${s.index}">
					</div>
</c:forEach>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div id="mf_des" class="noPadding"></div>
			</div>
<!-- 			權限結束 -->		
		</div>
	</div>
<script>
var array=[];
<c:forEach var="man_funVO" items="${Man_funSvc.all}" varStatus="s">
	array[${s.index}]= "${man_funVO.mf_des}" ;
</c:forEach>

$(".mf_checkbox").hover(function(){
	  $(this).css("background-image","radial-gradient(ellipse,white,#5bc0de)");
	  $("#mf_des").html("<b>權限說明:</b><br>"+array[$(this).next().val()]);
},function(){
	  $(this).css("background-image","none");
	  $("#mf_des").html("");
});
$(".mf_name").hover(function(){
	  $(this).css({"color":"blue","font-size":"1.2em"});
	  $(this).parent().css({"margin-bottom":"1.7px"});
	  $("#mf_des").html("<b>權限說明:</b><br>"+array[$(this).next().val()]);
},function(){
	  $(this).css({"color":"black","font-size":"1em"});
	  $(this).parent().css({"margin-bottom":"3px"});
	  $("#mf_des").html("");
});
</script>
<%@ include file="/back-end/be_FooterPage.jsp" %>