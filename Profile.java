// Code adapted from javatpoint

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;


public class Profile {


    private String email;

    private String username;

    private String password;

    private String encryptedPassword;

    public Profile(String e, String u, String p) {
        this.email = e;
        this.username = u;
        this.password = p;

        this.encryptedPassword = EncryptPassword(p);


    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }





    public String EncryptPassword(String p) {
        try {
            // MessageDigest instance for MD5
            MessageDigest m = MessageDigest.getInstance("MD5");

            // Add plain-text password bytes to digest using MD5 update() method
            m.update(password.getBytes());

            // Convert the hash value into bytes
            byte[] bytes = m.digest();

            // The bytes array has bytes in decimal form. Converting it into hexadecimal format
            StringBuilder s = new StringBuilder();
            for (int i=0; i< bytes.length ;i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Complete hashed password in hexadecimal format
            encryptedPassword = s.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        // Display the unencrypted and encrypted passwords
            System.out.println("Plain-text password: " + password);
            System.out.println("Encrypted password using MD5: " + encryptedPassword);

            return encryptedPassword;
    }



}
