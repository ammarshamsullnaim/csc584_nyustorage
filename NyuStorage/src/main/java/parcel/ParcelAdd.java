package parcel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ParcelAdd")
public class ParcelAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ParcelModel parcelModel;

    public ParcelAdd() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            parcelModel = new ParcelModel();
            parcelModel.initJDBC();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error initializing ParcelModel", e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        parcelModel.closeJDBC();
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
        Parcel parcel = new Parcel(tracking_num, receipient_name, tel_num, received_date, collection_date, weight,
                remark, userId, shelf_id, courier_id);

        try {
            parcelModel.addParcel(parcel);
            response.sendRedirect("parcelView.jsp"); // Redirect to the parcel view page after adding the parcel
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error adding parcel. Please try again.");
        }
    }
}
