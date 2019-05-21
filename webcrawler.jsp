<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.util.*"%>
<%@page import="com.company.CrawlerServlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crawler</title>
</head>
<body>
<form action="CrawlerServlet" method="post">
			<table style="with: 50%">
				<tr>
					<td>Web URL</td>
					<td><input type="text" name="url_form" /></td>
				</tr>
				
				</table>
			<input type="submit" value="Submit" /></form>
			
<c:forEach items="${webout}" var="result">       
    <tr>
        <td>${result}</td>   
    </tr>
</c:forEach>
</body>
</html>