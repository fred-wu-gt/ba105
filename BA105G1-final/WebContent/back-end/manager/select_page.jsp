<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/back-end/be_HtmlHeadPage.jsp" %>
<head>

<style>
  #select_page_div .input-group{
 	 margin-bottom:10px;
  }
  	#select_page_div .input-group-addon{
		background-image: radial-gradient(ellipse,white,#FFE8E8);
	}
</style>
<script type="text/javascript">
$(function(){
	$("#ggg").addClass("in");
	$("#ggg1").css("background-image","radial-gradient(ellipse,white,white,#5bc0de)");

})

</script>
</head>
<%@ include file="/back-end/be_HeaderPage.jsp" %>
<jsp:useBean id="managerSvc" scope="page" class="com.manager.model.ManagerService" />

	<div class="row" id="select_page_div">
		<div class="col-xs-12 col-sm-5">
		    	<div>
					<h3 class="breadcrumbs">�޲z��><b>�d��/�ק�޲z��</b></h3>
				</div>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/manager/manager.do" >
					<div class="input-group">
						<span class="input-group-addon" >�޲z���s��</span>
						<select name="man_no" class="form-control" >
							<c:forEach var="managerVO" items="${managerSvc.all}" > 
					    		<option value="${managerVO.man_no}">${managerVO.man_no}</option>
					    	</c:forEach> 
						</select>
						<span class="input-group-btn"><button type="submit" class="btn btn-info"><i class="glyphicon glyphicon-search"></i></button></span>
						<input type="hidden" name="action" value="getOne_For_Display">
					</div>
				</FORM>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/manager/manager.do" >
					<div class="input-group">
						<span class="input-group-addon" >�޲z���m�W</span>
						<select name="man_no" class="form-control" >
							<c:forEach var="managerVO" items="${managerSvc.all}" > 
					    		<option value="${managerVO.man_no}">${managerVO.man_name}</option>
					    	</c:forEach> 
						</select>
						<span class="input-group-btn"><button type="submit" class="btn btn-info"><i class="glyphicon glyphicon-search"></i></button></span>
						<input type="hidden" name="action" value="getOne_For_Display">
					</div>
				</FORM>
				
				
				<div class="input-group">
					<a href='<%=request.getContextPath()%>/back-end/manager/manager.do?action=getManagersByMan_sta'>
						<input id="btn" type="button" value="�d�ߩҦ��޲z��" class="btn btn-info ">
					</a>
				</div>
				
				
<!-- <ul> -->
<%--    <jsp:useBean id="man_funSvc" scope="page" class="com.man_fun.model.Man_funService" /> --%>
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/man_aut/man_aut.do" > --%>
<!--        <b><font color=orange>��ܥ\��:</font></b> -->
<!--        <select size="1" name="mf_no"> -->
<%--          <c:forEach var="man_funVO" items="${man_funSvc.all}" >  --%>
<%--           <option value="${man_funVO.mf_no}">${man_funVO.mf_name} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="�e�X"> -->
<!--        <input type="hidden" name="action" value="listManagers_ByMf_no_A"> -->
<!--      </FORM> -->
<!--   </li> -->
<!-- </ul> -->

<!-- <h3><font color=orange>�޲z���\��P�v���޲z</font></h3> -->

<!-- <ul> -->
<%--   <li><a href='<%=request.getContextPath()%>/back-end/man_aut/listAllMan_aut.jsp'>List</a> all Man_auts. </li> --%>
<!-- </ul> -->


		</div>
	</div>
<%@ include file="/back-end/be_FooterPage.jsp" %>