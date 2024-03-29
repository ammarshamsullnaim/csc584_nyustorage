package shelf;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShelfUpdate")
public class ShelfUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private shelf.ShelfModel ShelfModel;

    public ShelfUpdate() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

     // Obtain data from the form
        String shelf_id = request.getParameter("shelf_id");
        String shelf_row = request.getParameter("shelf_row");
        int shelf_column = Integer.parseInt(request.getParameter("shelf_column"));

        // Create a new User object
        Shelf shelf = new Shelf(shelf_id, shelf_row, shelf_column);

        try {
        	ShelfModel = new ShelfModel();
        	ShelfModel.shelfUpdate(shelf);
            response.sendRedirect("shelfView.jsp"); // Redirect to the user view page after updating the user
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error updating user. Please try again.");
        }
    }
}