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

<!-- 		<h4 class="">���|���ʯd��</h4> -->
		<div class="form-group form-group-lg">
			<label for="acr_rsn">���|��]</label>
			<select class="form-control" name="acr_rsn" id="acr_rsn">
				<option value="�L�����ʪ��d��">�L�����ʪ��d��</option>
				<option value="�����d��">�����d��</option>
				<option value="�c�N���d��">�c�N���d��</option>
				<option value="�s�i�ʽ誺�d��">�s�i�ʽ誺�d��</option>
			</select>
		</div>
	    <div class="form-group">
			<label for="acr_cnt">���|���e</label>
			<textarea class="form-control" style="resize: none;" name="acr_cnt" id="acr_cnt" maxlength='66' rows="3" cols="100"></textarea>
		</div>
		<div>
		 	<input type="hidden" name="aco_no" value="${param.aco_no}">
	      	<input type="hidden" name="mem_no" value="${sessionScope.mem_no}">
	      	<input type="hidden" name="act_no" value="${param.act_no}">
	      	<input type="hidden" name="action" value="insert_report">
		</div>
		<div class="modal-footer">
			<p align="left">�`�N: �u�Ω����|���n�A�ݵo�s�i�A�M�����D(�ⱡ�A�e���ް_�����A�Ϊ̲ʫU)�����ʡC</p>
		</div>

		<div class="">
			<button type="button" class="btn btn-default" data-dismiss="modal">����</button>
			<button class="btn btn-primary" data-dismiss="modal" onClick="document.act_com_report.submit()">�e�X���|</button>
		</div>

	</form>
</body>
</html>