<%-- 
    Document   : ShowResults
    Created on : Oct 14, 2016, 1:08:08 PM
    Author     : phuphanmbp
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Results</h1>
        <% 
            
            Integer sessionCount = (Integer)session.getAttribute("sessionCount");
            ResultSet resultSet = (ResultSet)request.getAttribute("results");
            while (resultSet.next()){
                out.println(resultSet.getString("musictype")+ " has " + resultSet.getString("numvotes")+" votes</br>");
            }
            out.println("My total votes: "+ sessionCount);
        %>
   
    </body>
</html>
