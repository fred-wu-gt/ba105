<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.fruit.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.List"%>
<jsp:useBean id="comVO" scope="request" class="com.commodity.model.CommodityVO" />
<jsp:useBean id="comSvc" scope="request" class="com.commodity.model.CommodityService" />
<jsp:useBean id="fruitSvc" scope="request" class="com.fruit.model.FruitService" />
<%@ include file="/front-end/HtmlHeadPage.jsp" %>
<%
	ShopVO shopVO = (ShopVO) session.getAttribute("loginShopVO");
pageContext.setAttribute("shopVO",shopVO);
	List<CommodityVO> list = comSvc.getComByShopNo(shopVO.getShop_no());
	pageContext.setAttribute("list", list);
%>
<head>
<script>
		$(document).ready(function(){		
		if ("${saveSuccess}".length != 0) {
			swal("修改完成", "已修改", "success");
		}

		$("#save").click(function() {
			var com_remarks = $("#com_remarks").val();
			var com_no = $("#com_remarks").attr("com_no");
			$("#com_remarksParam").val(com_remarks);
			$("#com_no").val(com_no);
			$("#form3").submit();
		});
	});



</script>
<title></title>
<style>
	tr,th,td{
  	text-align:center;
  	vertical-align:center;
  }
  #table{
  width: 25%}  
  
  #com_remarks{
    	height: 302px; 
    	width:574px; 	
    	}
    	
  .com_name{
  		height: 100px; 
    	width:100px;	
  			}
  			
  .text{
  			width:80px;
  			} 	
  			
  .inpuesize{
  		width:100px;
  			}
  			
  .modal-backdrop.in{
  			background-color:gray;
  			opacity:0.5;
  			}
  			
  .noWrap{
  white-space:nowrap;
  		}
  #img1{
  		
  		margin-left: 0px;
  		}
  #img2{
  		
  		margin-left: 0px;
  		}
  #img3{
  		
  		margin-left: 0px;
  		}
  		
  #upfile1{
  			width: 60px;
  			}	
  #back{
	    text-align: center;
	    display:inline-block;
	    text-decoration: none !important;
	    margin:0 auto;
	    width:100%;
  	}	
  #backContainer{
    width: 100%;
  	text-align: center;
  
  }
  .fru_no{
  width:90px;
  }
  .com_status{
  width:60px;
  }
</style>
</head>
<%@ include file="/front-end/HeaderPage.jsp" %>		
		<div class="container">	<!-- 大容器開始 -->
			<div class="row">			<!-- ROW開始 -->						

				<div class="col-xs-12 col-sm-12">	<!-- 右欄開始 -->
					<table class="table table-hover table-bordered" id="table">
						<caption><b>所有商品資料</b></caption>
						<thead style="background-color:#d0f28a;">
							<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<font style="color:red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color:red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
							<tr>
								<th class="noWrap">商品編號</th>
								<th class="noWrap">商品名稱</th>
								<th class="noWrap">蔬果種類</th>
								<th class="noWrap">單價</th>
								<th class="noWrap">規格</th>
								<th class="noWrap">敘述</th>
								<th class="noWrap">照片1</th>
								<th class="noWrap">照片2</th>
								<th class="noWrap">照片3</th>
								<th class="noWrap">狀態</th>
								<th class="noWrap">庫存</th>
								<th class="noWrap">評分</th>
<!-- 								<th class="noWrap">評分人數</th>							 -->
								<th class="noWrap">修改</th>							
							</tr>
						</thead>
					<%@ include file="page1.file" %> 
					<c:forEach var="comVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<c:if test="${comVO.shop_no ==shopVO.shop_no}">
						<tbody>
							<form method="post" action="<%=request.getContextPath()%>/front-end/shop/shop.do" enctype="multipart/form-data">
							<tr>
								<td style="font-size:14px">${comVO.com_no}</td>
								<td><textarea class="com_name" name="com_name">${comVO.com_name}</textarea></td>
								<td>
					           	<select size="1" name="fru_no" class="fru_no" style="font-size:14px">
									<c:forEach var="fruitVO" items="${fruitSvc.getAll()}">
									<option value="${fruitVO.fru_no}" ${comVO.fru_no==fruitVO.fru_no? 'selected' : ''}>【${fruitVO.fru_name}】</option>
					                </c:forEach>
					            </select>
								</td>
								<td><input type="text" name="com_price" class="text" value="${comVO.com_price}"></td>
								<td style="font-size:14px"><input type="text" name="com_weight" class="text" value="${comVO.com_weight}"></td>
								<td>		
										<a href='#modal-id' data-toggle="modal" class="btn btn-default">更多</a>
											<div class="modal fade" id="modal-id">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
															<h6 class="modal-title">商品敘述</h6>
														</div>
														<div class="modal-body">
															<textarea name="com_remarks" id="com_remarks" com_no="${comVO.com_no}">${comVO.com_remarks}</textarea>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
															<button type="button" class="btn btn-primary" id="save">儲存</button>
														</div>
													</div>
												</div>
											</div>
								</td>
								<td>
									<label class="btn btn-info"style="font-size:14px;padding:3px;margin-bottom:3px">
									<input type="file" name="com_pic1" id="upfile1${comVO.com_no}" style="display:none;">
									<i class="fa fa-photo" style="font-size:14px"></i> 上傳圖片
									</label>
									<img  style="width:70px;" id="img1${comVO.com_no}" src="<%=request.getContextPath() %>/front-end/shop/DBGifReader_v4_pic1?com_no=${comVO.com_no}&comCol=com_pic1" alt="尚無圖片"  >
								</td>
								<script>								
										$(function() {
											$("#upfile1${comVO.com_no}").change(function() {
												readImage(this);
											});
											function readImage(input) {
												if (input.files && input.files[0]) {
													var FR = new FileReader();
													FR.onload = function(e) {
														$('#img1${comVO.com_no}').attr("src", e.target.result);
														$('#img1${comVO.com_no}').show();
													};
													FR.readAsDataURL(input.files[0]);
												}
											}
										});
								</script>
								<td>
									<label class="btn btn-info" style="font-size:14px;padding:3px;margin-bottom:3px">
									<input type="file" id="upfile2${comVO.com_no}" name="com_pic2" style="display:none;">
									<i class="fa fa-photo"></i> 上傳圖片
									</label>
									<img id="img2${comVO.com_no}"  style="width:70px;" src="<%=request.getContextPath() %>/front-end/shop/DBGifReader_v4_pic1?com_no=${comVO.com_no}&comCol=com_pic2" alt="尚無圖片">
								</td>
								<script>
										$(function() {
											$("#upfile2${comVO.com_no}").change(function() {
												readImage(this);
											});
											function readImage(input) {
												if (input.files && input.files[0]) {
													var FR = new FileReader();
													FR.onload = function(e) {
														$('#img2${comVO.com_no}').attr("src", e.target.result);
														$('#img2${comVO.com_no}').show();
													};
													FR.readAsDataURL(input.files[0]);
												}
											}
										});
								</script>
								<td>
									<label class="btn btn-info" style="font-size:14px;padding:3px;margin-bottom:3px">
									<input type="file" id="upfile3${comVO.com_no}" name="com_pic3" style="display:none;">
									<i class="fa fa-photo"></i> 上傳圖片
									</label>
									<img id="img3${comVO.com_no}" style="width:70px;" src="<%=request.getContextPath() %>/front-end/shop/DBGifReader_v4_pic1?com_no=${comVO.com_no}&comCol=com_pic3" alt="尚無圖片" >
								</td>
								<script>
										$(function() {
											$("#upfile3${comVO.com_no}").change(function() {
												readImage(this);
											});
											function readImage(input) {
												if (input.files && input.files[0]) {
													var FR = new FileReader();
													FR.onload = function(e) {
														$('#img3${comVO.com_no}').attr("src", e.target.result);
														$('#img3${comVO.com_no}').show();
													};
													FR.readAsDataURL(input.files[0]);
												}
											}
										});
								</script>
								<td>
									<select name="com_status" class="com_status">
											<option>${comVO.com_status}</option>
											<option>${comVO.com_status=='上架'?'下架':'上架'}</option>
									</select>
								</td>
								<td name="com_store">${comVO.com_store}</td>
								<td name="com_score">${comVO.com_score}</td>
<%-- 								<td name="com_peo">${comVO.com_peo}</td> --%>
								<td>
								     <input type="submit" value="修改">
							     		 <input type="hidden" name="com_no" value="${comVO.com_no}">
								     	 <input type="hidden" name="shop_no" value="${comVO.shop_no}">
							     	     <input type="hidden" name="com_store" value="${comVO.com_store}">
									     <input type="hidden" name="com_score" value="${comVO.com_score}">
									     <input type="hidden" name="com_peo" value="${comVO.com_peo}">
									     <input type="hidden" name="action"	value="updateCOM">
								</td>
							</tr>
							</form>
						</tbody>
					</c:if>
				</c:forEach>
				</table>
				<%@ include file="page2.file" %>

				</div>	<!-- 右欄結束 -->
				
				<br>
				<div id="backContainer" class="container">
					<a href="<%=request.getContextPath()%>/front-end/shop/shop_default.jsp" class="btn btn-warning" id="back" style="font-size:24px;padding:3px;margin-top:10px" ><b>back</b></a>
				</div>				
			</div>		<!-- row結束 -->
		</div>			<!-- 大容器結束 -->	
								
								
								<form id="form3" method="post" action="<%=request.getContextPath()%>/front-end/shop/shop.do">
								<input type="hidden" id="com_remarksParam" name="com_remarks"  value="">
								<input type="hidden" id="com_no" name="com_no"  value="">
								<input type="hidden" name="action"  value="saveCom_remarksParam">
								</form>
								

								<%@ include file="/front-end/FooterPage.jsp" %>