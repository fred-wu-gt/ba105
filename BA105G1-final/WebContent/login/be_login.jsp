<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/res/image/home/logo_transparent.png" />
		<title>[果書]後台管理系統</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.1/jquery.mobile-1.2.1.min.css" />
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<style type="text/css">

			body,html{
				font-family: 微軟正黑體;
				font-size: 18px;
				color: #652825;
				background: linear-gradient(to bottom right,white,#FFE8E8);
				height: 100%;
			}	
			#manager_login_panel span{
				font-size: 1em;
			}
			#manager_login_panel{
				background-color: white;
				border:1px lightblue outset;
				border-radius: 10px;
				padding-top: 30px;
				padding-bottom: 30px;
				top:20%;
				position: relative;
			}
			#manager_login_panel #titleDiv{
				margin-top: 5%;
			}
			#manager_login_panel #titleDiv>span{
				font-size: 2em;
			}
			#manager_login_panel img{
				width: 90%;
			}
			#manager_login_panel .row{
				margin-bottom: 10px;
			}
			#manager_login_panel .inputTitle{
				font-size: 1.2em;
				margin-left: -5px;
				margin-right: -5px;
			}
			#manager_login_panel input{
				width: 100%;
			}
			#manager_login_panel .btn{
				width: 100%;
				font-size: 1em;
			}
			#manager_login_panel a{
				align: right;
			}
			@media screen and (max-width:1050px){

				body #manager_login_panel{
					font-size: 16px;
				}
				#manager_login_panel{
					padding-left: 0;
					padding-right: 0;
				}
				#manager_login_panel #titleDiv>span{
					font-size: 1.9em;
				}
			}
			@media screen and (max-width:850px){

				#manager_login_panel #titleDiv>span{
					font-size: 1.6em;
					margin-left: -5px;
				}
			}
			@media screen and (max-width:767px){

				#manager_login_panel #titleDiv>span{
					font-size: 2.5em;
					margin-left: 15px;
				}
			}
			@media screen and (max-width:552px){

				#manager_login_panel #titleDiv>span{
					font-size: 1.5em;
					margin-left: 0px;
				}
			}
		</style>

	</head>

	<body>
		<div class="col-xs-12 col-sm-6 col-sm-offset-3  col-md-6 col-md-offset-3" id="manager_login_panel">
		    <div class="container-fluid">
		    	<div class="row">
		    		<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<img src="<%=request.getContextPath()%>/res/image/home/logo_transparent.png">
					</div>
					<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9" id="titleDiv">
						<span>果書 - 後台管理系統</span>
					</div>
		    	</div>
		    	<form method="post" action="<%=request.getContextPath()%>/login/LoginHandler">
			    	<div class="row">
			    		<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2" align="right">
							<span class="inputTitle">帳號</span>
						</div>
						<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
							<input type="email" maxLength="30" name="account" id="account" value="${param.account}">
						</div>
			    	</div>
			    	<div class="row">
			    		<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2" align="right">
							<span class="inputTitle">密碼</span>
						</div>
						<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
							<input type="password" maxLength="30" id="password"  name="password" value="${param.password}">
						</div>
			    	</div>
			    	<input type="hidden" name="action" value="managerLogin">
			    	<input type="submit" class="btn btn-success" value="登入">
			    </form>
			    <div style="color:red" class="col-xs-9"> ${errorMsgs[0]}</div>

		    	<div align="right" class="col-xs-3">
		    		<a><span>忘記密碼</span></a>
		    		<input type="button" id="magic" style="width:20px;height:20px;background-color:lightyellow;border:0;">
		    		
		    		
		    		<script type="text/javascript">
		    		$("#magic").click(function(){
		    			$("#account").val("blessing03250@gmail.com");
		    			
		    		});
		    		</script>
		    	</div>
		    </div>
		</div>
	</body>
</html>