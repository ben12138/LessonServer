<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="css/title.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel = "stylesheet" type = "text/css" href = "css/allLesson.css">
<link rel = "stylesheet" type = "text/css" href = "css/childLesson.css">
<link rel="stylesheet" type="text/css" href="css/change1.css">
<link rel="stylesheet" type="text/css" href="css/change2.css">
<link rel = "stylesheet" type = "text/css" href = "css/change4.css">
<link rel = "stylesheet" type = "text/css" href = "css/selectCourse.css">
<link rel = "stylesheet" type = "text/css" href = "css/lessonList.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>猿辅导</title>
</head>
<body onkeydown="keyDown(event)">
<!------------------------------------------------------------------ 头部 -------------------------------------------------------------------------------->
	<div id = "hd">
		<div class = "warp">
			<h1>
				<a href = "http://localhost:8080/LessonServer/main.jsp">
					<img src = "images/logo.png" width = "122" height = "28" title = "首页">
				</a>
			</h1>
			<ul class = "all_lesson_menu">
				<li class = "hed" >
					<a id = "btn01" href = "?type=allCourse" class = "link" >全部课程</a>
					<jsp:include page="LessonType.jsp"></jsp:include>
				</li>
				<li class = "hed">
					<a id = "btn02" href = "?type=myLesson" class = "link" >我的课程</a>
				</li>
				<li class = "hed">
					<a id = "btn03" href = "?type=understand" class = "link" >了解猿辅导</a>
				</li>
				<li class = "hed">
					<a id = "btn04" href = "?type=download" class = "link" >下载客户端</a>
				</li>
			</ul>
		</div>
		<div>
			<input type = "text" id = "search_input" onfocus="if(this.value=='搜索'){this.value=''}" onblur="if(this.value==''){this.value='搜索'}" value = "搜索">
			<div class="search_img" style = "background-image: url('images/search.png');"></div>
		</div>
		<div class = "auth-menu" >
			<div class = "login">
				<a href="javascript:;" class="loginIn">登录</a>
					<jsp:include page="Login.jsp"></jsp:include>
				<a href="javascript:;" class="registerIn" >注册</a>
					<jsp:include page="Register.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<div style = "height:56px;"></div>
<!-- -----------------------------------------------------------------内容--------------------------------------------------------------------------- -->
	<div style = "width:100%;background-color:#F8F8F8;">
		<div id = "bod">
			<div class = "goToTop">
				<img id = "goTop_img"alt="" src="images/top.png" title = "回到顶部">
			</div>
<!-- --------------------------------------------------------------子元素----------------------------------------------------------------------- -->
			<div id = "childElement">
				<div class = "hidden" style = "display:block;" id = "change1">
					<jsp:include page="change1.jsp" ></jsp:include>
				</div>
				<div class = "hidden" id = "change2">
					<jsp:include page="change2.jsp"></jsp:include>
				</div>
				<div class="hidden" id="change3">
				    <jsp:include page = "change3.jsp" ></jsp:include>
				</div>
				<div class = "hidden" id = "change5">
					<jsp:include page = "selectCourse.jsp"></jsp:include>
				</div>
				<div class = "hidden" id = "change6">
					<jsp:include page = "lessonList.jsp"></jsp:include>
				</div>
			</div>
			<div class = "hidden" id = "change4" >
				<div>
					<jsp:include page="change4.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</body>
<script src ="build-in js/jquery.min.js"></script>
<script src = "build-in js/unslider.min.js"></script>
<script src ="js/title.js"></script>
<script src ="js/change1.js"></script>
<script src = "js/change4.js"></script>
<script src="http://www.jq22.com/js/jq.js"></script>
</html>