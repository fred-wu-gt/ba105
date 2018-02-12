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
<title>�ӫ~�s�W-addShopCommodity.jsp</title>
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
		swal("�s�W�ӫ~���\", "�w�s�W", "success");
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
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<div class="container">				<!-- �j�e���}�l -->		
		<div class="row">						<!-- ROW�}�l -->		
			<div class="col-xs-12 col-sm-2">	<!-- ���� -->				
				<div class="panel-group" id="accordion2" role="tablist"
					aria-multiselectable="true">
					<!-- �϶�1 -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="panel2">
							<h4 class="panel-title">
								<a href="#aaa" data-parent="#accordion2" data-toggle="collapse"
									role="button" class="collapsed" aria-expanded="false"
									aria-controls="aaa">
									<h4>�Ӯa��ƺ޲z</h4>
								</a>
							</h4>
						</div>
						<div id="aaa" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="panel2">
							<div class="panel-body">
								<ul class="list-unstyled">
									<li><a
										href="<%=request.getContextPath()%>/front-end/shop/shopUpdate.jsp"><b>�Ӯa��ƭק�</b></a></li>
								</ul>
							</div>
						</div>
						<!-- �϶�1���� -->
					</div>
					<!-- �϶�2 -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="panel2">
							<h4 class="panel-title">
								<a href="#bbb" data-parent="#accordion2" data-toggle="collapse"
									role="button" class="collapsed" aria-expanded="false"
									aria-controls="bbb">
									<h4>�ӫ~�޲z</h4>
								</a>
							</h4>
						</div>
						<div id="bbb" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="panel2">
							<div class="panel-body">
								<ul class="list-unstyled">
									<li><a
										href="<%=request.getContextPath()%>/front-end/shop/addShopCommodity.jsp"><b>�s�W�ӫ~</b></a></li>
									<li><a
										href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=getOne_For_Display&shop_no=${shopVO.shop_no}"><b>�ӫ~�W�U�[</b></a></li>
									<li><a
										href="<%=request.getContextPath()%>/front-end/ad/adadd.jsp"><b>�ӽХ��s�i</b></a></li>
									<li><a
										href="<%=request.getContextPath()%>/front-end/prom/prom.do?action=init&shop_no=${shopVO.shop_no}"><b>�P�P��׺޲z</b></a></li>
								</ul>
							</div>
						</div>
						<!-- �϶�2���� -->
					</div>
					<!-- �϶�3 -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="panel3">
							<h4 class="panel-title">
								<a href="#ccc" data-parent="#accordion2" data-toggle="collapse"
									role="button" class="collapsed" aria-expanded="false"
									aria-controls="ccc">
									<h4>�q��޲z</h4>
								</a>
							</h4>
						</div>
						<div id="ccc" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="panel3">
							<div class="panel-body">
								<ul class="list-unstyled">
									<li><a
										href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=shopOrder&shop_no=${shopVO.shop_no}"><b>�ӫ~�X�f���A</b></a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- �϶�3���� -->
				</div>
			</div>	<!-- ���浲�� -->			
			<div class="col-xs-12 col-sm-10">	<!-- �k��}�l -->	
				<h3 id="addcom"><b>��Ʒs�W:</b></h3>			
				<form action="<%=request.getContextPath()%>/front-end/shop/shop.do"
					method="post" name="form1" enctype="multipart/form-data">
					<table class="table table-hover table-bordered" id="table">
						<tr>
							<td class="noWrap"><b>�ӫ~�W��</b></td>
							<td><input type="text" name="com_name" size="100"
								maxlength="33" id="com_name"
								value="<%=(comVO == null) ? " " : comVO.getCom_name()%>"></td>
						</tr>
						<tr>
							<td class="noWrap"><b>���G����:</b></td>
							<td><select size="1" name="fru_no">
									<c:forEach var="fruitVO" items="${fruSvc.all}">
										<option value="${fruitVO.fru_no}"
											${(comVO.fru_no==fruitVO.fru_no)? 'selected':'' }>${fruitVO.fru_name}
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td class="noWrap"><b>���</b></td>
							<td><input type="text" name="com_price" size="100"
								maxlength="10" id="com_price"
								value="<%=(comVO == null) ? "" : comVO.getCom_price()%>"></td>
						</tr>
						<tr>
							<td class="noWrap"><b>�W��</b></td>
							<td><input type="text" name="com_weight" size="100"
								maxlength="10" id="com_weight"
								value="<%=(comVO == null) ? "" : comVO.getCom_weight()%>">
							</td>
						</tr>
						<tr>
							<td class="noWrap"><b>�ӫ~�ԭz</b></td>
							<td><textarea rows="5" cols="50" name="com_remarks" id="com_remarks"><%=(comVO == null) ? "" : comVO.getCom_remarks()%></textarea>
							</td>
						</tr>
						<tr>
							<td class="noWrap"><b>�ӫ~�Ӥ�1:</b></td>
							<td><label class="btn btn-info"> <input type="file"
									name="com_pic1" size="100" id="upfile1" style="display: none;" <%=(comVO == null) ? "" : comVO.getCom_pic1()%>" />
									<i class="fa fa-photo"></i> �W�ǹϤ�
							</label> <img id="img1" src="" ></td>
						</tr>
						<tr>
							<td class="noWrap"><b>�ӫ~�Ӥ�2:</b></td>
							<td><label class="btn btn-info"> <input type="file"
									name="com_pic2" size="100" id="upfile2" style="display: none;" <%=(comVO == null) ? "" : comVO.getCom_pic2()%>" />
									<i class="fa fa-photo"></i> �W�ǹϤ�
							</label> <img id="img2" src="" ></td>
						</tr>
						<tr>
							<td class="noWrap"><b>�ӫ~�Ӥ�3:</b></td>
							<td><label class="btn btn-info"> <input type="file"
									name="com_pic3" size="100" id="upfile3" style="display: none;" <%=(comVO == null) ? "" : comVO.getCom_pic3()%>" />
									<i class="fa fa-photo"></i> �W�ǹϤ�
							</label> <img id="img3" src="" ></td>
						</tr>
						<tr>
						</tr>
						<tr>
							<td class="noWrap"><b>�W/�U�[���A:</b></td>
							<td><select size="1" name="com_status">
									<option
										value="<%=(comVO == null ? "�U�[" : comVO.getCom_status())%>">�U�[
									<option value="�W�[">�W�[
							</select></td>
						</tr>
						<tr>
							<td class="noWrap"><b>�w�s�ƶq</b></td>
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
						type="submit" value="�s�W�ӫ~">
				</form>
			</div>	<!-- �k�浲�� -->			
		</div>	<!-- row���� -->		
	</div>	<!-- �j�e������ -->	
	
	<script>
	$().ready(function(){
		$('#addcom').click(function(){
			$('#com_name').val('�����n�δ�');
			$('#com_price').val('200');
			$('#com_weight').val('����500g');
			$('#com_remarks').val('�����t���״I����i�A���ΦR��S�ܦn��֡A�@�~���Y�����Ͳ��A�Y�F�������P�����q�]���P��Ӱ��A�ҥH���ԪQ���ɮɳ��|�ǳƭ��������ɥR��O�A��i���n���G�A��������̦w���w�ߡC�S�O�O�굦�|�P�ǧ����Ӧh�Y�����A�WServlet�~���|���O��');
			$('#com_store').val('80');
		});
	});
	</script>
<%@ include file="/front-end/FooterPage.jsp" %>