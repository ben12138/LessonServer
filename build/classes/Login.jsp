<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="build-in js/jquery.min.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
state=<%=request.getSession().getAttribute("jjj")%>;
var jso = eval(state);
if(jso.result=="success"){
	var str = "<p class = 'login_nickName' style = 'margin-left:5px;'>"+jso.nickname+"</p><img src='images/person.jpg'>";
	$(".auth-menu").html(str);
}
</script>
<div id="loginBg"></div>
	<div id="login" class="logined">
		<div class="loginTop" >
			<font>登录猿辅导</font>
			<a href="javascript:;" class="loginBtn">
				<img id = "change-img" src ="images/close.png">
			</a>
		</div>
		<form action="LoginServlet" method="get" id="editForm">
			<ul class="loginInfos"	>
				<li><label><input type="text" id = "mail" name="username" required value="注册账号" onfocus="this.value=''" onblur="if(this.value==''){this.value='注册账号'}" class="ipt" /></label></li>
				<li><label><input type="text" id = "password" name="password" required value="密码" onfocus="this.value=''" onblur="if(this.value==''){this.value='密码'}" class="ipt" /></label></li>
				<li><input type="submit"value="确认登录" class="submitBtn" /></li>
			</ul>
		</form>
	</div>
</body>
</html>