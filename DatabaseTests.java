public class DatabaseTests {

    public static void main(String[] args) {
        //Modify method calls in this function to change test
        //NewPlayerTest();
        //NewUserProfileTest();
        LoginTest();
    }

    //Class created to test player creation/deletion process
    public static void NewPlayerTest() {
        Util.createPlayer("test", "Tester", 1);
        Util.deletePlayer("test", "Tester");
    }

    public static void NewUserProfileTest() {
        Util.createProfile("test@test.com", "test", "password");
        Util.deleteProfile("test");
    }

    public static void LoginTest() {
        Util.createProfile("test@test.com", "test", "password");
        Profile profile = dbConnection.login("test", "password");
        Util.deleteProfile("test");
    }
}
