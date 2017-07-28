/**
 * 切换
 */

function change(str){
	for(i = 1;i<=6;i++){
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
 * 登录弹框
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
	
	//显示弹框
	$('.loginIn').click(function(){
		className = $(this).attr('class');
		$('#loginBg').fadeIn(300);
		$('#login').removeAttr('class').addClass('logined '+className+'').fadeIn();
	});
	
	//关闭弹窗
	$('.loginBtn').click(function(){
		$('#loginBg').fadeOut(300,function(){
			$('#login').addClass('loginOutUp').fadeOut();
		});
	});
});
/**
 * 注册弹框
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
	
	//显示弹框
	$('.registerIn').click(function(){
		className = $(this).attr('class');
		$('#registerBg').fadeIn(300);
		$('#register').removeAttr('class').addClass('registered '+className+'').fadeIn();
	});
	
	//关闭弹窗
	$('.registerBtn').click(function(){
		$('#registerBg').fadeOut(300,function(){
			$('#register').addClass('registerOutUp').fadeOut();
		});
	});
});
/**
 * 更改关闭窗口图片
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
 * 验证码
 **/
function verification(){
	$(".verificationBtn").css("background-color","#CDCDCD");
}
/**
 * 子课程拉框
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
 * 回到顶部
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
 * URL参数获取
 */
function splitURL(){
	var url = window.location.href;		//获取url
	var fir = url.split("?");						//将url以？进行切割
	var array = new Array();					//存储名字和值
	var num = 0;									//计数
	if(fir[1]!=null){
		var scon = fir[1].split("&");
		for(var i=0;i<scon.length;i++){
			var key_name = scon[i].split("=");//存储单个名字和值
			array[num]=key_name[0];
			array[++num]=key_name[1];
			num++;
		}
	}
	return array;
}
/**
 * 页面切换
 */
$(function(){
	var array = splitURL();
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
	}
});

/**
 * 课程获取
 */
function getCourse(type,getInf){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //请求方式  
         url:"./GetCourseInf", //请求路径  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf,  //传参  
         dataType: 'json',   //返回值类型  
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
 * 获取一级菜单课程
 */
function getFirstCourse(type,getInf,coursetype1){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //请求方式  
         url:"./GetCourseInf", //请求路径  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf+"&coursetype1="+coursetype1,  //传参  
         dataType: 'json',   //返回值类型  
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
 * 获取二级菜单课程
 */
function getSecondCourse(type,getInf,coursetype1,coursetype2){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //请求方式  
         url:"./GetCourseInf", //请求路径  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf+"&coursetype1="+coursetype1+"&coursetype2="+coursetype2,  //传参  
         dataType: 'json',   //返回值类型  
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
 * 获取三级菜单课程
 */
function getThridCourse(type,getInf,coursetype1,coursetype2,coursetype3){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //请求方式  
         url:"./GetCourseInf", //请求路径  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf+"&coursetype1="+coursetype1+"&coursetype2="+coursetype2+"&coursetype3="+coursetype3,  //传参  
         dataType: 'json',   //返回值类型  
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
 * 获取我的课程
 * @param type
 * @param getInf
 * @param name
 * @returns
 */
function getMyCourse(type,getInf,name){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //请求方式  
         url:"./GetCourseInf", //请求路径  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf+"&username="+name,  //传参  
         dataType: 'json',   //返回值类型  
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
 * 搜索
 */
$(".search_img").click(function(){
	changeJSP();
});
function changeJSP(){
	window.location.href("?keyword="+document.getElementsByTagName("input")[0].value);
}
/**
 * 判断enter键
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
 * 判断浏览器类型
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
 * 查找课程
 * @param type
 * @param getInf
 * @param name
 * @returns
 */
function searchCourse(type,getInf,name){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //请求方式  
         url:"./GetCourseInf", //请求路径  
         async:false,
         cache: false,   
         data:"type="+type+"&getInf="+getInf+"&name="+name,  //传参  
         dataType: 'json',   //返回值类型  
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
 * 获取视频
 * @param courseinfid
 * @returns
 */
function findVideo(courseinfid){
	var course = null;
	var test = null;
	 $.ajax({   
     	type:"POST", //请求方式  
         url:"./GetCourseVideoServlet", //请求路径  
         async:false,
         cache: false,   
         data:"courseinfid="+courseinfid,  //传参  
         dataType: 'json',   //返回值类型  
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
 * 课程显示,css样式变化
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