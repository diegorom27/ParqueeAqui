<%@page import="java.util.ArrayList"%>
<%@page import="modelo.logica.Servicio"%>
<%@page import="modelo.logica.Servicio"%>
<%
ArrayList<Servicio> a = (ArrayList<Servicio>) session.getAttribute("count"); 
int i =0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>
        <%while(a.size()>i){ %> 
        <h1>Id: <%=a.get(i).getK_idservicio()%> Fecha de entrada: <%=a.get(i).getF_fycentrada()%> Fecha de entrada: <%=a.get(i).getF_fycsalida()%>   </h1>
        <% i++;
            } %>
    </body>
</html>
