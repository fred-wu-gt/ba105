<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="java.util.List"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
<%  int rowsPerPage = 5;  //�C��������    
	if(request.getParameter("rowsPerPage")!=null){
		rowsPerPage = Integer.parseInt(request.getParameter("rowsPerPage"));
	}
	String chooseGroup=request.getParameter("chooseGroup");
	List<ManagerVO> list=(List<ManagerVO>) session.getAttribute("managerVOList");
%>
<%@ include file="/back-end/be_HtmlHeadPage.jsp" %>
<head>
<style>
  #listAllManager_div table,#listAllManager_div th,#listAllManager_div td {
    border: 1px outset #652825;
    padding:2px 5px;
    text-align: center;
    word-break: keep-all;white-space:nowrap;
  }
  #listAllManager_div th{
	border-style:outset;
	background-image: linear-gradient(to top, white,#ecfeff,#d9fafd);
  }
  #listAllManager_div img{
  	width:100px;
  }
	#listAllManager_div .input-group-addon{
		background-image: radial-gradient(ellipse,white,#FFE8E8);
	}
	#outerTable{
    background-image: linear-gradient(to top right, white, white,#FBFFFD);
	font-size:16px;
	width:90%;
  }
  #outerTable img{
  height:60px;
  width:auto;
  }
</style>
<script>
$(function(){
	$("#chooseGroup").change(function(){
		$("#chooseGroupForm").submit();
	});	
	$("#chooseRowsPerPage").change(function(){
		$("#chooseRowsPerPageForm").submit();
	});	
	$("#ggg").addClass("in");
	$("#ggg1").css("background-image","radial-gradient(ellipse,white,white,#5bc0de)");
})


</script>
</head>
<%@ include file="/back-end/be_HeaderPage.jsp" %>
	<div class="row" id="listAllManager_div">
		<div class="col-xs-12">
		    	<div>
					<h3 class="breadcrumbs">�޲z��>�d��/�ק�޲z��><b>�d�ߩҦ��޲z��</b></h3>
				</div>

				<div class="col-xs-12 col-md-4 col-md-offset-3">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/manager/manager.do" style="margin-bottom: 0px;" id="chooseGroupForm">
						<div class="input-group">
							<span class="input-group-addon" >�޲z�����A</span>
							<select name="chooseGroup" id="chooseGroup"  class="form-control">
								<option value="normal" ${param.chooseGroup=='normal'?'selected':''}>���`</option>
								<option value="forbidden" ${param.chooseGroup=='forbidden'?'selected':''}>���v</option>
								<option value="all" ${param.chooseGroup=='all'?'selected':''}>����</option>
							</select>
							<input type="hidden" name="action" value="getManagersByMan_sta">
							<input type="hidden" name="rowsPerPage" value="<%=rowsPerPage%>">
						</div>
					</FORM>
				</div>
				<div class="col-xs-12 col-md-4"">
					<FORM METHOD="post" ACTION="<%=request.getRequestURI()%>" id="chooseRowsPerPageForm">
						<div class="input-group">
							<span class="input-group-addon" >�C������</span>
							<select name="rowsPerPage" id="chooseRowsPerPage"  class="form-control">
								<option value="5" ${param.rowsPerPage=='5'?'selected':''}>5</option>
								<option value="10" ${param.rowsPerPage=='10'?'selected':''}>10</option>
								<option value="15" ${param.rowsPerPage=='15'?'selected':''}>15</option>
								<option value="20" ${param.rowsPerPage=='20'?'selected':''}>20</option>
							</select>
							<input type="hidden" name="chooseGroup" value="<%=chooseGroup%>">
						</div>
					</FORM>
				</div>
<div class="col-xs-12">				
<%@ include file="/back-end/pages/page1.file" %> 
</div>
<table id="outerTable">
	<tr>
		<th>�s��</th>
		<th>�b��</th>
		<th>�m�W</th>
		<th>�ʧO</th>
		<th>���</th>
		<th>�Ӥ�</th>
		<th>���A</th>
		<th>�ק���</th>
		<th>�ԲӸ��</th>
	</tr>

	<c:forEach var="managerVO" items="${managerVOList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${managerVO.man_no}</td>
			<td>${managerVO.man_id}</td>
			<td>${managerVO.man_name}</td>
			<td>${managerVO.man_gen}</td>
			<td>${managerVO.man_tel}</td>
			<td><img src="data:image/jpeg;base64,${managerVO.man_pho_base64}"></td>
			<td>${managerVO.man_sta}</td>		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/manager/manager.do">
			     <button type="submit" class="btn btn-danger"><i class="glyphicon glyphicon-pencil"></i></button> 
			     <input type="hidden" name="man_no"      value="${managerVO.man_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--><!-- �ثe�|���Ψ�  -->
			     <input type="hidden" name="whichPage"	    value="<%=whichPage%>">
			     <input type="hidden" name="action"	    value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/manager/manager.do">
			     <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-zoom-in"></i></button> 
			     <input type="hidden" name="man_no"      value="${managerVO.man_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--><!-- �ثe�|���Ψ�  -->
			     <input type="hidden" name="whichPage"	    value="<%=whichPage%>">
			     <input type="hidden" name="action"	    value="getOne_For_Display">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<br>
<%@ include file="/back-end/pages/page2.file" %>

		</div>
	</div>
<%@ include file="/back-end/be_FooterPage.jsp" %>