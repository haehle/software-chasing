import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.*;

public class dbConnection {
    //Fields necessary for database connection
    private final static String url = "jdbc:mysql://34.68.213.44:3306/software-chasing";
    private final static String loginUsername = "root";
    private final static String loginPassword = "OU^_Eq^R@zr5rd^,";

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

            } catch (SQLException e) {
                throw new IllegalStateException("Could not create user", e);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void addPlayer(Player player) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                int locationX = player.getLocation()[0];
                int locationY = player.getLocation()[1];

                String query = "INSERT INTO Players VALUES (" +
                        "\"" + player.getName() + "\", " +
                        "\"" + player.getUsername() + "\", " +
                        "\"" + player.getPlayerClass() + "\", " +
                        "\"" + locationX + "\", " +
                        "\"" + locationY + "\", " +
                        "\"" + player.getHp() + "\", " +
                        "\"" + player.getMaxHP() + "\", " +
                        "\"" + player.getSpeed() + "\", " +
                        "\"" + player.getStamina() + "\", " +
                        "\"" + player.getMaxStamina() + "\", " +
                        "\"" + player.getLevel() + "\", " +
                        "\"" + player.getLevelXP() + "\", " +
                        "\"" + player.getInitialLevelXP() + "\", " +
                        "\"" + player.getCurrentLevelNo() + "\", " +
                        "\"" + player.getTimePlayed() + "\", " +
                        "\"" + player.getPoints() + "\");";

                statement.execute(query);
                System.out.println("Player added successfully.");

            } catch (SQLException e) {
                throw new IllegalStateException("Could not create player", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void deletePlayer(String username, String name) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "DELETE FROM Players WHERE Username = \"" + username + "\" AND Name = \"" +
                        name + "\"";

                statement.execute(query);
                System.out.println("Player deleted successfully.");

            } catch (SQLException e) {
                throw new IllegalStateException("Could not delete player", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void addUserProfile(Profile profile) {
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

    public static void deleteUserProfile(String username) {
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

    public static Profile login(String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String encryptedPassword = Util.EncryptPassword(password);
                String email = "";

                String query = "SELECT * FROM Users WHERE username=\"" + username +
                        "\" AND password=\"" + encryptedPassword + "\";";

                ResultSet rs = statement.executeQuery(query);

                if (!rs.isBeforeFirst()) {
                    //User not found
                    System.out.println("User with given credentials not found in database.");
                    return null;
                } else {
                    //User found
                    System.out.println("User with given credentials found in database.");

                    //Parse data
                    while (rs.next()) {
                        email = rs.getString("email");
                        System.out.println("EMAIL: " + email + "\nUSERNAME: " + username);
                    }

                    //Create new User object with fetched data
                    Profile profile = new Profile(email, username, password);
                    return profile;
                }

            } catch (SQLException e) {
                throw new IllegalStateException("Could not access user profiles for login", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static Player[] getPlayers(String username) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "SELECT * FROM Players WHERE username=\"" + username + "\";";

                Player[] players = {null, null, null};
                ResultSet rs = statement.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    //User not found
                    System.out.println("No players found for user.");
                    return players;
                } else {
                    //User found
                    System.out.println("Players found for user...");

                    //Parse data
                    int i = 0;
                    while (rs.next()) {
                        String name = rs.getString("name");

                        //Get inventory for player
                        Inventory inventory = new Inventory();
                        inventory = getInventory(username, name);

                        Player player = new Player(username, name, rs.getInt("playerClass"),
                                rs.getInt("locationX"), rs.getInt("locationY"), rs.getInt("hp"),
                                rs.getInt("maxHP"), rs.getInt("speed"), rs.getInt("stamina"),
                                rs.getInt("maxStamina"), rs.getInt("level"), rs.getInt("levelXP"),
                                rs.getInt("initialLevelXP"), inventory, rs.getLong("timePlayed"), rs.getLong("points"));

                        players[i] = player;

                        System.out.println("Existing player data " + (i + 1) + " fetched");
                        i++;
                    }

                    return players;
                }

            } catch (SQLException e) {
                throw new IllegalStateException("Could not access user profiles for login", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static Inventory getInventory(String username, String name) {
        Inventory inventory = new Inventory();

        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "SELECT * FROM InventoryItems WHERE username=\"" + username + "\" AND name=\"" + name + "\";";

                ResultSet rs = statement.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    //No inventory entries found
                    System.out.println("No inventory items found for specified player.");
                    return inventory;
                } else {
                    //Inventory entries found
                    System.out.println("Inventory items found for user...");

                    //Parse data
                    int i = 0;
                    while (rs.next()) {
                        //Get item ID
                        int id = rs.getInt("id");

                        //Look up item name by id
                        String itemName = getItemName(id);

                        if (itemName == null)
                        {
                            //Do not add to inventory
                        }
                        else
                        {
                            inventory.addItem(itemName);
                        }

                        System.out.println("Existing player data " + (i + 1) + " fetched");
                        i++;
                    }

                    return inventory;
                }

            } catch (SQLException e) {
                throw new IllegalStateException("Could not access user profiles for login", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }

    }

    public static String getItemName(int id) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "SELECT * FROM InventoryItems WHERE id=\"" + id + "\"";
                String itemName = null;

                ResultSet rs = statement.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    //Item not found
                    System.out.println("Item not found.");
                    return null;
                } else {
                    //Item found
                    System.out.println("Item found.");

                    //Parse data
                    while (rs.next()) {
                        itemName = rs.getString("name");
                        System.out.println("ITEM NAME: " + itemName);
                    }
                }

                    return itemName;
                } catch (SQLException e) {
                throw new IllegalStateException("Could not get inventory items from database", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static String getPlayerClassName(int id) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "SELECT * FROM PlayerClasses WHERE id=\"" + id + "\"";
                String className = null;

                ResultSet rs = statement.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    //Item not found
                    System.out.println("Class not found.");
                    return null;
                } else {
                    //Item found
                    System.out.println("Class found.");

                    //Parse data
                    while (rs.next()) {
                        className = rs.getString("name");
                        System.out.println("CLASS NAME: " + className);
                    }
                }

                return className;
            } catch (SQLException e) {
                throw new IllegalStateException("Could not get classes from database", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static int getNextId() {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "SELECT * FROM Items";
                String itemName = null;

                ResultSet rs = statement.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    //User not found
                    System.out.println("No items found.");
                    return 0;
                } else {
                    //Items found
                    System.out.println("Items found in database.");

                    //Parse data
                    int i = 0;
                    while (rs.next()) {
                        int num = rs.getInt("id");
                        i++;
                    }

                    return i;
                }
            } catch (SQLException e) {
                throw new IllegalStateException("Could not get items from database", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void updatePlayer(Player player) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                int locationX = player.getLocation()[0];
                int locationY = player.getLocation()[1];

                String query = "UPDATE Players SET LocationX=" + locationX + ", " +
                        "LocationY=" + locationY + ", " +
                        "HP=" + player.getHp() + ", " +
                        "MaxHP=" + player.getMaxHP() + ", " +
                        "Speed=" + player.getSpeed() + ", " +
                        "Stamina=" + player.getStamina() + ", " +
                        "MaxStamina=" + player.getMaxStamina() + ", " +
                        "Level=" + player.getLevel() + ", " +
                        "LevelXP=" + player.getLevelXP() + ", " +
                        "InitialLevelXP=" + player.getInitialLevelXP() + " " +
                        "WHERE Username=\"" + player.getUsername() + "\" " +
                        "AND Name=\"" + player.getName() + "\";";

                System.out.println(query);
                statement.execute(query);
                System.out.println("Player data updated successfully.");

            } catch (SQLException e) {
                throw new IllegalStateException("Could not update player data", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static boolean checkPassword(Profile profile, String encryptedPassword)
    {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "SELECT * FROM Users WHERE username=\"" + profile.getUsername() +
                        "\" AND password=\"" + encryptedPassword + "\";";

                ResultSet rs = statement.executeQuery(query);

                if (!rs.isBeforeFirst()) {
                    //User not found
                    System.out.println("Incorrect password.");
                    return false;
                } else {
                    return true;
                }

            } catch (SQLException e) {
                throw new IllegalStateException("Could not access user profile.", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void addItem(Item item) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "INSERT INTO Items VALUES (" +
                        "\"" + item.getId() + "\", " +
                        "\"" + item.getName() + "\");";

                statement.execute(query);
                System.out.println("Item added successfully.");

            } catch (SQLException e) {
                throw new IllegalStateException("Could not add item", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void deleteItem(int id) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "DELETE FROM Items WHERE id = \"" + id + "\"";

                statement.execute(query);
                System.out.println("Item deleted successfully.");

            } catch (SQLException e) {
                throw new IllegalStateException("Could not delete item", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }
}
