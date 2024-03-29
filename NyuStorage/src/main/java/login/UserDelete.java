package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserModel userModel;

    public UserDelete() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // Obtain the user ID to delete
        String userID = request.getParameter("userID");

        try {
            userModel = new UserModel();
            userModel.deleteUserByID(userID);
            response.sendRedirect("userView.jsp"); // Redirect to the user view page after deleting a user
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error deleting user. Please try again.");
        }
    }
}