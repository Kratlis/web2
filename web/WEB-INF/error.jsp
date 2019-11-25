<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="main.Point" %>
<%@ page import="main.AreaCheckServlet" %>
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
    </style>
</head>
<%!
    private String checkY(String y) {
        if (y == null || y.equals("")){
            return "Введите координату Y. Координата Y должна быть числом и должна находиться в пределах от -5 до 5.";
        }
        if (!Pattern.matches("^[-+]?([0-5]([.,]\\d+)?)", y)) {
            return "Координата Y должна быть числом, которое находится в пределах от -5 до 5.";
        }
//        double yValue = Double.parseDouble(y);
//        if (yValue < -5 || yValue > 5){
//            return "Координата Y должна находиться в пределах от -5 до 5.";
//        }
        return "";
    }

    private String createTable(HttpServletRequest request){
        ArrayList<Point> list = (ArrayList<Point>) request.getSession().getAttribute("list");
        if (list == null){
            return "";
        }
        return drawTable(list);
    }

    private String drawTable(ArrayList<Point> list){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table class='response' align='center'><thead><tr align='center'>\n" +
                "    <th> <h5>Х</h5></th>" +
                "    <th> <h5>Y</h5></th>" +
                "    <th> <h5>R</h5></th>" +
                "    <th> <h5>Результат</h5></th>" +
                "    <th> <h5>Время</h5></th>" +
                "    </tr></thead>");

        for (Point point: list) {
            stringBuilder.append("<tr align='center'>" + "<td>").append(point.getX()).append("</td>")
                    .append("<td>").append(point.getY()).append("</td>")
                    .append("<td>").append(point.getR()).append("</td>")
                    .append("<td>");
//            if(point.isInArea()){
//                stringBuilder.append("Попала");
//            }
//            else{
//                stringBuilder.append("Не попала");
//            }
//            String f = (point.isInArea())?"Попала":"Не попала";
            stringBuilder.append((point.isInArea()) ? "Попала" : "Не попала").append("</td><td>")
                    .append(point.getTime());
            stringBuilder.append("</td></tr>");
        }

        stringBuilder.append("</table>");
        return String.valueOf(stringBuilder);
    }

%>
<body>
<%
    String warningX = "";
    if (request.getParameter("x") != null) {
        warningX = (request.getParameter("x").equals("no")) ? "Не выбрана координата X." : "";
    }

    String warningY = checkY(request.getParameter("y"));

    String warningR = "";
    if (request.getParameter("r") != null) {
        warningR = (request.getParameter("r").equals("no")) ? "Не выбран радиус R." : "";
    }
%>
<p class="warning"><%= warningX %></p>
<p class="warning"><%= warningY %></p>
<p class="warning"><%= warningR %></p>
<%=createTable(request)%>
</body>
</html>
