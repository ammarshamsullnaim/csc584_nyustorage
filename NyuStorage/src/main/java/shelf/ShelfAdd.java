package shelf;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShelfAdd")
public class ShelfAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShelfModel shelfModel;

    public ShelfAdd() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // Obtain data from the form
        String shelfId = request.getParameter("shelf_id");
        String shelfRow = request.getParameter("shelf_row");
        int shelfColumn = Integer.parseInt(request.getParameter("shelf_column"));

        // Create a Shelf object
        Shelf shelf = new Shelf(shelfId, shelfRow, shelfColumn);

        try {
            shelfModel = new ShelfModel();
            shelfModel.shelfAdd(shelf);
            response.sendRedirect("shelfView.jsp"); // Redirect to the shelf view page after adding a shelf
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error adding shelf. Please try again.");
        }
    }
}
