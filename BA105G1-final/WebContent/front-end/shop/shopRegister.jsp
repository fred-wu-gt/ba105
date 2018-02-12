<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.io.*"%>
<%
  ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
%>		
<head>
<%@ include file="/front-end/HtmlHeadPage.jsp" %>
<title>�Ӯa�|������</title>		
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

		/*IE���ýb�Y�˦�*/
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
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="container">		<!-- �j�e���}�l -->	
	<div class="row">		<!-- row�}�l -->	
		
		<div class="col-xs-12 col-sm-8 col-sm-offset-2">
			<!-- �k��}�l -->
			<div class="panel panel-success">
				<!-- panel sucess�}�l -->
				<div class="panel-heading">
					<h3 class="panel-title" id="regis">�Ӯa���U</h3>
				</div>
				<div class="panel-body">
					<!-- panel body�}�l -->

					<form method="post"
						action="<%=request.getContextPath()%>/front-end/shop/shop.do"
						name="form1" enctype="multipart/form-data">
						<label for="name" class="control-label">�Ӯa�W��</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-apple"></span>
							</span> <input type="text" class="form-control" name="shop_name" id="shop_name"
								placeholder="�п�J�Ӯa�W��" maxlength="10" value="${shopVO.shop_name}">
						</div>
						<label for="id" class="control-label">�Ӯa�b��</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-user"></span>
							</span> <input type="text" class="form-control" id="id" name="shop_id"
								placeholder="�п�J�b��" maxlength="10" value="${shopVO.shop_id}">
						</div>
						<div>
							<span id="id2"></span>
						</div>
						<label for="psw" class="control-label">�Ӯa�K�X</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-lock"></span>
							</span> <input type="password" class="form-control" name="shop_psw" id="shop_psw"
								placeholder="�п�J�K�X" maxlength="10" value="${shopVO.shop_name}">
						</div>
						<label for="email" class="control-label">�q�l�H�c</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-envelope"></span>
							</span> <input type="email" class="form-control" placeholder="�п�J�H�c"
								name="shop_email" id="shop_email" value="${shopVO.shop_email}">
						</div>
						<label for="phone" class="control-label">������X</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-phone"></span>
							</span> <input type="text" class="form-control" name="shop_phone" id="shop_phone"
								placeholder="�п�J������X" maxlength="10"
								value="${shopVO.shop_phone}">
						</div>
						<label for="location" class="control-label">�Ӯa�a�}</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-home"></span>
							</span> <input type="text" class="form-control" name="shop_loc" id="shop_loc"
								placeholder="�п�J�Ӯa�a�}" maxlength="30" value="${shopVO.shop_loc}">
						</div>
						<label for="introduction" class="control-label">�򥻤���</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-pencil"></span>
							</span>
							<textarea name="shop_desc" rows="4" cols="50" name="shop_desc" 
								placeholder="�бԭz�z���򥻤���" maxlength="200" class="form-control"
								id="text"> 
						${shopVO.shop_desc}</textarea>
						</div>
						<label for="bank" class="control-label">�״ڻȦ�</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-piggy-bank"></span>
							</span> <span> <select style="color: black" name="shop_bank"
								class="pretty-select" id="select">
									<option>�鲱�Ȧ�</option>
									<option>�ɤs�Ȧ�</option>
									<option>�x�W�Ȧ�</option>
									<option>�x�s�Ȧ�</option>
									<option>�I���Ȧ�</option>
							</select>
							</span>
						</div>
						<label for="account" class="control-label">�״ڱb��</label>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-usd"></span>
							</span> <input type="text" class="form-control" name="shop_acc" id="shop_acc"
								placeholder="�п�J�״ڻȦ�b��" value="${shopVO.shop_acc}">
						</div>
						<label for="photo" class="control-label">�Ӯa�Ӥ�</label>
						<div class="input-group input-group-lg" id="photo">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-camera"></span>
							</span>  <img class="form-control" id="img1" src="" style="width:70%">
						</div>
						<div>
							<label class="btn btn-info"> <input type="file" name="shop_photo"
								class="form-control" style="display: none;"
								shop_photo" id="upfile1"> <i class="fa fa-photo"></i>
								�W�ǹϤ�
							</label>
						</div>
						
						
						
						<label for="proof" class="control-label">�p�A�ҩ�</label>
						<div class="input-group input-group-lg" id="proof">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-file"></span>
							</span>  <img class="form-control"  id="img2" src="" style="width:70%">
						</div>
						<div>
							<label class="btn btn-info"> <input type="file"
								class="form-control" style="display: none;" name="shop_proof"
								shop_proof" id="upfile2"> <i class="fa fa-photo"></i>
								�W�ǹϤ�
							</label>
						</div>
						<div align="middle">
							<input type="hidden" name="shop_no" value="${shopVO.shop_no}">
							<input type="hidden" name="action" value="insert"> <input
								type="submit" value="�e�X�s�W" class="btn btn-info"
								style="color: #652825"> <a
								href="<%=request.getContextPath()%>/front-end/shop/shop_default.jsp"
								class="btn btn-info">����</a>
						</div>
					</form>
				</div>
				<!-- panel body���� -->
			</div>
			<!-- panel sucess���� -->
		</div>
		<!-- �k�浲�� -->
	</div>
	<!-- row���� -->
</div>
<!-- �j�e������ -->
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
			$('#shop_name').val('java�X�A�A��');
			$('#id').val('SHOP12');
			$('#shop_psw').val('SHOP12');
			$('#shop_loc').val('��饫���c�Ϥ��j��300��');
			$('#text').val('java�X�A�A�� �̦��X�A�̥Τߥh�D�諸�A���~(DEBUG)�C�@�����O�g�L�X�A�Ӥ߬D��~�|�e����O�̤⤤�X�A�̧�C���U�ȷ�@�ۤv���a�H,����~��̦n �~�|�X�f��');
			$('#shop_acc').val('1001254879');
		});
	});

</script>
<%@ include file="/front-end/FooterPage.jsp" %>