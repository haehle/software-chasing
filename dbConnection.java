import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.*;
import java.util.ArrayList;

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
                        "\"" + profile.getEncryptedPassword() + "\", " +
                        "FALSE);";

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
                boolean loggedIn = false;

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
                        loggedIn = rs.getBoolean("loggedIn");
                        System.out.println("EMAIL: " + email + "\nUSERNAME: " + username);
                    }

                    //Create new User object with fetched data
                    Profile profile = new Profile(email, username, password);

                    //Changed loggedIn status
                    //if (loggedIn)
                    //{
                    //    throw new IllegalStateException("User already logged in");
                    //} else {
                        //try (Statement newStatement = connection.createStatement()) {
                            //String newQuery = "UPDATE Users SET loggedIn = true WHERE username = '" + username + "';";

                            //statement.execute(newQuery);
                       //     System.out.println("loggedIn status changed successfully.");

                        //} catch (SQLException e) {
                        //    throw new IllegalStateException("Could not change loggedIn status", e);
                        //}

                        return profile;
                    //}
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

                        //Get skills for player
                        ArrayList<Skill> skills = new ArrayList<>();
                        skills = getSkills(username, name);

                        Player player = new Player(username, name, rs.getInt("playerClass"),
                                rs.getInt("locationX"), rs.getInt("locationY"), rs.getInt("hp"),
                                rs.getInt("maxHP"), rs.getInt("speed"), rs.getInt("stamina"),
                                rs.getInt("maxStamina"), rs.getInt("level"), rs.getInt("levelXP"),
                                rs.getInt("initialLevelXP"), inventory, rs.getLong("timePlayed"),
                                rs.getLong("points"), rs.getInt("currentLevelNo"),
                                rs.getInt("maxLevel"), skills);

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

                        //Get item description
                        String itemDesc = getItemDescription(itemName);

                        System.out.println("Item = " + itemName);

                        if (itemName == null)
                        {
                            //Do not add to inventory
                        }
                        else
                        {
                            Item newItem = new Item(id, itemName, itemDesc);
                            inventory.simpleAddItem(newItem);
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

    public static ArrayList<Skill> getSkills(String username, String name) {
        ArrayList<Skill> skills = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "SELECT * FROM PlayerSkills WHERE username=\"" + username + "\" AND name=\"" + name + "\";";

                ResultSet rs = statement.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    //No skill entries found
                    System.out.println("No skills found for specified player.");
                    return skills;
                } else {
                    //Skills found
                    System.out.println("Skills found for user...");

                    //Parse data
                    int i = 0;
                    while (rs.next()) {
                        //Get values from table
                        String skillName = rs.getString("skillName");
                        String description = getSkillDescription(skillName);

                        Skill skill = new Skill(skillName, description);
                        skills.add(skill);

                        System.out.println("Existing player data " + (i + 1) + " fetched");
                        i++;
                    }

                    return skills;
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

                String query = "SELECT * FROM Items WHERE id=\"" + id + "\"";
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
                throw new IllegalStateException("Could not get items from database", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static int getItemId(String name) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "SELECT * FROM Items WHERE name=\"" + name + "\"";

                ResultSet rs = statement.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    //Item not found
                    System.out.println("Item not found.");
                    Item newItem = new Item(Util.getNextId(), name, "");
                    addNewItem(newItem);
                    return newItem.getId();
                } else {
                    //Item found
                    System.out.println("Item found.");
                    int itemId = 0;

                    //Parse data
                    while (rs.next()) {
                        itemId = rs.getInt("id");
                        System.out.println("ITEM ID: " + itemId);
                    }
                    return itemId;
                }
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

                    return i+1;
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
                        "InitialLevelXP=" + player.getInitialLevelXP() + ", " +
                        "CurrentLevelNo=" + player.getCurrentLevelNo() + ", " +
                        "timePlayed=" + player.getTimePlayed() + ", " +
                        "points=" + player.getPoints() + " " +
                        "maxLevel=" + player.getMaxLevelNO() + " " +
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

    public static void addNewItem(Item item) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String query = "INSERT INTO Items VALUES (" +
                        "\"" + item.getId() + "\", " +
                        "\"" + item.getName() + "\", " +
                        "\"" + item.getDescription() + "\");";

                statement.execute(query);
                System.out.println("Item added successfully.");

            } catch (SQLException e) {
                return;
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
    public static void logout(String username)
    {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {
                String query = "UPDATE Users SET loggedIn = FALSE WHERE username = '" + username + "';";

                statement.execute(query);
                System.out.println("loggedIn status changed successfully.");

            } catch (SQLException e) {
                throw new IllegalStateException("Could not update player data", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void addInventoryItem(String username, String name, Item item)
    {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {
                String query = "INSERT INTO InventoryItems VALUES (" +
                        "\"" + item.getId() + "\", " +
                        "\"" + name + "\", " +
                        "\"" + username + "\");";

                statement.execute(query);
                System.out.println("Added item to player's inventory");
            } catch (SQLException e) {
                System.out.println("Item already in database for player");
                return;
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static boolean playerHasItem(String username, String name, Item item)
    {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {
                String query = "SELECT * FROM InventoryItems WHERE Username = \"" + username + "\" AND Name = \"" +
                        name + "\" AND id = \"" + item.getId() + "\"";

                ResultSet rs = statement.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    //Item not found
                    System.out.println("Player does not have item yet");
                    return false;
                } else {
                    //Item found
                    System.out.println("Player already has item");
                    return true;
                }
            } catch (SQLException e) {
                throw new IllegalStateException("Error occurred while fetching player items", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static String getItemDescription(String itemName) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String itemDesc = "";
                String query = "SELECT * FROM Items WHERE name=\"" + itemName + "\"";

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
                        itemDesc = rs.getString("description");
                    }
                }

                return itemDesc;
            } catch (SQLException e) {
                throw new IllegalStateException("Could not get items from database", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static String getSkillDescription(String skillName) {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {

                String skillDesc = "";
                String query = "SELECT * FROM Skills WHERE name=\"" + skillName + "\"";

                ResultSet rs = statement.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    //Skill not found
                    System.out.println("Skill not found.");
                    return null;
                } else {
                    //Skill found
                    System.out.println("Skill found.");

                    //Parse data
                    while (rs.next()) {
                        skillDesc = rs.getString("description");
                    }
                }

                return skillDesc;
            } catch (SQLException e) {
                throw new IllegalStateException("Could not get skills from database", e);
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }

    public static void removeInventoryItem(String username, String name, String itemName)
    {
        try (Connection connection = DriverManager.getConnection(url, loginUsername, loginPassword)) {

            //Connected to database
            System.out.println("Connected to database successfully.");

            try (Statement statement = connection.createStatement()) {
                String query = "DELETE FROM InventoryItems WHERE username = \"" + username + "\"" +
                        " AND name = \"" + name + "\" AND id = " + dbConnection.getItemId(itemName) + ";";

                statement.execute(query);
                System.out.println("Removed " + itemName + " item from player's inventory");
            } catch (SQLException e) {
                System.out.println("Item not in database for player's inventory");
                return;
            }


        } catch (SQLException e) {
            throw new IllegalStateException("Error occurred while connecting to database", e);
        }
    }
}
