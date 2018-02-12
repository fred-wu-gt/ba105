<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.commodity.model.CommodityVO"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
<jsp:useBean id="fav_shopSvc" scope="page" class="com.fav_shop.model.Fav_shopService" />
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<body>
		<div id="wrapper">
			<nav class="navbar navbar-default" id="home_navbar" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="data-toggle" data-target=".navbar-ex1-collapse">
							<span class="sr-only">選單切換</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<img src="<%=request.getContextPath()%>/res/image/home/logo_transparent.png" class="logo">
					</div>
					
					
					<div class="leftIn"><!-- 手機隱藏選單開始 -->
					
							
						<ul class="list-unstyled">
							<li><!-- 左選單開始 -->	
								<a href="<%=request.getContextPath()%>/front-end/index.jsp">
									<div class="navbar_left">
										<img src="<%=request.getContextPath()%>/res/image/home/home.png" class="icon_left">
										<h4><b>首頁</b></h4>
									</div>
								</a>
							</li>					
							<li>
								<a href="<%=request.getContextPath()%>/front-end/shopping/listAllCommodityBS.jsp">
									<div class="navbar_left">
										<img src="<%=request.getContextPath()%>/res/image/home/shopping.png" class="icon_left">
										<h4><b>購物商城</b></h4>
									</div>
								</a>
							</li>					
							<li>
								<a id="writingA" href="<%=request.getContextPath()%>/front-end/writing/${(not empty loginMemberVO || not empty loginManagerVO) && empty loginShopVO ?'Writing_Home_Page.jsp':''}${empty loginShopVO?'':'writing.do?action=listAllWritingFromShop'}">
									<div class="navbar_left">
										<img src="<%=request.getContextPath()%>/res/image/home/writing.png" class="icon_left">
										<h4><b>文章專區</b></h4>
									</div>
								</a>
							</li>				
							<li>
								<a id="activityA" href="<%=request.getContextPath()%>/front-end/activity/${(not empty loginMemberVO || not empty loginManagerVO) && empty loginShopVO ?'activity_mem_home.jsp':''}${empty loginShopVO?'':'activity_shop_home.jsp'}">
									<div class="navbar_left">
										<img src="<%=request.getContextPath()%>/res/image/home/activity.png" class="icon_left">
										<h4><b>活動專區</b></h4>
									</div>
								</a>
							</li>				
							<li>
								<a id="fav_shopA" href="<%=request.getContextPath()%>/front-end/fav_shop/Fav_shop.jsp">
									<div class="navbar_left">
										<img src="<%=request.getContextPath()%>/res/image/home/favorite.png" class="icon_left">
										<h4><b>收藏商家區</b></h4>
									</div>
								</a>
							</li>					
							<li>
								<a href="<%=request.getContextPath()%>/front-end/fruit/fruitMap.jsp">
									<div class="navbar_left">
										<img src="<%=request.getContextPath()%>/res/image/home/fruitMap.png" class="icon_left">
										<h4><b>蔬果地圖</b></h4>
									</div>
								</a>
							</li>	
							<li>
								<a href="<%=request.getContextPath()%>/front-end/fru_pri/searchfruprice.jsp">
									<div class="navbar_left">
										<img src="<%=request.getContextPath()%>/res/image/home/knowledge.png" class="icon_left">
										<h4><b>蔬果時價</b></h4>
									</div>
								</a>
							</li>						
							<li>
								<a id="centerA" href="<%=request.getContextPath()%>/front-end/${empty loginMemberVO?'':'member/loginsuss.jsp'}${empty loginShopVO?'':'shop/shop_default.jsp'}">
									<div class="navbar_left">
										<img src="<%=request.getContextPath()%>/res/image/home/member.png" class="icon_left">
										<h4><b>會員中心</b></h4>
									</div>
								</a>
							</li><!-- 左選單結束 -->	
							<li><!-- 右選單開始 -->
								<table class="navbar_right_container">	
									<tr>
										<td>
											<div>
												<a id="storeA" href="<%=request.getContextPath()%>/front-end/point/top_up.jsp">
													<div class="navbar_right">
														<img src="<%=request.getContextPath()%>/res/image/home/value.png" class="icon_right">
														<h5><b>儲值</b></h5>
													</div>
												</a>
												<c:if test="${empty loginMemberVO && empty loginShopVO}">
												<a id="registerA" href="<%=request.getContextPath()%>/front-end/register/addMember2.jsp">
													<div class="navbar_right">
														<img src="<%=request.getContextPath()%>/res/image/home/register.png" class="icon_right">
														<h5><b>註冊</b></h5>
													</div>
												</a>
												</c:if>
												
												<script type="text/javascript">
												$(function(){
													$("#registerA").click(function(){
														event.preventDefault();
														swal("請問您要註冊成「買家」或「商家」?","", {
															  buttons: {
															    member: {
															      text: "買家",
															      value: "member",
															    },
															    shop:{
															      text: "商家",
															      value: "shop",
															    },
																 cancel: "取消",
															  },
															})
															.then((value) => {
															  switch (value) {
															 
															    case "member":
															      location.href="<%=request.getContextPath()%>/front-end/register/addMember2.jsp";
															      break;
															 
															    case "shop":
															      location.href="<%=request.getContextPath()%>/front-end/shop/shopRegister.jsp";
															      break;
															 
															  }
															});
													});
												});
												</script>
												
												
												
												
												<c:if test="${empty loginMemberVO && empty loginShopVO && empty loginManagerVO}">
													<input type="hidden" id="role" value="">
													<input type="hidden" id="userName" value="">
													<input type="hidden" id="userNo" value="">
												</c:if>
												<c:if test="${not empty loginMemberVO}">
													<input type="hidden" id="role" value="member">
													<input type="hidden" id="userName" value="${loginMemberVO.mem_name}">
													<input type="hidden" id="userNo" value="${loginMemberVO.mem_no}">
												</c:if>
												<c:if test="${not empty loginShopVO}">
													<input type="hidden" id="role" value="shop">
													<input type="hidden" id="userName" value="${loginShopVO.shop_name}">
													<input type="hidden" id="userNo" value="${loginShopVO.shop_no}">
												</c:if>
												<c:if test="${empty loginMemberVO && empty loginShopVO && not empty loginManagerVO}">
													<input type="hidden" id="role" value="manager">
													<input type="hidden" id="userName" value="${loginManagerVO.man_name}">
													<input type="hidden" id="userNo" value="${loginManagerVO.man_no}">
												</c:if>
												<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">	
												<a href="${(empty loginMemberVO && empty loginShopVO)?'#loginModal':''}" data-toggle="modal" id="loginAndoutA">
													<div class="navbar_right">
														<c:if test="${empty loginMemberVO && empty loginShopVO}">											
															<img src="<%=request.getContextPath()%>/res/image/home/login.png" class="icon_right" class="loginAndout">
															<h5 class="loginAndoutH5"><b>登入</b></h5>
														</c:if>
														<c:if test="${(not empty loginMemberVO) || (not empty loginShopVO)}">
															<img src="<%=request.getContextPath()%>/res/image/home/logout.png" class="icon_right" class="loginAndout">
															<h5 class="loginAndoutH5"><b>登出</b></h5>				
														</c:if>
													</div>
												</a>
				
<div  class="modal fade" id="loginModal">
	<div class="modal-dialog">
		<div class="modal-content ">
			<div role="tabpanel">
			    <!-- 標籤面板：標籤區 -->
			    <ul class="nav nav-tabs" role="tablist">
			        <li role="presentation" class="active">
			            <a href="#m_tab1" aria-controls="m_tab1" role="tab" data-toggle="tab">一般會員登入</a>
			        </li>
			        <li role="presentation">
			            <a href="#m_tab2" aria-controls="m_tab2" role="tab" data-toggle="tab">商家登入</a>
			        </li>
			    </ul>
			
			    <!-- 標籤面板：內容區 -->
			    <div class="tab-content">
			        <div role="tabpanel" class="tab-pane active " id="m_tab1"><!-- 一般會員區塊開始  -->
						<FORM id="memberLoginForm">
							<div class="modal-header ">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h3 class="modal-title"><b>一般會員登入</b></h3>
							</div>
							<div class="modal-body modal-dialog">
								<div class="input-group">
									<span class="input-group-addon textAddon">帳號</span>
									<input name="mem_id" type="text" class="form-control" maxlength="30" id="loginMem_id">
								</div>
								<div class="input-group">
									<span class="input-group-addon textAddon">密碼</span>
									<input name="mem_psw" type="password" class="form-control" maxlength="30" id="loginMem_psw">
								</div>
							</div>
							<div class="modal-footer">
								<div id="memberAlertMsg" style="color:red;text-align:center;height:20px;margin-bottom:30px;"></div>
								<div class="input-group" style="width:100%">
									<button type="submit" class="btn btn-success form-control" style="width:50%;">登入</button>
									<input type="hidden" name="action" value="memberLogin">
									<button type="button" class="btn btn-default form-control" data-dismiss="modal" style="width:50%">取消</button>
								</div>
							</div>
						</FORM>
					</div><!-- 一般會員區塊結束  -->
			        <div role="tabpanel" class="tab-pane" id="m_tab2"><!-- 商家區塊開始  -->
						<FORM id="shopLoginForm">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h3 class="modal-title"><b>商家登入</b></h3>
							</div>
							<div class="modal-body modal-dialog">
								<div class="input-group">
									<span class="input-group-addon textAddon">帳號</span>
									<input name="shop_id" type="text" class="form-control" maxlength="30" id="loginShop_id">
								</div>
								<div class="input-group">
									<span class="input-group-addon textAddon">密碼</span>
									<input name="shop_psw" type="password" class="form-control"  maxlength="30" id="loginShop_psw">
								</div>
							</div>
							<div class="modal-footer">
								<div id="shopAlertMsg" style="color:red;text-align:center;height:20px;margin-bottom:30px;"></div>
								<div class="input-group" style="width:100%">
									<button type="submit" class="btn btn-success form-control" style="width:50%;">登入</button>
									<input type="hidden" name="action" value="shopLogin">
									<button type="button" class="btn btn-default form-control" data-dismiss="modal" style="width:50%">取消</button>
								</div>
							</div>
						</FORM>
					</div><!-- 商家區塊開始結束  -->
			    </div>
			</div><!-- 標籤面板：標籤區結束 -->	
		</div>
	</div>
</div>
							
												
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<h4 id="sayHi">
												<c:if test="${not empty loginMemberVO}">
													Hi! <b>${loginMemberVO.mem_name}</b><br>今天吃水果了嗎?
												</c:if>
												<c:if test="${not empty loginShopVO}">
													Hi! <b>${loginShopVO.shop_name}</b><br>今天吃水果了嗎?
												</c:if>
												<c:if test="${empty loginMemberVO && empty loginShopVO && not empty loginManagerVO}">
													Hi! 果書後台管理員<br><b>${loginManagerVO.man_name}</b>
												</c:if>
											</h4>
										</td>
									</tr>
								</table>
							</li><!-- 右選單結束 -->	
						</ul>				
					</div><!-- 手機隱藏選單區結束 -->
					
				</div>
			</nav>
			
			
			<div id="fastbtn"><!-- 浮動區快鍵開始 -->
				<div id="openChatRoomBtn">
					<img src="<%=request.getContextPath()%>/res/image/home/chat.png" class="fastbtn_icon"><span class="badge" id="totalUnreadCount"></span>
				</div>
				
				<%
 					int count = 0;
 					Map<String, Map> mapH1 = new LinkedHashMap<String, Map>();
 					Map<String, CommodityVO> mapH2 = new LinkedHashMap<String, CommodityVO>();
 					mapH1 = (Map<String, Map>) session.getAttribute("shoppingcart");
 					System.out.println("HeaderPage的mapH1 = "+mapH1);
 					
 				
 					if (mapH1 != null) {
 						System.out.println("HeaderPage的mapH1.size() =" + mapH1.size());
 						Set key1 = mapH1.keySet();
 						Iterator<String> it1h = key1.iterator();
 						while (it1h.hasNext()) {
 							String shop_noH = it1h.next();
 							mapH2 = mapH1.get(shop_noH);
 							Set key2 = mapH2.keySet();
 							Iterator<String> it2h = key2.iterator();
 							while (it2h.hasNext()) {
 								String com_noH = it2h.next();
 								mapH2.get(com_noH);
 								count++;
 							}

 						}
 						System.out.println("HeaderPage的count =" + count);
 						session.setAttribute("count", count);

 					}else{
 						count = 0 ;
 					}
 				%> 
				<div id="openShoppingcartBtn">
					<a href="<%=request.getContextPath()%>/front-end/shopping/Cart.jsp">
                		<img src="<%=request.getContextPath()%>/res/image/home/shoppingcart.png" class="fastbtn_icon">
                		 <c:if test="${count gt 0  }">
                		 	<span class="badge"  style="background-color: #2489ce;"  > ${count }</span>
                		 </c:if>
                    </a>
				</div>
		
				<div id="fastbtn_div_top">
					<img src="<%=request.getContextPath()%>/res/image/home/top.png" class="fastbtn_icon">
				</div>
			</div><!-- 浮動區快鍵結束 -->
			
			<div id="main-content"><!-- 內文開始 -->
			
			<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-2 aa" align="left" >
					<div class="panel panel-info" style="width:100%">
					  <div class="panel-heading">
					    <h3 class="panel-title" align="center">會員資料</h3>
					  </div>
					  <div class="list-group" align="center">
					   <a href="<%=request.getContextPath()%>/front-end/member/listOneMember2.jsp"><b>查詢會員資料</b></a>
					   <br><a href="<%=request.getContextPath()%>/front-end/fav_com/listOneFav_com2.jsp"><b>收藏商品管理</b></a>
<%-- 					   <br><a href="<%=request.getContextPath()%>/front-end/fav_shop/listOneFav_shop2.jsp"><b>收藏商家管理</b></a> --%>
					   <br> <a href="<%=request.getContextPath()%>/front-end/ord_mas/listAllByMemNo.jsp"><b>瀏覽訂單</b></a>
					   
					  </div> 
					
					</div>
				
					


				</div>
				
			</div>
		</div>
			
			

<%@ include file="/front-end/chat.jsp" %>
			
			
		
		