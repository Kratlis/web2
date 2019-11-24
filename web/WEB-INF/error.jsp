<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.ArrayList" %>
<%-- page import="Point" --%>
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


%>
<body>

<%--<script>--%>
<%--    document.formname.action="JspPage";--%>
<%--    document.formname.submit;--%>
<%--</script>--%>
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
<p>
    <%--"here"+new Point().toString()--%>
</p>
</body>
</html>
