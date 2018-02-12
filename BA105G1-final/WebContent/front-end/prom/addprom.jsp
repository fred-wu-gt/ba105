<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<%@ page import="com.prom.model.*"%>

<%@ page import="com.shop.model.*"%>
<!--  TODO 2018/01/21整合須併入 承霖 -->
<%@ include file="/front-end/HtmlHeadPage.jsp" %>
<%
	PromVO promVO = (PromVO) request.getAttribute("promVO");

ShopVO shopVO = (ShopVO) session.getAttribute("loginShopVO");
pageContext.setAttribute("shopVO",shopVO);
%>
	<head>	
	<jsp:useBean id="promSvc" scope="page" class="com.prom.model.PromService"/>	
	
<style type="text/css">

		#select{
			height: 46px;
			border: 1px solid;
			border-radius: 5px;
			border-color: #ccc;
			box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
		}
		
		#select2{
			height: 46px;
			border: 1px solid;
			border-radius: 5px;
			border-color: #ccc;
			box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
		}

		.pretty-select {		  
		  border:0px;
		  width:30%;
		  height:34px;
		  padding-left:2px;
		  padding-right:40px;
		  color:gray;
		}
		.pretty-select::-ms-expand { 
		  display: none; 
		}
		.pretty-select:focus{
		  box-shadow: 0 0 5px 2px #467BF4;    
		}
		.btn{
		margin-top: 6px;}


</style>
<script type="text/javascript">

        $(document).ready(function(){
        	
		$("#show").click(function() {
			$("#discount").show(300);
		});
		
		$("#hide").click(function() {
			$("#discount").hide(300);
		});
		if ("${saveSuccess}".length != 0) {
			swal("修改完成", "已修改", "success");
		}
		if ("${notSaveSuccess}".length != 0) {
			console.log("${notSaveSuccess}");
			swal("修改失敗", "修改失敗", "error");
		}
		if ("${saveSuccess}".length != 0) {
			swal("修改成功", "修改完成", "success");
		$(".firstOption").val(${firstOption});
		$(".secondOption").val(${secondOption});
		}
		
		if("${initialData}".length != 0){
			$(".firstOption").val(${firstOption});
			$(".secondOption").val(${secondOption});
		}
	});
</script>

	</head>		
	<%@ include file="/front-end/HeaderPage.jsp" %>		
	
	<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

		<!-- 大容器開始 -->
		<div class="container">
			<div class="row">
			
			<!-- 左欄 -->
				<div class="col-xs-12 col-sm-2">
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					  <!-- 區塊1 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel2">
					      <h4 class="panel-title">
					        <a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="aaa">
					          <h4>商家資料管理</h4>
					        </a>
					      </h4>
					    </div>
					    <div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
					      <div class="panel-body">
					        <ul class="list-unstyled">
<!-- 					        	<li><a href="#"><b>商家資料查詢</b></a></li> -->
					        	<li><a href="<%=request.getContextPath()%>/front-end/shop/shopUpdate.jsp"><b>商家資料修改</b></a></li>
					        </ul>
					      </div>
					    </div>
					    <!-- 區塊1結束 -->
					  </div>
					  <!-- 區塊2 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel2">
					      <h4 class="panel-title">
					        <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
					          <h4>商品管理</h4>
					        </a>
					      </h4>
					    </div>
					    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
					      <div class="panel-body">
					        <ul class="list-unstyled">
					        	<li><a href="<%=request.getContextPath()%>/front-end/shop/addShopCommodity.jsp"><b>新增商品</b></a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=getOne_For_Display&shop_no=${shopVO.shop_no}"><b>商品上下架</b></a></li>
					        	<li><a href="<%=request.getContextPath()%>/front-end/ad/adadd.jsp"><b>申請打廣告</b></a></li>
					        	<li><a href="<%=request.getContextPath()%>/front-end/prom/prom.do?action=init&shop_no=${shopVO.shop_no}"><b>促銷方案管理</b></a></li>
					        </ul>
					      </div>
					    </div>
					    <!-- 區塊2結束 -->
					  </div>
					  <!-- 區塊3 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel3">
					      <h4 class="panel-title">
					        <a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
					          <h4>訂單管理</h4>
					        </a>
					      </h4>
					    </div>
					    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
					      <div class="panel-body">
							 <ul class="list-unstyled">
					        	<li><a href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=shopOrder&shop_no=${shopVO.shop_no}"><b>商品出貨狀態</b></a></li>
					        </ul>
					      </div>
					    </div>
					  </div>
					<!-- 區塊3結束 -->			
					</div>
					</div>	<!-- 左欄結束 -->					
				<div class="col-xs-12 col-sm-10">			<!-- 右欄開始 -->
					<div class="panel panel-success">		<!-- panel sucess開始 -->
					<div class="panel-heading">
					<h3 class="panel-title">修改促銷方案</h3>
					</div>
					<div class="panel-body">		<!-- panel body開始 -->
					<form action="<%=request.getContextPath()%>/front-end/prom/prom.do" method="post">
					<label for="name" class="control-label">商家名稱</label>
					<div class="input-group input-group-lg">
						 <span class="input-group-addon">
						 <span class="glyphicon glyphicon-user"></span>
					 	 </span>
					  <h4>${shopVO.shop_name}</h4>							 
					</div>
					<label for="promStart" class="control-label">促銷開始時間</label>
					<div class="input-group input-group-lg">
						 <span class="input-group-addon">
						 <span class="glyphicon glyphicon-calendar"></span>
					 	 </span>
					  <input type="text" class="form-control" name="prom_start" id="f_date1" placeholder="請輸入開始日期"
							 value="${promSvc.findByPrimaryKey(shopVO.shop_no).prom_start}" >
					</div>
					<label for="promEnd" class="control-label">促銷結束時間</label>
					<div class="input-group input-group-lg">
						 <span class="input-group-addon" >
					  	 <span class="glyphicon glyphicon-calendar"></span>
					 	 </span>
					  <input type="text" class="form-control" name="prom_end" id="f_date2" placeholder="請輸入結束日期"
							 value="${promSvc.findByPrimaryKey(shopVO.shop_no).prom_end}" >
					</div>				
					<label for="discount" class="control-label">折扣</label>
					<div class="input-group input-group-lg">					 	 					 	 	
					 <div>
						<span class="radiobutton">
					 		<label for="">不要折扣
					 			<input type="radio" name="hasDiscount" id="hide" checked="checked" value="noDiscount">
					 		</label>
					 	</span>
					 	<span class="radiobutton">
					 		<label for="">點選折扣
					 			<input type="radio" name="hasDiscount" id="show" value="discount">
					 		</label>
					 	</span>
					 </div>					 						  
					</div>								
					<div id="discount" style="display: none">	<!-- display開始 -->
						<label for="bank" class="control-label">促銷折扣</label>
						<div class="input-group input-group-lg">
							 <span class="input-group-addon" >
						  	 <span class="glyphicon glyphicon-piggy-bank"></span>
						 	 </span>						
							<select style="color:black" name="prom_dis" class="pretty-select firstOption" id="select">
								<option>9</option>
								<option>8</option>
								<option>7</option>
								<option>6</option>
								<option>5</option>
								<option>4</option>
								<option>3</option>
								<option>2</option>
								<option>1</option>
							</select>		
							<select style="color:black" name="prom_dis1" class="pretty-select secondOption" id="select2">
								<option>9</option>
								<option>8</option>
								<option>7</option>
								<option>6</option>
								<option>5</option>
								<option>4</option>
								<option>3</option>
								<option>2</option>
								<option>1</option>
								<option>0</option>
							</select>
						</div>
					</div>			<!-- display結束 -->			
					<input type="hidden" name="shop_no" value="${shopVO.shop_no}">
					<input type="hidden" name="prom_no" value="${promSvc.findByPrimaryKey(shopVO.shop_no).prom_no}">
					<input type="hidden" name="action" value="update">
					<input type="submit" value="送出" class="btn btn-info" >
					<a href="<%=request.getContextPath()%>/front-end/shop/shop_default.jsp" class="btn btn-info">取消</a>
					</form>
				
		  </div>	<!-- panel body結束 -->
		</div>		<!-- panel sucess結束 -->
				</div>	<!-- 右欄結束 -->
			</div>
		</div>	<!-- 大容器結束 -->		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
		<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
		<script>
        $.datetimepicker.setLocale('zh');
        var somedate1 = new Date('2018-01-01');
        $('#f_date1').datetimepicker({
        	format:'Y-m-d H:i:s',
            beforeShowDay: function(date) {
          	  if (  date.getYear() <  somedate1.getYear() || 
   		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
   		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                ) {
                     return [false, ""]
                }
                return [true, ""];
        }});
        
        var somedate1 = new Date('2018-01-01');
        $('#f_date2').datetimepicker({
        	format:'Y-m-d H:i:s',
            beforeShowDay: function(date) {
          	  if (  date.getYear() <  somedate1.getYear() || 
   		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
   		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                ) {
                     return [false, ""]
                }
                return [true, ""];
        }});
        $('#f_date2').change(function(){
//         	alert($('#f_date2').val());
        });

</script>
<br><br><br>
<%@ include file="/front-end/FooterPage.jsp" %>