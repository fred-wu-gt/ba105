<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 左邊漢堡 -->
						<div class="col-xs-12 col-sm-2">
							<div class="panel panel_self">
							  <div class="panel-heading panel_heading_self">
							    <h3 class="panel-title panel_title">一般會員活動管理</h3>
							  </div>
							  <div class="list-group">
								<a href="<%=request.getContextPath()%>/front-end/activity/activity_join.jsp" class="list-group-item item_self">已報名活動</a>
								<a href="<%=request.getContextPath()%>/front-end/activity/activity_like.jsp" class="list-group-item item_self">已按讚活動</a>
							  </div>
							</div>
						</div>