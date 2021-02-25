<%-- 
    Document   : estadisticaF
    Created on : 25/02/2021, 10:50:20 AM
    Author     : diego
--%>
<%
int a = (int) session.getAttribute("count"); 
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=a%></h1>
    </body>
</html>
