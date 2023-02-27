import java.sql.*;

public class dbConnection {
    public static void main(String[] args) {
        /*
        - Currently only set up for local database; can test by creating local MySQL database schema
        and executing queries in "schema.txt" document
        - Will later establish cloud provider for MySQL database and connect to it here using JDBC
         */
        String url = "jdbc:mysql://localhost:3306/software_chasing";
        String loginUsername = "java";
        String loginPassword = "password";

        System.out.println("Connecting to database...");

        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement stmt = connection.createStatement()) {
                String insertUsers = "INSERT INTO Users VALUES (\"test\", \"test@purdue.edu\", \"password\");";
                String returnUsers = "SELECT * FROM Users;";
                String removeUsers = "DELETE FROM Users;";

                //Add user to table
                stmt.execute(insertUsers);

                //Display users in table
                ResultSet rs = stmt.executeQuery(returnUsers);

                while (rs.next()) {
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    System.out.println("USER: " + username + ", " + email + ", " + password);
                }

                //Remove user from table
                stmt.execute(removeUsers);

                } catch (SQLException e){
                    throw new IllegalStateException("Could not create user", e);
                }

        } catch ( SQLException e) {
            throw new IllegalStateException("Error occurred", e);
        }
    }
}
