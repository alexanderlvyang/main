 <%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	
		
		<frameset cols="12%,88%" border="1 green">
			<frame src="left.do" noresize="noresize"/>
			<frame src="right.do" name="right"/>
		</frameset>
		
	
</html>
