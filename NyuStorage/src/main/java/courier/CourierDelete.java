package courier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CourierDelete")
public class CourierDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourierModel CourierModel;

    public CourierDelete() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // Obtain the user ID to delete
        String courier_id = request.getParameter("courier_id");

        try {
        	CourierModel = new CourierModel();
        	CourierModel.deleteCourierByID(courier_id);
            response.sendRedirect("courierView.jsp"); // Redirect to the user view page after deleting a user
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error deleting Courier. Please try again.");
        }
    }
}