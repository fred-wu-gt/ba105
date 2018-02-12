<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ad.model.*"%>

<%@ page import="com.shop.model.*"%>
<%
ShopVO shopVO = (ShopVO) session.getAttribute("loginShopVO");
pageContext.setAttribute("shopVO",shopVO);
  AdVO adVO = (AdVO) request.getAttribute("adVO");
%>
<%@ include file="/front-end/HtmlHeadPage.jsp" %>
<head>
<script>
// TODO 2018/01/21整合須併入 承霖
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

$(function(){		
	 $("#upfile").change(function(){
		    readImage( this );
	 });	 	 
		  function readImage(input) {
		    if ( input.files || input.files[0] ) {   
		      var FR= new FileReader();
		      FR.onload = function(e) {
		        $('#img').attr( "src", e.target.result );
		        $('#img').show();
		      };       
		      FR.readAsDataURL( input.files[0] );
		    }
		  }
});

$(function(){
	$("#f_date1,#f_date2").change(function(){
		var start=new Date($("#f_date1").val());
		var end=new Date($("#f_date2").val());
		if($("#f_date1").val()!="" && $("#f_date2").val()!=""){
			ad_idesta = start.getTime();
			ad_end = end.getTime();
			ad_total=Math.round(100*(ad_end-ad_idesta)/(1000*60*60*24));
			$("#ad_exp").html(ad_total+"元");
		}	
	});
});
</script>
<style>

#img {
	height:auto;
	width:auto;
	display:${empty adVO.ad_photo?"none":""};
 } 
 
</style>
<title>申請打廣告</title>
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
<div class="container">	<!-- 大容器開始 -->	
	<div class="row">
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
		</div>		
		<div class="col-xs-12 col-sm-10">		<!-- 右欄開始 -->
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title">申請打廣告</h3>
				</div>
				<div class="panel-body">
					<form name="form1" method="post" enctype="multipart/form-data"
						action="<%=request.getContextPath()%>/front-end/ad/ad.do">
						<div>
							<label for="name" class="control-label">店家名稱:</label>
							${shopVO.shop_name}
						</div>
						<div>
							<label for="name" class="control-label">廣告開始時間:</label> <input
								name="ad_idesta" id="f_date1" type="text"
								value="<%=(adVO == null) ? "" : adVO.getAd_idesta()%>">
						</div>
						<div>
							<label for="name" class="control-label">廣告結束時間:</label> <input
								name="ad_end" id="f_date2" type="text"
								value="<%=(adVO == null) ? "" : adVO.getAd_end()%>">
						</div>
						<div>
							<label for="photo" class="control-label">廣告照片</label> <input
								name="ad_photo" type="file" id="upfile">
							<div id="imgDiv">
								<img id="img" style="height:auto;width:auto;" src="">
							</div>
						</div>
						<div >
							<h4>一天100$,總共:<font color=red><b name="pay" value="payfor" id="ad_exp"></b></font></h4>
						</div>
						<input type="submit" value="送出"> <input type="hidden"
							name="action" value="add"> <input type="hidden"
							name="shop_no" value="${shopVO.shop_no}"> <input
							type="hidden" name="ad_no" value="${adVO.ad_no}"> <input
							type="hidden" name="img"
							value="${empty adVO.ad_photo?  '':adVO.ad_photo}">
					</form>
				</div>
			</div>
		</div>		<!-- 右欄結束 -->
	</div>
</div>	<!-- 大容器結束 -->
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="/front-end/FooterPage.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
        $.datetimepicker.setLocale('zh');
        var somedate1 = new Date('2018-01-25');
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
        
        var somedate1 = new Date('2018-01-25');
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
