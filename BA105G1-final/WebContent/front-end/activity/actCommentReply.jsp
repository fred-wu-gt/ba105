<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title></title>
<style>
 
</style>
</head>
<body>

	<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/activity/act_com_reply.do" name="act_com_reply">

	    <div class="form-group form-group-lg">
			<label for="acr_cnt">回覆內容</label>
			<textarea class="form-control" style="resize: none;" name="acr_cnt" id="acr_cnt" maxlength='66' rows="3" cols="100"></textarea>
		</div>
		<div>
			<input type="hidden" name="act_no" value="${param.act_no}">
		 	<input type="hidden" name="aco_no" value="${param.aco_no}">
	      	<input type="hidden" name="shop_no" value="${sessionScope.shop_no}">
	      	<input type="hidden" name="action" value="insert_reply">
		</div>
		
		<div class="">
			<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
			<button class="btn btn-primary" data-dismiss="modal" onClick="document.act_com_reply.submit()">送出回覆</button>
		</div>

	</form>
</body>
</html>