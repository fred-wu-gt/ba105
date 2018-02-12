<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.List"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%  int rowsPerPage = 5;  //每頁的筆數    
	if(request.getParameter("rowsPerPage")!=null){
		rowsPerPage = Integer.parseInt(request.getParameter("rowsPerPage"));
	}
	String chooseGroup=request.getParameter("chooseGroup");
	if(chooseGroup==null)
		chooseGroup="waiting";
	List<ShopVO> list=(List<ShopVO>) session.getAttribute("shopVOList");
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
	$("#fff").addClass("in");
	$("#fff1").css("background-image","radial-gradient(ellipse,white,white,#5bc0de)");
	
	
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
					<h3 class="breadcrumbs">客戶權限><b>商家權限</b></h3>
				</div>

				<div class="col-xs-12 col-md-4 col-md-offset-3">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do" id="chooseGroupForm">
						<div class="input-group">
							<span class="input-group-addon selectAddon" >商家狀態</span>
							<select name="chooseGroup" id="chooseGroup"  class="form-control">
								<option value="waiting" ${param.chooseGroup=='waiting'?'selected':''}>待審核</option>
								<option value="pass" ${param.chooseGroup=='pass'?'selected':''}>正常</option>
								<option value="fail" ${param.chooseGroup=='fail'?'selected':''}>停權</option>
								<option value="all" ${param.chooseGroup=='all'?'selected':''}>全部</option>
							</select>
							<input type="hidden" name="action" value="getShopsByShop_stat">
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
		<th>商家照片</th>
		<th>商家名稱</th>
		<th>商家帳號</th>
		<th>商家違規記點</th>
		<th>商家狀態</th>
		<th>審核資料</th>
	</tr>

	<c:forEach var="shopVO" items="${shopVOList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr id="tr${shopVO.shop_no}">
			<td><img src="<%=request.getContextPath()%>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${shopVO.shop_no}"></td>
			<td>${shopVO.shop_name}</td>
			<td>${shopVO.shop_id}</td>
			<td>${shopVO.shop_point}</td>
			<td id="status${shopVO.shop_no}">${shopVO.shop_stat}</td>
			<td>
			
<a href='#modal${shopVO.shop_no}' data-toggle="modal" class="btn btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
	<div  class="modal fade" id="modal${shopVO.shop_no}">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"><b>商家資料</b></h4>
				</div>
				<div class="modal-body">
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家編號</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_no}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家照片</span>
						<img class="form-control" style="width:100%;height:auto;" src="<%=request.getContextPath()%>/front-end/DBPicReader/DBPicReader.do?action=shop&id_no=${shopVO.shop_no}">
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家名稱</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_name}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家信箱</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_email}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家電話</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_phone}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家地址</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_loc}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家基本介紹</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_desc}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家儲值點數</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_val}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">本月累計商品銷售額</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_cosum}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">本月累計廣告費</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_adsum}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">匯款銀行</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_bank}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">匯款帳號</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_bank}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家可見度</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_vis}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家狀態</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_stat}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">商家違規記點</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_point}</span>
					</div>
					<div class="input-group" style="width:100%">
						<span class="input-group-addon textAddon" style="width:18%;">評分平均</span>
						<span class="form-control" style="text-align:left;">${shopVO.shop_score}</span>
					</div>
					<div class="input-group" style="width:100%" >
						<span class="input-group-addon textAddon" style="width:18%;">小農證明文件</span>
						<img style="width:100%;height:auto;" src="<%=request.getContextPath()%>/front-end/DBPicReader/DBPicReader.do?action=shop2&id_no=${shopVO.shop_no}">
					</div>
				</div>
				<div class="modal-footer">
					
					<FORM id="form${shopVO.shop_no}" style="width:100%;float:right">
						<div class="input-group" style="width:100%;margin-bottom:10px;">
							<span class="input-group-addon selectAddon"  style="width:18%;" >修改狀態</span>
							<select name="shop_stat" class="form-control"  id="select${shopVO.shop_no}">
								<option value="待審核" ${shopVO.shop_stat=='待審核'?'selected':''}>待審核</option>
								<option value="正常" ${shopVO.shop_stat=='正常'?'selected':''}>正常</option>
								<option value="停權" ${shopVO.shop_stat=='停權'?'selected':''}>停權</option>
							</select>
						</div>
						<div class="input-group" style="width:100%;margin-bottom:10px;">
							<span class="input-group-addon"  style="width:18%;" >請輸入簡訊金鑰</span>
							<input type="password" name="pswKey" class="form-control" >
						</div>
						<div id="alert${shopVO.shop_no}" style="color:red;text-align:center;height:50px"></div>
						<div class="input-group" style="width:100%">
							<button type="submit" class="btn btn-success form-control" style="width:50%;">送出修改</button>
							<input type="hidden" name="shop_no" value="${shopVO.shop_no}">
							<input type="hidden" name="action" value="updateStatus">
							<input type="hidden" name="chooseGroup" value="<%=chooseGroup%>">
							<button type="button" class="btn btn-default form-control" data-dismiss="modal" style="width:50%">取消</button>
						</div>
						<script>
							$("#form${shopVO.shop_no}").submit(function(){
								if($(this).find("#select${shopVO.shop_no}").val()==$("#status${shopVO.shop_no}").html()){   //顯示狀態和下拉式選單所選選項一樣時
									$(this).find("#alert${shopVO.shop_no}").html(
											"<div class='alert alert-danger alert-dismissible' style='padding-top:5px;padding-bottom:5px;' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>您尚未變更商家狀態!</div>");
									return false;
								}
								 
								 $.ajax({
									 type: "POST",
									 url: "<%=request.getContextPath()%>/front-end/shop/shop.do",
									 data: $(this).serialize()+"&originalShop_stat="+$("#status${shopVO.shop_no}").html(),
									 dataType: "json",
									 success: function (data){
										 $("#modal${shopVO.shop_no}").modal('hide');
										 if("<%=chooseGroup%>" !="all") { //所選清單群組不為全部時，才要修改顯示的資料筆數和隱藏項目
											 var dataCount=parseInt($("#dataCount").html())-1;
											 $("#dataCount").html(dataCount);
											 $("#tr${shopVO.shop_no}").hide();
										 }else{
											 $("#status${shopVO.shop_no}").html($("#select${shopVO.shop_no}").val());
										 }
										  if(data.result=="msgSuccess")
										 	sweetAlert("修改成功!", "已寄簡訊通知商家「${shopVO.shop_name}」", "success");
										  else
											  sweetAlert("修改成功!", "", "success");
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