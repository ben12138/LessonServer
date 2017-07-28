<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程信息</title>
</head>
<body>

<script type="text/javascript">


window.onload=function(){
	alert("running");
	
}
</script>
<div style="height:200px;width: 1160xp;">
	<a href="${pageContext.request.contextPath }/Test">Test</a>
	<c:out value="${student }"></c:out>
	<c:out value="${sessionScope.jjj }"></c:out><br>
	<input id="data" type="hidden" value="${sessionScope.jjj }">
	<c:out value="${data }"></c:out>
</div>
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
				<a href = "?video=jick">
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
				</a>
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
		
		
		<div class = "lessonList_fourthDiv" >
			<h2 class = "lessonList_evaluate">评价</h2>
			<div class = "lessonList_evaluateAll">
				<div class = "lessonList_evaluatePerson">
					<img alt="" src="images/person.jpg" class = "lessonList_evaluatePersonImg">
					<div class="lessonList_evaluatePersonInf">
						<p class = "lessonList_evaluatePersonMail">m1352447939@163.com</p>
						<span class = "lessonList_evaluateDate">4月10日</span>
					</div>
				</div>
				<div class = "lessonList_realEvaluate">
						我的积分好事分厘卡圣诞节分厘卡三季稻家里ask的几次撒娇都vi睡觉哦啊错就扫街的从i啊是经常
				</div>
			</div>
			<div class = "lessonList_knowMore">
				<a class = "lessonList_knowMoreHref" href = "#modal1">
					<p>查看更多</p>
				</a>
			</div>
		</div>
		
		<div class="overlay"></div>
	    <div id="modal1" class="modal" >
	    	<div style="height: 30px;width: 100%;">
	    		<div style="width: 750px;height: 30px;position: absolute;font-size: 14px;line-height: 30px;font-weight: bold; ">全部评论</div>
	    		<div>
	    			<img alt="" src="images/close.png" class="lessonList_closeBtn" width="30px" style="">
	    		</div>
	        </div>
	        <div class = "lessonList_allEvaluate">
				<div class = "lessonList_evaluatePerson">
					<img alt="" src="images/person.jpg" class = "lessonList_evaluatePersonImg">
					<div class="lessonList_evaluatePersonInf">
						<p class = "lessonList_evaluatePersonMail">m1352447939@163.com</p>
						<span class = "lessonList_evaluateDate">4月10日</span>
					</div>
				</div>
				<div class = "lessonList_realEvaluate">
						我的积分好事分厘卡圣诞节分厘卡三季稻家里ask的几次撒娇都vi睡觉哦啊错就扫街的从i啊是经常
				</div>
				<div class = "lessonList_evaluatePerson">
					<img alt="" src="images/person.jpg" class = "lessonList_evaluatePersonImg">
					<div class="lessonList_evaluatePersonInf">
						<p class = "lessonList_evaluatePersonMail">m1352447939@163.com</p>
						<span class = "lessonList_evaluateDate">4月10日</span>
					</div>
				</div>
				<div class = "lessonList_realEvaluate">
						我的积分好事分厘卡圣诞节分厘卡三季稻家里ask的几次撒娇都vi睡觉哦啊错就扫街的从i啊是经常
				</div>
			</div>
			<div class="content">
			  <div class="pagination-holder black clearfix">
			    <ul id="dark-pagination" class="pagination"></ul>
			  </div>
			</div>
    </div>
</div>
</body>
</html>