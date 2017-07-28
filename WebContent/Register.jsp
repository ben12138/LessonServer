<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
$(function(){
	$(".verificationBtn").click(function(){
		var mail = $("#register_mail").val();
		var pwd = $("#register_pwd").val();
		if(isEmail(mail)){
			var relyzm = GetYZM(mail).yanzhengma;
			if(relyzm=="registered"){
				alert("用户已存在");
			}else{
				verification();
				$("#register_submitBtn").click(function(){
					var yzm = $("#register_yzm").val();
					if(yzm==relyzm){
						if(addUser("web",mail,pwd).result=="success"){
							Login("web",mail,pwd);
							//alert("login");
						}
					}else{
						alert("验证码错误");
					}
				});
			}
		}else{
			alert("邮箱格式错误");
		}
	});
});
function isEmail(mail){
	var reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
	return reg.test(mail);
}
function GetYZM(mail){   
	var YZM = null;
    $.ajax({   
    	type:"GET", //请求方式  
        url:"./RegisterServlet", //请求路径  
        async:false,
        cache: false,   
        data:"type=step1&email="+mail,  //传参  
        dataType: 'json',   //返回值类型  
        success:function(msg){        
        	YZM = eval(msg);
        },  
        error:function(){  	
        	alert("error");  
        }  
     });  
    return YZM;
 }
 function addUser(device,mail,pwd){
	 var result = null;
	 var date = new Date();
	 var str = date.getFullYear()+"年"+date.getMonth()+"月"+date.getDay()+"日";
	 $.ajax({   
	    	type:"POST", //请求方式  
	        url:"./RegisterServlet", //请求路径  
	        async:false,
	        cache: false,   
	        data:"email="+mail+"&password="+pwd+"&name="+mail+"&onlinedevice="+device+"&sex=0&birthday="+str,  //传参  
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
</script>

<div id="registerBg"></div>
				<div id="register" class="registered">
					<div class="registerTop" >
						<font class = "title_color">注册上课啦</font>
						<a href="javascript:;" class="registerBtn">
							<img id = "change-img" src ="images/close.png">
						</a>
					</div>
					<form action="" method="post" id="editForm">
						<ul class="registerInfos"	>
							<li><label><input type="email" name=""  placeholder="邮箱地址" required class="ipt" id = "register_mail"/></label></li>
							<li><label><input type="password" name=""   placeholder="密码"class="ipt"  id = "register_pwd" required /></label></li>
							<li>
								<label>
									<input type = "text" name = "" class = "ipt" style = "width:100px;" id = "register_yzm" required/>
									<input type = "button" value = "验证码" class = "verificationBtn" style = "width:100px;"/> 
								</label>
							</li>
							<li><input type="submit" value="确认注册" class="submitBtn" id = "register_submitBtn"/></li>
						</ul>
					</form>
				</div>
</body>
</html>