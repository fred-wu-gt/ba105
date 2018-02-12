<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wri_mes.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%--不是這一支送出表單 --%>

<style>
#media-body{
	margin-left:200px;
}

</style>

<!-- 標題、圖片的container開始-->
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="container">
							<div class="row">
								<div class="col-xs-12 col-sm-12">

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
<!-- 標題、圖片的container結束-->

<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-12">

				
					</div>
				</div>
			</div>
		</div>
		
							
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<fieldset style="width:90%;background-color: #pink">
							<legend style=" color:blue; font-size: 19px"></legend>
									</fieldset>									
		
			<div id="wrt_mes_area" >
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/writing_mes/writing_mes.do" name="form1">
						<div class="media">
							 <div class="media-left">
							
							 		</div>
							<div class="media-body" style="width:1000px;">
								 <h4 class="media-heading" style="width:90px;margin-top:25px;"></h4>
						 			</div>
						 				<div class="media-body">
									 		<textarea class="form-control" style="resize: none;" name="wmsg_cont" maxlength='70' rows="15" cols="100" class="t4textarea"></textarea>
									 	</div>
									  		<div class="media-body" style="width:1500px;">
										<input type="hidden" name="action"   value="insert_from_writing_mes"> 
									<input type="hidden" name="wrt_no"   value="WRT0000001"> 
								<input type="hidden" name="mem_no"   value="MEM0000001"> 
							<input type="submit" value="送出留言" class="btn btn-default btn-lg subBtn" name="">
						<a href="<%=request.getContextPath()%>/front-end/writing/Writing_Home_Page.jsp"	class="btn btn-default btn-lg BacklistBtn">回文章列表</a>
					</div>
				</div>
			</form>
		</div>
						 </div>
					<!-- row結束-->			
				</div>
			<!--container結束 -->
		</div>
	</div>
</div>


	<script type="text/javascript">
		$(document).ready(function(){
			$(".carousel").carousel({
			interval:4000,
			pause:false
			});
		});
	</script>