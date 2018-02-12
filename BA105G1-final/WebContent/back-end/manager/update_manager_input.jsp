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
	.cannotModify{
		background-color:#f0f0f0;
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
</head>
<%@ include file="/back-end/be_HeaderPage.jsp" %>


<c:set var="addPart" value="${fn:split(managerVO.man_add,'_')}"/>
<form method="post" action="<%=request.getContextPath()%>/back-end/manager/manager.do" enctype="multipart/form-data">

	<div class="row"  id="addManager_div">
<!-- 		基本資料開始 -->
		<div class="col-xs-12">
		    	<div>
					<h3 class="breadcrumbs">管理員>查詢/修改管理員><b>修改管理員</b></h3>
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
							<input class=" form-control" type="password" name="man_pas" maxLength="30" value="<%= (managerVO==null)? "" : managerVO.getMan_pas()%>" />
				    	</div>
			    	</div>
			    	<div class="col-xs-12 col-sm-6">
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("man_pas")}</span>
			    	</div>
			    </div>	
			    	    	
			    <div class="row">
					<div class="col-xs-12 col-sm-6">		    	
				    	<div class="input-group">
				    		<span class="input-group-addon">確認密碼</span>
							<input class=" form-control" type="password" name="man_pas2" maxLength="30" value="<%= (managerVO==null)? "" : managerVO.getMan_pas()%>" />
				    	</div>
				    </div>
			    	<div class="col-xs-12 col-sm-6">
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("man_pas2")}</span>
			    	</div>
			    </div>					    	

			    <div class="row">
					<div class="col-xs-12 col-sm-6">					    	
				    	<div class="input-group">
				    		<span class="input-group-addon">姓名</span>
							<input class="form-control" type="TEXT" name="man_name" maxLength="10" value="<%= (managerVO==null)? "" : managerVO.getMan_name()%>" />
				    	</div>
				    </div>
			    	<div class="col-xs-12 col-sm-6">
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("man_name")}</span>
			    	</div>
			    </div>		
			    			   
			    <div class="row">
					<div class="col-xs-12 col-sm-6">					   
						<div class="input-group">
							<span class="input-group-addon">性別</span>
				      		<span class="form-control">
				      			<input type="radio" name="man_gen" value="男" ${(managerVO.man_gen).equals("男")?"checked":""} aria-label="Radio button for following addon"/>
				      		</span>
				      		<span class="input-group-addon" aria-label="Addon with radio button">
				      			男
				      		</span>
				      		<span class="form-control">
				      			<input class="input-group-addon" type="radio" name="man_gen" value="女" ${(managerVO.man_gen).equals("女")?"checked":""} aria-label="Radio button for following addon" style="margin-top:-4px;"/>
				      		</span>
				      		<span class="input-group-addon" aria-label="Addon with radio button">
				      			女
				      		</span>
				    	</div>
				    </div>
			    	<div class="col-xs-12 col-sm-6">
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("man_gen")}</span>
			    	</div>
			    </div>	
			    				    	
			    <div class="row">
					<div class="col-xs-12 col-sm-6">					    	
				    	<div class="input-group">
				    		<span class="input-group-addon">手機</span>
							<input class="form-control" type="TEXT" name="man_tel" maxLength="10" value="<%= (managerVO==null)? "" : managerVO.getMan_tel()%>" />
				    	</div>
				    </div>
			    	<div class="col-xs-12 col-sm-6">
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("man_tel")}</span>
			    	</div>
			    </div>	
			    	
			    <div class="row">
					<div class="col-xs-12 col-sm-6">			    			  	
						<div class="input-group" id="addressDiv">
							<span class="input-group-addon">地址</span>
							<div class="form-control noPadding">
								<select class="form-control" id="citySelect" name="citySelect" style="width:25%">
								<option></option>
								<c:forEach var="city" items="${addressMap1.keySet()}">
								<option value="${city}" ${addPart[1]==city?"selected":""}>${city}</option>
								</c:forEach>
								</select>
								<select class="form-control" id="areaSelect" name="areaSelect" style="width:25%"></select>
								<input class="form-control" list="roadSelect" name="roadSelect" id="roadSelectInput" value="${addPart[3]=='?'?'':addPart[3]}" style="width:50%">
								<datalist id="roadSelect"></datalist>
							<datalist id="zipSelect"></datalist>
							</div>
							<div class="input-group" style="width:100%">
								<input class="form-control" style="padding-left:12px" type="TEXT" name="add" size="45" maxLength="20" value="${addPart[4]=='?'?'':addPart[4]}"/>
								<span class="input-group-addon">郵遞區號</span>
								<input class="form-control" list="zipSelect" name="zipSelect" id="zipSelectInput" maxLength="5" value="${addPart[0]=='?'?'':addPart[0]}">
							</div>
				    	</div>
				    </div>
			    	<div class="col-xs-12 col-sm-6">
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("citySelect")}</span>
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("areaSelect")}</span>
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("roadSelect")}</span>
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("add")}</span>
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("zipSelect")}</span>
			    	</div>
			    </div>	
			    <div class="row">
					<div class="col-xs-12 col-sm-6">				    		
						<div class="input-group">
				    		<span class="input-group-addon">狀態</span>
							<select name="man_sta" class="form-control">
								<option value="正常" ${(managerVO.man_sta).equals("正常")?'selected':'' }>正常</option>
								<option value="停權" ${(managerVO.man_sta).equals("停權")?'selected':'' }>停權</option>
							</select>
				    	</div>
			    	</div>
			    </div>					  
			    <div class="row">
					<div class="col-xs-12 col-sm-6">				    		   
				    	<div class="input-group">
				    		<span class="input-group-addon">照片</span>
							<input class="form-control" width="100" accept="image/*" id="uploadImage" type="file" name="man_pho">
				    	</div>
				    </div>
			    	<div class="col-xs-12 col-sm-6">
			    		<span class="errorMsgs">${empty errorMsgs?"":errorMsgs.get("man_pho")}</span>
			    	</div>
			    </div>	
			    <div class="row">
					<div class="col-xs-12 col-sm-6">				    
				    	<div class="input-group" id="imgDiv">
							<span class="input-group-addon">圖片預覽</span>
							<img class="form-control" id="img" src="<%= (managerVO==null)? "" : "data:image/jpeg;base64,"+managerVO.getMan_pho_base64() %>" align="right"/>
				    	</div>
					</div>
			    </div>
				    	
		<input type="hidden" name="img" value="${empty managerVO.man_pho_base64?'':managerVO.man_pho_base64}">
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="man_no" value="${managerVO.man_no}">
		<input type="hidden" name="man_id" value="${managerVO.man_id}">
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
						<span class="mf_checkbox form-control checkBoxSpan"><input type="checkbox" name="mf_no" value="${man_funVO.mf_no}" <c:forEach var="man_autVO" items="${man_autVOList}">${(man_funVO.mf_no==man_autVO.mf_no)? 'checked':'' } </c:forEach> class="checkbox"  aria-label="Checkbox for following text input"></span>
						<input type="hidden" value="${s.index}">
						<span class="mf_name input-group-addon"  style="width:100%" aria-label="Text input with checkbox">${man_funVO.mf_name}</span>
						<input type="hidden" value="${s.index}">
					</div>
</c:forEach>
					<div class="btn-group">
						<button type="button" id="chooseAllBtn" class="btn btn-info">全選</button>
						<button type="button" id="clearAllBtn" class="btn btn-info">清除</button>
					</div>
					<div class="row" style="margin-top:70px">
						<div class="col-xs-12"><input id="btn" type="submit" value="送出修改" class="btn btn-success"></div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div id="mf_des" class="noPadding"></div>
			</div>
<!-- 			權限結束 -->		
		</div>
	</div>
</form>
<script>
$("#ggg").addClass("in");
$("#ggg1").css("background-image","radial-gradient(ellipse,white,white,#5bc0de)");
$("#uploadImage").change(function(){
    readImage( this );
  });
  function readImage(input) {
    if ( input.files && input.files[0] ) {
      var FR= new FileReader();
      FR.onload = function(e) {
        //e.target.result = base64 format picture
        $('#img').attr( "src", e.target.result );
        $('#imgDiv').css("visibility","visible");
      };       
      FR.readAsDataURL( input.files[0] );
    }
  }
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
  $("#chooseAllBtn").click(function(){
		$(".checkbox").prop("checked",true);
  });
  $("#clearAllBtn").click(function(){
		$(".checkbox").prop("checked",false);
  });

  $("#citySelect").change(function(){citySelect('newSelect');});
  $("#areaSelect").change(function(){areaSelect('newSelect');});
  $("#roadSelectInput").change(function(){roadSelect('newSelect');});
  

  function citySelect(selectType){    //選取縣市後欲執行查詢鄉鎮區
	  if($("#citySelect").val()!=""){
		  $.post("<%=request.getContextPath()%>/AddressSelect","city="+$("#citySelect").val(),function(data){
			  var area=data.split(",");
			  if(selectType=='newSelect'){
				  $("#areaSelect").empty();
				  $("#roadSelectInput").val("");
				  $("#roadSelect").empty();
				  $("#zipSelectInput").val("");
				  $("#zipSelect").empty();
				  for(var i=0;i<area.length;i++)
					  $("#areaSelect").append("<option value='"+area[i]+"'>"+area[i].split("_")[0]+"</option>");
			  }
			  else{
				  for(var i=0;i<area.length;i++)
					  $("#areaSelect").append("<option value='"+area[i]+"'"+('${addPart[2]}'==area[i].split("_")[0]?' selected':'')+">"+area[i].split("_")[0]+"</option>");
				  areaSelect("defaultSelect");
			  }
		  });
	  }else{
		  $("#areaSelect").empty();
		  $("#roadSelectInput").val("");
		  $("#roadSelect").empty();
		  $("#zipSelectInput").val("");
		  $("#zipSelect").empty();
	  }
	  
  }
  var zip3=null;
  function areaSelect(selectType){    //選取鄉鎮區後欲執行查詢路
	  if($("#areaSelect").val()!=""){
		  $.post("<%=request.getContextPath()%>/AddressSelect","area="+$("#areaSelect").val(),function(data){
			  var road=data.split(",");
			  zip3=(road[1]).split("_")[1];
			  if(selectType=='newSelect'){
				  $("#roadSelectInput").val("");
				  $("#roadSelect").empty();
				  $("#zipSelectInput").val("");
				  $("#zipSelect").empty();
				  for(var i=0;i<road.length;i++)
					  $("#roadSelect").append("<option value='"+road[i].split("_")[0]+"'></option>");
			  }
			  else{
				  for(var i=0;i<road.length;i++){
					  $("#roadSelect").append("<option value='"+road[i].split("_")[0]+"'"+('${addPart[3]}'==road[i]?' selected':'')+"></option>");
				  }
				  roadSelect("defaultSelect");
			  }
		  });
	  }else{
		  $("#roadSelectInput").val("");
		  $("#roadSelect").empty();
		  $("#zipSelectInput").val("");
		  $("#zipSelect").empty();
	  }
  }
  function roadSelect(selectType){
	  if($("#roadSelectInput").val()!=""){
		  $.post("<%=request.getContextPath()%>/AddressSelect","road="+$("#roadSelectInput").val()+"_"+zip3,function(data){
			  if(data!=""){  //查得到資料的情形
				  var zip_scope=data.split(",");
				  if(selectType=='newSelect'){
					  $("#zipSelectInput").val("");
					  $("#zipSelect").empty();
					  for(var i=0;i<zip_scope.length;i++)
						  $("#zipSelect").append("<option value='"+zip_scope[i].substring(0,5)+"'>"+zip_scope[i].substring(6)+"</option>");
				  }
				  else{
					  for(var i=0;i<zip_scope.length;i++){
						  $("#zipSelect").append("<option value='"+zip_scope[i].substring(0,5)+"'"+('${addPart[0]}'==zip_scope[i].substring(0,5)?' selected':'')+">"+zip_scope[i].substring(6)+"</option>");
					  }
				  }
			  }else{ //路的值不為空字串,但查不到對應的郵遞區號時
				  $("#zipSelectInput").val("");
				  $("#zipSelect").empty();
			  }

		  });
	  }else{ //路的值為空字串時
		  $("#zipSelectInput").val("");
		  $("#zipSelect").empty();
	  }
  }
    if(${addPart[1]!='?'}){
		citySelect("defaultSelect");
	}
</script>



<%@ include file="/back-end/be_FooterPage.jsp" %>
