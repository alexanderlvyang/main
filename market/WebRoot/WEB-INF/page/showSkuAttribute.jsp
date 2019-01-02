<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showSkuAttribute.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table>
    	<thead>
    	<%@page import="javabean.SkuAttribute" %>
    	<%
    		List<String> attributeNameList=(List<String>)request.getAttribute("attributeNameList");
    		int attributeNameListSize=(int)request.getAttribute("attributeNameListSize");
			for(int i=0;i<attributeNameListSize;i++){
    	 %>
    	  	<th><%=attributeNameList.get(i) %></th>
    	 <%} %>
    	 </thead>
    	 <tbody>
    	 <%
    	 	List<SkuAttribute> attributeList=(List<SkuAttribute>)request.getAttribute("attributeList");
    		int attributeListSize=(int)request.getAttribute("attributeListSize");
			for(int i=0;i<attributeListSize/attributeNameListSize;i++){
    	  %>
    	 	<tr>
	    		<% for(int j=i*attributeNameListSize;j<attributeNameListSize*(i+1);j++){%>
	    		<td><%=attributeList.get(j).getAttributeValue() %></td>
	    		
	    	<%} %>
	    	<td><a>编辑</a></td>
	    	</tr>
	    	<%} %>
    	</tbody>
    </table>
    <a href='addSpecification.do?productId=<%=request.getAttribute("productId")%>'>添加属性</a>
  </body>
</html>
