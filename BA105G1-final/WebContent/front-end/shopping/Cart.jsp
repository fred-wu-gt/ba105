<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.* ,com.commodity.model.*,com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.sql.*"%>
<%@ page import="com.prom.model.*"%>


<jsp:useBean id="promSvc" class="com.prom.model.PromService"/>

<%@ include file="/front-end/HtmlHeadPage.jsp"%><%--~~必須include1/3~~ --%>
<head>
<style type="text/css">
#tagShop_no{
background-color:red;

}
.com_img{
width:100PX; 
hight:100PX;
}
.container .nav-tabs>li.active>a,.container  .nav-tabs>li.active>a:focus,.container  .nav-tabs>li.active>a:hover{
background-color:#E8F6C5;
}

</style>



<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/shoppingCart.css"> --%>
<script>

$(function(){
	//一般會員 商家能進入的區域
	$(".checkoutbtn").click(function(){
		if($("#role").val()=='' || $("#role").val()=='manager'){
			sweetAlert("請先登入","");
			event.preventDefault();
		}
	});
// 	$('li').click(function(){
// 		$(this).css({"background-color":"#D8F09A"});
// 	});


		
});
	
	
// 	$(function(){
// 		$(".first").addClass("active");
// 		console.log($("li:first-child"));
// // // 		$(".tab-pane:first").addClass("active");
// // 		console.log($(".tab-pane:first"));
// // 		console.log($(".checkoutbtn"));
// 	})
	
	/*
		因為購買數量無法送到controller，所以用Ajax送
		step1. 將input type= submit 改成 "button"，並註冊 onclick事件 "ggg(this)"
		step2. 找到點擊按鈕後，該商家有幾筆資料
		step3. 把那幾筆資料裝成json
		step4. 把多筆json裝成jsonArray
		step5. 把jsonArray傳到controller
	
	*/
// 	function ggg(thebtn) {
// 			var list = [];  
// 			console.log("EEEEE")
// 			//從thebtn開始傳回所有名為form的元素， 取到from後傳回前一個兄弟元素<table>
// 			var table = $(thebtn).parents('form').prev();
// 			//從傳回所有名為"com_no"的子孫元素，並計算數量。以便跑迴圈
// 			for(var i = 0 ; i<$(table).find(".com_no").length ; i++){
// 				//印出第i個".com_no"的值....以此類推
// 				console.log($(table).find(".com_no").eq(i).text());
// 				console.log($(table).find(".shop_no").eq(i).text());
// 				console.log($(table).find(".com_name").eq(i).text());
// 				console.log($(table).find(".com_price").eq(i).text());
// 				console.log($(table).find(".com_quan").eq(i).val());
// 				console.log("EETTTTTTTTTTTT")
				
// 				//JSON物件
// 				var obj = 				
// 					//將取到的資料寫成　json格式  命名為obj
// 				{
// 					"com_no":$(table).find(".com_no").eq(i).text(),
// 					"shop_no":$(table).find(".shop_no").eq(i).text(),
// 					"com_name":$(table).find(".com_name").eq(i).text(),
// 					"com_price":$(table).find(".com_price").eq(i).text(),
// 					"com_quan":$(table).find(".com_quan").eq(i).val()
// 				};
				
// 				//將obj裝進array
// 				list.push(obj);
// 			}
			
// 				//把array設成json的value，key取名為"orderlist"
// 			var obj = 			
// 			{ "orderlist":list,
// 			}
// 				/*
// 				把json物件stringify成json字串
// 				用ajax將json字串送到controller做解析
// 				*/
// 			$.ajax({
// 				//傳送方式
// 				type : "POST",
// 				//送到哪裡
<%-- 				url : "<%=request.getContextPath()%>/front-end/shopping/shopping.do", --%>
// 				//傳遞的參數及json字串
// 				data : "&action=NUMB_AJAX&orderlist=" + JSON.stringify(obj),
// 				dataType : "json",
// 				//成功後要做的事:將form送出
// 				success: function(xxx){
// 		 			$(thebtn).parents('form').submit();
		 			
// 				},
// 				//失敗要做的事:跳出alert&錯誤訊息
// 				error: function(msg){
// 					alert('AJAX error!! ' + msg.responseText)
// 				}
// 			})
// 		}
	</script>
	
	
	
</head>
<%@ include file="/front-end/HeaderPage.jsp"%>
<%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>
	
	<%--    <% @SuppressWarnings("unchecked") --%>
	<%	MemberVO memVO = (MemberVO)session.getAttribute("loginMemberVO");
		
	Map<String, Map> map1 = new LinkedHashMap<String, Map>(); // key:shop_no  // value:map2
	Map<String, CommodityVO> map2 = new LinkedHashMap<String, CommodityVO>(); // key:com_no   // value:CommodityVO


		map1 = (Map<String, Map>) session.getAttribute("shoppingcart");
		CommodityVO comOrder = null;
	%>

	<%
		if (map1 != null && (map1.size() > 0)) {
	%>
	<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


	
			<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<font size="+3">${loginMemberVO.mem_name} 的購物車內容如下：</font>
					<!-- 標籤面板：標籤區 -->
					<div role="tabpanel">
						<ul class="nav nav-tabs" role="tablist">
						<%
					Set<String> setTag = map1.keySet();
					Iterator<String> itTag = setTag.iterator();
					int j = 0 ;
					
					while (itTag.hasNext()) {
						String shop_no = itTag.next();
						
						%>
						<li  id="shop_tag" role="presentation" class="<% if(j==0){ %>active<% } %> tagShop_no" >
							<a href="#<%=shop_no %>"aria-controls="<%=shop_no %>" role="tab" data-toggle="tab">
<%-- 								<jsp:useBean id="shopSvc" class="com.shop.model.ShopService"/> --%>
								<c:set var="shop_no" value="<%=shop_no %>"/>
								<span style="font-size:20px;font-weight:bold;">${shopSvc.findByPrimaryKey(shop_no).getShop_name() }</span>
							</a>
						</li>	
							<% j++;} %>
							
						</ul>

						<!-- 標籤面板：內容區 -->
						<div class="tab-content">
						<%
							Iterator<String> itTag11 = setTag.iterator();
						int jj = 0 ;
								while (itTag11.hasNext()) {
									String shop_no = itTag11.next();
									%>
							
						<div role="tabpanel" class="<% if(jj==0){ %>active <% } %>tab-pane" id="<%=shop_no %>">
							
							<table>
								<thead>
									<tr style="font-size:25px;">
										<th></th>
	<!-- 									<th width="200">商品編號</th> -->
	<!-- 									<th width="200">商家</th> -->
										<td width="100" align="center"><b>商品名稱</b></td>
										<td width="100" align="center"><b>價格</b></td> 	
										<td width="120" align="center"><b>數量</b></td>
									</tr>
								</thead>
							<%
								map2 = map1.get(shop_no);
										Set<String> set2 = map2.keySet();
										Iterator<String> it2 = set2.iterator();
							%>

							<!-- 開始取出商品 -->
								<% while (it2.hasNext()) {
									String com_no = it2.next();
									comOrder = map2.get(com_no);
									pageContext.setAttribute("comOrder", comOrder);
									%>
								<form  name="checkoutForm" action="<%=request.getContextPath()%>/front-end/shopping/shopping.do" method="POST" class="form-horizontal">
								
								<tr style="font-size:20px;">
									<td>
										<a href="<%=request.getContextPath()%>/front-end/commodity/OneCommodityDetil.jsp?com_no=<%=comOrder.getCom_no()%>" class="list-group-item hotitem">
											<img class="com_img" src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=<%=comOrder.getCom_no()%>&i=1"alt="尚無圖片">
										</a>
									</td>
<%-- 									<td class="com_no" width="100"><%=comOrder.getCom_no()%></td> --%>
<%-- 									<td class="shop_no" width="100"><%=comOrder.getShop_no()%></td> --%>
									<td class="com_name" width="300" align="center"><%=comOrder.getCom_name()%></td>
									
<%-- 									<td class="com_price" width="100"> <fmt:formatNumber type="number" value="${prom? (promVO.prom_dis*comOrder.com_price):comOrder.com_price}" pattern="#,#00"/> </td> --%>
										<td class="com_price" width="100" align="center"><%=comOrder.getCom_price()%></td>
								<td>
								<div id="countChange" style="padding-left:40px" >
								<select class="com_quan" name="com_quan" width="100" size="1" id="<%=comOrder.getShop_no()%>_select">
										<%
											for (int i = 1; i <= comOrder.getCom_store(); i++) {
										%>
										<option  	value="<%=i%>" <%= comOrder.getQuan()==i?"selected":"" %>><%=i%></option>
										<%
											}
										%>
								</select>
								</div>
								</td>
								<td>

									<a  class="btn btn-warning"href="<%=request.getContextPath()%>/front-end/shopping/shopping.do?action=DELETE&delShopNO=<%=comOrder.getShop_no()%>&delComNO=<%=comOrder.getCom_no()%>">
										 <font style="font-family:Microsoft JhengHei;font-size:13.5">刪  除</font>
									</a>
									
								</td>
							</tr>
								<%} %>
						</table>
<%-- 							<form id="checkoutForm" name="checkoutForm" action="<%=request.getContextPath()%>/front-end/shopping/shopping.do" method="POST"> --%>
<%-- 								<input type="hidden" name="checkOutShopNO" value="<%=comOrder.getShop_no()%>"> --%>
								<input type="hidden" name="checkOutComNO" value="<%=comOrder.getCom_no()%>">
								<input type="hidden" name="checkOutShopNO" value="<%=comOrder.getShop_no()%>">
								<input type="hidden" name="action" value="CHECKOUT"> 
								<input type="hidden" name="login" value="${loginMemberVO }"> 
								<input  type="submit" value="付款結帳" class="checkoutbtn" > 
<!-- 								<input type="button" value="付款結帳" class="checkoutbtn" onclick="ggg(this)">  -->
							</form>
						</div>
<%jj++;} %>
				
						</div>
					</div>


				</div>
			</div>
		</div>

<%} %>
		
	
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>




<%@ include file="/front-end/FooterPage.jsp"%><%-- ~~必須include3/3~~ --%>
		<%-- ~~body結束:無需寫body或html標籤~~ --%>