<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.ord_mas.model.*,com.member.model.*,com.ord_det.model.*,com.commodity.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.prom.model.*"%>


<%@ include file="/front-end/HtmlHeadPage.jsp"%><%--~~必須include1/3~~ --%>





<%-- ${loginMemberVO.mem_name} --%>




<jsp:useBean id="ord_masSvc" scope="session" class="com.ord_mas.model.Ord_masService" />
<%

// Ord_masService ord_masSvc= new Ord_masService();

// 由訂單主檔的方法getListByMemNO()回傳一個list
MemberVO memberVO = (MemberVO)session.getAttribute("loginMemberVO");
List<Ord_masVO> list = ord_masSvc.getListByMemNO(memberVO.getMem_no());

Set<Ord_detVO> ord_det_set = null ;
//由for取出list裡面的 Ord_masVO
for(int i = 0 ; i<list.size();i++){
	Ord_masVO ord_mas = list.get(i);
	//用訂單主檔的方法 getOrd_detByOrd_no傳入Ord_no回傳訂單明細的Set
	ord_det_set= ord_masSvc.getOrd_detByOrd_no(ord_mas.getOrd_no());
	
	ord_mas.setOrd_set(ord_det_set);

}

pageContext.setAttribute("list", list);

// List<Ord_masVO> list = (List<Ord_masVO>)request.getAttribute("listOrd_mas"); 


CommodityService comSvc = new CommodityService();
pageContext.setAttribute("comSvc", comSvc);
//  Iterator<Ord_detVO> it= ord_det_set.iterator();
//  while(it.hasNext()){
// 	 Ord_detVO  ord_detvo = it.next();
// 	 String com_no = ord_detvo.getCom_no();
// CommodityVO comVO = comSvc.getOneCommodityVO(com_no);
// pageContext.setAttribute("comVO", comVO);
//  }
%>



<head>
	<style>
		<%-- ~~寫此頁的css~~ --%>
	</style>
	
	<script>
		<%-- ~~寫此頁的js~~ --%>
	</script>
</head>

<%@ include file="/front-end/HeaderPage.jsp" %> <%-- ~~必須include2/3~~ --%>
<%-- ~~body開始:請直接開始寫內文，無須寫body或html標籤~~ --%>

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
		<%@ include file="page/page1.file" %> <br>
		<table class="table table-striped">
					<tr align="justify" style="background:#c790338a;">
    					<td width="100" align="center" ><b>訂單編號</b></td>
    					<td width="100" align="center"><b>商家名稱</b></td>
		    			<td width="100" align="center"><b>下單時間</b></td>
		    			<td width="100" style="padding-left:5px" ><b>訂單狀態</b></td>
		    			<td width="100"><b>訂單金額</b></td>		    			
		    		</tr>
				</table>
<!-- 				第一層迴圈開始，取出訂單主檔 -->
		<c:forEach var="ord_masVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
<!-- 							collapse 開啟 -->
			<br><a  data-toggle="collapse" href="#${ord_masVO.ord_no}" aria-expanded="false" aria-controls="${ord_masVO.ord_no}">
					<table class="table table-striped" >
			    		<tr>
			    			<td width="100" style="WORD-WRAP: break-word" width="180">${ord_masVO.ord_no}</td>
<%-- 			    			<jsp:useBean id="shopSvc" class="com.shop.model.ShopService"/> --%>
							<c:set var="shop_no" value="${ord_masVO.shop_no}"/>
			    			<td width="100" >${shopSvc.findByPrimaryKey(shop_no).getShop_name() }</td>
			    			<td width="100" ><fmt:formatDate value="${ord_masVO.ord_time}" pattern="yyyy-MM-dd HH:mm"/></td>
			    			<td width="100" >${ord_masVO.ord_sta}</td>
							<td width="100" >${ord_masVO.ord_total}</td>											
				    	</tr>
					</table>				
				</a>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/ord_mas/ord_mas.do" style="margin-bottom: 0px;">
				     <input type="submit" value="修改收件資訊">
				     <input type="hidden" name="ord_no"  value="${ord_masVO.ord_no}">
				     <input type="hidden" name="sendURL"  value="<%= request.getServletPath()%>">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				</FORM>				

<!-- 							collapse 內文 -->

			<div class="collapse" id='${ord_masVO.ord_no}'>
				<table class="table table-hover">
					<tr style="background:#d8f09a;">
    					<td width="25%" align="center" ><b>商品名稱</b></td>
    					<td width="25%" align="center" ><b>商品單價</b></td>		    					
		    			<td width="25%" align="center" ><b>訂購數量</b></td>
		    			<td width="25%" align="center" ><b>商品評分</b></td>			    			
		    		</tr>
				</table>
<!-- 						第二層迴圈開始，取出訂單明細 -->
				<c:forEach var="ord_detVO" items="${ord_masVO.ord_set}"  >
					<table class="table table-hover">
					<%
						Ord_masVO ord_masVO = (Ord_masVO) pageContext.getAttribute("ord_masVO");
								Ord_detVO ord_detVO = (Ord_detVO) pageContext.getAttribute("ord_detVO");
								CommodityVO comVO = comSvc.getCommodityVO(ord_detVO.getCom_no());
								Integer com_price = comVO.getCom_price();
								String disS = ord_masVO.getOrd_can_rea();
								if (disS != null) {
									Double dis = new Double(disS);
									int afterdis = (int) Math.round(com_price * dis);
									pageContext.setAttribute("afterdis", afterdis);
								}else{
									pageContext.setAttribute("afterdis", com_price);
								}
					%>
			    		<tr>
	    					<td width="25%" style="WORD-WRAP: break-word">${comSvc.getOneCommodityVO(ord_detVO.com_no).com_name}</td>
<%-- 			    					<td>${comSvc.getOneCommodityVO(ord_detVO.com_no).com_price}</td> --%>
	    					<td width="25%" Align="center"><div class="text-center">${afterdis}</div></td>
<!-- 			    					style="padding-left:90px" -->
	    					<td width="25%"><div class="text-center">${ord_detVO.od_quan}</div></td>
	    					<td width="25%">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/commodity/commodity.do" style="margin-bottom: 0px;">
									 <div class="text-center">
							     		<input  type="submit" value="評分">
							     		<input type="hidden" name="com_no"  value="${ord_detVO.com_no}">
							     		<input type="hidden" name="action"	value="getOne_For_Update_Score">
								     </div>
								</FORM>			
							</td>	
			    		</tr>
					</table>
				</c:forEach>
<!-- 						第二層迴圈結束 -->
			</div>
			
		</c:forEach>
<!-- 							第一層迴圈結束，取出訂單主檔 -->
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-sm-offset-5" >
				<%@ include file="page/page2.file" %>
		</div>
		
	</div>
</div>




<%@ include file="/front-end/FooterPage.jsp" %> <%-- ~~必須include3/3~~ --%>
<%-- ~~body結束:無需寫body或html標籤~~ --%>




<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>訂單主檔編號</th> -->
<!-- 		<th>一般會員編號</th> -->
<!-- 		<th>商家編號</th> -->
<!-- 		<th>下單時間</th> -->
<!-- 		<th>訂單狀態</th> -->
<!-- 		<th>訂單金額</th> -->
<!-- <!-- 		<th>收貨人姓名</th> --> 
<!-- <!-- 		<th>收貨人地址</th> --> 
<!-- <!-- 		<th>收貨人電話</th> --> 
<!-- <!-- 		<th>訂單取消原因</th> --> 
		
		
<!-- 	</tr> -->
	
	

<!-- 		<tr> -->
<%-- 			<td>${ord_masVO.ord_no}</td> --%>
<%-- 			<td>${ord_masVO.mem_no}</td> --%>
<%-- 			<td>${ord_masVO.shop_no }</td> --%>
<%-- 			<td>${ord_masVO.ord_time}</td> --%>
<%-- 			<td>${ord_masVO.ord_sta}</td> --%>
<%-- 			<td>${ord_masVO.ord_total}</td> --%>
<%-- <%-- 			<td>${ord_masVO.ord_rec}</td> --%> 
<%-- <%-- 			<td>${ord_masVO.ord_adr}</td> --%> 
<%-- <%-- 			<td>${ord_masVO.ord_tel}</td> --%> 
<%-- <%-- 			<td>${ord_masVO.ord_can_rea}</td> --%> 
			
				 	
<!-- 			<td> -->
<%-- 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/commodity/commodity.do" style="margin-bottom: 0px;"> --%>
<!-- 					     <input type="submit" value="刪除"> -->
<%-- 					     <input type="hidden" name="empno"  value="${comVO.com_no}"> --%>
<!-- 					     <input type="hidden" name="action" value="delete"> -->
<!-- 				 </FORM>		 -->
<!-- 			</td> -->
<!-- 		</tr>	 -->
		
<%-- 		<c:forEach var="ord_detVO" items="${ord_masVO.ord_set}"  > --%>
<!-- 		<tr> -->
<%-- 		<td>${ord_detVO.getOd_quan()}</td> --%>
<!-- 		</tr> -->
		
<%-- 		</c:forEach> --%>
		
	
<!-- </table> -->
