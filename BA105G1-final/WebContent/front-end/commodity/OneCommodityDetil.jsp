<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.commodity.model.*,com.member.model.*"%>
<%@ page import="com.fruit.model.*"%>
<%@ page import="com.prom.model.*"%>
<%@ page import="com.fav_com.model.*"%>
<%@ page import="java.sql.*"%>

<!-- 取得com_no -->
<% 
String com_no = request.getParameter("com_no");
CommodityService comSvc = new CommodityService();
CommodityVO comVO = comSvc.getCommodityVO(com_no);
pageContext.setAttribute("comVO", comVO);

%>
 
<%--  <%=comVO.getCom_no() %>  --%>


<!-- 取得 memVO -->
<%
MemberVO memVO = (MemberVO)session.getAttribute("loginMemberVO");
pageContext.setAttribute("memVO",memVO);

%>
<%-- <%=memVO.getMem_no() %> --%>

<%
	FruitService fruSvc = new FruitService();
  	FruitVO friutVO = fruSvc.getOneFruit(comVO.getFru_no());
%>

<%-- friutVO==null? 	<%=friutVO==null %> --%>
<%-- <%=friutVO.getFru_no() %>  --%>


<%@ include file="/front-end/HtmlHeadPage.jsp"%><%--~~必須include1/3~~ --%>

<head>
<!-- Font Awesome CDN  -->
<script src="https://use.fontawesome.com/f33d2f4ae3.js"></script>


		<script>
                $(function(){
                      $('[data-toggle="tooltip"]').tooltip();
                  })
               </script>






</head>
<%-- ~~必須include2/3~~ --%>
<%@ include file="/front-end/HeaderPage.jsp"%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>
<script	src="https://unpkg.com/sweetalert2@7.0.9/dist/sweetalert2.all.js"></script>
<script type="text/javascript">
	
	
window.onload = init;
function init() {
		$("#btnaa").click(function(){
			var xxx = $(this);
			swal('成功加入購物車!', '', 'success').then(function() {
				  setTimeout(function(){
				  	$(xxx).parents('form').submit();
				  }, 200);
			  });
		});
		
		$('#addfav').click(function() {
//	         alert($(this).find("form").serialize());
			console.log('test');
	        $.ajax({
					type:"POST",
					url:"<%=request.getContextPath()%>/front-end/fav_com/fav_com.do",
					data:{
						"com_no":'${comVO.getCom_no()}',
						"mem_no":'${memVO.getMem_no()}',
						"action" : "insert"
					},
					dataType:"json",
					success:function(data){
						console.log("success");
						$("#addfav").prop("disabled", true );
						$("#addfav").removeClass("btn-danger").addClass("btn-default");
					},
					error:function(){swal("請先登入會員!")}
		
				})
	  });
}
	


	
	</script>
	
	


<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-12">
      <h6>商品編號${comVO.com_no}</h6>
    </div>
  </div>
</div>



<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-5">
			<!-- Place somewhere in the <body> of your page -->
			<div class="flexslider">
				<ul class="slides">
					<li><img
						src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${comVO.com_no}&i=1"
						alt="暫無圖片" height="300" width="350"></li>
					<li><img
						src="<%=request.getContextPath() %>/DBGifReader_v4_pic2XX.do?com_no=${comVO.com_no}&i=2"
						alt="暫無圖片" height="300" width="350"></li>
					<li><img
						src="<%=request.getContextPath() %>/DBGifReader_v4_pic3.do?com_no=${comVO.com_no}&i=3"
						alt="暫無圖片" height="300" width="350"></li>
				</ul>
			</div>



		</div>
		<div class="col-xs-12 col-sm-5 col-sm-offset-1">
			<h2>${comVO.com_name}</h2>

			<!--                   <button type="button" class="btn btn-danger" data-toggle="tooltip" data-placement="top" title="加入收藏"> -->
			<form name="shoppingForm"
				action="<%=request.getContextPath() %>/front-end/commodity/fav_com.do"
				method="POST">
				<input type="hidden" name="com_no" value="${comVO.com_no}">
				<input type="hidden" name="mem_no" value="${memVO.mem_no}">
				<input type="hidden" name="action" value="insert">

				<jsp:useBean id="fav_comSvc"
					class="com.fav_com.model.Fav_comService" />


				<c:if
					test="${fav_comSvc.findByBoth(memVO.mem_no,comVO.com_no)!= null}">
					<input id='addfav' type="button" class="cartbtn2 btn-default"
						value="加入收藏" disabled>
				</c:if>

				<c:if
					test="${fav_comSvc.findByBoth(memVO.mem_no,comVO.com_no)== null}">
					<input id='addfav' type="button" class="cartbtn2 btn-danger"
						value="加入收藏">

				</c:if>

			</form>

			<!--                   </button> -->
			<!-- tooltip的js -->

			<jsp:useBean id="promSvc" class="com.prom.model.PromService" />
			<%
//要從JSTL取出comVO時，必須先將comVO從pageContext取出來
		
		PromVO promVO = promSvc.findByPrimaryKey(comVO.getShop_no());
		//重要!!! 防止promVO為null的處理
		if (promVO != null) {
			pageContext.setAttribute("promVO", promVO);
			// 取出折扣開始，結束時間 及 當下時間
			Timestamp start_time = promVO.getProm_start();
			Timestamp end_time = promVO.getProm_end();
			Timestamp now_time = new Timestamp(new java.util.Date().getTime());

			// 用旗標的方式給符合時間條件 為 true。 時間用long來比較
			boolean prom = false;
			if (now_time.getTime() > start_time.getTime() && end_time.getTime() > now_time.getTime()) {
				// 	時間在區段內，改為TRUE
				prom = true;
			}

			pageContext.setAttribute("prom", prom);
		}
%>

			<c:set var="xxx"
				value="${prom ? (promVO.prom_dis*comVO.com_price):comVO.com_price}"></c:set>
			<%
											Object yyy = pageContext.getAttribute("xxx");
											long showPrice = Math.round(Double.parseDouble(yyy.toString()));
											pageContext.setAttribute("showPrice", showPrice);
											%>

			<c:if test="${showPrice == comVO.com_price }">
				<b>售價 :<span class="sa_newprice" style="font-size: 20px;"> <fmt:formatNumber
							type="number" value="${showPrice}" pattern="#,#00" /></span></b>元
			</c:if>

			<c:if test="${showPrice != comVO.com_price }">
				<font color="gray"><div class="old-price">
						原價 :
						<del>${comVO.com_price}</del>
						元
					</div></font>
				<b><div class="new-price">
						<span style="font-size: 20px;">限時特價 :<fmt:formatNumber type="number"
								value="${prom? (promVO.prom_dis*comVO.com_price):comVO.com_price}"
								pattern="#,#00" />元
						</span></b>
			</c:if>



			<form name="shoppingForm"
				action="<%=request.getContextPath() %>/front-end/shopping/shopping.do"
				method="POST">
				<table>
					<tr>
						<%-- 										<td><h4>商品評價:${comVO.com_score}</h4></td> --%>
						<td><input id="input-21d" value="${comVO.com_score}"
							type="text" class="rating" data-min=0 data-max=5 data-step=0.5
							data-size="xs" disabled></td>
					</tr>

					<tr>

						<td>
							<div class="cartbtn">
								<input id="btnaa" type="button"
									class="cartbtn2 ${comVO.com_store=='0'?'btn-warning':'btn-info'}"
									value="${comVO.com_store=='0'?'補貨中':'放入購物車'}"
									${comVO.com_store=='0'?'disabled':''}>
							</div>
						</td>
					</tr>
				</table>
				<input type="hidden" name="com_no" value="${comVO.com_no}">
				<input type="hidden" name="com_name" value="${comVO.com_name}">
				<input type="hidden" name="com_price" value="${comVO.com_price}">
				<input type="hidden" name="shop_no" value="${comVO.shop_no }">
				<input type="hidden" name="com_store" value="${comVO.com_store }">
				<input type="hidden" name="sentURL"
					value="<%=request.getRequestURI() %>"> <input type="hidden"
					name="action" value="ADD"> <input type="hidden" name="quan"
					value=1>
			</form>
			<span style="font-size:20px;font-weight:bold;">--規格說明--</span>
			<div class="com_weight">${comVO.com_weight}</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<span style="font-size:20px;font-weight:bold;">--關於商品--</span>
				<div class="about">${comVO.com_remarks}</div>
			</div>
		</div>
	</div>
</div>



<!-- Place somewhere in the <head> of your document -->
<link rel="stylesheet" href="css/flexslider.css" type="text/css">
<link rel="stylesheet" href="css/star-rating.css" type="text/css">

<script src="js/jquery.flexslider.js"></script>
<script src="js/star-rating.js"></script>
		<script>
			$(window).load(function() {
				$('.flexslider').flexslider({
					directionNav : false
				});
			});
		</script>
        
        <%@ include file="/front-end/FooterPage.jsp"%>
		<%-- ~~必須include3/3~~ --%>
		<%-- ~~body結束:無需寫body或html標籤~~ --%>