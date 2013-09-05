<%-- 
    Document   : result
    Created on : Sep 4, 2013, 7:50:57 PM
    Author     : jrankin2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculator Results</title>
    </head>
    <body>
        <h1>Calculator Results</h1>
        Result: <% out.print(request.getAttribute("result")); %>
        <br/>
    </body>
</html>
