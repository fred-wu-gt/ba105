<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
<jsp:useBean id="fav_shopSvc" scope="page" class="com.fav_shop.model.Fav_shopService" />
	<body>
		<div class="container-fluid" id="header">
			<div class="row">
				<div class="col-xs-9">
						<h3><b>果書 後台管理系統</b></h3>
				</div>
				<div class="col-xs-3">
					<a href="<%=request.getContextPath()%>/login/LoginHandler?action=managerLogout">
						<div align="right">
							<img src="<%=request.getContextPath()%>/res/image/home/logout.png">
							<h5><b>登出</b></h5>
						</div>
					</a>
				</div>
				
			</div>
		</div>

		<div id="fastbtn"><!-- 浮動區快鍵開始 -->
			<div id="openChatRoomBtn">
				<img src="<%=request.getContextPath()%>/res/image/home/chat.png" class="fastbtn_icon"><span class="badge" id="totalUnreadCount"></span>
			</div>
			<div id="fastbtn_div_top">
				<img src="<%=request.getContextPath()%>/res/image/home/top.png" class="fastbtn_icon">
			</div>
		</div><!-- 浮動區快鍵結束 -->
		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-2" id="be_navbar">
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					  <!-- 區塊0 -->
					  <div class="panel">
					    <div class="panel-heading" role="tab" id="panel0">
					    	<img src="data:image/jpeg;base64,${loginManagerVO.man_pho_base64}">
					      	<h4 class="panel-title" id="sayHi">
					         嗨，${loginManagerVO.man_name}
					      	</h4>
					    </div>
					  </div>
<!-- 					  區塊1 -->
<!-- 					  <div class="panel panel-info"> -->
<!-- 					    <div class="panel-heading" role="tab" id="panel1"> -->
<!-- 					      <h4 class="panel-title"> -->
<%-- 					        <a href="<%=request.getContextPath()%>/back-end/be_index.jsp"> --%>
<!-- 					          首頁 -->
<!-- 					        </a> -->
<!-- 					      </h4> -->
<!-- 					    </div> -->
<!-- 					  </div> -->
					  <!-- 區塊2 -->
					  <div class="panel panel-info">
					    <div class="panel-heading" role="tab" id="panel2">
					      <h4 class="panel-title">
					        <a href="<%=request.getContextPath()%>/front-end/ad/ad.do?action=getAdsByAd_stat" >
					          廣告牆
					        </a>
					      </h4>
					    </div>
<!-- 					    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2"> -->
<!-- 					      <div class="panel-body"> -->
<!-- 					        <a href="">商家廣告</a> -->
<!-- 					      </div> -->
<!-- 					      <div class="panel-body"> -->
<!-- 					        <a href="">官方廣告</a> -->
<!-- 					      </div> -->
<!-- 					    </div> -->
					  </div>
					  <!-- 區塊3 -->
					  <div class="panel panel-info">
					    <div class="panel-heading" role="tab" id="panel3">
					      <h4 class="panel-title">
					        <a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
					          檢舉
					        </a>
					      </h4>
					    </div>
					    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
					      <div id="ccc1" class="panel-body">
					        <a href="">商品檢舉</a>
					      </div>
					      <div id="ccc2" class="panel-body">
					        <a href="<%=request.getContextPath()%>/front-end/writing_rep/writing_rep.do?action=getWri_repsByWre_stat">文章檢舉</a>
					      </div>
					      <div id="ccc3" class="panel-body">
					        <a href="<%=request.getContextPath()%>/front-end/activity/act_rep.do?action=getAct_repsByAct_status">活動檢舉</a>
					      </div>
					      <div id="ccc4" class="panel-body">
					        <a href="">文章留言檢舉</a>
					      </div>
					      <div id="ccc5" class="panel-body">
					        <a href="">活動留言檢舉</a>
					      </div>
					    </div>
					  </div>
					  <!-- 區塊4 -->
<!-- 					  <div class="panel panel-info"> -->
<!-- 					    <div class="panel-heading" role="tab" id="panel4"> -->
<!-- 					      <h4 class="panel-title"> -->
<!-- 					        <a href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ddd"> -->
<!-- 					          兌換商品 -->
<!-- 					        </a> -->
<!-- 					      </h4> -->
<!-- 					    </div> -->
<!-- 					    <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel4"> -->
<!-- 					      <div class="panel-body"> -->
<!-- 					        <a href="">兌換商品一覽表</a> -->
<!-- 					      </div> -->
<!-- 					      <div class="panel-body"> -->
<!-- 					        <a href="">兌換商品上/下架</a> -->
<!-- 					      </div> -->
<!-- 					    </div> -->
<!-- 					  </div> -->
					  <!-- 區塊5 -->
<!-- 					  <div class="panel panel-info"> -->
<!-- 					    <div class="panel-heading" role="tab" id="panel5"> -->
<!-- 					      <h4 class="panel-title"> -->
<!-- 					        <a href="#eee" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="eee"> -->
<!-- 					          認識蔬果 -->
<!-- 					        </a> -->
<!-- 					      </h4> -->
<!-- 					    </div> -->
<!-- 					    <div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel5"> -->
<!-- 					      <div class="panel-body"> -->
<!-- 					        <a href="">蔬果知識</a> -->
<!-- 					      </div> -->
<!-- 					      <div class="panel-body"> -->
<!-- 					        <a href="">蔬果新聞</a> -->
<!-- 					      </div> -->
<!-- 					    </div> -->
<!-- 					  </div> -->
					  <!-- 區塊6 -->
					  <div class="panel panel-info">
					    <div class="panel-heading" role="tab" id="panel6">
					      <h4 class="panel-title">
					        <a href="#fff" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="fff">
					          客戶權限
					        </a>
					      </h4>
					    </div>
					    <div id="fff" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel6">
					      <div id="fff1" class="panel-body">
					        <a href="<%=request.getContextPath()%>/front-end/shop/shop.do?action=getShopsByShop_stat">商家權限</a>
					      </div>
					      <div id="fff2" class="panel-body">
					        <a href="">一般會員權限</a>
					      </div>
					    </div>
					  </div>
					  <!-- 區塊7 -->
					  <div class="panel panel-info">
					    <div class="panel-heading" role="tab" id="panel7">
					      <h4 class="panel-title">
					        <a href="#ggg" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ggg">
					          管理員
					        </a>
					      </h4>
					    </div>
					    <div id="ggg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel7">
					      <div id="ggg1" class="panel-body">
					        <a href="<%=request.getContextPath()%>/back-end/manager/select_page.jsp">查詢/修改管理員</a>
					      </div>
					      <div id="ggg2" class="panel-body">
					        <a href="<%=request.getContextPath()%>/back-end/manager/addManager.jsp">新增管理員</a>
					      </div>
<!-- 					      <div id="ggg3" class="panel-body"> -->
<!-- 					        <a href="">查詢管理員功能</a> -->
<!-- 					      </div> -->
					    </div>
					  </div>
					</div>
				</div>

				
					<input type="hidden" id="role" value="manager">
					<input type="hidden" id="userName" value="${loginManagerVO.man_name}">
					<input type="hidden" id="userNo" value="${loginManagerVO.man_no}">
				
				<!-- 內文開始 -->
				<div class="col-xs-12 col-sm-10">
				
				<%@ include file="/front-end/chat.jsp" %>