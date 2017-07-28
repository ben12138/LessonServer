/**
 * 初始图片轮播
 */
$(function(){
	$('.change1_pic').unslider({arrows:true,dots:true});
	$('.change1_pic ').mouseover(function(){$(this).closest('.change1_pic').find('ul').stop();$(this).click();});
	$('.change1_pic').hover(function(){
		$(this).toggleClass('hover');
		}
	);
});