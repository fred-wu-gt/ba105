<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* , com.commodity.model.*,com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.sql.*"%>
<%@ page import="com.prom.model.*"%>


<jsp:useBean id="promSvc" class="com.prom.model.PromService"/>
<html>
<%@ include file="/front-end/HtmlHeadPage.jsp"%><%--~~����include1/3~~ --%>
<head>

 </head>
<%@ include file="/front-end/HeaderPage.jsp"%><%-- ~~����include2/3~~ --%>
<%-- ~~body�}�l:�Ъ����}�l�g����A�L���gbody��html����~~ --%>
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

	//����Ӯa�s�����X�A�@�����Y
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
					swal("������\�A�Y�N��ܭq�歶��");
					setTimeout(function(){$('#payForm').submit(); }, 2500);
					
				}
				if(data == 404){
					swal("�I�Ƥ����A�Y�N����x�ȭ���");
					$('#payForm').submit();
				} 
				
			},
			error:function(){alert("AJAX-class�o�Ϳ��~�o!")}
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
									<%-- ���~��C --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">�Эץ��H�U���~:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<table class="table table-hover">
 					<font size="+2">
	 					<c:if test="${not empty loginMemberVO}">
							${loginMemberVO.mem_name} �z�ثe���I��:�� <span class="nowVal"style="color:#28a418;"><b><%=memVO.getMem_val() %></b></span>�I<br>
						</c:if>
						
 					</font>
<%-- 						<caption><%=shop_no %></caption> --%>
					
						<caption>�Ӯa�W��:  	${shopSvc.findByPrimaryKey(shop_no).shop_name}</caption>
						<thead>
							<tr style="font-size:25px;">
								<th>�ӫ~�W��</th>
								<th>����</th>
								<th>�ƶq</th>
								<th width="120"><h3>�p�p</h3></th>
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
//�n�qJSTL���XcomVO�ɡA�������NcomVO�qpageContext���X��
// 		CommodityVO comVO = (CommodityVO) pageContext.getAttribute("checkoutComVO");

// 		PromVO promVO = promSvc.findByPrimaryKey(comVO.getShop_no());
// 		//���n!!! ����promVO��null���B�z
// 		if (promVO != null) {
// 			pageContext.setAttribute("promVO", promVO);
// 			// ���X�馩�}�l�A�����ɶ� �� ��U�ɶ�
// 			Timestamp start_time = promVO.getProm_start();
// 			Timestamp end_time = promVO.getProm_end();
// 			Timestamp now_time = new Timestamp(new java.util.Date().getTime());

// 			// �κX�Ъ��覡���ŦX�ɶ����� �� true�C �ɶ���long�Ӥ��
// 			boolean prom = false;
// 			if (now_time.getTime() > start_time.getTime() && end_time.getTime() > now_time.getTime()) {
// 				// 	�ɶ��b�Ϭq���A�אּTRUE
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
								<div class="amount"><font size="+2px">�`���B�G</font><font size="+3px" color="red">$<%=amount%></font></div>
							</td>
						</tr>
					</tbody>
				</table>
				
				<form id="payForm" action="<%=request.getContextPath()%>/front-end/shopping/shopping.do" method="POST">
						
					<table class="table table-hover ">
						<div align="center" >
								<h3>�� �� �� �T</h3>
						</div>
						
							<tr>
								<td style="font-size:20px">����H</td>
								<td>
<%-- 								<input type="text" name="ord_rec" size="100" maxlength="50" value="<%=(memVO == null) ?"" : memVO.getMem_name()%>"> --%>
								<input type="text" name="ord_rec" size="100" maxlength="50" value="${loginMemberVO.mem_name}">
<%-- 								${(not empty loginMemberVO || not empty loginManagerVO) && empty loginShopVO ?'activity_mem_home.jsp':''} --%>
								
								</td>
							</tr>
							<tr>
								<td style="font-size:20px">����a�}</td>
								<td>
<%-- 								<input type="text" name="ord_adr" size="100" maxlength="100" value="<%=(memVO == null) ?"" : memVO.getMem_loc()%>"> --%>
									<input type="text" name="ord_adr" size="100" maxlength="50" value="${loginMemberVO.mem_loc}">
								
								</td>
							</tr>
							<tr>
								<td style="font-size:20px">�s���q��</td>
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
									<input id="paybtn" type="button" value="�T�{���b" class="button" >
								</div>
						</form>

				
				<p><a href="<%=request.getContextPath()%>/front-end/shopping/listAllCommodityBS.jsp"><font size="+1"> �^����</font></a>
				<p><a href="<%=request.getContextPath()%>/front-end/shopping/Cart.jsp"><font size="+1"> �^���ʪ���</font></a>

					
				</div>
			</div>
		</div>



		<%@ include file="/front-end/FooterPage.jsp"%><%-- ~~����include3/3~~ --%>
		<%-- ~~body����:�L�ݼgbody��html����~~ --%>
		</html>