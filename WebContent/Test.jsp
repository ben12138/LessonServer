<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="build-in js/jquery.min.js" type="text/javascript">
</script>
<script type="text/javascript">
function fun(url){
	var realpath= window.URL.createObjectURL(document.getElementById('path').files[0]);
	alert(realpath);
}
</script>
<title>Insert title here</title>
</head>
<body>
<input type="file" id = "path" onchange="fun(this.value)">
</body>
</html>
