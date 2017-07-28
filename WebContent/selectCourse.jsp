<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="build-in js/jquery.min.js" type="text/javascript"></script>
<title>课程选项中的课程</title>
</head>
<body>
<script type="text/javascript">
$(function(){
	var array = splitURL();
	var course = null;
	var str = "";
	if(array[0]=="type"&&array[1]=="allCourse"){
		course = getCourse("phone","allcourses");
		str += "<div class = 'selectCourse_firstdiv'>"+
						"<div class = 'selectCourse_seconddiv'>";
		for(var i=0;i<course.length;i++){
				str += htmlStr(course[i].androidimage, course[i].coursename, searchTeacherName(course[i].teacherid).teachername);
		}
		str+="</div></div>";
	}else if(array[0]=="coursetype1"){
		if(array.length==2){
			course = getFirstCourse("phone","getcoursetype1",array[1]);
			str += "<div class = 'selectCourse_firstdiv'>"+
							"<div class = 'selectCourse_seconddiv'>";
			for(var i=0;i<course.length;i++){
					str += htmlStr(course[i].androidimage, course[i].coursename, searchTeacherName(course[i].teacherid).teachername);
			}
			str+="</div></div>";
		}else if(array.length==4){
			course = getSecondCourse("phone","getcoursetype2",array[1],array[3]);
			str += "<div class = 'selectCourse_firstdiv'>"+
							"<div class = 'selectCourse_seconddiv'>";
			for(var i=0;i<course.length;i++){
					str += htmlStr(course[i].androidimage, course[i].coursename, searchTeacherName(course[i].teacherid).teachername);
			}
			str+="</div></div>";
		}else if(array.length==6){
			course = getThridCourse("phone","getcoursetype3",array[1],array[3],array[5]);
			str += "<div class = 'selectCourse_firstdiv'>"+
							"<div class = 'selectCourse_seconddiv'>";
			for(var i=0;i<course.length;i++){
					str += htmlStr(course[i].androidimage, course[i].coursename, searchTeacherName(course[i].teacherid).teachername);
			}
			str+="</div></div>";
		}
	}else if(array[0]=="keyword"){
		var course = searchCourse("phone","searchcourses",array[1]);
		str += "<div class = 'selectCourse_firstdiv'>"+
		"<div class = 'selectCourse_seconddiv'>";
		for(var i=0;i<course.length;i++){
			str += htmlStr(course[i].androidimage, course[i].coursename, searchTeacherName(course[i].teacherid).teachername);
		}
		str+="</div></div>";
	}
	$(".selectCourse_lesson").html(str);
});
function htmlStr(img,coursename,teachername){
	return "<div class = 'selectCourse_thriddiv'>"+
	"<a href = '?lessonName="+coursename+"'>"+
		"<div class = 'selectCourse_forthdiv'>"+
			"<img src = '"+img+"'>"+
		"</div>"+
		"<div class = 'selectCourse_lessonName'>"+
			"<h3>"+coursename+"<h3>"+
		"</div>"+
		"<div class = 'selectCourse_teacherName'>"+
			"<h4>"+teachername+"</h4>"+
		"</div>"+
	"</a>"+
"</div>";
}
</script>
<div>
	<div class = "selectCourse_lesson">
		<div class = "selectCourse_noLesson">
			<img alt="" src="images/noLesson.png">
			<div class = "selectCourse_noResult">暂无相关结果</div>
			<div class = "selectCourse_changeWord">换个搜索词试试吧：）</div>
		</div>
	</div>
</div>
</body>
</html>