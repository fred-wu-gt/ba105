<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.writing.model.*"%>

<%
	WritingVO writingVO = (WritingVO) request.getAttribute("writingVO");
	//WritingServlet.java (Controller) 存入req的writingVO物件 (包括幫忙取出的writingVO, 也包括輸入資料錯誤時的writingVO物件)
%>
<%@ include file="/front-end/HtmlHeadPage.jsp"%>

<style type="text/css">
#img {
	width:1200px;
	display: ${empty writingVO.wrt_photo_base64?"none":""};
	}
table th {
	width: 20%;
}

#submit_button {
	margin-left: 1400px;
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

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM method="post" action="writing.do" name="form1" enctype="multipart/form-data">

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<th><h1>修改文章</h1></th>
					</div>
					<div class="panel-body">
						<table class="inputTable">
							<tr>
								<th><h1>文章標題:</h1></th>
								<td><input type="text" name="wrt_title"
									value="${writingVO.wrt_title}" maxLength="50" /></td>
							</tr>

							<tr>
								<th><h1>文章內容:</h1></th>
								<td><textarea name="wrt_cont" rows="10" cols="100">${writingVO.wrt_cont}</textarea>
								</td>
							</tr>

							<tr>
								<th><h1>商家編號:</h1></th>
								<td><input type="text" name="wrt_title"	value="${writingVO.shop_no}" maxLength="50" /></td>
							</tr>

							<tr>
								<th><h1>更改照片:</h1></th>
								<td colspan="2"><input width="100" accept="image/*"	id="uploadImage" type="file" name="wrt_photo_base64"></td>
							</tr>
						</table>
						
							<input type="submit" value="送出修改" class="btn btn-default btn-lg subBtn"> 
							<a	href="<%=request.getContextPath()%>/front-end/writing/writing.do?action=listAllWritingFromShop"	class="btn btn-default btn-lg BacklistBtn">回文章列表</a>

					</div>

					<jsp:useBean id="writingSvc" scope="page"	class="com.writing.model.WritingService" />

				</div>

				<input type="hidden" name="img"	value="${empty writingVO.wrt_photo_base64? '':writingVO.wrt_photo_base64} ">
				<input type="hidden" name="action" value="update">
				 <input	type="hidden" name="wrt_no" value="${writingVO.wrt_no}"> 
				 <input	type="hidden" name="shop_no" value="${writingVO.shop_no}">
		</div>
	</div>
</div>

</FORM>
<div>
	<img id="img"
		src="<%=(writingVO == null) ? "" : "data:image/jpeg;base64," + writingVO.getWrt_photo_base64()%>" />
</div>

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
<%@ include file="/front-end/FooterPage.jsp"%>