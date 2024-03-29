package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserAdd")
public class UserAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserModel userModel;

    public UserAdd() {
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

        System.out.println("Received data from form: userID=" + userID + ", userName=" + userName + ", userIC=" + userIC);

        try {
            // Initialize the userModel object
            userModel = new UserModel();

            // Create a User object
            User user = new User(userID, userPassword, userName, userIC, userPhoneNo, userAddress);
            userModel.addUser(user);

            // User registration successful
            response.sendRedirect("userView.jsp");
        } catch (Exception e) {
            e.printStackTrace();

            // User registration failed
            response.sendRedirect("userView.jsp");
        }
    }

}