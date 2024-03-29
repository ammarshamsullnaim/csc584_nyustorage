package parcel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ParcelDelete")
public class ParcelDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ParcelModel parcelModel;

    public ParcelDelete() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // Obtain the parcel tracking number to delete
        String tracking_num = request.getParameter("tracking_num");

        try {
            parcelModel = new ParcelModel();
            parcelModel.deleteParcelByTrackingNumber(tracking_num);
            response.sendRedirect("parcelView.jsp"); // Redirect to the parcel view page after deleting a parcel
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error deleting Parcel. Please try again.");
        }
    }
}
