<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.writing.model.*"%>
<%
	WritingVO writingVO = (WritingVO) request.getAttribute("writingVO");
%>

<%@ include file="/front-end/HtmlHeadPage.jsp"%>


<style type="text/css">
#img {
	width: 900px;
	display: ${empty writingVO.wrt_photo_base64?"none":""
}

;
}
table th {
	width: 20%;
}

#submit_button {
	margin-left: 1006px;
}

.centerTable {
	border: solid 10px;
	padding-top: 10px;
	padding-bottom: 10px;
	border-color: #ccc;
}

.inputTable td {
	padding: 5px;
}

.t4textarea {
	resize: none;
}

.subBtn:hover {
	background-color: #f8db95;
}

.BacklistBtn:hover {
	background-color: #cff6d8;
}
</style>


<%@ include file="/front-end/HeaderPage.jsp"%>

<div id="main-content">
	<!-- 內文開始 -->

	<!-- 錯誤修正驗證區塊-->

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>

		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<!-- 錯誤修正驗證區塊-->


	<!--表單內容區塊開始 -->
	<div>
		<div class="container">
			<div class="row">
				<div class="" col-xs-12 col-sm-10 col-sm-offset-1centerTable">

				

					<FORM METHOD="post" ACTION="writing.do" name="form1"	enctype="multipart/form-data">
						<div class="panel panel-info">
							<div class="panel-heading">
								<th><h1>新增一篇文章</h1></th>
							</div>
							<div class="panel-body">
								<table class="inputTable">
									<tr>
										<th><h1>文章標題 :</h1></th>
										<td colspan="2"><input type="text" name="wrt_title"
											size="60" maxLength="30"
											value="<%=(writingVO == null) ? "" : writingVO.getWrt_title()%>" />
										</td>
									</tr>

									<tr>
										<th><h1>文章內容 :</h1></th>
										<td colspan="2"><textarea name="wrt_cont" rows="10"
												cols="100" class="t4textarea"><%=(writingVO == null) ? "" : writingVO.getWrt_cont()%></textarea>
										</td>
									</tr>

									<tr>
										<th><h1>上傳照片:</h1></th>
										<td colspan="2"><input width="100" accept="image/*"
											id="uploadImage" type="file" name="wrt_photo_base64"></td>
									</tr>

								</table>
								<input type="hidden" name="shop_no" value="SHOP000001">
								<input type="hidden" name="img" 	value="${empty writingVO.wrt_photo_base64? '':writingVO.wrt_photo_base64} ">
								<input type="hidden" name="action" value="insert"> 
								<input type="submit" value="送出新增" class="btn btn-default btn-lg subBtn"> 
								<a	href="<%=request.getContextPath()%>/front-end/writing/writing.do?action=listAllWritingFromShop"	class="btn btn-default btn-lg BacklistBtn">回文章列表</a>
							</div>
						</div>

					</FORM>
					<button id="magic" class="btn btn-default btn-lg submagicBtn">神奇小按鈕</button>
				</div>
			</div>
		</div>
	</div>


	<!--表單內容區塊結束 -->



</div>
<!-- 內文結束 -->

<img id="img"
	src="<%=(writingVO == null) ? "" : "data:image/jpeg;base64," + writingVO.getWrt_photo_base64()%>" />
<script>
	$("#uploadImage").change(function() {
		readImage(this);
	});

	function readImage(input) {
		if (input.files && input.files[0]) {
			var FR = new FileReader();
			FR.onload = function(e) {
				//e.target.result = base64 format picture
				$('#img').attr("src", e.target.result);
				$('#img').show();
			};
			FR.readAsDataURL(input.files[0]);
		}
	}
	
	$('#magic').click(function(){
		$('[name=wrt_title]').val('不一樣的養生法');
		$('[name=wrt_cont]').val
('學習java ,越學越健康，越寫越早睡...還不錯..吧。JAVA是簡單的。所謂Object Code指的是和硬體相關的機器指令,要甚麼就給甚麼，JAVA三部曲:宣告、取值、拿來用。JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的 JAVA是簡單的'
				
		
		
		
		);
	});
	
</script>
<br>

</div>

<script type="text/javascript">
	$(document).ready(function() {
		$(".carousel").carousel({
			interval : 4000,
			pause : false
		});
	});
</script>



<%@ include file="/front-end/FooterPage.jsp"%>