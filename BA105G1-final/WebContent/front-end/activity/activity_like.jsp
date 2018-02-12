<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.act_like.model.*"%>

<%	
	ActivityService actSvc = new ActivityService();

	String mem_no = (String)session.getAttribute("mem_no");
	
	@SuppressWarnings("unchecked")
	//查看已按讚活動
	List<ActivityVO> list =(List<ActivityVO>)session.getAttribute("likeList");
	
	if(list == null){
		//new一個裝ActivityVO的list
		list = new ArrayList<ActivityVO>();
		//new service
		Act_likeService likeSvc = new Act_likeService();
		//用service呼叫dao查出這個會員已報名的活動的Act_likeVO(不含活動詳情)
		List<Act_likeVO> likeVOList = likeSvc.findByMemNo(mem_no);
		
		//查出這個會員已報名活動的所有ActivityVO	
		for(Act_likeVO act_likeVO : likeVOList){
			//由act_join_detVO得到act_no
			String act_no = act_likeVO.getAct_no();
			//由act_no去findbypk查出這個會員已報名活動的ActivityVO(單一活動)
			ActivityVO activityVO = actSvc.findByActNo(act_no);
			//把ActivityVO(單一活動)加到裝ActivityVO的list(名字是joinActList)
			list.add(activityVO);
		}
		
		for(ActivityVO activityVO :list){
			System.out.println("活動編號ITERATOR== "+activityVO.getAct_no());
		}
		session.setAttribute("likeList", list);
	}

%>

<%@ include file="/front-end/HtmlHeadPage.jsp" %>
	<head>
		<style type="text/css">
			.empty_row{
				margin-bottom: 60px;
			}
			
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
				font-size: 22px;
			}
			.actlike{
				font-size: 18px;
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
		</style>
	</head>
	<%@ include file="/front-end/HeaderPage.jsp" %>
				<!-- 請在此加內文 -->
				<div class="container-fluid">
					<div class="row">
						<div class="col-xs-12 col-sm-12">
							<div class="row empty_row">
								<div class="col-xs-12 col-sm-2">
								</div>
								<div class="col-xs-12 col-sm-8">
										
								</div>
							</div>
						</div>

						<!-- 左邊漢堡 -->
						<%@ include file="/front-end/activity/activity_left.jsp" %>

						<div class="col-xs-12 col-sm-8">
							<div class="col-xs-12 col-sm-12">
							
							<c:forEach var="activityVO" items="${likeList}" >
								<div class="row act_row">
									<div class="col-xs-12 col-sm-4">
<!-- 動態加入圖片 -->					<img width="100" src="data:image/jpeg;base64,${activityVO.act_pic_base64}">
									</div>
								
									<jsp:useBean id="act_likeSvc" scope="page" class="com.act_like.model.Act_likeService" />
									<div class="col-xs-12 col-sm-8">
										<h2 class="act_title">${activityVO.act_name} 
<!-- 活動標題 --><!-- 活動按讚數 -->			<span class="actlike">按讚數:</span><span class="actlike">${act_likeSvc.getAll(activityVO.act_no)}</span>
										</h2>

										<p>
<!-- 活動時間 -->							活動時間:<fmt:formatDate type = "both" value = "${act_start}" pattern = "yyyy-MM-dd HH:mm" />至 <fmt:formatDate type = "both" value = "${activityVO.act_end}" pattern = "yyyy-MM-dd HH:mm" />
         								</p>
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
							          	<a href="javascript:document.${activityVO.act_no}.submit()">...(閱讀全文)</a>
										</form>
									</div>
								</div>
							</c:forEach>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-sm-offset-3">
						</div>
					</div>
				</div>
			<%@ include file="/front-end/FooterPage.jsp" %> 