/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joe
 */
@WebServlet(name = "ShapeCalculator", urlPatterns = {"/ShapeCalculator"})
public class ShapeCalculatorController extends HttpServlet {

    private static final String RESULT_PAGE = "result.jsp";
    private static final double NULL_VALUE = -1;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            double result = getResult(request);
            request.setAttribute("result", result);
            RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
            view.forward(request, response);

        } finally {
        }
    }

    private double getParameterDouble(HttpServletRequest request, String name) {
        String nameValue = request.getParameter(name).trim();
        return (nameValue != null && !nameValue.isEmpty()
                ? Double.parseDouble(request.getParameter(name))
                : NULL_VALUE);
    }

    private double getResult(HttpServletRequest request) {
        double result = -1;
        String sParam = request.getParameter("s");
        if (sParam != null) {
            Shapes shape = Shapes.toShape(Integer.parseInt(sParam));
            if (shape == null) {
                //invalid s value
            }
            switch (shape) {
                case RECTANGLE:
                    double width = getParameterDouble(request, "width");
                    double length = getParameterDouble(request, "length");
                    //double width = Double.parseDouble(request.getParameter("width"));
                    //double length = Double.parseDouble(request.getParameter("length"));
                    if (width != NULL_VALUE && length != NULL_VALUE) {
                        result = width * length;
                    } else {
                        //error?
                    }

                    break;
                case CIRCLE:
                    double radius = getParameterDouble(request, "radius");
                    //double radius = Double.parseDouble(request.getParameter("radius"));
                    if (radius != NULL_VALUE) {
                        result = Math.PI * Math.pow(radius, 2);
                    } else {
                        //error
                    }
                    break;
                case TRIANGLE:
                    double a = getParameterDouble(request, "a");
                    double b = getParameterDouble(request, "b");
                    double c = getParameterDouble(request, "c");
                    /*
                     String aStr = request.getParameter("a");
                     String bStr = request.getParameter("b");
                     String cStr = request.getParameter("c");
                     double a = Double.parseDouble(aStr);
                     double b = Double.parseDouble(bStr);
                     double c = Double.parseDouble(cStr);
                     */
                    if (c == NULL_VALUE) {
                        result = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                    } else {
                        if (a != NULL_VALUE) {
                            result = Math.sqrt(Math.pow(a, 2) - Math.pow(c, 2));
                        } else if (b != NULL_VALUE) {
                            result = Math.sqrt(Math.pow(b, 2) - Math.pow(c, 2));
                        } else {
                            //error
                        }
                    }
                    break;
            }

            
        }
        return result;
    }



        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        /**
         * Handles the HTTP
         * <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response
        )
            throws ServletException
        , IOException {
            processRequest(request, response);
        }

        /**
         * Handles the HTTP
         * <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response
        )
            throws ServletException
        , IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            
            () {
        return "Short description";
        }// </editor-fold>
    }
