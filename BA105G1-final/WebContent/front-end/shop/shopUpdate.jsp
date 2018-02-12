<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shop.model.*"%>
<%
	ShopVO shopVO = (ShopVO) session.getAttribute("loginShopVO");
pageContext.setAttribute("shopVO",shopVO);
%>
<%@ include file="/front-end/HtmlHeadPage.jsp"%>
<head>
<script>
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
	
	
	$(document).ready(function(){		
		if ("${saveSuccess}".length != 0) {
			swal("新增完成", "已新增", "success");
		}
	});
	$(document).ready(function(){		
		if ("${notSaveSuccess}".length != 0) {
			swal("新增失敗", "新增失敗", "error");
		}
	});
</script>


<style type="text/css">
#text {
	height: 231px;
	width: 416px;
}


</style>
</head>
<%@ include file="/front-end/HeaderPage.jsp"%>

<div class="container">
	<!-- 大容器開始 -->
	<div class="row">
		<div class="col-xs-12 col-sm-2">
			<!-- 左欄 -->
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
		</div>

		<!-- 右欄開始 -->
		<div class="col-xs-12 col-sm-10">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<form method="post"
				action="<%=request.getContextPath()%>/front-end/shop/shop.do"
				name="form1" enctype="multipart/form-data">
				<table class="table table-hover table-bordered">
					<h1>資料修改</h1>
					<tr>
						<th>店家名稱 :</th>
						<td><input type="text" name="shop_name" size="45"
							maxlength="10" value="${shopVO.shop_name}" /></td>
					</tr>
					<tr>
						<th>帳號：</th>
						<td>${shopVO.shop_id}</td>
					</tr>
					<tr>
						<th>密碼：</th>
						<td><input type="text" name="shop_psw" size="45"
							maxlength="10" value="${shopVO.shop_psw}" /></td>
					</tr>
					<tr>
						<th>信箱 :</th>
						<td><input type="email" name="shop_email" size="45"
							value="${shopVO.shop_email}" /></td>
					</tr>
					<tr>
						<th>手機號碼 :</th>
						<td><input type="text" name="shop_phone" size="45"
							maxlength="10" value="${shopVO.shop_phone}" /></td>
					</tr>
					<tr>
						<th>地址 :</th>
						<td><input type="text" name="shop_loc" size="45"
							maxlength="30" value="${shopVO.shop_loc}" /></td>
					</tr>
					<tr>
						<th>基本介紹 :</th>
						<td><textarea name="shop_desc" maxlength="200" id="text"> 
    						${shopVO.shop_desc}</textarea></td>
					</tr>

					<tr>
						<th>匯款銀行 :</th>
						<td><select size="1" name="shop_bank">
								<option>日盛銀行</option>
								<option>玉山銀行</option>
								<option>台灣銀行</option>
								<option>台新銀行</option>
								<option>富邦銀行</option>
						</select></td>
					</tr>
					<tr>
						<th>匯款帳號 :</th>
						<td><input type="text" name="shop_acc" size="45"
							value="${shopVO.shop_acc}" /></td>
					</tr>
					<tr>
						<div class="fred" id="photo">
							<th>照片:</th>
							<td>
							<input type="file" name="shop_photo" accept="image/*"
								id="upfile1" height="200" width="200"> 				
								<c:if test="${empty shopVO}">						
									<img style="height:auto;width:auto;" id="img1" src="<%=request.getContextPath()%>/front-end/shop/DBGifReader1?shop_no=${shopVO.shop_no}&shopCol=shop_photo">
								</c:if>
								<c:if test="${not empty shopVO}">						
									<img style="height:auto;width:auto;" id="img1" src="<%=request.getContextPath()%>/front-end/shop/DBGifReader1?shop_no=${shopVO.shop_no}&shopCol=shop_photo">
								</c:if>
							</td>
						</div>
					</tr>
					<tr>
						<div class="fred" id="photo">
							<th>小農證明文件:</th>
							<td>
							
								<input type="file" name="shop_proof" accept="image/*"
								id="upfile2" height="100" width="200"> 
							<c:if test="${empty shopVO}">
								<img id="img2"   style="height:auto;width:auto;" src="<%= request.getContextPath()%>/front-end/shop/DBGifReader1?shop_no=${shopVO.shop_no}&shopCol=shop_proof">
							</c:if>
							<c:if test="${not empty shopVO}">
								<img id="img2"  style="height:auto;width:auto;" src="<%= request.getContextPath()%>/front-end/shop/DBGifReader1?shop_no=${shopVO.shop_no}&shopCol=shop_proof">
							</c:if>
							</td>
						</div>
					</tr>
				</table>
				<br>
				<%-- <input type="hidden" name="img" value="${empty shopVO.shop_photo?'':shopVO.shop_photo} "> --%>
				<input type="hidden" name="shop_no" value="${shopVO.shop_no}">
				<input type="hidden" name="shop_id" value="${shopVO.shop_id}">
				<input type="hidden" name="action" value="update"> <input
					type="submit" value="送出修改" class="btn btn-info"
					style="color: #652825"> <a
					href="<%=request.getContextPath()%>/front-end/shop/shop_default.jsp"
					class="btn btn-info">取消</a>
			</form>
		</div>
		<!-- 右欄結束 -->
	</div>
</div>
<!-- 大容器結束 -->
<%@ include file="/front-end/FooterPage.jsp"%>