<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/back-end/be_HtmlHeadPage.jsp" %>
<head>

	    <style>
    #dropDIV{
        text-align: center; 
        width: 300px;
        height: 200px;        
        margin: auto;
        border: dashed 2px gray;
         
    }
    img{
        max-height:200px; 
        max-width:300px;
    }
    </style>
	<script type="text/javascript">
		$(function(){
			$("#panel1").css("background-image","radial-gradient(ellipse,white,white,#5bc0de)");
			
		});
	</script>
</head>
<%@ include file="/back-end/be_HeaderPage.jsp" %>
<!-- 直接輸入內文內容，無須body標籤 -->

<%@ include file="/back-end/be_FooterPage.jsp" %>