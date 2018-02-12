<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.fruit.model.*"%>

<%
CommodityVO comVO = (CommodityVO) request.getAttribute("comVO");
%>


<%
	FruitService fruSvc = new FruitService();
  	FruitVO friutVO = fruSvc.getOneFruit(comVO.getFru_no());
%>

	

<%-- friutVO==null? 	<%=friutVO==null %> --%>
<%-- <%=friutVO.getFru_no() %>  --%>

<!DOCTYPE html>
<html>
<%@ include file="/front-end/HtmlHeadPage.jsp"%><%--~~必須include1/3~~ --%>

<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>
	$(window).load(function() {
		$('.flexslider').flexslider({
			directionNav : false
		});
	});

	$(function() {
		$('[data-toggle="tooltip"]').tooltip();
	})
</script>

</head>
<link rel="stylesheet" href="css/flexslider.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="js/jquery.flexslider.js"></script>




<%@ include file="/front-end/HeaderPage.jsp"%>
<%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>

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
    <li>
              <img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${comVO.com_no}&i=1" alt="暫無圖片"height="300" width="350">
    </li>
    <li>
              <img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?com_no=${comVO.com_no}&i=2" alt="暫無圖片"height="200" width="300">
    </li>
    <li>
			<img src="<%=request.getContextPath() %>/DBGifReader_v4_pic3.do?com_no=${comVO.com_no}&i=3" alt="暫無圖片"height="200" width="300">
    </li>
  </ul>
</div>
            


            </div>
            <div class="col-xs-12 col-sm-5 col-sm-offset-1">
              <h2>${comVO.com_name}</h2>
                  
                  <button type="button" class="btn btn-danger" data-toggle="tooltip" data-placement="top" title="加入收藏">
                    <i class="fa fa-heart"></i>
                  </button>
                <!-- tooltip的js -->
                 <script>
                    $(function(){
                        $('[data-toggle="tooltip"]').tooltip();
                    })
                </script>
                


          	<font color="gray"><div class="old-price"><span>原價 :</span><del>${comVO.com_price}</del>元</div></font>
          
          <!-- 加底線的CSS
          <span class="sa_oldprice" style="text-decoration:line-through">1000</span>元 -->



          	<b><div class="new-price"><span>特價 :</span> ${comVO.com_price}元</div></b>

        

         <div class="btn-primary btn" style="width:145px">加入購物車
          <i class="fa fa-shopping-cart"></i>
         </div>

          <div class="btn-success btn" style="width:145px">私訊商家
          <i class="fa fa-commenting"></i>
         </div>


         <h4>關於商品</h4>

         <div class="about"> 
          ${comVO.com_remarks}
          </div>

          <h4>規格說明</h4>
          <div class="com_weight">
            ${comVO.com_weight}
          </div>
            </div>
          </div>
        </div>
		
<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>商品編號</th> -->
<!-- 		<th>商品名稱</th> -->
<!-- 		<th>商家編號</th> -->
<!-- 		<th>蔬果編號</th> -->
<!-- 		<th>單價</th> -->
<!-- 		<th>規格</th> -->
<!-- 		<th>商品敘述</th> -->
<!-- 		<th>商品照片1</th> -->
<!-- 		<th>商品照片2</th> -->
<!-- 		<th>商品照片3</th> -->
<!-- 		<th>上次修改時間</th> -->
<!-- 		<th>上/下架狀態</th> -->
<!-- 		<th>庫存數量</th> -->
<!-- 		<th>評分平均</th> -->
<!-- 		<th>評分人數</th> -->
	
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td>${comVO.com_no}</td> --%>
<%-- 		<td>${comVO.com_name}</td> --%>
<%-- 		<td>${comVO.shop_no}</td> --%>
<%-- 		<td><%=friutVO.getFru_name() %></td> --%>
<%-- 		<td>${comVO.com_price}</td> --%>
<%-- 		<td>${comVO.com_weight}</td> --%>
<%-- 		<td>${comVO.com_remarks}</td> --%>
<%-- 		<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic1.do?com_no=${comVO.com_no}&i=1" alt="暫無圖片"height="200" width="300"></td> --%>
<%-- 		<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic2.do?com_no=${comVO.com_no}&i=2" alt="暫無圖片"height="200" width="300"></td> --%>
<%-- 		<td><img src="<%=request.getContextPath() %>/DBGifReader_v4_pic3.do?com_no=${comVO.com_no}&i=3" alt="暫無圖片"height="200" width="300"></td> --%>
<%-- <%-- 		<td><img alt="暫無圖片" src="data:image/png;base64,${com_pic1S}"/> </td> --%> 
<%-- <%-- 		<td><img alt="暫無圖片" src="data:image/png;base64,${com_pic2S}"/> </td> --%> 
<%-- <%-- 		<td><img alt="暫無圖片" src="data:image/png;base64,${com_pic3S}"/> </td> --%>
<%-- 		<td><fmt:formatDate value="${comVO.com_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%-- 		<td>${comVO.com_status}</td> --%>
<%-- 		<td>${comVO.com_store}</td> --%>
<%-- 		<td>${comVO.com_score}</td> --%>
<%-- 		<td>${comVO.com_peo}</td>	 --%>
		
<!-- 	</tr> -->
<!-- </table> -->

<%@ include file="/front-end/FooterPage.jsp"%>
<!-- Place somewhere in the <head> of your document -->

<%-- ~~必須include3/3~~ --%>
		<%-- ~~body結束:無需寫body或html標籤~~ --%>
</html>