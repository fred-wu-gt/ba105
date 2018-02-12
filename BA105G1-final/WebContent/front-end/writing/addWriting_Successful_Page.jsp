<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.writing.model.*"%>
<%
	WritingVO writingVO = (WritingVO) request.getAttribute("writingVO");
%>
<%@ include file="/front-end/HtmlHeadPage.jsp"%>





<style type="text/css">
</style>



<%@ include file="/front-end/HeaderPage.jsp"%>


<div id="main-content">
	<!-- 內文開始 -->


	<!--轉交成功區塊開始 -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-6">
							<FORM METHOD="post" ACTION="writing.do" name="form1"
								enctype="multipart/form-data">

								<input type="hidden" name="img"
									value="${empty writingVO.wrt_photo_base64? '':writingVO.wrt_photo_base64} ">
								<input type="hidden" name="action" value="insert">


							</FORM>
						</div>
						
						
						<div class="col-xs-12 col-sm-6">
							<P>已成功送出文章!</P>
							<a	href="<%=request.getContextPath()%>/front-end/writing/writing.do?action=listAllWritingFromShop"	class="btn btn-default btn-lg BacklistBtn">回文章列表</a>
						</div>
						
						
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--轉交成功區塊結束 -->



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
