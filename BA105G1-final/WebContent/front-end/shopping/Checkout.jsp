<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* , com.commodity.model.*,com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.sql.*"%>
<%@ page import="com.prom.model.*"%>


<jsp:useBean id="promSvc" class="com.prom.model.PromService"/>
<html>
<%@ include file="/front-end/HtmlHeadPage.jsp"%><%--~~必須include1/3~~ --%>
<head>

 </head>
<%@ include file="/front-end/HeaderPage.jsp"%><%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>
<%-- <jsp:useBean id="memVO" class="com.member.model.MemberVO"/>  --%>


<%-- <% MemberService memSVC= new MemberService(); --%>
<!-- // MemberVO memVO= memSVC.getOneMember("MEM8"); -->
<%--  %> --%>

<%-- <jsp:useBean id="shopSvc" class="com.shop.model.ShopService"/>  --%>
<%
	Map<String, Map> map1 = new LinkedHashMap<String, Map>(); // key:shop_no  // value:map2
	Map<String, CommodityVO> map2 = new LinkedHashMap<String, CommodityVO>(); // key:com_no   // value:CommodityVO
	map1 = (Map<String, Map>) session.getAttribute("shoppingcart");
	String amount = String.valueOf(request.getAttribute("amount"));

	//先把商家編號取出，作為表頭
	String shop_no = request.getParameter("checkOutShopNO");
	pageContext.setAttribute("shop_no", shop_no);

	CommodityVO checkoutComVO = null;

	MemberService memSvc = new MemberService();
	MemberVO memVO = (MemberVO) session.getAttribute("loginMemberVO");
	memVO = memSvc.getOneMember(memVO.getMem_id());
	 
%>


<script type="text/javascript">
window.onload = init;
function init(){
	$('#paybtn').click(function(){
		console.log('testtttttttttt');
		$.ajax({
			type:"POST",
			url:"<%=request.getContextPath()%>/front-end/shopping/shopping.do",
			data:{
				"amount" : '<%= amount%>',
				"action":"BILLajax"
			},
			dataType:"text",
			success:function(data){
				if (data == 200){
					swal("交易成功，即將轉至訂單頁面");
					setTimeout(function(){$('#payForm').submit(); }, 2500);
					
				}
				if(data == 404){
					swal("點數不足，即將轉至儲值頁面");
					$('#payForm').submit();
				} 
				
			},
			error:function(){alert("AJAX-class發生錯誤囉!")}
		})
		
	});
	
}



</script>
<style>
#payForm{
    background-color: #f8ecc6;
}
</style>


<hr><p>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
									<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<table class="table table-hover">
 					<font size="+2">
	 					<c:if test="${not empty loginMemberVO}">
							${loginMemberVO.mem_name} 您目前的點數:為 <span class="nowVal"style="color:#28a418;"><b><%=memVO.getMem_val() %></b></span>點<br>
						</c:if>
						
 					</font>
<%-- 						<caption><%=shop_no %></caption> --%>
					
						<caption>商家名稱:  	${shopSvc.findByPrimaryKey(shop_no).shop_name}</caption>
						<thead>
							<tr style="font-size:25px;">
								<th>商品名稱</th>
								<th>價格</th>
								<th>數量</th>
								<th width="120"><h3>小計</h3></th>
							</tr>
						</thead>
						<tbody>
							<%	map2 = map1.get(shop_no);
							Set<String> set2 = map2.keySet();
							Iterator<String> it2 = set2.iterator();
							while (it2.hasNext()) {
							String com_no = it2.next();
							checkoutComVO = map2.get(com_no);
							pageContext.setAttribute("checkoutComVO", checkoutComVO);
							%>
							<tr style="font-size:20px;">
								<td width="200"><%=checkoutComVO.getCom_name()%> </td>

<%
//要從JSTL取出comVO時，必須先將comVO從pageContext取出來
// 		CommodityVO comVO = (CommodityVO) pageContext.getAttribute("checkoutComVO");

// 		PromVO promVO = promSvc.findByPrimaryKey(comVO.getShop_no());
// 		//重要!!! 防止promVO為null的處理
// 		if (promVO != null) {
// 			pageContext.setAttribute("promVO", promVO);
// 			// 取出折扣開始，結束時間 及 當下時間
// 			Timestamp start_time = promVO.getProm_start();
// 			Timestamp end_time = promVO.getProm_end();
// 			Timestamp now_time = new Timestamp(new java.util.Date().getTime());

// 			// 用旗標的方式給符合時間條件 為 true。 時間用long來比較
// 			boolean prom = false;
// 			if (now_time.getTime() > start_time.getTime() && end_time.getTime() > now_time.getTime()) {
// 				// 	時間在區段內，改為TRUE
// 				prom = true;
// 			}

// 			pageContext.setAttribute("prom", prom);
// 		}
	%>				
<%-- 								<td width="100"><fmt:formatNumber type="number" value="${prom? (promVO.prom_dis*checkoutComVO.com_price):checkoutComVO.com_price}" pattern="#,#00"/></td> --%>
								<td width="100" style="font-size:20px;"><%=checkoutComVO.getCom_price()%> </td>
								<td width="100" style="font-size:20px;"><%=checkoutComVO.getQuan()%> </td>
								<td width="100" style="font-size:20px;"><%=checkoutComVO.getCom_price()*checkoutComVO.getQuan()%> </td>								
<%-- 								<td width="100"><fmt:formatNumber type="number" value="${ prom? (promVO.prom_dis*checkoutComVO.com_price)*checkoutComVO.getQuan():checkoutComVO.com_price *checkoutComVO.getQuan()}" pattern="#,#00"/></td> --%>
							</tr>
							<%
						}
						%>
						<tr>
							<td colspan="6" style="text-align:right;"> 
								<div class="amount"><font size="+2px">總金額：</font><font size="+3px" color="red">$<%=amount%></font></div>
							</td>
						</tr>
					</tbody>
				</table>
				
				<form id="payForm" action="<%=request.getContextPath()%>/front-end/shopping/shopping.do" method="POST">
						
					<table class="table table-hover ">
						<div align="center" >
								<h3>收 件 資 訊</h3>
						</div>
						
							<tr>
								<td style="font-size:20px">收件人</td>
								<td>
<%-- 								<input type="text" name="ord_rec" size="100" maxlength="50" value="<%=(memVO == null) ?"" : memVO.getMem_name()%>"> --%>
								<input type="text" name="ord_rec" size="100" maxlength="50" value="${loginMemberVO.mem_name}">
<%-- 								${(not empty loginMemberVO || not empty loginManagerVO) && empty loginShopVO ?'activity_mem_home.jsp':''} --%>
								
								</td>
							</tr>
							<tr>
								<td style="font-size:20px">收件地址</td>
								<td>
<%-- 								<input type="text" name="ord_adr" size="100" maxlength="100" value="<%=(memVO == null) ?"" : memVO.getMem_loc()%>"> --%>
									<input type="text" name="ord_adr" size="100" maxlength="50" value="${loginMemberVO.mem_loc}">
								
								</td>
							</tr>
							<tr>
								<td style="font-size:20px">連絡電話</td>
								<td>
<%-- 								<input type="text" name="ord_tel" size="100" maxlength="10" value="<%=(memVO == null) ?"" : memVO.getMem_phone()%>"> --%>
									<input type="text" name="ord_tel" size="100" maxlength="50" value="${loginMemberVO.mem_phone}">
								
								</td>
							</tr>
					</table>					
								<input type="hidden" name="shop_no" value="<%=shop_no %>">
								<input type="hidden" name="com_no" value="<%=checkoutComVO.getCom_no()%>"> 
								<input type="hidden" name="com_price" value="<%=checkoutComVO.getCom_price()%>"> 
								<input type="hidden" name="com_quan" value="<%=checkoutComVO.getQuan()%>"> 
								<input type="hidden" name="amount" value="<%=amount%>"> 
								<input type="hidden" name="discount" value="${promVO.getProm_dis()}">
								<input type="hidden" name="action" value="BILL"> 
								<div align="center" >
									<input id="paybtn" type="button" value="確認結帳" class="button" >
								</div>
						</form>

				
				<p><a href="<%=request.getContextPath()%>/front-end/shopping/listAllCommodityBS.jsp"><font size="+1"> 回到賣場</font></a>
				<p><a href="<%=request.getContextPath()%>/front-end/shopping/Cart.jsp"><font size="+1"> 回到購物車</font></a>

					
				</div>
			</div>
		</div>



		<%@ include file="/front-end/FooterPage.jsp"%><%-- ~~必須include3/3~~ --%>
		<%-- ~~body結束:無需寫body或html標籤~~ --%>
		</html>