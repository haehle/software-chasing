import java.sql.*;

public class dbConnection {
    //Fields necessary for database connection
    private final static String url = "jdbc:mysql://localhost:3306/software_chasing";
    private final static String loginUsername = "java";
    private final static String loginPassword = "password";

    public static void main(String[] args) {
        /*
        - Currently only set up for local database; can test by creating local MySQL database schema
        and executing queries in "schema.txt" document
        - Will later establish cloud provider for MySQL database and connect to it here using JDBC
         */

        System.out.println("Connecting to database...");

        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {
                String insertUsers = "INSERT INTO Users VALUES (\"test\", \"test@purdue.edu\", \"password\");";
                String returnUsers = "SELECT * FROM Users;";
                String removeUsers = "DELETE FROM Users;";

                //Add user to table
                statement.execute(insertUsers);

                //Display users in table
                ResultSet rs = statement.executeQuery(returnUsers);

                while (rs.next()) {
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    System.out.println("USER: " + username + ", " + email + ", " + password);
                }

                //Remove user from table
                statement.execute(removeUsers);

                } catch (SQLException e){
                    throw new IllegalStateException("Could not create user", e);
                }

        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void addCharacter(Player player)
    {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                int locationX = player.getLocation()[0];
                int locationY = player.getLocation()[1];

                String query = "INSERT INTO Characters VALUES (" +
                        "\"" + player.getName() + "\", " +
                        "\"" + player.getUsername() + "\", " +
                        "\"" + locationX + "\", " +
                        "\"" + locationY + "\", " +
                        "\"" + player.getHp() + "\", " +
                        "\"" + player.getMaxHP() + "\", " +
                        "\"" + player.getSpeed() + "\", " +
                        "\"" + player.getStamina() + "\", " +
                        "\"" + player.getMaxStamina() + "\", " +
                        "\"" + player.getLevel() + "\", " +
                        "\"" + player.getLevelXP() + "\", " +
                        "\"" + player.getInitialLevelXP() + "\");";

                statement.execute(query);
                System.out.println("Character added successfully.");

            } catch (SQLException e) {
                throw new IllegalStateException("Could not create character", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void deleteCharacter(String username, String name)
    {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "DELETE FROM Characters WHERE Username = \"" + username + "\" AND Name = \"" +
                        name + "\"";

                statement.execute(query);
                System.out.println("Character deleted successfully.");

            } catch (SQLException e) {
                throw new IllegalStateException("Could not delete character", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void addUserProfile(Profile profile)
    {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "INSERT INTO Users VALUES (" +
                        "\"" + profile.getUsername() + "\", " +
                        "\"" + profile.getEmail() + "\", " +
                        "\"" + profile.getEncryptedPassword() + "\");";

                statement.execute(query);
                System.out.println("User profile added successfully.");

            } catch (SQLException e) {
                throw new IllegalStateException("Could not create user profile", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void deleteUserProfile(String username)
    {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "DELETE FROM Users WHERE Username = \"" + username + "\"";

                statement.execute(query);
                System.out.println("User profile deleted successfully.");

            } catch (SQLException e) {
                throw new IllegalStateException("Could not delete user profile", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }
}
