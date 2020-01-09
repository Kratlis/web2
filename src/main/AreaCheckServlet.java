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
        request.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("control");
    }
}
