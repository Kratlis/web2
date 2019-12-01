<%@ page import="java.util.ArrayList" %>
<%@ page import="main.Point" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<% response.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <style>
        .warning{
            color: firebrick;
        }
        .response{
            background-color: wheat;
        }
        table{
            width: 100%;
            font-family: Verdana, sans-serif;

        }
    </style>
</head>
<body>
<%
    ArrayList<String> errorList = (ArrayList<String>) request.getAttribute("errorList");
    if (errorList != null){
        for (String message: errorList){
%>
<p class="warning"><%=message%></p>
<% }} %>
<%
    ArrayList<Point> list = (ArrayList<Point>) request.getSession().getAttribute("list");
        if (list != null){
            Collections.reverse(list);
            %>
<table class='response' align='center'>
    <thead>
    <tr align='center'>
        <th> <h5>Х</h5></th>
        <th> <h5>Y</h5></th>
        <th> <h5>R</h5></th>
        <th> <h5>Результат</h5></th>
        <th> <h5>Время</h5></th>
    </tr>
    </thead>
    <% for (Point point: list) {%>
    <tr align='center'>
        <td><%= point.getX()%></td>
        <td><%=point.getY()%></td>
        <td><%=point.getR()%></td>
        <td><%= (point.isInArea()) ? "Попала" : "Не попала"%></td>
        <td><%=point.getTime()%></td>
    </tr>
</table>
<% }} %>
</body>
</html>
