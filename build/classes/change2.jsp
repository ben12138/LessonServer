<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="build-in js/jquery.min.js" type="text/javascript"></script>
<title>我的课程</title>
</head>
<body>
<script type="text/javascript">
$(function(){
	state=<%=request.getSession().getAttribute("jjj")%>;
	var name = eval(state).username;
	var array = splitURL();
	var course = null;
	var str = "";
	if(array[0]=="type"&&array[1]=="myLesson"){
		course = getMyCourse("phone","getmycourse",name);
		alert(course.length);
		str += "<div class = 'change2_firstdiv'>"+
						"<div class = 'change2_seconddiv'>";
		for(var i=0;i<course.length;i++){
				str += htmlStr(course[i].androidimage, course[i].coursename, searchTeacherName(course[i].teacherid).teachername);
		}
		str+="</div></div>";
	}
	$(".change2_lesson").html(str);
});
</script>
<div>
	<div class = "change2_lesson">
		<div class = "change2_noLesson">
			<img alt="" src="images/noLesson.png">
			<div class = "change2_noResult">暂无相关结果</div>
			<div class = "change2_changeWord">换个搜索词试试吧：）</div>
		</div>
	</div>
</div>
</body>
</html>