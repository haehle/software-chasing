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

        this.encryptedPassword = Util.EncryptPassword(p);


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

}
