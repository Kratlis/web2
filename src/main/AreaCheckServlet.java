package main;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@WebServlet(name = "main.AreaCheckServlet", urlPatterns = "/WEB-INF/checking")
public class AreaCheckServlet extends HttpServlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) {
        this.config = config;
    }
    @Override
    public void destroy() {}
    @Override
    public ServletConfig getServletConfig()
    {
        return config;
    }

    private String drawTable(ArrayList<Point> list){
        Collections.reverse(list);
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
            stringBuilder.append((point.isInArea()) ? "Попала" : "Не попала").append("</td><td>")
                    .append(point.getTime());
            stringBuilder.append("</td></tr>");
        }

        stringBuilder.append("</table>");
        return String.valueOf(stringBuilder);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Point> list = (ArrayList<Point>) request.getSession().getAttribute("list");
        if (list == null){
            list = new ArrayList<>();
            request.getSession().setAttribute("list", list);
        }
        try{
            System.out.println("date: "+new Date());
            Point p = new Point(Double.parseDouble(request.getParameter("x")), Double.parseDouble(request.getParameter("y")),
                    Double.parseDouble(request.getParameter("r")), new Date());
            list.add(p);
        } catch (Exception e) {
            System.err.println("ACS: POINT");
            request.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
        }

        response.setContentType("text/html");
        request.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);//        PrintWriter out = response.getWriter();
//        out.println("<!DOCTYPE HTML> " +
//                "<html lang='ru'> " +
//                "<head> " +
//                "<meta charset='UTF-8'> " +
//                "<style>" +
//                ".warning{" +
//                "            color: firebrick;" +
//                "        }" +
//                "body{" +
//                "    height: 100%;" +
//                "}" +
//                "table{" +
//                "    width: 100%;" +
//                "    font-family: Verdana, sans-serif;" +
//                "}" +
//                "th{" +
//                "    font-weight: lighter;" +
//                "    height: 30px;" +
//                "    vertical-align: bottom;" +
//                "}" +
//                ".coordinate{" +
//                "    font-family: Verdana, sans-serif;\n" +
//                "}" +
//                "\n" +
//                ".response{" +
//                "    background-color: wheat;" +
//                "}" +
//                "</style>" +
//                "</head> " +
//                "<body>");
//
//        out.println(drawTable(list));
//        out.println("</body>" +
//                "</html>");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("control");
    }
}
