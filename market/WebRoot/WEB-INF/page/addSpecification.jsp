<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showSpecification.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		.tableBox{
			width:100%;
			border:1px solid red;
		}
		#submitButton{
			width:150px;
			height:30px;
			background-color:#e22;
			color:white;
			border:none;
			margin-left:70px;
		}
		.attributeInput{
			width:250px;
			height:30px;
			padding-left:5px;
		}
		table{
			margin-left:450px;
		}
		.labelInput{
			border:none;
			width:100px;
			background-color:white;
		}
	</style>
  </head>
  
  <body>
  <div class='tableBox'>
    <table>
    	<form action='addSpecificationOperation.do?productId=<%=request.getAttribute("productId") %>' method='post' id='form'>
    	<%@page import="javabean.CategoryAttribute" %>
    	<%
    		ArrayList<CategoryAttribute> attributeList=(ArrayList<CategoryAttribute>)request.getAttribute("attributeList");
    		int attributeListSize=(int)request.getAttribute("attributeListSize");
    		for(int i=0;i<attributeListSize;i++){
    	 %>
    	<tr class='inputBox'>
    		<td><input type='text' name='<%=attributeList.get(i).getAttributeId()%>' value='<%=attributeList.get(i).getAttributeName() %>' class='labelInput' readonly></td>
    		<td><input type='text' name='<%=attributeList.get(i).getAttributeId()%>input' class='attributeInput'></td>
    	</tr>
    	<%} %>
    	</div>
    	<tr>
    		<td colspan='2'><input type='submit' value='提交' id='submitButton'></td>
    	</tr>
    	</form>
    </table>
    </div>
  </body>
</html>
