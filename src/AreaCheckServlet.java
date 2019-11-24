import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@WebServlet(name = "AreaCheckServlet", urlPatterns = "/WEB-INF/checking")
public class AreaCheckServlet extends HttpServlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
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
        StringBuilder sb = new StringBuilder();
        sb.append("<table class='response' align='center'><thead><tr align='center'>\n" +
                "    <th> <h5>Х</h5></th>" +
                "    <th> <h5>Y</h5></th>" +
                "    <th> <h5>R</h5></th>" +
                "    <th> <h5>Результат</h5></th>" +
                "    <th> <h5>Время</h5></th>" +
                "    </tr></thead>");

        for (Point point: list) {
            sb.append("<tr align='center'>" + "<td>").append(point.getX()).append("</td>").append("<td>").append(point.getY()).append("</td>").append("<td>").append(point.getR()).append("</td>").append("<td>");

            if(checkArea(point.getX(), point.getY(), point.getR())){
                sb.append("Попала");
            }
            else{
                sb.append("Не попала");
            }
            sb.append("</td></tr>");
        }

        sb.append("</table>");
        return String.valueOf(sb);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Point> list = (ArrayList<Point>) request.getSession().getAttribute("list");
        if (list == null){
            list = new ArrayList<>();
            request.getSession().setAttribute("list", list);
        }
        try{
            Point p = new Point(Integer.parseInt(request.getParameter("x")), Double.parseDouble(request.getParameter("y")),
                    Double.parseDouble(request.getParameter("r")), new Date());
            list.add(p);
        } catch (Exception e) {
            System.err.println("ACS: POINT");
//            e.printStackTrace();
            request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML> " +
                "<html lang='ru'> " +
                "<head> " +
                "<meta charset='UTF-8'> " +
                "<style>" +
                ".warning{" +
                "            color: firebrick;" +
                "        }" +
                "        .response{" +
                "            background-color: wheat;" +
                "        }" +
                "body{" +
                "    height: 100%;" +
                "}" +
                "table{" +
                "    width: 100%;" +
                "    font-family: Verdana, sans-serif;" +
                "}" +
                "th{" +
                "    font-weight: lighter;" +
                "    height: 30px;" +
                "    vertical-align: bottom;" +
                "}" +
                ".coordinate{" +
                "    font-family: Verdana, sans-serif;\n" +
                "}\n" +
                "\n" +
                ".response{" +
                "    background-color: wheat;" +
                "}" +
                "</style>" +
                "</head> " +
                "<body>");
//        out.println("<div class='container' style='padding:20px 0px;'> " +
//                "<a href='index.jsp'> " +
//                "<button class='submit'> Return to HOME </button> " +
//                "</a> <br>");

        out.println(drawTable(list));
        out.println("</body>" +
                "</html>");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("control");
    }


    private static boolean checkArea(int x, double y, double R){
        if ((x >= (-R/2)) && (y >= (-x - R/2)) && (x <= 0) && (y <= 0)){
            return true;
        }
        if ((x >= 0) && (x <= R/2) && (y >= - sqrt(pow((R/2),2) - pow(x, 2)))
                && (y <= R)){
            return true;
        }
        return false;
//
//        if ((x <= 0) && (y <= 0) && ((pow(x, 2) + pow(y, 2)) <= pow(R, 2))){
//            return true;
//        }
//        if ((x <= 0) && (y >= 0) && (y <= (2*x + R))){
//            return true;
//        }
//        if ((x >= 0) && (y <= 0) && (x <= R) && (y >= -R)){
//            return true;
//        }
//        return false;
    }
}
