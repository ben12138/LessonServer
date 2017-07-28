<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel = "stylesheet" type = "text/css" href = "css/UserInf.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
var filepath=jso.headimage;
window.onload=function(){
	var str = "";
	if(jso!=null){
		var filepath = jso.headimage;
		if(!CheckImgExists(filepath)){
			//filepath = "images/person.jpg";
		}
	}
	str += "<table class = 'UserInf_table'>"+
				"<tbody class = 'UserInf_tbody'>"+
					"<tr class = 'UserInf_normalTr'>"+
						"<td class='UserInf_normalTitle'>昵称"+
						"</td>"+
						"<td>"+
							"<div class = 'UserInf_normalInputDiv'>"+
								"<input id = 'UserInf_nickname' class = 'UserInf_normalInput' value='"+jso.nickname+"'>"+
							"</div>"+
						"</td>"+
					"</tr>"+
					"<tr class = 'UserInf_normalTr'>"+
						"<td class='UserInf_normalTitle'>邮箱"+
						"</td>"+
						"<td>"+
							"<div class = 'UserInf_normalInputDiv'>"+
								"<input id = 'UserInf_email' class = 'UserInf_normalInput' readonly='readonly'value='"+jso.email+"'>"+
							"</div>"+
						"</td>"+
					"</tr>"+
					"<tr class = 'UserInf_normalTr'>"+
						"<td class='UserInf_normalTitle'>头像"+
						"</td>"+
						"<td>"+
							//"<img src='"+filepath+"' id='upfile' style='width: 100px;height: 100px;'>"+
							//"<input type='button' value='修改头像' onclick='path.click()' style='border:1px solid #ccc;width: 100px;height: 30px;margin-left: 30px;'>  "+
							//"<input type='file' id='path' style='display:none' onchange='upfile.value=this.value'>"+
							"<form action='upLoadFile' enctype='multipart/form-data'  id = 'form1' method='post'>"+
								"<img src='"+filepath+"' id='upfile' style='width: 100px;height: 100px;'>"+
								"<input type='button' value='修改头像' onclick='path.click()' style='border:1px solid #ccc;width: 100px;height: 30px;margin-left: 30px;'>  "+
								"<input type='hidden' name='uemail' value='"+jso.email+"'>"+
								"<input type='file' id='path' name='headimage' style='display:none;'onchange='UserImageChange(this.value)'>"+
							"</form>"+
							//"<input type = 'file' id='testPath' onchange='UserImageChange(this.value)'>"+
						"</td>"+
					"</tr>"+
					"<tr class = 'UserInf_normalTr'>"+
						"<td class='UserInf_normalTitle'>性别"+
						"</td>"+
						"<td>"+ 
							"<input type='radio' name='sex' value='0'/>男"+
							"<input type='radio' name='sex' value='1'/>女"+
						"</td>"+
					"</tr>"+
					"<tr class = 'UserInf_normalTr'>"+
						"<td class='UserInf_normalTitle'>生日"+
						"</td>"+
						"<td>"+
							"<div class = 'UserInf_normalInputDiv'>"+
								"<input type='text' class = 'UserInf_normalInput' id = 'date' readonly='readonly'value='"+jso.birthday+"'>"+
							"</div>"+
						"</td>"+
					"</tr>"+
					"<tr class = 'UserInf_normalTr'>"+
						"<td class='UserInf_normalTitle'>介绍"+
						"</td>"+
						"<td>"+
							"<textarea id = 'UserInf_introduction' cols='30' rows='3'>"+jso.introduction+"</textarea>"+
						"</td>"+
					"</tr>"+
					"<tr class = 'UserInf_normalTr'>"+
						"<td class='UserInf_normalTitle'>学校"+
						"</td>"+
						"<td>"+
						"<div class = 'UserInf_normalInputDiv'>"+
							"<input id = 'UserInf_school' class = 'UserInf_normalInput'value='"+jso.school+"'>"+
						"</div>"+
						"</td>"+
					"</tr>"+
					"<tr class = 'UserInf_normalTr'>"+
						"<td style = 'width:20px;'>"+
						"</td>"+
						"<td class = 'UserInf_normalTitle'>"+
							"<input type='button' value='提交' class = 'UserInf_submmit'/>"+
						"</td>"+
					"</tr>"+
				"</tbody>"+
			"</table>";
	$(".UserInf_div").html(str);
	if(jso.sex==0){
		document.getElementsByName("sex")[0].checked="checked";
	}else if(jso.sex==1){
		document.getElementsByName("sex")[1].checked="checked";
	}
	$(".UserInf_submmit").click(function(){
		var nickname = document.getElementById("UserInf_nickname").value;
		var email = document.getElementById("UserInf_email").value;
		var sex =  $("input[@type=radio][name=sex][checked]").val();
		var birthday = document.getElementById("date").value;
		var introduction = document.getElementById("UserInf_introduction").value;
		var school = document.getElementById("UserInf_school").value;
		//var file = document.getElementById("path").files[0];
		document.getElementById("form1").submit();
		upDateUserInf(nickname,email, sex, birthday, introduction, school);
		Login("web",email,localStorage.getItem("password"));
		window.location.href="http://localhost:8080/LessonServer/main.jsp?page=user";
	});
	$("#date").jeDate({
	    isinitVal:true,
	    festival:false,
	    ishmsVal:false,
	    minDate: '1800-06-16 23:59:59',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD",
	    zIndex:3000,
	});
	
}
function UserImageChange(url){
	var realpath=getRealPath(document.getElementById("upfile"));
	alert(realpath);
	document.getElementById("upfile").src=realpath;
}	
function upDateUserInf(nickname,email,sex,birthday,introduction,school){
	 $.ajax({   
     	type:"POST", //请求方式  
         url:"./UpDateUserInfServlet", //请求路径  
         async:false,
         cache: false,   
         data:"nickname="+nickname+"&email="+email+"&sex="+sex+"&birthday="+birthday+"&introduction="+introduction+"&school="+school,  //传参  
         dataType: 'json',   //返回值类型  
         success:function(msg){
         },  
         error:function(){  	
         }  
      });
}
/*window.onload=function(){
	$("#date").jeDate({
	    isinitVal:true,
	    festival:false,
	    ishmsVal:false,
	    minDate: '1800-06-16 23:59:59',
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD",
	    zIndex:3000,
	})
}*/
function upload(){
	document.getElementById("form1").submit();
}
</script>

<div class = "UserInf_div">
	<table class = "UserInf_table">
		<tbody class = "UserInf_tbody">
		
			<tr class = "UserInf_normalTr">
				<td class="UserInf_normalTitle">
					昵称
				</td>
				<td>
					<div class = "UserInf_normalInputDiv">
						<input class = "UserInf_normalInput">
					</div>
				</td>
			</tr>
			
			<tr class = "UserInf_normalTr">
				<td class="UserInf_normalTitle">
					邮箱
				</td>
				<td>
					<div class = "UserInf_normalInputDiv">
						<input class = "UserInf_normalInput">
					</div>
				</td>
			</tr>
			<tr class = "UserInf_normalTr">
				<td class="UserInf_normalTitle">
					头像
				</td>
				<td>
					<form action="upLoadFile"enctype="multipart/form-data"  id = "form1"method="post">
						<!--img alt="" src="images/person.jpg" id="upfile" style="width: 100px;height: 100px;">
						<input type="button" value="修改头像" onclick="path.click()" style="border:1px solid #ccc;width: 100px;height: 30px;margin-left: 30px;"-->  
						<input type="file" name= 'jick'/>
					</form>
				</td>
			</tr>
			<tr class = "UserInf_normalTr">
				<td class="UserInf_normalTitle">
					性别
				</td>
				<td>
					<input type="radio" name="sex" value="man" checked="checked"/>男
					<input type="radio" name="sex" value="woman" style="margin-left: 20px;"/>女
				</td>
			</tr>
			<tr class = "UserInf_normalTr">
				<td class="UserInf_normalTitle">
					生日
				</td>
				<td>
					<div class = "UserInf_normalInputDiv">
						<input type="text" class = "UserInf_normalInput" id = "date" readonly="readonly">
					</div>
				</td>
			</tr>
			<tr class = "UserInf_normalTr">
				<td class="UserInf_normalTitle">
					介绍
				</td>
				<td>
					<textarea cols="30" rows="3"></textarea>
				</td>
			</tr>
			<tr class = "UserInf_normalTr">
				<td class="UserInf_normalTitle">
					学校
				</td>
				<td>
					<select name="school">
						<option>请选择学校</option>
						<option>金陵科技学院</option>
						<option>晓庄</option>
						<option>南理工</option>
					</select>
				</td>
			</tr>
			<tr class = "UserInf_normalTr">
				<td style="width: 20px;">
				</td>
				<td class="UserInf_normalTitle">
					<input type="button" value="提交" onclick="upload()" style="width: 120px;height: 40px;font-size: 20px;font-weight: bold;background-color: #3AD7FC;"/>
				</td>
			</tr>
		</tbody>
	</table>
</div>

</body>
<script src ="build-in js/jquery.min.js"></script>
</html>