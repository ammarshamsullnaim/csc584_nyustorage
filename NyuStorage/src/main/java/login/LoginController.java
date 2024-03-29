package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String userPassword = request.getParameter("userPassword");

        // Check userID and userPass with record from the database
        boolean isValidUser = validateUser(userID, userPassword);

        if (isValidUser) {
            HttpSession session = request.getSession();
            session.setAttribute("status", "loggedin");
            session.setAttribute("success", "You've successfully logged in.");

            // TODO: Set the role based on the user record retrieved from the database

            response.sendRedirect("index.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("danger", "Login failed... Please check your id and password before trying again.");
            response.sendRedirect("login.jsp");
        }
    }

    private boolean validateUser(String userID, String userPassword) {
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");

            // Prepare the SQL statement to retrieve the user credentials
            String sql = "SELECT * FROM user WHERE userID = ? AND userPassword = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, userPassword);

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();

            // Check if any matching records are found
            boolean isValidUser = resultSet.next();

            // Close the database resources
            resultSet.close();
            statement.close();
            conn.close();

            return isValidUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

   
}