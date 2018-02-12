<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_rep.model.*"%>
<%@ page import="java.util.List"%>
<jsp:useBean id="writingSvc" scope="page" class="com.writing.model.WritingService" />
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%  int rowsPerPage = 5;  //每頁的筆數    
	if(request.getParameter("rowsPerPage")!=null){
		rowsPerPage = Integer.parseInt(request.getParameter("rowsPerPage"));
	}
	String chooseGroup=request.getParameter("chooseGroup");
	if(chooseGroup==null)
		chooseGroup="waiting";
	List<Wri_repVO> list=(List<Wri_repVO>) session.getAttribute("wri_repVOList");
%>
<%@ include file="/back-end/be_HtmlHeadPage.jsp" %>
<head>
<style>
  #listAll_div table,#listAll_div th,#listAll_div td {
    border: 1px outset #652825;
    padding:2px 5px;
    text-align: center;
  	font-size:16px;
  }
  #listAll_div th{
	border-style:outset;
	background-image: linear-gradient(to top, white,#fffde8,#fffde8);
  }
  #listAll_div img{
  	width:100px;
  }
  #listAll_div .thumbnail{
  	border:0;
  }
  #outerTable{
    background-image: linear-gradient(to top right, white, white,#FBFFFD);
	font-size:16px;
	width:95%;
  }
  #outerTable img{
  height:50px;
  width:auto;
  }
  	.textAddon{
		background-image: radial-gradient(ellipse,white,#fffde8);
	}
	.selectAddon{
		background-image: radial-gradient(ellipse,white,#FFE8E8);
		
	}
	.modal-backdrop.in{
		background-color:gray;
		opacity:0.5;
	}
	.shop_table{
          overflow: hidden;
          text-overflow: ellipsis;
          word-break: break-all;
          border:0;
          background-image: radial-gradient(ellipse,#f0ffe0,white);
   	}
	.member_table{
          overflow: hidden;
          text-overflow: ellipsis;
          word-break: break-all;
          border:0;
          background-image: radial-gradient(ellipse,#DDDDFF,white);
   	}
   	.alert-dismissible{
   	    padding-left: 55px;
   	}
</style>
<script>
$(function(){
	$("#chooseGroup").change(function(){
		$("#chooseGroupForm").submit();
	});	
	$("#chooseRowsPerPage").change(function(){
		$("#chooseRowsPerPageForm").submit();
	});	
	$("#ccc").addClass("in");
	$("#ccc2").css("background-image","radial-gradient(ellipse,white,white,#5bc0de)");
})


</script>
</head>
<%@ include file="/back-end/be_HeaderPage.jsp" %>
	<div class="row" id="listAll_div">
		<div class="col-xs-12">
		    	<div>
					<h3 class="breadcrumbs">檢舉><b>文章檢舉</b></h3>
				</div>

				<div class="col-xs-12 col-md-4 col-md-offset-3">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/writing_rep/writing_rep.do" id="chooseGroupForm">
						<div class="input-group">
							<span class="input-group-addon selectAddon" >文章檢舉狀態</span>
							<select name="chooseGroup" id="chooseGroup"  class="form-control">
								<option value="waiting" ${param.chooseGroup=='waiting'?'selected':''}>待審核</option>
								<option value="pass" ${param.chooseGroup=='pass'?'selected':''}>審核通過</option>
								<option value="fail" ${param.chooseGroup=='fail'?'selected':''}>審核不通過</option>
								<option value="all" ${param.chooseGroup=='all'?'selected':''}>全部</option>
							</select>
							<input type="hidden" name="action" value="getWri_repsByWre_stat">
							<input type="hidden" name="rowsPerPage" value="<%=rowsPerPage%>">
						</div>
					</FORM>
				</div>
				<div class="col-xs-12 col-md-4">
					<FORM METHOD="post" ACTION="<%=request.getRequestURI()%>" id="chooseRowsPerPageForm">
						<div class="input-group">
							<span class="input-group-addon selectAddon" >每頁筆數</span>
							<select name="rowsPerPage" id="chooseRowsPerPage"  class="form-control">
								<option value="5" ${param.rowsPerPage=='5'?'selected':''}>5</option>
								<option value="10" ${param.rowsPerPage=='10'?'selected':''}>10</option>
								<option value="15" ${param.rowsPerPage=='15'?'selected':''}>15</option>
								<option value="20" ${param.rowsPerPage=='20'?'selected':''}>20</option>
							</select>
							<input type="hidden" name="chooseGroup" value="<%=chooseGroup%>">
						</div>
					</FORM>
				</div>
<div class="col-xs-12">				
<%@ include file="/back-end/pages/page1.file" %> 
</div>
<table id="outerTable">
	<tr>
		<th>文章檢舉編號</th>
		<th style="width:300px;">文章</th>
		<th>被檢舉商家</th>
		<th>檢舉會員</th>
		<th>狀態</th>
		<th>檢舉內容</th>
	</tr>

	<c:forEach var="wri_repVO" items="${wri_repVOList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<c:set var="writingVO" scope="page" value="${writingSvc.getOneWriting(wri_repVO.wrt_no)}" />
		<c:set var="memberVO" scope="page" value="${memberSvc.findByMem_no(wri_repVO.mem_no)}" />
		<c:set var="shopVO" scope="page" value="${shopSvc.findByPrimaryKey(writingVO.shop_no)}" />
		<tr id="tr${wri_repVO.wre_no}">
			<td>${wri_repVO.wre_no}</td>
			<td style="text-align:left">${writingVO.wrt_title}
				<span><a href="javascript:openWindow${wri_repVO.wrt_no}()" class="btn btn-info"><i class="glyphicon glyphicon-zoom-in"></i></a></span>				
			</td>
			<script>
			function openWindow${wri_repVO.wrt_no}(){
			 	document.open("<%=request.getContextPath()%>/front-end/writing/writing.do?wrt_no=${wri_repVO.wrt_no}&action=getOne_For_Display", "" ,"height=400,width=800,left=250,top=100,resizable=yes,scrollbars=yes");
			}
			</script>
			<td>
			    <div class="thumbnail" style="margin:0;">
	   				<table class="shop_table" style="border:0;">
	   					<tr>
	   						<td rowspan="2" class="pro_name" style="border:0;">
	   							<img src="<%=request.getContextPath()%>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${shopVO.shop_no}">
	   						</td>
	   						<td style="border:0;">
	   							<span>${shopVO.shop_name}</span>
	   						</td>
	   					</tr>
	   				</table>
	   			</div>
			</td>
			<td>
				<div class="thumbnail home_product_item" style="margin:0;">
	   				<table class="member_table" style="border:0;">
	   					<tr>
	   						<td rowspan="2" class="pro_name" style="border:0;">
	   							<img  src="data:image/jpeg;base64,${memberVO.mem_photo_base64}">
	   						</td>
	   						<td style="border:0;">
	   							<span>${memberVO.mem_name}</span>
	   						</td>
	   					</tr>
	   				</table>
	   			</div>
			</td>
			<td id="status${wri_repVO.wre_no}">${wri_repVO.wre_stat}</td>
			<td>
			
				<a href='#modal${wri_repVO.wre_no}' data-toggle="modal" class="btn btn-warning"><i class="glyphicon glyphicon-zoom-in"></i></a>
					<div  class="modal fade" id="modal${wri_repVO.wre_no}">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title"><b>[文章檢舉]</b> 檢舉編號:${wri_repVO.wre_no}</h4>
								</div>
								<div class="modal-body">
									<div class="input-group" style="height:40px;">
										<span class="input-group-addon textAddon" style="width:10%;">檢舉原因</span>
										<span class="form-control" style="text-align:left;height:40px;">${wri_repVO.wre_rsn}</span>
									</div>
									<div class="input-group" style="height:80px;">
										<span class="input-group-addon textAddon" style="width:10%;">檢舉內容</span>
										<span class="form-control" style="text-align:left;height:80px;">${wri_repVO.wre_cont}</span>
									</div>
								</div>
								<div class="modal-footer">
									
									<FORM id="reportForm${wri_repVO.wre_no}" style="float:right">
										<div class="input-group" style="width:100%;margin-bottom:20px;">
											<span class="input-group-addon selectAddon" >處置方式</span>
											<select name="wre_stat" class="form-control"  id="select${wri_repVO.wre_no}">
												<option value="待審核" ${wri_repVO.wre_stat=='待審核'?'selected':''}>待審核</option>
												<option value="審核通過" ${wri_repVO.wre_stat=='審核通過'?'selected':''}>審核通過</option>
												<option value="審核不通過" ${wri_repVO.wre_stat=='審核不通過'?'selected':''}>審核不通過</option>
											</select>
										</div>
										<div id="alert${wri_repVO.wre_no}" style="color:red;text-align:center;height:80px"></div>
										<div class="input-group" style="width:100%">
											<button type="submit" class="btn btn-success form-control" style="width:50%;">送出審核</button>
											<input type="hidden" name="wre_no" value="${wri_repVO.wre_no}">
											<input type="hidden" name="wrt_no" value="${wri_repVO.wrt_no}">
											<input type="hidden" name="shop_no" value="${shopVO.shop_no}">
											<input type="hidden" name="action" value="updateStatus">
											<input type="hidden" name="chooseGroup" value="<%=chooseGroup%>">
											<button type="button" class="btn btn-default form-control" data-dismiss="modal" style="width:50%">取消</button>
										</div>
										<script>
											$("#reportForm${wri_repVO.wre_no}").submit(function(){
												if($(this).find("#select${wri_repVO.wre_no}").val()==$("#status${wri_repVO.wre_no}").html()){   //顯示狀態和下拉式選單所選選項一樣時
													$(this).find("#alert${wri_repVO.wre_no}").html(
															"<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>您尚未變更處置方式!</div>");
													return false;
												}
												 
												 $.ajax({
													 type: "POST",
													 url: "<%=request.getContextPath()%>/front-end/writing_rep/writing_rep.do",
													 data: $(this).serialize()+"&originalWre_stat="+$("#status${wri_repVO.wre_no}").html(),
													 dataType: "json",
													 success: function (data){
														 $("#modal${wri_repVO.wre_no}").modal('hide');
														 if("<%=chooseGroup%>" !="all") { //所選清單群組不為全部時，才要修改顯示的資料筆數和隱藏項目
															 var dataCount=parseInt($("#dataCount").html())-1;
															 $("#dataCount").html(dataCount);
															 $("#tr${wri_repVO.wre_no}").hide();
														 }else{
															 $("#status${wri_repVO.wre_no}").html($("#select${wri_repVO.wre_no}").val());
														 }
														 
											            var newLine = "\r\n";
										                var message = "文章檢舉編號 : "+data.wre_no;
										                message += newLine;
										                message += "商家名稱(被檢舉) : "+data.shop_name;
										                message += newLine;
										                message += "原違規點數 : "+data.originalShop_point;
										                message += newLine;
										                message += "目前違規點數 : "+data.shop_point;
										                message += newLine+newLine;
										                message += "文章標題(被檢舉) : "+data.wrt_title;
										                message += newLine;
										                message += "原狀態 : "+data.originalWrt_sta;
										                message += newLine;
										                message += "目前狀態 : "+data.wrt_sta;
											                
														 
														 sweetAlert("送出成功 : "+data.wre_stat, message, "success");
													
												     },
										             error: function(){sweetAlert("修改失敗!", "系統忙線中，請重試。", "error");}
										         })
												 
												return false;
											});
										</script>
									</FORM>
								</div>
							</div>
						</div>
					</div>
			</td>
		</tr>
	</c:forEach>
</table>
<br>

<%@ include file="/back-end/pages/page2.file" %>

		</div>
	</div>
<%@ include file="/back-end/be_FooterPage.jsp" %>