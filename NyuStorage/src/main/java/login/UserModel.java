package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private Statement statement;
    private PreparedStatement ps;
    private Connection connection;

    public UserModel() {
        super();
    }

    public void initJDBC() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        // Connect to the database
        connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
        System.out.println("Database connected");

        // Create a statement
        statement = connection.createStatement();
    }
    

    public void closeJDBC() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean doesUserExist(String userId) throws SQLException, ClassNotFoundException {
        initJDBC();

        boolean exists = false;

        try {
            String sqlQuery = "SELECT COUNT(*) AS count FROM user WHERE userID = ?";

            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                exists = count > 0;
            }
        } catch (Exception ex) {
            System.out.println("doesUserExist(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }

        return exists;
    }

    public List<User> viewUsers() throws ClassNotFoundException, SQLException {
        initJDBC();

        List<User> userList = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM user");

            while (rs.next()) {
                User user = new User(
                        rs.getString("userID"),
                        rs.getString("userPassword"),
                        rs.getString("userName"),
                        rs.getString("userPhoneNo"),
                        rs.getString("userAddress"), // Get user address from database
                        rs.getString("userIC") // Get user IC from database
                );

                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println("viewUsers(): " + e);
            e.printStackTrace();
        } finally {
            closeJDBC();
        }

        return userList;
    }

    public void addUser(User user) throws ClassNotFoundException, SQLException {
        initJDBC();

        try {
            String sql = "INSERT INTO user (userID, userPassword, userName, userIC, userPhoneNo, userAddress) VALUES (?, ?, ?, ?, ?, ?)";

            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserID());
            ps.setString(2, user.getUserPassword());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getUserIC());
            ps.setString(5, user.getUserPhoneNo());
            ps.setString(6, user.getUserAddress());

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("addUser(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }

    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException {
        initJDBC();

        try {
            String sqlUpdate = "UPDATE user SET userPassword = ?, userName = ?, userIC = ?,  userPhoneNo = ?, userAddress = ? WHERE userID = ?";

            ps = connection.prepareStatement(sqlUpdate);
            ps.setString(1, user.getUserPassword());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getUserIC());
            ps.setString(4, user.getUserPhoneNo());
            ps.setString(5, user.getUserAddress());
            ps.setString(6, user.getUserID());

            int i = ps.executeUpdate();

            if (i > 0) {
                System.out.println("Record Updated Successfully");
            } else {
            	System.out.println("There is a problem in updating the record.");
            }
        } catch (Exception ex) {
            System.out.println("updateUser(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }
    }

    public User getUserByID(String userID) throws SQLException, ClassNotFoundException {
        initJDBC();

        User user = null;

        try {
            String sqlQuery = "SELECT * FROM user WHERE userID = ?";

            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, userID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("userID"),
                        rs.getString("userPassword"),
                        rs.getString("userName"),
                        rs.getString("userIC"),
                        rs.getString("userPhoneNo"),
                        rs.getString("userAddress")
                );
            }
        } catch (Exception ex) {
            System.out.println("getUserByID(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }

        return user;
    }
    public boolean userExists(String userId) throws ClassNotFoundException, SQLException {
        initJDBC();

        try {
            String sqlQuery = "SELECT COUNT(*) FROM users WHERE userId = ?";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println("userExists(): " + ex);
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return false;
    }

    public void deleteUserByID(String userID) throws SQLException, ClassNotFoundException {
        initJDBC();

        try {
            String sqlDelete = "DELETE FROM user WHERE userID = ?";

            ps = connection.prepareStatement(sqlDelete);
            ps.setString(1, userID);

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("deleteUserByID(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
    }
    
    public static int getTotalUsers() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int totalUsers = 0;

        try {
            conn = getConnection();
            String sql = "SELECT COUNT(userID) AS total_users FROM user";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalUsers = rs.getInt("total_users");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions as needed
        } finally {
            // Close the database resources
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return totalUsers;
    }

    
  
}
