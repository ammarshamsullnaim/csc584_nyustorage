package courier;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CourierAdd")
public class CourierAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourierModel CourierModel;

    public CourierAdd() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // Obtain data from the form
        String courier_id = request.getParameter("courier_id");
        String courier_name = request.getParameter("courier_name");

        System.out.println("Received data from form: courier_id=" + courier_id + ", courier_name=" + courier_name);

        try {
            // Initialize the userModel object
        	CourierModel = new CourierModel();

            // Create a User object
            Courier user = new Courier(courier_id, courier_name);
            CourierModel.addCourier(user);

            // User registration successful
            response.sendRedirect("courierView.jsp");
        } catch (Exception e) {
            e.printStackTrace();

            // User registration failed
            response.sendRedirect("courierView.jsp");
        }
    }

}