<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ include file="/front-end/HtmlHeadPage.jsp" %>
<head>		
<%  
	CommodityVO comVO = (CommodityVO)request.getAttribute("comVO");
	ShopVO shopVO = (ShopVO)session.getAttribute("loginShopVO");
	pageContext.setAttribute("shopVO",shopVO);
%>
<jsp:useBean id="fruSvc" scope="page" class="com.fruit.model.FruitService" />
<title>商品新增-addShopCommodity.jsp</title>
<style>

#img1 {
	display: none;
	width:200px;
  	margin-left: 0px;
}

#img2 {
	display: none;
	width:200px;
  	margin-left: 0px;
}

#img3 {
	display: none;
  	width:200px;
  	margin-left: 0px;
}

.noWrap {
	white-space: nowrap;
}

#table {
	width: 30%
}
</style>
<script>
$(document).ready(function(){		
	if ("${saveSuccess}".length != 0) {
		swal("新增商品成功", "已新增", "success");
	}
});
	$(function() {
		$("#upfile1").change(function() {
			readImage(this);
		});
		function readImage(input) {
			if (input.files && input.files[0]) {
				var FR = new FileReader();
				FR.onload = function(e) {
					$('#img1').attr("src", e.target.result);
					$('#img1').show();
				};
				FR.readAsDataURL(input.files[0]);
			}
		}
	});
	$(function() {
		$("#upfile2").change(function() {
			readImage(this);
		});
		function readImage(input) {
			if (input.files && input.files[0]) {
				var FR = new FileReader();
				FR.onload = function(e) {
					$('#img2').attr("src", e.target.result);
					$('#img2').show();
				};
				FR.readAsDataURL(input.files[0]);
			}
		}
	});
	$(function() {
		$("#upfile3").change(function() {
			readImage(this);
		});
		function readImage(input) {
			if (input.files && input.files[0]) {
				var FR = new FileReader();
				FR.onload = function(e) {
					$('#img3').attr("src", e.target.result);
					$('#img3').show();
				};
				FR.readAsDataURL(input.files[0]);
			}
		}
	});

	
</script>
</head>
<%@ include file="/front-end/HeaderPage.jsp"%> 
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<div class="container">				<!-- 大容器開始 -->		
		<div class="row">						<!-- ROW開始 -->		
			<div class="col-xs-12 col-sm-2">	<!-- 左欄 -->				
				<div class="panel-group" id="accordion2" role="tablist"
					aria-multiselectable="true">
					<!-- 區塊1 -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="panel2">
							<h4 class="panel-title">
								<a href="#aaa" data-parent="#accordion2" data-toggle="collapse"
									role="button" class="collapsed" aria-expanded="false"
									aria-controls="aaa">
									<h4>商家資料管理</h4>
								</a>
							</h4>
						</div>
						<div id="aaa" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="panel2">
							<div class="panel-body">
								<ul class="list-unstyled">
									<li><a
										href="<%=request.getContextPath()%>/front-end/shop/shopUpdate.jsp"><b>商家資料修改</b></a></li>
								</ul>
							</div>
						</div>
						<!-- 區塊1結束 -->
					</div>
					<!-- 區塊2 -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="panel2">
							<h4 class="panel-title">
								<a href="#bbb" data-parent="#accordion2" data-toggle="collapse"
									role="button" class="collapsed" aria-expanded="false"
									aria-controls="bbb">
									<h4>商品管理</h4>
								</a>
							</h4>
						</div>
						<div id="bbb" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="panel2">
							<div class="panel-body">
								<ul class="list-unstyled">
									<li><a
										href="<%=request.getContextPath()%>/front-end/shop/addShopCommodity.jsp"><b>新增商品</b></a></li>
									<li><a
										href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=getOne_For_Display&shop_no=${shopVO.shop_no}"><b>商品上下架</b></a></li>
									<li><a
										href="<%=request.getContextPath()%>/front-end/ad/adadd.jsp"><b>申請打廣告</b></a></li>
									<li><a
										href="<%=request.getContextPath()%>/front-end/prom/prom.do?action=init&shop_no=${shopVO.shop_no}"><b>促銷方案管理</b></a></li>
								</ul>
							</div>
						</div>
						<!-- 區塊2結束 -->
					</div>
					<!-- 區塊3 -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="panel3">
							<h4 class="panel-title">
								<a href="#ccc" data-parent="#accordion2" data-toggle="collapse"
									role="button" class="collapsed" aria-expanded="false"
									aria-controls="ccc">
									<h4>訂單管理</h4>
								</a>
							</h4>
						</div>
						<div id="ccc" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="panel3">
							<div class="panel-body">
								<ul class="list-unstyled">
									<li><a
										href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=shopOrder&shop_no=${shopVO.shop_no}"><b>商品出貨狀態</b></a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊3結束 -->
				</div>
			</div>	<!-- 左欄結束 -->			
			<div class="col-xs-12 col-sm-10">	<!-- 右欄開始 -->	
				<h3 id="addcom"><b>資料新增:</b></h3>			
				<form action="<%=request.getContextPath()%>/front-end/shop/shop.do"
					method="post" name="form1" enctype="multipart/form-data">
					<table class="table table-hover table-bordered" id="table">
						<tr>
							<td class="noWrap"><b>商品名稱</b></td>
							<td><input type="text" name="com_name" size="100"
								maxlength="33" id="com_name"
								value="<%=(comVO == null) ? " " : comVO.getCom_name()%>"></td>
						</tr>
						<tr>
							<td class="noWrap"><b>蔬果類型:</b></td>
							<td><select size="1" name="fru_no">
									<c:forEach var="fruitVO" items="${fruSvc.all}">
										<option value="${fruitVO.fru_no}"
											${(comVO.fru_no==fruitVO.fru_no)? 'selected':'' }>${fruitVO.fru_name}
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td class="noWrap"><b>單價</b></td>
							<td><input type="text" name="com_price" size="100"
								maxlength="10" id="com_price"
								value="<%=(comVO == null) ? "" : comVO.getCom_price()%>"></td>
						</tr>
						<tr>
							<td class="noWrap"><b>規格</b></td>
							<td><input type="text" name="com_weight" size="100"
								maxlength="10" id="com_weight"
								value="<%=(comVO == null) ? "" : comVO.getCom_weight()%>">
							</td>
						</tr>
						<tr>
							<td class="noWrap"><b>商品敘述</b></td>
							<td><textarea rows="5" cols="50" name="com_remarks" id="com_remarks"><%=(comVO == null) ? "" : comVO.getCom_remarks()%></textarea>
							</td>
						</tr>
						<tr>
							<td class="noWrap"><b>商品照片1:</b></td>
							<td><label class="btn btn-info"> <input type="file"
									name="com_pic1" size="100" id="upfile1" style="display: none;" <%=(comVO == null) ? "" : comVO.getCom_pic1()%>" />
									<i class="fa fa-photo"></i> 上傳圖片
							</label> <img id="img1" src="" ></td>
						</tr>
						<tr>
							<td class="noWrap"><b>商品照片2:</b></td>
							<td><label class="btn btn-info"> <input type="file"
									name="com_pic2" size="100" id="upfile2" style="display: none;" <%=(comVO == null) ? "" : comVO.getCom_pic2()%>" />
									<i class="fa fa-photo"></i> 上傳圖片
							</label> <img id="img2" src="" ></td>
						</tr>
						<tr>
							<td class="noWrap"><b>商品照片3:</b></td>
							<td><label class="btn btn-info"> <input type="file"
									name="com_pic3" size="100" id="upfile3" style="display: none;" <%=(comVO == null) ? "" : comVO.getCom_pic3()%>" />
									<i class="fa fa-photo"></i> 上傳圖片
							</label> <img id="img3" src="" ></td>
						</tr>
						<tr>
						</tr>
						<tr>
							<td class="noWrap"><b>上/下架狀態:</b></td>
							<td><select size="1" name="com_status">
									<option
										value="<%=(comVO == null ? "下架" : comVO.getCom_status())%>">下架
									<option value="上架">上架
							</select></td>
						</tr>
						<tr>
							<td class="noWrap"><b>庫存數量</b></td>
							<td><input type="text" name="com_store" size="100"
								maxlength="10" id="com_store"
								value="<%=(comVO == null) ? "" : comVO.getCom_store()%>"></td>
						</tr>
					</table>
					<input type="hidden" name="shop_no" value="${shopVO.shop_no}">
					<input type="hidden" name="com_score" value="${comVO.com_score}">
					<input type="hidden" name="com_peo" value="${comVO.com_peo}">
					<input type="hidden" name="com_time" value="${comVO.com_time}">
					<input type="hidden" name="action" value="addProduct"> <input
						type="submit" value="新增商品">
				</form>
			</div>	<!-- 右欄結束 -->			
		</div>	<!-- row結束 -->		
	</div>	<!-- 大容器結束 -->	
	
	<script>
	$().ready(function(){
		$('#addcom').click(function(){
			$('#com_name').val('香蕉好棒棒');
			$('#com_price').val('200');
			$('#com_weight').val('足重500g');
			$('#com_remarks').val('香蕉含有豐富的營養，不用吐籽又很好剝皮，一年到頭都有生產，吃了有飽足感但熱量也不致於太高，所以馬拉松比賽時都會準備香蕉給選手補充體力，營養的好水果，有機栽培最安全安心。特別是資策會同學更應該多吃香蕉，上Servlet才不會打瞌睡');
			$('#com_store').val('80');
		});
	});
	</script>
<%@ include file="/front-end/FooterPage.jsp" %>