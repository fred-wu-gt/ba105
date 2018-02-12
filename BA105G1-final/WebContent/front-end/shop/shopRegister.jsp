<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.io.*"%>
<%
  ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
%>		
<head>
<%@ include file="/front-end/HtmlHeadPage.jsp" %>
<title>商家會員中心</title>		
<script>
		$(function(){		
		 $("#upfile1").change(function(){
			    readImage( this );
			  });
			  function readImage(input) {
			    if ( input.files && input.files[0] ) {
			      var FR= new FileReader();
			      FR.onload = function(e) {
			        $('#img1').attr( "src", e.target.result );
			        $('#img1').show();
			      };       
			      FR.readAsDataURL( input.files[0] );
			    }
			  }
		});	
		$(function(){		
		 $("#upfile2").change(function(){
			    readImage( this );
			  });

			  function readImage(input) {
			    if ( input.files && input.files[0] ) {
			      var FR= new FileReader();
			      FR.onload = function(e) {
			        $('#img2').attr( "src", e.target.result );
			        $('#img2').show();
			      };       
			      FR.readAsDataURL( input.files[0] );
			    }
			  }
		});	
</script>
<style type="text/css">

  #text{height: 231px;}

		.pretty-select {		  
		  border:0px;
		  width:100%;
		  height:34px;
		  padding-left:2px;
		  padding-right:40px;
		  color:gray;
		}

		/*IE隱藏箭頭樣式*/
		.pretty-select::-ms-expand { 
		  display: none; 
		}

		.pretty-select:focus{
		  box-shadow: 0 0 5px 2px #467BF4;    
		}

		#select{
			height: 46px;
			border: 1px solid;
			border-radius: 5px;
			border-color: #ccc;
			box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
		}
 		#img1{  
  		  height:auto;
  		  width:auto;
  		display:${empty shopVO.shop_photo?"none" : ""}  ;
 		}  
 		#img2{  
 		  height:auto;
 		  width:auto;
 		display:${empty shopVO.shop_proof?"none" : ""}  ;
  		}  


		.btn{
		margin-top: 6px;
		}		
</style>
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
<div class="container">		<!-- 大容器開始 -->	
	<div class="row">		<!-- row開始 -->	
		
		<div class="col-xs-12 col-sm-8 col-sm-offset-2">
			<!-- 右欄開始 -->
			<div class="panel panel-success">
				<!-- panel sucess開始 -->
				<div class="panel-heading">
					<h3 class="panel-title" id="regis">商家註冊</h3>
				</div>
				<div class="panel-body">
					<!-- panel body開始 -->

					<form method="post"
						action="<%=request.getContextPath()%>/front-end/shop/shop.do"
						name="form1" enctype="multipart/form-data">
						<label for="name" class="control-label">商家名稱</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-apple"></span>
							</span> <input type="text" class="form-control" name="shop_name" id="shop_name"
								placeholder="請輸入商家名稱" maxlength="10" value="${shopVO.shop_name}">
						</div>
						<label for="id" class="control-label">商家帳號</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-user"></span>
							</span> <input type="text" class="form-control" id="id" name="shop_id"
								placeholder="請輸入帳號" maxlength="10" value="${shopVO.shop_id}">
						</div>
						<div>
							<span id="id2"></span>
						</div>
						<label for="psw" class="control-label">商家密碼</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-lock"></span>
							</span> <input type="password" class="form-control" name="shop_psw" id="shop_psw"
								placeholder="請輸入密碼" maxlength="10" value="${shopVO.shop_name}">
						</div>
						<label for="email" class="control-label">電子信箱</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-envelope"></span>
							</span> <input type="email" class="form-control" placeholder="請輸入信箱"
								name="shop_email" id="shop_email" value="${shopVO.shop_email}">
						</div>
						<label for="phone" class="control-label">手機號碼</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-phone"></span>
							</span> <input type="text" class="form-control" name="shop_phone" id="shop_phone"
								placeholder="請輸入手機號碼" maxlength="10"
								value="${shopVO.shop_phone}">
						</div>
						<label for="location" class="control-label">商家地址</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-home"></span>
							</span> <input type="text" class="form-control" name="shop_loc" id="shop_loc"
								placeholder="請輸入商家地址" maxlength="30" value="${shopVO.shop_loc}">
						</div>
						<label for="introduction" class="control-label">基本介紹</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-pencil"></span>
							</span>
							<textarea name="shop_desc" rows="4" cols="50" name="shop_desc" 
								placeholder="請敘述您的基本介紹" maxlength="200" class="form-control"
								id="text"> 
						${shopVO.shop_desc}</textarea>
						</div>
						<label for="bank" class="control-label">匯款銀行</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-piggy-bank"></span>
							</span> <span> <select style="color: black" name="shop_bank"
								class="pretty-select" id="select">
									<option>日盛銀行</option>
									<option>玉山銀行</option>
									<option>台灣銀行</option>
									<option>台新銀行</option>
									<option>富邦銀行</option>
							</select>
							</span>
						</div>
						<label for="account" class="control-label">匯款帳號</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-usd"></span>
							</span> <input type="text" class="form-control" name="shop_acc" id="shop_acc"
								placeholder="請輸入匯款銀行帳號" value="${shopVO.shop_acc}">
						</div>
						<label for="photo" class="control-label">商家照片</label>
						<div class="input-group input-group-lg" id="photo">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-camera"></span>
							</span>  <img class="form-control" id="img1" src="" style="width:70%">
						</div>
						<div>
							<label class="btn btn-info"> <input type="file" name="shop_photo"
								class="form-control" style="display: none;"
								shop_photo" id="upfile1"> <i class="fa fa-photo"></i>
								上傳圖片
							</label>
						</div>
						
						
						
						<label for="proof" class="control-label">小農證明</label>
						<div class="input-group input-group-lg" id="proof">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-file"></span>
							</span>  <img class="form-control"  id="img2" src="" style="width:70%">
						</div>
						<div>
							<label class="btn btn-info"> <input type="file"
								class="form-control" style="display: none;" name="shop_proof"
								shop_proof" id="upfile2"> <i class="fa fa-photo"></i>
								上傳圖片
							</label>
						</div>
						<div align="middle">
							<input type="hidden" name="shop_no" value="${shopVO.shop_no}">
							<input type="hidden" name="action" value="insert"> <input
								type="submit" value="送出新增" class="btn btn-info"
								style="color: #652825"> <a
								href="<%=request.getContextPath()%>/front-end/shop/shop_default.jsp"
								class="btn btn-info">取消</a>
						</div>
					</form>
				</div>
				<!-- panel body結束 -->
			</div>
			<!-- panel sucess結束 -->
		</div>
		<!-- 右欄結束 -->
	</div>
	<!-- row結束 -->
</div>
<!-- 大容器結束 -->
<script type="text/javascript">
$(document).ready(
		
		function test(){
			$("#id").change(function(){
				
				var shop_id2 = $("#id").val();
				$.ajax({
					type:"post",
					url:"<%=request.getContextPath()%>/front-end/shop/shop.do",
					data:{
						action:'checkRegisteredID',
						shop_id : shop_id2,
					},
					dataType : "json",
					success : function(data){
						console.log(data);
						if (data.result == 'false'){
							$('#id2').html(
									'<font color="green">'+data.message+'</font>');
							$('#id').parent().removeClass('has-error').addClass('has-success');
						} else {
							$('#id2').html('<font color="red">'+data.message+'</font>');
							$('#id').parent().removeClass('has-success').addClass('has-error');
						}	
					},
					err: function(){
						console.log('error your json response not true,plz reset your contorller')
					}
					
				});							
			});

})



	$().ready(function(){
		$('#regis').click(function(){
			$('#shop_name').val('java碼農農場');
			$('#id').val('SHOP12');
			$('#shop_psw').val('SHOP12');
			$('#shop_loc').val('桃園市中壢區中大路300號');
			$('#text').val('java碼農農場 裡有碼農們用心去挑選的農產品(DEBUG)每一項都是經過碼農細心挑選才會送到消費者手中碼農們把每位顧客當作自己的家人,堅持品質最好 才會出貨喔');
			$('#shop_acc').val('1001254879');
		});
	});

</script>
<%@ include file="/front-end/FooterPage.jsp" %>