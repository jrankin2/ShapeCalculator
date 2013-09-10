package controllers;

import static controllers.Shapes.CIRCLE;
import static controllers.Shapes.RECTANGLE;
import static controllers.Shapes.TRIANGLE;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Joe
 */
public class ShapeCalculatorService {
    private static final double NULL_VALUE = -1;
    
    private static final String WIDTH_PARAM = "width";
    private static final String LENGTH_PARAM = "length";
    private static final String RADIUS_PARAM = "radius";
    private static final String A_PARAM = "a";
    private static final String B_PARAM = "b";
    private static final String C_PARAM = "c";
    
    
    public static double calculateFromRequest(HttpServletRequest request){
        double result = -1;
        String sParam = request.getParameter("s");
        if (sParam != null) {
            Shapes shape = Shapes.toShape(Integer.parseInt(sParam));
            if (shape == null) {
                //invalid s value
            }
            switch (shape) {
                case RECTANGLE:
                    double width = getParameterDouble(request, WIDTH_PARAM);
                    double length = getParameterDouble(request, LENGTH_PARAM);
                    //double width = Double.parseDouble(request.getParameter("width"));
                    //double length = Double.parseDouble(request.getParameter("length"));
                    if (width != NULL_VALUE && length != NULL_VALUE) {
                        result = width * length;
                    } else {
                        //error?
                    }

                    break;
                case CIRCLE:
                    double radius = getParameterDouble(request, RADIUS_PARAM);
                    //double radius = Double.parseDouble(request.getParameter("radius"));
                    if (radius != NULL_VALUE) {
                        result = Math.PI * Math.pow(radius, 2);
                    } else {
                        //error
                    }
                    break;
                case TRIANGLE:
                    double a = getParameterDouble(request, A_PARAM);
                    double b = getParameterDouble(request, B_PARAM);
                    double c = getParameterDouble(request, C_PARAM);
                    
                    if (c == NULL_VALUE) {
                        result = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                    } else {
                        if (a != NULL_VALUE) {
                            result = Math.sqrt(Math.pow(c, 2) - Math.pow(a, 2));
                        } else if (b != NULL_VALUE) {
                            result = Math.sqrt(Math.pow(c, 2) - Math.pow(b, 2));
                        } else {
                            //error
                        }
                    }
                    break;
            }

            
        }
        return result;
    }
    
    private static double getParameterDouble(HttpServletRequest request, String name) {
        String nameValue = request.getParameter(name).trim();
        return (nameValue != null && !nameValue.isEmpty()
                ? Double.parseDouble(request.getParameter(name))
                : NULL_VALUE);
    }
}
