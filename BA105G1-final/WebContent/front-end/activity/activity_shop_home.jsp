<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.act_join_det.model.*"%>

<%
	ShopVO shopVO = (ShopVO)session.getAttribute("loginShopVO");
	String shop_no = shopVO.getShop_no();
	List<ActivityVO> list = (List<ActivityVO>)session.getAttribute("shopActlist");
	
	ActivityService actSvc = new ActivityService();
	if(list == null){
		list = actSvc.findByShopNo(shop_no);
		session.setAttribute("shopActlist", list);
		System.out.println(list+"------------block y");
	}
	
	String str =request.getParameter("act_select");
	
	if(str == null){
		System.out.println(list+"------------block z");
		list =  (List<ActivityVO>)session.getAttribute("shopActlist");
		session.setAttribute("shopActlist", list);
	}
	else if ((str != null && str.equals("getAll"))){
		list =  actSvc.findByShopNo(shop_no);//user沒選下拉:依新到舊//user有選下拉選單:依新到舊
		session.setAttribute("shopActlist", list);
	 	System.out.println(list+"------------block A");
	} else if (str != null && str.equals("getAllRev")) {
		System.out.println(list+"------------block B");
		Collections.sort(list, new Comparator<ActivityVO>(){
			@Override
			public int compare(ActivityVO o1, ActivityVO o2) {
				int result;
				result =o1.getAct_start().compareTo(o2.getAct_start());//user有選下拉選單:舊到新
		        return result;
			}
		});
		session.setAttribute("shopActlist", list);
	}
	System.out.println("list.size()= "+list.size()+"------------block C");
	

	@SuppressWarnings("unchecked")
	//關鍵字查詢得到的活動們
	List<ActivityVO> patternList = (List<ActivityVO>)request.getAttribute("patternList");
	//有輸入 有查到資料 或 沒有查到資料是空字串
	if(patternList != null){
		list = patternList;
		request.removeAttribute("patternList");
	}

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
				padding: 0px;
				width: 100%;
				border: solid 5px;
				border-color: #d2e9e6;
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
				font-size: 22px;
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
			.act_ahref{
				text-decoration: none;
				margin-left: 10px;
			}
			#managerdiv{
				margin-bottom: 30px;
			}
			.managerform{
			    margin-left: 20px;
    			display: inline;
			}
			.managerBtn:hover{
				background-color: #cff6d8;
			}
		</style>
		
	</head>
	<%@ include file="/front-end/HeaderPage.jsp" %>
	<c:set var="shop_no" scope="session" value="${loginShopVO.shop_no}" />
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
												<form name="" method="post" action="<%=request.getContextPath()%>/front-end/activity/activity_shop_home.jsp">
												<select class="act_select" name="act_select">
									  				<option value="getAll" selected>新到舊</option>
									  				<option value="getAllRev">舊到新</option>
												</select>
												<input class="btn btn-default btn-md" type="submit" value="查詢">
												</form>
											</td>
											
											
										</tr>
									</table>	
								</div>
							</div>
						</div>
<!-- 商家左邊漢堡 -->
						<%@ include file="/front-end/activity/activity_left_shop.jsp" %> 
						<div class="col-xs-12 col-sm-8">
							<div class="col-xs-12 col-sm-12">
							<%@ include file="page1.file" %> 
							<c:forEach var="activityVO" items="${shopActlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<div class="row act_row">
									<div class="col-xs-12 col-sm-4">
<!-- 動態加入圖片 -->					<img width="100" src="data:image/jpeg;base64,${activityVO.act_pic_base64}">
									</div>
								
									<jsp:useBean id="act_likeSvc" scope="page" class="com.act_like.model.Act_likeService" />
									<div class="col-xs-12 col-sm-8">
										<h2 class="act_title">${activityVO.act_name} 
<!-- 活動標題 --><!-- 活動按讚數 -->			<span class="actlike">按讚數:</span><span class="actlike">${act_likeSvc.getAll(activityVO.act_no)}</span>
										</h2>

										<div id="managerdiv">
										<c:set var="act_start" value="${activityVO.act_start}" />
										<c:set var="act_end" value="${activityVO.act_end}" />
<!-- 活動時間 -->							活動時間:<fmt:formatDate type = "both" value = "${act_start}" pattern = "yyyy-MM-dd HH:mm" /> 至 <fmt:formatDate type = "both" value = "${act_end}" pattern = "MM-dd H:mm" />
         								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/activity/activity.do" class="managerform">
									     <input type="submit" class="btn btn-default btn-md managerBtn" value="修改活動">
									     <input type="hidden" name="act_no"  value="${activityVO.act_no}">
										 <input type="hidden" name="img" value="${empty activityVO.act_pic_base64 ? '':activityVO.act_pic_base64}">
									     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
									     <input type="hidden" name="action"	value="getOne_For_Update">
									  	</FORM>
									  	<form name="listjoin"  method="post"  action="<%=request.getContextPath()%>/front-end/activity/act_join_det.do" style="display:inline;">  
										<input type="hidden" name="action" value="getAllJoin_For_Display">
					                 	<input type="hidden" name="act_no" value="${activityVO.act_no}">
					                 	<button class="btn btn-default btn-md managerBtn">
	                 					查詢報名名單
	                 					</button>
										</form>
         								</div>

									  	
										<c:set var="act_art" value="${activityVO.act_art}" />
										<% String act_art2 = String.valueOf(pageContext.getAttribute("act_art")); 
										   int length = act_art2.length();
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