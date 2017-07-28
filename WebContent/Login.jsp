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
$(function(){
	var mail = null;
	var pwd = null;
	$("#Login_submitBtn").click(function(){
		pwd = $("#Login_pwd").val();
		localStorage.setItem("password", pwd);
	})
	if(jso==null){
		$(".login").css("display","block");
		$("#Login_submitBtn").click(function(){
			mail = $("#Login_mail").val();
			pwd = $("#Login_pwd").val();
			if(isEmail(mail)){
				//Login("web",mail,pwd);
			}else{
				//alert("邮箱格式错误");
			}
		});
	}else{
		var filepath = jso.headimage;
		if(!CheckImgExists(filepath)){
			//filepath = "images/person.jpg";
		}
		if(jso.result=="success"){
			var str = "<p class = 'login_nickName' style = 'margin-left:5px;width:100px;'>"+jso.nickname+"</p><img id = 'Login_personImg' class = 'toUserInf'  src='"+filepath+"'>";
			$(".auth-menu").html(str);
		}else if(jso.result=="username_not_exist"){
			$(".login").css("display","block");
			alert("username_not_exist");
			Exit();
		}else if(jso.result=="password_error"){
			$(".login").css("display","block");
			alert("password_error");
			Exit();
		}
	}
});
function CheckImgExists(imgurl) {  
    var ImgObj = new Image(); //判断图片是否存在  
    ImgObj.src = imgurl;  
    //没有图片，则返回-1  
    if (ImgObj.fileSize > 0 || (ImgObj.width > 0 && ImgObj.height > 0)) {  
        return true;  
    } else {  
        return false;
    }  
}
function Login(device,username,password){
	var test = null;
	 $.ajax({   
     	type:"POST", //请求方式  
         url:"./LoginServlet", //请求路径  
         async:false,
         cache: false,   
         data:"device="+device+"&username="+username+"&password="+password,  //传参
      });  
}
$(function(){
	$('.toUserInf ').hover(
		function(){
			$('.main_userAll').css("display","block");
		},
		function(){
			$('.main_userAll').css("display","none");
		}
	);
});
function FindPwd(){
	$(".title_color").html("找回密码");
	var Find_pwdStr="<input type='email' id = 'Find_pwdEmail' name='Find_pwdEmail' required placeholder='邮箱地址' class='ipt' style='margin-left:45px;margin-top:20px;'/>"+
					"<input type='submit' value='找回密码' id='Find_pwdBtn' style = 'margin-left:50px;'/>";
	var Login_str="<form action='LoginServlet' method='post' id='editForm'>"+
							"<ul class='loginInfos'>"+
								"<li><label><input type='email' id = 'Login_mail' name='username' required placeholder='邮箱地址' class='ipt' /></label></li>"+
								"<li><label><input type='password' id = 'Login_pwd' name='password' required placeholder='密码' class='ipt' /></label></li>"+
								"<li><label><input type='hidden' id = 'device' name='device' value='web'  class='ipt' /></label></li>"+
								"<li>"+
									"<input type='submit'value='确认登录' class='submitBtn' id = 'Login_submitBtn' />"+
									"<a class='Find_pwd' onclick='FindPwd()'>找回密码</a>"+
								"</li>"+
							"</ul>"+
						"</form>";
	$(".Login_div").html(Find_pwdStr);
	$("#change-img").click(function(){
		setTimeout(function() {
			$(".title_color").html("登录上课啦");
			$(".Login_div").html(Login_str);
		}, 2000);
	});
	$("#Find_pwdBtn").click(function(){
		var email=$("#Find_pwdEmail").val();
		if($("#Find_pwdEmail").val()!=""){
			if(isEmail(email)){
				$(".title_color").html("登录上课啦");
				$(".Login_div").html(Login_str);
				Find_Password(email);	
			}else{
				alert("邮箱格式有误");
			}
		}
	});
}
function Find_Password(email){
	var result=null;
	 $.ajax({   
	    	type:"POST", //请求方式  
	        url:"./FindPasswordServlet", //请求路径  
	        async:false,
	        cache: false,   
	        data:"email="+email+"&type=getUserInf",  //传参  
	        dataType: 'json',   //返回值类型  
	        success:function(msg){        
	        	result=eval(msg);
	        	//alert(JSON.stringify(msg));
	        },  
	        error:function(){  	
	        	alert("error");  
	        }  
	     }); 
	 return result;
}
function Exit(){
	 $.ajax({   
	    	type:"POST", //请求方式  
	        url:"./Exit", //请求路径  
	        async:false,
	        cache: false,   
	     }); 
}
</script>
<div id="loginBg"></div>
	<div id="login" class="logined">
		<div class="loginTop" >
			<font class = "title_color" style="margin-right: 50px;">登录上课啦</font>
			<a href="javascript:;" class="loginBtn">
				<img id = "change-img" src ="images/close.png">
			</a>
		</div>
		<div class = "Login_div">
			<form action="LoginServlet" method="post" id="editForm">
				<ul class="loginInfos">
					<li><label><input type="email" id = "Login_mail" name="username" required placeholder="邮箱地址" class="ipt" /></label></li>
					<li><label><input type="password" id = "Login_pwd" name="password"  required placeholder="密码"  class="ipt" /></label></li>
					<li><label><input type="hidden" id = "device" name="device" value="web"  class="ipt" /></label></li>
					<li>
						<input type="submit"value="确认登录" class="submitBtn" id = "Login_submitBtn" />
						<a class="Find_pwd" onclick="FindPwd()">找回密码</a>
					</li>
				</ul>
			</form>
		</div>
	</div>
</body>
</html>