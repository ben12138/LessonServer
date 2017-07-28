/**
 * 鍒囨崲
 */

function change(str){
	for(i = 1;i<=8;i++){
		if(i==str){
			if(str==3){
				document.getElementById("change3").style.display = "block";
				$('.banner').unslider({arrows:true,dots:true});
				$('.mydots').eq(1).hide();
				$('.banner .dot').mouseover(function(){$(this).closest('.banner').find('ul').stop();$(this).click();});
				$('.banner').hover(function(){
					$(this).toggleClass('hover');
					}
				);
			}else{
				document.getElementById('change'+str).style.display="block";
			}
		}else{
			document.getElementById('change'+i).style.display="none";
		}
		
	}
}
/**
 * 鐧诲綍寮规
 */
var w,h,className;
function getSrceenWH(){
	w = $(window).width();
	h = $(window).height();
	$('#loginBg').width(w).height(h);
}

window.onresize = function(){  
	getSrceenWH();
}  
$(window).resize();  

$(function(){
	getSrceenWH();
	
	//鏄剧ず寮规
	$('.loginIn').click(function(){
		className = $(this).attr('class');
		$('#loginBg').fadeIn(300);
		$('#login').removeAttr('class').addClass('logined '+className+'').fadeIn();
	});
	
	//鍏抽棴寮圭獥
	$('.loginBtn').click(function(){
		$('#loginBg').fadeOut(300,function(){
			$('#login').addClass('loginOutUp').fadeOut();
		});
	});
});
/**
 * 娉ㄥ唽寮规
 */
var w,h,className;
function getSrceenWH(){
	w = $(window).width();
	h = $(window).height();
	$('#registerBg').width(w).height(h);
}

window.onresize = function(){  
	getSrceenWH();
}  
$(window).resize();  

$(function(){
	getSrceenWH();
	
	//鏄剧ず寮规
	$('.registerIn').click(function(){
		className = $(this).attr('class');
		$('#registerBg').fadeIn(300);
		$('#register').removeAttr('class').addClass('registered '+className+'').fadeIn();
	});
	
	//鍏抽棴寮圭獥
	$('.registerBtn').click(function(){
		$('#registerBg').fadeOut(300,function(){
			$('#register').addClass('registerOutUp').fadeOut();
		});
	});
});
/**
 * 鏇存敼鍏抽棴绐楀彛鍥剧墖
 **/
$("#change-img").hover(
	function(){
		$(this).attr("src","images/close2.png");
	},	
	function(){
		$(this).attr("src","images/close.png");
	}
);
/**
 * 楠岃瘉鐮�
 **/
function verification(){
	$(".verificationBtn").css("background-color","#CDCDCD");
	$(".verificationBtn").attr("disabled","disabled");
	var initTime = parseInt(60);
	function timer(initTime){
		var second=0;
		window.setInterval(function(){
			if(initTime>=0){
				second=Math.floor(initTime);
			}
			if(second<=9){
				second='0'+second;
			}
			$(".verificationBtn").val(second);
			if(initTime>0){
				initTime--;
			}else{
				$(".verificationBtn").css("background-color","#428bca");
				$(".verificationBtn").attr("disabled",false);
				$(".verificationBtn").val("楠岃瘉鐮�");
			}
		},1000);
	}
	$(function(){
		timer(initTime);
	});
}
/**
 * 瀛愯绋嬫媺妗�
 */
$(function(){
	$('.submenu').mouseenter(function(){
		$('.childflright').fadeTo(0,0.8).stop().animate({'width':'550px'},300);
	});
	$('.john').mouseleave(function(){
		$('.childflright').stop().animate({'width':'0px'},300);
	})
})
/**
 * 鍥炲埌椤堕儴
 **/
$(window).scroll(function() {		
	if($(window).scrollTop() >= 50){
		$('.goToTop').fadeIn(300); 
	}else{    
		$('.goToTop').fadeOut(300);    
	}  
});
$('.goToTop').click(function(){
$('html,body').animate({scrollTop: '0px'}, 800);});
$(".goToTop").hover(
	function(){
		$("#goTop_img").attr("src","images/topover.png");
	},
	function(){
		$("#goTop_img").attr("src","images/top.png");
	}
);
/*
 * URL鍙傛暟鑾峰彇
 */
function splitURL(){
	var url = window.location.href;		//鑾峰彇url
	var fir = url.split("?");						//灏唘rl浠ワ紵杩涜鍒囧壊
	var array = new Array();					//瀛樺偍鍚嶅瓧鍜屽�
	var num = 0;									//璁℃暟
	if(fir[1]!=null){
		var scon = fir[1].split("&");
		for(var i=0;i<scon.length;i++){
			var key_name = scon[i].split("=");//瀛樺偍鍗曚釜鍚嶅瓧鍜屽�
			array[num]=key_name[0];
			array[++num]=key_name[1];
			num++;
		}
	}else{
		return null;
	}
	return array;
}
/**
 * 椤甸潰鍒囨崲
 */
$(function(){
	var array = splitURL();
	if(array==null){
		change(1);
	}else{
		if(array[0]=="type"){
			if(array[1]=="allCourse"){
				change(5);
			}else if(array[1]=="myLesson"){
				change(2);
			}else if(array[1]=="understand"){
				change(3);
			}else if(array[1]=="download"){
				change(4);
			}
		}else if(array[0]=="coursetype1"){
			change(5);
		}else if(array[0]=="keyword"){
			change(5);
		}else if(array[0]=="lessonName"){
			change(6);
		}else if(array[0]=="video"){
			change(7);
		}else if(array[0]=="page"){
			change(8);
		}
	}
});

/**
 * 璇剧▼鑾峰彇
 */
function getCourse(type,getInf){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //璇锋眰鏂瑰紡  
         url:"./GetCourseInf", //璇锋眰璺緞  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf,  //浼犲弬  
         dataType: 'json',   //杩斿洖鍊肩被鍨� 
         success:function(msg){        
         	course = eval(msg);
         	test = JSON.stringify(msg);
         },  
         error:function(){  	
         	alert("error");  
         }  
      });  
	 return course;
}
/**
 * 鑾峰彇涓�骇鑿滃崟璇剧▼
 */
function getFirstCourse(type,getInf,coursetype1){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //璇锋眰鏂瑰紡  
         url:"./GetCourseInf", //璇锋眰璺緞  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf+"&coursetype1="+coursetype1,  //浼犲弬  
         dataType: 'json',   //杩斿洖鍊肩被鍨� 
         success:function(msg){        
         	course = eval(msg);
         	test = JSON.stringify(msg);
         },  
         error:function(){  	
         	alert("error");  
         }  
      });  
	 return course;
}
/**
 * 鑾峰彇浜岀骇鑿滃崟璇剧▼
 */
function getSecondCourse(type,getInf,coursetype1,coursetype2){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //璇锋眰鏂瑰紡  
         url:"./GetCourseInf", //璇锋眰璺緞  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf+"&coursetype1="+coursetype1+"&coursetype2="+coursetype2,  //浼犲弬  
         dataType: 'json',   //杩斿洖鍊肩被鍨� 
         success:function(msg){        
         	course = eval(msg);
         	test = JSON.stringify(msg);
         },  
         error:function(){  	
         	alert("error");  
         }  
      });  
	 return course;
}
/**
 * 鑾峰彇涓夌骇鑿滃崟璇剧▼
 */
function getThridCourse(type,getInf,coursetype1,coursetype2,coursetype3){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //璇锋眰鏂瑰紡  
         url:"./GetCourseInf", //璇锋眰璺緞  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf+"&coursetype1="+coursetype1+"&coursetype2="+coursetype2+"&coursetype3="+coursetype3,  //浼犲弬  
         dataType: 'json',   //杩斿洖鍊肩被鍨� 
         success:function(msg){        
         	course = eval(msg);
         	test = JSON.stringify(msg);
         },  
         error:function(){  	
         	alert("error");  
         }  
      });  
	 return course;
}
/**
 * 鑾峰彇鎴戠殑璇剧▼
 * @param type
 * @param getInf
 * @param name
 * @returns
 */
function getMyCourse(type,getInf,name){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //璇锋眰鏂瑰紡  
         url:"./GetCourseInf", //璇锋眰璺緞  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf+"&username="+name,  //浼犲弬  
         dataType: 'json',   //杩斿洖鍊肩被鍨� 
         success:function(msg){        
         	course = eval(msg);
         	test = JSON.stringify(msg);
         },  
         error:function(){  	
         	alert("error");  
         }  
      });  
	 return course;
}
/**
 * 鎼滅储
 */
$(".search_img").click(function(){
	changeJSP();
});
function changeJSP(){
	//涓哄吋瀹圭伀鐙愰噰鐢�
	var url="http://106.15.194.192:8080/LessonServer/main.jsp?keyword="+document.getElementsByTagName("input")[0].value;
	window.location.href=url;
//	window.location.href("?keyword="+document.getElementsByTagName("input")[0].value);
}
/**
 * 鍒ゆ柇enter閿�
 * @param e
 */
function keyDown(e){
	var keycode = 0;
	if(CheckBrowserIsIE()){
		keycode = event.keyCode;
	}else{
		keycode = e.which;
	}
	if(keycode == 13){
		window.location.href("?keyword="+document.getElementsByTagName("input")[0].value);
	}
}
/**
 * 鍒ゆ柇娴忚鍣ㄧ被鍨�
 * @returns {Boolean}
 */
function CheckBrowserIsIE(){
	var result = false;
	var browser = navigator.appName;
	if(browser == "Microsoft Internet Explorer"){
		result = true;
	}
	return result;
}
/**
 * 鏌ユ壘璇剧▼
 * @param type
 * @param getInf
 * @param name
 * @returns
 */
function searchCourse(type,getInf,name){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //璇锋眰鏂瑰紡  
         url:"./GetCourseInf", //璇锋眰璺緞  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf+"&name="+name,  //浼犲弬  
         dataType: 'json',   //杩斿洖鍊肩被鍨� 
         success:function(msg){        
         	course = eval(msg);
         	test = JSON.stringify(msg);
         },  
         error:function(){  	
         	alert("error");  
         }  
      });  
	 return course;
}
/**
 * 鑾峰彇瑙嗛
 * @param courseinfid
 * @returns
 */
function findVideo(courseinfid){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //璇锋眰鏂瑰紡  
         url:"./GetCourseVideoServlet", //璇锋眰璺緞  
         async:false,
         cache: false,   
         data:"courseinfid="+courseinfid,  //浼犲弬  
         dataType: 'json',   //杩斿洖鍊肩被鍨� 
         success:function(msg){        
         	course = eval(msg);
         	test = JSON.stringify(msg);
         },  
         error:function(){  	
         	alert("error");  
         }  
      });  
	 return course;
}
/**
 * 璇剧▼鏄剧ず,css鏍峰紡鍙樺寲
 * @param changeNum
 */
function lessonList_change(changeNum){
	$("#lessonList_catalogChildAll"+changeNum).hover(
		function(){
			$("#lessonList_catalogTimeAll"+changeNum).css("display","none");
			$("#lessonList_catalogPlayAll"+changeNum).css("display","block");
		},
		function(){
			$(" #lessonList_catalogTimeAll"+changeNum).css("display","block");
			$(" #lessonList_catalogPlayAll"+changeNum).css("display","none");
		}
	);
}

function LessonType_mouseOver(changeNum,str){
	$("#firstLesson"+changeNum).mouseover(function(){
			$(".flright").html(str);
		});
}