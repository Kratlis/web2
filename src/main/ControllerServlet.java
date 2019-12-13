package main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        if (!checkRequestValues(request)){
            request.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
        } else {
            request.getServletContext().getRequestDispatcher("/WEB-INF/checking").forward(request, response);
        }
        response.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void writeErrorMessage(HttpServletRequest request, String error){
        switch (error){
            case ("x parsing"):{
                addMessageInList(request, "Координата X должна быть числом.");
                break;
            }
            case ("x value"):{
                addMessageInList(request, "Введите координату X.");
                break;
            }
            case ("y parsing"):{
                addMessageInList(request, "Координата Y должна быть числом.");
                break;
            }
            case ("y value"):{
                addMessageInList(request, "Координата Y должна находиться в диапазоне от -5 до 5.");
                break;
            }
            case ("r parsing"):{
                addMessageInList(request, "Радиус R должен быть числом.");
                break;
            }
            case ("r value"):{
                addMessageInList(request, "Введите радиус R.");
                break;
            }
            default:addMessageInList(request, "Unidentified Error");
        }
    }

    private void addMessageInList(HttpServletRequest request, String error){
        ArrayList<String> list = (ArrayList<String>) request.getAttribute("errorList");
        if (list == null){
            list = new ArrayList<>();
            request.setAttribute("errorList", list);
        }
        list.add(error);
    }

    private boolean checkRequestValues(HttpServletRequest request){
        boolean xValid = checkX(request.getParameter("x"), request);
        boolean yValid = checkY(request.getParameter("y"), request);
        boolean rValid = checkR(request.getParameter("r"), request);
        return xValid && yValid && rValid;
    }

    private boolean checkX(String xString, HttpServletRequest request){
        if (xString == null || xString.equals("") || xString.equals("no")){
            writeErrorMessage(request, "x value");
            return false;
        }
        try {
            double x = Double.parseDouble(xString.replace(",","."));
        } catch (Exception e){
            writeErrorMessage(request, "x parsing");
            return false;
        }
        return true;
    }

    private boolean checkY(String yString, HttpServletRequest request){
        if (yString == null || yString.equals("")){
            writeErrorMessage(request, "y value");
            return false;
        }
        try {
            double y = Double.parseDouble(yString.replace(",","."));
            if ((y < -5.0D) || (y > 5.0D)){
                writeErrorMessage(request, "y value");
                return false;
            }
        } catch (Exception e){
            writeErrorMessage(request, "y parsing");
            return false;
        }
        return true;
    }

    private boolean checkR(String rString, HttpServletRequest request){
        if (rString == null || rString.equals("") || rString.equals("no")){
            writeErrorMessage(request, "r value");
            return false;
        }
        try {
            double r = Double.parseDouble(rString.replace(",","."));
        } catch (Exception e){
            writeErrorMessage(request, "r parsing");
            return false;
        }
        return true;
    }
}
