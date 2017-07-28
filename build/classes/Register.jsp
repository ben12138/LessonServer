<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="registerBg"></div>
				<div id="register" class="registered">
					<div class="registerTop" >
						<font>注册猿辅导</font>
						<a href="javascript:;" class="registerBtn">
							<img id = "change-img" src ="images/close.png">
						</a>
					</div>
					<form action="" method="post" id="editForm">
						<ul class="registerInfos"	>
							<li><label><input type="text" name=""  value = "注册账号"onfocus="this.value=''" onblur="if(this.value==''){this.value='注册账号'}" class="ipt" required/></label></li>
							<li><label><input type="text" name=""  value = "密码"onfocus="this.value=''" onblur="if(this.value==''){this.value='密码'}" class="ipt"  required/></label></li>
							<li>
								<label>
									<input type = "text" name = "" class = "ipt" style = "width:100px;"required/>
									<input type = "button" value = "验证码" class = "verificationBtn" style = "width:100px;" onclick = "verification()"/> 
								</label>
							</li>
							<li><input type="submit" value="确认注册" class="submitBtn" /></li>
						</ul>
					</form>
				</div>
</body>
</html>