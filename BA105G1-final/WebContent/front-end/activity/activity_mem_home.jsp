<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.regex.*" %>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.act_join_det.model.*"%>

<%
	// 給活動排序用
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = null;
	String str =request.getParameter("act_select");
	//System.out.println("user未選下拉選單 act_select是:"+str);
 	if (str == null){
		list = actSvc.getAll();
	}
	if (str != null && str.equals("getAllRev")) {//user有選下拉選單 並送出查詢
		list = actSvc.getAllRev();
	}
	if(str != null && str.equals("getAll")){//user有選下拉選單 並送出查詢
		list = actSvc.getAll();
	}
	
	@SuppressWarnings("unchecked")
	//關鍵字查詢得到的活動們
	List<ActivityVO> patternList = (List<ActivityVO>)request.getAttribute("patternList");
	//System.out.println("取到的patternList: " + patternList);
	//有輸入 有查到資料 或 沒有查到資料是空字串
	if(patternList != null){
		list = patternList;
		request.removeAttribute("patternList");
		//System.out.println("inside if(patternList != null)");
	}
	//給include用的list物件
	pageContext.setAttribute("list", list);
%>

<%@ include file="/front-end/HtmlHeadPage.jsp" %>
	<head>
		<style type="text/css">
			.pagination>li>a{
				font: 20px bold;
				color: #fe8c0a;
			}
			.act_row{
				margin-bottom: 20px;
			}
			.act_title{
				margin-top: 0px;
			}
			.act_table{
				margin-left: 14px;     
				margin-bottom: 20px;
				margin-top: 20px;
			}
			.act_td{
				font-size:20px;
				font-weight: 600;
			}
			.act_select{
				font-size:20px;
				font-weight: 600;
				margin-bottom: 0px;
			} 
			.panel_self{
				border-color: #d8f09b;
			}
			.panel_title{
				font-weight: bold;
				font: bold 22px "微軟正黑體";
			}
			.actlike{
				font: bold 18px "微軟正黑體";
			}
			.panel_heading_self{
			    background-color: #d8f09b;
			}
			.item_self{
				font-size: 18px;
				font-weight: 700;
			}
			.addbottom{
				margin-bottom: 20px;
			}
			.act_ahref{
				text-decoration: none;
				margin-left: 10px;
			}
		</style>
		
	</head>
	<%@ include file="/front-end/HeaderPage.jsp" %>
	<c:set var="mem_no" scope="session" value="${loginMemberVO.mem_no}" />
				<!-- 請在此加內文 -->
				<div class="container-fluid">
					<div class="row">
						<div class="col-xs-12 col-sm-12">
							<div class="row">
								<div class="col-xs-12 col-sm-2">
								</div>
								<div class="col-xs-12 col-sm-8">
									<table class="act_table">
										<tr>
<!-- 下拉選單依時間排序活動-->					<td class="act_td">活動依時間排序</td>
											<td>
												<form name="" method="post" action="<%=request.getContextPath()%>/front-end/activity/activity_mem_home.jsp">
											
												<select class="act_select" name="act_select">
									  				<option value="getAll" selected>新到舊</option>
									  				<option value="getAllRev">舊到新</option>
												</select>
												
												<input class="btn btn-default btn-md" type="submit" value="查詢">
												</form>
											</td>
											
											<td style="padding-left: 70px">
<!-- 搜尋表單 -->									<form name="formPattern" method="post" action="<%=request.getContextPath()%>/front-end/activity/activity.do">
													<div class="input-group searchbar">
														<input type="text" name="pattern" class="form-control" placeholder="請輸入關鍵字" style="z-index:0;">
														<input type="hidden" name="action" value="pattern">
														<span class="input-group-btn">
															<button class="btn btn-success" style="z-index:0; border:0px; height: 32px;" type="button" onClick="document.formPattern.submit()"><i class="glyphicon glyphicon-search"></i></button>
														</span>
													</div>
												</form>
											</td>
										</tr>
									</table>	
								</div>
							</div>
						</div>

						<!-- 左邊漢堡 -->
						<%@ include file="/front-end/activity/activity_left.jsp" %>

						<div class="col-xs-12 col-sm-8">
							<div class="col-xs-12 col-sm-12">
							<%@ include file="page1.file" %> 
							<c:forEach var="activityVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<div class="row act_row">
									<div class="col-xs-12 col-sm-4">
<!-- 動態加入圖片 -->					<img width="100" src="data:image/jpeg;base64,${activityVO.act_pic_base64}">
									</div>
								
									<jsp:useBean id="act_likeSvc" scope="page" class="com.act_like.model.Act_likeService" />
									<div class="col-xs-12 col-sm-8">
										<h2 class="act_title">${activityVO.act_name} 
<!-- 活動標題 --><!-- 活動按讚數 -->			<span class="actlike">按讚數:</span><span class="actlike">${act_likeSvc.getAll(activityVO.act_no)}</span>
										</h2>

										<p class="addbottom">
										<c:set var="act_start" value="${activityVO.act_start}" />
										<c:set var="act_end" value="${activityVO.act_end}" />
<!-- 活動時間 -->							活動時間:<fmt:formatDate type = "both" value = "${act_start}" pattern = "yyyy-MM-dd HH:mm" /> 至 <fmt:formatDate type = "both" value = "${act_end}" pattern = "MM-dd H:mm" />
         								</p>
										<c:set var="act_art" value="${activityVO.act_art}" />
										<% String act_art2 = String.valueOf(pageContext.getAttribute("act_art")); 
										   int length = act_art2.length();
										   System.out.println(length);
										   String part = "";
										   if(length >= 60){
											   if(act_art2.charAt(59) == '<'){
												   part = act_art2.substring(0, 59);
											   }else if(act_art2.charAt(58) == '<'){
												   part = act_art2.substring(0, 58);   
											   }else if(act_art2.charAt(57) == '<'){
												   part = act_art2.substring(0, 57);
											   }else{
												   part = act_art2.substring(0, 59);
											   } 
										   }else{
											   part = act_art2;
										   } 
										%>
<!-- 篩選活動部分內文 -->					<%= part %>
										<form name="${activityVO.act_no}"  method="post"  action="<%=request.getContextPath()%>/front-end/activity/activity.do" style="display:inline;">  
										<input type="hidden" name="action" value="getOneAct_For_Display">
										<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
					                 	<input type="hidden" name="act_no" value="${activityVO.act_no}">
							          	<a href="javascript:document.${activityVO.act_no}.submit()">...(查看活動詳情)</a>
										</form>
									</div>
								</div>
							</c:forEach>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-sm-offset-3">
						<%@ include file="page2.file" %>
						</div>
						</div>
					</div>
				
			
<%@ include file="/front-end/FooterPage.jsp" %>