<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="build-in js/jquery.min.js" type="text/javascript"></script>
<title>所有课程类型列举</title>
</head>
<body>
	<script type="text/javascript">
	$(function(){     
		var jsObj = getCourse("phone","allcoursename");
		var num = 0;					//一级菜单id计数
    	var x = 0;						//二三级菜单分类计数
    	var str = new Array();		//二三级菜单
    	var str1 = "";					//一级菜单
    	var type1 = "";
    	var type2 = "";
    	str[x] = "";
    	for(var i=0;i<jsObj.length;i++){
    		for(var key in jsObj[i]){
    			if(key=="coursetype1"){
    				type1 = jsObj[i].coursetype1;
    				str1 +="<li><a href='?coursetype1="+jsObj[i].coursetype1+"' id=firstLesson"+(num++)+">"+jsObj[i].coursetype1+"</a></li>"; 
    			}else if(key=="coursetype2"){
    				type2 = jsObj[i].coursetype2;
    				str[x] +="<dt><a href = '?coursetype1="+type1+"&coursetype2="+jsObj[i].coursetype2+"'>"+jsObj[i].coursetype2+"</dt>";
    				for(var key in jsObj[i+1]){
        				if(key=="coursetype3"){
        					str[x]+="<dd>";
        				}
        			}
    			}else if(key=="coursetype3"){
    				str[x] +="<a href = '?coursetype1="+type1+"&coursetype2="+type2+"&coursetype3="+jsObj[i].coursetype3+"''>"+jsObj[i].coursetype3+"</a>";
    				for(var key in jsObj[i+1]){
    					if(key=="coursetype2"){
        					str[x]+="</dd>";
        				}
    					if(key=="coursetype1"){
    						x++;
    						str[x] = "";
    					}
    				}
    			}
    		}
    	}
    	str[x]=str[x]+"</dd>";
    	$(".submenu").html(str1);
   		for(var i = 0;i<str.length;i++){
   			LessonType_mouseOver(i,str[i]);
   		}
    } );
	</script>
	<div class = "john">
		<ul class="submenu">
		</ul>
		<div class = "childflright" style = "opacity:0.8;">
			<dl class="flright">
			</dl>
		</div>
	</div>
</body>
</html>