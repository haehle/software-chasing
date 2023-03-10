import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Util {
    //Utility class for static methods
    public static void createPlayer(String username, String name, int type) {
        Player player = new Player(username, name, type);
        dbConnection.addPlayer(player);
    }

    public static void deletePlayer(String username, String name) {
        dbConnection.deletePlayer(username, name);
    }

    public static void createProfile(String email, String username, String password) {
        Profile profile = new Profile(email, username, password);
        dbConnection.addUserProfile(profile);
    }

    public static void deleteProfile(String username) {
        dbConnection.deleteUserProfile(username);
    }

    public static boolean checkPassword(Profile profile, String password)
    {
        String encryptedPassword = EncryptPassword(password);
        return dbConnection.checkPassword(profile, encryptedPassword);
    }

    public static String EncryptPassword(String p) {
        String encryptedPassword = "";
        try {
            // MessageDigest instance for MD5
            MessageDigest m = MessageDigest.getInstance("MD5");

            // Add plain-text password bytes to digest using MD5 update() method
            m.update(p.getBytes());

            // Convert the hash value into bytes
            byte[] bytes = m.digest();

            // The bytes array has bytes in decimal form. Converting it into hexadecimal format
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Complete hashed password in hexadecimal format
            encryptedPassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Display the unencrypted and encrypted passwords
        System.out.println("Plain-text password: " + p);
        System.out.println("Encrypted password using MD5: " + encryptedPassword);

        return encryptedPassword;
    }
}
