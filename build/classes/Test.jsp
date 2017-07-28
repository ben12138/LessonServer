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
$(function(){         
       $.ajax({   
       	type:"POST", //请求方式  
           url:"./GetCourseInf", //请求路径  
           cache: false,   
           data:"type=phone&getInf=tuisong",  //传参  
           dataType: 'json',   //返回值类型  
           success:function(msg){        
           	var jsObj = eval(msg);
           	var str  = "";
           	//alert(JSON.stringify(msg));
           	for(var i=0;i<jsObj.length;i++){
           		for(var key in jsObj[i]){
           			if(key=="coursetype1"){
           				//str += "<p style = 'color:red'>"+jsObj[i].coursetype1+"</p>";
           			}
           		}
           	}
           //	$(".tuisong_lesson").html(str);
           },  
           error:function(){  	
           	alert("error");  
           }  
        });  
    });
</script>
	<div class="change1_pic" >
		<ul>
			<li><img src="images/1.jpg" /></li>
			<li><img src="images/2.png"  /></li>
			<li><img src="images/3.jpg"  /></li>
			<li><img src="images/4.jpg"  /></li>
			<li><img src="images/5.jpg"  /></li>
			<li><img src="images/6.jpg"  /></li>
			<li><img src="images/7.jpg"  /></li>
		</ul>
	</div>
	<div class = "tuisong_lesson">
		<div class = "tuisong_title">
			<h2>编程开发</h2>
		</div>
		<div class = "tuisong_firstdiv">
			<div class = "tuisong_seconddiv" >
				<div class = "tuisong_thriddiv">
					<a>
						<div class = "tuisong_forthdiv">
							<img alt="" src="images/childLesson.png">
						</div>
						<div class = "tuisong_lessonName">
							<h3>c语言</h3>
						</div>
						<div class = "tuisong_teacherName">
							<h4>顾鹏</h4>
						</div>
					</a>
				</div>
				<div class = "tuisong_thriddiv">
					<a>
						<div class = "tuisong_forthdiv">
							<img alt="" src="images/childLesson.png">
						</div>
					</a>
				</div>
				<div class = "tuisong_thriddiv">
					<a>
						<div class = "tuisong_forthdiv">
							<img alt="" src="images/childLesson.png">
						</div>
					</a>
				</div>
				<div class = "tuisong_thriddiv">
					<a>
						<div class = "tuisong_forthdiv">
							<img alt="" src="images/childLesson.png">
						</div>
					</a>
				</div>
			</div>
		</div>
		<div class = "tuisong_title">
			<h2>编程开发</h2>
		</div>
		<div style = "margin-bottom:40px;">
			<div style = "width:1160px;height:410px;background-color:red;" >
				<div>
					<a>
						<div>
						
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>