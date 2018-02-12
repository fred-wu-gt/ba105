<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title></title>
<style>
 
</style>
</head>
<body>

	<form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/activity/act_com_report.do" name="act_com_report">

<!-- 		<h4 class="">檢舉活動留言</h4> -->
		<div class="form-group form-group-lg">
			<label for="acr_rsn">檢舉原因</label>
			<select class="form-control" name="acr_rsn" id="acr_rsn">
				<option value="無關活動的留言">無關活動的留言</option>
				<option value="不當的留言">不當的留言</option>
				<option value="惡意的留言">惡意的留言</option>
				<option value="廣告性質的留言">廣告性質的留言</option>
			</select>
		</div>
	    <div class="form-group">
			<label for="acr_cnt">檢舉內容</label>
			<textarea class="form-control" style="resize: none;" name="acr_cnt" id="acr_cnt" maxlength='66' rows="3" cols="100"></textarea>
		</div>
		<div>
		 	<input type="hidden" name="aco_no" value="${param.aco_no}">
	      	<input type="hidden" name="mem_no" value="${sessionScope.mem_no}">
	      	<input type="hidden" name="act_no" value="${param.act_no}">
	      	<input type="hidden" name="action" value="insert_report">
		</div>
		<div class="modal-footer">
			<p align="left">注意: 只用於檢舉爭吵，濫發廣告，和有問題(色情，容易引起爭鬥，或者粗俗)的活動。</p>
		</div>

		<div class="">
			<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
			<button class="btn btn-primary" data-dismiss="modal" onClick="document.act_com_report.submit()">送出檢舉</button>
		</div>

	</form>
</body>
</html>