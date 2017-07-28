<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="build-in js/jquery.min.js" type="text/javascript"></script>
<title>主页</title>
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
        	var numType = 0;//课程类别计数
           	var numName;//推荐课程名字计数
           	var numImage;//推荐课程图片计数
           	var numTeacher;//推荐课程教师计数
           	var courseType = new Array();//推荐课程类别
           	var courseName = new Array();//推荐课程名字
           	var courseImage = new Array();//推荐课程图片
           	var courseTeacher = new Array();//推荐课程教师
           	for(var i=0;i<jsObj.length;i++){
           		for(var key in jsObj[i]){
           			if(key=="coursetype1"){
           				courseType[numType]=jsObj[i].coursetype1;
           				courseName[numType]=new Array();
           				courseImage[numType]=new Array();
           				courseTeacher[numType]=new Array();
           				numName=0;
           				numImage=0;
           				numTeacher=0;
           				numType++;
           			}else if(key=="coursename"){
           				courseName[numType-1][numName]=jsObj[i].coursename;
           				numName++;
           			}else if(key=="androidimage"){
           				courseImage[numType-1][numImage]=jsObj[i].androidimage;
           				numImage++;
           			}else if(key=="teacherid"){
           				courseTeacher[numType-1][numTeacher]=jsObj[i].teacherid;
           				numTeacher++;
           			}
           		}
           	}
           	for(var i=0;i<numType;i++){
           		str += "<div class ='tuisong_title'><h2>"+courseType[i]+
           		"</h2></div>"+
           		"<div class = 'tuisong_firstdiv'><div class = 'tuisong_seconddiv'>";
           		for(var j=0;j<numName;j++){
           			str += 
           			"<div class = 'tuisong_thriddiv'>"+
           				"<a href = '?lessonName="+courseName[i][j]+"'>"+
           					"<div class = 'tuisong_forthdiv'>"+
           						"<img src='"+courseImage[i][j]+"'>"+
           					"</div>"+
           					"<div class = 'tuisong_lessonName'>"+
           						"<h3>"+courseName[i][j]+"</h3>"+
           					"</div>"+
           					"<div class = 'tuisong_teacherName'>"+
           						searchTeacherName(courseTeacher[i][j]).teachername+
           					"</div>"+
           				"</a>"+
           			"</div>";
           		}
           		str +="</div></div>";
           	}
           
          	$(".tuisong_lesson").html(str);
           },  
           error:function(){  	
           	alert("error");  
           }  
        });  
    });
    //获取教师信息
    function searchTeacherName(id){
    	var name = null;
    	$.ajax({
			type:"POST", //请求方式  
	        url:"./GetTeacherInfServlet", //请求路径 
	        async:false,
	        cache: false,   
	        data:"id="+id,  //传参  
	        dataType: 'json',   //返回值类型  
	        success:function(msg){
	        	name = eval(msg);
	        },  
	        error:function(){  	
	        	alert("error");  
	        }  
		});
    	return name;
    }
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
		
	</div>
</body>
</html>