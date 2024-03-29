package courier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CourierUpdate")
public class CourierUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private courier.CourierModel CourierModel;

    public CourierUpdate() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

     // Obtain data from the form
        String courier_id = request.getParameter("courier_id");
        String courier_name = request.getParameter("courier_name");
    

        // Create a new User object
        Courier courier = new Courier(courier_id, courier_name);

        try {
        	CourierModel = new CourierModel();
        	CourierModel.updateCourier(courier);
            response.sendRedirect("courierView.jsp"); // Redirect to the user view page after updating the user
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error updating user. Please try again.");
        }
    }
}