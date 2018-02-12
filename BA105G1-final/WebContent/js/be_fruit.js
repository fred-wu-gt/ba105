$(function(){
	// 設定頁面未置頂時會出現top按鈕
	$(document).scroll(function() {
		if($(document).scrollTop()>0){
			$("#fastbtn_div_top").fadeIn(500);
		}else{
			$("#fastbtn_div_top").fadeOut(500);
		}
	});

	// 設定點選top按鈕頁面會動態置頂
	$("#fastbtn_div_top>img").on("click",function(){
		var $body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');
		$body.animate({
			scrollTop: 0
		}, 300);
	});
})