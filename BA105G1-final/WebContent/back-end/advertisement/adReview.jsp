<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="java.util.List"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%  int rowsPerPage = 5;  //每頁的筆數    
	if(request.getParameter("rowsPerPage")!=null){
		rowsPerPage = Integer.parseInt(request.getParameter("rowsPerPage"));
	}
	String chooseGroup=request.getParameter("chooseGroup");
	if(chooseGroup==null)
		chooseGroup="waiting";
	List<AdVO> list=(List<AdVO>) session.getAttribute("adVOList");
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
	background-image: linear-gradient(to top, white,#f0ffe0,#f0ffe0);
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
	width:90%;
  }
  #outerTable img{
  height:50px;
  width:auto;
  }
  	.textAddon{
		background-image: radial-gradient(ellipse,white,#f0ffe0,#f0ffe0);
	}
	.selectAddon{
		background-image: radial-gradient(ellipse,white,#FFE8E8);
		
	}
	.modal-backdrop.in{
		background-color:gray;
		opacity:0.5;
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
	$("#panel2").css("background-image","radial-gradient(ellipse,white,white,#5bc0de)");
	
	
	function setBstModalMaxHeight(element) {
		this.$element          = $(element);
		this.$modalContent     = this.$element.find('.modal-content');
		var $window            = $(window);
		var $modalContentOH    = this.$modalContent.outerHeight();
		var $modalContentIH    = this.$modalContent.innerHeight();
		var _modalBorderWidth   = $modalContentOH - $modalContentIH;
		var _modalDialogMargin  = $window.width() < 768 ? 20 : 60;
		var _modalContentHeight = $window.height() - (_modalDialogMargin + _modalBorderWidth) ;
		var _modalHeaderHeight  = this.$element.find('.modal-header').outerHeight() || 0;
		var _modalFooterHeight  = this.$element.find('.modal-footer').outerHeight() || 0;
		var _modalMaxHeight     = _modalContentHeight - (_modalHeaderHeight + _modalFooterHeight);
		
		this.$modalContent.css({
		'overflow': 'hidden'
		});
		
		this.$element
		.find('.modal-body').css({
		'max-height': _modalMaxHeight,
		'overflow-y': 'auto'
		});
		}
		
		$('.modal').on('show.bs.modal', function() {
		$(this).show();
		setBstModalMaxHeight(this);
		});
		
		$(window).resize(function() {
		if ($('.modal.in').length != 0) {
		setBstModalMaxHeight($('.modal.in'));
		}
		});

})


</script>
</head>
<%@ include file="/back-end/be_HeaderPage.jsp" %>
	<div class="row" id="listAll_div">
		<div class="col-xs-12">
		    	<div>
					<h3 class="breadcrumbs"><b>廣告牆</b></h3>
				</div>

				<div class="col-xs-12 col-md-4 col-md-offset-3">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ad/ad.do" id="chooseGroupForm">
						<div class="input-group">
							<span class="input-group-addon selectAddon" >廣告狀態</span>
							<select name="chooseGroup" id="chooseGroup"  class="form-control">
								<option value="waiting" ${param.chooseGroup=='waiting'?'selected':''}>待審核</option>
								<option value="pass" ${param.chooseGroup=='pass'?'selected':''}>審核通過</option>
								<option value="fail" ${param.chooseGroup=='fail'?'selected':''}>審核不通過</option>
								<option value="all" ${param.chooseGroup=='all'?'selected':''}>全部</option>
							</select>
							<input type="hidden" name="action" value="getAdsByAd_stat">
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
		<th>廣告編號</th>
		<th>商家名稱</th>
		<th>廣告理想開始時間</th>
		<th>廣告上架時間</th>
		<th>廣告下架時間</th>
		<th>廣告狀態</th>
		<th>審核資料</th>
	</tr>

	<c:forEach var="adVO" items="${adVOList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr id="tr${adVO.ad_no}">
			<td>${adVO.ad_no}</td>
			<td>${shopSvc.findByPrimaryKey(adVO.shop_no).shop_name}</td>
			<td><fmt:formatDate value="${adVO.ad_idesta}" pattern="yyyy/MM/dd HH:mm"/></td>
			<td><fmt:formatDate value="${adVO.ad_start}" pattern="yyyy/MM/dd HH:mm"/></td>
			<td><fmt:formatDate value="${adVO.ad_end}" pattern="yyyy/MM/dd HH:mm"/></td>
			<td id="status${adVO.ad_no}">${adVO.ad_stat}</td>
			<td>
			
<a href='#modal${adVO.ad_no}' data-toggle="modal" class="btn btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
	<div  class="modal fade" id="modal${adVO.ad_no}">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"><b>廣告資料</b></h4>
				</div>
				<div class="modal-body">
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">廣告編號</span>
						<span class="form-control" style="text-align:left;">${adVO.ad_no}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家名稱</span>
						<span class="form-control" style="text-align:left;">${shopSvc.findByPrimaryKey(adVO.shop_no).shop_name}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">廣告理想開始時間</span>
						<span class="form-control" style="text-align:left;"><fmt:formatDate value="${adVO.ad_idesta}" pattern="yyyy/MM/dd HH:mm"/></span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">廣告上架時間</span>
						<span class="form-control" style="text-align:left;"><fmt:formatDate value="${adVO.ad_start}" pattern="yyyy/MM/dd HH:mm"/></span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">廣告下架時間</span>
						<span class="form-control" style="text-align:left;"><fmt:formatDate value="${adVO.ad_end}" pattern="yyyy/MM/dd HH:mm"/></span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">廣告費</span>
						<span class="form-control" style="text-align:left;">${adVO.ad_exp}元</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">廣告狀態</span>
						<span class="form-control" style="text-align:left;">${adVO.ad_stat}</span>
					</div>
					<div class="input-group" style="width:100%" >
						<span class="input-group-addon textAddon" style="width:18%;">廣告圖片</span>
						<img style="width:100%;height:auto;" src="<%=request.getContextPath()%>/front-end/DBPicReader/DBPicReader.do?action=ad&id_no=${adVO.ad_no}">
					</div>
				</div>
				<div class="modal-footer">
					
					<FORM id="form${adVO.ad_no}" style="width:100%;float:right">
						<div class="input-group" style="width:100%;margin-bottom:10px;">
							<span class="input-group-addon selectAddon"  style="width:18%;" >修改狀態</span>
							<select name="ad_stat" class="form-control"  id="select${adVO.ad_no}">
								<option value="待審核" ${adVO.ad_stat=='待審核'?'selected':''}>待審核</option>
								<option value="審核通過" ${adVO.ad_stat=='審核通過'?'selected':''}>審核通過</option>
								<option value="審核不通過" ${adVO.ad_stat=='審核不通過'?'selected':''}>審核不通過</option>
							</select>
						</div>
						<div id="alert${adVO.ad_no}" style="color:red;text-align:center;height:50px"></div>
						<div class="input-group" style="width:100%">
							<button type="submit" class="btn btn-success form-control" style="width:50%;">送出修改</button>
							<input type="hidden" name="ad_no" value="${adVO.ad_no}">
							<input type="hidden" name="action" value="updateStatus">
							<input type="hidden" name="chooseGroup" value="<%=chooseGroup%>">
							<button type="button" class="btn btn-default form-control" data-dismiss="modal" style="width:50%">取消</button>
						</div>
						<script>
							$("#form${adVO.ad_no}").submit(function(){
								if($(this).find("#select${adVO.ad_no}").val()==$("#status${adVO.ad_no}").html()){   //顯示狀態和下拉式選單所選選項一樣時
									$(this).find("#alert${adVO.ad_no}").html(
											"<div class='alert alert-danger alert-dismissible' style='padding-top:5px;padding-bottom:5px;' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>您尚未變更廣告狀態!</div>");
									return false;
								}
								 
								 $.ajax({
									 type: "POST",
									 url: "<%=request.getContextPath()%>/front-end/ad/ad.do",
									 data: $(this).serialize()+"&originalAd_stat="+$("#status${adVO.ad_no}").html(),
									 dataType: "json",
									 success: function (data){
										 $("#modal${adVO.ad_no}").modal('hide');
										 if("<%=chooseGroup%>" !="all") { //所選清單群組不為全部時，才要修改顯示的資料筆數和隱藏項目
											 var dataCount=parseInt($("#dataCount").html())-1;
											 $("#dataCount").html(dataCount);
											 $("#tr${adVO.ad_no}").hide();
										 }else{
											 $("#status${adVO.ad_no}").html($("#select${adVO.ad_no}").val());
										 }
										  
										 if(data.result=='outTime'){
											 sweetAlert("目前時間已超過商家申請的廣告結束時間!", "已寄信通知商家「${shopSvc.findByPrimaryKey(adVO.shop_no).shop_name}」審核不通過", "error");
										 }else if(data.result=='startOverEnd'){
											 sweetAlert("商家申請的廣告希望開始時間比結束時間晚!", "已寄信通知商家「${shopSvc.findByPrimaryKey(adVO.shop_no).shop_name}」審核不通過", "error");
										 }else if(data.result=='pass'){
											 sweetAlert("修改成功!", "已寄信通知商家「${shopSvc.findByPrimaryKey(adVO.shop_no).shop_name}」審核通過，費用為"+data.ad_exp+"元。", "success");
										 }else if(data.result=='fail'){
											 sweetAlert("修改成功!", "已寄信通知商家「${shopSvc.findByPrimaryKey(adVO.shop_no).shop_name}」審核不通過", "success");
										 }else if(data.result=='waiting'){
											 sweetAlert("修改成功!", "已寄信通知商家「${shopSvc.findByPrimaryKey(adVO.shop_no).shop_name}」待審核", "success");
										 }
										 
								     },
						             error: function(){sweetAlert("修改失敗!", "系統忙線中，請重試。", "error");}
						         });
								 
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