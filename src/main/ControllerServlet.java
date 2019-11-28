package main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String xString = request.getParameter("x");
        String yString = request.getParameter("y").replace(",",".");
        String rString = request.getParameter("r");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        if(xString.equals("no") || yString.equals("") || rString.equals("no")){
            request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } else {
            try {
                Double.parseDouble(xString);
                Double.parseDouble(yString);
                Double.parseDouble(rString);
                request.getServletContext().getRequestDispatcher("/WEB-INF/checking").forward(request, response);
            } catch (Exception e){
                System.out.println(e.getMessage());
                request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }
        }
        response.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
