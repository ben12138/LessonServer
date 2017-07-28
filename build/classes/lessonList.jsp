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
var lessonList_num = 0;
$(function(){
	var state=<%=request.getSession().getAttribute("jjj")%>
	var str = "";
	var array = splitURL();
	var course = searchCourse("phone","searchcourses",array[1]);
	var courseVideo = findVideo(course[0].id);
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
	for(var i = 0;i<courseVideo.length;i++){
		str +="<div class = 'lessonList_catalogChildAll' id = 'lessonList_catalogChildAll"+i+"'>"+
					"<span class = 'lessonList_catalogNum'>"+courseVideo[i].coursename+"</span>"+
					"<span class='lessonList_catalogTimeAll' id = 'lessonList_catalogTimeAll"+i+"'>"+
						"<span class = 'lessonList_catalogTime'>"+"10:20"+"</span>"+
						"<span class = 'lessonList_catalogTimeImg'style = \"background-image: url('images/play.png');\"></span>"+
					"</span>"+
					"<a class = 'lessonList_catalogPlayAll' id = 'lessonList_catalogPlayAll"+i+"'>"+
						"<span class = 'lessonList_catalogPlay'>课时预览</span>"+
						"<div class = 'lessonList_catalogPlayImg' style = \"background-image: url('images/eye.png');\"></div>"+
					"</a></div>";
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
	$(".lessonList").html(str);
	lessonList_num = courseVideo.length;
	if(state!=null){
		var hasCourse = searchAddCourse("hascourse",eval(state).username,course[0].courseid);
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
			var addCourse = searchAddCourse("addcourse",eval(state).username,course[0].courseid);
			alert(addCourse.result);
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
			var deleteCourse = searchAddCourse("deletecourse",eval(state).username,course[0].courseid);
			alert(deleteCourse.result);
			if(deleteCourse.result=="success"){
				$(".lessonList_join").css("display","block");
				$(".lessonList_delect").css("display","none");
			}
		}
	});
});

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
</script>

<div class = "lessonList">
	<div class = "lessonList_firstDiv">
		<div class = "lessonList_bgImg" style = "background-image: url('images/childLesson.png');">
		</div>
		<div class = "lessonList_rightDiv">
			<h2 class = "lessonList_title">面向对象程序设计C++</h2>
			<div class = "lessonList_teacher">
				<div style="float: left;">讲师：</div>
				<div style="float: left;">顾鹏</div>
			</div>
			<div class = "lessonList_join" style="display: none;">
				<div class = "lessonList_nouse">
				</div>
				<a class = "lessonList_joinBtn">
					<span>立即参加</span>
				</a>
			</div>
			<div class = "lessonList_delect">
				<div class = "lessonList_nouse">
				</div>
				<a class = "lessonList_delectBtn">
					<span>取消课程</span>
				</a>
			</div>
		</div>
	</div>
		<div class = "lessonList_secondDiv" > 
			<div class = "lessonList_introAll" >
				<h2 class = "lessonList_intro">简介</h2>
				<div class = "lessonList_introFont">
					本系列教程面向零基础的同学，是一个深入浅出，通俗易懂的Python3视频教程。
					<br>
					前半部分主要讲解Python3的语法特性，后半部分着重讲解Python3在爬虫、Tkinter、Pygame游戏开发等实例上的应用。
					整个系列共16个章节，前边13个章节从一个小游戏引入Python，逐步介绍Python的语法以及语言特色。
					最后3个章节为案例的演示，是前边内容的总结和提高。
				</div>
			</div>
			<div class = "lessonList_catalogAll">
				<div class = "lessonList_catalogTop">
					<h2 class = "lessonList_catalogTitle">目录</h2>
				</div>
				<div class = "lessonList_catalogChildAll">
					<span class = "lessonList_catalogNum">课时1</span>
					<span class = "lessonList_catalogChildTitle">我和Python的第一次亲密接触</span>
					<span class="lessonList_catalogTimeAll">
						<span class = "lessonList_catalogTime">10:20</span>
						<span class = "lessonList_catalogTimeImg"style = "background-image: url('images/play.png');"></span>
					</span>
					<a class = "lessonList_catalogPlayAll">
						<span class = "lessonList_catalogPlay">课时预览</span>
						<div class = "lessonList_catalogPlayImg" style = "background-image: url('images/eye.png');"></div>
					</a>
				</div>
			</div>
		</div>
		<div class = "lessonList_thridDiv">
			<div class = "lessonList_teacherInfAll">
				<img alt="" src="images/person.jpg"width="75" height="75" />
				<p class = "lessonList_teacherName">顾鹏</p>
			</div>
			<div class = "lessonList_teacherAbout">
				韦玮，笔名韦苏恩，重庆韬翔网络科技有限公司联合创始人兼CEO，51CTO学院专家讲师，极客学院布道师，多年软件开发与项目管理经验，国家专利发明人。
			</div>
		</div>
</div>
</body>
</html>