package shelf;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShelfDelete")
public class ShelfDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShelfModel ShelfModel;

    public ShelfDelete() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // Obtain the user ID to delete
        int shelf_id = Integer.parseInt(request.getParameter("shelf_id"));

        try {
        	ShelfModel = new ShelfModel();
        	ShelfModel.deleteShelfByID(shelf_id);
            response.sendRedirect("shelfView.jsp"); // Redirect to the user view page after deleting a user
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error deleting Shelf. Please try again.");
        }
    }
}