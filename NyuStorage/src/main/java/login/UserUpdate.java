package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserModel userModel;

    public UserUpdate() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

     // Obtain data from the form
        String userID = request.getParameter("userID");
        String userPassword = request.getParameter("userPassword");
        String userName = request.getParameter("userName");
        String userIC = request.getParameter("userIC");
        String userPhoneNo = request.getParameter("userPhoneNo");
        String userAddress = request.getParameter("userAddress");
        

        // Create a new User object
        User user = new User(userID, userPassword, userName, userIC, userPhoneNo,userAddress);

        try {
            userModel = new UserModel();
            userModel.updateUser(user);
            response.sendRedirect("userView.jsp"); // Redirect to the user view page after updating the user
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error updating user. Please try again.");
        }
    }
}