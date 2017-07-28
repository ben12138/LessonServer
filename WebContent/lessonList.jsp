<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程信息</title>
</head>
<body>

<script type="text/javascript">
$(function(){
	$(".lessonList_closeBtn").hover(
			function(){
				$(this).attr("src","images/close2.png");
			},	
			function(){
				$(this).attr("src","images/close.png");
			}
		);
});
$(function(){
	    $('.lessonList_knowMoreHref').modal({
	    	trigger: '.lessonList_knowMoreHref',          // id or class of link or button to trigger modal
	        olay:'div.overlay',             // id or class of overlay
	        modals:'div.modal',             // id or class of modal
	        animationEffect: 'slideDown',   // overlay effect | slideDown or fadeIn | default=fadeIn
	        animationSpeed: 300,            // speed of overlay in milliseconds | default=400
	        moveModalSpeed: 'slow',         // speed of modal movement when window is resized | slow or fast | default=false
	        background: '333333',           // hexidecimal color code - DONT USE #
	        opacity: 0.7,                   // opacity of modal |  0 - 1 | default = 0.8
	        openOnLoad: false,              // open modal on page load | true or false | default=false
	        docClose: true,                 // click document to close | true or false | default=true    
	        closeByEscape: true,            // close modal by escape key | true or false | default=true
	        moveOnScroll: true,             // move modal when window is scrolled | true or false | default=false
	        resizeWindow: true,
	        close:'.lessonList_closeBtn'               // id or class of close button
	    });
});
var lessonList_num = 0;
$(function(){
	var state=<%=request.getSession().getAttribute("jjj")%>
	var str = "";
	var array = splitURL();
	var videoStr = ""
	if(array[0]=="video"){
		videoStr +="<video src = '"+array[1]+"' controls width='1160' height='600''/>";
		$("#video").html(videoStr);
	}
	var course = searchCourse("phone","searchcourses",array[1]);
	var courseVideo = findVideo(course[0].id);
	var comments = getAllComentsServlet(course[0].id);
	var currentPage = 1;
	str += "<div class = 'lessonList_firstDiv'>"+
					"<div class = 'lessonList_bgImg' style = \"background-image: url('"+course[0].androidimage+"');\"></div>"+
						"<div class = 'lessonList_rightDiv'>"+
							"<h2 class = 'lessonList_title'>"+course[0].coursename+"</h2>"+
							"<div class = 'lessonList_teacher'>"+
								"<div style='float: left;'>讲师：</div>"+
								"<div style='float: left;'>"+searchTeacherName(course[0].teacherid).teachername+"</div>"+
							"</div>"+
							"<div class = 'lessonList_join'>"+
								"<div class = 'lessonList_nouse'></div>"+
								"<a class = 'lessonList_joinBtn'>"+
									"<span>立即参加</span>"+
								"</a></div>"+
								"<div class = 'lessonList_delect' style='display: none;'>"+
									"<div class = 'lessonList_nouse'></div>"+
									"<a class = 'lessonList_delectBtn'>"+
										"<span>取消参加</span>"+
									"</a></div>"+
						"</div></div>"+
					"<div class = 'lessonList_secondDiv'>"+
						"<div class = 'lessonList_introAll'>"+
							"<h2 class = 'lessonList_intro'>简介</h2>"+
							"<div class = 'lessonList_introFont'>"+course[0].courseintroduction+"</div>"+
						"</div>"+
						"<div class = 'lessonList_catalogAll'>"+
							"<div class = 'lessonList_catalogTop'>"+
								"<h2 class = 'lessonList_catalogTitle'>目录</h2>"+
							"</div>";
	if(courseVideo!=null){
		for(var i = 0;i<courseVideo.length;i++){
			str +="<a href='?video="+courseVideo[i].courseurl+"'>"+
						"<div class = 'lessonList_catalogChildAll' id = 'lessonList_catalogChildAll"+i+"'>"+
							"<span class = 'lessonList_catalogNum'>"+courseVideo[i].coursename+"</span>"+
							"<span class='lessonList_catalogTimeAll' id = 'lessonList_catalogTimeAll"+i+"'>"+
								"<span class = 'lessonList_catalogTimeImg'style = \"background-image: url('images/play.png');\"></span>"+
							"</span>"+
							"<div class = 'lessonList_catalogPlayAll' id = 'lessonList_catalogPlayAll"+i+"'>"+
								"<span class = 'lessonList_catalogPlay'>课时预览</span>"+
								"<div class = 'lessonList_catalogPlayImg' style = \"background-image: url('images/eye.png');\"></div>"+
							"</div>"+
						"</div>"+
					"</a>";
			lessonList_num = courseVideo.length;
		}
	}
	str +="</div></div><div class = 'lessonList_thridDiv'>"+
				"<div class = 'lessonList_teacherInfAll'>"+
					"<img alt='' src='"+searchTeacherName(course[0].teacherid).teacherimage+"' width='75' height='75' >"+
					"<p class = 'lessonList_teacherName'>"+searchTeacherName(course[0].teacherid).teachername+"</p>"+
				"</div>"+
				"<div class = 'lessonList_teacherAbout'>"+
				searchTeacherName(course[0].teacherid).teacherintroduction+
				"</div>"+
			"</div>";
	if(comments!=null){
		str +="<div class = 'lessonList_fourthDiv' >"+
					"<h2 class = 'lessonList_evaluate'>评价</h2>"+
					"<div class = 'notNull' style='display:none;color:red;font-size:red;line-height:30px;margin-left:10px;font-weight:900;'>不能为空</div>"+
					"<div class = 'lessonList_giveCommentsDiv'>"+
						"<div class = 'lessonList_giveCommentsTextDiv'>"+
							"<textarea class='lessonList_giveCommentsText' id='lessonList_myComments' ></textarea>"+
						"</div>"+
						"<div class='lessonList_relGiveCommentsBtn'>"+
							"<input type='button' class='lessonList_giveBtn'value='发表'/>"+
							"<input type='button' class = 'lessonList_cancelBtn' value='取消'/>"+
						"</div>"+
					"</div>";
		if(comments.length>=3){
			for(var i=0;i<3;i++){
				str+=commentsStr(comments[i].senderheadimage, comments[i].sendernickname, comments[i].sendertime, comments[i].content,"lessonList_evaluateAll");
			}
		}else{
			for(var i=0;i<comments.length;i++){
				str+=commentsStr(comments[i].senderheadimage, comments[i].sendernickname, comments[i].sendertime, comments[i].content,"lessonList_evaluateAll");
			}
		}
		str+="<div class = 'lessonList_knowMore'>"+
					"<a class = 'lessonList_knowMoreHref' href = ''>"+
						"<p>查看更多</p>"+
					"</a>"+
				"</div>"+
				"</div>";
		str+="<div class='overlay' style='opacity:0.7;'></div>"+
					"<div id='modal1' class='modal' >"+
						"<div class = 'lessonList_allCommentsUp'>"+
							"<div class = 'lessonList_allComments'>全部评论</div>"+
							"<div>"+
								"<img src='images/close.png' class='lessonList_closeBtn' width='30px'>"+
							"</div>"+
						"</div><div class = 'lessonList_allEvaluate'>";
		str+="</div><div class='content'>"+
					"<div class='pagination-holder black clearfix'>"+
						"<ul id='dark-pagination' class='pagination' style='margin-top:10px;float:right;'></ul>"+
					"</div>"+
				"</div>"+
			"</div>";
	}
	$(".lessonList").html(str);
	if(state!=null){
		var hasCourse = searchAddCourse("hascourse",eval(state).username,course[0].id);
		if(hasCourse.result){
			$(".lessonList_join").css("display","none");
			$(".lessonList_delect").css("display","block");
		}else{
			$(".lessonList_join").css("display","block");
			$(".lessonList_delect").css("display","none");
		}
	}
	$(".lessonList_joinBtn").click(function(){
		if(state==null){
			alert("请登录");
		}else{
			var addCourse = searchAddCourse("addcourse",eval(state).username,course[0].id);
			if(addCourse.result=="success"){
				$(".lessonList_join").css("display","none");
				$(".lessonList_delect").css("display","block");
			}
		}
	});
	$(".lessonList_delectBtn").click(function(){
		if(state==null){
			alert("请登录");
		}else{
			var deleteCourse = searchAddCourse("deletecourse",eval(state).username,course[0].id);
			if(deleteCourse.result=="success"){
				$(".lessonList_join").css("display","block");
				$(".lessonList_delect").css("display","none");
			}
		}
	});
	
	$(".lessonList_giveBtn").click(function(){
		if(state==null){
			alert("请登录");
		}else{
			var content = document.getElementById("lessonList_myComments").value;
			if(content==""){
				$(".notNull").css("display","block");
			}else{
				$(".notNull").css("display","none");
				var sendernickname = state.nickname;
				var senderheadimage = state.headimage;
				var sender = state.username;
				var courseinfid = course[0].id;
				var result=addAllComments(sendernickname, senderheadimage, content, sender, courseinfid);
				if(result!="-1"){
					$("#toFlash").load(location.href);
				}else{
					alert("添加失败");
				}
				toWatchAllComments();
			}
		}
	});
	window.onload	= toWatchAllComments();
	function toWatchAllComments(){
	    $('.lessonList_knowMoreHref').modal({
	    	trigger: '.lessonList_knowMoreHref',          // id or class of link or button to trigger modal
	        olay:'div.overlay',             // id or class of overlay
	        modals:'div.modal',             // id or class of modal
	        animationEffect: 'slideDown',   // overlay effect | slideDown or fadeIn | default=fadeIn
	        animationSpeed: 300,            // speed of overlay in milliseconds | default=400
	        moveModalSpeed: 'slow',         // speed of modal movement when window is resized | slow or fast | default=false
	        background: '333333',           // hexidecimal color code - DONT USE #
	        opacity: 0.7,                   // opacity of modal |  0 - 1 | default = 0.8
	        openOnLoad: false,              // open modal on page load | true or false | default=false
	        docClose: true,                 // click document to close | true or false | default=true    
	        closeByEscape: true,            // close modal by escape key | true or false | default=true
	        moveOnScroll: true,             // move modal when window is scrolled | true or false | default=false
	        resizeWindow: true,
	        close:'.lessonList_closeBtn'               // id or class of close button
	    });
	    $('#dark-pagination').pagination({
			pages: (comments.length-comments.length%5)/5+1,
			cssStyle: 'dark-theme',
			displayedPages: 3,
			edges: 3
		});
	    
	    $(".lessonList_closeBtn").hover(
			function(){
				$(this).attr("src","images/close2.png");
			},	
			function(){
				$(this).attr("src","images/close.png");
			}
		);
	}
	var comStr = "";
	var comNum = 1;
	if(comments.length<=5){
		for(var i=0;i<comments.length;i++){
			comStr+=commentsStr(comments[i].senderheadimage, comments[i].sendernickname, comments[i].sendertime, comments[i].content,"");
		}
	}else{
		for(var i=0;i<5;i++){
			comStr+=commentsStr(comments[i].senderheadimage, comments[i].sendernickname, comments[i].sendertime, comments[i].content,"");
		}
		$("#dark-pagination").click(function(){
			currentPage=$("#dark-pagination").pagination('getCurrentPage');
			var comStr="";
			if(currentPage==1){
				for(var i=0;i<5;i++){
					comStr+=commentsStr(comments[i].senderheadimage, comments[i].sendernickname, comments[i].sendertime, comments[i].content,"");
				}
			}else{
				comNum=comments.length-5*(currentPage-1);
				if(comNum>=5){
					for(var i=comNum;i<comNum+5;i++){
						comStr+=commentsStr(comments[i].senderheadimage, comments[i].sendernickname, comments[i].sendertime, comments[i].content,"");
					}
				}else{
					for(var i=comNum;i<comments.length;i++){
						comStr+=commentsStr(comments[i].senderheadimage, comments[i].sendernickname, comments[i].sendertime, comments[i].content,"");
					}
				}
			}
			$(".lessonList_allEvaluate").html(comStr);
		});	
	}
	$(".lessonList_allEvaluate").html(comStr);
});
function commentsStr(img,name,date,comments,css){
	return "<div class = '"+css+"' id = 'toFlash'>"+
				"<div class = 'lessonList_evaluatePerson'>"+
					"<img src='"+img+"' class = 'lessonList_evaluatePersonImg'>"+
					"<div class='lessonList_evaluatePersonInf'>"+
						"<p class = 'lessonList_evaluatePersonMail'>"+name+"</p>"+
						"<span class = 'lessonList_evaluateDate'>"+date+"</span>"+
					"</div>"+
				"</div>"+
				"<div class = 'lessonList_realEvaluate'>"+comments+"</div>"+
			"</div>";
}
$(function(){
		var i = 0;
		for(i=0;i<lessonList_num;i++){
			lessonList_change(i);
		}
});
function searchAddCourse(type,username,courseinfid){
	var result = null;
	//alert(type+"--"+username+"--"+courseinfid);
	$.ajax({
		type:"POST", //请求方式  
        url:"./AddCourseServlet", //请求路径 
        async:false,
        cache: false,   
        data:"type="+type+"&username="+username+"&courseinfid="+courseinfid,  //传参  
        dataType: 'json',   //返回值类型  
        success:function(msg){
        	result=eval(msg);
        },  
        error:function(){  	
        	alert("error");  
        }  
	});
	return result;
}
function getAllComentsServlet(courseinfid){
	var result = null;
	//alert(type+"--"+username+"--"+courseinfid);
	$.ajax({
		type:"POST", //请求方式  
        url:"./GetAllCommentsServlet", //请求路径 
        async:false,
        cache: false,   
        data:"courseinfid="+courseinfid,  //传参  
        dataType: 'json',   //返回值类型  
        success:function(msg){
        	result=eval(msg);
        },  
        error:function(){  	
        	alert("error");  
        }  
	});
	return result;
}
function addAllComments(sendernickname,senderheadimage,content,sender,courseinfid){
	var result = null;
	$.ajax({
		type:"POST", //请求方式  
        url:"./AddAllCommentsServlet", //请求路径 
        async:false,
        cache: false,   
        data:"sendernickname="+sendernickname+"&senderheadimage="+senderheadimage+"&content="+content+"&sender="+sender+"&courseinfid="+courseinfid,  //传参  
        dataType: 'json',   //返回值类型  
        success:function(msg){
        	result=eval(msg);
        },  
        error:function(){  	
        	alert("error");  
        }  
	});
	return result;
}
</script>

<div class = "lessonList">
	
</div>
</body>
</html>