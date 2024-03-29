package shelf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShelfModel {
    private Statement statement;
    private PreparedStatement ps;
    private Connection connection;

    public ShelfModel() {
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
    public boolean doesShelfExist(String shelfId) throws SQLException, ClassNotFoundException {
        initJDBC();

        boolean exists = false;

        try {
            String sqlQuery = "SELECT COUNT(*) AS count FROM shelf WHERE shelf_id = ?";

            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, shelfId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                exists = count > 0;
            }
        } catch (Exception ex) {
            System.out.println("doesShelfExist(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }

        return exists;
    }

    public List<Shelf> shelfRead() throws ClassNotFoundException, SQLException {
        initJDBC();

        List<Shelf> shelfList = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM shelf");

            while (rs.next()) {
                Shelf shelf = new Shelf(
                        rs.getString("shelf_id"),
                        rs.getString("shelf_row"),
                        rs.getInt("shelf_column")
                );

                shelfList.add(shelf);
            }
        } catch (Exception e) {
            System.out.println("shelfRead(): " + e);
            e.printStackTrace();
        } finally {
            closeJDBC();
        }

        return shelfList;
    }

    public void shelfAdd(Shelf shelf) throws ClassNotFoundException, SQLException {
        initJDBC();

        try {
            String sql = "INSERT INTO shelf (shelf_id, shelf_row, shelf_column) VALUES (?, ?, ?)";

            ps = connection.prepareStatement(sql);
            ps.setString(1, shelf.getShelf_id());
            ps.setString(2, shelf.getShelf_row());
            ps.setInt(3, shelf.getShelf_column());

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("shelfAdd(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }
    }

    public void shelfUpdate(Shelf shelf) throws SQLException, ClassNotFoundException {
        initJDBC();

        try {
            String sqlUpdate = "UPDATE shelf SET shelf_row = ?, shelf_column = ? WHERE shelf_id = ?";

            ps = connection.prepareStatement(sqlUpdate);
            ps.setString(1, shelf.getShelf_row());
            ps.setInt(2, shelf.getShelf_column());
            ps.setString(3, shelf.getShelf_id());

            int i = ps.executeUpdate();

            if (i > 0) {
                System.out.println("Shelf Updated Successfully");
            } else {
                System.out.println("There is a problem in updating the shelf.");
            }
        } catch (Exception ex) {
            System.out.println("shelfUpdate(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }
    }
    public boolean shelfExists(String shelf_id) throws ClassNotFoundException, SQLException {
        initJDBC();

        try {
            String sqlQuery = "SELECT COUNT(*) FROM shelf WHERE shelf_id = ?";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, shelf_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println("shelfExists(): " + ex);
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return false;
    }


    public Shelf shelfReadById(String shelf_id) throws SQLException, ClassNotFoundException {
        initJDBC();

        Shelf shelf = null;

        try {
            String sqlQuery = "SELECT * FROM shelf WHERE shelf_id = ?";

            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, shelf_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                shelf = new Shelf(
                        rs.getString("shelf_id"),
                        rs.getString("shelf_row"),
                        rs.getInt("shelf_column")
                );
            }
        } catch (Exception ex) {
            System.out.println("shelfReadById(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }

        return shelf; // Add this line to return the Shelf object
    }
    
    public void deleteShelfByID(int shelf_id) throws SQLException, ClassNotFoundException {
        initJDBC();

        try {
            String sqlDelete = "DELETE FROM shelf WHERE shelf_id = ?";

            ps = connection.prepareStatement(sqlDelete);
            ps.setInt(1, shelf_id);

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("deleteShelfByID(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
    }
    
    public static int getTotalShelf() throws ClassNotFoundException, SQLException {
            
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int totalShelf = 0;
      

        try {
            //ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS total_shelf FROM shelf");
            conn = getConnection();
            String sql = "SELECT COUNT(shelf_id) AS total_shelf FROM shelf";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalShelf = rs.getInt("total_shelf");
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

        return totalShelf;
    }
    
}


