package parcel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ParcelUpdate")
public class ParcelUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ParcelModel parcelmodel;

    public ParcelUpdate() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

     // Obtain data from the form
        String tracking_num = request.getParameter("tracking_num");
        String receipient_name = request.getParameter("receipient_name");
        String tel_num = request.getParameter("tel_num");
        String received_date = request.getParameter("received_date");
        String collection_date = request.getParameter("collection_date");
        int weight = Integer.parseInt(request.getParameter("weight"));
        String remark = request.getParameter("remark");
        String userId = request.getParameter("userId");
        String shelf_id = request.getParameter("shelf_id");
        String courier_id = request.getParameter("courier_id");

        // Create a new Parcel object
        Parcel parcel = new Parcel(tracking_num, receipient_name, tel_num, received_date, collection_date,
                weight, remark, userId, shelf_id, courier_id);

        try {
        	parcelmodel = new ParcelModel();
        	parcelmodel.updateParcel(parcel);
            response.sendRedirect("parcelView.jsp"); // Redirect to the user view page after updating the user
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error updating user. Please try again.");
        }
    }
}